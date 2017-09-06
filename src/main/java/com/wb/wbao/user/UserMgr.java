package com.wb.wbao.user;

import java.util.List;

public interface UserMgr {

    List<User> queryAll();

    User queryUserById(Long userId);

    User createUser(User user);

    User modifyUser(User user);

    void removeUsers(List<Long> userIds);

    User queryUserByLoginNameAndPassword(String loginName, String password);

}
