<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->--%>
<%--<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>--%>
<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>


<nav class="navbar navbar-default navbar-fix-top" role="navigation" style="height:100px;">
    <div class="container-fluid">
        <div class="navbar-header" style="width: 100%;">
            <a class="navbar-brand" href="#">
                <h3>O A</h3>
            </a>
        </div>

        <div id="navbar" class="collapse navbar-collapse" style="margin-bottom: 5px;">
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${username}<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">个人信息</a></li>
                        <li><a href="#">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>