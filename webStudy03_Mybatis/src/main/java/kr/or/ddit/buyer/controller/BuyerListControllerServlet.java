package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/buyer/buyerList.do")
public class BuyerListControllerServlet extends HttpServlet{
	BuyerService service = new BuyerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO simpleSearch = new SearchVO(searchType, searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setSimpleSearch(simpleSearch);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
//				memberList 라는 이름의 속성으로 회원 목록 공유.
		
		List<MemberVO> memberList = service.retrieveBuyerList(pagingVO);
		String dest = "/WEB-INF/views/member/memberList.jsp";
//				req.setAttribute("memberList", memberList);
		pagingVO.setDataList(memberList);
		req.setAttribute("pagingVO", pagingVO);
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
}
