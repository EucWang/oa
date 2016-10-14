<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>雇员修改</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- 时间控件的js插件 -->
    <link href="${pageContext.request.contextPath}/css/datetimepicker.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"/>
    <script src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"/>

    <!-- 表单校验的js插件, bootstrapValidator -->
    <link href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"/>


</head>
<body background="#fff">
<div class="container" style="width:auto;">
    <form role="form" id="addUserForm" class="form-horizontal form-validate"
          action="${pageContext.request.contextPath}/user/edit"
          method="post">

        <input type="hidden" name="id" value="${user.id}"/>

        <div class="form-group">
            <label for="department" class="col-sm-2 control-label">所属部门</label>
            <div class="col-sm-10">
                <select class="form-control" name="department" id="department">
                    <option value="0">无</option>
                    <c:forEach items="${departments}" var="department">
                        <option value="${department.id}"
                                <c:if test="${user.department.id == department.id}">
                                    selected
                                </c:if>
                        >${department.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label for="role" class="col-sm-2 control-label">所在岗位</label>
            <div class="col-sm-10">
                <select class="form-control" name="role" id="role" multiple>
                    <option value="0">无</option>
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}"
                                <c:forEach items="${user.roles}" var="userrole">
                                    <c:if test="${role.id == userrole.id}">
                                        selected
                                    </c:if>
                                </c:forEach>
                        >${role.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">雇员名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" value="${user.name}"
                       placeholder="输入雇员名称"/>
            </div>
        </div>

        <div class="form-group">
            <label for="nickname" class="col-sm-2 control-label">雇员昵称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="nickname" id="nickname" placeholder="输入雇员昵称"
                       value="${user.nickname}">
            </div>
        </div>

        <div class="form-group">
            <label for="phoneNum" class="col-sm-2 control-label">电话号码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="phoneNum" id="phoneNum" placeholder="输入电话号码"
                       value="${user.phoneNumber}">
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="email" id="email" placeholder="输入邮箱"
                       value="${user.email}">
            </div>
        </div>

        <div class="form-group">
            <label for="birthday" class="col-sm-2 control-label">生日</label>
            <div class="col-sm-10">
                <%--value="12-02-2012"--%>
                <input class="form_datetime1 form-control" size="16" type="text" name="birthday" id="birthday" readonly
                       value='<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />'>
            </div>
        </div>

        <div class="form-group">
            <label for="gender" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-10">
                <select class="form-control" name="gender" id="gender">
                    <option value="-1"
                            <c:if test="${user.gender == -1}">selected</c:if>
                    >保密
                    </option>
                    <option value="0"
                            <c:if test="${user.gender == 0}">selected</c:if>
                    >男
                    </option>
                    <option value="1"
                            <c:if test="${user.gender == 1}">selected</c:if>
                    >女
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">雇员描述</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" placeholder="输入岗位描述" id="description" name="description"
                       value="${user.description}"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/mime/mime.js"/>
</body>
</html>