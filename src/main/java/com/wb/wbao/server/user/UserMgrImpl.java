package com.wb.wbao.server.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Service("userMgr")
public class UserMgrImpl implements UserMgr {

    @Resource
    private UserDao userDao;

    @Resource
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);

    @Override
    public List<User> queryAll() {
        logger.debug("query all user");
        return userDao.findAll();
    }

    @Override
    public User queryUserById(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User modifyUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void removeUsers(List<Long> userIds) {
        userIds.forEach(userDao::delete);
    }

    @Override
    public User queryUserByLoginNameAndPassword(String loginName, String password) {
        return userDao.findByLoginNameAndPassword(loginName, password);
    }

    @Override
    public void sendEmail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mambo1991@163.com");//发送者.
        message.setTo("wang.bao@h3c.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.

        mailSender.send(message);//发送邮件
    }
}
