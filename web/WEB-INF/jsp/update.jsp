<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-2-10
  Time: 下午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新信息</title>
</head>
<body>
    <table align="center">
        <form action="/user/update" method="post" enctype="multipart/form-data">
            <caption align="center">修改用户信息</caption>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="uname"/></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" name="usex" value="男" checked/>男
                    <input type="radio" name="usex" value="女"/>女
                </td>
            </tr>
            <tr>
                <td>头像：</td>
                <td><input type="file" name="photo"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"/></td>
                <td><input type="reset" value="重置"/></td>
            </tr>
        </form>

    </table>
</body>
</html>
