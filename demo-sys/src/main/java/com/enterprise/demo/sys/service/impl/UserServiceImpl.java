package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.dao.UserMapper;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
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

    @Override
    public User findByUserId(String userId) {
        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("user_id", userId));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int updateByUserId(User user) {
        return userMapper.update(user, new EntityWrapper<User>().eq("user_id", user.getUserId()));
    }

}
