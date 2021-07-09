package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerDAO {

	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	public BuyerVO selectBuyerDetail(String buyerId);
	
	public int insertBuyer(BuyerVO buyerVO);
	
	public int buyerTotalRecord();
}
