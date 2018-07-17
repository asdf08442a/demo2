package com.enterprise.demo.sys.dao;

import com.enterprise.demo.sys.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author jinzhengang
 * @since 2018-07-16
 */
public interface RoleMapper extends BaseMapper<Role> {

    Set<String> findRoleByUserId(String userId);
}
