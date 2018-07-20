package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.dao.PermissionMapper;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        return permissionMapper.selectList(new EntityWrapper<Permission>()
                .eq("status", status)
                .orderBy("order_num"));
    }

    @Override
    public Set<String> findPermsByUserId(String userId) {
        return permissionMapper.findPermsByUserId(userId);
    }

    @Override
    public List<Permission> selectMenuByUserId(String userId) {
        return permissionMapper.selectMenuByUserId(userId);
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
        permission.setParentId(String.valueOf(IdWorker.nextId()));
        permission.setStatus(CoreConst.STATUS_VALID);
        return permissionMapper.insert(permission);
    }

    @Override
    public int selectSubPermsByPermissionId(String permissionId) {
        Permission permission = selectOneByPermissionId(permissionId);
        if (null == permission) {
            return 0;
        }
        return permissionMapper.selectCount(
                new EntityWrapper<Permission>().eq("parent_id", permission));
    }

    @Override
    public int updateStatus(String permissionId, Integer status) {
        Permission permission = selectOneByPermissionId(permissionId);
        if (null == permission) {
            return 0;
        }
        permission.setStatus(status);
        return permissionMapper.updateById(permission);
    }

    @Override
    public Permission findByPermissionId(String permissionId) {
        return selectOneByPermissionId(permissionId);
    }

    @Override
    public Permission findByParentId(String parentId) {
        List<Permission> permissions = permissionMapper.selectList(
                new EntityWrapper<Permission>().eq("parent_id", parentId));
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        return permissions.get(0);
    }

    @Override
    public int updateByPermissionId(Permission permission) {
        return permissionMapper.updateById(permission);
    }

    private Permission selectOneByPermissionId(String permissionId) {
        List<Permission> permissions = permissionMapper.selectList(
                new EntityWrapper<Permission>().eq("permission_id", permissionId));
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        return permissions.get(0);
    }
}
