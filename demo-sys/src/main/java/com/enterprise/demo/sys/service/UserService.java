package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.User;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
public interface UserService {

    User selectByUsername(String username);

    void updateLastLoginTime(User principal);

    User selectByUserId(String userId);

    int updateByUserId(User user);
}
