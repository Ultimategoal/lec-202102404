<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/jdbcDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>

</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	: 데이터베이스 프로그래밍 단계
	1. 벤더가 제공하는 드라이버를 찾고, 빌드패스에 추가.
	2. 드라이버 클래스 로딩
	3. Connection 생성
	4. 쿼리 객체 생성
		- Statement
		- PreparedStatement
		- CallableStatement
	5. 쿼리 실행 : DML
		- ResultSet executeQuery() : SELECT
		- int executeUpdate() : INSERT, UPDATE, DELETE
	<%

// 	데이터베이스로부터 raw 데이터 조회후,
// 	모든 propert value 에 조회날짜를 추가할 것.
// 		out.println(conn);
// 	4. 쿼리 객체 생성
	
// 		rs.close(); when statement close it will automatically close by itself
	%>
	6. 질의 결과 사용
	7. 자원 해제
</pre>
<table border="1">
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="3">
				<div id="searchUI">
				<input type="text" name="search"/>
				<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="text" name="search" />
</form>

<script type="text/javascript">
	function makeTdFromData(propVO){
		let tds = [];
		for(let propName in propVO){
			console.log(propName);
			let td = $("<td>").html(propVO[propName]);
			tds.push(td);
		}
		return tds;
	}
	let tbody = $("table>tbody");
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name='"+name+"']").val(value);
		})
		searchForm.submit();
	})
	let searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let formData = new FormData(this);
// 		let data = {};
// 		for(let key of formData.keys()){
// 			data[key] = formData.get();
// 		}
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : 'json',
			success : function(resp){
				console.log(resp);
				tbody.empty();
				let trs = [];
				$.each(resp, function(i,v){
					trs.push($("<tr>").append(makeTdFromData(v)));
//	 				code = "<tr><td>"+v.property_name+"</td>";
//	 				code += "<tr><td>"+v.property_value+"</td>";
//	 				code += "<tr><td>"+v.description+"</td></tr>";
//	 				$("tbody").append(code);
				});
				tbody.append(trs);
			},
			error : function(errorResp){
				
			}
		})
		return false;
	}).submit();
</script>
</body>
</html>