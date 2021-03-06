package com.wb.wbao.server.user;

import com.wb.wbao.common.FileUtils;
import com.wb.wbao.common.PasswordHelper;
import com.wb.wbao.common.concurrent.MamboExecutors;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.email.EmailMgrImpl;
import com.wb.wbao.server.permission.Permission;
import com.wb.wbao.server.role.Role;
import com.wb.wbao.server.session.SessionMgr;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("userMgr")
public class UserMgrImpl implements UserMgr {

    @Resource
    private UserDao userDao;

    @Resource
    private EmailMgrImpl emailMgr;

    @Resource
    private SessionMgr sessionMgr;

    private PasswordHelper passwordHelper = new PasswordHelper();

    private Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);


    /**
     * 检测文件变化
     */
    @PostConstruct
    private void init() {
        MamboExecutors.get().getIoBoundService().submit(this::watchFileChange);
    }

    private void watchFileChange(){

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Paths.get("C:\\filetest").register(watchService,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent watchEvent : key.pollEvents()) {
                    System.out.println(watchEvent.context() + "文件发生了" + watchEvent.kind() + "事件");
                    this.queryAll();
                }

                boolean valid = key.reset();
                if(!valid){
                    break;
                }

                TimeUnit.SECONDS.sleep(2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<UserDTO> queryAll() {
        logger.debug("query all user");
        List<UserDTO> userDTOS = userDao.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        userDTOS.forEach(userDTO -> FileUtils.checkUserDir(userDTO.getId()));

        return userDTOS;
    }

    @Override
    public User queryUserById(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = this.convertToUser(userDTO);
        passwordHelper.encryptPassword(user);
        User userDB =  userDao.save(user);
        FileUtils.checkUserDir(userDB.getId());
        return userDB;
    }

    @Override
    public User modifyUser(UserDTO userDTO) {
        User user = this.convertToUser(userDTO);
        return userDao.save(user);
    }

    @Override
    public void removeUsers(List<Long> userIds) {
        userIds.forEach(this::deleteUser);
    }

    private void deleteUser(Long userId){
        FileUtils.deleteUserDir(userId);
        userDao.delete(userId);
    }

    @Override
    public User queryUserByLoginNameAndPassword(String loginName, String password) {
        return userDao.findByLoginNameAndPassword(loginName, password);
    }

    @Override
    public void sendEmail(User user) {
        try {
            emailMgr.sendTemplateMail(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User queryByLoginName(String loginName) {

        logger.info("query by loginName");
        return userDao.findByLoginName(loginName);
    }

    @Override
    public UserDTO convertToDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setLoginName(user.getLoginName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setComeYear(user.getComeYear());
        userDTO.setRoleStr(this.getRoleStr(user.getRoleList()));
        if(userDTO.getRoleStr().contains("admin")){
            userDTO.setRoleType(User.ROLE_TYPE_ADMIN);
        }else {
            userDTO.setRoleType(User.ROLE_TYPE_COMMON);
        }

        return userDTO;
    }

    private String getRoleStr(List<Role> roleList) {
        return roleList.stream()
                .map(Role::getRole)
                .collect(Collectors.joining(","));
    }

    @Override
    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        if(Objects.nonNull(userDTO.getId())){
            user = this.queryUserById(userDTO.getId());
        }
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setLoginName(userDTO.getLoginName());
        user.setComeYear(userDTO.getComeYear());
        user.setRoleList(this.getRole(userDTO));
        user.setState(User.STATE_NORMAL);
        return user;
    }

    private List<Role> getRole(UserDTO userDTO) {
        if(Objects.equals(userDTO.getRoleType(), User.ROLE_TYPE_ADMIN)){
            Role role = new Role();
            role.setId(1L);
            return Arrays.asList(role);
        }else {
            Role role = new Role();
            role.setId(2L);
            return Arrays.asList(role);
        }
    }


    @Override
    public Set<String> queryRoles(String loginName) {
        return this.queryByLoginName(loginName).getRoleList()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> queryPermissions(String loginName) {
        return this.queryByLoginName(loginName).getRoleList().stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .map(Permission::getPermission)
                .collect(Collectors.toSet());
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = this.queryUserById(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.save(user);
    }

    @Override
    public UserDTO queryLoginUser() {
        return sessionMgr.currentLoginUser();
    }
}
