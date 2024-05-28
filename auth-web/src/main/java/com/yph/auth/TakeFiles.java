package com.yph.auth;

import java.io.File;
public class TakeFiles {
    public static void main(String[] args) {
        String path = "E:\\upload";
        getFile(path, 0);
    }

    private static void getFile(String path, int deep) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFile(f.getAbsolutePath(), deep + 1);
            } else if (deep == 0) {
                System.out.println("文件：" + f.getName());
            }
        }
    }
}
