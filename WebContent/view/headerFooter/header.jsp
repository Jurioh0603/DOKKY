<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>header</title>
<style>
/*nav-bar 게시판 메뉴 마우스 올리면 파란색으로 변하는 hover적용*/
.nav-item a:hover {
  color: #0090f9 !important; 
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

</style>
</head>
<body>

<!-- 상단바 영역 -->
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-white nav-container ">
	  <div class="container">
	    <a href="landingPage.jsp" class="navbar-brand"> <!-- 로고 클릭 시 메인페이지 이동 -->
		<!-- 로고 이미지 경로 설정 주의하기! -->
	      <img src="<%=request.getContextPath() %>/imgs/dokkyLogo.png" alt="DOKKY Logo" width="100" height="auto">
	    </a>
		
		<!-- nav-bar에 게시판 메뉴명 클릭 시 각 게시판으로 이동 -->
	    <div class="collapse navbar-collapse justify-content-center" id="navbarNav" role="header">
	      <ul class="navbar-nav">
	        <li class="nav-item"><a href="#" class="nav-link px-4 text-black">Q&A</a></li>
	        <li class="nav-item"><a href="#" class="nav-link px-4 text-black">자유게시판</a></li>
	        <li class="nav-item"><a href="#" class="nav-link px-4 text-black">스터디모집</a></li>
	        <li class="nav-item"><a href="#" class="nav-link px-4 text-black">점메추</a></li>
	      </ul>
	    </div>
        
        <!-- 검색창 -->
        <form class="d-flex ">
          <div class="position-relative">
            <!-- search SVG 아이콘 클릭시 버튼 활성화 -->
            <button class="searchButton" >
            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="15" viewBox="0 0 14 15" fill="none" xmlns="http://www.w3.org/2000/svg" class="bi bi-search" style="position: absolute; left: 20px; top: 50%; transform: translateY(-50%);">
              <path d="M12.25 12.75L9.21841 9.71842M9.21841 9.71842C10.0389 8.89792 10.4999 7.78508 10.4999 6.62471C10.4999 5.46434 10.0389 4.3515 9.21841 3.531C8.39791 2.7105 7.28507 2.24955 6.12471 2.24955C4.96434 2.24955 3.8515 2.7105 3.031 3.531C2.2105 4.3515 1.74954 5.46434 1.74954 6.62471C1.74954 7.78508 2.2105 8.89792 3.031 9.71842C3.8515 10.5389 4.96434 10.9999 6.12471 10.9999C7.28507 10.9999 8.39791 10.5389 9.21841 9.71842Z" stroke="#6B7280" stroke-width="0.875" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            </button>
            <!-- 검색창 입력 필드 -->
            <input type="search" class="form-control rounded-pill  pl-6" placeholder="검색" aria-label="Search" style="padding-left: 25px; font-weight: 300;">
          </div>
        </form>
        
      </div> 
    </nav>
    <!-- 헤더와 메인영역 구분 선 -->
    <div style="border-bottom: 1px solid #ccc;"></div>
    <!-- 상단바 영역 끝 -->
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
