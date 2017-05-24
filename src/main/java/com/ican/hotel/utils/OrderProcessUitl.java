package com.ican.hotel.utils;

import com.ican.hotel.beans.Order;
import com.ican.hotel.beans.Room;
import com.ican.wxpay.lib.WechatPayData;
import com.ican.wxpay.lib.WechatPayException;
import com.ican.wxpay.nativePay.NativePay;
import com.ican.wxpay.util.OutTradeNo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by mrzhou on 17-2-17.
 * 订单相关处理工具类
 */
public class OrderProcessUitl {
  private static final int S = 1000;
  private static final int M = 60 * S;
  private static final int H = 60 * M;
  private static final int D = 24 * H;
  private static final int SINGLE = 168;
  private static final int DOUBLE = 188;
  private static final int HOME = 218;

  /**
   * 根据下单入住日期时间和订房天数算出退房日期时间
   * 计算规则：订房的时间若在12点前，则到当天12点
   * 算作一天，否则到第二天12点算作一天。
   *
   * @param orderDate 下单的日期
   * @param days      订房天数
   * @return 退房日期
   */
  public static String getOrderQuitDate(Date orderDate, String days) throws ParseException {
    //日期格式
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //取得下单入住日期
    String format = simpleDateFormat.format(orderDate);
    String[] split = format.split(" ");
    //用于判断下单时间是否在12点之前
    Date currentDate = simpleDateFormat.parse(split[0] + " 12:00");
    //将字符串类型的天数转换成int类型
    int day = Integer.parseInt(days);
    //判断入住时间是否在12点前，若在12点前，到当天12点算作一天
    if (orderDate.getTime() < currentDate.getTime()) {
      day -= 1;
    }
    long quit = orderDate.getTime() + day * D;
    //算出退房的日期
    return new SimpleDateFormat("yyyy-MM-dd 12:00").format(new Date(quit));
  }

  /**
   * 用订单列表中已有的订单的入住时间及退房时间
   * 与查询的入住时间及入住天数来比较判断空房，
   * 从而返回空房。
   *
   * @param rooms     房间列表
   * @param orders    订单列表
   * @param orderDate 查询入住时间
   * @param days      入住天数
   * @return 客房列表
   */
  public static List<Room> getFreeRooms(List<Room> rooms, List<Order> orders, Date orderDate, String days) throws ParseException {
    //若客房表为空，则表示连房间都没有，哪来的空房
    if (rooms == null)
      return null;
    //若订单记录表为空，则表示没人订房，直接返回客房表中所有的记录
    if (orders == null)
      return rooms;
    //获取查询的退房时间
    String orderQuitDate = OrderProcessUitl.getOrderQuitDate(orderDate, days);
    Date parseQuitDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(orderQuitDate);
    //循环迭代取出订单表中的入住时间与退房时间，与当前查询的入住时间和退房时间比较
    for (Order order :
            orders) {
      Date odate = order.getOdate();
      Date oquit = order.getOquit();
      //如果订房时间有冲突
      if (!((odate.getTime() < orderDate.getTime() && oquit.getTime() < orderDate.getTime())
              || (odate.getTime() > orderDate.getTime() && odate.getTime() > parseQuitDate.getTime()))) {
        //取出有冲突的订单的客房号
        String orid = order.getRoom().getRid();
        //将对应的客房号的客房从rooms中移除
        //循环迭代找到匹配的客房号的客房
        rooms.removeIf(room -> orid.equals(room.getRid()));
      }
    }
    return rooms;
  }

  /**
   * 判断对应房号的入住日期与退房日期是否有冲突
   * 若有冲突则表示该房已经有人住了。
   *
   * @param order 当前下单的订单
   * @param orders 根据当前订单中的客房号查出来的订单列表
   * @return 是否空房
   * */
  public static boolean isFreeRoom(Order order, List<Order> orders) throws ParseException {

    //若根据房号查出来的订单记录为0，则表示这间房没人订
    if (orders == null || orders.size() == 0) {
      return true;
    }
    //循环迭代是否有冲突时间的订单
    for (Order o :
            orders) {
      if (!((o.getOdate().getTime() < order.getOdate().getTime() && o.getOquit().getTime() < order.getOdate().getTime())
              || (o.getOdate().getTime() > order.getOdate().getTime() && o.getOdate().getTime() > order.getOquit().getTime()))) {
        return false;
      }
    }
    return true;
  }

  /**
   * 设置订单的退房日期和总金额
   *
   * @param room 订单指定的客房对象
   * @param order 订单对象
   * */
  public static void setQuitDateAndTotal(Room room, Order order) throws ParseException {
    //取得当前订单的入住时间并计算退房时间
    Date orderOdate = order.getOdate();
    String odays = order.getOdays();
    String orderQuitDate = OrderProcessUitl.getOrderQuitDate(orderOdate, odays);
    Date parseQuitDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(orderQuitDate);
    order.setOquit(parseQuitDate);
    //判断房间类型，设置价钱
    String rtype = room.getRtype();
    switch (rtype) {
      case "1":
        order.setOtotal(SINGLE * Integer.parseInt(odays) + "");
        break;
      case "2":
        order.setOtotal(DOUBLE * Integer.parseInt(odays) + "");
        break;
      default:
        order.setOtotal(HOME * Integer.parseInt(odays) + "");
        break;
    }
  }

  /**
   * 获取微信支付统一下单生成的url
   * 用于生成二维码，微信扫码支付
   *
   * @param room 下单的房间
   * @param order 订单对象
   * @return 用于扫码支付的url，统一下单失败时返回null
   * */
  public static String getPayUrl(Room room, Order order){
    NativePay nativePay = new NativePay();
    String outTradeNo = OutTradeNo.generateOutTradeNo();
    WechatPayData wechatPayData = new WechatPayData();
    wechatPayData.addSubData("product_id","123");
    wechatPayData.addSubData("out_trade_no", outTradeNo);
    wechatPayData.addSubData("body","预定客房");
    wechatPayData.addSubData("total_fee",/*room.getRprice()*/"1");
    wechatPayData.addSubData("trade_type","NATIVE");
    /*Date curDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    long cutTime = curDate.getTime();
    long timeExpire = cutTime + M * 5;
    wechatPayData.addSubData("time_start",dateFormat.format(curDate));
    wechatPayData.addSubData("time_expire",dateFormat.format(new Date(timeExpire)));*/
    order.setWxNO(outTradeNo);
    try {
      wechatPayData = nativePay.unifiedOrder(wechatPayData);
    } catch (WechatPayException e) {
      e.printStackTrace();
    }
    return "SUCCESS".equals(wechatPayData.getDataByKey("return_code")) && "SUCCESS".equals(wechatPayData.getDataByKey("result_code"))?(String)wechatPayData.getDataByKey("code_url"):null;
  }

  /**
   * 微信支付订单查询
   *
   * @param order 订单信息//临时使用other1这个属性
   * @return 是否查询到订单
   * */
  public static boolean queryOrder(Order order) {
    NativePay nativePay = new NativePay();
    WechatPayData wechatPayData = new WechatPayData();
    wechatPayData.addSubData("out_trade_no", order.getWxNO());
    try {
      wechatPayData = nativePay.queryOrder(wechatPayData);
    } catch (WechatPayException e) {
      e.printStackTrace();
    }
    return "SUCCESS".equals(wechatPayData.getDataByKey("return_code")) && "SUCCESS".equals(wechatPayData.getDataByKey("result_code")) && "SUCCESS".equals(wechatPayData.getDataByKey("trade_state"));
  }
}