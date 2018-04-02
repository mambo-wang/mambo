package com.wb.wbao.jvm;

public class ObjectAllocate {

    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {

//        testAllocation();
//        testPretenureSizeThreshold();
        testTenuringThreshold();
    }

    /**
     * 测试分配担保机制
     * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 日志：
     [GC (Allocation Failure) [DefNew: 7825K->687K(9216K), 0.0072760 secs] 7825K->6831K(19456K), 0.0154361 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
     Heap
     def new generation   total 9216K, used 4865K [0x04600000, 0x05000000, 0x05000000)
     eden space 8192K,  51% used [0x04600000, 0x04a14938, 0x04e00000)
     from space 1024K,  67% used [0x04f00000, 0x04fabc68, 0x05000000)
     to   space 1024K,   0% used [0x04e00000, 0x04e00000, 0x04f00000)
     tenured generation   total 10240K, used 6144K [0x05000000, 0x05a00000, 0x05a00000)
     the space 10240K,  60% used [0x05000000, 0x05600030, 0x05600200, 0x05a00000)
     Metaspace       used 1849K, capacity 2242K, committed 2368K, reserved 4480K
     Disconnected from the target VM, address: '127.0.0.1:56401', transport: 'socket'
     */
    public static void testAllocation(){
        byte[] a1,a2,a3,a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[4 * _1MB];
    }

    /**
     * 测试大对象直接存老年代
     * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * 日志：
     Heap
     def new generation   total 9216K, used 1845K [0x04600000, 0x05000000, 0x05000000)
     eden space 8192K,  22% used [0x04600000, 0x047cd550, 0x04e00000)
     from space 1024K,   0% used [0x04e00000, 0x04e00000, 0x04f00000)
     to   space 1024K,   0% used [0x04f00000, 0x04f00000, 0x05000000)
     tenured generation   total 10240K, used 4096K [0x05000000, 0x05a00000, 0x05a00000)
     the space 10240K,  40% used [0x05000000, 0x05400010, 0x05400200, 0x05a00000)
     Metaspace       used 1850K, capacity 2242K, committed 2368K, reserved 4480K
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    /**
     * 测试长期存活对象进入老年代
     * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * 日志：
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 1)
     - age   1:     966232 bytes,     966232 total
     : 6033K->943K(9216K), 0.0062223 secs] 6033K->5039K(19456K), 0.0063159 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 1)
     : 5039K->0K(9216K), 0.0050258 secs] 9135K->5036K(19456K), 0.0050627 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     def new generation   total 9216K, used 4178K [0x04600000, 0x05000000, 0x05000000)
     eden space 8192K,  51% used [0x04600000, 0x04a14938, 0x04e00000)
     from space 1024K,   0% used [0x04e00000, 0x04e00000, 0x04f00000)
     to   space 1024K,   0% used [0x04f00000, 0x04f00000, 0x05000000)
     tenured generation   total 10240K, used 5036K [0x05000000, 0x05a00000, 0x05a00000)
     the space 10240K,  49% used [0x05000000, 0x054eb350, 0x054eb400, 0x05a00000)
     Metaspace       used 1850K, capacity 2242K, committed 2368K, reserved 4480K
     */
    public static void testTenuringThreshold(){
        byte[] a1,a2,a3;
        a1 = new byte[_1MB/4];
        a2 = new byte[4 * _1MB];
        a3 = new byte[4 * _1MB];
        a3 = null;
        a3 = new byte[4 * _1MB];

    }

}
