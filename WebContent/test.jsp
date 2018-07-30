<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="./css/jquery-ui-1.8.19.custom.css">
<link rel="stylesheet" type="text/css" href="./css/ui.jqgrid.css">
<script type="text/javascript" src="./scripts/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./scripts/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="./scripts/grid.locale-cn.js"></script>
<title>grid首頁</title>
<script type="text/javascript">

$("#list").jqGrid({
	 url:'getdata.php?act=initial',
	 datatype: 'xml',
	 mtype: 'GET',
	 colNames:['ID Number','Last Sales','Name', 'Stock', 'Ship via','Notes'],

	 colModel:[
	  {name:'id',index:'id', width:90, sorttype:"int", editable: true, xmlmap: "id" },
	  {name:'sdate',index:'sdate',width:90, editable:true, sorttype:"date", xmlmap: "sdate" },
	  {name:'name',index:'name', width:150,editable: true, editoptions:{size:"20",maxlength:"30"}, xmlmap: "name" },
	  {name:'stock',index:'stock',width:60,editable:true,edittype:"checkbox",editoptions:{value:"Yes:No"}, xmlmap: "stock" },
	  {name:'ship',index:'ship',width:90,editable:true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}, xmlmap: "ship" },
	  {name:'note',index:'note',width:200,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"10"}, xmlmap: "note"}],

	  pager: '#list_pager',
	  autowidth:true,
	  height:'100%',
	  rownumbers: true,
	  rownumWidth:30,
	  toolbar: [true, "top"],
	  toppager: true,       
	  pgbuttons: true,
	  pginput: true,
	  rowNum: 15,
	  rowList: [10, 15, 20, 25, 30, 50, 100],
	  rowNum:15,    
	  viewrecords: true,
	  loadonce:true,
	  gridview: true, 
	  editurl : 'setdata.php?q=dummy',
	  caption: '導覽員列表'
	  
	  

		  $("#list").jqGrid('navGrid','#list_pager',{
		  cloneToTop: true, edit:false, add:true, del:false, view:true, addtext: "新增資料", addtitle: "新增資料",viewtext: "檢視資料", viewtitle: "檢視資料",searchtext: "搜尋...", searchtitle: "搜尋...", refreshtext: "更新", refreshtitle: "更新"}, 
		  {}, /*default settings for edit*/ 
		  {
		  url:'setdata.php?act=add',
		  closeAfterAdd:true,
		  closeOnEscape:true,
		  afterComplete: function (response, postdata, formid) {
		  if ($("#list").getGridParam("datatype") === "local") {
		  $("#list").setGridParam({ datatype: 'xml' });
		  }
		  $("#list").trigger("reloadGrid");},
		  beforeShowForm: function (form) {
		  }
		  }, /*default settings for add*/ 
		  {},  /*default settings for delete*/ 
		  {multipleSearch:true,closeOnEscape:true,closeAfterSearch:true,multipleGroup:true, showQuery: true}, 
		  /*default settings for search*/ 
		  {
		  closeOnEscape:true,
		  viewPagerButtons:false, 
		  beforeShowForm: function (form) {
		  $('#viewmodlist').css('width', '450px');}
		  } /*default settings for view*/ 
		  );	  

</script>
</head>

<body>
	<table id="list"></table>
	<div id="list_pager"></div>
</body>
</html>