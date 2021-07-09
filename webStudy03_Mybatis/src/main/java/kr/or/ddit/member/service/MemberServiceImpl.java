package kr.or.ddit.member.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.exceptions.PersistenceException;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

public class MemberServiceImpl implements MemberService {

	// singleton
	private static MemberService service;
	private MemberDAO dao;
	
	private MemberServiceImpl() {
		dao = MemberDAOImpl.getInstance();
	}
	
	public static MemberService getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	@Override
	public ServiceResult createMemeber(MemberVO member) {
		ServiceResult result = null;
		
		if(dao.selectMemberDetail(member.getMemId()) == null) {
			String plain = member.getMemPass();
			String encoded = EncryptUtils.encryptSha512Base64(plain);
			member.setMemPass(encoded);
			int rowcnt = dao.insertMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
//		
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		List<MemberVO> memList = dao.selectMemberList(pagingVO);
		return memList;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO member = dao.selectMemberDetail(mem_id);
		if(member == null) {
			throw new UserNotFoundException("존재하지 않는 회원");
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		
		Object authResult = authService.authenticate(member);
		ServiceResult result = null;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = (ServiceResult) authResult;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
//		MemberVO savedMember = retrieveMember(member.getMemId());
//		String savedPass = savedMember.getMemPass();
//		String inputPass = member.getMemPass();
		Object authResult = authService.authenticate(member);
		ServiceResult result = null;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.deleteMember(member.getMemId());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public int retrieveMemberCount(PagingVO pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

	@Override
	public List<ZiptbVO> listZip() {
		return dao.selectZip();
	}

	@Override
	public ZiptbVO detailZip(ZiptbVO ziptvVO) {
		return null;
	}
	

}
