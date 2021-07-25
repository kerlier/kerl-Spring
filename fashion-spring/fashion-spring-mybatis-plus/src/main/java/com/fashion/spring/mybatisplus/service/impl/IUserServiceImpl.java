package com.fashion.spring.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fashion.spring.mybatisplus.dto.UserInfo;
import com.fashion.spring.mybatisplus.mapper.UserInfoMapper;
import com.fashion.spring.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 10:29
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }

    @Override
    public UserInfo getByUsername(String search){
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.like("username",search);
        List<UserInfo> userInfos = userInfoMapper.selectList(wrapper);
        if(!userInfos.isEmpty()){
            return userInfos.get(0);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(List<UserInfo> userInfos)  throws RuntimeException{
        int i =0;
        for (UserInfo userInfo : userInfos) {
            i ++;
            userInfoMapper.updateById(userInfo);
            if(i == 2){
               throw new RuntimeException("测试");
            }
        }
    }
}
