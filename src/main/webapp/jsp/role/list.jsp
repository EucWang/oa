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

<c:import url="/nav/navhead"/>

<div class="container-fluid">

    <div class="row">

        <c:import url="/nav/navmenu"/>

        <div class="col-sm-10 main table-responsive">
            <div class="table-responsive">
                <table id="roles" class="table table-striped">
                    <h2>岗位列表</h2>
                    <tr class="table-row-cell">
                        <th>岗位</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${roles}" var="role">
                        <tr>
                            <td>${role.name}</td>
                            <td>${role.description}</td>
                            <td>
                                <button type="button" class="btn btn-default" onclick="toEditUI(${role.id})">修改</button>
                                <button type="button" class="btn btn-default" onclick="toDel(${role.id})">删除</button>
                                <button type="button" class="btn btn-default" onclick="toEditPrivilegeUI(${role.id})">
                                    修改权限
                                </button>
                            </td>
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


    <script>
        function toAddUI() {
            $('#addOrEditDialogUI').modal({backdrop: false, show: true});
            $('#modal_title').html("添加岗位");
            $('#modal_body').load("${pageContext.request.contextPath}/role/addUI");
        }

        function toEditUI(roleid) {
            $('#addOrEditDialogUI').modal({backdrop: false, show: true});
            $('#modal_title').html("岗位修改");
            $('#modal_body').load("${pageContext.request.contextPath}/role/editUI/" + roleid);
        }
        function toDel(roleid) {
            $(this).load("${pageContext.request.contextPath}/role/del/" + roleid, function (data) {
                if (data) {
                    window.location.reload();
                }
            });
            <%--$('#addOrEditDialogUI').modal({backdrop: false, show: true});--%>
            <%--$('#modal_title').html("岗位删除");--%>
            <%--$('#modal_body').load("${pageContext.request.contextPath}/role/delUI/" + roleid);--%>
        }

        function toEditPrivilegeUI(roleid) {
            $('#addOrEditDialogUI').modal({backdrop: false, show: true});
            $('#modal_title').html("权限修改");
            $('#modal_body').load("${pageContext.request.contextPath}/role/editPrivilegeUI/" + roleid);
        }
    </script>
</div>

</body>
</html>