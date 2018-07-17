package com.enterprise.demo.sys.service;

import java.util.Set;

/**
 * @author jinzhengang
 * @date 2018-07-16
 **/
public interface RoleService {

    Set<String> findRoleByUserId(String userId);

}
