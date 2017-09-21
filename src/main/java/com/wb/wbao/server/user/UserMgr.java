package com.wb.wbao.server.user;

import com.wb.wbao.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserMgr {

    List<UserDTO> queryAll();

    User queryUserById(Long userId);

    User createUser(UserDTO userDTO);

    User modifyUser(UserDTO userDTO);

    void removeUsers(List<Long> userIds);

    User queryUserByLoginNameAndPassword(String loginName, String password);

    void sendEmail(User user);

    User queryByLoginName(String loginName);

    UserDTO convertToDTO(User user);

    User convertToUser(UserDTO userDTO);

    Set<String> queryRoles(String loginName);

    Set<String> queryPermissions(String loginName);

    void changePassword(Long userId, String newPassword);

}
