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
	function showModal(memid, name, email, grade, currentPage) {
	    $('#changeMemberInfoModal').modal('show');
	    document.getElementById('id').value = memid;
	    document.getElementById('name').value = name;
	    document.getElementById('email').value = email;
	    document.getElementById('currentPage').value = currentPage;
	    
	    // grade에 따라 해당하는 옵션을 선택
	    var gradeSelect = document.getElementById('grade');
	    for (var i = 0; i < gradeSelect.options.length; i++) {
	        if (gradeSelect.options[i].value === grade) {
	            gradeSelect.options[i].selected = true;
	        }
	    }
	}
</script>
</head>
<body>
<%@ include file="../headerFooter/header.jsp" %>

<div class="d-flex">
	<div class="d-flex flex-column flex-shrink-0 ps-5 pt-5 side-bar ms-5" style="width: 280px;">
  		<ul class="nav nav-pills flex-column mb-auto">
    		<li class="nav-item l1">
      			<a href="/admin/memberList.do" class="nav-link link-dark active">회원 관리</a>
    		</li>
    		<li class="l1">
	  			<a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="color: black;">
	  			게시글 관리
	  			</a>
	  			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="/admin/boardList.do?board=qna">Q&A</a></li>
				    <li><a class="dropdown-item" href="/admin/boardList.do?board=community">자유게시판</a></li>
				    <li><a class="dropdown-item" href="/admin/boardList.do?board=study">스터디모집</a></li>
				    <li><a class="dropdown-item" href="/admin/boardList.do?board=lunch">점메추</a></li>
	  			</ul>
    		</li>
  		</ul>
	</div>

	<hr/>

	<div class="m-5 flex-grow-1">
		<h2>회원 관리</h2>
		<h6 style="color: gray;">클릭 시 회원 정보 수정이 가능합니다.</h6>
		<div class="container mt-4">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
			            <th scope="col">번호</th>
			            <th scope="col">아이디</th>
			            <th scope="col">이름</th>
			            <th scope="col">이메일</th>
			            <th scope="col">가입일</th>
			            <th scope="col">회원등급</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${memberPage.hasNoMembers()}">
						<tr>
							<td colspan="6">회원이 존재하지 않습니다.</td>
					</c:if>
					<c:set var="currentPage" value="${memberPage.getCurrentPage()}"/>
					<c:set var="number" value="${(currentPage-1)*10+1 }"/>
					<c:forEach var="member" items="${memberPage.memberList}">
						<tr onclick="showModal('${member.memid}', '${member.name}', '${member.email}', '${member.grade}', ${currentPage})">
							<td>${number }</td>
							<td>${member.memid }</td>
							<td>${member.name }</td>
							<td>${member.email }</td>
							<td>${member.regdate }</td>
							<td>
								<c:choose>
									<c:when test="${member.grade == 1111}">
										정회원
									</c:when>
									<c:when test="${member.grade == 2222}">
										준회원
									</c:when>
									<c:when test="${member.grade == 9999}">
										관리자
									</c:when>
								</c:choose>
					        </td>
						</tr>
						<c:set var="number" value="${number+1}"/>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${memberPage.hasMembers()}">
      			<div class="pagination-container">
          			<div class="pagination">
          				<c:if test="${memberPage.startPage > 5}">
             				<a href="/admin/memberList.do?pageNo=${memberPage.startPage - 5}">&laquo;</a>
             			</c:if>
             			<c:forEach var="pNo" begin="${memberPage.startPage}" end="${memberPage.endPage}">
             				<c:if test="${pNo eq memberPage.getCurrentPage()}">
             					<a href="/admin/memberList.do?pageNo=${pNo}" class="active">${pNo}</a>
              				</c:if>
             				<c:if test="${pNo ne memberPage.getCurrentPage()}">
             					<a href="/admin/memberList.do?pageNo=${pNo}">${pNo}</a>
              				</c:if>
			            </c:forEach>
			            <c:if test="${memberPage.endPage < memberPage.totalPages}">
			            	<a href="/admin/memberList.do?pageNo=${memberPage.startPage + 5}">&raquo;</a>
			            </c:if>
         			</div>
      			</div>
     		</c:if>
		</div>
	</div>
</div>

<div class="modal" id="changeMemberInfoModal" tabindex="-1">
 		<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">회원 정보 수정</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	        <form action="/admin/updateGrade.do" method="post">
		      <div class="modal-body">
		          <div class="mb-3">
		            <label for="id" class="col-form-label">아이디</label>
		            <input type="text" class="form-control" id="id" name="id" readonly="readonly">
		          </div>
		          <div class="mb-3">
		            <label for="name" class="col-form-label">이름</label>
		            <input type="text" class="form-control" id="name" name="name" readonly="readonly">
		          </div>
		          <div class="mb-3">
		            <label for="email" class="col-form-label">이메일</label>
		            <input type="text" class="form-control" id="email" name="email" readonly="readonly">
		          </div>
		          <div class="mb-3">
		            <label for="grade" class="col-form-label">회원등급</label>
		            <select class="form-select" id="grade" name="grade">
		                <option value="2222">준회원</option>
		                <option value="1111">정회원</option>
		                <option value="9999">관리자</option>
					</select>
		          </div>
		          <input type="hidden" id="currentPage" name="currentPage"/>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary" style="background-color: #0090F9; border-color: #0090F9;">수정</button>
		      </div>
	      </form>
	    </div>
	</div>
</div>

<%@ include file="../headerFooter/footer.jsp" %>
</body>
</html>