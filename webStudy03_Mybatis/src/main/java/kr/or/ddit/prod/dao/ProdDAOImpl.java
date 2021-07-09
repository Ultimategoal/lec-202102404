package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	private static ProdDAO dao;

	public static ProdDAO getInstance() {
		if(dao == null) dao = new ProdDAOImpl();
		return dao;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class); 
			return mapper.selectProd(prodId);
		}
	}


	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			int rowcnt = mapper.insertProd(prod);
			sqlSession.commit();
			return rowcnt;
		}
	}


	@Override
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}


	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}


	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			int rowcnt = mapper.updateProd(prod);
			sqlSession.commit();
			return rowcnt;
		}
	}



}
