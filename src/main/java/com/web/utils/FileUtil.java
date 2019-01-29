package com.web.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件相关工具
 * Created by Jone on 2019-01-25.
 */
public class FileUtil {
    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean fileExist(String origin,String filePath){
        boolean flag=false;
        File file=new File(origin,filePath);
        flag=file.exists();
        return flag;
    }

    /**
     * 判断文件是否存在
     */
    public static boolean fileExist(String filePath){
        boolean flag =false;
        File file = new File(filePath);
        flag=file.exists();
        return flag;
    }

    /**
     * 获取文件路径
     * @param filePath
     * @param fileName
     * @return
     */
    public  static String getFilePath(String filePath,String fileName){
        File file = new File(filePath,fileName);
        return file.getPath();
    }

    /**
     * 文件复制
     * @param srcFilePath
     * @param desFilePath
     * @throws IOException
     */
    public static void copyFile(String srcFilePath,String desFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        File desFile = new File(desFilePath);

        FileUtils.copyFile(srcFile,desFile);
    }

    /**
     * 文件复制
     * @param srcFilePath
     * @param srcFileName
     * @param desFilePath
     * @param desFileName
     * @throws IOException
     */
    public static void copyFile(String srcFilePath,String srcFileName,
                                String desFilePath,String desFileName) throws IOException {
        File srcFile = new File(srcFilePath,srcFileName);
        File desFile = new File(desFilePath,desFileName);

        FileUtils.copyFile(srcFile,desFile);
    }

    public static void deleteFile(String filePath,String fileName){
        File file = new File(filePath,fileName);
        file.delete();
    }
}
