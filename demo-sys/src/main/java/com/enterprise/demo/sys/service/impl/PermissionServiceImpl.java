package com.enterprise.demo.sys.service.impl;

import com.enterprise.demo.sys.dao.PermissionMapper;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectAll(Integer status) {
        return permissionMapper.selectAllPerms(status);
    }

    @Override
    public Set<String> findPermsByUserId(String userId) {
        return permissionMapper.findPermsByUserId(userId);
    }

    @Override
    public List<Permission> selectMenuByUserId(String userId) {
        return permissionMapper.selectMenuByUserId(userId);
    }
}
