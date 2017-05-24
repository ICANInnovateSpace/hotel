package com.ican.hotel.controller;

import com.ican.hotel.beans.Order;
import com.ican.hotel.beans.Room;
import com.ican.hotel.beans.User;
import com.ican.hotel.service.IOrderManager;
import com.ican.hotel.service.IRoomManager;
import com.ican.hotel.service.IUserManager;
import com.ican.hotel.utils.OrderProcessUitl;
import com.ican.hotel.utils.ResultResponseUtil;
import com.ican.hotel.validation.ValidGroup_3;
import com.ican.hotel.validation.ValidGroup_4;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrzhou on 17-2-18.
 * 订单控制器
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource(name = "orderManager")
    private IOrderManager orderManager;
    @Resource(name = "roomManager")
    private IRoomManager roomManager;
    @Resource(name = "userManager")
    private IUserManager userManager;

    /**
     * 转到下单页面
     *
     * @return 下单页面
     */
    @RequestMapping("/toGetPayUrl")
    public String toGetPayUrl() {
        return "/getPayUrl";
    }

    @RequestMapping("/toQueryUserOrder")
    public String toQueryOrder() { return "/queryUserOrder"; }

    @RequestMapping("/toDownOrder")
    public String toDownOrder(){ return "/downOrder"; }

    /**
     * 生成统一下单的url
     *
     * @param order         订单对象
     * @param bindingResult 绑定结果
     * @param response      http响应
     */
    @RequestMapping(value = "/getPayUrl", method = RequestMethod.POST)
    public void getPayUrl(@Validated(value = {ValidGroup_3.class}) @ModelAttribute Order order, BindingResult bindingResult, HttpServletResponse response) throws ParseException {
        //表单校验
        if (bindingResult.hasErrors()) {
            ResultResponseUtil.fail(bindingResult, response);
        } else {
            Room room = roomManager.queryByRid(order.getRoom().getRid());
            User user = userManager.queryByUid(order.getOuid());
            if (room == null) {//检查是否为非法客房号
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_orid", "非法客房号");
                ResultResponseUtil.returnJson(response, data);
            } else if (user == null) {//检查是否为非法用户id
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_ouid", "非法用户id");
                ResultResponseUtil.returnJson(response, data);
            } else {
                //插入退房时间和总金额
                OrderProcessUitl.setQuitDateAndTotal(room, order);
                //查出此房号的订单
                List<Order> orders = orderManager.queryByOrid(order.getRoom().getRid());
                //判断此订单的房是否为空房
                if (OrderProcessUitl.isFreeRoom(order, orders)) {
                    String payUrl = OrderProcessUitl.getPayUrl(room, order);
                    if (payUrl == null){
                        //统一下单失败
                        Map<String, Object> data = new HashMap<>();
                        data.put("state_code", "0");
                        data.put("result", "FAIL");
                        data.put("failure", "统一下单失败");
                        ResultResponseUtil.returnJson(response, data);
                    }else {
                        order.setRoom(room);
                        order.setWxPayUrl(payUrl);
                        ResultResponseUtil.returnJson(response, order);
                    }
                } else {//非空房
                    Map<String, Object> data = new HashMap<>();
                    data.put("state_code", "0");
                    data.put("result", "FAIL");
                    data.put("err_orid", "该客房已被预定");
                    ResultResponseUtil.returnJson(response, data);
                }
            }
        }
    }

    /**
     * 下单
     *
     * @param order 订单信息
     * @param bindingResult 绑定结果
     * @param response http响应
     * */
    @RequestMapping(value = "/downOrder",method = RequestMethod.POST)
    public void downOrder(@Validated(value = {ValidGroup_4.class}) @ModelAttribute Order order, BindingResult bindingResult, HttpServletResponse response) throws ParseException {
        //表单校验
        if (bindingResult.hasErrors()) {
            ResultResponseUtil.fail(bindingResult, response);
        } else {
            Room room = roomManager.queryByRid(order.getRoom().getRid());
            User user = userManager.queryByUid(order.getOuid());
            if (room == null) {//检查是否为非法客房号
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_orid", "客房不存在");
                ResultResponseUtil.returnJson(response, data);
            } else if (user == null) {//检查是否为非法用户id
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_ouid", "用户不存在");
                ResultResponseUtil.returnJson(response, data);
            } else {
                //查出此房号的订单
                List<Order> orders = orderManager.queryByOrid(room.getRid());
                //判断此订单的房是否为空房
                if (OrderProcessUitl.isFreeRoom(order, orders)) {
                    order.setRoom(room);
                    if (orderManager.add(order)) {
                        user.setUstate("1");
                        userManager.update(user);
                        ResultResponseUtil.returnJson(response,userManager.queryByUid(order.getOuid()));
                    } else {
                        //下单失败，单号已存在
                        Map<String, Object> data = new HashMap<>();
                        data.put("state_code", "0");
                        data.put("result", "FAIL");
                        data.put("exist_oid", "订单号已存在");
                        ResultResponseUtil.returnJson(response, data);
                    }
                } else {//非空房
                    Map<String, Object> data = new HashMap<>();
                    data.put("state_code", "0");
                    data.put("result", "FAIL");
                    data.put("err_orid", "房间已被预定");
                    ResultResponseUtil.returnJson(response, data);
                }
            }
        }
    }

    /**
     * 根据用户id查询用户订房订单
     *
     * @param response http响应
     * @param uid 用户id
     * */
    @RequestMapping(value = "/queryOrder",method = RequestMethod.POST)
    public void queryOrder(String uid,HttpServletResponse response){
        //非空校验
        if (uid == null || uid.equals("")){
            Map<String, Object> data = new HashMap<>();
            data.put("state_code", "0");
            data.put("result", "FAIL");
            data.put("null_uid", "用户号为空");
            ResultResponseUtil.returnJson(response, data);
        }else {
            User user = userManager.queryByUid(uid);
            if (user == null){
                //用户id不存在
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_uid", "非法用户号");
                ResultResponseUtil.returnJson(response, data);
            }else {
                //返回用户的订单
                ResultResponseUtil.returnJson(response,user.getOrders());
            }
        }
    }
}
