package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.dao.PermissionMapper;
import com.enterprise.demo.sys.dao.RolePermissionMapper;
import com.enterprise.demo.sys.dao.UserRoleMapper;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> selectAll(Integer status) {
        return permissionMapper.selectList(new EntityWrapper<Permission>()
                .eq("status", status)
                .orderBy("order_num"));
    }

    @Override
    public Set<String> findPermsByUserId(String userId) {
        Set<String> roleIds = userRoleMapper.findRoleIdByUserId(userId);
        Set<String> permissionIds = rolePermissionMapper.findPermissionIdsByRoleIds(roleIds);
        return permissionMapper.findPermsByPermissionIds(permissionIds);
    }

    @Override
    public List<Permission> selectMenuByUserId(String userId) {
        Set<String> roleIds = userRoleMapper.findRoleIdByUserId(userId);
        Set<String> permissionIds = rolePermissionMapper.findPermissionIdsByRoleIds(roleIds);
        return permissionMapper.findMenuByPermissionIds(permissionIds);
    }

    @Override
    public List<Permission> selectAllMenuName(Integer status) {
        return permissionMapper.selectList(new EntityWrapper<Permission>()
                .eq("status", status)
                .in("type", new String[]{"0", "1"})
                .orderBy("order_num")
        );
    }

    @Override
    public int insert(Permission permission) {
        permission.setPermissionId(String.valueOf(IdWorker.genPermissionId()));
        permission.setStatus(CoreConst.STATUS_VALID);
        return permissionMapper.insert(permission);
    }

    @Override
    public int selectSubPermsByPermissionId(String permissionId) {
        return permissionMapper.selectCount(new EntityWrapper<Permission>()
                .eq("parent_id", permissionId));
    }

    @Override
    public int updateStatus(String permissionId, Integer status) {
        return permissionMapper.updateStatusByPermissionId(permissionId, status);
    }

    @Override
    public Permission findByPermissionId(String permissionId) {
        List<Permission> permissions = permissionMapper.selectList(new EntityWrapper<Permission>()
                .eq("permission_id", permissionId));
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        return permissions.get(0);
    }

    @Override
    public Permission findByParentId(String parentId) {
        List<Permission> permissions = permissionMapper.selectList(new EntityWrapper<Permission>()
                .eq("parent_id", parentId));
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        return permissions.get(0);
    }

    @Override
    public int updateByPermissionId(Permission permission) {
        return permissionMapper.update(permission, new EntityWrapper<Permission>()
                .eq("permission_id", permission.getPermissionId()));
    }
}
