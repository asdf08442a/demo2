package com.enterprise.demo.sys.service.impl;

import com.enterprise.demo.sys.dao.RoleMapper;
import com.enterprise.demo.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
