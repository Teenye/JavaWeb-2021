package com.yiyue.service;

import com.yiyue.mapper.GoodMapper;
import com.yiyue.pojo.Good;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class GoodService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Good> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public void add(Good good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.add(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    public void delete(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.delete(id);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    public void update(Good good) {
        SqlSession sqlSession = factory.openSession();
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.update(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    public List<Good> selectByCondition(String brandname,String goodname) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByCondition(brandname, goodname);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<String> selectBrand() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<String> brands = mapper.selectBrand();
        //5. 释放资源
        sqlSession.close();
        return brands;
    }



}
