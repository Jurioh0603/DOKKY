<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="../../../css/dokkyCss/lunchDetailStyle.css"> <!-- 스타일 시트 링크 -->
  	  	  	  	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
  	  	
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 점심메뉴추천 글보기</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>

<div class="container">
<br>
<br>
<br>
	<div class="square-box">
		<p class="main-text">점메추</p>
		<p class="sub-text">학원 근처 맛집을 소개해주세요.</p>
		<img src="<%=request.getContextPath() %>/imgs/lunch.svg" alt="SVG Icon" class="r-icon">
	</div>
	<hr style="clear:both;"/>
	
	<!-- 글 보기 -->
	<h1 class="logo">게시글 제목</h1>
	<main class="main">
		<div class="post">
			<p class="post-content">게시글 내용이 여기에 들어갑니다.</p>
		</div>
		<br>
		<br>
		<br>
	</main>
	<hr style="clear:both;"/>
    
    <!-- 댓글 -->
	<div class="comment-form">
		<form action="#" method="POST">
			<div class="form-group">
				<label for="comment">댓글</label>
				<textarea id="comment" name="comment" rows="4" required></textarea>
			</div>
			<div class="form-group row">
				<div class="button-container" style="margin-bottom:15px;">
					<button type="button" class="custom-button">댓글작성</button>
				</div>
			</div>
		</form>
	</div>
	<br/>
	<br/>
	<br/>
	<div>
		<button class="next">이전글</button>
		<button class="next" style="float: right;">다음글</button>
	</div>
</div>  
     
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>