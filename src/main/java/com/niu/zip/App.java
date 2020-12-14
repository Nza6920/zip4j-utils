package com.niu.zip;

import net.lingala.zip4j.exception.ZipException;

import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ZipException, InterruptedException {

        // 压缩文件夹
        String zipPath = "E:\\test\\zip";

        // 压缩后的目标文件
        String target = "E:\\test\\demo.xrhealer";

        // 解压目标地址
        String unZipPath = "E:\\test\\unzip";

        // 密码
        String password = "abc123456";

        Zip4jUtils.zipFolder(new File(target), new File(zipPath), password);

        System.out.println("----------------------- 压缩成功 ---------------------");
        System.out.println("----------------------- 开始解压 ---------------------");

        Thread.sleep(5000);

        Zip4jUtils.unzip(new File(target), unZipPath, password);

        System.out.println("----------------------- 解压成功 ---------------------");
    }
}
