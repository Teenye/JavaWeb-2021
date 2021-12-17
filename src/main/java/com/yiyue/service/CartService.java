package com.yiyue.service;

import com.yiyue.mapper.CartMapper;
import com.yiyue.mapper.GoodMapper;
import com.yiyue.pojo.Cart;
import com.yiyue.pojo.CartGood;
import com.yiyue.pojo.Good;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CartService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*联合查询，商品信息*/
    public List<CartGood> selectCart(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        List<CartGood> goods = mapper.selectCart(id);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    /*计算商品数量*/
    public Integer selectCount(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        Integer count = mapper.selectCount(id);
        //5. 释放资源
        sqlSession.close();
        return count;
    }

    /*查询是否已经存在条目*/
    public Cart selectBy2Id(Integer userid,Integer goodid) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        Cart cart = mapper.selectBy2Id(userid, goodid);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
        return cart;
    }

    /*增添新条目*/
    public void add(Cart good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        mapper.add(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    /*删除条目*/
    public void delete(Cart good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        mapper.delete(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    /*更新加一减一*/
    public void update(Cart good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        //4. 调用方法
        mapper.update(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }



}
