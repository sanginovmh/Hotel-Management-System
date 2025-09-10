<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/auth/login" method="post">

</form>

<label>
    Not a customer?
    <select>
        <a href="${pageContext.request.contextPath}/auth/manager/login"> Manager </a>
        <a href="${pageContext.request.contextPath}/auth/admin/login"> Admin </a>
    </select>
</label>

</body>
</html>