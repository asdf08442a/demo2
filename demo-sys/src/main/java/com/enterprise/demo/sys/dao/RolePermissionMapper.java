package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    Set<String> selectPermissionIdsByRoleId(@Param("roleId") String roleId);

    Set<String> selectPermissionIdsByRoleIds(@Param("roleIds") Set<String> roleIds);
}
