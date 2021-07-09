<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	
	Locale locale = request.getLocale();
	String language = request.getParameter("language");
	if(language != null && !language.isEmpty()){
		locale = Locale.forLanguageTag(language);
	}
	
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
	Calendar cal = Calendar.getInstance();
	
	if(yearStr != null && yearStr.matches("\\d{4}")){
		cal.set(YEAR, Integer.parseInt(yearStr));
	}
	if(monthStr != null && monthStr.matches("\\d{1,2}")){
		cal.set(MONTH, Integer.parseInt(monthStr));
	}
	
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
	
	cal.set(DAY_OF_MONTH, 1);
	int offset = cal.get(DAY_OF_WEEK) - 1;
	int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
	
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	cal.add(MONTH, 2);
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	cal.add(MONTH, -1);
%>
<style type="text/css">
 table td:nth-child(1){
 	color : red;
 }
 table td:nth-child(7){
 	color : blue;
 }
 .current{
 	color : green;
 }
</style>

<h4>현재 서버의 시각 : <%=String.format(locale, "%tc", cal) %></h4>
<h4>
<%-- <a href="?year=<%=beforeYear %>&month=<%=beforeMonth%>">이전달</a> --%>
<%-- <a href="?year=<%=nextYear %>&month=<%=nextMonth%>">다음달</a> --%>
<a href="#" class="moveA" data-year="<%=beforeYear%>" data-month="<%=beforeMonth%>">이전달</a>
<%=String.format(locale, "%1$tY, %1$tB", cal) %>
<a href="#" class="moveA" data-year="<%=nextYear%>" data-month="<%=nextMonth%>">다음달</a>
</h4>
<form id="calendarForm">
	<input type="hidden" name="service" value="CALENDAR" />
	<input type="number" name="year" placeholder="<%=year%>" value="<%=year %>">
	<select name="month">
		<option value>월선택</option>
		<%
			String optionPtrn = "<option %s value='%s'>%s</option>";
			String[] months = dfs.getMonths();
			for(int tmp=0; tmp<12; tmp++){
				String selected = (tmp) == month ? "selected":"";
				out.println(
					String.format(optionPtrn, selected, tmp, months[tmp])		
				);
			}
		%>
	</select>
	<select name="language">
		<%
			Locale[] locales = Locale.getAvailableLocales();
			
			for(Locale tmpLoc : locales){
				String tag = tmpLoc.toLanguageTag();
				String name = tmpLoc.getDisplayName();
				String selected = tmpLoc.equals(locale) ? "selected" : "";
				if(name.isEmpty()) continue;
				out.println(
					//String.format(optionPtrn, "", tag, name)		
					String.format(optionPtrn, selected, tag, name)
				);
			}
		%>
	</select>
	
	<select name="timeZone">
		<%
			
		%>
	</select>
</form>
<%-- <%=Calendar.SUNDAY %> --%>
<%-- <%=Calendar.MONDAY %> --%>
<%=String.format("%tc", cal) %>
<table>
	<thead>
		<tr>
			<%
				String[] weekDays = dfs.getWeekdays();
				String thPtrn = "<th>%s</th>";
				for(int idx = SUNDAY; idx <= SATURDAY; idx++){
					out.println(
						String.format(thPtrn, weekDays[idx])
					);
				}
			 %>
		</tr>
	</thead>

	<tbody>
		<%
			String pattern = "<td>%s</td>";
			int number = 1;
			for(int row=1; row<=6; row++){
				out.println("<tr>");
				for(int col=SUNDAY; col<=SATURDAY; col++){
					int dateNumber = number++ - offset;
					String printNumber = dateNumber >= 1 && dateNumber <= lastDate ? dateNumber+"" : "&nbsp;";
					out.println(String.format(pattern, printNumber));
				}
				
				
				out.println("</tr>");
			}
		%>
	</tbody>
</table>
<script type="text/javascript">
	let calForm = $('#calendarForm').on('change', ":input", function(){
		console.log(this.form);
		console.log(calForm[0])
		this.form.submit();
// 		calForm.submit();
// 		$('#calendarForm').submit();
	}).on("submit", function(){
		console.log("=============================");
		return true;
	});
	
	$('.moveA').on('click', function(event){
		event.preventDefault();
		let year = $(this).data("year");
		let month = $(this).data("month");
		calForm.find("input[name='year']").val(year);
		$(calForm.get(0).month).val(month);
		calForm.submit();
		return false;
	});
</script>
