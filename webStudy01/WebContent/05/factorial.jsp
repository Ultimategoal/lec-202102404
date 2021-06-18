<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
left 입력을 통해 숫자를 입력받고,
값이 변경되는 순간 서버로 비동기 요청 발생시킴.
서버에서 factorial 연산을 수행한 후,
선택한 mime의 형태로 응답을 전송.
plain : "2! = 2"
json :
{
	left : 2
	operator : !,
	expression : "2! = 2"
}
xml :
<result>
	<left>2</left>
	<expression>2! = 2</expression>
</result>

<form action="/05/factorial">
<input type="radio" name="mime" value="json">JSON
<input type="radio" name="mime" value="plain">PLAIN
<input type="radio" name="mime" value="xml">XML
<input type="number" name="left" min="1" max="10">
</form>
<div id="resultArea">

</div>
</body>
</html>