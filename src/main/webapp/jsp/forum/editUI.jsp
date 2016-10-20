<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>版块修改</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body background="#fff">
<div class="container col-xs-8">
    <form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/forum/edit/"
          method="post">

        <input type="hidden" name="id" value="${forum.id}"/>

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">版块名称</label>
            <div class="col-sm-10">
                <input name="name" type="text" class="form-control" placeholder="输入版块称" value="${forum.name}"
                       id="name"/>
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">版块描述</label>
            <div class="col-sm-10">
                <input name="description" type="text" class="form-control" placeholder="输入版块描述"
                       value="${forum.description}" id="description"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>