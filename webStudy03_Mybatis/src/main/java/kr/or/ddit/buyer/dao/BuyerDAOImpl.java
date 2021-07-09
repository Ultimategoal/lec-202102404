package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import oracle.sql.CustomDatumFactory;

public class BuyerDAOImpl implements BuyerDAO {
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectBuyerList(pagingVO);
		}
	}

	@Override
	public BuyerVO selectBuyerDetail(String buyerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBuyer(BuyerVO buyerVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buyerTotalRecord() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.buyerTotalRecord();
		}
	}

}
