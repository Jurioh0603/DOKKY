<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

글이 수정되었습니다.
<br>
${ctxPath = pageContext.request.contextPath;"}
<a href="${ctxPath}/study/list.do">[게시글내용보기]</a>
<a href="${ctxPath}/study/read.do?no=${modReq.studyNumber}">[게시글내용보기]</a>
</body>
</html>