package com.yph.auth;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yph.auth.mapper")
@ComponentScan(basePackages = {"com.yph.authcommon.config","com.yph.auth"})
public class AuthWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthWebApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
