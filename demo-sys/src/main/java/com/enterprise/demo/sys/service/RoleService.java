package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<String> findRoleByUserId(String userId);

    List<Role> selectRoles(String name);

    int insert(Role role);

    int updateStatusBatch(List<String> roleIds, Integer status);

    Role findByRoleId(Integer roleId);

    int updateByRoleId(Role role);

    List<Permission> findPermissionsByRoleId(String roleId);
}
