package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements BuyerService {

	BuyerDAO buyerDAO = new BuyerDAOImpl();
	
	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		return buyerList;
	}

	@Override
	public BuyerVO retrieveBuyerDetail(String buyerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBuyer(BuyerVO buyerVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
