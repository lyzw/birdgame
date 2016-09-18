<%--
  Created by IntelliJ IDEA.
  User: zhouwei
  Date: 2016/8/31
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<head>
    <title>Title</title>
</head>
<body>
<%--查询框--%>
<section>
<div class="widget-body no-padding">
    <form action="" id="checkout-form" class="smart-form" novalidate="novalidate">
        <fieldset>

            <div class="row">
                <section class="col col-3">
                    <label class="input"> <i class=" fa "></i>
                        <input type="text" name="userId" id="userId" placeholder="用户号">
                    </label>

                </section>
                <section class="col col-3">
                    <label class="input"> <i class=" fa "></i>
                        <input type="text" name="openid" id="openid" placeholder="微信openid">
                    </label>
                </section>
                <footer>
                    <button type="button" class="btn btn-primary" onclick="doQuery();">查询</button>
                    <button type="reset" class="btn btn-default" >重置</button>
                </footer>

            </div>
        </fieldset>
    </form>
</div>
</section>
<%-- 数据显示 --%>
<section>
    <%--分页组件--%>
    <ul class="pagination" id="pagination1"></ul>
    <%--数据列表--%>
    <div id="dataTable"> </div>
</section>
</body>
<script lang="text/javascript">

    /*定义每页显示的记录数#### !important 非常重要 */
    var pageSize = 10;

    var defaultConifg = {
//        showIndex:false,
        /*columns格式为：columns:[{"text":"列一","dataName":"name","":fromat:funcitonName},{},{}]  ,其中format操作整条记录*/
        columns: [
            {dataName:"userId",text:" 用户号"},
            {dataName:"nickname",text:"昵称"},
            {dataName:"sex",text:"性别",format:'transferSex'},
            {dataName:"province",text:"省份"},
            {dataName:"city",text:"城市"},
            {dataName:"openid",text:"openid"},
            {dataName:"cards",text:"房卡数"},
            {dataName:"oper",text:"操作",format:'operation'},
            /*
             {dataName:"unionid",text:"unionid"},
             */

            /*
             {dataName:"country",text:"国家"},
             {dataName:"headimgurl",text:"头像"},
             {dataName:"privilege",text:"权限"},
             {dataName:"accessToken",text:"微信信令"},
             {dataName:"refreshToken",text:"微信刷新信令"},
             */
        ]
    };

    $.initPartition("pagination1");

    /**
     * 分页操作实现函数
     * @param num
     * @param type
     */
    function doOnPaginatorChange(num,type){
        doQuery(num);
    }

    function doQuery(currentPage){
        if (typeof(currentPage)== "undefined") currentPage = 1;
        $.ajax({
            type:"GET",
            url : "wxuser/list",
            data:{
                pageSize :　pageSize,
                currentPage : currentPage,
                userId : $('#userId').val(),
                openid : $('#openid').val()
            },
            dataType : "json",
            success: function(data){
                console.info(data)
                var jsonData = eval("("+data+")");
                $.loadJGridData('dataTable',"pagination1", defaultConifg,jsonData);
            }
        });

    }



    function transferSex(row){
        if(row.sex == 0) {
            return "女";
        }else{
            return "男";
        }
    }

    function operation(row){
        return '<nav><a href="<%=basePath%>wxuser/detailPage?userId='+ row.userId + '" title="查看用户的详细信息">查看</a> '
                + '| <a href="javascript:;" onclick="doRereshUser(' + row.userId  + ')" title="重新从微信获取客户的信息">刷新用户</a>'
                + '| <a href="<%=basePath%>wxuser/manageCards?userId='+ row.userId + '" title="管理用户的房卡">房卡管理</a>'
                + '| <a href="#" onclick="registerHx(\'' + row.userId  + '\')" title="给用户注册环信账号">注册环信账号</a></nav>'
    }

    function doRereshUser(userId) {

    }
    function registerHx(userId) {
        $.ajax({
            type:"GET",
            url:'wxuser/refresh?userId='+ userId ,
            success:function (data) {
                console.info(data)
                var jsonData = eval("("+data+")");
                if(jsonData.code == 200){
                    alert("注册环信账号成功");
                }else{
                    alert("注册环信账号失败，失败原因为："+ jsonData.message)
                }
            }
        });


    }
</script>
</html>
