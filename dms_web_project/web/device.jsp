<%@ page import="org.dms.entity.Pagination" %>
<%@ page import="org.dms.entity.Device" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设备管理</title>
</head>
<body>
<% Pagination<Device> pagination = (Pagination<Device>) request.getAttribute("pagination");%>
<table border="1">
    <thead>
    <tr>
        <th>设备编码</th>
        <th>批次</th>
        <th>设备类型</th>
        <th>设备品牌</th>
        <th>设备名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="devices" value="${requestScope.pagination.data}" scope="page"/>
    <c:forEach var="device" items="${pageScope.devices}">
        <tr>
            <td>
                <a href="/device?method=detail&code=${pageScope.device.code}">${pageScope.device.code}</a>
            </td>
            <td>
                    ${pageScope.device.batch}
            </td>
            <td>
                    ${pageScope.device.type}
            </td>
            <td>
                    ${pageScope.device.brand}
            </td>
            <td>
                    ${pageScope.device.name}
            </td>
            <td>
                <a href="/device?currentPage=${requestScope.pagination.currentPage}&pageSize=${requestScope.pagination.pageSize}&method=delete&code=${pageScope.device.code}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="add/device.jsp">新增</a><br>
共 ${requestScope.pagination.totalCount} 条记录, 每页显示
<form action="/device" style="display: inline-block">
    <select name="pageSize">
        <c:forEach begin="5" end="20" step="5" varStatus="status">
            <option value="${status.index}" <c:if test="${requestScope.pagination.pageSize == status.index}"> selected </c:if>>
                ${status.index}
            </option>
        </c:forEach>
    </select>
    条记录
    <input type="submit" value="确定">
</form>
<br>
<%
    if (pagination.getCurrentPage() != 1) {
%>
<a href="/device?currentPage=<%= 1 %>&pageSize=<%= pagination.getPageSize() %>">首页</a>
<a href="/device?currentPage=<%= pagination.getCurrentPage() - 1 %>&pageSize=<%= pagination.getPageSize() %>">上一页</a>
<%
    }
%>
<%
    if (pagination.getCurrentPage() != pagination.getTotalPage()) {
%>
<a href="/device?currentPage=<%= pagination.getCurrentPage() + 1 %>&pageSize=<%= pagination.getPageSize() %>">下一页</a>
<a href="/device?currentPage=<%= pagination.getTotalPage() %>&pageSize=<%= pagination.getPageSize() %>">尾页</a>
<%
    }
%>
</body>
</html>
