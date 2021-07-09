package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 *
 */
public interface MemberDAO {
	/**
	 * 회원가입
	 * @param member
	 * @return 성공 : > 0
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 페이징 처리를 위해 total record 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO pagingVO);
	
	/**
	 * 페이징 처리를 위해 구간별 데이터를 조회
	 * 회원 전체 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	/**
	 * 회원 상세 조회
	 * @param mem_id
	 * @return 성공 : not null, 실패 : null
	 */
	public MemberVO selectMemberDetail(String mem_id);
	/**
	 * 식별자(PK) 로 레코드 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMemberById(String mem_id);
	
	/**
	 * 회원 수정
	 * @param member
	 * @return
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 삭제
	 * @param mem_id
	 * @return
	 */
	public int deleteMember(String mem_id);
	
	public int selectCount(String mem_id);
	
	public List<ZiptbVO> selectZip();
	
	public ZiptbVO selectZipDetail(ZiptbVO ziptbVO);
	
}
