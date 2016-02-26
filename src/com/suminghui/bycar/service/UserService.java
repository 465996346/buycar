package com.suminghui.bycar.service;

import java.util.List;

import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.bean.User;
import com.suminghui.bycar.exception.ParameterException;
import com.suminghui.bycar.exception.ServiceException;

public interface UserService {
    public User getByUserName(String userName, String password) throws ParameterException, ServiceException;

    public List<User> selectUserList(Pagination pagination);

    public void insertUser(User user) throws ParameterException;

    public void updateUser(User user) throws ParameterException;

    public User selectUserInfoById(Integer userId);

    public void delete(int id);
}
