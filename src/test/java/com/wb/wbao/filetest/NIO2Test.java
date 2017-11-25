package com.wb.wbao.filetest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class NIO2Test {


    public static void main(String[] args) {

//        testPathsMethod();

//        testFilesMethod();

        testWatchService();

    }

    /**
     * 监控文件变化，貌似只能检测到二级子目录的增删改事件。
     *
     * 也就是说，能检测到C:/filetest/user下面文件或文件夹的增删改动作，但是二级目录下的文件变化不能明确检测到具体的文件和动作类型
     * 能准确检测到一级目录C:/filetest下文件或文件夹得具体文件及其变动
     */
    private static void testWatchService() {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Paths.get("C:\\filetest").register(watchService,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent watchEvent : key.pollEvents()) {
                    System.out.println(watchEvent.context() + "文件发生了" + watchEvent.kind() + "事件");
                }

                boolean valid = key.reset();
                if(!valid){
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Files类测试
     */
    private static void testFilesMethod() {

        try {
            /** 复制文件 */
            Files.copy(Paths.get("C:\\filetest\\user\\readme.txt"), new FileOutputStream("C:\\filetest\\user\\user-1\\read.txt"));

            System.out.println("readme.txt是否为隐藏文件：" + Files.isHidden(Paths.get("C:\\filetest\\user\\readme.txt")));
            /** 读取文件 */
            List<String> lines = Files.readAllLines(Paths.get("C:\\filetest\\user\\readme.txt"), Charset.forName("gbk"));
            System.out.println(lines);

            /** 读取文件 */
            byte[] bytes = Files.readAllBytes(Paths.get("C:\\filetest\\user\\readme.txt"));
            System.out.println(bytes);

            System.out.println("readme文件的大小为：" + Files.size(Paths.get("C:\\filetest\\user\\readme.txt")));

            /** 写入文件 */
            List<String> poem = new ArrayList<>();
            poem.add("两个黄鹂鸣翠柳");
            poem.add("一行白鹭上青天");
            //创建文件并写入内容，自动换行
            Files.write(Paths.get("C:\\filetest\\user\\poem.txt"), poem, Charset.forName("gbk"));

            /**获取文件列表*/
            Files.list(Paths.get("C:\\filetest\\user")).forEach(System.out::println);
            /** 读取文件内容，返回Stream流 */
            Files.lines(Paths.get("C:\\filetest\\user\\readme.txt"), Charset.forName("gbk")).forEach(System.out::println);

            /** 获取存储信息 */
            FileStore cStore = Files.getFileStore(Paths.get("C:\\filetest\\user"));

            System.out.println("C:共有空间：" + cStore.getTotalSpace());
            System.out.println("C:可用空间：" + cStore.getUsableSpace());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试Paths工具类
     */
    private static void testPathsMethod() {
        Path path = Paths.get("C:\\filetest\\user");
//        Path path = Paths.get(".\\filetest\\user");

        System.out.println("path里包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());

        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);

        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());

        //区分该路径是几级目录
        System.out.println("absolutePath中包含的路径数量：" + absolutePath.getNameCount());

        System.out.println(absolutePath.getName(1));

        Path path2 = Paths.get("c:","filetest", "newdir");

        System.out.println(path2);

    }

}
