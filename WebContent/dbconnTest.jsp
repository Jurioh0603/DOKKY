<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = ConnectionProvider.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		String sql = "select bno, title from community";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int bno = rs.getInt(1);
			String title = rs.getString(2);
%>
			번호는 <%=bno %>, 제목은 <%=title %> <br/>
<%
			
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
%>
</body>
</html>