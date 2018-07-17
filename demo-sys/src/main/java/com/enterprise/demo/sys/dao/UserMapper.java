package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jinzhengang
 * @since 2018-07-16
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    int updateLastLoginTime(User user);
}
