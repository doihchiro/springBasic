<%--
  Created by IntelliJ IDEA.
  User: l3lur
  Date: 2024-11-21
  Time: 오후 7:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>All</h1>
    <div>
        <sec:authorize access="isAuthenticated()">
            Logout
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            Login
        </sec:authorize>
    </div>
</body>
</html>
