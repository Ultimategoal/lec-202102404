package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		req.setAttribute("lprod", lprodList);
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		req.setAttribute("buyer", buyerList);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		addAttribute(req);
		
		String pageParam = req.getParameter("page");
//		String pl = req.getParameter("pl");
//		String pb = req.getParameter("pb");
//		String pn = req.getParameter("pn");
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		ProdVO detailSearch = new ProdVO();
		try {
			BeanUtils.populate(detailSearch, parameterMap);
		}catch(IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveProdList(pagingVO);
		
		String accept = req.getHeader("accept");
		if(StringUtils.contains(accept, "json")) {
			resp.setContentType("application/json; charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
			
		}else {
//		req.setAttribute("prodList", prodList);
//			req.setAttribute("pagingVO", pagingVO);
//		검색 조건 : 상품명, 상품분류코드, 거래처코드 (상세 검색)
//		동일 페이지내에서 정렬 조건 : 상품 분류별 정렬 및 최근 등록된 상품부터 조회됨.
			String viewName = "prod/prodList";
			
			if(viewName.startsWith("redirect:")) {
				viewName = viewName.substring("redirect:".length()); //redirect:만큼 짤라내야하기 때문에 씀
				resp.sendRedirect(req.getContextPath() + viewName);
			}else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
			}
		}
		
	}
}
