package com.enterprise.demo.sys.service.impl;

import com.enterprise.demo.sys.dao.UserMapper;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void updateLastLoginTime(User user) {
        userMapper.updateLastLoginTime(user);
    }

}
