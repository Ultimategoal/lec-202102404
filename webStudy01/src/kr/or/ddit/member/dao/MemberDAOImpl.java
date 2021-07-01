package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberById(String mem_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_id, mem_pass, mem_name from member where mem_id = ?");
//		String sql = "select mem_id, mem_pass, mem_name from member where mem_id = ? and mem_pass = ?";
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
//				PreparedStatement pstmt = conn.prepareStatement(sql);
			){	
				stmt.setString(1, mem_id);
				
//		 	5. 쿼리 실행
//				ResultSet rs = pstmt.executeQuery();
				ResultSet rs = stmt.executeQuery();
				MemberVO savedMember = null;
				if(rs.next()){
					savedMember = new MemberVO();
					savedMember.setMem_id(rs.getString("mem_id"));
					savedMember.setMem_pass(rs.getString("mem_pass"));
					savedMember.setMem_name(rs.getString("mem_name"));
				}
				rs.close();
				return savedMember;
			}catch (SQLException | NullPointerException e) {
				throw new RuntimeException(e);
			}
	}

}
