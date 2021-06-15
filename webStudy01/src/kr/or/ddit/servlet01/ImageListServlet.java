package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageListServlet extends HttpServlet{
	String contentsPath;
	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		contentsPath = application.getInitParameter("contentsPath");
		System.out.printf("%s 서블릿 초기화됨\n", getClass().getName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		//응답데이터는 출력스트림 이전에 작성해야함
		
		File contentsFolder = new File(contentsPath);
		String[] imageList = contentsFolder.list(new FilenameFilter() {
			
			@Override // MIME
			public boolean accept(File dir, String name) {
				// main/sub;charset=encoding
				String mimeText = application.getMimeType(name);
				return mimeText != null && mimeText.startsWith("image/");
			}
		});
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(String name : imageList) {
			options.append(String.format(pattern, name));
		}
		
		InputStream is = getClass().getResourceAsStream("imageList.tmpl");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String tmp = null;
		StringBuffer tmplSource = new StringBuffer();
		while((tmp = reader.readLine()) != null) {
			tmplSource.append(tmp+"\n");
		}
		
		String html = tmplSource.toString().replace("#{data", options);
		
		resp.getWriter().println(html);
	}
}
