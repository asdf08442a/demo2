package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.PageUtils;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.PermissionTreeListDTO;
import com.enterprise.demo.sys.dto.base.PageResultDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.Role;
import com.enterprise.demo.sys.service.PermissionService;
import com.enterprise.demo.sys.service.RoleService;
import com.enterprise.demo.sys.shiro.MyShiroRealm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MyShiroRealm myShiroRealm;

    /**
     * 角色列表数据
     */
    @PostMapping("/list")
    @ResponseBody
    public PageResultDTO pageRoles(String name, Integer limit, Integer offset) {
        PageHelper.startPage(PageUtils.getPageNo(limit, offset), limit);
        List<Role> roleList = roleService.selectRoles(name);
        PageInfo<Role> pages = new PageInfo<>(roleList);
        return ResultUtils.table(roleList, pages.getTotal());
    }

    /**
     * 新增角色
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseDTO addRole(Role role) {
        int a = roleService.insert(role);
        if (a > 0) {
            return ResultUtils.success("添加角色成功");
        } else {
            return ResultUtils.error("添加角色失败");
        }
    }

    /**
     * 删除角色
     */
    @GetMapping("/delete")
    @ResponseBody
    public ResponseDTO deleteRole(String roleId) {
        List<String> roleIdsList = Arrays.asList(roleId);
        int a = roleService.updateStatusBatch(roleIdsList, CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtils.success("删除角色成功");
        } else {
            return ResultUtils.error("删除角色失败");
        }
    }

    /**
     * 批量删除角色
     */
    @GetMapping("/batch/delete")
    @ResponseBody
    public ResponseDTO batchDeleteRole(String roleIdStr) {
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        int a = roleService.updateStatusBatch(roleIdsList, CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtils.success("删除角色成功");
        } else {
            return ResultUtils.error("删除角色失败");
        }
    }

    /**
     * 编辑角色详情
     */
    @GetMapping("/edit")
    public String detail(Model model, Integer roleId) {
        Role role = roleService.findByRoleId(roleId);
        model.addAttribute("role", role);
        return "role/detail";
    }

    /**
     * 编辑角色
     */
    @PostMapping("/edit")
    @ResponseBody
    public ResponseDTO editRole(@ModelAttribute("role") Role role) {
        int a = roleService.updateByRoleId(role);
        if (a > 0) {
            return ResultUtils.success("编辑角色成功");
        } else {
            return ResultUtils.error("编辑角色失败");
        }
    }

    /*分配权限列表查询*/
    @PostMapping("/assign/permission/list")
    @ResponseBody
    public List<PermissionTreeListDTO> assignRole(String roleId) {
        List<PermissionTreeListDTO> listDTOS = Lists.newArrayList();
        List<Permission> allPermissions = permissionService.selectAll(CoreConst.STATUS_VALID);
        List<Permission> hasPermissions = roleService.findPermissionsByRoleId(roleId);
        for (Permission permission : allPermissions) {
            PermissionTreeListDTO dto = new PermissionTreeListDTO();
            dto.setId(permission.getId());
            dto.setPermissionId(permission.getPermissionId());
            dto.setName(permission.getName());
            dto.setParentId(permission.getParentId());
            for (Permission hasPermission : hasPermissions) {
                //有权限则选中
                if (hasPermission.getPermissionId().equals(permission.getPermissionId())) {
                    dto.setChecked(true);
                    break;
                }
            }
            listDTOS.add(dto);
        }
        return listDTOS;
    }


//    /*分配权限*/
//    @PostMapping("/assign/permission")
//    @ResponseBody
//    public ResponseVo assignRole(String roleId, String permissionIdStr) {
//        List<String> permissionIdsList = new ArrayList<>();
//        if (StringUtils.isNotBlank(permissionIdStr)) {
//            String[] permissionIds = permissionIdStr.split(",");
//            permissionIdsList = Arrays.asList(permissionIds);
//        }
//        ResponseVo responseVo = roleService.addAssignPermission(roleId, permissionIdsList);
//        /*重新加载角色下所有用户权限*/
//        List<User> userList = roleService.findByRoleId(roleId);
//        if (userList.size() > 0) {
//            List<String> userIds = new ArrayList<>();
//            for (User user : userList) {
//                userIds.add(user.getUserId());
//            }
//            myShiroRealm.clearAuthorizationByUserId(userIds);
//        }
//        return responseVo;
//    }

}
