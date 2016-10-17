<%--
  Created by IntelliJ IDEA.
  User: zhouwei
  Date: 2016/10/17
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到转转游戏运营管理平台</title>
</head>
<body>
<div class="login_frame"><h3>登录</h3>
    <div class="login_err_panel" style="display:none;" id="err"></div>
    <form class="login_form" id="loginForm">
        <div class="login_input_panel" id="js_mainContent">
            <div class="login_input">
                <i class="icon_login un"> </i>
                <input type="text" placeholder="用户名" id="account" name="account">
            </div>
            <div class="login_input">
                <input type="password" placeholder="密码" id="pwd" name="password"></div>
        </div>
        <div class="verifycode" style="display:none;" id="verifyDiv">
            <span class="frm_input_box">
                <input class="frm_input" type="text" id="verify" name="verify">
            </span>
            <img id="verifyImg" src=""/>
                <a href="javascript:;" id="verifyChange">换一张</a>
        </div>

    </form>
</body>
</html>
