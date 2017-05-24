package com.ican.hotel.controller;

import com.ican.hotel.beans.Order;
import com.ican.hotel.beans.Room;
import com.ican.hotel.service.IOrderManager;
import com.ican.hotel.service.IRoomManager;
import com.ican.hotel.utils.OrderProcessUitl;
import com.ican.hotel.utils.ResultResponseUtil;
import com.ican.hotel.validation.beans.ValidateOrder;
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
 * Created by mrzhou on 17-2-15.
 * 客房操作控制器
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource(name = "roomManager")
    private IRoomManager roomManager;
    @Resource(name = "orderManager")
    private IOrderManager orderManager;

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
    public void query(@Validated ValidateOrder order, BindingResult bindingResult, HttpServletResponse response) throws ParseException {
        //表单校验
        if (bindingResult.hasErrors()){
            ResultResponseUtil.fail(bindingResult, response);
        }else {
            String rtype = order.getRtype();
            if (rtype !=null && !"1".equals(rtype) && !"2".equals(rtype) && !"3".equals(rtype)){
                Map<String,Object> data = new HashMap<>();
                data.put("state_code", "0");
                data.put("result", "FAIL");
                data.put("err_rtype","错误的房间类型");
                ResultResponseUtil.returnJson(response,data);
            }else{
                List<Room> rooms ;
                //若选择了房间类型，则查询某类房间，否则查询全部房间
                if (rtype ==null){
                    rooms = roomManager.getRooms();
                }else {
                    rooms = roomManager.queryByRtype(rtype);
                }
                //查询全部订单
                List<Order> orders = orderManager.getOrders();
                List<Room> freeRooms = OrderProcessUitl.getFreeRooms(rooms,orders,order.getOrderDate(),order.getDays());
                if (freeRooms == null){
                    Map<String,Object> data = new HashMap<>();
                    data.put("state_code", "1");
                    data.put("result", "SUCCESS");
                    data.put("full","客房已满");
                    ResultResponseUtil.returnJson(response,data);
                }else {
                    ResultResponseUtil.returnJson(response,freeRooms);
                }
            }
        }
    }
}
