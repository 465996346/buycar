package com.suminghui.bycar.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.bean.User;

/*
 * SQL接口
 */
public interface UserDao {
    /**
     * 登录
     */
    public User selectUserByLogin(@Param (value="userName")String userName) throws SQLException;


    /**
     * 获取用户列表
     */
    public List<User> selectUserList(Pagination pagination) throws SQLException;
    /**
     * 添加用户
     */
    public void insertUser(User user) throws SQLException;
    /**
     * 修改用户
     */
    public void updateUser(User user) throws SQLException;
    /**
     * 获取单个用户的信息
     */
    public User selectUserById(Integer userId) throws SQLException;

    /**
     * 获取所有用户信息的条数
     * @return
     * @throws SQLException
     */
    public int getTotalCount() throws SQLException;

    /**
     * 删除用户信息
     * @param userId
     * @throws SQLException
     */
    public void deleteUser(Integer userId) throws SQLException;
}
