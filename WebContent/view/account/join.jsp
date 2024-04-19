<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>회원가입</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link href="../../css/dokkyCss/accountStyle.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-container">
  		<form action="/join.do" method="post">
    		<a href="/main.do"><img class="mb-4" src="../../imgs/dokkyLogo.png" alt="DOKKY" width="200"></a>
    		<h1 class="h3 mb-3 fw-bold">DOKKY에 오신것을 환영합니다.</h1>
    		<p>DOKKY는 중앙정보처리학원 수강생들을 위한 커뮤니티 사이트입니다.</p>
    
   			<div class="wrap">
	   			<hr class="bar">
	   			<span class="txt">회원가입에 필요한 기본정보를 입력해주세요.</span>
	   			<hr class="bar">
   			</div>

		    <div class="form-floating custom-form">
		      	<input type="text" class="form-control" name="id" value="${param.id}" id="floatingId" placeholder="아이디">
		      	<label for="floatingId">아이디</label>
		    </div>
	    
		    <div class="form-floating custom-form">
		      	<input type="password" class="form-control" name="password" id="floatingPassword1" placeholder="비밀번호">
		      	<label for="floatingPassword1">비밀번호</label>
		    </div>
	    
		    <div class="form-floating custom-form">
		      	<input type="password" class="form-control" name="verifyPassword" id="floatingPassword2" placeholder="비밀번호 확인">
		      	<label for="floatingPassword2">비밀번호 확인</label>
		    </div>
		    
		    <div class="form-floating custom-form">
		      	<input type="text" class="form-control" name="name" value="${param.name}" id="floatingName" placeholder="이름">
		      	<label for="floatingName">이름</label>
		    </div>
    
		    <div class="form-floating custom-form">
		      	<input type="email" class="form-control" name="email" value="${param.email}" id="floatingEmail" placeholder="이메일">
		      	<label for="floatingEmail">이메일</label>
		    </div>

			<div class="button-wrapper d-flex justify-content-center">
		    	<button class="btn btn-lg btn-primary custom-button form-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">회원가입</button>  
		 	</div>
		    	이미 회원이신가요?<a href="/login.do" class="flex items-center text-blue-500 underline hover:text-blue-400">로그인</a>
  		</form>
	       <c:choose>
	           <c:when test="${errors.id}">
	               <p class="errors-msg"><b>아이디</b>를 입력해 주세요.</p>
	           </c:when>
	           <c:when test="${errors.duplicateId}">
	               <p class="errors-msg">이미 사용중인 아이디입니다.</p>
	           </c:when>
	           <c:when test="${errors.password}">
	               <p class="errors-msg"><b>비밀번호</b>를 입력해 주세요.</p>
	           </c:when>
	           <c:when test="${errors.name}">
	               <p class="errors-msg"><b>이름</b>을 입력해 주세요.</p>
	           </c:when>
	           <c:when test="${errors.email}">
	               <p class="errors-msg"><b>이메일</b>을 입력해 주세요.</p>
	           </c:when>
	           <c:when test="${errors.notMatch}">
	               <p class="errors-msg">비밀번호와 비밀번호 확인이 일치하지 않습니다.</p>
	           </c:when>
	           <c:when test="${errors.invalidEmail}">
	               <p class="errors-msg">이메일 형식이 올바르지 않습니다.</p>
	           </c:when>
	           <c:otherwise>
	               <p class="errors-msg">&nbsp;</p>
	           </c:otherwise>
	       </c:choose>
	</main>

</body>
</html>