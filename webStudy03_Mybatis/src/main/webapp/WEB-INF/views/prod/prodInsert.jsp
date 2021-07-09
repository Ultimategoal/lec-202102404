<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<form method="post">
	<table>
		<tr>
			<th>상품아이디</th>
			<td>
				<select id="prodLgu1" name="prodId">
					<option value="${prod.prodId }">상품분류</option>
					<c:forEach items="${lprodList}" var="lprod">
						<option value="${lprod['LPROD_GU'] }">${lprod["LPROD_NM"] }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prodName" value="${prod.prodName }" required></td>
		</tr>
		
		<tr>
			<th>상품분류</th>
			<td>
				<select id="pr" name="prodLgu">
					<option value>상품분류</option>
					<c:forEach items="${lprodList}" var="lprod">
						<option  value="${lprod['LPROD_GU'] }">${lprod["LPROD_NM"] }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>상품바이어코드</th>
			<td>
				<select name="prodBuyer">
					<option value>바이어이름</option>
					<c:forEach items="${buyerList}" var="buyer">
						<option value="${buyer['buyerId']}">${buyer["buyerName"]}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>상품cost</th>
			<td><input type="text" name="prodCost" value="${prod.prodCost }" required></td>
		</tr>
		
		<tr>
			<th>상품가격</th>
			<td><input type="text" name="prodPrice" value="${prod.prodPrice }" required></td>
		</tr>
		
		<tr>
			<th>상품판매가</th>
			<td><input type="text" name="prodSale" value="${prod.prodSale }" required></td>
		</tr>
		
		<tr>
			<th>개요</th>
			<td><input type="text" name="prodOutline" value="${prod.prodOutline }" required></td>
		</tr>
		
		<tr>
			<th>상세</th>
			<td><input type="text" name="prodDetail" value="${prod.prodDetail }"></td>
		</tr>
		
		<tr>
			<th>이미지</th>
			<td><input type="text" name="prodImg" value="${prod.prodImg }" required></td>
		</tr>
		
		<tr>
			<th>재고수량</th>
			<td><input type="text" name="prodTotalstock" value="${prod.prodTotalstock }" required></td>
		</tr>
		
		<tr>
			<th>입고일</th>
			<td><input type="date" name="prodInsdate" value="${prod.prodInsdate }"></td>
		</tr>
		
		<tr>
			<th>안전 재고수량</th>
			<td><input type="text" name="prodProperstock" value="${prod.prodProperstock }" required></td>
		</tr>
		
		<tr>
			<th>크기</th>
			<td><input type="text" name="prodSize" value="${prod.prodSize }"></td>
		</tr>
		
		<tr>
			<th>색상</th>
			<td><input type="text" name="prodColor" value="${prod.prodColor }"></td>
		</tr>
		
		<tr>
			<th>택배 특이사항</th>
			<td><input type="text" name="prodDelivery" value="${prod.prodDelivery }"></td>
		</tr>
		
		<tr>
			<th>단위</th>
			<td><input type="text" name="prodUnit" value="${prod.prodUnit }"></td>
		</tr>
		
		<tr>
			<th>입고</th>
			<td><input type="text" name="prodQtyin" value="${prod.prodQtyin }"></td>
		</tr>
		
		<tr>
			<th>QTYSALE</th>
			<td><input type="text" name="prodQtysale" value="${prod.prodQtysale }"></td>
		</tr>
	</table>
	
	<input type="submit" value="저장">
	<input type="reset" value="취소">
</form>
	<c:if test="${not empty message}">
		<script>alert("${message}");</script>
		<c:remove var="message" scope="session"/>
	</c:if>
</body>
<script>
	$("#prodLgu1").on("click", function(){
		$("#pr option:selected").html($("#prodLgu1 option:selected").html());
		$("#pr option:selected").val($("#prodLgu1 option:selected").val());
	})
</script>
</html>