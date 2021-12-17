package com.yiyue.mapper;

import com.yiyue.pojo.Cart;
import com.yiyue.pojo.CartGood;
import com.yiyue.pojo.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartMapper {


    /*---------联合查询---------*/
    /*查询某个用户的商品*/
    @Select("select id,brandname,goodname,price,specification,num from goods inner join cart on(goods.id=cart.goodid) where userid = #{id}")
    List<CartGood> selectCart(int id);
    /*查询某个用户的商品数量*/
    @Select("select sum(num) from cart where userid=#{id}")
    Integer selectCount(int id);

    /*------------------------------------------------------*/

    /*查询条目*/
    @Select("select * from cart where userid=#{userid} and goodid=#{goodid}")
    Cart selectBy2Id(@Param("userid")Integer userid,@Param("goodid") Integer goodid);

    /*增加新条目*/
    @Insert("insert into cart values(#{userid},#{goodid},#{num})")
    void add(Cart good);

    /*更新*/
    @Update("update cart set num=#{num} where userid=#{userid} and goodid=#{goodid}")
    void update(Cart good);

    @Delete("delete from cart where userid=#{userid} and goodid=#{goodid}")
    void delete(Cart good);


}
