package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD) Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 *  신규 상품 등록
	 * @param prod PK를 제외한 나머지 상품 데이터를 가진 VO
	 * @return rowcnt > 0 성공, PK는 call by refernece 로 확인.
	 */
	public int insertProd(ProdVO prod);
	
	/**
	 * 페이징 처리를 위한 토탈 레코드 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	/**
	 * @param pagingVO 페이징 처리를 위해 PagingVO를 받음
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 상품 상세 조회(구매자 목록 동시 조회)
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	
	public int updateProd(ProdVO prod);
	
}
