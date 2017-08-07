package liaotianshi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet({ "/RegisterUser", "/registeruser" })
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out = response.getWriter();

		String nickname = request.getParameter("nickname");
		nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");

		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		String profile_path = (String)session.getAttribute("profile_path");
		Integer answer = (Integer) (session.getAttribute("answer"));

		if (Integer.parseInt(code) == answer.intValue()) {
			// SqlCon db = new SqlCon();
			// PreparedStatement pStmt;
			// try {
			// pStmt = db.conn.prepareStatement("insert into user values(?)");
			// pStmt.setString(1, nickname);
			// if (!pStmt.execute()) {
			// Cookie cookie = new Cookie("nickname", URLEncoder.encode(nickname, "UTF-8"));
			// cookie.setMaxAge(60 * 60 * 24 * 7);
			// response.addCookie(cookie);
			//
			// session.setAttribute("nickname", nickname);
			// response.sendRedirect("/webchat/chatroom/frame.html");
			// pStmt.close();
			// }
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// db.close();
			// out.println("该昵称已被他人注册，网站将于三秒后返回登录界面");
			// out.println("<a href='login.html'>立即返回</a>");
			// response.addHeader("refresh", "3;url=login.html");
			// e.printStackTrace();
			// }
			// db.close();
			if (nickname != null) {
				User user = new User();
				user.setNickname(nickname);
				user.setProfile_path(profile_path);
				System.out.println(user.getLogin_time());
				HashSet<User> OnlineUser = (HashSet<User>) application.getAttribute("OnlineUser");
				
				if (application.getAttribute("OnlineUser") == null) {
					OnlineUser = new HashSet<User>();
					OnlineUser.add(user);
					application.setAttribute("OnlineUser", OnlineUser);
					session.setAttribute("User", user);
					System.out.println(user.getNickname() + "注册了！");
					response.sendRedirect("/webchat/chatroom/frame.html");
				} else {
					if (!OnlineUser.contains(user)) {
						OnlineUser.add(user);
						application.setAttribute("OnlineUser", OnlineUser);
						session.setAttribute("User", user);
						System.out.println(user.getNickname() + "注册了！");
						response.sendRedirect("/webchat/chatroom/frame.html");
					}else {
						System.out.println("该昵称已经有人注册过了");
					}
				}
			}
		} else {
			Random r = new Random();
			response.sendRedirect("login.html?" + r.nextInt(20));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
