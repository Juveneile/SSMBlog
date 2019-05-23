<%--
  Created by IntelliJ IDEA.
  User: haoxx
  Date: 2019/4/4
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/about_icon.png"/>
        关于博主
    </div>
    <div style="padding: 30px">
        ${blogger.profile}
    </div>
</div>
