<%--
  Created by IntelliJ IDEA.
  User: zhouwei
  Date: 2016/9/5
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
    table {
        font-size: 1em;
        width: 80%;
    }

    table td {
        border-style: solid;
        border-width: 1px;
        border-color: #666666;
        line-height: 1.8em;
    }

    .titleTd {
        width: 200px;
        border-color: #666666;
        background-color: #dedede;
        text-align: right;
        padding: 0.3em;
        margin: 0.3em;
        font-weight: 600;
        font-family: 微软雅黑;
    }

    .contentTd {
        word-break: break-all;
        padding: 0.3em;
        margin: 0.3em
    }
</style>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <table>
        <tr>
            <td class="titleTd" colspan="2" style="text-align: center;background-color: yellow">玩家[<c:out value="${user.userId}"></c:out>]账户详细信息</td>
        </tr>
        <tr>
            <td class="titleTd">用户id</td>
            <td class="contentTd"><c:out value="${user.userId}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">昵称</td>
            <td class="contentTd"><c:out value="${user.nickname}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">openid</td>
            <td class="contentTd"><c:out value="${user.openid}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">性别</td>
            <td class="contentTd">
                <c:choose>
                    <c:when test="${user.sex == null}">未知性别</c:when>
                    <c:when test="${user.sex == 1}">男</c:when>
                    <c:when test="${user.sex == 0}">女</c:when>
                    <c:otherwise>未知性别</c:otherwise>
                </c:choose>
        </tr>
        <tr>
            <td class="titleTd">国家</td>
            <td class="contentTd"><c:out value="${user.country}"></c:out></td>
        </tr>
        </tr>
        <tr>
        <td class="titleTd">省份</td>
        <td class="contentTd"><c:out value="${user.province}"></c:out></td>
        </tr>
        <tr>
        <td class="titleTd">城市</td>
        <td class="contentTd"><c:out value="${user.city}"></c:out></td>
        <tr>
            <td class="titleTd">头像地址</td>
            <td class="contentTd"><c:out value="${user.headimgurl}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">权限</td>
            <td class="contentTd"><c:out value="${user.privilege}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">用户id</td>
            <td class="contentTd"><c:out value="${user.unionid}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">访问信令</td>
            <td class="contentTd"><c:out value="${user.accessToken}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">刷新信令</td>
            <td class="contentTd"><c:out value="${user.refreshToken}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">房卡数</td>
            <td class="contentTd"><c:out value="${user.cards}"></c:out></td>
        </tr>

    </table>
</section>

</body>
</html>
