/**
 * Created by ht-liushici on 2016/4/9.
 */
/**
 * K线图模版
 * @param update_time 更新时间
 * @param xAxisData x轴标记
 * @param data 数据
 * @returns {{title: {text: string, subtext: *}, tooltip: {trigger: string}, toolbox: {show: boolean, feature: {mark: {show: boolean}, dataView: {show: boolean, readOnly: boolean}, magicType: {show: boolean, type: string[]}, restore: {show: boolean}, saveAsImage: {show: boolean}}}, calculable: boolean, xAxis: *[], yAxis: *[], series: *[]}}
 */
function k_line_template(title,series_title,update_time,xAxisData,data,yAxis_unit){
    var option = {
        title : {
            text: title,
            subtext: update_time
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: false},
                dataView : {show: false, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
		grid:{
			y:70
		},
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : xAxisData
            }
        ],
        yAxis : [
            {
                name : yAxis_unit,
                type : 'value'
            }
        ],
        series : [
            {
                name:series_title,
                type:'line',
                symbol:'none',//圆点，none为不显示,不设置默认为显示
                smooth:true,
                itemStyle: {normal: {}},//normal: {areaStyle: {type: 'default'}}//显示阴影效果
                data:data
            }
        ]
    };
    return option;
}

/**
 * 地图模版
 * @param title
 * @param update_time
 * @param data
 * @returns {{title: {text: *, subtext: *, x: string}, tooltip: {trigger: string}, dataRange: {min: number, max: number, x: string, y: string, text: string[], calculable: boolean}, toolbox: {show: boolean, orient: string, x: string, y: string, feature: {mark: {show: boolean}, dataView: {show: boolean, readOnly: boolean}, restore: {show: boolean}, saveAsImage: {show: boolean}}}, roamController: {show: boolean, x: string, mapTypeControl: {china: boolean}}, series: *[]}}
 */
function map_template(title,update_time,data,max,series_name) {
    var option = {
        title: {
            text: title,
            subtext: update_time,
            x: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
//                    legend: {
//                        orient: 'vertical',
//                        x:'left',
//                        data:['pc','android','ios']
//                    },
        dataRange: {
            min: 0,
            max: max,
            x: 'left',
            y: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            x: 'right',
            y: 'center',
            feature: {
                mark: {show: false},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        roamController: {
            show: true,
            x: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series: [
            {
                name: series_name,
                type: 'map',
                mapType: 'china',
                roam: false,
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data
            }
        ]
    };
    return option;
}

/**
* 饼图模板
*/
function pie_template(title,update_time,legend_data,data){
	var option = {
		title : {
			text: title,
			subtext: update_time,
			x:'center'
		},
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient : 'vertical',
			x : 'left',
			data:legend_data
		},
		toolbox: {
			show : true,
			feature : {
				mark : {show: false},
				dataView : {show: true, readOnly: false},
				magicType : {
					show: true,
					type: ['pie', 'funnel'],
					option: {
						funnel: {
							x: '25%',
							width: '50%',
							funnelAlign: 'left',
							max: 1548
						}
					}
				},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		series : [
			{
				//name:'男女占比',
				type:'pie',
				radius : '45%',
				center: ['50%', '60%'],
				data:data,
                itemStyle:{
                    normal:{
                        label:{
                            show: true,
                            formatter: '{b}:{c}({d}%)'
                        },
                        labelLine :{show:true}
                    }
                }
            }
		]
	};
	return option;
}


/**
* 柱状图模板
*/
function bar_template(title,update_time,yAxis_data,series_name,series_data,yAxis_unit,xAxis_unit){
	var option = {
		title : {
			text: title,
			subtext: update_time
		},
		tooltip : {
			trigger: 'axis'
		},
		grid:{
			x:450
		},
	//                    legend: {
	//                        data:['2011年', '2012年']
	//                    },
		toolbox: {
			show : true,
			feature : {
				mark : {show: false},
				dataView : {show: true, readOnly: false},
				magicType: {show: true, type: ['line', 'bar']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		xAxis : [
			{
				name : xAxis_unit,
				type : 'value',
				boundaryGap : [0, 0.01]
			}
		],
		yAxis : [
			{
				name : yAxis_unit,
				type : 'category',
				data : yAxis_data
			}
		],
		series : [
			{
				name:series_name,
				type:'bar',
				data:series_data
			}
		]
	};
	return option;
}