(function($){
    var defaultConifg = {
        showIndex:true,
       /*columns格式为：columns:[{"text":"列一","dataName":"name","fun":funcitonName},{},{}]*/
        columns:[]
    };


    $.initPartition = function(id){
        $('#' + id).jqPaginator({
            pageSize : pageSize,
            totalCounts : 1,
            currentPage:1,
            onPageChange: function (num,type) {
                doOnPaginatorChange(num,type)
            }
        })
    }
    /**
     *
     * @param target
     * @param config
     * @param data
     */
    $.loadJGridData = function(target,patitionid,config,data){
        var setting  = $.extend({},defaultConifg,config);
        /*定义表*/
        var gridHtmlBegin = '<table class="table table-bordered table-striped">';
        var gridHtmlEnd = '</table>';

        /*设置表头*/
        var headHtmlBegin = '<thead><tr>';
        var headHtmlEnd = '</tr></head>'
        /*定义表体*/
        var tbBodyHtemlBegin = '<tbody>';
        var tbBodyHtemlEnd = '</tbody>';
        /*定义分页*/
        var patitionHtml = '<ul class="pagination" id="pagination1"></ul>';
        if (setting.columns.length == 0) {
            console.log("没有定义列信息");
            return;
        }

        /*处理列头html*/
        var tbHeadHtml = headHtmlBegin;
        if (setting.showIndex){
            tbHeadHtml += '<th>序号</th>';
        }
        $(setting.columns).each(function(index,value){
            if (typeof (value.css) != "undefined"){
                tbHeadHtml += '<th class="' + value.css + '">' + value.text + '</th>'
            }else {
                tbHeadHtml += '<th>' + value.text + '</th>'
            }
        });
        tbHeadHtml += headHtmlEnd;

        /*处理数据html*/
        var datalist = data.list;
        var tbBodyHtml = tbBodyHtemlBegin;
        var indexNum = 0;
        var pageinfo = data.page;
        $(data.list).each(function(dataIndex,dataValue){
            indexNum += 1;
            tbBodyHtml += '<tr>';
            if (setting.showIndex){
                tbBodyHtml += '<td><b>'+ (parseInt((pageinfo.currentPage-1) * pageinfo.pageSize) + indexNum ) + '</b></td>';
            }
            $(setting.columns).each(function(cfgIndex,cfgValue){
                var value ;
                if(typeof(cfgValue.format) != "undefined"){
                    value = eval(cfgValue.format + '(dataValue)');
                }else{
                    value = eval('dataValue.'+ cfgValue.dataName);
                }
                value = nullToBlank(value);
                var subvalue = value;
                if(typeof (cfgValue.size != "undefined") && value.toString().length > cfgValue.size  ){
                    subvalue = value.toString().substr(0,cfgValue.size-2) +"...";
                }
                if (typeof (cfgValue.css) != "undefined"){
                    tbBodyHtml += '<td class="' + cfgValue.css + '" title="'+ value+'">' + subvalue + '</td>'
                }else{
                    tbBodyHtml += '<td>'+ subvalue + '</td>';
                }
            });
            tbBodyHtml += '</tr>';
        });
        tbBodyHtml += tbBodyHtemlEnd;

        /*组装最后的html*/
        var tableHtml = gridHtmlBegin + tbHeadHtml + tbBodyHtml + gridHtmlEnd;

        $('#' + target).html(tableHtml)  ;
        var pageinfo = data.page;
        $('#'+patitionid).jqPaginator('option', {
            pageSize : pageSize,
            totalCounts : pageinfo.totalCount,
            currentPage:pageinfo.currentPage,
        });
    }

    function nullToBlank(value){
        if(typeof(value) == "undefined" || value == null){
            return  "";
        }else{
            return value;
        }
    }

     $.reloadData = function(data){

    }
})(jQuery);