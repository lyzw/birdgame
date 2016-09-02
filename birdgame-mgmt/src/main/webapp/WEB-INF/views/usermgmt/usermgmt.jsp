<%--
  Created by IntelliJ IDEA.
  User: zhouwei
  Date: 2016/8/31
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--查询框--%>
<div class="widget-body no-padding"  >
    <form action="" id="checkout-form" class="smart-form" novalidate="novalidate" >
        <fieldset >

            <div class="row">
                <section class="col col-6" >
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="startdate" id="startdate" placeholder="统计开始时间">
                    </label>

                </section>

                <section  class="col col-6" >
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="finishdate" id="finishdate" placeholder="统计结束时间">
                    </label>
                </section>


            </div>
            <%--<div class="row">
                <div style="font-size: 8px;color:red;">请注意： </div>
                <div style="font-size: 8px;color:red;">
                    <li>未输入日期默认展示当前日期前的一个月内的统计数据</li>
                    <li>为防止展示数据过多，查询区间最大为一个月</li>
                    <li>截止日期最大为当前日期；</li>
                </div>
            </div>--%>
            <footer>
                <button type="button" class="btn btn-primary" onclick="doQuery();">查询</button>
                <button type="button" class="btn btn-default" onclick="resetForm();">重置</button>
            </footer>
        </fieldset>
    </form>
</div>

<%-- 数据显示 --%>
<div>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th> 用户id</th>
                <th> 用户名</th>
                <th> 昵称</th>
                <th> 状态</th>
                <th> 用户类型</th>
            </tr>
        </thead>
        <%c: %>
    </table>
</div>
</body>
</html>
