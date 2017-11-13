package com.wb.wbao.dto;

import com.wb.wbao.common.Utils;

import java.io.File;

public class FileDTO {

    private String fileName;

    private String size;

    private String lastModified;

    private String absolutePath;

    public static FileDTO convert(File file){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSize("" + file.length());
        fileDTO.setAbsolutePath(file.getAbsolutePath());
        fileDTO.setLastModified(Utils.formatFullDateTime(file.lastModified()));
        fileDTO.setFileName(file.getName());
        return fileDTO;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName:'" + fileName + '\'' +
                ", absolutePath:'" + absolutePath + '\'' +
                '}';
    }
}
