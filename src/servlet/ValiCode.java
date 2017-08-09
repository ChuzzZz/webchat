package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValiCode
 */
@WebServlet({ "/ValiCode", "/valicode" })
public class ValiCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		response.addHeader("Cache_Control",	 "no-cache");
		
		BufferedImage img  = new BufferedImage(80, 32, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics g = img.getGraphics();
		Random r  = new Random();
		
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int answer = a * b;
		HttpSession session = request.getSession();
		session.setAttribute("answer", answer);
		
		int R = r.nextInt(256);
		int G = r.nextInt(256);
		int B = r.nextInt(256);
		g.setFont(new Font("ËÎÌו",Font.BOLD,24));
//		System.out.println(answer);
		for(int i = 0; i<10; i++) {
			g.setColor(new Color(R,G,B));
			g.drawLine(r.nextInt(80), r.nextInt(32), r.nextInt(80), r.nextInt(32));
		}
		g.drawString(a + "*" + b + "=", 20, 20);
		ImageIO.write(img, "jpeg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
