package com.suminghui.bycar.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.bean.User;
import com.suminghui.bycar.dao.UserDao;
import com.suminghui.bycar.exception.ParameterException;
import com.suminghui.bycar.exception.ServiceException;
import com.suminghui.bycar.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByUserName(String userName, String password) throws ParameterException, ServiceException {
        User user = null;
        try {
            user = userDao.selectUserByLogin(userName);
            
            if (user == null) {
                throw new ServiceException("301", "用户不存在！");
            }
            
            if (!password.equals(user.getPassword())) {
                throw new ServiceException("302", "用户名或密码错误！");
            }
            
            if (user.getIsManager() != 1) {
                throw new ServiceException("302", "用户名或密码错误！");
            }
        } catch (SQLException exception) {
            // log
        }
        return user;
    }

    @Override
    public List<User> selectUserList(Pagination pagination) {
        List<User> users = null;
        try {
            users = userDao.selectUserList(pagination);
            int totalCount = userDao.getTotalCount();
            pagination.setTotalCount(totalCount);
        } catch (SQLException exception) {
            // log
        }
        return users;
    }

    @Override
    public void insertUser(User user) throws ParameterException {
        try {
            userDao.insertUser(user);
        } catch (SQLException exception) {
            // log
        }
    }

    @Override
    public void updateUser(User user) throws ParameterException {
        try {
            userDao.updateUser(user);
        } catch (SQLException exception) {
            // log
        }
    }

    @Override
    public User selectUserInfoById(Integer userId) {
        User user = null;
        try {
            user = userDao.selectUserById(userId);
        } catch (SQLException exception) {
            // log
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try {
            userDao.deleteUser(id);
        } catch (SQLException exception) {
            // log
        }
    }

}
