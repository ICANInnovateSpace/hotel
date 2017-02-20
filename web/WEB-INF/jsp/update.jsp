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
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/html5-date/css/datedropper.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/html5-date/css/timedropper.min.css">
    <style type="text/css">
        .demo{margin:80px auto 40px auto;width:320px}
        .input{padding:6px;border:1px solid #d3d3d3}
    </style>
</head>
<body>
    <table align="center">
        <form action="${pageContext.request.contextPath}/user/update" method="post" enctype="multipart/form-data">
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
                <td>出生日期：</td>
                <td>
                    <input type="text" class="input" id="pickdate" name="uborn" />
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/html5-date/js/jquery-1.12.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/html5-date/js/datedropper.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/html5-date/js/timedropper.min.js"></script>
    <script>
        $("#pickdate").dateDropper({
            animate: false,
            format: 'Y-m-d',
            maxYear: '2020'
        });
    </script>
</body>
</html>
