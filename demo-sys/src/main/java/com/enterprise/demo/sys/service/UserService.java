package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.User;

public interface UserService {

    User findByUsername(String username);

    void updateLastLoginTime(User principal);

    User findByUserId(String userId);

    int updateByUserId(User user);
}
