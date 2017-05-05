<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div id="container" style="min-width:800px;height:400px"></div>



<script type="text/javascript">
	//汉化图表菜单
	Highcharts.setOptions({
    lang: {
        contextButtonTitle: "图表菜单",
        printChart: "打印图片",
        downloadJPEG: "下载JPEG 图片",
        downloadPDF: "下载PDF文档",
        downloadPNG: "下载PNG 图片",
        downloadSVG: "下载SVG 矢量图",
        exportButtonTitle: "导出图片"
    }
});
  $('#container').highcharts({
        chart: {
            type: 'spline',
            events : {
						load : st// 定时器
					},
        },
        title: {
            text: 'LOFTER 发帖数据分析'
        },
        subtitle: {
            text: 'Source: WorldClimate.com'
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Temperature'
            },
            labels: {
                formatter: function() {
                    return this.value +'°'
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
        series: [{
            name: '文字',
            marker: {
                symbol: 'square'
            },
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, {
                y: 26.5,
                marker: {
                    symbol: 'square'
                }
            }, 23.3, 18.3, 13.9, 9.6]

        }, {
            name: '图文',
            marker: {
                symbol: 'diamond'
            },
            data: [{
                y: 3.9,
                marker: {
                    symbol: 'diamond'
                }
            }, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
 
});

//10秒钟刷新一次数据
		function st() {
			setInterval("getData()", 10000);
		}
		//动态更新图表数据
		function getData() {
			
			var categories = [];
			$.ajax({
				  type: "post",
				  url: "topicanalysis.action?op=getData",			 
				  dataType: "json",
				  success : function(data){
					alter(data);
				
				//	var d = [];
					//  $(data).each(function(n,item){
					//	  d.push(item.data);
					//	  categories.push(item.categories);
					//  })
					 //chart.series[0].setData(d);
					// chart.xAxis[0].setCategories(categories);
				  }
		  });
		}
</script>

