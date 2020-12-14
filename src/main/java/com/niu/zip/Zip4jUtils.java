package com.niu.zip;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.List;

/**
 * Zip4j 工具类
 *
 * @author [nza]
 * @version 1.0 2020/12/14
 * @createTime 19:59
 */
public class Zip4jUtils {

    /**
     * 文件压缩参数
     */
    private static ZipParameters zipParameters;

    static {
        // 实例化压缩参数
        ZipParameters zipParameters = new ZipParameters();
        // 开启文件加密
        zipParameters.setEncryptFiles(true);
        // 设置加密方式
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // 设置秘钥强度
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        // 设置加密参数
        setZipParameters(zipParameters);
    }

    private Zip4jUtils() {
    }

    /**
     * 设置压缩参数
     *
     * @param zipParameters 压缩参数
     */
    public static void setZipParameters(ZipParameters zipParameters) {
        Zip4jUtils.zipParameters = zipParameters;
    }


    /**
     * 压缩文件列表
     *
     * @param target   压缩后的目标文件
     * @param zipFiles 需要压缩的文件列表
     */
    public static void zipFiles(File target, List<File> zipFiles) throws ZipException {
        ZipFile zipFile = new ZipFile(target);
        zipFile.addFiles(zipFiles);
    }

    /**
     * 加密压缩文件列表
     *
     * @param target   压缩后的目标文件
     * @param zipFiles 需要压缩的文件列表
     */
    public static void zipFiles(File target, List<File> zipFiles, String password) throws ZipException {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("密码不能为空");
        }
        ZipFile zipFile = new ZipFile(target, password.toCharArray());
        zipFile.addFiles(zipFiles, zipParameters);
    }

    /**
     * 压缩文件夹
     *
     * @param target 压缩后的目标文件
     * @param folder 需要压缩的文件夹
     */
    public static void zipFolder(File target, File folder) throws ZipException {
        ZipFile zipFile = new ZipFile(target);
        zipFile.addFolder(folder);
    }

    /**
     * 加密压缩文件夹
     *
     * @param target 压缩后的目标文件
     * @param folder 需要压缩的文件夹
     */
    public static void zipFolder(File target, File folder, String password) throws ZipException {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("密码不能为空");
        }
        ZipFile zipFile = new ZipFile(target, password.toCharArray());
        zipFile.addFolder(folder, zipParameters);
    }


    /**
     * 普通解压
     *
     * @param zip          压缩文件
     * @param targetFolder 解压到
     */
    public static void unzip(File zip, String targetFolder) throws ZipException {
        ZipFile zipFile = new ZipFile(zip);
        zipFile.extractAll(targetFolder);
    }

    /**
     * 加密解压
     *
     * @param zip          压缩文件
     * @param targetFolder 解压到
     * @param password     解压密码
     */
    public static void unzip(File zip, String targetFolder, String password) throws ZipException {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("密码不能为空");
        }
        ZipFile zipFile = new ZipFile(zip, password.toCharArray());
        zipFile.extractAll(targetFolder);
    }
}
