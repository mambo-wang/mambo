package com.wb.wbao.filetest;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NIOTest {

    public static void main(String[] args) {

//        testBuffer();

        testChannel();
    }

    private static void testChannel() {
        File f = new File("C:/filetest/newdir/test.txt");

        try(FileChannel inChannel = new FileInputStream(f).getChannel();
            FileChannel outChannel = new FileOutputStream("C:/filetest/newdir/a.txt").getChannel()){

            //将FileChannel里所有数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            Charset charset = Charset.forName("GBK");
            outChannel.write(buffer);
            buffer.clear();
            CharsetDecoder decoder = charset.newDecoder();
            //把ByteBuffer转换为CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);

            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testBuffer() {

        /* 创建buffer */
        CharBuffer buff = CharBuffer.allocate(8);

        System.out.println("capacity:" + buff.capacity());
        System.out.println("limit:" + buff.limit());
        System.out.println("position:" + buff.position());

        buff.put('c');
        buff.put('b');
        buff.put('a');

        System.out.println("放入三个元素后，position:" + buff.position());
        System.out.println("放入三个元素后，limit:" + buff.limit());

        //为从buffer中取元素做好准备
        buff.flip();

        System.out.println("执行flip()方法后，limit:" + buff.limit());
        System.out.println("执行flip()方法后，position:" + buff.position());

        System.out.println("第一个元素" + buff.get());
        System.out.println("取出第一个元素后，position:" + buff.position());

        //为再次向buffer中放入元素做准备
        buff.clear();

        System.out.println("执行clear()方法后，limit:" + buff.limit());
        System.out.println("执行clear()方法后，position:" + buff.position());
        System.out.println("执行clear()方法后，缓冲区第三个元素为:" + buff.get(2));
        System.out.println("执行按索引读取后，position:" + buff.position());


    }


}
