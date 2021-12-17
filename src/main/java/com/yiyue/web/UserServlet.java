package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import com.yiyue.service.GoodService;
import com.yiyue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();


    /*-------------------获取用户信息--------------------*/
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String jsonString = JSON.toJSONString(user.getUserName());
        response.getWriter().write(jsonString);
    }
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.getWriter().write("success");
    }

    /*------------------登陆注册------------------*/

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收用户名和密码*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User login_user = JSON.parseObject(params,User.class);
        System.out.println(login_user);
        /*调用service*/
        User user = userService.selectByNP(login_user.getUserName(),login_user.getPassword());
        if(user!= null){
            /*同个浏览器同个sessionid*/
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            /*回发*/
            String jsonString = JSON.toJSONString("success");
            response.getWriter().write(jsonString);
        }
        else{
            String jsonString = JSON.toJSONString("fail");
            response.getWriter().write(jsonString);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收用户名和密码*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User register_user = JSON.parseObject(params,User.class);

        User u = userService.selectByName(register_user.getUserName());
        if(u == null){
            /*用户名不存在，加用户*/
            userService.add(register_user);
            String jsonString = JSON.toJSONString("success");
            response.getWriter().write(jsonString);
        }
        else{
            /*用户存在，不能添加*/
            String jsonString = JSON.toJSONString("fail");
            response.getWriter().write(jsonString);
        }
    }



    /*-----------------后台管理-------------------------*/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        String jsonString = JSON.toJSONString(users);
        response.getWriter().write(jsonString);
    }
    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params,User.class);
        List<User> users= userService.selectByCondition(user.getUserName());
        String jsonString = JSON.toJSONString(users);
        response.getWriter().write(jsonString);
    }

    /*添加用户*/
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params,User.class);

        userService.add(user);
        response.getWriter().write("success");
    }
    /*删除用户*/
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params,User.class);

        userService.delete(user.getId());
        response.getWriter().write("success");
    }
    /*更新用户*/
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User user = JSON.parseObject(params,User.class);

        userService.update(user);
        response.getWriter().write("success");
    }


}
