<%@ page language="java" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<div class="col-sm-2 sidebar">
    <ul class="nav nav-sidebar">
        <li>
            <a href="">Nav item</a>
        </li>
        <li>
            <a href="">Nav item again</a>
        </li>
        <li>
            <a href="">One more nav</a>
        </li>
        <li>
            <a href="">Another nav item</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/role/list">岗位管理</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/user/list">用户管理</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/department/list">部门管理</a>
        </li>
    </ul>
</div>