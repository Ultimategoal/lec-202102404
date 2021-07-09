package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MemberMyPageControllerServlet extends HttpServlet {
	MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 파라미터 가져오기 (세션을 이용함)
		HttpSession session = req.getSession();
		MemberVO auth = (MemberVO)session.getAttribute("authMember");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String mem_id = auth.getMemId();
		
//		2. selectDetail 를 이용하여 해당 아이디의 모든 정보 불러오기
		MemberVO member = service.retrieveMember(mem_id);
		
		PrintWriter out = resp.getWriter();
		out.println("<table border='1'>");
		out.println("<tr><td>dfdf</td></tr>");
		out.println("</table>");
		req.setAttribute("member", member);
		String dest = "/WEB-INF/views/member/memberView.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
	}
}
