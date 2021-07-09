<%@page import="java.util.Objects"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
</head>
<body>
<%-- <jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" /> --%>
<%-- <jsp:useBean id="errors" class="java.util.HashMap" scope="request" /> --%>
<c:if test="${not empty message }">
<script type="text/javascript">
	alert("${message}");
</script>
</c:if>
<form id="memberForm" method="post">
	<table>
		<tr>
			<th>회원 ID</th>
			<td><input type="text" name="memId" required
				value="${member.memId}" data-msg-remote="아이디 중복!" /><label id="memId-error"
				class="error" for="memId">${errors["memId"]}</label></td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td><input type="text" name="memPass" required
				value="${member.memPass}" /><label id="memPass-error"
				class="error" for="memPass">${errors["memPass"]}</label></td>
		</tr>
		<tr>
			<th>회원 명</th>
			<td><input type="text" name="memName" required
				value="${member.memName}" /><label id="memName-error"
				class="error" for="memName">${errors["memName"]}</label></td>
		</tr>
		<tr>
			<th>주민등록번호1</th>
			<td><input type="text" name="memRegno1"
				value="${member.memRegno1}" /><label id="memRegno1-error"
				class="error" for="memRegno1">${errors["memRegno1"]}</label></td>
		</tr>
		<tr>
			<th>주민등록번호2</th>
			<td><input type="text" name="memRegno2"
				value="${member.memRegno2}" /><label id="memRegno2-error"
				class="error" for="memRegno2">${errors["memRegno2"]}</label></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir"
				value="${member.memBir}" /><label id="memBir-error"
				class="error" for="memBir">${errors["memBir"]}</label></td>
		</tr>
		<tr>
			<th>우편 번호</th>
			<td><input type="text" name="memZip"
				value="${member.memZip}" /><label id="memZip-error"
				class="error" for="memZip">${errors["memZip"]}</label>
				<input type="button" value="우편" id="df">
				</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1"
				value="${member.memAdd1}" /><label id="memAdd1-error"
				class="error" for="memAdd1">${errors["memAdd1"]}</label></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2"
				value="${member.memAdd2}" /><label id="memAdd2-error"
				class="error" for="memAdd2">${errors["memAdd2"]}</label></td>
		</tr>
		<tr>
			<th>집 전화 번호</th>
			<td><input type="text" name="memHometel"
				value="${member.memHometel}" /><label
				id="memHometel-error" class="error" for="memHometel">${errors["memHometel"]}</label></td>
		</tr>
		<tr>
			<th>회사 전화 번호</th>
			<td><input type="text" name="memComtel"
				value="${member.memComtel}" /><label id="memComtel-error"
				class="error" for="memComtel">${errors["memComtel"]}</label></td>
		</tr>
		<tr>
			<th>이동 전화 번호</th>
			<td><input type="text" name="memHp" required
				value="${member.memHp}" /><label id="memHp-error"
				class="error" for="memHp">${errors["memHp"]}</label></td>
		</tr>
		<tr>
			<th>이메일 주소</th>
			<td><input type="text" name="memMail" required
				value="${member.memMail}" /><label id="memMail-error"
				class="error" for="memMail">${errors["memMail"]}</label></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob"
				value="${member.memJob}" /><label id="memJob-error"
				class="error" for="memJob">${errors["memJob"]}</label></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike"
				value="${member.memLike}" /><label id="memLike-error"
				class="error" for="memLike">${errors["memLike"]}</label></td>
		</tr>
		<tr>
			<th>기념일 명</th>
			<td><input type="text" name="memMemorial"
				value="${member.memMemorial}" /><label
				id="memMemorial-error" class="error" for="memMemorial">${errors["memMemorial"]}</label></td>
		</tr>
		<tr>
			<th>기념일 날짜</th>
			<td><input type="date" name="memMemorialday"
				value="${member.memMemorialday}" /><label
				id="memMemorialday-error" class="error" for="memMemorialday">${errors["memMemorialday"]}</label></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" /> 
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
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
<script type="text/javascript">
$(function(){
	let exampleModal = $("#exampleModal").modal({
		show:false
	}).on('show.bs.modal', function(event){
		console.log(event.relatedTarget);
		let trTag = event.relatedTarget;
		if(!trTag) return false;
		let mem_id = trTag.id;
		$.ajax({
			url : '<%=request.getContextPath()%>/member/memberView.do',
			data : {who:mem_id},
			dataType : 'html',
			success : function(resp){
				exampleModal.find(".modal-body").html(resp);
	//				code = resp;
	//				modal('my_modal');
	//				$("#my_modal").html(code);
			}
		});
	}).on('hidden.bs.modal', function(event){
		exampleModal.find(".modal-body").empty();
	});
	$("table").on('click', "#df", function(){
		alert($(this).find("td").html())
		let mem_id = this.id;
		exampleModal.modal('show', this);
	<%-- 		location.href = "<%=request.getContextPath()%>/member/memberView.do?who="+mem_id; --%>
	})
	
})
	let validateOptions = {};
	<%
		String command = (String) request.getAttribute("command");
		if("INSERT".equals(command)){
			%>
			validateOptions.rules={
				memId:{
					remote:"<%=request.getContextPath()%>/member/idCheck.do"
				}
			}
			validateOptions.messages={
				memId:{
					remote:"아이디 중복"
				}
			}
			<%
		}
	%>
	console.log(validateOptions);
	$("#memberForm").validate();
</script>
<jsp:include page="/includee/footer.jsp" />
</body>
<style>
	th{
		background : linear-gradient(lightblue, orange, lightgreen);
	}
	td, input[type=text], input[type=date]{
		background : linear-gradient(skyblue, lightblue, lightgreen);
	}
</style>
</html>












