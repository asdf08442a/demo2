package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.service.PermissionService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission")
@Slf4j
public class PermissionController {
    /**
     * 1:全部资源，2：菜单资源
     */
    private static final String[] MENU_FLAG = {"1", "2"};
    @Autowired
    private PermissionService permissionService;

    /**
     * 权限列表数据
     */
    @PostMapping("/list")
    @ResponseBody
    public List<Permission> loadPermissions(String flag) {
        List<Permission> permissionListList = Lists.newArrayList();
        if (StringUtils.isBlank(flag) || MENU_FLAG[0].equals(flag)) {
            permissionListList = permissionService.selectAll(CoreConst.STATUS_VALID);
        } else if (MENU_FLAG[1].equals(flag)) {
            permissionListList = permissionService.selectAllMenuName(CoreConst.STATUS_VALID);
        }
        return permissionListList;
    }

//    /*添加权限*/
//    @ResponseBody
//    @PostMapping("/add")
//    public ResponseVo addPermission(Permission permission) {
//        try {
//            int a = permissionService.insert(permission);
//            if (a > 0) {
//                shiroService.updatePermission();
//                return ResultUtil.success("添加权限成功");
//            } else {
//                return ResultUtil.error("添加权限失败");
//            }
//        } catch (Exception e) {
//            logger.error(String.format("PermissionController.addPermission%s", e));
//            throw e;
//        }
//    }
//
//    /*删除权限*/
//    @ResponseBody
//    @PostMapping("/delete")
//    public ResponseVo deletePermission(String permissionId) {
//        try {
//            int subPermsByPermissionIdCount = permissionService.selectSubPermsByPermissionId(permissionId);
//            if (subPermsByPermissionIdCount > 0) {
//                return ResultUtil.error("改资源存在下级资源，无法删除！");
//            }
//            int a = permissionService.updateStatus(permissionId, CoreConst.STATUS_INVALID);
//            if (a > 0) {
//                shiroService.updatePermission();
//                return ResultUtil.success("删除权限成功");
//            } else {
//                return ResultUtil.error("删除权限失败");
//            }
//        } catch (Exception e) {
//            logger.error(String.format("PermissionController.deletePermission%s", e));
//            throw e;
//        }
//    }
//
//    /*权限详情*/
//    @GetMapping("/edit")
//    public String detail(Model model, String permissionId) {
//        Permission permission = permissionService.findByPermissionId(permissionId);
//        if (null != permission) {
//            if (permission.getParentId().equals(CoreConst.TOP_MENU_ID)) {
//                model.addAttribute("parentName", CoreConst.TOP_MENU_NAME);
//            } else {
//                Permission parent = permissionService.findById(permission.getParentId());
//                model.addAttribute("parentName", parent.getName());
//            }
//        }
//        model.addAttribute("permission", permission);
//        return "permission/detail";
//    }
//
//    /*编辑权限*/
//    @ResponseBody
//    @PostMapping("/edit")
//    public ResponseVo editPermission(@ModelAttribute("permission") Permission permission) {
//        int a = permissionService.updateByPermissionId(permission);
//        if (a > 0) {
//            shiroService.updatePermission();
//            return ResultUtil.success("编辑权限成功");
//        } else {
//            return ResultUtil.error("编辑权限失败");
//        }
//    }

}