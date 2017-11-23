package com.wb.wbao.filetest;

import com.wb.wbao.common.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NIOTest {

    public static void main(String[] args) {

//        testBuffer();

//        testFileChannel();

//        testRandomFileChannel();

        testChannelReadMethod();
    }

    /** 读取大文件时，使用Channel和Buffer传统的 用竹筒多次取水的方式
     * read方法
     * */
    private static void testChannelReadMethod() {

        try (FileInputStream fis = new FileInputStream("C:/filetest/user/user-1/ChromeStandalone_61.0.3163.91_Setup.exe");
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = new FileOutputStream("C:/filetest/user/user-19/ChromeStandalone_61.0.3163.91_Setup.exe").getChannel()){

            long before = System.currentTimeMillis();
//            ByteBuffer bbuff = ByteBuffer.allocate(1024*1024);
            ByteBuffer bbuff = ByteBuffer.allocateDirect(1024*1024);
            while (inChannel.read(bbuff) != -1) {
                bbuff.flip();
                outChannel.write(bbuff);
                bbuff.clear();
            }
            System.out.println(System.currentTimeMillis() - before);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            File file = new File("C:/filetest/user/user-19/ChromeStandalone_61.0.3163.91_Setup.exe");
            file.delete();
        }

    }

    /**
     * 复制文件内容并追加到该文件尾部,position,map
     */
    private static void testRandomFileChannel() {
        File f = new File("C:/filetest/newdir/a.txt");

        try (RandomAccessFile raf = new RandomAccessFile(f, "rw");FileChannel randomChannel = raf.getChannel()){

            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

            System.out.println(f.length());
            //把Channel的记录指针移动到最后
            randomChannel.position(f.length());
            randomChannel.write(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试FileChannel,map,writer
     */
    private static void testFileChannel() {
        File f = new File("C:/filetest/user/user-1/ChromeStandalone_61.0.3163.91_Setup.exe");

        try(FileChannel inChannel = new FileInputStream(f).getChannel();
            FileChannel outChannel = new FileOutputStream("C:/filetest/user/user-19/ChromeStandalone_61.0.3163.91_Setup.exe").getChannel()){

            long before = System.currentTimeMillis();
            //将FileChannel里所有数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            outChannel.write(buffer);
            buffer.clear();
            System.out.println(System.currentTimeMillis() - before);

//            Charset charset = Charset.forName("GBK");
//            CharsetDecoder decoder = charset.newDecoder();
//            //把ByteBuffer转换为CharBuffer
//            CharBuffer charBuffer = decoder.decode(buffer);
//
//            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            File file = new File("C:/filetest/user/user-19/ChromeStandalone_61.0.3163.91_Setup.exe");
            file.delete();
        }
    }

    /**
     * 测试Buffer缓冲队列
     */
    private static void testBuffer() {

        /* 创建buffer */
        CharBuffer buff = CharBuffer.allocate(10);

        System.out.println("capacity:" + buff.capacity());
        System.out.println("limit:" + buff.limit());
        System.out.println("position:" + buff.position());

        buff.put('d');
        buff.put('c');
        buff.put('b');
        buff.put('a');

        System.out.println("放入四个元素后，position:" + buff.position());
        System.out.println("放入四个元素后，limit:" + buff.limit());

        //为从buffer中取元素做好准备
        buff.flip();

        System.out.println("执行flip()方法后，limit:" + buff.limit());
        System.out.println("执行flip()方法后，position:" + buff.position());
        System.out.println("执行flip()方法后，remaining:" + buff.remaining());

        System.out.println("第一个元素" + buff.get());
        System.out.println("取出第一个元素后，position:" + buff.position());

        //为再次向buffer中放入元素做准备
        buff.clear();

        System.out.println("执行clear()方法后，remaining:" + buff.remaining());
        System.out.println("执行clear()方法后，limit:" + buff.limit());
        System.out.println("执行clear()方法后，position:" + buff.position());
        System.out.println("执行clear()方法后，缓冲区第三个元素为:" + buff.get(2));
        System.out.println("执行按索引读取后，position:" + buff.position());



    }


}
