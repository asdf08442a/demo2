package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
public interface PermissionService {

    List<Permission> selectAll(Integer status);

    Set<String> findPermsByUserId(String userId);
}
