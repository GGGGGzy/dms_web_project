<%@ page import="org.dms.entity.Device" %>
<%@ page import="org.dms.enums.DeviceType" %>
<%@ page import="org.dms.enums.DeviceStatus" %>
<%@ page import="org.dms.util.StringUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/27 0027
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备详情</title>
</head>
<body>
<% Device device = (Device) request.getAttribute("device"); %>
<form action="/device" method="post">
    <input type="hidden" name="method" value="update">
    设备编号: <input type="text" name="code" value="<%= StringUtil.emptyOrValue(device.getCode()) %>" readonly>
    <br>
    主设备编号: <input type="text" name="parent" value="<%= StringUtil.emptyOrValue(device.getParent()) %>">
    <br>
    批次: <input type="date" name="batch" value="<%= StringUtil.emptyOrValue(device.getBatch()) %>">
    <br>
    设备类型:
    <select name="type">
        <%
            for (DeviceType deviceType : DeviceType.values()) {
        %>
        <option value="<%= deviceType.getType() %>" <%= device.getType() == deviceType.getType() ? "selected" : "" %>><%= deviceType.getTypeName() %>
        </option>
        <%
            }
        %>
    </select>
    <br>
    设备品牌: <input type="text" name="brand" value="<%= StringUtil.emptyOrValue(device.getBrand()) %>">
    <br>
    设备名称: <input type="text" name="name" value="<%= StringUtil.emptyOrValue(device.getName()) %>">
    <br>
    设备型号: <input type="text" name="model" value="<%= StringUtil.emptyOrValue(device.getModel()) %>">
    <br>
    IMEI1: <input type="text" name="imei1" value="<%= StringUtil.emptyOrValue(device.getImei1()) %>">
    <br>
    IMEI2: <input type="text" name="imei2" value="<%= StringUtil.emptyOrValue(device.getImei2()) %>">
    <br>
    MEID: <input type="text" name="meid" value="<%= StringUtil.emptyOrValue(device.getMeid()) %>">
    <br>
    SIM: <input type="text" name="sim" value="<%= StringUtil.emptyOrValue(device.getSim()) %>">
    <br>
    SN: <input type="text" name="sn" value="<%= StringUtil.emptyOrValue(device.getSn()) %>">
    <br>
    设备状态:
    <select name="status">
        <%
            for (DeviceStatus deviceStatus : DeviceStatus.values()) {
        %>
        <option value="<%= deviceStatus.getStatus() %>" <%= device.getStatus() == deviceStatus.getStatus() ? "selected" : "disabled" %>><%= deviceStatus.getStatusName() %>
        </option>
        <%
            }
        %>
    </select>
    <br>
    备注: <input type="text" name="note" value="<%= StringUtil.emptyOrValue(device.getNote()) %>">
    <br>
    创建时间: <input type="text" name="createTime" value="<%= StringUtil.emptyOrValue(device.getCreateTime() == null ? "" : device.getCreateTime().toString()) %>" readonly>
    <br>
    创建人: <input type="text" name="createOperator" value="<%= StringUtil.emptyOrValue(device.getCreateOperator()) %>" readonly>
    <br>
    最后操作时间: <input type="text" name="updateTime" value="<%= StringUtil.emptyOrValue(device.getUpdateTime() == null ? "" : device.getUpdateTime().toString()) %>" readonly>
    <br>
    操作人: <input type="text" name="updateOperator" value="<%= StringUtil.emptyOrValue(device.getUpdateOperator()) %>" readonly>
    <br>
    <input type="submit" value="提交">
    <a href="/device">返回</a>
</form>
</body>
</html>
