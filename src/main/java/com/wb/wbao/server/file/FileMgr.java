package com.wb.wbao.server.file;

import com.wb.wbao.dto.FileDTO;

import java.util.List;

public interface FileMgr {

    /** 查询指定类型的文件或文件夹
     * @param type 1：个人文件夹  2：素材空间 3：学生空间
     * @param pureFile true:只展示文件，不展示文件夹 false:所有的
     * @return 所有文件
     */
    List<FileDTO> getFiles(Integer type, boolean pureFile);

    /**
     * 清空用户个人文件
     */
    void clearUserPersonalFiles();

    /**
     * 清空文件夹
     * @param url 文件夹路径
     */
    void clearDir(String url);

}
