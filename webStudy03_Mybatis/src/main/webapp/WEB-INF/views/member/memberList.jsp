<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
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
</head>
<body>
<h4>회원 목록 조회</h4>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>지역</td>
		<td>휴대폰</td>
		<td>이메일</td>
		<td>마일리지</td>
	</tr>
	<tbody>
	
	<%--
	PagingVO<MemberVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
	List<MemberVO> memberList = pagingVO.getDataList();
// 	List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList"); 
	if(memberList.size() > 0){
		for(int i = 0; i < memberList.size(); i++){
			
	--%>
	<c:set var="memberList" value="${pagingVO.dataList }"></c:set>
	<c:choose>
	<c:when test="${not empty memberList }">
		<c:forEach var="i" begin="0" end="${memberList.size()-1 }">
			<tr id="${pagingVO.dataList.get(i).memId }">
					<td>${pagingVO.dataList.get(i).memId }</td>	
					<td>${pagingVO.dataList.get(i).memName }</td>
					<td>${pagingVO.dataList.get(i).memAdd1 }</td>
					<td>${pagingVO.dataList.get(i).memHp }</td>
					<td>${pagingVO.dataList.get(i).memMail }</td>
					<td>${pagingVO.dataList.get(i).memMileage }</td>
			</tr>
<%-- 			<tr id="<%=memberList.get(i).getMemId()%>"> --%>
<%-- 					<td><%=memberList.get(i).getMemId() %></td>	 --%>
<%-- 					<td><%=memberList.get(i).getMemName() %></td> --%>
<%-- 					<td><%=memberList.get(i).getMemAdd1() %></td> --%>
<%-- 					<td><%=memberList.get(i).getMemHp() %></td> --%>
<%-- 					<td><%=memberList.get(i).getMemMail() %></td> --%>
<%-- 					<td><%=memberList.get(i).getMemMileage() %></td> --%>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="6">조건에 맞는 회원이 없습니다.</td></tr>
	</c:otherwise>
	</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				${pagingVO.pagingHTML }
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
						<option value="hp">휴대폰</option>
					</select>
					<input type="text" name="searchWord" />
					<button type="button" id="searchBtn">검색</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
	<input type="text" name="page" />
</form>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>



<!-- <div id="my_modal"> -->
<!--             <a class="modal_close_btn">닫기</a> -->
<!--         </div> -->

<!--         <button id="popup_open_btn">팝업창 띄어 줘염</button> -->

        <script>
//             function modal(id) {
//                 var zIndex = 9999;
//                 var modal = document.getElementById(id);

//                 // 모달 div 뒤에 희끄무레한 레이어
//                 var bg = document.createElement('div');
//                 bg.setStyle({
//                     position: 'fixed',
//                     zIndex: zIndex,
//                     left: '0px',
//                     top: '0px',
//                     width: '100%',
//                     height: '100%',
//                     overflow: 'auto',
//                     // 레이어 색갈은 여기서 바꾸면 됨
//                     backgroundColor: 'rgba(0,0,0,0.4)'
//                 });
//                 document.body.append(bg);

//                 // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
//                 modal.querySelector('.modal_close_btn').addEventListener('click', function() {
//                     bg.remove();
//                     modal.style.display = 'none';
//                 });

//                 modal.setStyle({
//                     position: 'fixed',
//                     display: 'block',
//                     boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

//                     // 시꺼먼 레이어 보다 한칸 위에 보이기
//                     zIndex: zIndex + 1,

//                     // div center 정렬
//                     top: '50%',
//                     left: '50%',
//                     transform: 'translate(-50%, -50%)',
//                     msTransform: 'translate(-50%, -50%)',
//                     webkitTransform: 'translate(-50%, -50%)'
//                 });
//             }

//             // Element 에 style 한번에 오브젝트로 설정하는 함수 추가
//             Element.prototype.setStyle = function(styles) {
//                 for (var k in styles) this.style[k] = styles[k];
//                 return this;
//             };

</script>
</body>
<style>
.tt:hover{
	background : yellow;
}
/* #my_modal { */
/*                 display: none; */
/*                 width: 800px; */
/*                 padding: 20px 60px; */
/*                 background-color: #fefefe; */
/*                 border: 1px solid #888; */
/*                 border-radius: 3px; */
/*             } */

/* #my_modal .modal_close_btn { */
/*     position: absolute; */
/*     top: 10px; */
/*     right: 10px; */
/* } */
</style>
<script>
	$(function(){
		$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
		$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");
		$(".pagingLink").on("click", function(event){
			event.preventDefault();
			let page = $(this).data("page");
			searchForm.find('[name="page"]').val(page);
			searchForm.submit();
			return false;
		}).css("cursor", "pointer");
		//페이징 처리
		let searchForm = $("#searchForm")
		let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
			let inputs = searchUI.find(":input[name]");
			$(inputs).each(function(idx, input){
				let name = this.name;
				let value = $(this).val();
				searchForm.find("[name='"+name+"']").val(value);
			})
			searchForm.submit();
		});
		
		let exampleModal = $("#exampleModal").modal({
			show:false
		}).on('show.bs.modal', function(event){
			console.log(event.relatedTarget);
			let trTag = event.relatedTarget;
			if(!trTag) return false;
			let mem_id = trTag.id;
			$.ajax({
				url : '${pageContext.request.contextPath }/member/memberView.do',
				data : {who:mem_id},
				dataType : 'html',
				success : function(resp){
					exampleModal.find(".modal-body").html(resp);
// 					code = resp;
// 					modal('my_modal');
// 					$("#my_modal").html(code);
				}
			});
		}).on('hidden.bs.modal', function(event){
			exampleModal.find(".modal-body").empty();
		});
		$("tbody").on('click', "tr[id]", function(){
			alert($(this).find("td").html())
			let mem_id = this.id;
			exampleModal.modal('show', this);
	<%-- 		location.href = "<%=request.getContextPath()%>/member/memberView.do?who="+mem_id; --%>
		})
	})

// 	$("table").on('click', '.tt', function(){
// 		alert($(this).find('.mem_id').html());
// 	})
</script>
<jsp:include page="/includee/footer.jsp" />
</html>