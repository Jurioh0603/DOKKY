<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/communitySelectStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 자유게시판 글목록</title>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var sort = '<%= request.getParameter("sort") %>';
        var dropdownText = document.getElementById('dropdownText');

        switch(sort) {
            case 'bno':
                dropdownText.textContent = '최신순';
                break;
            case 'hit':
                dropdownText.textContent = '조회순';
                break;
            case 'replyCount':
                dropdownText.textContent = '댓글순';
                break;
            default:
                dropdownText.textContent = '최신순'; // 기본값 설정
        }
    });
</script>

</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>
<br>
<br>
<br>

<!-- 페이지소개 -->
<div class="container">
	<div class="square-box">
		<p class="main-text">자유게시판</p>
		<p class="sub-text">다양한 주제로 함께하는 소통의 공간, 커뮤니티 게시판입니다.</p>
		<img src="<%=request.getContextPath() %>/imgs/community.svg" alt="SVG Icon" class="r-icon" >
	</div>

	<!-- 글작성버튼 -->
	<div class="button-container">
		<button class="custom-button" onclick="location.href='/community/write.do'">
			<span><img src="<%=request.getContextPath() %>/imgs/write-icon.png" alt="write-icon"></span>
			<span>작성하기</span>
		</button>
	</div>

	<!-- 정렬바 -->
	<div class="dropdown" style="float:right;">
	    <button class="dropbtn" id="dropdownButton">
	        <img src="<%=request.getContextPath() %>/imgs/select-icon.png" alt="select-icon">
	        <span id="dropdownText"></span>
	    </button>
	    <div class="dropdown-content">
	        <a href="/community/list.do?&search=${search}&sort=bno">최신순</a>
	        <a href="/community/list.do?&search=${search}&sort=hit">조회순</a>
	        <a href="/community/list.do?&search=${search}&sort=replyCount">댓글순</a>
	    </div>
	</div>

	<!-- 검색창 -->
    <div style="display: grid; place-items: center; text-align: center;">
       <form class="search-box" action="/community/list.do" method="get" >
           <input class="search-txt" type="text" name="search" value="${search}" placeholder="검색어를 입력하세요."/>
           <input type="hidden" name="sort" value="${sort}"/>
           <button class="search-btn" type="submit" title="검색">
               <img src="<%=request.getContextPath() %>/imgs/search-icon.png" alt="검색" style="width: 20px;" />
           </button>
       </form>
    </div>
    <div style="clear:both;"></div>
    
	<!-- 글 목록 -->
	<ul class="bordered-list">
		<c:if test="${communityPage.hasNoContents()}">
			<li>게시글이 존재하지 않습니다.</li>
		</c:if>
		<c:forEach var="communityItem" items="${communityPage.communityList }">
			<li>
	  			<div class="content">
	    			<div class="user">${communityItem.memId}</div>
	    			<div class="title"><a href="/community/read.do?no=${communityItem.bno}">${communityItem.title}</a></div>
	    			<div class="dateHit">${communityItem.formattedRegDateSel}&nbsp;&nbsp;&nbsp;<i class="bi bi-eye" style="margin-right: 3px;"></i>${communityItem.hit}</div>
	  			</div>
			</li>
		</c:forEach>
	</ul>

	<hr>

	<!-- 페이지네이션 -->
	<c:if test="${communityPage.hasContents()}">
		<div class="pagination-container">
  			<div class="pagination">
  				<c:if test="${communityPage.startPage > 5}">
     				<a href="/community/list.do?pageNo=${communityPage.startPage - 5}&search=${search}&sort=${sort}">&laquo;</a>
     			</c:if>
     			<c:forEach var="pNo" begin="${communityPage.startPage}" end="${communityPage.endPage}">
     				<c:if test="${pNo eq communityPage.getCurrentPage()}">
     					<a href="/community/list.do?&pageNo=${pNo}&search=${search}&sort=${sort}" class="active">${pNo}</a>
      				</c:if>
     				<c:if test="${pNo ne communityPage.getCurrentPage()}">
     					<a href="/community/list.do?&pageNo=${pNo}&search=${search}&sort=${sort}">${pNo}</a>
      				</c:if>
		       </c:forEach>
		       <c:if test="${communityPage.endPage < communityPage.totalPages}">
		       	<a href="/community/list.do?pageNo=${communityPage.startPage + 5}&search=${search}&sort=${sort}">&raquo;</a>
		       </c:if>
 			</div>
		</div>
	</c:if>
</div>
<br>
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
