package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	String nickname;
	String login_time;
	String profile_path;

	public User(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formatter.format(date);
		this.login_time = time;
	}
	
	@Override
	public boolean equals(Object arg0) {
		User u = (User)arg0;
		return (this.nickname.equals(u.nickname));
	}
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.nickname.length();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = nickname + "    " + login_time;
		return str;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getLogin_time() {
		return login_time;
	}
	
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	
	public String getProfile_path() {
		return profile_path;
	}
	
	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HashSet<User> OnlineUser = new HashSet<User>();
//		User user1 = new User();
//		User user2 = new User();
//		User user3 = new User();
//		
//		user1.setNickname("A");
//		user2.setNickname("B");
//		user3.setNickname("A");
//		OnlineUser.add(user1);
//		OnlineUser.add(user2);
//
//		if(OnlineUser.contains(user3)) {
//			System.out.println("这个nickname已经被注册了");
//		}else {
//			System.out.println("这个nickname没被注册");
//		}
	}
}
