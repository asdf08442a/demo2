package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 编辑用户详情
     */
    @GetMapping("/edit")
    public String userDetail(Model model, String userId) {
        log.debug("用户id：{}", userId);
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/userDetail";
    }

    /**
     * 编辑用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public ResponseDTO editUser(User user) {
        log.debug("用户：{}", user);
        int a = userService.updateByUserId(user);
        if (a > 0) {
            return ResultUtils.success("编辑用户成功！");
        } else {
            return ResultUtils.error("编辑用户失败");
        }
    }
}
