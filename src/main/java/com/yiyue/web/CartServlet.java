package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.*;
import com.yiyue.service.CartService;
import com.yiyue.service.ReportService;
import com.yiyue.util.SendMail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{
    private CartService cartService = new CartService();

    /*---------------------------购物车------------------------------*/
    /*查询购物车*/
    public void selectCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*查询购物车商品*/
        List<CartGood> goods = cartService.selectCart(user.getId());
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    public void selectCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取section用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*查询购物车的商品数量*/
        Integer count = cartService.selectCount(user.getId());
        String jsonString = JSON.toJSONString(count);
        response.getWriter().write(jsonString);
    }


    /*添加购物车*/
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*查看当前用户是否有该商品*/
        Cart c = cartService.selectBy2Id(user.getId(), cart.getGoodid());

        if(c!=null)
        {
            /*更新购物车表加一*/
            Integer num = c.getNum();
            c.setNum(num+1);
            cartService.update(c);
        }
        else
        {
            cart.setNum(1);
            cart.setUserid(user.getId());
            cartService.add(cart);
        }
        response.getWriter().write("success");
    }
    /*删减购物车*/
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*查看当前用户是否有该商品*/
        Cart c = cartService.selectBy2Id(user.getId(), cart.getGoodid());

        if(c.getNum()>1)
        {
            /*更新购物车表加一*/
            Integer num = c.getNum();
            c.setNum(num-1);
            cartService.update(c);
        }
        else
        {
            cart.setUserid(user.getId());
            cartService.delete(cart);
        }
        response.getWriter().write("success");
    }

    public void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*添加进用户信息，做表的删除*/
        cart.setUserid(user.getId());
        cartService.delete(cart);

        /*添加日期信息，并设置*/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        cart.setDate(dateNowStr);

        /*插入表*/
        ReportService reportService = new ReportService();
        reportService.add(cart);


        response.getWriter().write("success");


        SendMail sendMail = new SendMail("谢谢惠顾~商品已发货，具体请看消息通知", user.getEmail());
        sendMail.start();
    }

    public void showtable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*插入表*/
        ReportService reportService = new ReportService();
        List<logData> cartGoods = reportService.selectAll();

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }

    public void showrecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*插入表*/
        ReportService reportService = new ReportService();
        List<logData> cartGoods = reportService.selectByName(user.getUserName());

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }
}
