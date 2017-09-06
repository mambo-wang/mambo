package com.wb.wbao.web;

import com.wb.wbao.common.Result;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserMgr userMgr;

    @PostMapping
    public User login(@RequestBody User user){

        return userMgr.queryUserByLoginNameAndPassword(user.getLoginName(), user.getPassword());
    }

    @GetMapping(value = "/{loginName}/{password}")
    public Result<User> loginget(@PathVariable String loginName, @PathVariable String password){

        User user =  userMgr.queryUserByLoginNameAndPassword(loginName, password);
        Result result = new Result();
        result.setData(user);
        return result;
    }


}
