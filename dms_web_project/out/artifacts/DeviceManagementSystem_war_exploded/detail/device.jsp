<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.dms.entity.Device" %>
<%@ page import="org.dms.enums.DeviceType" %>
<%@ page import="org.dms.enums.DeviceStatus" %>
<%@ page import="org.dms.util.StringUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备详情</title>
</head>
<body>
<form action="/device" method="post">
    <input type="hidden" name="method" value="update">
    设备编号: <input type="text" name="code" value="${requestScope.device.code}" readonly>
    <br>
    主设备编号: <input type="text" name="parent" value="${requestScope.device.parent}">
    <br>
    批次: <input type="date" name="batch" value="${requestScope.device.batch}">
    <br>
    设备类型:
    <select name="type">
        <%
            for (DeviceType deviceType : DeviceType.values()) {
        %>
        <option value="<%= deviceType.getType() %>" <%= deviceType.getType() == ((Device) request.getAttribute("device")).getType() ? "selected" : "" %>>
            <%= deviceType.getTypeName() %>
        </option>
        <%
            }
        %>
    </select>
    <br>
    设备品牌: <%--<input type="text" name="brand" value="${requestScope.device.brand}">--%>
    <select>
        <c:forEach var="brand" items="${requestScope.brands}">
            <option value="${brand.name}" <c:if test="${brand.name == requestScope.device.brand}">selected</c:if>>
                    ${brand.name}
            </option>
        </c:forEach>
    </select>
    <a href="/brand">详情</a>
    <br>
    设备名称: <input type="text" name="name" value="${requestScope.device.name}">
    <br>
    设备型号: <input type="text" name="model" value="${requestScope.device.model}">
    <br>
    IMEI1: <input type="text" name="imei1" value="${requestScope.device.imei1}">
    <br>
    IMEI2: <input type="text" name="imei2" value="${requestScope.device.imei2}">
    <br>
    MEID: <input type="text" name="meid" value="${requestScope.device.meid}">
    <br>
    SIM: <input type="text" name="sim" value="${requestScope.device.sim}">
    <br>
    SN: <input type="text" name="sn" value="${requestScope.device.sn}">
    <br>
    设备状态:
    <select name="status">
        <%
            for (DeviceStatus deviceStatus : DeviceStatus.values()) {
        %>
        <option value="${pageScope.deviceStatus.statusName}" ${pageScope.deviceStatus.status == requestScope.device.status ? "selected" : "disabled"}>
            ${pageScope.deviceStatus.statusName}
        </option>
        <%
            }
        %>
    </select>
    <br>
    备注: <input type="text" name="note" value="${requestScope.device.note}">
    <br>
    创建时间: <input type="text" name="createTime" value="${requestScope.device.createTime}" readonly>
    <br>
    创建人: <input type="text" name="createOperator" value="${requestScope.device.createOperator}" readonly>
    <br>
    最后操作时间: <input type="text" name="updateTime" value="${requestScope.device.updateTime}" readonly>
    <br>
    操作人: <input type="text" name="updateOperator" value="${requestScope.device.updateOperator}" readonly>
    <br>
    <input type="submit" value="修改">
    <a href="/device">返回</a>
</form>
</body>
</html>
