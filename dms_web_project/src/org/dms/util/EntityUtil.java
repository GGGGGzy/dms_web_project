package org.dms.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
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
            if (!methodMap.containsKey(methodName))
                continue;
            Method method = methodMap.get(methodName);
            Object param = parameter;
            String typeName = method.getParameterTypes()[0].getTypeName();
            try {
                if ("java.lang.Integer".equals(typeName)) {
                    param = Integer.valueOf(parameter);
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
        for (Method method : entityClass.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("get"))
                continue;
            String underlineMethodName = StringUtil.camelToUnderline(methodName.substring(methodName.startsWith("set") ? 3 : 2));
            try {
                method.invoke(entity, rs.getObject(underlineMethodName));
            } catch (SQLException e) {
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
}
