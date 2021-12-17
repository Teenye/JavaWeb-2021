package com.yiyue.service;

import com.yiyue.mapper.GoodMapper;
import com.yiyue.mapper.UserMapper;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /*---------------------用于登陆注册-----------------------*/
    public User selectByNP(String username,String password){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*调用方法*/
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }
    public User selectByName(String username){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*调用方法*/
        User user = mapper.selectByName(username);
        sqlSession.close();
        return user;
    }

    /*----------------------用于用户后台管理-----------------------*/
    public List<User> selectAll() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        sqlSession.commit();
        sqlSession.close();
        return users;
    }
    public List<User> selectByCondition(String username) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectByCondition(username);
        sqlSession.commit();
        sqlSession.close();
        return users;
    }
    public void add(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.add(user);
        sqlSession.commit();
        sqlSession.close();
    }
    public void delete(int id) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }
    public void update(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
