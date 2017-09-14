package com.wb.wbao.server.user;

import com.wb.wbao.server.email.EmailMgrImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;

@Service("userMgr")
public class UserMgrImpl implements UserMgr {

    @Resource
    private UserDao userDao;

    @Resource
    private EmailMgrImpl emailMgr;

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
        try {
            emailMgr.sendTemplateMail(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
