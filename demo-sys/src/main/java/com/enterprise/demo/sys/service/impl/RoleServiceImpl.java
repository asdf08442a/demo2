package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.dao.PermissionMapper;
import com.enterprise.demo.sys.dao.RoleMapper;
import com.enterprise.demo.sys.dao.RolePermissionMapper;
import com.enterprise.demo.sys.dao.UserRoleMapper;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.Role;
import com.enterprise.demo.sys.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> findRoleByUserId(String userId) {
        return userRoleMapper.findRoleIdByUserId(userId);
    }

    @Override
    public List<Role> selectRoles(String name) {
        if (StringUtils.isNotBlank(name)) {
            return roleMapper.selectList(new EntityWrapper<Role>()
                    .eq("status", 1)
                    .like("name", name));
        }
        return roleMapper.selectList(new EntityWrapper<Role>()
                .eq("status", 1));
    }

    @Override
    public int insert(Role role) {
        role.setRoleId(String.valueOf(IdWorker.genRoleId()));
        role.setStatus(CoreConst.STATUS_VALID);
        return roleMapper.insert(role);
    }

    @Override
    public int updateStatusBatch(List<String> roleIds, Integer status) {
        return roleMapper.updateStatusBatch(roleIds, status);
    }

    @Override
    public Role findByRoleId(Integer roleId) {
        List<Role> roles = roleMapper.selectList(new EntityWrapper<Role>()
                .eq("role_id", roleId));
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        return roles.get(0);
    }

    @Override
    public int updateByRoleId(Role role) {
        return roleMapper.update(role, new EntityWrapper<Role>()
                .eq("role_id", role.getRoleId()));
    }

    @Override
    public List<Permission> findPermissionsByRoleId(String roleId) {
        Set<String> permissonIds = rolePermissionMapper.findPermissionIdsByRoleId(roleId);
        return permissionMapper.selectList(new EntityWrapper<Permission>()
                .in("permission_id", permissonIds.toArray()));
    }
}
