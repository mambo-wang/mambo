package com.wb.wbao.filetest;

import java.io.*;
import java.util.Arrays;

public class FileTest {

    public static void main(String[] args) throws IOException {

//        testFileMethod();

//        testInputStreamAndOutputStream();

//        testReader();

//        testWriter();

//        testPrintStream();

//        testOutputStreamReader();

        testReadFromProcess();

    }

    /**
     * 获取另一个进程的输出流
     * @throws IOException
     */
    private static void testReadFromProcess() throws IOException {

        Process p = Runtime.getRuntime().exec("javac");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))){

            String buff = null;
            while ((buff = br.readLine()) != null) {

                System.out.println(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换流：字节流转换为字符流
     */
    private static void testOutputStreamReader(){
        try(InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(reader)){

            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.equals("exit")){
                    System.exit(1);
                }
                System.out.println("输入内容为：" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通常需要输出文本内容时，都包装成PrintStream来进行操作
     */
    private static void testPrintStream() {

        try(FileOutputStream fos = new FileOutputStream("C:/filetest/user/user-1/printStreamTest.txt");
            PrintStream ps = new PrintStream(fos)){

            ps.println("普通字符串");

            ps.println(new FileTest());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testWriter() {
        try (FileWriter fw = new FileWriter("C:/filetest/user/user-1/poem.txt")){
            fw.write("锄禾日当午");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testReader() throws IOException {

        try (FileReader reader = new FileReader("C:/filetest/user/user-1/FileTest.java")){

            char[] cbuf = new char[20];

            int hasRead = 0;

            while ((hasRead = reader.read(cbuf)) > 0) {

                System.out.println(new String(cbuf, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试输入流和输出流，实现复制文件内容
     */
    private static void testInputStreamAndOutputStream() throws IOException {

        try (FileInputStream fis = new FileInputStream("C:/filetest/user/user-1/FileTest.java");
             FileOutputStream fos = new FileOutputStream("C:/filetest/user/user-1/FileOutTest.txt")){
            byte[] bbuf = new byte[20];

            /** 本地读取的字节数量 */
            int hasRead = 0;

            /** 读 */
            while ((hasRead = fis.read(bbuf)) > 0) {

                System.out.println(new String(bbuf, 0, hasRead));

                /** 写 */
                fos.write(bbuf, 0, hasRead);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试File类相关方法
     * @throws IOException IO异常
     */
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
        System.out.println("====================================================");

        /** 文件过滤器 */
        String[] strings = file.list(((dir, name) -> name.endsWith(".txt") &&
                !new File(dir.getAbsolutePath() + "\\" + name).isDirectory()));
        Arrays.stream(strings).forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "FileTest{a FileTest instance}";
    }
}
