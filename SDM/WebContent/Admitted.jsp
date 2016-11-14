<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//get the userid and filepath and print it on screen
		ArrayList<String[]> fis = (ArrayList<String[]>)request.getAttribute("fileLocs");
	
		for (int i= 0; i< fis.size(); i++) {
			String[] dat = fis.get(i);
	%>
	 		<a href=<%=dat[1]%>><%=dat[0]%></a>
	<%
		}
			
	%>
</body>
</html>