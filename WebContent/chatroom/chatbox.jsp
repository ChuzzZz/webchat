<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="liaotianshi.SqlCon" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>

<body>
<%
response.setIntHeader("refresh",5);

String nickname;
String content;
String time;
SqlCon sc = new SqlCon();
ResultSet rs = sc.retrieveChatlog();
while(rs.next()){
	nickname = rs.getString(1);
	content = rs.getString(2);
	time = rs.getString(3);
	out.print("<p style='color:red'>" + time + "</p>");
 	out.println(nickname + " : " + content);
}
rs.close();
sc.close();
%>
</body>

</html>