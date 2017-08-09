package servlet;

import java.io.*;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class Upload
 */
@WebServlet({ "/Upload", "/upload" })
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
//			System.out.println(System.getProperty("java.io.tmpdir"));
//			System.out.println(File.separator);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			factory.setSizeThreshold(1024*1024*10);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			//目录不存在则创建
//			File uploadDir = new File(uploadPath);
//			if(!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
			
			try {
				List<FileItem> items = upload.parseRequest(request);
				
				for(int i = 0; i<items.size();i++) {
					FileItem item = items.get(i);
					String name = item.getName();
					
					String path = "D:\\temp\\" + name;
					item.write(new File(path));
					session.setAttribute("profile_path", path);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
