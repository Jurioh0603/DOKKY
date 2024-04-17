<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ tablib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/writeStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 스터디 글작성</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>
<br>
<br>
<br>

<!-- 페이지소개 -->
<div class="container" style="margin-left:360px;">
	<div class="content">
		<h1 class="title">궁금증 해결하기</h1>
		<h2 class="user">DOKKY에서 당신의 궁금증을 해결해보세요.</h2>     
		<br>
		<br>
		<br>
	</div>

	<!-- 글쓰기 -->
	<div class="form-group row">
		<label for="inputTitle" class="col-sm-2 col-form-label"><strong>제목</strong></label>
		<div class="col-sm-10">
			<input type="text" name="title" class="form-text" id="inputTitle" value="${modReq.title}" />
			<c:if test="${errors.title}">제목을 입력하세요.</c:if>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputAuthor" class="col-sm-2 col-form-label"><strong>작성자</strong></label>
		<div class="col-sm-10">
			<input type="text" name="author" class="form-text" id="inputAuthor" />
		</div>
	</div>
	<div class="form-group row">
		<label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
		<div class="col-sm-10">
			<textarea cols="100" wrap="hard" name="content" class="form-text1" id="inputContent">${modReq.content}</textarea>
		</div>
	</div>

	<!-- 글 등록과 취소 -->
	<div class="form-group row">
		<label class="col-sm-2"></label> <!-- col-sm-2를 사용하여 제목과 내용의 컬럼을 맞춰줍니다. -->
		<div class="col-sm-10">
			<div class="button-container">
				<button type="button" class="custom-button">글 수정</button>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>