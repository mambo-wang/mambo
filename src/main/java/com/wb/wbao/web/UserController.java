package com.wb.wbao.web;

import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2017/7/2.
 * 控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserMgr userMgr;

    @GetMapping(value = "/{id}")
    public User queryById(@PathVariable Long id){
        return userMgr.queryUserById(id);
    }

    @PostMapping
    public List<User> createUser(@RequestBody User user){
        userMgr.createUser(user);
        return userMgr.queryAll();
    }

    @GetMapping
    public List<User> queryAllUsers() {
        return userMgr.queryAll();
    }

    @PutMapping
    public List<User> modifyUser(@RequestBody User user){
        userMgr.modifyUser(user);
        return userMgr.queryAll();
    }

    @DeleteMapping(value = {"/{idList}"})
    public List<User> removeUsers(@PathVariable List<Long> idList){

        userMgr.removeUsers(idList);

        return userMgr.queryAll();
    }
}
