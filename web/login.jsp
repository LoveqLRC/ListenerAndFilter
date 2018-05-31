<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/31
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td colspan="1"><input type="checkbox" name="saveName" value="ok">记住用户名</td>
            <td colspan="1"><input type="checkbox" name="autoLogin" value="ok">自动登录</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>

    </table>

</form>
</body>
<script type="text/javascript">
    onload = function (ev) {
        var s = "${cookie.saveName.value}";
        s = decodeURI(s);

        document.getElementsByName("username")[0].value = s;

    }
</script>
</html>
