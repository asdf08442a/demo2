package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.User;

public interface UserService {

    User selectByUsername(String username);

    void updateLastLoginTime(User principal);

    User selectByUserId(String userId);

    int updateByUserId(User user);
}
