package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 微信登录
     *
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO);

    User getById(Long id);

    Integer countByMap(Map map);
}