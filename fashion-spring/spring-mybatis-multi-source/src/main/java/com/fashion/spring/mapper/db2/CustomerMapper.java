package com.fashion.spring.mapper.db2;

import com.fashion.spring.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {

    Customer findUserById(@Param("userId") Integer userId);
}
