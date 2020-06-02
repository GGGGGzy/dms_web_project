package org.dms.dao.impl;

import org.dms.dao.IUserDAO;
import org.dms.entity.User;
import org.dms.util.DBUtil;
import org.dms.util.EntityUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User queryUserById(Integer id) {
        User user = null;
        String sql = "select * from db_user where id=?";
        Object[] params = {id};
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, params);
            if (rs.next()) {
                user = new User();
                EntityUtil.setPropertiesByResultSet(user, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return user;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = null;
        String sql = "select * from db_user where username=?";
        Object[] params = {username};
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, params);
            if (rs.next()) {
                user = new User();
                EntityUtil.setPropertiesByResultSet(user, rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "select * from db_user where username=? and password=?";
        Object[] params = {username, password};
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, params);
            if (rs.next()) {
                user = new User();
                EntityUtil.setPropertiesByResultSet(user, rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return user;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> users = new ArrayList<>();
        String sql = "select * from db_user";
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, null);
            while (rs.next()) {
                User user = new User();
                EntityUtil.setPropertiesByResultSet(user, rs);
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setId(rs.getInt("auth"));
//                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return users;
    }

    @Override
    public List<User> queryUsersByPage(int currentPage, int pageSize) {
        List<User> users = new ArrayList<>();
        String sql = "select * from db_user order by id limit ?,?";
        Object[] params = {(currentPage - 1) * pageSize, pageSize};
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, params);
            while (rs.next()) {
                User user = new User();
                EntityUtil.setPropertiesByResultSet(user, rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return users;
    }
}
