package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.mapper.UserMapper;
import com.yiyue.pojo.User;
import com.yiyue.service.UserService;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService= new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
