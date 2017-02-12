<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-2-10
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
</head>
<body>
    <table align="center">
        <form action="/user/login" method="post">
            <caption align="center">用户登陆</caption>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="uname"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="upsw"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="登陆"/></td>
                <td><input type="reset" value="重置"/></td>
            </tr>
        </form>

    </table>
</body>
</html>
