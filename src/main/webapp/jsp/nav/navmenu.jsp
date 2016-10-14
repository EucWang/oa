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
        padding-right: 0px;
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
        padding-right: 0px;
        align-content: center;
    }
</style>
<div class="col-sm-2 sidebar">

    <div class="btn-group-vertical">
        <ul id="privilege_tree" class="nav nav-sidebar list-group">
            <c:forEach items="${privileges}" var="privilege" varStatus="status">
                <c:if test="${privilege.isMenu == 1}">
                    <li class="list-group-item">
                        <label>
                            <a href="#" id="privilegeId_${privilege.id}"
                               onclick="privilegeCheck(this)">${privilege.name}</a>
                                <span class="caret"></span>
                        </label>

                        <ul>
                            <c:forEach items="${privileges}" var="privilege2" varStatus="status">
                                <c:if test="${privilege2.isMenu == 2 && privilege2.parent.id == privilege.id}">
                                    <li class="list-group-item">
                                        <label>
                                            <a
                                                    <c:if test="${privilege2.url != null}">href="${pageContext.request.contextPath}${privilege2.url}"</c:if>
                                                    <c:if test="${privilege2.url == null}">href="#"</c:if>
                                                    id="privilegeId_${privilege2.id}">${privilege2.name}</a>
                                        </label>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <%--<div class="btn-group-vertical">--%>
    <%--<ul id="privilege_tree" class="nav nav-sidebar list-group">--%>
    <%--<c:forEach items="${privileges}" var="privilege" varStatus="status">--%>
    <%--<li class="list-group-item">--%>
    <%--<label>--%>
    <%--<a <c:if test="${privilege.url != null}">href="${pageContext.request.contextPath}${privilege.url}"</c:if>--%>
    <%--<c:if test="${privilege.url == null}">href="#"</c:if>--%>
    <%--id="privilegeId_${privilege.id}"--%>
    <%--onclick="privilegeCheck(this)">${privilege.name}</a>--%>
    <%--<c:if test="${privilege.children != null && privilege.children.size()>0}">--%>
    <%--<span class="caret"></span>--%>
    <%--</c:if>--%>
    <%--</label>--%>
    <%--<c:if test="${privilege.children != null}">--%>
    <%--<ul>--%>
    <%--<c:forEach items="${privilege.children}" var="privilege1" varStatus="status">--%>
    <%--<li>--%>
    <%--<label> <a <c:if test="${privilege1.url != null}">href="${pageContext.request.contextPath}${privilege1.url}"</c:if>--%>
    <%--<c:if test="${privilege1.url == null}">href="#"</c:if>--%>
    <%--id="privilegeId_${privilege1.id}"--%>
    <%--onclick="privilegeCheck(this)">${privilege1.name}</a>--%>
    <%--<c:if test="${privilege1.children != null && privilege1.children.size()>0}">--%>
    <%--<span class="caret"></span>--%>
    <%--</c:if>--%>
    <%--</label>--%>
    <%--<c:if test="${privilege1.children != null}">--%>
    <%--<ul class="list-group">--%>
    <%--<c:forEach items="${privilege1.children}" var="privilege2"--%>
    <%--varStatus="status">--%>
    <%--<li class="list-group-item">--%>

    <%--<label>   <a <c:if test="${privilege2.url != null}">href="${pageContext.request.contextPath}${privilege2.url}"</c:if>--%>
    <%--<c:if test="${privilege2.url == null}">href="#"</c:if>--%>
    <%--id="privilegeId_${privilege2.id}"--%>
    <%--onclick="privilegeCheck(this)">${privilege2.name}</a>--%>
    <%--<c:if test="${privilege2.children != null && privilege2.children.size()>0}">--%>
    <%--<span class="caret"></span>--%>
    <%--</c:if>--%>
    <%--</label>--%>
    <%--<c:if test="${privilege2.children != null}">--%>
    <%--<ul class="list-group">--%>
    <%--<c:forEach items="${privilege2.children}"--%>
    <%--var="privilege3" varStatus="status">--%>
    <%--<li class="list-group-item">--%>
    <%--<label>--%>
    <%--<a <c:if test="${privilege3.url != null}">href="${pageContext.request.contextPath}${privilege3.url}"</c:if>--%>
    <%--<c:if test="${privilege3.url == null}">href="#"</c:if>--%>
    <%--id="privilegeId_${privilege3.id}"--%>
    <%--onclick="privilegeCheck(this)">${privilege3.name}</a>--%>
    <%--<c:if test="${privilege3.children != null && privilege3.children.size()>0}">--%>
    <%--<span class="caret"></span>--%>
    <%--</c:if>--%>
    <%--</label>--%>
    <%--</li>--%>
    <%--</c:forEach>--%>
    <%--</ul>--%>
    <%--</c:if>--%>
    <%--</li>--%>
    <%--</c:forEach>--%>
    <%--</ul>--%>
    <%--</c:if>--%>
    <%--</li>--%>
    <%--</c:forEach>--%>
    <%--</ul>--%>
    <%--</c:if>--%>
    <%--</li>--%>
    <%--</c:forEach>--%>
    <%--</ul>--%>
    <%--</div>--%>
</div>

<script type="text/javascript">
    function privilegeCheck(menu) {
        var parentLi = $(menu).parent("label").parent("li");
        var childrenUl = $(parentLi).children("ul");
        $(childrenUl).toggle();
    }
</script>