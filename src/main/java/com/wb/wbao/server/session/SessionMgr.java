package com.wb.wbao.server.session;

import com.wb.wbao.dto.UserDTO;

/**
 * Created by dell on 2017/9/22.
 */
public interface SessionMgr {

    void setAttribute(String key, UserDTO value);

    UserDTO currentLoginUser();
}
