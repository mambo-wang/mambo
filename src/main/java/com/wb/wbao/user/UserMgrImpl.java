package com.wb.wbao.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userMgr")
public class UserMgrImpl implements UserMgr {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
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
        return userDao.findUserByLoginNameAndPassword(loginName, password);
    }
}
