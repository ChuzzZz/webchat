package liaotianshi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class CheckName
 */
@WebServlet({ "/CheckName", "/checkname" })
public class CheckName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		
		String nickname = request.getParameter("nickname");
		nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
//		nickname = URLDecoder.decode(nickname, "UTF-8");
		User user = new User();
		user.setNickname(nickname);
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		HashSet<User> OnlineUser = (HashSet<User>)application.getAttribute("OnlineUser");
//		SqlCon sql = new SqlCon();
//		if(sql.checkNickname(nickname)){
//			out.print("该昵称可用");
//		}else{
//			out.print("该昵称已被注册");
//		}
		if(OnlineUser == null) {
			out.print("该昵称可用");
		}else {
			if(OnlineUser.contains(user)) {
				out.print("该昵称已被注册");
			}else {
				out.print("该昵称可用");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
