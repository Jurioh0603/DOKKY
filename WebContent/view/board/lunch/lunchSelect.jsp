<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/lunchSelectStyle.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 점심메뉴추천</title>

</head>
<body>
<%@ include file="../../headerFooter/header.jsp" %>

<!-- 사이드여백 -->
<div class="container">
	<div class="gallery-container">
		<br/>
		<br/>
		<br/>
	
		<!-- 게시글베너 -->
		<div class="square-box">
  			<p class="main-text">점메추</p>
  			<p class="sub-text">학원 근처 맛집을 소개해주세요.</p>
			<img src="<%=request.getContextPath() %>/imgs/lunch.svg" alt="SVG Icon" class="r-icon">
		</div>
		
		<!-- 글작성 -->
		<div class="button-container">
	  		<button class="custom-button" onclick = "location.href='/lunch/write.do'">
	    		<span><img src="<%=request.getContextPath() %>/imgs/writeIcon.png" alt="write-icon"></span>
	    		<span>작성하기</span>
	  		</button>
		</div>
	
		<!-- 정렬바 -->
		<div class="dropdown" style="float:right;">
	  		<button class="dropbtn">
				<img src="<%=request.getContextPath() %>/imgs/selectIcon.png" alt="select-icon">최신순
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
				<input class="search-btn" type="image" src="<%=request.getContextPath() %>/imgs/searchIcon.png" title="search-icon"/>
			</form>
		</div>
		<hr style="clear:both;"/>
		
		<c:if test="${lunchPage.hasNoContents()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if> 
		
		<!-- 1 -->
		<form name="lunchList" method="post" action="/lunch/lunchList.do" >
			<c:forEach var="lunchItem" items="${lunchPage.lunchList}">
	    		<div class="gallery-item-box-col-321">
	        		<div class="gallery-item-img">
	           			 <a target="_blank" href="/lunch/read.do?no=${lunchItem.bno}">
	               			<img src="${pageContext.request.contextPath}/upload/${lunchItem.filerealname}" alt="${lunchItem.title}" width="600" height="400">
	            		</a>
	        		</div>
	        		<div class="gallery-title">
	            		<a style="text-decoration:none; color:black;" href="/lunch/read.do?no=${lunchItem.bno}" title="${lunchItem.title}">
	                		<span>${lunchItem.title}</span>
	            		</a>
	        		</div>
	        		<div class="gallery-date">
	            		<span>${lunchItem.regdate}</span> | <span>${lunchItem.hit}</span>
	        		</div>
	        		<div class="gallery-name">
	            		<span>${lunchItem.memid}</span>
	        		</div>
	    		</div>
			</c:forEach>
		</form>
		<div class="clearfix"></div>
		<br/>
		<br/>
		<hr/>
		<!-- 페이지네이션 -->
		<c:if test="${lunchPage.hasContents() }">
			<div class="pagination-container">
    			<div class="pagination" style="margin-top:-50px">
					<c:if test="${lunchPage.startPage > 5}">
						<a href="/lunch/list.do?pageNo=${lunchPage.startPage - 5}">&laquo;</a>
					</c:if>
					<c:forEach var="pNo" begin="${lunchPage.startPage }" end="${lunchPage.endPage }">
						<c:if test="${pNo eq lunchPage.getCurrentPage()}">
							<a href="lunch/list.do?pageNo=${pNo}" class="active">${pNo}</a>
						</c:if>
						<c:if test="${pNo ne lunchPage.getCurrentPage()}">
							<a href="/lunch/list.do?pageNo=${pNo}">${pNo}</a>
						</c:if>
					</c:forEach>
					<c:if test="${lunchPage.endPage < lunchPage.totalPages}">
						<a href="/lunch/list.do?pageNo=${lunchPage.startPage + 5}">&raquo;</a>
					</c:if>
				</div>
			</div>
		</c:if>
		<br/>
		<br/>
		<br/>
				
		<!-- footer -->
	</div>
</div>
<%@ include file="../../headerFooter/footer.jsp" %>

<!-- Scripts -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="app.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>	
</body>
</html>