package com.wb.wbao.unitest;

import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMgrTest {

    @Resource
    private UserMgr userMgr;

    @Test
    public void getUser() {
        User user = userMgr.queryUserById(1L);
        System.out.println("hashcode方法的返回值是：" +user.hashCode());
        Assert.assertEquals(user.getLoginName(), "admin");
    }

    @Test
    public void createUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setLoginName("123");
        userDTO.setPassword("123");
        userDTO.setUsername("哈哈哈");
        userDTO.setRoleType(User.ROLE_TYPE_COMMON);
        userDTO.setComeYear(2017);

        userMgr.createUser(userDTO);
    }

    @Test
    public void removeUser(){
        userMgr.removeUsers(Arrays.asList(23L));
    }

    public void loginTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLoginName("1234");
        userDTO.setPassword("123");

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getLoginName(), userDTO.getPassword());

        subject.login(token);
    }
}
