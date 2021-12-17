package com.yiyue.mapper;

import com.yiyue.pojo.Cart;
import com.yiyue.pojo.CartGood;
import com.yiyue.pojo.logData;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReportMapper {

    /*查询销售日志*/
    @Select("select username,brandname,goodname,price,specification,num,date from report inner join goods on(report.goodid=goods.id) inner join user on(report.userid=user.id)")
    List<logData> selectAll();

    @Select("select username,brandname,goodname,price,specification,num,date from report inner join goods on(report.goodid=goods.id) inner join user on(report.userid=user.id) where username=#{username}")
    List<logData> selectByName(String username);

    /*增加新条目*/
    @Insert("insert into report values(#{userid},#{goodid},#{num},#{date})")
    void add(Cart good);

}
