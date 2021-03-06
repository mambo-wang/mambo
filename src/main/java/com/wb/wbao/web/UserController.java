package com.wb.wbao.web;

import com.wb.wbao.common.annotation.Loggable;
import com.wb.wbao.common.listener.MyTestEventPublisher;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Resource
    private UserMgr userMgr;

    @Resource
    private MyTestEventPublisher publisher;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/rest/{id}")
    public User queryRestUser(@PathVariable Long id){
        return this.restTemplate.getForObject("http://localhost:8888/mambo/user/" + id, User.class);
    }

    @GetMapping(value = "/{id}")
    public User queryById(@PathVariable Long id){
        return userMgr.queryUserById(id);
    }

    @GetMapping(value = "/loginUser")
    public UserDTO queryLoginUser(){
        return userMgr.queryLoginUser();
    }

    @ApiOperation(value = "创建用户",notes = "根据用户名、密码、入学年份、用户姓名创建用户")
    @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    @RequiresRoles("admin")
    @RequiresPermissions("user:add")
    @PostMapping
    public List<UserDTO> createUser(@RequestBody UserDTO userDTO){
        userMgr.createUser(userDTO);
        logger.info("create a user");
        return userMgr.queryAll();
    }

    @Loggable(describe = "查询所有用户", optType = "QUERY", module = "USER")
    @ApiOperation(value = "查询用户", notes = "查询所有用户")
    @GetMapping
    @RequiresPermissions("user:view")
    public List<UserDTO> queryAllUsers() throws Exception {
        UserDTO loginUser = userMgr.queryLoginUser();
        publisher.pushListener("Hello World!");
        return userMgr.queryAll();
    }

    @ApiOperation(value = "修改",notes = "根据用户名、密码、入学年份、用户姓名修改用户")
    @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    @PutMapping
    public List<UserDTO> modifyUser(@RequestBody UserDTO userDTO){
        userMgr.modifyUser(userDTO);
        return userMgr.queryAll();
    }

    @ApiOperation(value = "删除用户",notes = "根据id删除用户")
    @ApiImplicitParam(name = "idList", value = "用户id集合", required = true, dataType = "ArrayList")
    @DeleteMapping(value = {"/{idList}"})
    public List<UserDTO> removeUsers(@PathVariable List<Long> idList){

        userMgr.removeUsers(idList);
        return userMgr.queryAll();
    }
}
