package com.wb.wbao.server.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByLoginNameAndPassword(String loginName, String password);

    User findByLoginName(String loginName);


}
