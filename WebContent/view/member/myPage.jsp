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

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="../../css/dokkyCss/memberStyle.css"/>
</head>
<body>
<%@ include file="../headerFooter/header.jsp" %>

<div class="d-flex">
	<div class="d-flex flex-column flex-shrink-0 ps-5 pt-5 side-bar ms-5" style="width: 280px;">
  		<ul class="nav nav-pills flex-column mb-auto">
    		<li class="nav-item l1">
      			<a href="#" class="nav-link link-dark active">계정 관리</a>
    		</li>
    		<li class="nav-item l1">
      			<a href="#" class="nav-link link-dark">내가 쓴 글</a>
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
	                		<h4 class="card-title mb-4">회원 정보</h4>
	                		<form>
		                    	<div class="mb-3">
		                        	<label for="userID" class="form-label">ID</label>
		                        	<input type="text" class="form-control" id="userID" value="사용자 ID" readonly>
		                    	</div>
		                    	<div class="mb-3">
			                        <label for="username" class="form-label">이름</label>
			                        <input type="text" class="form-control" id="username" value="사용자 이름">
		                    	</div>
		                    	<div class="mb-3">
			                        <label for="password" class="form-label">비밀번호</label>
			                        <input type="password" class="form-control" id="password">
		                    	</div>
			                    <div class="mb-3">
			                        <label for="passwordConfirm" class="form-label">비밀번호 확인</label>
			                        <input type="password" class="form-control" id="passwordConfirm">
			                    </div>
			                    <div class="mb-3">
			                        <label for="email" class="form-label">이메일 주소</label>
			                        <input type="email" class="form-control" id="email" value="user@example.com">
			                    </div>
			                    <div class="mb-3">
			                        <label for="userGrade" class="form-label">회원 등급</label>
			                        <input type="text" class="form-control" id="userGrade" value="관리자가 정한 등급" readonly>
			                    </div>
			                    <div class="text-center">
			                    	<button class="btn btn-primary custom-button form-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">수정</button>
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