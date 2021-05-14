package com.fashion.spring.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fashion.spring.mybatisplus.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author test
 * @since 2021-05-14
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
