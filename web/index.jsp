<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-2-9
  Time: 下午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <table align="center">
    <form action="/user/register" method="post">
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="uname"/></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="upsw"/></td>
      </tr>
      <tr>
        <td>手机号码：</td>
        <td><input type="text" name="uphone"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="注册"/></td>
        <td><input type="reset" value="重置"/></td>
      </tr>
    </form>
  </table>
  </body>
</html>
