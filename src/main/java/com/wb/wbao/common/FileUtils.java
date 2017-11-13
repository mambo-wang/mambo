package com.wb.wbao.common;

import java.io.File;

public final class FileUtils {


    private static void checkRootDir() {
        createDir("C:\\filetest");
    }

    private static void checkUserParentDir() {
        createDir("C:\\filetest\\user");
    }

    public static void checkUserDir(Long userId) {
        checkRootDir();
        checkUserParentDir();
        createDir("C:\\filetest\\user\\user-" + userId);
    }

    public static void deleteUserDir(Long userId){
        deleteDir("C:\\filetest\\user\\user-" + userId);
    }

    public static void deleteDir(String url) {
        File file = new File(url);
        if(file.exists()){
            file.delete();
        }
    }

    public static void createDir(String url) {
        File file = new File(url);
        if(!file.exists()){
            if(!file.mkdir()){
                file.mkdir();
            }
        }
    }
}
