package com.fashion.clickhouse.mapper;

import com.fashion.clickhouse.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:29
 */
@Mapper
public interface UserInfoMapper {

    void saveData(UserInfo userInfo);

    UserInfo selectById(@Param("id")Integer id);

    List<UserInfo> selectList();
}
