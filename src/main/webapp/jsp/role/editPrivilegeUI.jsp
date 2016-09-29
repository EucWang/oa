<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>岗位修改</title>
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
    <form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/role/editPrivilege"
          method="post">

        <input type="hidden" name="roleid" value="${roleid}"/>

        <c:forEach items="${privileges}" var="privilege">
            <div class="form-group checkbox">
                <label>
                    <input name="privilegeIds" type="checkbox" id="privilegeIds" value="${privilege.id}"
                        <c:if test="${privilege.checked}">
                            checked="checked"
                        </c:if>
                    >${privilege.name}
                </label>
            </div>
        </c:forEach>

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