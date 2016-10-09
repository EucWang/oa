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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body>


<c:import url="/nav-head"/>

<div class="container-fluid">

    <div class="row">

        <c:import url="/nav-menu"/>

        <div class="col-sm-10 main table-responsive">

            <div class="table-responsive">
                <table id="roles" class="table table-striped">
                    <h2>雇员列表</h2>
                    <tr class="table-row-cell">
                        <th>雇员</th>
                        <th>昵称</th>
                        <th>手机号码</th>
                        <th>性别</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.nickname}</td>
                            <td>${user.phoneNumber}</td>
                            <td>
                            <c:if test="${user.gender == -1}">保密</c:if>
                            <c:if test="${user.gender == 0}">男</c:if>
                            <c:if test="${user.gender == 1}">女</c:if>
                            </td>
                            <td>${user.description}</td>
                            <td>
                                <button type="button" class="btn btn-default" onclick="toEditUI(${user.id})">修改</button>
                                <button type="button" class="btn btn-default" onclick="toDel(${user.id})">删除</button>
                        </tr>
                    </c:forEach>
                </table>
                <button type="button" class="btn btn-primary" id="btnAddUI" onclick="toAddUI()">新增</button>

                <!-- Modal -->
                <div class="modal fade" id="addOrEditDialogUI" tabindex="-1" role="dialog" aria-labelledby="modal_title"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title" id="modal_title"></h4>
                            </div>
                            <%--<div class="modal-body" id="modal_body">--%>
                            <%--</div>--%>
                            <div class="modal-footer" id="modal_body">
                                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                                <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                            </div>
                            <%--</div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function toAddUI() {
        $('#addOrEditDialogUI').modal({backdrop: false, show: true});
        $('#modal_title').html("添加雇员");
        $('#modal_body').load("${pageContext.request.contextPath}/user/addUI");
    }

    function toEditUI(userid) {
        $('#addOrEditDialogUI').modal({backdrop: false, show: true});
        $('#modal_title').html("雇员修改");
        $('#modal_body').load("${pageContext.request.contextPath}/user/editUI/" + userid);
    }
    function toDel(userid) {
        $(this).load("${pageContext.request.contextPath}/user/del/" + userid, function (data) {
            if (data) {
                window.location.reload();
            }
        });
        <%--$('#addOrEditDialogUI').modal({backdrop: false, show: true});--%>
        <%--$('#modal_title').html("岗位删除");--%>
        <%--$('#modal_body').load("${pageContext.request.contextPath}/role/delUI/" + roleid);--%>
    }
</script>
</body>
</html>