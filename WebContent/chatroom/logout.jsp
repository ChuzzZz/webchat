<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,java.util.HashSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
	User u = (User)session.getAttribute("User");
	HashSet<User> OnlineUser = (HashSet<User>)application.getAttribute("OnlineUser");
	OnlineUser.remove(u);
	application.setAttribute("OnlineUser", OnlineUser);
	session.invalidate();
%>
<script>
	window.opener=null;
	window.close();
</script>
</body>
</html>