package com.ican.hotel.controller;

import com.ican.hotel.beans.Order;
import com.ican.hotel.beans.Room;
import com.ican.hotel.service.IOrderManager;
import com.ican.hotel.service.IRoomManager;
import com.ican.hotel.service.IUserManager;
import com.ican.hotel.utils.OrderProcessUitl;
import com.ican.hotel.utils.ResultResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
     * */
    @RequestMapping("/toDownOrder")
    public String toDownOrder(){
        return "/downOrder";
    }

    /**
     * 下单，预定房间
     *
     * @param order 订单对象
     * @param bindingResult 绑定结果
     * @param response http响应
     * */
    @RequestMapping(value = "/downOrder",method = RequestMethod.POST)
    public void downOrder(@Validated Order order, BindingResult bindingResult, HttpServletResponse response) throws ParseException {
        //表单校验
        if (bindingResult.hasErrors()){
            ResultResponseUtil.fail(bindingResult, response);
        }else {
            //检查是否为非法客房号
            Room room = roomManager.queryByRid(order.getOrid());
            if (room == null) {
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_orid", "非法客房号");
                ResultResponseUtil.returnJson(response, data);
            }else if (userManager.queryByUid(order.getOuid()) == null){
                Map<String, Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("illegal_ouid", "非法用户id");
                ResultResponseUtil.returnJson(response, data);
            } else {
                //插入退房时间和总金额
                OrderProcessUitl.setQuitDateAndTotal(room,order);
                //查出此房号的订单
                List<Order> orders = orderManager.queryByOrid(order.getOrid());
                //判断此订单的房是否为空房
                if (OrderProcessUitl.isFreeRoom(order, orders)) {
                    //若为空房则下单
                    if (orderManager.add(order)) {
                        //下单成功
                        ResultResponseUtil.returnJson(response, order);
                    } else {//下单失败，单号已存在
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
                    data.put("err_orid", "该客房已被预定");
                    ResultResponseUtil.returnJson(response, data);
                }
            }
        }
    }
}
