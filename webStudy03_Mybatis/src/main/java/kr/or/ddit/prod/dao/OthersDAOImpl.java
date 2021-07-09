package kr.or.ddit.prod.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fasterxml.jackson.databind.cfg.MapperConfig;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;

public class OthersDAOImpl implements OthersDAO {
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); 
		){
			OthersDAO mapper = sqlSession.getMapper(OthersDAO.class);
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); 
		){
			OthersDAO mapper = sqlSession.getMapper(OthersDAO.class);
			return mapper.selectBuyerList();
		}
	}

}
