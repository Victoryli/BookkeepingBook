<%--
  Created by IntelliJ IDEA.
  User: liqing
  Date: 2019-12-25
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
id:&nbsp &nbsp ${requestScope.user.id}<br/>
email:&nbsp &nbsp ${requestScope.user.email}<br/>
username:&nbsp &nbsp ${requestScope.user.username}<br/>
role:&nbsp &nbsp ${requestScope.user.role}<br/>
mobile:&nbsp &nbsp ${requestScope.user.mobile}<br/>

<img src="/assets/img/test.jpg">

</body>
</html>
