package liaotianshi;

import java.sql.*;

import model.Chatlog;
import model.User;

public class SqlCon {
	Connection conn;

	public SqlCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败！");
		}
		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/chat?useSSL=false&"
							+ "user=naive&password=123456");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addChatlog(Chatlog cl) {
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement("insert into chatlog values(?,?,?)");
			pStmt.setString(1, cl.getNickname());
			pStmt.setString(2, cl.getContent());
			pStmt.setString(3, cl.getTime());
			pStmt.execute();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet retrieveChatlog(){
		ResultSet rs = null;
		try {
			PreparedStatement pStmt = conn.prepareStatement("select * from chatlog order by time");
			rs = pStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean checkNickname(String nickname){
		String str = "select * from user where name = '" + nickname +"'";
		System.out.println(str);
		try {
			PreparedStatement pStmt = conn.prepareStatement(str);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
//		SqlCon sql = new SqlCon();
//		if(sql.checkNickname("123")){
//			System.out.print("该昵称可用");
//		}else{
//			System.out.print("该昵称已被注册");
//		}
	}
}
