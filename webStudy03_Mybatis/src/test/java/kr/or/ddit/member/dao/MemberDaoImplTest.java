package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.emp.dao.EmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImplTest {

	MemberDAO dao;
	Map<String, Object> pMap;
	
	@Before
	public void setUp() throws Exception {
		dao = MemberDAOImpl.getInstance();
		pMap = new HashMap<>();
	}

	@Test
	public void testSelectMemberById() {
		MemberVO vo = dao.selectMemberById("a001");
		System.out.println(vo);
		assertNotNull(vo);
//		List<EmployeeVO> empList = empDAO.selectEmployeeList(pMap);
//		assertNotNull(empList);
//		assertEquals(1, empList.size());
//		assertEquals(false, empList.get(0).isLeaf());
//		System.out.println(empList.get(0));
	}
	
	@Test
	public void testSelectMemberList() {
		List<MemberVO> list = dao.selectMemberList(pagingVO);
		assertNotNull(list);
		assertNotEquals(1, list.size());
	}
	
	@Test
	public void testSelectMemberDetail() {
		MemberVO vo = dao.selectMemberDetail("a001");
		assertNotNull(vo);
	}
	
	@Test
	public void testUpdateMember() {
		MemberVO member = dao.selectMemberDetail("a001");
		member.setMemName("김은대");
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);
	}
}
