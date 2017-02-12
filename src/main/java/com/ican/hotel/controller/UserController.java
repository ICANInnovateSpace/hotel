package com.ican.hotel.controller;

import com.ican.hotel.beans.User;
import com.ican.hotel.service.IUserManager;
import com.ican.hotel.utils.ResultResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mrzhou on 17-2-9.
 * 用户操作Controller
 * 包括、增、删、查、改
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //注入spring-core核心容器中的bean（含事务管理）
    @Resource(name = "userManager")
    private IUserManager userManager;

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * 转到登陆页面
     *
     * @return 登陆页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 转到更新页面
     *
     * @return 更新页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate() {
        return "update";
    }

    /**
     * 用户注册
     * 访问url：http：//ipAddress：8080/user/register
     * 响应注册成功或失败的json数据
     *
     * @param user     用户数据
     * @param response http响应
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(User user, HttpServletResponse response) {
        if (userManager.add(user)) {
            //注册成功
            ResultResponseUtil.success(response);
        } else {
            //注册失败
            ResultResponseUtil.fail(response);
        }
    }

    /**
     * 用户登陆
     * 访问url：http：//ipAddress：8080/user/login
     * 登陆成功响应用户信息/失败响应默认失败信息（json格式）
     *
     * @param user     用户数据
     * @param response http响应
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(User user, HttpServletResponse response) {
        //验证用户名、密码
        User queryUser = userManager.query(user.getUname(), user.getUpsw());
        if (queryUser == null) {
            //登陆失败
            ResultResponseUtil.fail(response);
        } else {
            //登陆成功
            ResultResponseUtil.returnJson(response, queryUser);
        }
    }

    /**
     * 更新用户数据
     * 访问url：http：//ipAddress：8080/user/update
     * 成功返回用户信息/失败返回默认失败信息（json格式）
     *
     * @param user     用户数据
     * @param request  http请求
     * @param response http响应
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先查询是否含有该用户
        User queryUser = userManager.query(user.getUname());
        if (queryUser == null) {
            ResultResponseUtil.fail(response);
        } else {
            //将从数据库中查询到的id赋值给新信息user对象，因为update操作要用到
            user.setUid(queryUser.getUid());
            //将数据库查到的密码赋值给信息user对象，因为修改资料没有修改密码
            user.setUpsw(queryUser.getUpsw());
            //创建一个多功能（自己理解的意思）解析器，可解析出请求中是否包含文件上传
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
            //判断是否是multipart请求，即是否包含有文件上传等操作
            if (commonsMultipartResolver.isMultipart(request)) {
                //存储照片（有上传则存储，没有则不会存储）
                this.storePhoto(user,request);
            }
            //更新用户信息
            userManager.update(user);
            //响应信息
            ResultResponseUtil.returnJson(response, user);
        }
    }

    /**
     * 在判断是multipart之后，我把存储文件的过程拿出来封装成方法了，
     * 这样update的方法看起来舒服些。
     *
     * @param user 代更新用户信息
     * @param request http请求
     * */
    private void storePhoto(User user,HttpServletRequest request) throws IOException {
        //转换请求类型
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //取得文件名称的迭代器
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            if ("photo".equals(fileName)) {
                //取得头像的照片文件
                MultipartFile file = multipartRequest.getFile(fileName);
                if (file != null) {
                    //存储照片到指定文件夹
                    //首先定义照片的存储路径
                    String uploadPath = request.getRealPath("/upload");
                    String userPath = uploadPath + "/" + user.getUname();
                    String photoName = userPath + "/" + file.getOriginalFilename();
                    File userPathDir = new File(userPath);
                    File photoFile = new File(photoName);
                    //判断用户目录是否存在
                    if (!userPathDir.exists()) {
                        //不存在则创建
                        userPathDir.mkdirs();
                    }
                    //判断该文件是否存在
                    if (!photoFile.exists()) {
                        //文件不存在则创建
                        photoFile.createNewFile();
                    }
                    //将文件存储到该路径
                    file.transferTo(photoFile);
                    //把照片的路径信息存到用户数据
                    user.setUphoto(photoName);
                }
            }
        }
    }

}
