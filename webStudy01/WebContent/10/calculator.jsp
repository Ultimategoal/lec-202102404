<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="<%=request.getContextPath() %>/caculate.do">
	<input type="radio" name="mime" value="plain" />PLAIN
	<input type="radio" name="mime" value="json" />JSON
	<input class="form-control" type="number" name="left" />
	<select name="operator" onchange="this.form.submit();">
	<%Map<String, String> operators = (Map<String, String>)request.getAttribute("operators"); %>
	연산자 UI : operator
	</select>
	<input class="form-control" type="number" name="right" />
	<input type="submit" value="=" />
	<span id="resultArea"></span>
</form>
<script>
 $("form").on('submit', function(){
	 event.preventDefault();
	 return false;
	 
</script>
