package com.wb.wbao.server.session;

import com.wb.wbao.dto.UserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2017/9/22.
 */
@Service("sessionMgr")
public class SessionMgrImpl implements SessionMgr {

    @Override
    public void setAttribute(String key, UserDTO value) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(key, value);

    }

    @Override
    public UserDTO currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        UserDTO currentUser = (UserDTO) subject.getSession().getAttribute("loginUser");
        return currentUser;
    }
}
