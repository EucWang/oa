<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<style type="text/css">
    #privilege_tree ul {
        list-style: none;
        margin-left: 0px;
        margin-top: 0px;
        margin-bottom: 0px;
        margin-right: 0px;

        padding-left: 0px;
        padding-top: 0px;
        padding-bottom: 0px;
        padding-right:0px;
        align-content: center;
    }

    #privilege_tree li {
        list-style: none;
        margin-left: 0px;
        margin-top: 0px;
        margin-bottom: 0px;
        margin-right: 0px;

        padding-left: 0px;
        padding-top: 0px;
        padding-bottom: 0px;
        padding-right:0px;
        align-content: center;
    }
</style>
<div class="col-sm-2 sidebar">
    <%--<ul class="nav nav-sidebar">--%>
    <%--<li>--%>
    <%--<a href="${pageContext.request.contextPath}/role/list">岗位管理</a>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<a href="${pageContext.request.contextPath}/user/list">用户管理</a>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<a href="${pageContext.request.contextPath}/department/list">部门管理</a>--%>
    <%--</li>--%>
    <%--</ul>--%>

    <div class="btn-group-vertical">
        <ul id="privilege_tree" class="nav nav-sidebar list-group">
            <c:forEach items="${privileges}" var="privilege" varStatus="status">
                <li class="list-group-item">
                    <label>
                        <a <c:if test="${privilege.url != null}">href="${pageContext.request.contextPath}${privilege.url}"</c:if>
                           <c:if test="${privilege.url == null}">href="#"</c:if>
                                id="privilegeId_${privilege.id}"
                           onclick="privilegeCheck(this)">${privilege.name}</a>
                        <c:if test="${privilege.children != null && privilege.children.size()>0}">
                            <span class="caret"></span>
                        </c:if>
                    </label>
                    <c:if test="${privilege.children != null}">
                        <ul>
                            <c:forEach items="${privilege.children}" var="privilege1" varStatus="status">
                                <li>
                                    <label> <a <c:if test="${privilege1.url != null}">href="${pageContext.request.contextPath}${privilege1.url}"</c:if>
                                               <c:if test="${privilege1.url == null}">href="#"</c:if>
                                               id="privilegeId_${privilege1.id}"
                                               onclick="privilegeCheck(this)">${privilege1.name}</a>
                                        <c:if test="${privilege1.children != null && privilege1.children.size()>0}">
                                            <span class="caret"></span>
                                        </c:if>
                                    </label>
                                    <c:if test="${privilege1.children != null}">
                                        <ul class="list-group">
                                            <c:forEach items="${privilege1.children}" var="privilege2"
                                                       varStatus="status">
                                                <li class="list-group-item">

                                                    <label>   <a <c:if test="${privilege2.url != null}">href="${pageContext.request.contextPath}${privilege2.url}"</c:if>
                                                                   <c:if test="${privilege2.url == null}">href="#"</c:if>
                                                                   id="privilegeId_${privilege2.id}"
                                                               onclick="privilegeCheck(this)">${privilege2.name}</a>
                                                        <c:if test="${privilege2.children != null && privilege2.children.size()>0}">
                                                            <span class="caret"></span>
                                                        </c:if>
                                                    </label>
                                                    <c:if test="${privilege2.children != null}">
                                                        <ul class="list-group">
                                                            <c:forEach items="${privilege2.children}"
                                                                       var="privilege3" varStatus="status">
                                                                <li class="list-group-item">
                                                                    <label>
                                                                        <a <c:if test="${privilege3.url != null}">href="${pageContext.request.contextPath}${privilege3.url}"</c:if>
                                                                           <c:if test="${privilege3.url == null}">href="#"</c:if>
                                                                           id="privilegeId_${privilege3.id}"
                                                                           onclick="privilegeCheck(this)">${privilege3.name}</a>
                                                                        <c:if test="${privilege3.children != null && privilege3.children.size()>0}">
                                                                            <span class="caret"></span>
                                                                        </c:if>
                                                                    </label>
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
</div>

<script type="text/javascript">
    function privilegeCheck(menu) {
        var parentLi = $(menu).parent("label").parent("li");
        var childrenUl = $(parentLi).children("ul");
        $(childrenUl).toggle();
    }
</script>