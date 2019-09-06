var returnpCommon = {
		appInfo : {
			gridPageSize : 50
		}
}

var keyValueNode = {
		"1" : "member",
		"2" : "recommender",
		"3" : "branch",
		"4" : "agency",
		"5" : "affiliate",
		"6" : "saleManager",
		"7" : "soleDist"
}

function gridToExcel(grid, fileName){
	var rows = $(grid).datagrid('getRows');
	for (var i = 0; i <rows.length; i++){
		var row = rows[i];
		changeValue(row);

	}
	$(grid).datagrid('toExcel',fileName);
}



function printGrid(grid){
	var rows = $(grid).datagrid('getRows');
	for (var i = 0; i <rows.length; i++){
		var row = rows[i];
		changeValue(row);
	}
	$(grid).datagrid('print','DataGrid');
}

function changeValue(row){
	if (row['createTime']) {
		row['createTime'] = dateFormatter(row['createTime']);
	}
	
	if (row['updateTime']) {
		row['updateTime'] = dateFormatter(row['updateTime']);	
	}

	if (row['withdrawalStatus']) {
		row['withdrawalStatus'] = withdrawalStatusFormatter(row['withdrawalStatus']);
	}
}