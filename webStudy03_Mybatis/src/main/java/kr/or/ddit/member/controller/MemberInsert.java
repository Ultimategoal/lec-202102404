package kr.or.ddit.member.controller;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsert extends HttpServlet {
	MemberService service = MemberServiceImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("command", "INSERT");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		req.setCharacterEncoding("utf-8");
		String viewName = "member/memberForm";
		if(viewName.startsWith("redirect")) { 
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		addAttribute(req);
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		Map<String, String> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String viewName = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.createMemeber(member);
			switch(result) {
			case OK:
				message = "ok";
				req.setAttribute("message", message);
				viewName = "redirect:/login/loginForm.jsp";
				break;
			case PKDUPLICATED:
//				memberForm.jsp ??? ??????, forward
				viewName = "member/memberForm";
				message = "????????? ??????, ?????? ???????????? ??????????????????";
				break;
				
			default:
//				memberForm.jsp ??? ??????, forward
				viewName = "member/memberForm";
				message = "?????? ??????, ?????? ??????????????????";
				break;
			}
		}else {
//			memberForm.jsp??? ??????, forward
			viewName = "member/memberForm"; // ???????????? view name
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
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "?????? ID ??????");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "?????? ?????? ??????");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "?????? ??? ??????");
		}
		if (StringUtils.isBlank(member.getMemHp())) {
			valid = false;
			errors.put("memHp", "?????? ?????? ?????? ??????");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "????????? ?????? ??????");
		}
		
		if(StringUtils.isNotBlank(member.getMemMemorialday())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemMemorialday());
			} catch (ParseException e) {
				valid = false;
				errors.put("memMemorialday", "?????? ?????? ??????");
			}
		}
		
		return valid;
	}
}
