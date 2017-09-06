package com.wb.wbao.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findUserByLoginNameAndPassword(String loginName, String password);
}
