<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//checks to see if the users have the attributes required for writing files and if they do
		//they upload file and files gets uploaded in the uploadFile servlet
		String ans = (String) request.getAttribute("answer"); 
		String corAns = "this is a test";
		if(ans.equals(corAns)) {
	%>
			Upload file now.
			<form method="post" action="/SDM_work/UploadFile" enctype="multipart/form-data">
				<input type="file" name="file"/>
				<input type="submit"/>
			</form>
	<%
		}else {
	%>
			<p>You do not have permission to upload files.
	<%
		}
	%>
</body>
</html>