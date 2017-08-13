package com.wb.wbao.web;

import com.wb.wbao.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2017/7/2.
 * 控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping(value = "/{id}")
    public User say(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setPassword("1992");
        user.setUsername("wang");
        return user;
    }
}
