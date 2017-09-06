package com.wb.wbao.unitest;

import com.wb.wbao.user.User;
import com.wb.wbao.user.UserMgr;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMgrTest {

    @Resource
    private UserMgr userMgr;

    @Test
    public void getUser() {
        User user = userMgr.queryUserById(1L);
        Assert.assertEquals(user.getLoginName(), "wbao");
    }
}
