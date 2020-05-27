package org.dms.service;

import org.dms.entity.User;

public interface IUserService {
    /**
     * 根据是否此用户名的用户
     * @param username 用户名
     * @return 存在为真，否则假
     */
    boolean isExist(String username);

    /**
     * 根据用户名和密码登录
     * @param username 用户名
     * @param password 密码
     * @return 用户实体类
     */
    User login(String username, String password);
}
