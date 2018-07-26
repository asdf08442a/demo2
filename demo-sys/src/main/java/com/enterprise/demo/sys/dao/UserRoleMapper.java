package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    Set<String> findRoleIdsByUserId(@Param("userId") String userId);

    Set<String> findUserIdsByRoleId(@Param("userId") String userId);
}
