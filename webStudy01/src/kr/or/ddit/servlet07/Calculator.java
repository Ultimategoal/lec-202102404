package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/caculator.do")
public class Calculator extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String left = req.getParameter("left");
		String right = req.getParameter("right");
		String accept = req.getParameter("mime");
		Map<String, String> operators = new HashMap<>();
		
		operators.put("add", "+");
		operators.put("sub", "-");
		operators.put("mul", "*");
		operators.put("dev", "/");
		operators.put("mod", "%");
		
		req.setAttribute("operators", operators);
		
		
		if(accept == null || accept.isEmpty()) {
			accept = req.getHeader("accept");
		}
		
		int status = 200;
		int result = 0;
		int left1 = 0;
		int right1 = 0;
		
		if(left == null || right == null || left.isEmpty() || right.isEmpty() || !left.matches("[0-9]{1,3}") || !right.matches("[0-9]{1,3}")) {
			status = 400;
			resp.sendError(status);
		}else {
			left1 = Integer.parseInt(left);
			right1 = Integer.parseInt(right);
		}
		
		String op = req.getParameter("operator");
		if(op == null || op.isEmpty()) {
			status = 400;
		}else {
			if(operators.get("add").equals(op)) {
				result = left1 + right1;
			}
			if(operators.get("sub").equals(op)) {
				result = left1 - right1;
			}
			if(operators.get("mul").equals(op)) result = left1 * right1;
			if(operators.get("div").equals(op)) result = left1 / right1;
			if(operators.get("mod").equals(op)) result = left1 % right1;
		}
	}
}
