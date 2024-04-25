<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>header</title>
<style>

@media (max-width: 768px) {
  .container {
    flex-direction: column;
    align-items: stretch;
  }
}  

.navbar{
	border-bottom: 1px solid #ccc;}
/*nav-bar 게시판 메뉴 마우스 올리면 파란색으로 변하는 hover적용*/
.nav-item a:hover {
  color: #0090f9 !important; 
}

body{
	padding-top:80px;
}

/* search svg icon 스타일 적용 */
.position-relative {
  display: flex;
  align-items: center;
}
/* 아이콘과 입력 필드 사이의 간격 조정 */
svg {
  margin-right: 3px; 
}
/*검색창 아이콘 버튼 안보이도록 스타일 적용 */
.searchButton {
	border: none; 
	background: none;
}
/*검색창 아이콘 마우스 올리면 파란색으로 변하는 hover효과 적용*/
.searchButton:hover svg path {
    stroke: #0090f9;
}
.login-btn:hover {
background-color: #f5f5f5;
}

</style>
</head>
<body>

<!-- 상단바 영역 -->
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-white nav-container fixed-top">
	  <div class="container"> <!-- 컨테이너 추가 -->
	    <a href="/main.do" class="navbar-brand"> <!-- 로고 클릭 시 메인페이지 이동 -->
	      <!-- 로고 이미지 경로 설정 주의하기! -->
	      <img src="<%=request.getContextPath() %>/imgs/dokkyLogo.png" alt="DOKKY Logo" width="100" height="auto">
	    </a>
		
		<!-- 토글메뉴 추가 -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
		
		<!-- nav-bar에 게시판 메뉴명 클릭 시 각 게시판으로 이동 -->
	    <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent" role="header">
	      <ul class="navbar-nav">
	        <li class="nav-item"><a href="/qna/list.do" class="nav-link px-4 text-black">Q&A</a></li>
	        <li class="nav-item"><a href="/community/list.do" class="nav-link px-4 text-black">자유게시판</a></li>
	        <li class="nav-item"><a href="/study/list.do" class="nav-link px-4 text-black">스터디모집</a></li>
	        <li class="nav-item"><a href="/lunch/list.do" class="nav-link px-4 text-black">점메추</a></li>
	      </ul>
	    
                <!-- 검색창 -->
		<form class="d-flex">
		  <div class="position-relative">
		    <!-- search SVG 아이콘 클릭시 버튼 활성화 -->
		    <button class="searchButton">
		      <svg xmlns="http://www.w3.org/2000/svg" width="14" height="15" viewBox="0 0 14 15" fill="none" xmlns="http://www.w3.org/2000/svg" class="bi bi-search" style="position: absolute; left: 20px; top: 50%; transform: translateY(-50%);">
		        <path d="M12.25 12.75L9.21841 9.71842M9.21841 9.71842C10.0389 8.89792 10.4999 7.78508 10.4999 6.62471C10.4999 5.46434 10.0389 4.3515 9.21841 3.531C8.39791 2.7105 7.28507 2.24955 6.12471 2.24955C4.96434 2.24955 3.8515 2.7105 3.031 3.531C2.2105 4.3515 1.74954 5.46434 1.74954 6.62471C1.74954 7.78508 2.2105 8.89792 3.031 9.71842C3.8515 10.5389 4.96434 10.9999 6.12471 10.9999C7.28507 10.9999 8.39791 10.5389 9.21841 9.71842Z" stroke="#6B7280" stroke-width="0.875" stroke-linecap="round" stroke-linejoin="round"/>
		      </svg>
		    </button>
		    <!-- 검색창 입력 필드 -->
		    <input type="search" class="form-control rounded-pill pl-6 query" placeholder="검색" aria-label="Search" style="padding-left: 25px; font-weight: 300;">
		  </div>
		</form>
		
		<div id="admin-btn" class="d-flex justify-content-between">
	     <c:if test="${ empty authUser }">
		<button type="button" class="login-btn rounded-pill d-flex justify-content-center align-items-center border border-gray-500 bg-white text-center text-sm px-4 " style="margin-left: 30px;"  onclick="location.href='/login.do'">
		  로그인
		</button>
		<button type="button" class="join-btn rounded-pill d-flex justify-content-center align-items-center border border-white text-center text-sm text-white px-4 " style="margin-left: 5px; background-color: #0090F9;" onclick="location.href='/join.do'">
		  회원가입
		</button>
		</c:if>
		
		<c:if test="${ !empty authUser }">          
		<button type="button" class="login-btn rounded-pill d-flex justify-content-center align-items-center border border-gray-500 bg-white text-center text-sm px-4 " style="margin-left: 25px;" onclick="location.href='/logout.do'">
		  로그아웃
		</button>
		<!-- 마이페이지 경로 설정하기 -->
		<button type="button" class="login-btn rounded-pill d-flex justify-content-center align-items-center text-center text-sm px-4 " style="margin-left: 5px; background-color: #0090F9; border: 1px solid #0090F9; color: white; " onclick="location.href='/myPage.do'">
		  마이페이지
		</button>
		</c:if>
		</div>
	  </div>	
    </nav>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
  document.querySelector('.navbar-toggler').addEventListener('click', function() {
    let navbarCollapse = document.querySelector('#navbarSupportedContent');
    if (navbarCollapse.classList.contains('show')) {
      navbarCollapse.classList.remove('show');
    } else {
      navbarCollapse.classList.add('show');
    }
  });

  let query = document.querySelector('.query');
  let searchButton = document.querySelector('.searchButton');
  
  searchButton.onclick = function(){
    let url = 'https://www.google.com/search?q='+query.value;
    window.open(url);
  }
});
</script>
</body>
</html>
