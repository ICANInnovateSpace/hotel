<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-5-11
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户订单</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/order/queryOrder" method="post">
        <table align="center">
            <tr>
                <td>用户id：</td>
                <td><input type="text" name="uid"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="查询"/></td>
                <td><input type="reset" value="重置" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
