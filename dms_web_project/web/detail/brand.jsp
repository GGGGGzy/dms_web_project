<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备详情</title>
</head>
<body>
<form action="/brand" method="post">
    <input type="hidden" name="method" value="update">
    品牌编号: <input type="text" name="id" value="${requestScope.brand.id}" readonly>
    <br>
    名称: <input type="text" name="name" value="${requestScope.brand.name}">
    <br>
    创建时间: <input type="text" name="createTime" value="${requestScope.brand.createTime}" readonly>
    <br>
    创建人: <input type="text" name="createOperator" value="${requestScope.brand.createOperator}" readonly>
    <br>
    修改时间: <input type="text" name="updateTime" value="${requestScope.brand.updateTime}" readonly>
    <br>
    修改人: <input type="text" name="updateOperator" value="${requestScope.brand.updateOperator}" readonly>
    <input type="submit" value="修改">
    <a href="/brand">返回</a>
</form>
</body>
</html>
