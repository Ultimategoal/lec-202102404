package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

public class MemberDAOImpl implements MemberDAO {
	// singleton
	private static MemberDAOImpl dao;
	
	public static MemberDAOImpl getInstance() {
		if(dao == null) dao = new MemberDAOImpl();
		return dao;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberById(String mem_id) {
		try(
		// 여기서 세션은 커넥션과 같은 용도로 쓰임
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return (MemberVO)sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberById", mem_id);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.insertMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	
	@Override
	public int selectTotalRecord(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class); 
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
//		StringBuffer sql = new StringBuffer();
////		sql.append("SELECT mem_id, mem_pass, mem_name, mem_regno1, mem_regno2, mem_bir, mem_zip ");
////		sql.append(" mem_add1, mem_add2, mem_hometel, mem_comtel, mem_hp, mem_mail, mem_job, mem_like ");
////		sql.append(" mem_memorial, mem_memorialday, mem_mileage, mem_delete ");
////		sql.append(" from member ");
//		
//		sql.append("SELECT mem_id,  mem_name, ");
//		sql.append(" mem_add1,   mem_hp, mem_mail,  ");
//		sql.append("  mem_mileage, mem_delete ");
//		sql.append(" from member ");
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList", pagingVO);
		}
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			proxy 객체 생성, 대리자 역할은 한다고 보면 됨, mapper에 있는 namespace에 따라서 찾을 수 있음
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			System.out.println(mapper.getClass());
			return mapper.selectMemberDetail(mem_id);
		}
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT mem_id, mem_pass, mem_name, mem_regno1, mem_regno2, mem_bir, mem_zip, mem_add1, mem_add2, mem_hp, "); 
//		sql.append(" mem_comtel, mem_hometel, mem_memorial, mem_memorialday, ");
//		sql.append(" mem_mail, mem_job, mem_like, mem_mileage, mem_delete from member where mem_id = ? ");
//		
//		try(
//			Connection conn = ConnectionFactory.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//		){
//			pstmt.setString(1, mem_id);
//			ResultSet rs = pstmt.executeQuery();
//			MemberVO member = null;
//			if(rs.next()) {
//				member = MemberVO.builder()
//								.mem_id(rs.getString("mem_id"))
//								.mem_pass(rs.getString("mem_pass"))
//								.mem_name(rs.getString("mem_name"))
//								.mem_regno1(rs.getString("mem_regno1"))
//								.mem_regno2(rs.getString("mem_regno2"))
//								.mem_bir(rs.getString("mem_bir"))
//								.mem_comtel(rs.getString("mem_comtel"))
//								.mem_hometel(rs.getString("mem_hometel"))
//								.mem_memorial(rs.getString("mem_memorial"))
//								.mem_memorialday(rs.getString("mem_memorialday"))
//								.mem_zip(rs.getString("mem_zip"))
//								.mem_add1(rs.getString("mem_add1"))
//								.mem_add2(rs.getString("mem_add2"))
//								.mem_hp(rs.getString("mem_hp"))
//								.mem_mail(rs.getString("mem_mail"))
//								.mem_job(rs.getString("mem_job"))
//								.mem_like(rs.getString("mem_like"))
//								.mem_mileage(rs.getInt("mem_mileage"))
//								.mem_delete(rs.getString("mem_delete"))
//								.build();
//			}
//			rs.close();
//			return member;
//		}catch(SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class); 
			int rowcnt = mapper.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class); 
			int rowcnt = mapper.deleteMember(mem_id);
			sqlSession.commit();
			return rowcnt;
		}
	}

	@Override
	public int selectCount(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectCount(mem_id);
		}
	}

	@Override
	public List<ZiptbVO> selectZip() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectZip();
		}
	}

	@Override
	public ZiptbVO selectZipDetail(ZiptbVO ziptbVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
				return mapper.selectZipDetail(ziptbVO);
			}
	}

}
