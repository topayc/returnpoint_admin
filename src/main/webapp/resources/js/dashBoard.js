initView();

function initView(){
	
	$(".card.card-stats").on("click",function(){		
		$(".card.card-stats").removeClass("active");
		$(this).addClass("active");
		
		$("#pointChartArea").find(".card-title span").html($(".card.card-stats").filter(".active").find(".card-title").text());
		
		refeshChart();
	});
	
	
}

$(document).ready(function(){
	
	returnp.api.call("getDashBoard", {}, function(res){
		
		if (res.resultCode  == "100") {
			var data = res.data;
			/*data.rate = 1;
			data.real = data.pointTransRate;
			*/
			for (var prop in data){
				if (data.hasOwnProperty(prop)){			
					
					$(($("." + prop).length>0?".":"#") +prop).text( comma(data[prop]==null?0:data[prop]));					
				}
			}
			demo.initChartsPages();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
	refeshChart();
});

function refeshChart(){
	
	returnp.api.call("getDashBoardChart", {}, function(res){
		if (res.resultCode  == "100") {
			var data = res.rows;
			/*data.rate = 1;
			data.real = data.pointTransRate;
			*/
			chart.dataSets = new Object();
			for (var i=0;i<data.length;i++){	
				var row = data[i];
	/*			//console.log(row);*/
				for (var prop in row){
					
					if(chart.dataSets[prop]==undefined){
						chart.dataSets[prop] = new Array();
					}
					chart.dataSets[prop][i]= row[prop];
				}
			}
			
			
			chart.initChartsPages();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}
var chart = { 
		
	initChartsPages: function() {
	
    var redPoint = {    		
    	type: 'line',
    	label: 'Red Point',
		data: chart.dataSets.accRedPoint,
		borderColor: '#ff9797',
		borderWidth: 1,
		backgroundColor: '#ff9797',
		fill: false,
    };
    
    var greenPoint = {
		type: 'bar',
		label: 'Green Point',
		data: chart.dataSets.greenPoint,
		borderColor: "#a9ce39",
		borderWidth: 1,
		backgroundColor: '#a9ce39',
    };

    var conversionPoint = {
		type: 'bar',
		label: '전환 Point',
		data: chart.dataSets.conversionPoint,    
		borderColor: "#51cbce",
		borderWidth: 1,
		backgroundColor: '#51cbce',      
    };
    
    var pointData = {
      labels: chart.dataSets.labels,
      datasets: [redPoint,greenPoint, conversionPoint ]
    };

    var chartOptions = {
      legend: {
        display: false,
        position: 'top',
      },  
    };
    
    var lineChart = new Chart(document.getElementById("pointChart"), {
      type: 'bar',
      data: pointData,
      options: chartOptions
    });
  },
};
//콤마찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
 
//콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}
 
//값 입력시 콤마찍기
function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}