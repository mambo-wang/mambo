package com.wb.wbao.web;

import com.wb.wbao.common.Result;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserMgr userMgr;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping
    public User login(@RequestBody User user){

        return userMgr.queryUserByLoginNameAndPassword(user.getLoginName(), user.getPassword());
    }

    @ApiOperation(value="登录系统", notes="输入用户名密码登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String")
    })
    @GetMapping(value = "/{loginName}/{password}")
    public Result<User> loginget(@PathVariable String loginName, @PathVariable String password){

        User user =  userMgr.queryUserByLoginNameAndPassword(loginName, password);
        Result result = new Result();
        result.setData(user);
        logger.debug("wo ca this is a debug ");
        logger.info("login with loginName {} and password {}", loginName, password);
        logger.error("test");
        return result;
    }


}
