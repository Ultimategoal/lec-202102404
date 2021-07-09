<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionTimer.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom.js"></script>
<script type="text/javascript">
	$(function(){
		let element = $("#timerArea").sessionTimer({  
			timeout:<%=session.getMaxInactiveInterval() %>,
			url:"<%=request.getContextPath()%>/sessionExtend"
		});
		console.log("===================");
		console.log(element);
	});
</script>

</head>
<body>
<h4>세션 타이머</h4>
<%=session.getId()%> : <%=session.getMaxInactiveInterval() %>
<h4 id="timerArea"></h4>
<!-- 1. 1초마다 출력되는 시간을 디스카운트 -->
<!-- 2. 1분 남은 시점에 메시지를 출력. -->
<!--    세션 연장 여부를 확인. -->
<!-- 3. 세션 연장을 선택한 경우, -->
<!--  1) 타이머 리셋 -->
<!--  2) 세션 연장을 위한 새로운 요청 발생(비동기- /sessionExtend, body 가 없는 응답) -->
  
<%-- <script type="text/javascript">
	let timer = $('#timerArea');
	let initvalue = 120;
	
	
	var timerInterval = setInterval(function(){
		timer.html(--initvalue);
		
		if(initvalue < 110){
			$("#messageArea").show();
			$(":input").show();
		}
		
		if(initvalue == 0){
			clearInterval(timerInterval)
		}
	}, 1000);
	
	
	$("#yesBtn").on("click", function(){
		$.ajax({
			url : '<%=request.getContextPath()%>/sessionExtend',
			method : "head",
			dataType : 'html',
			success : function(resp){
				
			}
		})
		initvalue = 120;
	})
	
	$("#noBtn").on("click", function(){
		$("#messageArea").css('display','none');
		$(":input").css('display','none');
	})
</script> --%>
</body>
</html>