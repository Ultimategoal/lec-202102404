<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> EL 에서의 집합 객체 사용 </h4>
<pre>
	<%
		String[] array = new String[]{"array1", "array2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = Collections.singleton("setValue");
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key 3", "value3");
		map.put("key-4", "value4");
		
		pageContext.setAttribute("array", array);
		pageContext.setAttribute("list", list);
		pageContext.setAttribute("set", set);
		pageContext.setAttribute("map", map);
	%>
	${array[1] }, <%=array[1] %>, ${array[4] } <!-- el에서는 제어를 담당하지 않고 표현만을 위해 쓰기 때문에 execption이 거의 뜨지 않고 white space로 처리 됨 -->
	${list.get(0) }, ${list[0] }, ${list[4] }, \${list.get(4) } <!-- 메소드를 호출하면 자바 그대로 따라 가기 때문에 whitespace가 안뜨고 에러가 뜸  그래서 list[4]와 같은 구조를 더 많이 사용 -->
	${set }
	${map.get("key2") }, ${map.key2 }, \${map.key 3 }, ${map["key 3"] }, ${map.key-4 }, ${map["key-4"] }
</pre>
</body>
</html>