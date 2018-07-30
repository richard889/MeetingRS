<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="scripts/jquery-3.3.1.js"></script>
</head>
<body>

	<fieldset>		
		<s:form action="loginvalid" >
			<s:textfield label="使用者帳號" name="login.account" value="m002"></s:textfield>
			<s:textfield label="使用者密碼" name="login.password" value="1111"></s:textfield>
			<s:submit label="登入" value="登入" id="btnvalid"></s:submit>
		</s:form>
	
	</fieldset>
	<br /> 
	<s:actionerror/>


</body>
</html>