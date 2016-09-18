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
<div id="content">
<article>
<div class="widget-body no-padding">
    <form action="" id="checkout-form" class="smart-form" novalidate="novalidate">
        <fieldset>

            <div class="row">
                <section class="col col-3">
                    <label class="label">id</label>
                    <label class="input"> <i class="icon-append fa "></i>
                        <input type="text" name="id" id="id" placeholder="id"  >
                    </label>
                </section>
                <section class="col col-3">
                    <label class="label" >类型</label>
                    <label class="select" style="line-height: 50px;"> <i class=" fa "></i>
                        <select  name="type" id="type" >
                            <option value="">全部类型</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                    </label>
                </section>
                <section class="col col-3">
                    <label class="label">开始时间</label>
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="startTime" id="startTime" placeholder="开始时间" class="hasDatepicker">
                    </label>
                </section>
                <section class="col col-3">
                    <label class="label">结束时间</label>
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="endTime" id="endTime" placeholder="结束时间" class="hasDatepicker">
                    </label>
                </section>


            </div>
            <div class="row">
                <footer>
                    <button type="button" class="btn btn-primary" onclick="doQuery();">查询</button>
                    <button type="reset" class="btn btn-default" >重置</button>
                </footer>
                </div>
        </fieldset>
    </form>
</div>
</article>
<%-- 数据显示 --%>
<section>
    <%--分页组件--%>
    <ul class="pagination" id="pagination1"></ul>
    <%--数据列表--%>
    <div id="dataTable"> </div>
</section>
</div>
</body>
<style>

    table{

        　　table-layout: fixed;

    }

    td{

        　　white-space: nowrap;
        　　overflow: hidden;
        　　text-overflow: ellipsis;

    }
    .td_width{ width: 20%}
</style>
<script lang="text/javascript">

    /*定义每页显示的记录数#### !important 非常重要 */
    var pageSize = 10;

    var defaultConifg = {
//        showIndex:false,
        /*columns格式为：columns:[{"text":"列一","dataName":"name","":fromat:funcitonName},{},{}]  ,其中format操作整条记录*/
        columns: [
            {dataName:"id       ",text:"id"},
            {dataName:"type     ",text:"类型"},
            {dataName:"title    ",text:"标题"},
            {dataName:"content  ",text:"内容",size:20},
            {dataName:"startTime",text:"开始时间" ,format:"getStartTime"},
            {dataName:"endTime  ",text:"结束时间" ,format:"getEndTime"},
            {dataName:"intervals ",text:"播放间隔(秒)"},
            {dataName:"status   ",text:"状态"},
            {dataName:"remark   ",text:"备注"},
            {dataName:"remark   ",text:"操作",format:'noticeOperation'}
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
            type:"POST",
            url : "notice/list",
            data:{
                pageSize :　pageSize,
                currentPage : currentPage,
                id : $("#id").val(),
                type : $("#type").val(),
                startDateTime : $("#startTime").val(),
                endDateTime : $("#endTime").val(),

            },
            dataType : "json",
            success: function(data){
                console.info(data)
                var jsonData = eval("("+data+")");
                $.loadJGridData('dataTable',"pagination1", defaultConifg,jsonData);
            }
        });

    }

    function noticeOperation(row){
        return '<nav>' +
                '<a href="<%=basePath%>notice/detailPage?id='+ row.id + '" title="查看消息的详细信息">查看</a> ' +
                '| <a href="<%=basePath%>notice/modify?id='+ row.id + '" title="编辑当前通知详情">编辑</a>'+
                '| <a href="javascript:;" onclick="doRereshUser(' + row.id  + ')" title="删除当前通知消息">删除</a>'+
                '</nav>'

    }

    function getStartTime (row) {
        return  new Date(row.startTime).toLocaleString().replace(/:\d{1,2}$/,' ');
    }

    function getEndTime(row) {
        return  new Date(row.endTime).toLocaleString().replace(/:\d{1,2}$/,' ');
    }

</script>
</html>
