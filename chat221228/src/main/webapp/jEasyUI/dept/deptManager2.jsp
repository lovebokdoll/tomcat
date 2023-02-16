<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리2</title>
<%@include file="../../common/easyUI_common.jsp"%>
</head>
<body>
<script>
  window.addEventListener("load", function(event) {
	  $('#dg').datagrid({
			title : "부서관리",
			url : 'datagrid_data.json',
			columns : [ [ {
				field : 'code',
				title : 'Code',
				width : 100
			}, {
				field : 'name',
				title : 'Name',
				width : 100
			}, {
				field : 'price',
				title : 'Price',
				width : 100,
				align : 'right'
			} ] ]
		})
  });
</script>
	<table id="dg"></table>
	   <div id="toolbar">

</body>
</html>