package com.yph.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.yph.auth.entity.SysUser;
import com.yph.auth.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/***
 * @Description: 登录控制器
 *
 * @Author: yph
 * @version 1.0
 * @Datetime: 2024/5/7-14:15
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    // 会话登录接口
    @RequestMapping("doLogin")
    public SaResult doLogin(String username, String password) {
        SysUser sysUser = sysUserService.lambdaQuery().eq(SysUser::getUsername, username).eq(SysUser::getPassword, password).one();
        // 第一步：比对前端提交的账号名称、密码
        if(Objects.nonNull(sysUser)) {
            // 第二步：根据账号id，进行登录
            StpUtil.login(sysUser.getId());
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ----
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ----
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ----
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
