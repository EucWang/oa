<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>部门列表</title>
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
            <table id="departments" class="table table-striped">
                <h2>版块列表</h2>
                <tr class="table-row-cell">
                    <th>版块名称</th>
                    <th>描述</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${forums}" var="forum">
                    <tr>
                        <td>${forum.name}</td>
                        </td>
                        <td>${forum.description}</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="toEditUI(${forum.id})">修改</button>
                            <button type="button" class="btn btn-default" onclick="toDel(${forum.id})">删除</button>
                            <button type="button" class="btn btn-default" onclick="toMovePosition(${forum.id},true)">上移</button>
                            <button type="button" class="btn btn-default" onclick="toMovePosition(${forum.id},false)">下移</button>
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
                        <div class="modal-footer" id="modal_body">
                        </div>
                    </div>
                </div>
            </div>

            <!-- 消息对话提示框 -->
            <div class="modal fade" id="message_dialog" tabindex="-2" role="dialog" aria-labelledby="output_info_title">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="output_info_title"></h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label id="output_info"></label>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                <span aria-hidden="true">关闭</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    function showMsgDialog(title, content) {
        $("#message_dialog").modal({
            backdrop: false,
            show: true
        });
        $("#output_info_title").text(title);
        $("#output_info").text(content);
    }

    function toAddUI() {
        console.log("toAddUI()");
        $('#addOrEditDialogUI').modal({
            backdrop: false,
            show: true
        });
        $('#modal_title').html("添加版块");
        $('#modal_body').load("${pageContext.request.contextPath}/forum/addUI");
    }

    function toEditUI(forumid) {
        $('#addOrEditDialogUI').modal({
            backdrop: false,
            show: true
        });
        $('#modal_title').html("版块修改");
        var url = "${pageContext.request.contextPath}/forum/editUI?forumid=" + forumid;
        $('#modal_body').load(url);
    }

    function toDel(forumid) {
        $(this).load("${pageContext.request.contextPath}/forum/delete?forumId=" + forumid, function (data) {
            if (1 == data) {
                window.location.reload();
            } else {
                console.log("删除失败!");
                showMsgDialog("提示!", "删除失败!");
            }
        });
    }

    function toMovePosition(forumid, isUp) {
        $(this).load("${pageContext.request.contextPath}/forum/movePosition?forumId=" + forumid + "&isUp=" + isUp , function (data) {
            if (1 == data) {
                window.location.reload();
            } else {
                console.log("移动失败!");
                showMsgDialog("提示!", "移动失败!");
            }
        });
    }
</script>
</body>
</html>