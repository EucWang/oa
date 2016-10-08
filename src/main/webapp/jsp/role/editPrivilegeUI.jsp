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
    <style>
        #privilege_tree ul {
            list-style: none;
            margin-left: 15px;
        }

        #privilege_tree li {
            list-style: none;
        }
    </style>
</head>

<body background="#fff">
<div class="container col-xs-10">
    <form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/role/editPrivilege"
          method="post">

        <input type="hidden" name="roleid" value="${roleid}"/>

        <div class="form-group checkbox text-left">
            <ul id="privilege_tree">
                <c:forEach items="${privileges}" var="privilege" varStatus="status">
                    <li>
                        <label>
                            <input name="privilegeIds" type="checkbox" id="privilegeId_${privilege.id}"
                                   value="${privilege.id}"
                                   onclick="privilegeCheck(this)"
                            <c:if test="${privilege.checked}">
                                   checked="checked"
                            </c:if>
                            >${privilege.name}
                        </label>

                        <c:if test="${privilege.children != null}">
                            <ul>
                                <c:forEach items="${privilege.children}" var="privilege1" varStatus="status">
                                    <li>
                                        <label>
                                            <input name="privilegeIds" type="checkbox" id="privilegeId_${privilege1.id}"
                                                   value="${privilege1.id}"
                                                   onclick="privilegeCheck(this)"
                                            <c:if test="${privilege1.checked}">
                                                   checked="checked"
                                            </c:if>
                                            >${privilege1.name}
                                        </label>

                                        <c:if test="${privilege1.children != null}">
                                            <ul>
                                                <c:forEach items="${privilege1.children}" var="privilege2"
                                                           varStatus="status">
                                                    <li>
                                                        <label>
                                                            <input name="privilegeIds" type="checkbox"
                                                                   id="privilegeId_${privilege2.id}"
                                                                   value="${privilege2.id}"
                                                                   onclick="privilegeCheck(this)"
                                                            <c:if test="${privilege2.checked}">
                                                                   checked="checked"
                                                            </c:if>
                                                            >${privilege2.name}
                                                        </label>

                                                        <c:if test="${privilege2.children != null}">
                                                            <ul>
                                                                <c:forEach items="${privilege2.children}"
                                                                           var="privilege3" varStatus="status">
                                                                    <li>
                                                                        <label>
                                                                            <input name="privilegeIds" type="checkbox"
                                                                                   id="privilegeId_${privilege3.id}"
                                                                                   value="${privilege3.id}"
                                                                                   onclick="privilegeCheck(this)"
                                                                            <c:if test="${privilege3.checked}">
                                                                                   checked="checked"
                                                                            </c:if>
                                                                            >${privilege3.name}
                                                                        </label>

                                                                        <c:if test="${privilege.children != null}">

                                                                        </c:if>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </c:if>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="form-group">
            <%--col-sm-offset-2 --%>
            <div class="col-sm-10">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</div>

<script>
    function privilegeCheck(input) {
        var lis = $(input).parent().parent().children("ul").children("li");
        var inputs = $(lis).find("input");
        if (input.checked) {
            for (i = 0; i < inputs.size(); i++) {
                $(inputs[i]).prop('checked', true);
            }
        } else {
            for (i = 0; i < inputs.size(); i++) {
                $(inputs[i]).removeAttr('checked');
            }
        }

        //获取input的所有父元素是LI的元素的集合
        var ps = $(input).parents("li");
        console.log("parents size is ::" + ps.size());
        for (i = 0; i < ps.size(); i++) {
            //获取父元素是LI的元素的第一个子元素是LABEL的元素以及label中的input元素
            var children = $(ps[i]).children("label:first");
            console.log("label is : " + $(children).prop("tagName"));
            if ("LABEL" == $(children).prop("tagName")) {
                children = children.children("input:first");
                if ("INPUT" != $(children).prop("tagName")) {
                    continue;
                }
            }

            //判断这个input元素是否就是当前被点击的input元素,是,则跳过循环
            if ($(input).prop("id") == $(children).prop("id")) {
                continue;
            }

            console.log("change checked status of children tag, id" + $(children).prop("tagName") + ", " +
                    $(children).prop("id"));
            if (input.checked) {
                $(children).prop('checked', true);
            } else {

                // 取消选择这个input 元素时,判断 下级input元素是否存在处于被选择状态的input子元素
                var inputs = $(ps[i]).find("input");

                var hasChecked = false;
                for (j = 0; j < inputs.size(); i++) {
                    if (inputs[j].checked && $(inputs[j]).prop("id") != $(input).prop("id")) {
                        hasChecked = true;
                        break;
                    }
                }
                if (!hasChecked) {
                    $(children).removeAttr('checked');
                }
            }
        }
    }
</script>
</body>
</html>