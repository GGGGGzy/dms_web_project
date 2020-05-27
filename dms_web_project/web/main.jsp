<%@ page import="org.dms.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
    <h3>欢迎，<%= ((User)session.getAttribute("user")).getUsername() %>！</h3>
    <ul>
        <li><a href="/device">设备管理</a></li>
        <li><a href="receive.jsp">设备领用</a></li>
        <li><a href="refund.jsp">设备退还</a></li>
        <li><a href="report.jsp">设备报修</a></li>
        <li><a href="repair.jsp">设备维修</a></li>
        <li><a href="replace.jsp">设备更换</a></li>
        <li><a href="lose.jsp">丢失报废</a></li>
    </ul>
</body>
</html>
