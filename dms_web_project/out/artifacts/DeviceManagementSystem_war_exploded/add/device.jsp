<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.dms.enums.DeviceType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增设备</title>
</head>
<body>
<form action="/device" method="post">
    <input type="hidden" name="method" value="add">
    主设备编号: <input type="text" name="parent">
    <br>
    批次: <input type="date" name="batch">
    <br>
    设备类型:
    <select name="type">
        <%
            for (DeviceType deviceType : DeviceType.values()) {
        %>
        <option value="<%= deviceType.getType() %>"><%= deviceType.getTypeName() %></option>
        <%
            }
        %>
    </select>
    <br>
    设备品牌:
    <select name="type">
        <c:forEach var="brand" items="${requestScope.brand}">
            <option value="${brand.name}">
                ${brand.name}
            </option>
        </c:forEach>
    </select>
    <br>
    设备名称: <input type="text" name="name">
    <br>
    设备型号: <input type="text" name="model">
    <br>
    IMEI1: <input type="text" name="imei1">
    <br>
    IMEI2: <input type="text" name="imei2">
    <br>
    MEID: <input type="text" name="meid">
    <br>
    SIM: <input type="text" name="sim">
    <br>
    SN: <input type="text" name="sn">
    <br>
    备注: <input type="text" name="note">
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
