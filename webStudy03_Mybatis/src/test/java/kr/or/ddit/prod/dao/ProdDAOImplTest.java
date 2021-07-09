package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {

	private ProdDAO dao;
	Logger logger = LoggerFactory.getLogger(ProdDAOImplTest.class);
	
	@Before
	public void setUp() throws Exception {
		dao = ProdDAOImpl.getInstance();
	}

	@Test
	public void testSelectProd() {
		ProdVO prod =  dao.selectProd("P101000001");
		assertNotNull(prod);
		if(logger.isInfoEnabled()) {
			logger.info("Prod : {}", prod);
		}
		assertNotNull(prod);
		if(logger.isInfoEnabled()) {
			logger.info("Buyer : {}", prod.getBuyer());
		}
		assertNotNull(prod);
		if(logger.isInfoEnabled()) {
			logger.info("MemberList : {}", prod.getMemberList());
		}
//		assertNotNull(prod.getBuyer());
//		assertEquals(2, prod.getMemberList().size());
	}

}
