package kr.or.ddit.prod.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	private ProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int insert = prodDAO.insertProd(prod);
		ServiceResult result = null;
		if(insert == 0) {
			result = ServiceResult.FAIL;
		}else {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) {
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		// raw data
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		// logic -> information
//		Calendar cal = Calendar.getInstance();
//		String pattern = "%s, %tc";
//		for(ProdVO prod : prodList) {
//			String infoValue = String.format(pattern, prod.getProdLgu(), cal);
//			prod.setProdLgu(infoValue);
//		}
		
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		return prodDAO.selectProd(prodId);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		int update = prodDAO.updateProd(prod);
		ServiceResult result = null;
		if(update == 0) {
			result = ServiceResult.FAIL;
		}else {
			result = ServiceResult.OK;
		}
		return result;
	}

}
