package com.ican.hotel.controller;

import com.ican.hotel.service.IRoomManager;
import com.ican.hotel.utils.ResultResponseUtil;
import com.ican.hotel.validation.beans.ValidateOrder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrzhou on 17-2-15.
 * 客房操作控制器
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource(name = "roomManager")
    private IRoomManager roomManager;

    public void setRoomManager(IRoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * 转到查询客房的页面
     *
     * @return 客房查询页面
     * */
    @RequestMapping(value = "/toQuery",method = RequestMethod.GET)
    public String toQuery(){
        return "/queryOrder";
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void query(@Validated ValidateOrder order, BindingResult bindingResult, HttpServletResponse response){
        //表单校验
        if (bindingResult.hasErrors()){
            Map<String,Object> data = new HashMap<>();
            data.put("state_code","0");
            data.put("result","FAIL");
            for (FieldError fieldError :
                    bindingResult.getFieldErrors()) {
                data.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            ResultResponseUtil.returnJson(response,data);
        }else {

        }
    }
}
