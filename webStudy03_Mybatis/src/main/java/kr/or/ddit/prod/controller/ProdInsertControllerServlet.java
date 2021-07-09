package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet {
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDAO = new OthersDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
		String dest = "/WEB-INF/views/prod/prodInsert.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		ProdVO prod = new ProdVO();
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		String message = null;
		try {
			BeanUtils.populate(prod, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServiceResult result =  service.createProd(prod);

		String viewName = null;
		switch(result) {
			case OK:
				viewName = "prod/prodInsert";
				message = "등록 성공";
				break;
			case FAIL:
				viewName = "redirect:/prod/prodInsert.do";
				message = "등록 실패";
				break;
		}
		req.setAttribute("message", message);
		
		
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
