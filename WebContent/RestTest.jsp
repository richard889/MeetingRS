<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./scripts/jquery-3.3.1.js"></script>
<script>
$(document).ready(function() {
	
	$("#btnRest").click(function(){			
			var memberid = $("#memberID").val();
			var endpoint = 'http://localhost:8080/MeetingRS/api/member/getmemberqry/2/json';
			$.ajax(
				{
					url:'http://localhost:8080/MeetingRS/api/member/getmemberqry/2/json',
					type:'GET',
					dataType:'json',
					success:function(result,status,xhr){
						alert(result.name);
					},
					error:function(result,status,xhr){
						alert(result);
					}
				}
			);
		}
	);
	
});

	
</script>
</head>
<body>
<input type="text" id="memberID" name="memberID">人員編號</input>
<input type="button" id="btnRest" name="btnRest"/>


</body>
</html>