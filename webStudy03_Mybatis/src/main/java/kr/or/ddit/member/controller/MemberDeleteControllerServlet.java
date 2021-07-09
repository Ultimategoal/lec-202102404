package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인되어있는 사용자에 대한 탈퇴 처리.
 *
 */
@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	private MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String memPass = req.getParameter("memPass");
		if(StringUtils.isBlank(memPass)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		
		String message = null;
		String viewName = null;
		MemberVO member = new MemberVO();
		member.setMemId(authMember.getMemId());
		member.setMemPass(memPass);
		ServiceResult result = service.removeMember(member);
		System.out.println(result);
		switch(result) {
		case OK:
//			welcom page : redirect
			message = "삭제 등록 성공 일주일 뒤에 정상적으로 삭제 처리가 됩니다.";
			viewName = "redirect:/";
			session.removeAttribute("authMember");
			break;
		case INVALIDPASSWORD:
//			mypage, redirect
			message = "비밀번호 오류";
			viewName = "redirect:/mypage.do";
			break;
		default:
//			mypage, redirect
			message = "서버 오류, 나중에 다시 시도하세요.";
			viewName = "redirect:/mypage.do";
			break;
		}
		session.setAttribute("message", message);
		System.out.println(message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
}
