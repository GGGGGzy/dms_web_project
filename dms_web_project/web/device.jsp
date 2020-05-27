<%@ page import="org.dms.entity.Pagination" %>
<%@ page import="org.dms.entity.Device" %>
<%@ page import="org.dms.enums.DeviceType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备管理</title>
</head>
<body>

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
    <%
        Pagination<Device> pagination = (Pagination<Device>) request.getAttribute("pagination");
        for (Device device : pagination.getData()) {
    %>
    <tr>
        <td><a href="/device?method=detail&code=<%=  device.getCode() %>"><%= device.getCode() %></a>
        </td>
        <td><%= device.getBatch() %>
        </td>
        <td><%= DeviceType.getDeviceTypeByType(device.getType()).getTypeName() %>
        </td>
        <td><%= device.getBrand() %>
        </td>
        <td><%= device.getName() %>
        </td>
        <td><a href="/device?currentPage=<%= pagination.getCurrentPage() %>&pageSize=<%= pagination.getPageSize() %>&method=delete&code=<%= device.getCode() %>">删除</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<a href="add/device.jsp">新增</a><br>
共 <%= pagination.getTotalCount() %> 条记录, 每页显示
<form action="/device" style="display: inline-block">
    <select name="pageSize">
    <%
        for (int i = 5; i <= 20; i += 5) {
    %>
        <option value="<%= i %>" <%= pagination.getPageSize() == i ? "selected" : "" %>><%= i %>
        </option>
    <%
        }
    %>
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
