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
            <td class="titleTd" colspan="2" style="text-align: center;background-color: yellow">通知[<c:out
                    value="${notice.id}"></c:out>-<c:out
                    value="${notice.title}"></c:out>]详情
            </td>
        </tr>

        <tr>
            <td class="titleTd">id</td>
            <td class="contentTd"><c:out value="${notice.id}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">类型</td>
            <td class="contentTd"><c:out value="${notice.type}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">标题</td>
            <td class="contentTd"><c:out value="${notice.title}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">内容</td>
            <td class="contentTd"><c:out value="${notice.content}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">开始时间</td>
            <td class="contentTd"><c:out value="${notice.startTime}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">结束时间</td>
            <td class="contentTd"><c:out value="${notice.endTime}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">时间间隔</td>
            <td class="contentTd"><c:out value="${notice.intervals}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">状态</td>
            <td class="contentTd"><c:out value="${notice.status}"></c:out></td>
        </tr>
        <tr>
            <td class="titleTd">备注</td>
            <td class="contentTd"><c:out value="${notice.remark}"></c:out></td>
        </tr>

    </table>
</section>

</body>
</html>
