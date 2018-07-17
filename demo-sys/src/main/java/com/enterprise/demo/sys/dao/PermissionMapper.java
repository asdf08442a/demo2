package com.enterprise.demo.sys.dao;

import com.enterprise.demo.sys.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jinzhengang
 * @since 2018-07-16
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectAllPerms(Integer status);

    Set<String> findPermsByUserId(String userId);
}
