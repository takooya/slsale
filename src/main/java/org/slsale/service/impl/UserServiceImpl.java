package org.slsale.service.impl;

import org.slsale.dao.UserMapper;
import org.slsale.pojo.User;
import org.slsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 22:26 2018/4/8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getLoginUser(User user) throws Exception {
        return userMapper.getLoginUser(user);
    }

    @Override
    public int loginCodeIsExist(User user) throws Exception {
        return userMapper.loginCodeIsExist(user);
    }

    @Override
    public int modifyUser(User user) throws Exception {
        return userMapper.modifyUser(user);
    }

    @Override
    public int addUser(User user) throws Exception {
        return userMapper.addUser(user);
    }

    @Override
    public int count(User user) throws Exception {
        return userMapper.count(user);
    }

    @Override
    public List<User> getUserList(User user)throws Exception {
        return userMapper.getUserList(user);
    }
}
