package com.enterprise.demo.sys.service;

import java.util.Set;

public interface RoleService {

    Set<String> findRoleByUserId(String userId);

}
