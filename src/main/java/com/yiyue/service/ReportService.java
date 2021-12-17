package com.yiyue.service;

import com.yiyue.mapper.CartMapper;
import com.yiyue.mapper.GoodMapper;
import com.yiyue.mapper.ReportMapper;
import com.yiyue.pojo.Cart;
import com.yiyue.pojo.CartGood;
import com.yiyue.pojo.logData;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ReportService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<logData> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<logData> goods = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }
    public List<logData> selectByName(String username) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<logData> goods = mapper.selectByName(username);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }


    /*增添新条目*/
    public void add(Cart good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        mapper.add(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
}
