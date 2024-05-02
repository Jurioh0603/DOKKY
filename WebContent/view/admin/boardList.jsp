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

<link rel="stylesheet" href="../../css/dokkyCss/adminStyle.css"/>

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
                        deleteList.push($(this).closest('tr').find('td:first').text());
                    });
                    
                    $('input[name="deleteList"]').val(deleteList.join(','));
                    
                    $('form[name="deleteForm"]').submit();
            	}
            }
        });
	}); 
	 // searchItem의 값이 title이면 value가 title인 option을 선택
	 // writer이면 value가 writer인 option을 선택
	
	 var searchItem = "${searchItem}"; // 여기에 searchItem의 값이 들어가야 합니다.
	
	 var selectElement = document.getElementById('searchItem');
	
	 for (var i = 0; i < selectElement.options.length; i++) {
	     if (selectElement.options[i].value === searchItem) {
	         selectElement.selectedIndex = i;
	         break;
	     }
	 }

    });
</script>
</head>
<body>
<%@ include file="../headerFooter/header.jsp" %>

<div class="d-flex">
	<div class="d-flex flex-column flex-shrink-0 ps-5 pt-5 side-bar ms-5" style="width: 280px;">
  		<ul class="nav nav-pills flex-column mb-auto">
    		<li class="nav-item l1">
      			<a href="/admin/memberList.do" class="nav-link link-dark">회원 관리</a>
    		</li>
    		<li class="l1">
	  			<a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="color: black;">
	  			게시글 관리
	  			</a>
	  			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" id="qna-link" href="/admin/boardList.do?board=qna&pageNo=1">Q&A</a></li>
				    <li><a class="dropdown-item" id="community-link" href="/admin/boardList.do?board=community&pageNo=1">자유게시판</a></li>
				    <li><a class="dropdown-item" id="study-link" href="/admin/boardList.do?board=study&pageNo=1">스터디모집</a></li>
				    <li><a class="dropdown-item" id="lunch-link" href="/admin/boardList.do?board=lunch&pageNo=1">점메추</a></li>
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
				<h6 style="color: gray;">총 게시물 수: ${boardPage.total}개</h6>
			</div>
			<form action="/admin/boardList.do" method="get" class="d-flex" style="align-self: flex-end;">
				<input type="hidden" name="pageNo" value="1"/>
				<input type="hidden" name="board" value="${board}"/>
				<select name="searchItem" id="searchItem" style="margin-right: 8px;">
					<option value="title">제목</option>
					<option value="writer">작성자</option>
				</select>
	        	<input class="form-control me-2" type="search" name="searchId" placeholder="검색어를 입력하세요." value="${searchId}" aria-label="Search" style="height: 40px;">
	        	<button class="btn btn-outline-primary" type="submit" style="white-space: nowrap;">검색</button>
	      	</form>
	    </div>
		<div class="container mt-4">
			<form name="deleteForm" method="post" action="/admin/boardList.do" >
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">글번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col">선택&nbsp;&nbsp;<input id="selectAll" type="checkbox" style="transform: scale(1.3);"></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${boardPage.hasNoContents()}">
							<tr>
								<td colspan="5">게시글이 존재하지 않습니다.</td>
						</c:if>
						<c:forEach var="boardItem" items="${boardPage.boardList}">
							<tr>
								<td>${boardItem.bno }</td>
								<td><a href="/${board}/read.do?no=${boardItem.bno}">${boardItem.title }</a></td>
								<td>${boardItem.memid }</td>
								<td style="white-space: nowrap;">${boardItem.formattedRegDate }</td>
								<td><input type="checkbox" style="transform: scale(1.3);"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="deleteList"/>
				<input type="hidden" name="board" value="${board}"/>   			
				<input type="hidden" name="pageNo" value="${boardPage.getCurrentPage()}"/>			
				<input type="hidden" name="searchId" value="${searchId}"/>	
				<input type="hidden" name="searchItem" value="${searchItem}"/>	
				<button type="button" class="btn btn-danger" id="deleteButton">삭제</button>
      			<c:if test="${boardPage.hasContents()}">
	      			<div class="pagination-container">
	          			<div class="pagination">
	          				<c:if test="${boardPage.startPage > 5}">
	             				<a href="/admin/boardList.do?board=${board}&pageNo=${boardPage.startPage - 5}&searchId=${searchId}&searchItem=${searchItem}">&laquo;</a>
	             			</c:if>
	             			<c:forEach var="pNo" begin="${boardPage.startPage}" end="${boardPage.endPage}">
	             				<c:if test="${pNo eq boardPage.getCurrentPage()}">
	             					<a href="/admin/boardList.do?board=${board}&pageNo=${pNo}&searchId=${searchId}&searchItem=${searchItem}" class="active">${pNo}</a>
	              				</c:if>
	             				<c:if test="${pNo ne boardPage.getCurrentPage()}">
	             					<a href="/admin/boardList.do?board=${board}&pageNo=${pNo}&searchId=${searchId}&searchItem=${searchItem}">${pNo}</a>
	              				</c:if>
				            </c:forEach>
				            <c:if test="${boardPage.endPage < boardPage.totalPages}">
				            	<a href="/admin/boardList.do?board=${board}&pageNo=${boardPage.startPage + 5}&searchId=${searchId}&searchItem=${searchItem}">&raquo;</a>
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