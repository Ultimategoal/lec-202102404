<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<%-- <% ProdVO prod = (ProdVO)request.getAttribute("prod"); %> --%>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<th>상품아이디</th>
			<td>${prod.prodId }</td>
		</tr>
		<tr>	
			<th>상품명</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>	
			<th>상품분류</th>
			<td>${prod.lprodNm}</td>
		</tr>
		<tr>	
			<th>판매자</th>
			<td>${prod.buyer.buyerName}</td>
		</tr>
		<tr>	
			<th>판매 담당자</th>
			<td>${prod.buyer.buyerCharger}</td>
		</tr>
		<tr>	
			<th>상품cost</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>	
			<th>상품가격</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>	
			<th>판매가격</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>	
			<th>상품개요</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>	
			<th>상품상세</th>
			<td>${prod.prodDetail}</td>
		</tr>
		<tr>	
			<th>상품크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>	
			<th>색깔</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>	
			<th>택배</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>	
			<th>압고</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>	
			<th>재고</th>
			<td>${prod.prodQtysale}</td>
		</tr>
	<tr>
		<th>구매자 목록</th>
		<td>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>메일</th>
				<th>마일리지</th>
			</tr>
			<c:forEach items="${prod.memberList }" var="product">
				<tr>
					<td>${product["memId"] }</td>
					<td>${product["memName"] }</td>
					<td>${product["memHp"] }</td>
					<td>${product["memMail"] }</td>
					<td>${product["memMileage"] }</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
	</thead>
</table>
<input type="button" value="수정" id="update">
</body>
<style>
	table{
		border-collapse : collapse;
	}

	th{
		background : linear-gradient(lightblue, white, skyblue);
	}
	td{
		background : linear-gradient(green, lightyellow, skyblue);
	}
</style>
<script>
	$("#update").on("click", function(){
		location.href = "${pageContext.request.contextPath}/prod/prodUpdate.do?product=${prod.prodId}";
	})
</script>
</html>