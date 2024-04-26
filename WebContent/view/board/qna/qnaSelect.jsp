<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/studySelectStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 스터디게시판 글목록</title>

<script>
	document.addEventListener('DOMContentLoaded', function(){
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
				dropdownText.textContent = '최신순'; //기본값설정
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
		<p class="main-text">Q&A</p>
		<p class="sub-text">좋은 질문과 답변으로 동료들의 시간을 아껴주세요.</p>
		<img src="<%=request.getContextPath() %>/imgs/study.svg" alt="SVG Icon" class="r-icon" >
	</div>

	<!-- 글작성버튼 -->
	<div class="button-container">
		<button class="custom-button" onclick="location.href='/qna/write.do'">
			<span><img src="<%=request.getContextPath() %>/imgs/write-icon.png" alt="write-icon"></span>
			<span>작성하기</span>
		</button>
	</div>


	<!-- 정렬바 -->
	<div class="dropdown" style="float:right;">
		<button class="dropbtn" id="dopdownButton">
			<img src="<%=request.getContextPath() %>/imgs/select-icon.png" alt="select-icon">
			<span id="dropdownText"></span>
		</button>
		<div class="dropdown-content">
   			<a href="/qna/list.do?&search=${search}&sort=bno">최신순</a>
   			<a href="/qna/list.do?&p&search=${search}&sort=hit">조회순</a>
   			<a href="/qna/list.do?&&search=${search}&sort=replyCount">댓글순</a>
		</div>
	</div>

	<!-- 검색창 -->
	   <div style="display: grid; place-items: center; text-align: center;">
         <form class="search-box" action="/qna/list.do" method="get" >
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
		<c:if test="${qnaPage.hasNoContents()}">
			<li>게시글을 작성해 주세요.</li>
		</c:if>
		<c:forEach var="qnaItem" items="${qnaPage.qnaList }">
			<li>
	  			<div class="content">
	    			<div class="user">${qnaItem.memId}</div>
	    			<div class="title"><a href="/qna/read.do?no=${qnaItem.bno}">${qnaItem.title}</a></div>
	    			<div class="dateHit">${qnaItem.formattedRegDateSel}&nbsp;&nbsp;&nbsp;<i class="bi bi-eye" style="margin-right: 3px;"></i>${qnaItem.hit}
	  				&nbsp;<i class="bi bi-chat-square-dots" style="font-size: 13px"></i>&nbsp;${qnaItem.replyCount}</div>
	  			</div>
			</li>
		</c:forEach>
	</ul>

	<hr>

	<!-- 페이지네이션 -->
	<c:if test="${qnaPage.hasContents()}">
		<div class="pagination-container">
  			<div class="pagination">
  				<c:if test="${qnaPage.startPage > 5}">
     				<a href="/qna/list.do?pageNo=${qnaPage.startPage - 5}&search=${search}">&laquo;</a>
     			</c:if>
     			
     			<c:forEach var="pNo" begin="${qnaPage.startPage}" end="${qnaPage.endPage}">
     				<c:if test="${pNo eq qnaPage.getCurrentPage()}">
     					<a href="/qna/list.do?&pageNo=${pNo}&search=${search}" class="active">${pNo}</a>
      				</c:if>
     				<c:if test="${pNo ne qnaPage.getCurrentPage()}">
     					<a href="/qna/list.do?&pageNo=${pNo}&search=${search}">${pNo}</a>
      				</c:if>
		       </c:forEach>
		       <c:if test="${qnaPage.endPage < qnaPage.totalPages}">
		       	<a href="/qna/list.do?pageNo=${qnaPage.startPage + 5}&search=${search}">&raquo;</a>
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
