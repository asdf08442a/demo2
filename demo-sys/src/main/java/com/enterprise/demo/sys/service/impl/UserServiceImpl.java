package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.dao.UserMapper;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void updateLastLoginTime(User user) {
        userMapper.updateLastLoginTime(user);
    }

}
