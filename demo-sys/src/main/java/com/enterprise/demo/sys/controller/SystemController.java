package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.PermissionService;
import com.enterprise.demo.sys.service.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class SystemController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/login")
    public String login() {
        return "system/login";
    }

    /**
     * 获取验证码图片
     */
    @RequestMapping("/verificationCode")
    public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码文本
        String capText = defaultKaptcha.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        log.info("生成验证码文本:{}", capText);
        //利用生成的字符串构建图片
        BufferedImage bi = defaultKaptcha.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 提交登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO login(HttpServletRequest request, String username, String password,
                             String verification, Integer rememberMe) {
        // 判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(verification) || StringUtils.isBlank(rightCode) || !verification.equals(rightCode)) {
            return ResultUtils.error("验证码错误！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            token.setRememberMe(1 == rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (LockedAccountException e) {
            token.clear();
            return ResultUtils.error("用户已经被锁定不能登录，请联系管理员！");
        } catch (AuthenticationException e) {
            token.clear();
            return ResultUtils.error("用户名或者密码错误！");
        }
        //更新最后登录时间
        userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
        return ResultUtils.success("登录成功！");
    }

    /**
     * 获取当前登录用户的菜单
     */
    @PostMapping("/menu")
    @ResponseBody
    public List<Permission> getMenus() {
        List<Permission> permissionListList = permissionService.selectMenuByUserId(
                ((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
        return permissionListList;
    }
}
