package org.dms.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EntityUtil {
    public static <T> void setPropertiesByRequest(T entity, HttpServletRequest request) {
        Class entityClass = entity.getClass();
        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : entityClass.getMethods()) {
            methodMap.putIfAbsent(method.getName(), method);
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String methodName = "set" + StringUtil.firstToUpper(entry.getKey());
            String parameter = StringUtil.nullOrValue(entry.getValue()[0]);
            if (parameter == null) {
                continue;
            }
            if (!methodMap.containsKey(methodName))
                continue;
            Method method = methodMap.get(methodName);
            Object param = parameter;
            String typeName = method.getParameterTypes()[0].getTypeName();
            try {
                if ("java.lang.Long".equals(typeName)) {
                    param = Long.valueOf(parameter);
                } else if ("java.sql.Date".equals(typeName)) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    param = new Date(format.parse(parameter).getTime());
                } else if ("java.sql.Timestamp".equals(typeName)) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    param = new Timestamp(format.parse(parameter).getTime());
                }
                method.invoke(entity, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void setPropertiesByResultSet(T entity, ResultSet rs) {
        Class entityClass = entity.getClass();
        for (Method method : entityClass.getMethods()) {
            String methodName = method.getName();
            if (!methodName.startsWith("set"))
                continue;
            String underlineMethodName = StringUtil.camelToUnderline(methodName.substring(3));
            try {
                method.invoke(entity, rs.getObject(underlineMethodName));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                try {
                    Object object = rs.getObject(underlineMethodName);
                    System.out.println(object);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    public static <T> void setCreateInformation(T entity, String operator) {
        Class entityClass = entity.getClass();
        try {
            Method setCreateOperator = entityClass.getMethod("setCreateOperator", String.class);
            Method setUpdateOperator = entityClass.getMethod("setCreateOperator", String.class);
            Method setCreateTime = entityClass.getMethod("setCreateTime", Timestamp.class);
            Method setUpdateTime = entityClass.getMethod("setUpdateTime", Timestamp.class);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            setCreateOperator.invoke(entity, operator);
            setUpdateOperator.invoke(entity, operator);
            setCreateTime.invoke(entity, timestamp);
            setUpdateTime.invoke(entity, timestamp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void setUpdateInformation(T entity, String operator) {
        Class entityClass = entity.getClass();
        try {
            Method setUpdateOperator = entityClass.getMethod("setUpdateOperator", String.class);
            Method setUpdateTime = entityClass.getMethod("setUpdateTime", Timestamp.class);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            setUpdateOperator.invoke(entity, operator);
            setUpdateTime.invoke(entity, timestamp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
