<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<style type="text/css">
    #privilege_tree_menu ul {
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

    #privilege_tree_menu li {
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
        <ul id="privilege_tree_menu" class="nav nav-sidebar list-group">
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

</div>

<script type="text/javascript">
    function privilegeCheck(menu) {
        var parentLi = $(menu).parent("label").parent("li");
        var childrenUl = $(parentLi).children("ul");
        $(childrenUl).toggle();
    }
</script>