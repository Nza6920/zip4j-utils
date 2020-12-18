package com.niu.zip;

import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Hello world!
 * @author Zian.Niu
 */
public class App {
    public static void main(String[] args) throws ZipException, FileNotFoundException {

        // 压缩文件夹
        String zipPath = "E:\\test\\zip";

        // 压缩后的目标文件
        String target = "E:\\test\\demo.zip";

        // 解压目标地址
        String unZipPath = "E:\\test\\unzip";

        // 密码
        String password = "abc123456";

        // 解压文件
//        testUnzipFile(zipPath, target, unZipPath, password);

        // 压缩文件流
        testZipStreams(zipPath, target, password);
    }

    /**
     * 解压文件
     *
     * @param zipPath   压缩路径
     * @param target    目标文件
     * @param unZipPath 解压路径
     * @param password  密码
     * @author nza
     * @createTime 2020/12/16 16:20
     */
    private static void testUnzipFile(String zipPath, String target, String unZipPath, String password) throws ZipException, InterruptedException {
        Zip4jUtils.zipFolder(new File(target), new File(zipPath), password);

        System.out.println("----------------------- 压缩成功 ---------------------");
        System.out.println("----------------------- 开始解压 ---------------------");
        Thread.sleep(5000);
        Zip4jUtils.unzip(new File(target), unZipPath, password);

        System.out.println("----------------------- 解压成功 ---------------------");
    }

    /**
     * 压缩文件流
     *
     * @param zipFolder 需要压缩的文件夹
     * @param target    目标文件
     * @param password  密码
     * @author nza
     * @createTime 2020/12/16 16:19
     */
    private static void testZipStreams(String zipFolder, String target, String password) throws FileNotFoundException, ZipException {
        System.out.println("----------------------- 压缩文件流 ---------------------");
        File zip = new File(zipFolder);
        if (zip.isDirectory()) {
            Map<String, InputStream> streams = new HashMap<>();
            for (File file : Objects.requireNonNull(zip.listFiles())) {
                FileInputStream fileInputStream = new FileInputStream(file);
                streams.put(file.getName(), fileInputStream);
            }
            if (!streams.isEmpty()) {
                Zip4jUtils.zipStreams(new File(target), streams, password);
            }
        }
        System.out.println("----------------------- 压缩成功 ---------------------");
    }
}
