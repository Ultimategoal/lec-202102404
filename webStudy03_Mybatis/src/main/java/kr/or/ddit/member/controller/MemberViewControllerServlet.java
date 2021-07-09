package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet {

	MemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. id 파라미터 
		String who = req.getParameter("who");
//		2. 검증
//			1) 성공 : 
//			2) 실패 : 클라이언트에게 400번대 에러 발생
		if (who == null || who.isEmpty()) {
			resp.sendError(404, "필수 파라미터 누락");
			return;
		}
		int status = 200;
		resp.setCharacterEncoding("utf-8");
//		3. 스코프 및 서비스에서 유저 null일시 execption 잡아주기
		MemberVO member = service.retrieveMember(who);
		try {
			req.setAttribute("member", member);
			String dest = "/WEB-INF/views/member/memberView.jsp";
			req.getRequestDispatcher(dest).forward(req, resp);
		}catch(UserNotFoundException e) {
			resp.sendError(404, e.getMessage());
		}

//		if (status == 200) {
//			PrintWriter out = resp.getWriter();
//			out.println("<table border='1'>");
//			out.println("<tr><th>회원 ID</th><td>" + member.getMemId() + "</td></tr>");
//			out.println("<tr><th>비밀 번호</th><td>" + member.getMemPass() + "</td></tr>         ");
//			out.println("<tr><th>회원 명</th><td>" + member.getMemName() + "</td></tr>           ");
//			out.println("<tr><th>주민등록번호1</th><td>" + member.getMemRegno1() + "</td></tr>   ");
//			out.println("<tr><th>주민등록번호2</th><td>" + member.getMemRegno2() + "</td></tr>   ");
//			out.println("<tr><th>생일</th><td>" + member.getMemBir() + "</td></tr>               ");
//			out.println("<tr><th>우편 번호</th><td>" + member.getMemZip() + "</td></tr>          ");
//			out.println("<tr><th>주소1</th><td>" + member.getMemAdd1() + "</td></tr>             ");
//			out.println("<tr><th>주소2</th><td>" + member.getMemAdd2() + "</td></tr>             ");
//			out.println("<tr><th>집 전화 번호</th><td>" + member.getMemHometel() + "</td></tr>   ");
//			out.println("<tr><th>회사 전화 번호</th><td>" + member.getMemComtel() + "</td></tr>  ");
//			out.println("<tr><th>이동 전화 번호</th><td>" + member.getMemHp() + "</td></tr>      ");
//			out.println("<tr><th>이메일 주소</th><td><" + member.getMemMail() + "</td></tr>       ");
//			out.println("<tr><th>직업</th><td>" + member.getMemJob() + "</td></tr>               ");
//			out.println("<tr><th>취미</th><td>" + member.getMemLike() + "</td></tr>              ");
//			out.println("<tr><th>기념일 명</th><td>" + member.getMemMemorial() + "</td></tr>     ");
//			out.println("<tr><th>기념일 날짜</th><td>" + member.getMemMemorialday() + "</td></tr>");
//			out.println("<tr><th>마일리지</th><td>" + member.getMemMileage() + "</td></tr>       ");
//			out.println("<tr><th>삭제 여부</th><td>" + member.getMemDelete() + "</td></tr>       ");
//			out.println("</table>");
//		}
	}
}
