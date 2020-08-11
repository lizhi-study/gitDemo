<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/23
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"/>
    <script type="text/javascript">


            $.ajax({
                url:"/text/t3",
                type:'post',
                data:JSON.stringify({id:'1',name:'张三'}),
                datatype:'json',
                contentType:'application/json',
                success:function (obj) {

                }
            })
    </script>
</head>


<body>
<form action="${pageContext.request.contextPath}/text/t1">
    <input type="checkbox" value="1" name="check">川端康成
    <input type="checkbox" value="2" name="check">东野圭吾
    <input type="checkbox" value="3" name="check">太宰治
    <input type="submit" value="测试">

</form>
<button type="button"onclick="add()"/>开始啦

</body>
</html>
