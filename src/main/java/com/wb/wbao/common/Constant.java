package com.wb.wbao.common;

import java.time.ZoneId;

public interface Constant {

    /** 用户状态：不可获取 */
    Integer AVAILABLE_FALSE = 0;

    /** 用户状态：可获取 */
    Integer AVAILABLE_TRUE = 1;

    /** 北京东八区 */
    ZoneId BEIJING_ZONE = ZoneId.of("UTC+08:00");

    /** 文件夹类型：用户个人空间 */
    Integer USER_PERSONAL = 1;

    String USER_PERSONAL_PARENT = "C:\\filetest\\user";
    String ROOT_DIR = "C:\\filetest";
}
