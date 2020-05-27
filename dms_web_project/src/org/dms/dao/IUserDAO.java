package org.dms.dao;

import org.dms.entity.User;

import java.util.List;

public interface IUserDAO {
    /**
     * 根据用户id查找用户
     * @param id id
     * @return 用户实体类
     */
    User queryUserById(Integer id);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户实体类
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户实体类
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 查找全部用户
     * @return 所有用户
     */
    List<User> queryAllUser();

    /**
     * 根据分页查找用户
     * @param currentPage 当前页码
     * @param pageSize 页面大小
     * @return 分页用户
     */
    List<User> queryUsersByPage(int currentPage, int pageSize);
}
