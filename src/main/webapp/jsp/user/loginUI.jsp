<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>岗位列表</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js" />
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js" />
    <![endif]-->
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>


    <!-- 表单校验的js插件, bootstrapValidator -->
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/mime/mime.js"></script>
    <script type="text/javascript">
        function commit() {
            var loginform = $("#loginform");
            loginform.submit(function () {
                $.ajax(
                        {
                            type: 'post',
                            url: "${pageContext.request.contextPath}/user/login",
                            data: loginform.serialize(),
                            success: function (data) {
                                var obj = eval('(' + data + ')');
                                if("OA0001" == obj.code) {
                                    location.href = "${pageContext.request.contextPath}/user/list" ;
                                } else {
                                    alert(obj.msg);
                                }
                            }
                        }
                );
                return false;
            });
        }
    </script>
</head>
<body>

<div class="container" style="width:300px;">
    <%-- --%>
    <form class="form-signin form-validate" id="loginform" role="form">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" name="username" class="form-control username" placeholder="用户名" required autofocus>
        <input type="text" name="password" class="form-control password" placeholder="密码" required>
        <div class="checkbox">
            <label>
                <input name="rememberme" type="checkbox" value="remember-me">记住我
            </label>
        </div>
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block submit" onclick="commit()">登录</button>
            <button class="btn btn-lg btn-primary btn-block" type="reset">重置</button>
        </div>
    </form>
</div>
</body>

</html>
