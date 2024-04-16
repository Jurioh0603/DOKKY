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

<link href="../../css/dokkyCss/accountStyle.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-container">
		<form action="/login.do" method="post">
	    	<a href="/main.do"><img class="mb-4" src="../../imgs/dokkyLogo.png" alt="DOKKY" width="200"></a>
	    	<h1 class="h3 mb-3 fw-bold">DOKKY에 오신것을 환영합니다.</h1>
	    	<p>DOKKY는 중앙정보처리학원 수강생들을 위한 커뮤니티 사이트입니다.</p>
	    
	   		<div class="wrap">
		   		<hr class="bar">
		   		<span class="txt">DOKKY 아이디로 로그인</span>
		   		<hr class="bar">
	   		</div>
	
	    	<div class="form-floating custom-form">
	      		<input type="text" class="form-control" id="floatingId" name="id" value="${param.id}" placeholder="아이디">
	      		<label for="floatingId">아이디</label>
	    	</div>
	    
		    <div class="form-floating custom-form">
		      	<input type="password" class="form-control" id="floatingPassword" name="password" placeholder="비밀번호">
		      	<label for="floatingPassword">비밀번호</label>
		    </div>
	
	    	<button class="w-100 btn btn-lg btn-primary custom-button login-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">로그인</button>  
	
		    <div class="link-container">
			    <a href="/findId.do">아이디찾기</a>
			    <a href="/findPwd.do">비밀번호찾기</a>
			    <a href="#">회원가입</a>
			</div>
  		</form>
  		<c:choose>
  			<c:when test="${errors.id}">
  				<p class="errors-msg"><b>아이디</b>를 입력해 주세요.</p>
  			</c:when>
  			<c:when test="${errors.password}">
  				<p class="errors-msg"><b>비밀번호</b>를 입력해 주세요.</p>
  			</c:when>
  			<c:when test="${errors.idOrPwNotMatch}">
  				<p class="errors-msg">아이디 또는 비밀번호를 잘못 입력했습니다.</p>
  			</c:when>
  			<c:otherwise>
  				<p class="errors-msg">&nbsp;</p>
  			</c:otherwise>
  		</c:choose>
	</main>

</body>
</html>