package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import com.smart.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Service
public class UserService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;

    public void register(User user) throws UserExistException {
        User u = this.getUserByUserName(user.getUserName());
        if (u != null) {
            throw new UserExistException("user is exist");
        } else {
            user.setCredit(100);
            user.setUserType(1);
            userDao.save(user);
        }
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    public User getUserById(int userId) {
        return userDao.get(userId);
    }

    public void lockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_LOCK);
        userDao.update(user);
    }

    public void unlockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_UNLOCK);
        userDao.update(user);
    }

    public List<User> queryUserByName(String userName) {
        return userDao.queryUserByName(userName);
    }

    public List<User> getAllUsers() {
        return userDao.loadAll();
    }

    public void loginSuccess(User user) {
        user.setCredit(5 + user.getCredit());
        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(new Date());
        userDao.update(user);
        loginLogDao.save(loginLog);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
