package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectTotalRecord(PagingVO<DataBasePropertyVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			DataBasePropertyDAO mapper = sqlSession.getMapper(DataBasePropertyDAO.class); 
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(PagingVO<DataBasePropertyVO> pagingVO) {
		{
			try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				DataBasePropertyDAO mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
				return mapper.selectDataBasePropertyList(pagingVO);
			}
		}
	}
//		StringBuffer sql = new StringBuffer();
//		sql.append("select property_name, property_value, description ");
//		sql.append("from database_properties ");
//		if(param!=null) {
//			
//			StringBuffer searchSql = new StringBuffer();
//			if(StringUtils.isNotBlank(param.getProperty_name())) {
//				searchSql.append(" OR INSTR(PROPERTY_NAME, ?) > 0");
//			}
//			if(StringUtils.isNotBlank(param.getProperty_value())) {
//				searchSql.append(" OR INSTR(PROPERTY_VALUE, ?) > 0");
//			}
//			if(StringUtils.isNotBlank(param.getDescription())) {
//				searchSql.append(" OR INSTR(DESCRIPTION, ?) > 0");
//			}
//			
//			int index = -1;
//			if((index=searchSql.indexOf("OR"))!=-1) {
//				searchSql.delete(index, index+2);
//				sql.append("WHERE ");
//			}
//			sql.append(searchSql.toString());
//		}
//		
//		System.out.println(sql);
//		try(
//			Connection conn = ConnectionFactory.getConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql.toString());
//				
//			){	
//			
//			if(param!=null) {
//				int idx = 1;
//				if(StringUtils.isNotBlank(param.getProperty_name())) {
//					stmt.setString(idx++, param.getProperty_name());
//				}
//				if(StringUtils.isNotBlank(param.getProperty_value())) {
//					stmt.setString(idx++, param.getProperty_value());
//				}
//				if(StringUtils.isNotBlank(param.getDescription())) {
//					stmt.setString(idx++, param.getDescription());
//				}
//			}
////		 	5. 쿼리 실행
//			ResultSet rs = stmt.executeQuery();
//			List<DataBasePropertyVO> propList = new ArrayList<>();
//			while(rs.next()){
//				DataBasePropertyVO vo = new DataBasePropertyVO(); 
//				vo.setProperty_name(rs.getString("property_name"));
//				vo.setProperty_value(rs.getString("property_value"));
//				vo.setDescription(rs.getString("description"));
//				propList.add(vo);
//			}
//			rs.close();
//			return propList;
//		}catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
