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

<script>
    document.addEventListener("DOMContentLoaded", function() {
        function goBackToPreviousPage() {
            var previousPageUrl = document.referrer;
            
        	window.location.href = "/findPwd.do";
        }

        document.querySelector(".back").addEventListener("click", goBackToPreviousPage);
    });
</script>
</head>
<body class="text-center">

	<main class="form-container">
  		<form action="/changePwd.do" method="post">
    		<a href="/main.do"><img class="mb-4" src="../../imgs/dokkyLogo.png" alt="DOKKY" width="200"></a>
    		<h1 class="h3 mb-4 fw-bold">비밀번호 변경</h1>
    
   			<div class="wrap">
	   			<hr class="bar">
	   			<span class="txt">변경하실 비밀번호를 입력해주세요.</span>
	   			<hr class="bar">
   			</div>

    		<div class="form-floating custom-form">
      			<input type="password" class="form-control" id="floatingPassword1" name="password1" maxlength='15' placeholder="변경할 비밀번호">
      			<label for="floatingPassword1">변경할 비밀번호</label>
    		</div>
    
    		<div class="form-floating custom-form">
      			<input type="password" class="form-control" id="floatingPassword2" name="password2" maxlength='15' placeholder="비밀번호 확인">
      			<label for="floatingPassword2">비밀번호 확인</label>
    		</div>

			<div class="button-wrapper d-flex justify-content-between">
			    <button class="btn btn-lg btn-primary custom-button form-button me-2 back" type="button" style="background-color: #ffffff; color: #0090F9; border-color: #0090F9;">취소</button>
			    <button class="btn btn-lg btn-primary custom-button form-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">비밀번호 변경</button>  
			</div>

  		</form>
  		
  		<c:choose>
			<c:when test="${save}">
				<p class="errors-msg">&nbsp;</p>
			</c:when>
			<c:when test="${errors.blank}">
				<p class="errors-msg">비밀번호는 공백 문자 포함이 불가능합니다.</p>
			</c:when>
			<c:when test="${errors.password1}">
				<p class="errors-msg"><b>변경할 비밀번호</b>를 입력해주세요.</p>
			</c:when>
			<c:when test="${errors.password2}">
				<p class="errors-msg"><b>비밀번호 확인</b>을 입력해주세요.</p>
			</c:when>
			<c:when test="${errors.cantChange}">
				<p class="errors-msg">비밀번호가 일치하지 않습니다.</p>
			</c:when>
			<c:when test="${update}">
				<p class="errors-msg">&nbsp;</p>
				<script>
					$(document).ready(function() {
	                	$('#changePwdModal').modal('show');
	            	});
				</script>
			</c:when>
			<c:otherwise>
				<p class="errors-msg">&nbsp;</p>
			</c:otherwise>
		</c:choose>
	</main>
	
	<div class="modal" id="changePwdModal" data-bs-backdrop="static" tabindex="-1">
  		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">비밀번호 변경 완료</h5>
		      </div>
		      <div class="modal-body">
		        <p style="font-size: 16px; margin-bottom: 0px">비밀번호 변경이 완료되었습니다.<br/>새로운 비밀번호로 로그인해주세요.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" onclick="location.href='/login.do'" style="background-color: #0090F9; border-color: #0090F9;">로그인</button>
		      </div>
		    </div>
		</div>
	</div>

</body>
</html>