package com.yph.auth.controller;

import com.yph.authcommon.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/***
 * @Description: 支持多层级下钻，返回目录结构
 *
 * @Author: yph
 * @version 1.0
 * @Datetime: 2024/4/30-16:17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")

    public ApiResult test() {
        String path = "C:\\Users\\zhaoxiang\\Desktop\\玉衡百天\\";
        List<String> list = getFile(path, new ArrayList<>(), path);
        return new ApiResult(0, "获取成功", list);
    }

    private static List<String> getFile(String path, List<String> result, String rootPath) {
        File file = new File(path);
        File[] files = file.listFiles();
        String s = "";
        for (File f : files) {
            if (f.isDirectory()) {
                getFile(f.getAbsolutePath(), result, rootPath);
            } else {
                s = f.getAbsolutePath().replace(rootPath, "").replaceAll("\\\\", "/");
                result.add(s);
            }
        }
        return result;
    }

}
