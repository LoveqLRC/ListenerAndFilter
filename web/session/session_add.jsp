<%@ page import="com.loveqrc.domian.Person" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/30
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
 session.setAttribute("person",new Person("rc","123"));
%>

</body>
</html>
