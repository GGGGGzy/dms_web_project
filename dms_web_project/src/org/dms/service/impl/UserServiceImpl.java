package org.dms.service.impl;

import org.dms.dao.IUserDAO;
import org.dms.dao.impl.UserDAOImpl;
import org.dms.entity.User;
import org.dms.service.IUserService;

public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean isExist(String username) {
        return username != null && userDAO.queryUserByUsername(username) != null;
    }

    @Override
    public User login(String username, String password) {
        if (isExist(username)) {
            return userDAO.queryUserByUsernameAndPassword(username, password);
        }
        return null;
    }
}
