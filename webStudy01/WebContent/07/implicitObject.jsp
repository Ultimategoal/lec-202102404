<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장객체)</h4>
<pre>
<%-- <%=request.getContextPath()%> --%>
<%-- <%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%> --%>
<%-- ${pageContext.request.contextPath } --%> pageContext가 기본 객체 중에서 가장 먼저 만들어지고 이 친구만 있으면 다 가능
	: jsp container 에 의해 서블릿 소스가 파싱될 때 자동으로 생성되는 객체
	1. request(*) : client와 request 에 대한 정보를 가진 객체
	2. response(*) : client 로 전송될 response에 대한 정보를 가진 객체
	3. out(JspWriter, *) : 응답데이터를 버퍼에 기록할 출력 스트림.
		: buffer를 제어하거나 상태를 확인할때도 활용됨.
	4. session(HttpSession,*) : 하나의 클라이언트가 하나의 브라우저를 사용할 때, 해당 클라이언트를 식별할 용도로 사용됨.
		<a href="../08/sessionDesc.jsp">session desc</a>
	5. application(ServletContext, **) : 현재 서버와 어플리케이션 자체에 대한 정보를 가진 객체.
		<a href="../08/applicationDesc.jsp">application desc</a>
	6. conifg(ServletConfig) : jsp를 직접 서블릿 매핑 하는일이 거의 없기 떄문에 거의 사용하지 않음
	7. page(Object)==this : jsp  인스턴스 자체., custom 태그를 쓰지 않는다면 거의 쓸일 없음
	8. exception(Throwable) : 에러나 예외가 발생했을 때 그 상황을 처리할 목적의 페이지에서 사용됨.
		page지시자의 isErrorPage="true"인 경우에만 활성화됨.
	9. pageContext(*****) : 모든 기본객체 중 가장 먼저 생성되고, 나머지 기본객체에 대한 참조를 가짐, 나머지 위 8가지 기본 객체는 pageContext 하나만 있어도 역할 수행 가능
	이렇게 9가지가 기본 객체
	번호순으로 가장 빈번하게 사용
</pre>
</body>
</html>