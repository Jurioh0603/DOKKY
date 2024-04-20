<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="pageNo" value="${empty modReq.pageNumber ? '1' : modReq.pageNumber}" />
<meta http-equiv="refresh" content="0; url=/board/study/list.do?pageNo=${pageNo}"></meta>
<title>글 삭제</title>
<script>
	alert('글 삭제 성공!');
	// 확인을 클릭하면 목록 페이지로 바로 이동
    window.location.href = '/study/list.do';
</script>
</head>
<body>

</body>
</html>