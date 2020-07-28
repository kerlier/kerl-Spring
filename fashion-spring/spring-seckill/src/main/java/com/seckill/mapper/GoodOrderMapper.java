package com.seckill.mapper;

import com.seckill.pojo.GoodOrder;
import com.seckill.pojo.GoodOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodOrderMapper {
    int countByExample(GoodOrderExample example);

    int deleteByExample(GoodOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(GoodOrder record);

    int insertSelective(GoodOrder record);

    List<GoodOrder> selectByExample(GoodOrderExample example);

    GoodOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") GoodOrder record, @Param("example") GoodOrderExample example);

    int updateByExample(@Param("record") GoodOrder record, @Param("example") GoodOrderExample example);

    int updateByPrimaryKeySelective(GoodOrder record);

    int updateByPrimaryKey(GoodOrder record);
}