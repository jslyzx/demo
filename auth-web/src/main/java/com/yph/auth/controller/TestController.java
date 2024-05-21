package com.yph.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Description:
 *
 * @Author: yph
 * @version 1.0
 * @Datetime: 2024/4/30-16:17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test(){
        return "hello";
    }

}
