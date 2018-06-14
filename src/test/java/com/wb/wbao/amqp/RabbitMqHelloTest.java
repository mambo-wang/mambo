package com.wb.wbao.amqp;

import com.wb.wbao.server.amqp.HelloSender;
import com.wb.wbao.server.amqp.NeoSender;
import com.wb.wbao.server.amqp.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private NeoSender neoSender;

    @Autowired
    private TopicSender topicSender;

    @Test
    public void hello() throws Exception {
//        helloSender.send();
        Thread.sleep(1000l);
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            helloSender.send(i);
        }
        Thread.sleep(10000l);
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
            helloSender.send(i);
        }
        Thread.sleep(10000l);
    }

    @Test
    public void topic1() throws Exception {
        topicSender.send1();
        Thread.sleep(1000l);
    }

}
