package org.dms.util;

import java.sql.*;

public class DBUtil {
    private static final String DRIVER_CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_device_management?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection conn = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    /**
     * 获取数据库连接
     * @return 数据库连接
     * @throws ClassNotFoundException 数据库驱动未找到
     * @throws SQLException 连接失败
     */
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASSNAME);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    /**
     * 创建预编译语句
     * @param sql SQL语句
     * @param params 变量
     * @return 预编译语句
     * @throws SQLException 创建预编译语句失败
     */
    private static PreparedStatement createPreparedStatement(String sql, Object[] params) throws ClassNotFoundException, SQLException {
        pstmt = getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; ++i) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    /**
     * 释放所有资源
     */
    public static void closeAll() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用的查询是否存在方法
     * @param sql
     * @param params
     * @return
     */
    public static boolean isExist(String sql, Object[] params) {
        boolean result = false;
        try {
            rs = createPreparedStatement(sql, params).executeQuery();
            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    /**
     * 通用增删改方法
     * @param sql 增删改SQL语句
     * @param params 增删改SQL变量
     * @return 执行成功条数
     */
    public static int executeUpdate(String sql, Object[] params) {
        int count = 0;
        try {
            count = createPreparedStatement(sql, params).executeUpdate();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

    /**
     * 通用查询方法，使用后应该调用DBUtil.closeAll()方法释放资源
     * @param sql 查询SQL语句
     * @param params 查询SQL变量
     * @return 结果集
     */
    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            rs = createPreparedStatement(sql, params).executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
