<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- css 참조링크 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- main게시판 css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/dokkyCss/mainStyle.css?after">
<!-- 폰트설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - All That Developer</title>
</head>
<body>
<!-- header include -->
<%@ include file="../../headerFooter/header.jsp" %>
<!-- main content -->
<main>
	<!-- 배너 및 로그인 영역 -->
	<div class="banner-login-container">
	  <div class="container">
	    <div class="row">
	      <div class="col-md-8">
	        <!-- 배너 -->
	        <div class="banner">
	          <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	            <div class="carousel-indicators">
	              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	            </div>
	            <div class="carousel-inner">
	              <div class="carousel-item active">
	              <a href="#">
	                <img src="<%=request.getContextPath() %>/imgs/banner1.jpg" class="d-block" alt="banner1">
	              </a>
	              </div>
	              <div class="carousel-item">
	              <a href="#">
	                <img src="<%=request.getContextPath() %>/imgs/banner2.jpg" class="d-block" alt="banner2">
	              </a>
	              </div>
	              <div class="carousel-item">
	              <a href="#">
	                <img src="<%=request.getContextPath() %>/imgs/banner3.jpg" class="d-block" alt="banner3">
	              </a>
	              </div>
	            </div><!-- 캐러셀 넘기는 양쪽 버튼 -->
	            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
	              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	              <span class="visually-hidden">Previous</span>
	            </button>
	            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
	              <span class="carousel-control-next-icon" aria-hidden="true"></span>
	              <span class="visually-hidden">Next</span>
	            </button>
	          </div>
	        </div>
	      </div>
	      <div class="col-md-4">
	        <!-- 로그인 영역 -->
	       	<c:if test="${ empty authUser }">
		        <div class="login-box">
		          <div class="card text-center">
		            <div class="card-body">
		              <h6 class="card-title">DOKKY에 오신 것을 환영합니다!</h6>
		              <a href="/login.do" class="btn btn-primary login-button" style="background-color: #0090f9; border-color: #0090f9;"><span style="text">DOKKY</span> 로그인</a>
		              <div>
			              <p class="card-text">
			                <a href="/findId.do">아이디찾기</a> |
			                <a href="/findPwd.do">비밀번호찾기</a> |
			                <a href="#">회원가입</a>
			              </p>
		              </div>
		            </div>
		          </div>
		        </div>
	        </c:if>
	        
	        <!-- 로그인성공 후 영역 -->
	        <c:if test="${ ! empty authUser }">
	           <div class="login-box">
	             <div class="card text-center">
	               <div class="card-body">
	               <h6 class="card-title"><a href="#">${authUser.name}</a>님, 어서오세요!</h6>
	                 <c:if test="${authUser.grade eq 9999}">
	                 	<a href="/admin/memberList.do" class="btn btn-primary login-button" style="background-color: #0090f9; border-color: #0090f9; margin-bottom: 0px">홈페이지 관리</a>
	                 </c:if>
	                 <a href="/logout.do" class="btn btn-primary login-button" style="background-color: #0090f9; border-color: #0090f9;"><span style="text">DOKKY</span> 로그아웃</a>
	                 <div>
	                 </div>
	               </div>
	             </div>
	           </div>
	        </c:if>
	        
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 게시판 미리보기 영역 -->
	<div class="container mt-4 board-preview">
	  <!-- 게시글 1과 2를 묶는 섹션 -->
	  <section class="my-12 flex flex-col gap-x-10 gap-y-10 md:flex-row">
	    <div class="row mb-4">
	    <!-- Q&A게시판 영역 -->
	      <div class="col-md-6">
			<!-- top-banner영역 -->
			<div class="container">
			  <a href="<%=request.getContextPath() %>/view/board/qna/qnaSelect.jsp" class="main-square-box">
			    <div class="main-text">
			      <span>Q&A</span>
			    </div>
			    <img src="<%=request.getContextPath() %>/imgs/qna.svg" alt="SVG Icon" class="r-icon" style="width: auto; height: 68px;">
			  </a>
			</div>
			<!-- 게시글목록영역 -->
	        <ul>
	        	<c:forEach var="board" items="${boardList.qna}">
		          <li>${board.memid}<br/><a href="/qna/read.do?no=${board.bno}" class="title">${board.title}</a></li>
	        	</c:forEach>
	        </ul>
	      </div>
	      <!-- 자유게시판영역 -->
	      <div class="col-md-6">
	        <div class="container">
			  <a href="<%=request.getContextPath() %>/view/board/community/communitySelect.jsp" class="main-square-box">
			    <div class="main-text">
			      <span>자유게시판</span>
			    </div>
			    <img src="<%=request.getContextPath() %>/imgs/community.svg" alt="SVG Icon" class="r-icon" style="width: auto; height: 68px;">
			  </a>
			</div>
	        <ul>
	        	<c:forEach var="board" items="${boardList.community}">
		          <li>${board.memid}<br/><a href="/community/read.do?no=${board.bno}" class="title">${board.title}</a></li>
	        	</c:forEach>
	        </ul>
	      </div>
	      </div>
	  </section>
	
	  <!-- 게시글 3과 4를 묶는 섹션 -->
	  <section class="my-8 flex flex-col gap-x-10 gap-y-10 md:flex-row">
	    <div class="row mb-4">
	    <!-- 스터디게시판 영역 -->
	      <div class="col-md-6">
			<div class="container">
			  <a href="/study/list.do" class="main-square-box">
			    <div class="main-text">
			      <span>스터디모집</span>
			    </div>
			    <img src="<%=request.getContextPath() %>/imgs/study.svg" alt="SVG Icon" class="r-icon" style="width: auto; height: 68px;">
			  </a>
			</div>
	        <ul>
	        	<c:forEach var="board" items="${boardList.study}">
		          <li>${board.memid}<br/><a href="/study/read.do?no=${board.bno}" class="title">${board.title}</a></li>
	        	</c:forEach>
	        </ul>
	      </div>
	      <!-- 점심게시판 영역 -->
	      <div class="col-md-6">
	        <div class="container">
			  <a href="<%=request.getContextPath() %>/view/board/lunch/lunchSelect.jsp" class="main-square-box">
			    <div class="main-text">
			      <span>점메추</span>
			    </div>
			    <img src="<%=request.getContextPath() %>/imgs/lunch.svg" alt="SVG Icon" class="r-icon" style="width: auto; height: 68px;">
			  </a>
			</div>
	        <ul>
	        	<c:forEach var="board" items="${boardList.lunch}">
		          <li>${board.memid}<br/><a href="/lunch/read.do?no=${board.bno}" class="title">${board.title}</a></li>
	        	</c:forEach>
	        </ul>
	      </div>
	      </div>
	  </section>
	</div>
</main>

<!-- footer include -->
<%@ include file="../../headerFooter/footer.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


</body>
</html>
