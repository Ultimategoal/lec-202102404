package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.*;
import java.io.*;

// 단, context name : third
//service name : /imageView
// service name : /imageList - 이미지 목록 제공, 요청 파라미터명 : imageName

public class ImageServlet extends HttpServlet{
	ServletContext application;
	File contentsFolder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String contentsPath = application.getInitParameter("contentsPath");
		contentsFolder = new File(contentsPath);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String mime = "image/jpeg";
		resp.setContentType(mime);
		String imageName = req.getParameter("image");
		if(imageName == null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "요청이 없습니다.");
			return;
		}
		//String source = "d:/contents/"+imageName; //여기가 imageName파라미터
		
		File srcFile = new File(contentsFolder, imageName);
		FileInputStream fis = new FileInputStream(srcFile);
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer))!=-1){
			os.write(buffer, 0, pointer);
		}
		fis.close();
		os.close();
	}	
}

