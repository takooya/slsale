package org.slsale.service.user;

import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int addUser(User user) throws Exception {
        return userMapper.addUser(user);
    }
}
