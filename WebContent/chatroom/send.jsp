<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.net.*"%>
<%@ page import="liaotianshi.SqlCon,model.User"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.Chatlog,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
	function checkMessage() {
		var content = document.form1.content.value;
		if (content.length == 0) {
			document.getElementById("d1").innerHTML = "消息内容不能为空！";
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body>

	<form name="form1" method="get" onsubmit="return checkMessage()"
		action="send.jsp">
		<textarea maxlength="32" rows="7" cols="100" name="content"
			placeholder="请输入聊天内容"></textarea>
		<div id="d1" style="color: red"></div>
		<input type="submit" name="send" value="发送">
	</form>

	<%
		//编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		//消息
		String content = request.getParameter("content");
		if (content != null) {
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			//时间
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = formatter.format(date);
			//用户昵称
			String nickname = null;
			nickname = ((User) session.getAttribute("User")).getNickname();
			
			/*Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("nickname")) {
						nickname = URLDecoder.decode(c.getValue(), "UTF-8");
						break;
					}
				}
			}*/

			Chatlog cl = new Chatlog();
			cl.setNickname(nickname);
			cl.setContent(content);
			cl.setTime(time);

			SqlCon sql = new SqlCon();
			sql.addChatlog(cl);
			sql.close();
		}
	%>
</body>

</html>