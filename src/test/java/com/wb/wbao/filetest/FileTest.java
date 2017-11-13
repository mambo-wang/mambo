package com.wb.wbao.filetest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {

    public static void main(String[] args) throws IOException {

        testFileMethod();

    }


    public static void testFileMethod() throws IOException {
        /** 列出所有根目录 */
        File[] files = File.listRoots();

        /** 获取绝对路径 */
        Arrays.stream(files)
                .map(File::getAbsolutePath)
                .forEach(System.out::println);

        /** 列出所有子目录 */
        String[] fileLists = files[0].list();
        assert fileLists != null;
        Arrays.stream(fileLists).forEach(System.out::println);

        System.out.println("=========================================================");

        /** 获取文件-绝对路径 */
//        File file = new File("C:\\filetest\\newFile2.txt");

        /** 获取文件-相对路径 */
//        File file = new File(".");

        /** 获取文件夹 */
        File file = new File("C:\\filetest\\newdir");
        /** 创建文件夹 */
        System.out.println("mkDir:" + file.mkdir());
        /** 创建文件 */
        System.out.println("createNewFile:" + file.createNewFile());

        /** File类的各种方法 */
        System.out.println("name:" + file.getName());
        System.out.println("path:" + file.getPath());
        System.out.println("absoluteFile:" + file.getAbsoluteFile());
        System.out.println("absolutePath:" + file.getAbsolutePath());
        System.out.println("parent:" + file.getParent());
        System.out.println("exists:" + file.exists());
        System.out.println("canWrite:" + file.canWrite());
        System.out.println("isFile:" + file.isFile());
        System.out.println("isDirectory:" + file.isDirectory());
        System.out.println("isAbsolute:" + file.isAbsolute());
        System.out.println("lastModified:" + file.lastModified());
        System.out.println("length:" + file.length());

        /** 重命名文件，路径也可以重新定义 */
//        System.out.println("rename:" + file.renameTo(new File("C:\\newFile2.txt")));

        /** 删除文件或者文件夹 */
//        System.out.println("delete:" + file.delete());

    }
}
