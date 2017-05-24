<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-5-24
  Time: 下午12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/changePassword" method="post">
        <table align="center">
            <tr>
                <td>用户id：</td>
                <td><input type="text" name="uid"/></td>
            </tr>
            <tr>
                <td>旧密码：</td>
                <td><input type="password" name="oldPassword"/></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" name="newPassword"/></td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td><input type="password" name="againPassword"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"/></td>
                <td><input type="reset" value="重置"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
