<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.net.*,java.util.HashSet,model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="60">
<title></title>
<script>
function logout(){
if (confirm("你确定要离开聊天室吗？")){
	window.opener=null;
	window.parent.open('logout.jsp','_self');
	}
}
</script>
</head>
<body>
	<h3>欢迎来到泰达首家线上聊天室</h3>
	<hr>
	<%
		/* Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("nickname")) {
					out.print("<h3>"+URLDecoder.decode(c.getValue(),"UTF-8")+"来到泰达首家线上聊天室</h3>");
					break;
				}
			}
		} */
		User user = (User)session.getAttribute("User");
		String nickname = user.getNickname();
		out.print(nickname);
	%>
	<img src='<jsp:attribute name=""></jsp:attribute>'>
	<input type="button" value="注销" onclick="logout()">
	<h4 style="color:blue">正在学习的人数：<span>
	<%
		int count = ((Integer)application.getAttribute("OnlineCount"));
		out.print(count);
	%>
	</span></h4>
	
	<h4 style="color:red">聊天室在线用户：</h4>
	<%
		HashSet<User> OnlineUser = (HashSet<User>)application.getAttribute("OnlineUser");
		if(OnlineUser != null){
			for(User u: OnlineUser){
				out.println(u);
			}
		}
	%>
</body>
</html>