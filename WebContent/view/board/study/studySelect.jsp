<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   <link rel="stylesheet" href="../../../css/dokkyCss/select.css"> <!-- 스타일 시트 링크 -->
  	  	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
  	  	<link href="../../../imgs/DokkyLogo.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - All That Developer</title>
</head>
<body>
  <%@ include file="../../headerFooter/header.jsp" %>
  <br>
  	<br>
  	<br>
  <div class="container">
  <div class="square-box">
           <p class="main-text">Study</p>
           <p class="sub-text">좋은 질문과 답변으로 동료의 시간을 아껴주세요.</p>
             <img src="../../../imgs/QA.svg" alt="SVG Icon" class="r-icon" >
      </div>
      
      <div class="button-container">
           <button class="custom-button">
             <span><img src="<%=request.getContextPath() %>/imgs/write-icon.png" alt="write-icon"></span>
             <span>작성하기</span>
           </button>
      </div>
   
      <!-- 정렬바 -->
      <div class="dropdown" style="float:right;">
           <button class="dropbtn">
            <img src="<%=request.getContextPath() %>/imgs/select-icon.png" alt="select-icon">최신순
         </button>
           <div class="dropdown-content">
              <a href="#">최신순</a>
              <a href="#">추천순</a>
              <a href="#">조회순</a>
              <a href="#">댓글순</a>
           </div>
      </div>
      
      <!-- 검색창 -->
      <div style="display: grid; place-items: center; text-align: center;">
         <form class="search-box" action="" method="get" >
            <input class="search-txt" type="text" name="" placeholder="검색어를 입력하세요."/>
            <input class="search-btn" type="image" src="<%=request.getContextPath() %>/imgs/search-icon.png" title="search-icon"/>
         </form>
      </div>
      <hr style="clear:both;"/>
      


     
     <ul class="bordered-list">
  <li>
    <div class="content">
      <div class="user">사용자 이름</div>
      <div class="title">글 제목</div>
      <div class="date">날짜</div>
    </div>
  </li>
  <li>
    <div class="content">
      <div class="user">사용자 이름</div>
      <div class="title">글 제목</div>
      <div class="date">날짜</div>
    </div>
  </li>
  <li>
    <div class="content">
      <div class="user">사용자 이름</div>
      <div class="title">글 제목</div>
      <div class="date">날짜</div>
    </div>
  </li>
</ul>


        
	
	
   	<hr>
   	<br>
   	<br>
   	<br>

    <div class="pagination-container">
          <div class="pagination" style="margin-top:-50px">
             <a href="#">&laquo;</a>
              <a href="#">1</a>
              <a href="#" class="active">2</a>
              <a href="#">3</a>
              <a href="#">4</a>
              <a href="#">5</a>
              <a href="#">6</a>
              <a href="#">&raquo;</a>
         </div>
      </div>
      </div>
      <br>
      <br>
      <br>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%@ include file="../../headerFooter/footer.jsp" %>

</body>
</html>
