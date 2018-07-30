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
		<s:fielderror />

		<s:form action="/member/addvalid.action">
			<s:textfield label="編號" name="member.memberID"></s:textfield>
			<s:textfield label="名稱" name="member.name"></s:textfield>
			<s:textfield label="帳號" name="member.account"></s:textfield>
			<s:password label="密碼" name="member.password"></s:password>
			<s:textfield label="電話" name="member.phone"></s:textfield>
			<s:textfield label="地址" name="member.address"></s:textfield>
			<s:textfield label="Email" name="member.email"></s:textfield>
			<s:submit label="確定" value="確定"></s:submit>
		</s:form>
	</fieldset>

</body>
</html>