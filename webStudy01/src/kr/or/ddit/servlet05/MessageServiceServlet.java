package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/messageService")
public class MessageServiceServlet extends HttpServlet{
	ResourceBundle bundle;
	Map<String,Object> dataMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String basename = config.getInitParameter("basename");
		bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message"); //베이스네임은 클래스패스 이후 경로부터 들어감
		dataMap = new HashMap<>();
		for(String key :bundle.keySet()) {
			dataMap.put(key,bundle.getObject(key));
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		**  mime 설정 : Content-Type
		String accept = req.getHeader("Accept");
		MimeType mime = MimeType.findMimeType(accept);
		
		if(!MimeType.JSON.equals(mime)) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		
//		1. marshalling
		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(dataMap);
//		2. 직렬화
	
		
		resp.setContentType(mime.getMimeText());
		
		try (
			PrintWriter out = resp.getWriter();
		){
//			out.println(json);
			mapper.writeValue(out, dataMap);
		}
		
	}
}
