package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.common.util.PasswordHelper;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dao.UserMapper;
import com.enterprise.demo.sys.dao.UserRoleMapper;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.entity.UserRole;
import com.enterprise.demo.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int selectRoleUserCnt(String roleId) {
        return userRoleMapper.selectCount(new EntityWrapper<UserRole>().eq("role_id", roleId));
    }

    @Override
    public User selectByUsername(String username) {
        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void updateLastLoginTime(User user) {
        userMapper.updateLastLoginTime(user);
    }

    @Override
    public User selectByUserId(String userId) {
        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("user_id", userId));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int updateByUserId(User user) {
        return userMapper.update(user, new EntityWrapper<User>().eq("user_id", user.getUserId()));
    }

    @Override
    public List<User> findUsers(User user) {
        Wrapper<User> wrapper = new EntityWrapper<User>().eq("status", CoreConst.STATUS_VALID);
        if (user != null && StringUtils.isNotBlank(user.getUsername())) {
            wrapper.like("username", user.getUsername());
        }
        if (user != null && StringUtils.isNotBlank(user.getEmail())) {
            wrapper.like("email", user.getEmail());
        }
        if (user != null && StringUtils.isNotBlank(user.getPhone())) {
            wrapper.like("phone", user.getPhone());
        }
        return userMapper.selectList(wrapper);
    }

    @Override
    public int insert(User user) {
        user.setUserId(String.valueOf(IdWorker.genUserId()));
        user.setStatus(CoreConst.STATUS_VALID);
        PasswordHelper.encryptPassword(user);
        return userMapper.insert(user);
    }

    @Override
    public int updateStatusBatch(List<String> userIdsList, Integer status) {
        return userMapper.updateStatusBatch(userIdsList, status);
    }

    @Override
    public ResponseDTO addAssignRole(String userId, List<String> roleIds) {
        userRoleMapper.delete(new EntityWrapper<UserRole>()
                .eq("user_id", userId));
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
        return ResultUtils.success("分配权限成功");
    }

}
