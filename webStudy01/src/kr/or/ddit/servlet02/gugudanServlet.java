package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class gugudanServlet extends ReadTmplServlet {

	@Override
	protected String getMime() {
		return "text/html; charset=utf-8";
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		
		int minDan = 2;
		if(minStr != null && minStr.matches("\\d{1,2}")) {
			minDan = Integer.parseInt(minStr);
		}
		int maxDan = 9;
		
		if(maxStr != null && maxStr.matches("[0-9]+")) {
			maxDan = Integer.parseInt(maxStr);
		}
		
		StringBuffer gugudan = new StringBuffer();
		
		
		gugudan.append("<tr>");
		for(int k = minDan; k <= maxDan; k++) {
			gugudan.append("<td>");
			gugudan.append(k + "단");
			gugudan.append("</td>");
		}
		gugudan.append("</tr>");
		
		for(int i = 1; i < 10; i++) {
			gugudan.append("<tr id='tr1'>");
			for(int j = minDan; j <= maxDan; j++) {
				gugudan.append("<td>");
				gugudan.append(j + " * " + i + " = " + i*j + " ");
				gugudan.append("</td>");
			}
			gugudan.append("</tr>");
		}
		req.setAttribute("gugudan", gugudan);
	}

}
