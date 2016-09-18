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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        line-height: 2em;
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
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>
<head>
    <title>Title</title>
</head>
<body onload="doOnload()">
<section>
    <form id="noticeUpdForm" method="post">
        <input type="hidden" id="id" name="id" value='<c:out value="${notice.id}"/>'>
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
            <td class="contentTd">
                <select type="text" name="type" id="type"  value="<c:out value="${notice.type}"/>">
                    <option value="" >全部</option>
                    <option value="1" >1</option>
                    <option value="2" >2</option>
                </select>
                <c:out value="${notice.type}"/>
            </td>
        </tr>
        <tr>
            <td class="titleTd">标题</td>
            <td class="contentTd"><input type="text" name="title" id="title" value='<c:out value="${notice.title}"></c:out>' /></td>
        </tr>
        <tr>
            <td class="titleTd">内容</td>
            <td class="contentTd"><textarea name="content"  style="width: 100% ;"><c:out value="${notice.content}"></c:out></textarea></td>
        </tr>
        <tr>
            <td class="titleTd">开始时间</td>
            <td class="contentTd">
                <input type="text" class="Wdate"  
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen' })"
                       name="startTime" id="startTime" value="<fmt:formatDate value="${notice.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
            </td>
        </tr>
        <tr>
            <td class="titleTd">结束时间</td>
            <td class="contentTd">
                <input type="text" class="Wdate"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen' })"
                       name="endTime" id="endTime" value="<fmt:formatDate value="${notice.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
            </td>
        </tr>
        <tr>
            <td class="titleTd">时间间隔(秒)</td>
            <td class="contentTd"><input type="number" min="1" name="intervals" id="intervals" value="<c:out value="${notice.intervals}"></c:out>" /></td>
        </tr>
        <tr>
            <td class="titleTd">状态</td>
            <td class="contentTd"><input type="text" name="status" id="status" value="<c:out value="${notice.status}"></c:out>"/></td>
        </tr>
        <tr>
            <td class="titleTd">备注</td>
            <td class="contentTd"><input type="text" name="remark" id="remark" value="<c:out value="${notice.remark}"></c:out>"/></td>
        </tr>

    </table>
        <br>
        <div align="center">
            <button type="button" class="btn btn-primary" onclick="doOnSubmit()" >确认</button>
            <button type="reset" class="btn btn-primary" style="text-align: center;" >重置</button></div>
        <div align="center"> </div>
    </form>
</section>

</body>
<script type="text/javascript" >
    function doOnload() {
        alert('<c:out value="${notice.type}"/>');
        $("select option[value='<c:out value="${notice.type}" />']").attr("selected", "selected");
    }

    function doOnSubmit() {
        alert(1);
        $.ajax({
            type: "POST",
            url: "<%=basePath%>" + "notice/update",
            data:$('#noticeUpdForm').serialize(),// 你的formid
            async: false,
            success :function (data) {
                var jsonData = eval("("+data+")");
                if(jsonData.code == 200){
                    alert("修改成功");
                    window.location ="<%=basePath%>#<%=basePath%>notice/init";
                }else{
                    alert("修改shibai ");
                }
            },
            error: function(request) {
                alert("Connection error");
            },
        });
    }
</script>
</html>
