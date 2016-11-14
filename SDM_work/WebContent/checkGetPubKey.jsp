<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="submitCheck.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="sddm" method="POST" action="/SDM_work/GetPublicKey">
		<input type="radio" name="userid" value="55" checked>userid55
		<select id="topicSelection" name="topicSelection" onchange="dropdownChange(this.value);">
			<option value="none">Select a Topic</option>
			<option value="Air">Air</option>
			<option value="Berry">Berry</option>
			<option value="Cooking">Cooking</option>
			<option value = "Garden">Garden</option>
		</select>
	</form>
</body>
</html>