<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>내 정보</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="../../css/dokkyCss/memberStyle.css"/>

<script>
	function openPopup(){
		var url = "/changePassword.do";
		var width = 570; // 팝업 창의 너비
        var height = 420; // 팝업 창의 높이
        var left = (screen.width - width) / 2; // 스크린의 가로 중앙 좌표
        var top = (screen.height - height) / 2; // 스크린의 세로 중앙 좌표
        var pop = window.open(url, "비밀번호 변경 팝업", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",resizable=yes");
    }
	
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
		<div class="container mt-4 myPageCon">
			<div class="row">
	    		<div class="col-md-6">
	        		<div class="card">
	            		<div class="card-body">
	                		<h4 class="card-title mb-4">내 정보</h4>
	                		<form>
		                    	<div class="mb-3">
		                        	<label for="id" class="form-label">아이디</label>
		                        	<input type="text" class="form-control" id="id" value="${authUser.id}" readonly>
		                    	</div>
		                    	<div class="mb-3">
			                        <label for="name" class="form-label">이름</label>
			                        <input type="text" class="form-control" id="name" value="${authUser.name}" readonly>
		                    	</div>
			                    <div class="mb-3">
			                        <label for="email" class="form-label">이메일</label>
			                        <input type="email" class="form-control" id="email" value="${authUser.email}" readonly>
			                    </div>
			                    <div class="mb-3">
			                        <label for="grade" class="form-label">회원 등급</label>
			                        <input type="text" class="form-control" id="grade" value="${authUser.grade == 2222 ? '준회원' : (authUser.grade == 1111 ? '정회원' : '관리자')}" readonly>
			                    </div>
			                    <div class="text-center">
			                    	<button class="btn btn-primary custom-button form-button" type="button" onclick="openPopup();" style="background-color: #0090F9; border-color: #0090F9;">비밀번호 변경</button>  
			                    </div>
		                	</form>
		            	</div>
		        	</div>
		    	</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../headerFooter/footer.jsp" %>
</body>
</html>