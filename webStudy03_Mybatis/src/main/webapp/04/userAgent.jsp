<%@page import="kr.or.ddit.enumtype.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		const PATTERN = "당신의 브라우저는 %s 입니다. OS의 종류는 %o 입니다.";
		let resultArea = $("#resultArea");
		$("a:first").on("click", function(event){
			event.preventDefault();
			$.ajax({
				url : "<%=request.getContextPath()%>/04/getBrowserName",
				dataType : 'text', // request header(Accept) / response header(Content-Type)
								  //text : text/plain, html: text/html, json: application/json, script: text/javascript
				success : function(resp){
					let message = null;
					if(typeof resp == "string"){
						message = resp;
					}else{
						message = resp.browser;
					}
					
					resultArea.empty();
					resultArea.append(
						$("<p>").html(PATTERN.replace("%s", message))
					);
					
				},
				error  : function(errorResp){
					console.log(errorResp);
				}				
			})
			
		});
	});
</script>
</head>
<body>
<a href="#">브라우저의 이름 받아오기(비동기로)</a>
<div id="resultArea"></div>
<!-- 사용자의 브라우저를 식별하고, 각 브라우저에 맞는 메시지 출력 -->
<!-- "당신의 브라우저는 ㅇㅇㅇ 입니다" 형식으로 포맷팅 메시지를 사용함. -->
<!-- // String pattern = "<p>당신의 브라우저는 %s 입니다.</p>"; -->
<!-- // String userAgent = request.getHeader("User-Agent"); -->
<!-- // // String userAgent = request.getHeader("User-Agent").toUpperCase(); -->
<!-- // out.println(userAgent); -->

<!-- // String browser = BrowserType.parseUserAgent(userAgent); -->


<!-- // String browser = BrowserType.OTHER.getBrowserName(); -->

<!-- // for(BrowserType tmp : BrowserType.values()){ -->
	
<!-- // 	if(userAgent.indexOf(tmp.name())>=0){ -->
<!-- // 		browser = tmp.getBrowserName(); -->
<!-- // 		break; -->
<!-- // 	} -->
<!-- // } -->

<!-- // Map<String, String> browserMap = new LinkedHashMap<>(); -->
<!-- // browserMap.put("MSIE", "익스플로러 구버전"); -->
<!-- // browserMap.put("TRIDENT", "익스플로러 최근버전"); -->
<!-- // browserMap.put("OPERA", "오페라"); -->
<!-- // browserMap.put("FIREFOX", "파이어폭스"); -->
<!-- // browserMap.put("EDG", "엣지"); -->
<!-- // browserMap.put("CHROME", "크롬"); -->
<!-- // browserMap.put("SAFARI", "사파리"); -->
<!-- // browserMap.put("OTHER", "기타"); -->

<!-- // String browser = browserMap.get("OTHER"); -->

<!-- // for(Entry<String, String> entry : browserMap.entrySet()){ -->
<!-- // 	if(userAgent.indexOf(entry.getKey())>=0){ -->
<!-- // 		browser = entry.getValue(); -->
<!-- // 		break; -->
<!-- // 	} -->
<!-- // } -->
<!-- // out.println(String.format(pattern, browser)); -->

</body>
</html>