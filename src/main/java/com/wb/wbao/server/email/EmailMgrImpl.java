package com.wb.wbao.server.email;

import com.wb.wbao.server.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailMgrImpl {

    @Resource
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(EmailMgrImpl.class);

    public void sendTextEmail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mambo1991@163.com");//发送者.
        message.setTo("wang.bao@h3c.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.

        mailSender.send(message);//发送邮件
        logger.info("send a normal email.");
    }

    public void sendAttachEmail(User user) throws MessagingException {
        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置.
        helper.setFrom("mambo1991@163.com");//发送者.
        helper.setTo("wang.bao@h3c.com");//接收者.
        helper.setSubject("来自mambo");//邮件主题.
        helper.setText("这是邮件内容（有附件哦.）");//邮件内容.

        //org.springframework.core.io.FileSystemResource下的:
        //附件1,获取文件对象.
        FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\Administrator\\Downloads\\desk5186/1.jpg"));
        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        helper.addAttachment("头像1.jpg", file1);
        //附件2
        FileSystemResource file2 = new FileSystemResource(new File("C:\\Users\\Administrator\\Downloads\\desk5186/2.jpg"));
        helper.addAttachment("头像2.jpg", file2);

        mailSender.send(mimeMessage);
        logger.info("send an email with attach");
    }
}
