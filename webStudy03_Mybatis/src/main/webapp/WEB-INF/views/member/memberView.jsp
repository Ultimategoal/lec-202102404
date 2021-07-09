<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>마이 페이지</h4>
<%-- 	<% --%>
<!-- // 		MemberVO member = (MemberVO)request.getAttribute("member"); -->
<%-- 	%> --%>
<table border="1">
<tr><th>회원 ID</th><td>${member.memId}</td></tr>
<tr><th>비밀 번호</th><td>${member.memPass}</td></tr>
<tr><th>회원 명</th><td><${member.memName}</td></tr>
<tr><th>주민등록번호1</th><td>${member.memRegno1}</td></tr>
<tr><th>주민등록번호2</th><td>${member.memRegno2}</td></tr>
<tr><th>생일</th><td>${member.memBir}</td></tr>
<tr><th>우편 번호</th><td>${member.memZip}</td></tr>
<tr><th>주소1</th><td>${member.memAdd1}</td></tr>
<tr><th>주소2</th><td>${member.memAdd2}</td></tr>
<tr><th>집 전화 번호</th><td>${member.memHometel}</td></tr>
<tr><th>회사 전화 번호</th><td>${member.memComtel}</td></tr>
<tr><th>이동 전화 번호</th><td>${member.memHp}</td></tr>
<tr><th>이메일 주소</th><td>${member.memMail}</td></tr>
<tr><th>직업</th><td>${member.memJob}</td></tr>
<tr><th>취미</th><td>${member.memLike}</td></tr>
<tr><th>기념일 명</th><td>${member.memMemorial}</td></tr>
<tr><th>기념일 날짜</th><td>${member.memMemorialday}</td></tr>
<tr><th>마일리지</th><td>${member.memMileage}</td></tr>
<tr><th>삭제 여부</th><td>${member.memDelete}</td></tr>
<tr><th>구매 기록</th>
<td>
<table>
	<thead>
		<tr>
			<th>상품분류</th>
			<th>거래처명</th>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty prodList }">
			<c:set var="prodList" value="${member.prodList }" />
				<tr>
					<td colspan="7">구매 기록 없음.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${prodList }" var="prod" ></c:forEach>
					<tr>
						<td>${prod.lprodNm}</td>
						<td>${prod.buyer.buyerName}</td>
						<td>${prod.buyer.buyerAdd1}</td>
						<td>${prod.prodName}</td>
						<td>${prod.prodCost}</td>
						<td>${prod.prodPrice}</td>
						<td>${prod.prodMileage}</td>
					</tr>
<%-- 				<c:forEach var="i" begin="0" end="9" step="1"></c:forEach> <!-- step 1인 경우 생략 가능 --> --%>
			</c:otherwise>
		</c:choose>
<%-- 		<% --%>
<!-- // 			MemberVO member = (MemberVO)request.getAttribute("member"); -->
<!-- // 			Set<ProdVO> prodList = member.getProdList(); -->
<!-- // 			if(prodList.isEmpty()){ -->
<%-- 				%> --%>
<!-- 				<tr> -->
<!-- 					<td colspan="7">구매 기록 없음.</td> -->
<!-- 				</tr> -->
<%-- 				<% --%>
<!-- // 			}else{ -->
<!-- // 				for(ProdVO prod : prodList){ -->
<!-- // 					pageContext.setAttribute("prod", prod); -->
<%-- 					%> --%>
<!-- 					<tr> -->
<%-- 						<td>${prod.lprodNm}</td> --%>
<%-- 						<td>${prod.buyer.buyerName}</td> --%>
<%-- 						<td>${prod.buyer.buyerAdd1}</td> --%>
<%-- 						<td>${prod.prodName}</td> --%>
<%-- 						<td>${prod.prodCost}</td> --%>
<%-- 						<td>${prod.prodPrice}</td> --%>
<%-- 						<td>${prod.prodMileage}</td> --%>
<!-- 					</tr> -->
<%-- 					<% --%>
// 				}
// 			}
<%-- 		%> --%>
	</tbody>
</table>
</td>
</tr>
<c:if test="${sessionScope.authMember eq member}">
	
</c:if>
<c:if test="${not empty message }">
<script>
alert("${message}")
</script>
<c:remove var="message" scope="session"/>
<%--
// 	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
// 	String memPass = authMember.getMemPass();
// 	String message = (String)session.getAttribute("message");
// 	if(StringUtils.isNotBlank(message)){
// 		session.removeAttribute("message");
// 	}
// 	boolean rendering = member.equals(authMember);
// 	if(rendering){
--%>
<tr>
	<td colspan="2">
		<input type="button" value="수정" id="update" />
		<input type="button" value="탈퇴" id="delete" />
		<form method="post" action="<%=request.getContextPath() %>/member/memberDelete.do">
			<input type="hidden" name="memPass" />
		</form>
		<script>
			$("#delete").on("click", function(){
				let memPass = prompt("비밀번호 입력").trim();
				
				$("input[name='memPass']").val(memPass);
				
// 				if(memPass == "" || memPass == null){
// 					alert("비밀번호를 입력해주세요");
// 				}
				
				$("form").submit();
			})
		</script>
	</td>
</tr>
</c:if>
</table>
<h4>${message}</h4>
<jsp:include page="/includee/footer.jsp" />
</body>
<script>
// 	$(function(){
// 		if("${message}" != null || "${message}" != ""){
<%-- 			<%session.removeAttribute("message");%> --%>
// 			console.log("${message}");
// 		}
// 	})	
		
	$('#update').on('click', function(){
		location.href = "${pageContext.request.contextPath }/member/memberUpdate.do";
	})
</script>
<style>
table{
	border-collapse : collapse;
} 
table tr th:nth-child(1){
	background : linear-gradient(lightyellow, white, lightblue);
	width : 150px;
}
table td:nth-child(2){
	background : linear-gradient(orange, white, lightgreen);
	width : 500px;
}
table tr:hover{
	background-color : black;
	color : white;
}
#my_modal {
                display: none;
                width: 300px;
                padding: 20px 60px;
                background-color: #fefefe;
                border: 1px solid #888;
                border-radius: 3px;
            }

#my_modal .modal_close_btn {
    position: absolute;
    top: 10px;
    right: 10px;
}
</style>
</html>