package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import com.yiyue.service.GoodService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Base64;
import java.util.List;


@WebServlet("/good/*")
public class GoodServlet extends BaseServlet {
    private GoodService goodService = new GoodService();
    /*条件查询*/
    public void selectByConditions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //将json对象转换为Good类对象
        Good good = JSON.parseObject(params,Good.class);
        //调用goodService类的方法做条件查询
        List<Good> goods = goodService.selectByCondition(good.getBrandName(),good.getGoodName());
        //将List<Good>转换为json对象
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    /*设置图片*/
    boolean setImage(String image, String filePath) {
        int index = image.indexOf("base64,");
        if (index == -1) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(image.substring(index + 7));
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*查询所有商品*/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> goods = goodService.selectAll();
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    /*查询所有品牌*/
    public void selectBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> goods = goodService.selectBrand();
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }

    /*添加商品*/
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);

        goodService.add(good);
        response.getWriter().write("success");
    }
    /*删除商品*/
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);
        goodService.delete(good.getId());
        response.getWriter().write("success");
    }
    /*更新商品*/
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);
        goodService.update(good);

        /*保存图片*/
        String image = good.getImg_src();

        //将上传的图片保存到本地
        boolean is_successful = true;
        if (image != null) {
            String fileName = String.valueOf(good.getId());
            String parePath = request.getSession().getServletContext().getRealPath("/imgs/");
            setImage(image, "C:\\Users\\59806\\IdeaProjects\\My_Web\\src\\main\\webapp\\imgs/" + fileName + ".jpg");
            is_successful = setImage(image, parePath + fileName + ".jpg");
        }

        response.getWriter().write("success");
    }
}
