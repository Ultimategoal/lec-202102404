<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
요기까지 오기위한 경로는?
<pre>
	<%=request.getAttribute("contents") %>
</pre>
</body>
</html>