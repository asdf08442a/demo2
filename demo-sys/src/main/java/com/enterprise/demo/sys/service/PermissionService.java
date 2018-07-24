package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    List<Permission> selectAll(Integer status);

    Set<String> findPermsByUserId(String userId);

    List<Permission> selectMenuByUserId(String userId);

    List<Permission> selectAllMenuName(Integer status);

    int insert(Permission permission);

    int selectSubPermsByPermissionId(String permissionId);

    int updateStatus(String permissionId, Integer status);

    Permission findByPermissionId(String permissionId);

    Permission findByParentId(String parentId);

    int updateByPermissionId(Permission permission);
}
