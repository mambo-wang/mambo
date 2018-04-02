package com.wb.wbao.web;

import com.wb.wbao.common.annotation.Loggable;
import com.wb.wbao.dto.CommonDTO;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.session.SessionMgr;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class LoginController {

    public static final String SIGN_IN_PAGE = "signin.html";

    public static final String HOME_PAGE = "home.html";

    @Resource
    private UserMgr userMgr;

    @Resource
    private SessionMgr sessionMgr;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName(SIGN_IN_PAGE);
        return mv;
    }

    /**
     * 如果不加@ResponseBody注解的话会报如下错误
     * javax.servlet.ServletException: Circular view path [login]:
     * would dispatch back to the current handler URL [/mambo/login] again
     * @param user
     * @return
     */
    @Loggable(describe = "登录", optType = "POST", module = "LOGIN")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonDTO login(@RequestBody User user) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());

        CommonDTO result = new CommonDTO();

        if(subject.isAuthenticated()){
            result.setResult(CommonDTO.Result.SUCCESS);
            result.setData(userMgr.convertToDTO(userMgr.queryByLoginName(user.getLoginName())));
            return result;
        }
        try{
            subject.login(token);
            result.setResult(CommonDTO.Result.SUCCESS);
            UserDTO userDTO = userMgr.convertToDTO(userMgr.queryByLoginName(user.getLoginName()));
            result.setData(userDTO);
            sessionMgr.setAttribute("loginUser", userDTO);
//            userMgr.sendEmail(user);
            return result;
        }
        catch (AuthenticationException e){
            result.setResult(CommonDTO.Result.FAILURE);
            result.setData(e.getMessage());
            return result;
        }
    }

    @GetMapping(value = "/{loginName}/{password}")
    public CommonDTO<User> loginget(@PathVariable String loginName, @PathVariable String password) {

        User user = userMgr.queryUserByLoginNameAndPassword(loginName, password);
        CommonDTO commonDTO = new CommonDTO();
        commonDTO.setData(user);
        return commonDTO;
    }


    @RequestMapping({"/", "/index"})
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName(HOME_PAGE);
        return modelAndView;

    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView modelAndView) {
        modelAndView.setViewName(SIGN_IN_PAGE);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return modelAndView;
    }


}
