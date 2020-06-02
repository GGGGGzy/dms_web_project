<%@ page import="org.dms.enums.DeviceType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备品牌</title>
</head>
<body>
<form action="/brand" method="post">
    <input type="hidden" name="method" value="add">
    名称: <input type="text" name="name">
    <input type="submit" value="提交">
</form>
</body>
</html>
