package com.fashion.spring.mapper.db1;

import com.fashion.spring.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findUserById(@Param("userId") Integer userId);
}

