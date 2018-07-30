<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<fieldset>
		<s:form action="/member/memberqry.action">
			<s:textfield label="人員帳號" name="account"></s:textfield>
			<s:textfield label="人員姓名" name="name"></s:textfield>
			<s:submit label="查詢" value="查詢"></s:submit>
		</s:form>
		<s:form action="/member/memberadd.action">
			<s:submit label="新增" value="新增"></s:submit>
		</s:form>		
	
	</fieldset>
	<table border="1">
		<tr>
			<td>人員編號</td>
			<td>人員帳號</td>
			<td>人員姓名</td>
			<td>電話</td>
			<td>地址</td>
			<td>Email</td>
		</tr>
		
		<s:iterator value="list">
		<tr>
			<td><s:property value="memberID" /></td>
			<td><s:property value="account" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="phone" /></td>
			<td><s:property value="address" /></td>
			<td><s:property value="email" /></td>
		</tr>			
		</s:iterator>		
				
	</table>

</body>
</html>