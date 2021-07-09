<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<%-- <% PagingVO<ProdVO> pagingVO = (PagingVO)request.getAttribute("pagingVO"); --%>
<%--    <%List<Map<String, Object>> lprodList = (List<Map<String, Object>>)request.getAttribute("lprod"); --%>
<%--    <% List<BuyerVO> buyerList = (List<BuyerVO>)request.getAttribute("buyer");%> --%>
</head>
<style>
	th{
		background : black;
		color : white;
	}
</style>
<body>
<h4>Search UI</h4>
<table border="1">
	<thead>
	<tr>
		<td colspan="3">
			<div id="searchUI">
			분류 : 
			<select name="prodLgu">
				<option value>분류</option>
				<c:forEach items="${lprod }" var="lprodList">
					<option value="${lprodList['LPROD_GU'] }">${lprodList["LPROD_NM"] }</option>
				</c:forEach>
<%-- 				<%for(int i = 0; i < lprodList.size(); i++){ %> --%>
<%-- 					<option value="<%=lprodList.get(i).get("LPROD_GU")%>"><%=lprodList.get(i).get("LPROD_NM")%></option> --%>
<%-- 				<%} %> --%>
<%-- 				<%for(Map<String, Object> map : lprodList){ %> --%>
<%-- 					<option><%=map.get() %></option> --%>
<%-- 				<%} %> --%>
			</select>
			거래처 : 
			<select name="prodBuyer">
					<option value>거래처</option>
				<c:forEach items="${buyer }" var="buy">
					<option class="${buy['buyerLgu'] }" value="${buy['buyerId'] }" >${buy["buyerName"] }</option>
				</c:forEach>
<%-- 				<%for(int i = 0; i < buyerList.size(); i++) {%> --%>
<%-- 					<option class="<%=buyerList.get(i).getBuyerLgu() %>" value="<%=buyerList.get(i).getBuyerId() %>"><%=buyerList.get(i).getBuyerName() %></option> --%>
<%-- 				<%} %> --%>
			</select>
			상품명 : 
			<input type="text" name="prodName">
			<input type="button" value="검색" id="searchBtn" />
			</div>
		</td>
	</tr>
	</thead>
	<tbody>
<!-- 	<tr> -->
<!-- 		<th>상품명</th> -->
<!-- 		<th>상품분류코드</th> -->
<!-- 		<th>거래처코드</th> -->
<!-- 		<th>구매가</th> -->
<!-- 		<th>판매가</th> -->
<!-- 		<th>마일리지</th> -->
<!-- 	</tr> -->
	
<%-- 	<% --%>
<!-- // 	List<ProdVO> prodList = pagingVO.getDataList(); -->
<!-- // 	if(prodList.isEmpty()){ -->
<%-- 		%> --%>
<!-- 		<tr><td colspan="6">조건에 맞는 상품 없음.</td> -->
<%-- 		<% --%>
<!-- // 	}else{ -->
<%-- 	for(int i = 0; i < prodList.size(); i++){ %> --%>
<!-- 	<tr> -->
<%-- 		<td><%=prodList.get(i).getProdName() %></td> --%>
<%-- 		<td><%=prodList.get(i).getLprodNm() %></td> --%>
<%-- 		<td><%=prodList.get(i).getBuyer().getBuyerName() %></td> --%>
<%-- 		<td><%=prodList.get(i).getProdCost() %></td> --%>
<%-- 		<td><%=prodList.get(i).getProdPrice() %></td> --%>
<%-- 		<td><%=prodList.get(i).getProdMileage() %></td> --%>
<!-- 	</tr> -->
<%-- 	<%}  --%>
<%-- 	}%> --%>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot>
	
</table>
<form id="searchForm">
	<input type="text" name="prodLgu" value="${pagingVO.detailSearch.prodLgu}"/>
	<input type="text" name="prodBuyer" value="${pagingVO.detailSearch.prodBuyer}"/>
	<input type="text" name="prodName" value="${pagingVO.detailSearch.prodName}"/>
	<input type="text" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).ajaxComplete(function(event, xhr, options){
			searchForm[0].reset();
		}).ajaxError(function(event, xhr, options, error){
			console.log(event);
			console.log(xhr);
			console.log(options);
			console.log(error);
		});
		let prodBuyer = $("select[name='prodBuyer']");
		$("select[name='prodLgu']").on("change", function(){
			let lgu = $(this).val();
// 			$("select[name='prodBuyer']").find("option").hide();
// 			$("select[name='prodBuyer']").find("option."+lgu).show();
// 			$("select[name='prodBuyer']").find("option:first").show();
			prodBuyer.find("option").hide();
			prodBuyer.find("option."+lgu).show();
			prodBuyer.find("option:first").show();

		})	
		
		let tbody = $("table>tbody").on("click", "tr", function(){
			let prod = $(this).data("prod");
			if(!prod) return false;
			let prodId = prod.prodId;
			location.href = "${pageContext.request.contextPath}/prod/prodView.do?what="+prodId;
		});
		let pagingArea = $("#pagingArea");
		function makeTrTag(prod){
			return $("<tr>").append(
				$("<td>").html(prod.prodName),		
				$("<td>").html(prod.lprodNm),
				$("<td>").html(prod.buyer.buyerName),
				$("<td>").html(prod.prodCost),
				$("<td>").html(prod.prodPrice),
				$("<td>").html(prod.prodMileage)
			).data("prod", prod);
		}
				
		let searchForm = $("#searchForm").paging({
		pagingArea : "#pagingArea",
		pagingLink : ".pagingLink",
		searchUI : "#searchUI",
		btnSelector : "#searchBtn",
		pageKey : "page",
		pageParam : "page"
		}).ajaxForm({
			dataType : "json",
			success : function(pagingVO){
				tbody.empty();
				pagingArea.empty();
				let prodList = pagingVO.dataList;
				let trTags = [];
				if(prodList){
					$(prodList).each(function(idx, prod){
						trTags.push(makeTrTag(prod));
					});
					pagingArea.html(pagingVO.pagingHTML);
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr("colspan", "6").html("조건에 맞는 결과가 업습니다.")
						)
					);
				}
				tbody.append(trTags);
				
			} // success end
		}).submit();
	});
</script>
</body>
</html>