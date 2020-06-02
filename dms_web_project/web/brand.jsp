<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>品牌详情</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>创建时间</th>
        <th>创建人</th>
        <th>修改时间</th>
        <th>修改人</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="brand" items="${requestScope.brands}">
        <tr>
            <td><a href="/brand?method=detail&id=${brand.id}">${brand.id}</a></td>
            <td>${brand.name}</td>
            <td>${brand.createTime}</td>
            <td>${brand.createOperator}</td>
            <td>${brand.updateTime}</td>
            <td>${brand.updateOperator}</td>
            <td><a href="/brand?method=delete&id=${brand.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
