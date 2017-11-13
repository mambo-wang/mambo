package com.wb.wbao.server.file;

import com.wb.wbao.common.Constant;
import com.wb.wbao.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service("fileMgr")
public class FileMgrImpl implements FileMgr {

    /**
     * @param type 1：个人文件夹  2：素材空间 3：学生空间
     * @param pureFile true:只展示文件，不展示文件夹 false:所有的
     */
    @Override
    public List<FileDTO> getFiles(Integer type, boolean pureFile) {

        File file = new File(getParentDirByType(type));

        if(pureFile){
            List<FileDTO> files = new ArrayList<>();
            this.getPureFiles(files, file);
            return files;
        }else {
            File[] files = file.listFiles();
            return Arrays.stream(files)
                    .map(FileDTO::convert)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void clearUserPersonalFiles() {

        File file = new File(Constant.USER_PERSONAL_PARENT);
        File[] fileArray = file.listFiles();

        Arrays.stream(fileArray).forEach(file1 -> this.clearDir(file1.getAbsolutePath()));
    }

    private void getPureFiles(List<FileDTO> files, File file) {

        File[] fileArray = file.listFiles();

        for (File oneFile : fileArray) {

            if(oneFile.isFile()){
                files.add(FileDTO.convert(oneFile));
            }else {
                getPureFiles(files, oneFile);
            }
        }
    }

    @Override
    public void clearDir(String url) {
        File file = new File(url);
        File[] fileArray = file.listFiles();

        Arrays.stream(fileArray).forEach(File::delete);
    }

    private String getParentDirByType(Integer type){

        if(Objects.equals(type, Constant.USER_PERSONAL)){
            return Constant.USER_PERSONAL_PARENT;
        }
        return Constant.USER_PERSONAL_PARENT;
    }
}
