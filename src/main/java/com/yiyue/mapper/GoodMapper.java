package com.yiyue.mapper;

import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodMapper {

    @Select("select * from goods")
    List<Good> selectAll();

    @Insert("insert into goods values(null,#{brandName},#{goodName},#{price},#{specification},#{description})")
    void add(Good good);

    @Delete("delete from goods where id=#{id}")
    void delete(int id);

    @Update("update goods set brandname=#{brandName},goodname=#{goodName},price=#{price},specification=#{specification},description=#{description} where id = #{id}")
    void update(Good good);

    @Select("select * from goods where brandname like CONCAT('%',#{brandname},'%') and goodname like CONCAT('%',#{goodname},'%')")
    List<Good> selectByCondition(@Param("brandname")String brandname,@Param("goodname") String goodname);

    @Select("select distinct brandname from goods")
    List<String> selectBrand();

}
