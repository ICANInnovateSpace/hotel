<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-2-17
  Time: 下午7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询客房</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/html5-date/css/datedropper.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/html5-date/css/timedropper.min.css">
    <style type="text/css">
        .demo{margin:80px auto 40px auto;width:320px}
        .input{padding:6px;border:1px solid #d3d3d3}
    </style>
    <script type="text/javascript">
        function fun(){
            var time;
            var input = document.getElementsByTagName('input');
            input[0].value = input[0].value + " " + input[1].value;
            var form = document.forms[0];
            form.action = "${pageContext.request.contextPath}/room/query";
            form.method = "post";
            form.submit();
        }
    </script>

</head>
<body>
<form>
    <table align="center">
        <tr>
            <td>选择房间类型：</td>
            <td>
                <select name="rtype">
                    <option value="1">单人房</option>
                    <option value="2">双人房</option>
                    <option value="3">家庭房</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>预定时间：</td>
            <td>
                <input type="text" class="input" id="pickdate" name="orderDate" />
                <input type="text" class="input" id="picktime" name="orderTime" />
            </td>
        </tr>
        <tr>
            <td>预定天数：</td>
            <td><input type="text" name="days"/></td>
        </tr>
        <tr>
            <td><input type="button" value="查询" onclick="fun();"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/html5-date/js/jquery-1.12.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/html5-date/js/datedropper.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/html5-date/js/timedropper.min.js"></script>
<script>
    $("#pickdate").dateDropper({
        animate: false,
        format: 'Y-m-d',
        maxYear: '2020'
    });
    $("#picktime").timeDropper({
        meridians: false,
        format: 'HH:mm',
    });

</script>
</body>
</html>
