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

<link href="../../css/dokkyCss/accountStyle.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-container">
  		<form action="findId.do" method="post">
  			<a href="#"><img class="mb-4" src="../../imgs/dokkyLogo.png" alt="DOKKY" width="200"></a>
    		<h1 class="h3 mb-4 fw-bold">아이디 찾기</h1>
    
	   		<div class="wrap">
		   		<hr class="bar">
		   		<span class="txt">회원 가입시 입력하신 이름과 이메일 주소를 입력해주세요.</span>
		   		<hr class="bar">
	   		</div>
	
		    <div class="form-floating custom-form">
		      	<input type="text" class="form-control" id="floatingName" name="name" value="${param.name}" placeholder="이름">
		      	<label for="floatingName">이름</label>
		    </div>
	    
		    <div class="form-floating custom-form">
		      	<input type="email" class="form-control" id="floatingEmail" name="email" value="${param.email}" placeholder="이메일">
		      	<label for="floatingEmail">이메일</label>
		    </div>
	
			<div class="button-wrapper d-flex justify-content-between">
			    <button class="btn btn-lg btn-primary custom-button form-button me-2" type="button" style="background-color: #ffffff; color: #0090F9; border-color: #0090F9;">취소</button>
			    <button class="btn btn-lg btn-primary custom-button form-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">아이디 찾기</button>  
			</div>
	 	</form>
	 	
	 	<c:choose>
			<c:when test="${errors.name}">
				<p class="errors-msg">이름을 입력해주세요.</p>
			</c:when>
			<c:when test="${errors.email}">
				<p class="errors-msg">이메일 주소를 입력해주세요.</p>
			</c:when>
			<c:when test="${errors.cantFind}">
				<p class="errors-msg">일치하는 가입 정보가 없습니다.</p>
			</c:when>
			<c:when test="${requestScope.memid != null}">
				<p class="errors-msg">&nbsp;</p>
				<script>
					$(document).ready(function() {
	                	$('#findIdModal').modal('show');
	            	});
				</script>
			</c:when>
			<c:otherwise>
				<p class="errors-msg">&nbsp;</p>
			</c:otherwise>
		</c:choose>
	</main>
	
	<div class="modal" id="findIdModal" tabindex="-1">
  		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">아이디 찾기</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p style="font-size: 20px;">아이디는 ${requestScope.memid} 입니다.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-primary" onclick="location.href='login.do'" style="background-color: #0090F9; border-color: #0090F9;">로그인</button>
		      </div>
		    </div>
		</div>
	</div>

</body>
</html>