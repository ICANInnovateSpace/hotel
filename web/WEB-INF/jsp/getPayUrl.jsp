<%--
  Created by IntelliJ IDEA.
  User: mrzhou
  Date: 17-2-18
  Time: 下午6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订房</title>
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
            input[2].value = input[2].value + " " + input[3].value;
            var form = document.forms[0];
            form.action = "${pageContext.request.contextPath}/order/getPayUrl";
            form.method = "post";
            form.submit();
        }
    </script>
</head>
<body>
<form>
    <table align="center">
        <tr>
            <td>客房号：</td>
            <td><input type="text" name="room.rid"/></td>
        </tr>
        <tr>
            <td>用户id：</td>
            <td><input type="text" name="ouid"/></td>
        </tr>
        <tr>
            <td>入住时间：</td>
            <td>
                <input type="text" class="input" id="pickdate" name="odate" />
                <input type="text" class="input" id="picktime" name="orderTime" />
            </td>
        </tr>
        <tr>
            <td>入住天数：</td>
            <td><input type="text" name="odays"/></td>
        </tr>
        <tr>
            <td><input type="button" value="下单" onclick="fun();"/></td>
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
