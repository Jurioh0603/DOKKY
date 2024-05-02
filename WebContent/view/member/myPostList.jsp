<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - All That Developer</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="../../css/dokkyCss/memberStyle.css"/>

<script>
	document.addEventListener("DOMContentLoaded", function() {
	  const board = "${board}";
	  
	  if (board === "qna") {
		document.getElementById("qna-link").classList.add("active");
	  } else if (board === "community") {
		document.getElementById("community-link").classList.add("active");
	  } else if (board === "study") {
		document.getElementById("study-link").classList.add("active");
	  } else if (board === "lunch") {
		document.getElementById("lunch-link").classList.add("active");
	  }
	
    $(function() {
    	
    	$('#selectAll').on('click', function() {
    		
    		var checked = $(this).is(':checked');
    		
    		$('tbody input[type=checkbox]').prop('checked', checked);
    	});
        
        $('#deleteButton').on('click', function(){
            var $checked = $('table input[type=checkbox]:checked').not('#selectAll');
            if($checked.length < 1) {
            	alert('삭제할 게시글을 선택해주세요.');
            	return false;
            } else {
            	var confirmation = confirm('선택한 게시글을 삭제하시겠습니까?');
            	
            	if(confirmation) {
                    var deleteList = [];
                    
                    $checked.each(function() {
                    	var boardItemBno = $(this).closest('tr').find('.boardItemBno').val();
                        deleteList.push(boardItemBno);
                    });
                    
                    $('input[name="deleteList"]').val(deleteList.join(','));
                    
                    $('form[name="deleteForm"]').submit();
            	}
            }
        });
	});
}); 
</script>
</head>
<body>
<%@ include file="../headerFooter/header.jsp" %>

<div class="d-flex">
	<div class="d-flex flex-column flex-shrink-0 ps-5 pt-5 side-bar ms-5" style="width: 280px;">
  		<ul class="nav nav-pills flex-column mb-auto">
    		<li class="nav-item l1">
      			<a href="/myPage.do" class="nav-link link-dark">계정 관리</a>
    		</li>
    		<li class="l1">
	  			<a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="color: black;">
	  			내가 쓴 글
	  			</a>
	  			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" id="qna-link" href="/postList.do?board=qna&pageNo=1">Q&A</a></li>
				    <li><a class="dropdown-item" id="community-link" href="/postList.do?board=community&pageNo=1">자유게시판</a></li>
				    <li><a class="dropdown-item" id="study-link" href="/postList.do?board=study&pageNo=1">스터디모집</a></li>
				    <li><a class="dropdown-item" id="lunch-link" href="/postList.do?board=lunch&pageNo=1">점메추</a></li>
	  			</ul>
	  		</li>
	  	</ul>
	</div>

	<hr/>

	<div class="m-5 flex-grow-1">
		<div style="display: flex; justify-content: space-between;">
			<div>
				<h2>
					<c:choose>
						<c:when test="${board eq 'qna'}">Q&A</c:when>
						<c:when test="${board eq 'community'}">자유게시판</c:when>
						<c:when test="${board eq 'study'}">스터디모집</c:when>
						<c:when test="${board eq 'lunch'}">점메추</c:when>
					</c:choose>
				</h2>
				<h6 style="color: gray;">제목 클릭 시 글 링크로 이동합니다.</h6>
				<h6 style="color: gray;">총 게시물 수: ${postPage.total}개</h6>
			</div>
			<form action="/postList.do" method="get" class="d-flex" style="align-self: flex-end;">
				<input type="hidden" name="pageNo" value="1"/>
				<input type="hidden" name="board" value="${board}"/>
	        	<input class="form-control me-2" type="search" name="search" placeholder="검색어를 입력하세요." value="${search}" aria-label="Search" style="height: 40px;">
	        	<button class="btn btn-outline-primary" type="submit" style="white-space: nowrap;">검색</button>
	      	</form>
	    </div>
		<div class="container mt-4">
			<form name="deleteForm" method="post" action="/postList.do" >
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col" style="white-space: nowrap;">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성일</th>
							<th scope="col" style="white-space: nowrap;">선택&nbsp;&nbsp;<input id="selectAll" type="checkbox" style="transform: scale(1.3);"></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${postPage.hasNoContents()}">
							<tr>
								<td colspan="4">게시글이 존재하지 않습니다.</td>
						</c:if>
						<c:set var="currentPage" value="${postPage.getCurrentPage()}"/>
						<c:set var="number" value="${(currentPage-1)*10+1 }"/>
						<c:forEach var="boardItem" items="${postPage.postList}">
							<tr>
								<td style="white-space: nowrap;">${number}</td>
								<td><a href="/${board}/read.do?no=${boardItem.bno}">${boardItem.title }</a></td>
								<td style="white-space: nowrap;">${boardItem.formattedRegDate }</td>
								<td><input type="checkbox" style="transform: scale(1.3);"></td>
								<input type="hidden" class="boardItemBno" value="${boardItem.bno}">
							</tr>
						<c:set var="number" value="${number+1}"/>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="deleteList"/>
				<input type="hidden" name="board" value="${board}"/>   			
				<input type="hidden" name="pageNo" value="${postPage.getCurrentPage()}"/>			
				<input type="hidden" name="search" value="${search}"/>	
				<button type="button" class="btn btn-danger" id="deleteButton">삭제</button>
      			<c:if test="${postPage.hasContents()}">
	      			<div class="pagination-container">
	          			<div class="pagination">
	          				<c:if test="${postPage.startPage > 5}">
	             				<a href="/postList.do?board=${board}&pageNo=${postPage.startPage - 5}&search=${search}">&laquo;</a>
	             			</c:if>
	             			<c:forEach var="pNo" begin="${postPage.startPage}" end="${postPage.endPage}">
	             				<c:if test="${pNo eq postPage.getCurrentPage()}">
	             					<a href="/postList.do?board=${board}&pageNo=${pNo}&search=${search}" class="active">${pNo}</a>
	              				</c:if>
	             				<c:if test="${pNo ne postPage.getCurrentPage()}">
	             					<a href="/postList.do?board=${board}&pageNo=${pNo}&search=${search}">${pNo}</a>
	              				</c:if>
				            </c:forEach>
				            <c:if test="${postPage.endPage < postPage.totalPages}">
				            	<a href="/postList.do?board=${board}&pageNo=${postPage.startPage + 5}&search=${search}">&raquo;</a>
				            </c:if>
	         			</div>
	      			</div>
	     		</c:if>
			</form>
		</div>
	</div>
</div>

<%@ include file="../headerFooter/footer.jsp" %>
</body>
</html>