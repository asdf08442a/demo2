package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.Role;

import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 */
public interface RoleMapper extends BaseMapper<Role> {

    Set<String> findRoleByUserId(String userId);
}
