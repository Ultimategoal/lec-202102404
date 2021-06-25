<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 지역적 에러 처리 방법에 따라 사용되는 에러 페이지 </h4>
<pre>
	자라고 그렇게 시간을 줬는데,
	그때는 안자고
	<%
		exception.printStackTrace();
		ErrorData ed = pageContext.getErrorData();
	%>
	<%=exception.getMessage() %>
	<%=ed.getRequestURI() %>
	<%=ed.getStatusCode() %>
	<%=ed.getThrowable()==exception %>
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	좀따 보면 또 졸고 있을거지?!!! 영현이!@@@!@!
	
</pre>
</body>
</html>