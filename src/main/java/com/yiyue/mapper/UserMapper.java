package com.yiyue.mapper;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /*根据用户名和密码查询*/
    @Select("select * from user where username= #{username} and password = #{password}")
    User select(@Param("username") String username,@Param("password") String password);

    /*根据用户名查询*/
    @Select("select * from user where username= #{username}")
    User selectByName(String username);


    /*--------------------------后台管理界面----------------------------------*/
    /*添加用户*/
    @Insert("insert into user values(null,#{userName},#{password},#{email})")
    void add(User user);

    @Delete("delete from user where id=#{id}")
    void delete(int id);

    @Update("update user set username=#{userName},password=#{password},email=#{email} where id = #{id}")
    void update(User user);

    /*查询所有用户*/
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where username like CONCAT('%',#{username},'%')")
    List<User> selectByCondition(String username);

}
