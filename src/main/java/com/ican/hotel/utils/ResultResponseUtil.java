package com.ican.hotel.utils;

import com.google.gson.Gson;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrzhou on 17-2-10.
 * 返回响应结果工具类
 */
public class ResultResponseUtil {
    /**
     * 将封装的javaBean或Map对象数据转换成json格式返回到客户端
     *
     * @param response      http响应
     * @param javaBeanOrMap 需要返回的数据
     */
    public static void returnJson(HttpServletResponse response, Object javaBeanOrMap) {
        PrintWriter writer = null;
        //设置响应回去的编码，不设置会乱码
        response.setCharacterEncoding("UTF-8");
        try {
            //获取输出流
            writer = response.getWriter();
            //获取Gson对象，将javaBean转换成json
            Gson gson = new Gson();
            String json = gson.toJson(javaBeanOrMap);
            //返回json数据
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 默认成功的响应
     * 默认值：{"state_code":"1","result":"SUCCESS"}
     */
    public static void success(HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        data.put("state_code", "1");
        data.put("result", "SUCCESS");
        ResultResponseUtil.returnJson(response, data);
    }

    /**
     * 默认失败的响应
     * 默认值：{"state_code":"1","result":"SUCCESS"}
     */
    public static void fail(HttpServletResponse response){
        Map<String, Object> data = new HashMap<>();
        data.put("state_code", "0");
        data.put("result", "FAIL");
        ResultResponseUtil.returnJson(response, data);
    }

    /**
     * 附加信息的失败的响应
     * 值：{"state_code":"0","result":"FAIL",....}
     */
    public static void fail(BindingResult bindingResult, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        data.put("state_code", "0");
        data.put("result", "FAIL");
        for (FieldError fieldError :
                bindingResult.getFieldErrors()) {
            data.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ResultResponseUtil.returnJson(response, data);
    }
}
