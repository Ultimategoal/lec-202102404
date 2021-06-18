package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/sessionExtend")
public class SessionExtend extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("Accept");
		
		MimeType mime = MimeType.findMimeType(accept);
		resp.setContentType(mime.getMimeText());
		
		String id = req.getParameter("session");
		System.out.println(id);
		
		
		HttpSession session = req.getSession();
		
		
		session.setMaxInactiveInterval(120);
		
	}
	
}
