<%@ page language="java"
    pageEncoding="UTF-8"%>
<%-- <%
	response.setContentType("text/plain; charset=utf-8");
	response.setHeader("Content-type", "text/plain; charset=utf-8");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDest.jsp</title>
</head>
<body>
<h4>HttpServletResponse (response 기본객체)</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 데이터를 캡슐화한 객체.
	
	1. Response Line : protocol, status code("상태 코드")
	<%-- <%
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "강제 서버 에러");
		return;
	%> --%>
		** 상태코드 : 요청 처리의 결과를 표현하는 숫자 체계
		100~ : ing...
		200~ : OK
		300~ : 처리 완료를 위해 클라이언트로부터 추가적인 액션이 필요한 경우.
			304 (Not Modified), 302/307(Moved< Location)
		400~ : client side fail
			404(Not Found), 405(Not supported method)
			415(Not supported Media type)
			400(Bad Request) : 필수 파라미터가 누락되었거나 파라미터 타입이 다를 때, 파라미터의 길이 제한이 있을 때 그 제한을 넘어서는 길이의 파라미터를 전해줄 때 등등 다양하게 일어남
			403(Forbidden), 401(UnAuthorized)
		500~ : server side fail
			500(Internal Server Error)
	2. Response Header : Meta data, setHeader(name, value);
		* Content-Type : body 영역의 데이터 mime
		* Cache-Control(1,1), Pragma(1,0), Expires : 캐시를 제어할 때 사용됨.
			<a href="cacheControl.jsp">캐시 제어 예제</a>
		* Refresh : auto request
			<a href="autoRequest.jsp">Refresh를 통한 자동 요청</a>
		* Location : 페이지 이동
			<a href="">페이지 이동 예제</a>
	3. Response Body
</pre>
<img src="<%=request.getContextPath() %>/resources/images/2.jpg">
</body>
</html>