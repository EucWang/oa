<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加雇员</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="//cdn.bootcss.com/twitter-bootstrap/2.2.2/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/prettify/r224/prettify.css" rel="stylesheet">
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="//cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/prettify/r224/prettify.js"></script>

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
    <form role="form" id="addUserForm" class="form-horizontal" action="${pageContext.request.contextPath}/user/add"
          method="post">

        <div class="form-group">
            <label for="department" class="col-sm-2 control-label">所属部门</label>
            <div class="col-sm-10">
                <select class="form-control" name="department" id="department">
                    <option value="0">无</option>
                    <c:forEach items="${departments}" var="department">
                        <option value="${department.id}">${department.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">雇员名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" placeholder="输入雇员名称"/>
            </div>
        </div>

        <div class="form-group">
            <label for="nickname" class="col-sm-2 control-label">雇员昵称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="nickname" id="nickname" placeholder="输入雇员昵称">
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">初始密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="输入初始密码">
            </div>
        </div>

        <div class="form-group">
            <label for="phoneNum" class="col-sm-2 control-label">电话号码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="phoneNum" id="phoneNum" placeholder="输入电话号码">
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="email" id="email" placeholder="输入邮箱">
            </div>
        </div>

        <div class="form-group">
            <label for="birthday" class="col-sm-2 control-label">生日</label>
            <div class="col-sm-10">
                <%--value="12-02-2012"--%>
                <input class="form_datetime1 form-control" size="16" type="text" name="birthday" id="birthday" readonly>
            </div>
        </div>

        <div class="form-group">
            <label for="gender" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-10">
                <select class="form-control" name="gender" id="gender">
                    <option value="-1">保密</option>
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">雇员描述</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" placeholder="输入岗位描述" id="description" name="description"/>
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
</body>
<script>
    //    $.fn.datetimepicker.dates['zh'] = {
    //        days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
    //        daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    //        daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
    //        months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    //        monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
    //        today: "Today"
    //    };
    $(".form_datetime1").datetimepicker({
                language: 'zh-CN',
                minView: 'month',
                autoclose: true,
                todayHighlight: true,
                format: 'yyyy-mm-dd',
                forceParse: true,
                todayBtn: true,
                initialDate: new Date()

            }
    );

    $("#addUserForm").bootstrapValidator(
            {
                message: '这个值没有被验证',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 64,
                                message: '用户名长度在6到64之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名必须由字母数组和下划线组成'
                            }
                        }
                    },
                    gender: {},
                    nickname: {},
                    password: {},
                    phoneNum: {
                        validators: {
                            notEmpty: {
                                message: '手机号码不能为空'
                            },
                            stringLength: {
                                min: 11,
                                max: 11,
                                message: '手机号码必须是11位号码'
                            },
                            regexp: {
                                regexp: /^1[0-9]+$/,
                                message: '手机号码格式错误'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空'
                            },
                            emailAddress: {
                                message: '邮箱格式错误'
                            }

                        }
                    },
                    description: {},
                    birthday: {
                        validators: {
                            date: {
                                format:"YYYY-MM-DD",
                                message: "日期格式错误"
                            }
                        }
                    },
                    department: {
                        validators: {
                            integer: {
                                message: '部门数据格式错误'
                            }
                        }
                    },
                }
            }
    );
</script>

</html>