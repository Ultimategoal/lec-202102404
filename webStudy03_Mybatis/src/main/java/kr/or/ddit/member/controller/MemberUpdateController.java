package kr.or.ddit.member.controller;

import java.beans.Beans;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
/**
 * 로그인된 사용자가 자기 정볼르 수정함.
 *
 */
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateController extends HttpServlet{
	private MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());
		req.setAttribute("member", member);
		String dest = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		MemberVO member = new MemberVO();
		
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
//		member.setMemPass(req.getParameter("memPass"));
//		member.setMemName(req.getParameter("memName"));
//		member.setMemAdd1(req.getParameter("memAdd1"));
//		member.setMemAdd2(req.getParameter("memAdd2"));
//		member.setMemHometel(req.getParameter("memHometel"));
//		member.setMemComtel(req.getParameter("memComtel"));
//		member.setMemHp(req.getParameter("memHp"));
//		member.setMemMail(req.getParameter("memMail"));
//		member.setMemBir(req.getParameter("memBir"));
//		member.setMemJob(req.getParameter("memJob"));
//		member.setMemLike(req.getParameter("memLike"));
//		member.setMemMemorial(req.getParameter("memMemorial"));
//		member.setMemMemorialday(req.getParameter("memMemorialday"));
		
		req.setAttribute("member", member);
		member.setMemId(req.getParameter("memId"));
		Map<String, String> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String viewName = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			System.out.println(result);
			
			switch(result) {
			case OK:
//				mypage 로 이동 , redirect
				message = "ok";
				req.setAttribute("message", message);
				viewName = "redirect:/mypage.do";
				break;
			case INVALIDPASSWORD:
//				memberForm.jsp 로 이동, forward
				viewName = "member/memberForm";
				message = "비밀번호 오류";
				break;
				
			default:
//				memberForm.jsp 로 이동, forward
				viewName = "member/memberForm";
				message = "서버 오류, 다시 시도해주세요";
				break;
			}
		}else {
//			memberForm.jsp로 이동, forward
			viewName = "member/memberForm"; // 논리적인 view name
			req.setAttribute("errors", errors);
		}
		
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
		
	}

	/**
	 * Member 테이블의 제약조건에 따른 검증.
	 * @param member
	 * @param errors
	 * @return
	 */
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원 ID 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀 번호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원 명 누락");
		}
		if (StringUtils.isBlank(member.getMemHp())) {
			valid = false;
			errors.put("memHp", "이동 전화 번호 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일 주소 누락");
		}
		
		if(StringUtils.isNotBlank(member.getMemMemorialday())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemMemorialday());
			} catch (ParseException e) {
				valid = false;
				errors.put("memMemorialday", "날짜 형식 확인");
			}
		}
		
		return valid;
	}
}
