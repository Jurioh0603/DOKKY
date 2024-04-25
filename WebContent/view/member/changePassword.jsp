<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link href="../../css/dokkyCss/accountStyle.css" rel="stylesheet">

<script>
	function openPopup(){
		var url = "../member/changePassword.jsp";
		var width = 570; // 팝업 창의 너비
        var height = 420; // 팝업 창의 높이
        var left = (screen.width - width) / 2; // 스크린의 가로 중앙 좌표
        var top = (screen.height - height) / 2; // 스크린의 세로 중앙 좌표
        var pop = window.open(url, "비밀번호 변경 팝업", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",resizable=yes");
    }
	
</script>
</head>
<body class="text-center">
<main class="form-container">
	<form action="/changePassword.do" method="post">
		<div class="mb-3 text-start">
	      	<label class="form-label">현재 비밀번호</label>
	      	<input type="password" name="curPwd" maxlength="15" placeholder="공백없이 15자 이내로 입력해주세요." class="form-control block w-full appearance-none rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base dark:bg-gray-500/20">
	    </div>	
		<div class="mb-3 text-start">
	      	<label class="form-label">새 비밀번호</label>
	      	<input type="password" name="newPwd" maxlength="15" placeholder="공백없이 15자 이내로 입력해주세요." class="form-control block w-full appearance-none rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base dark:bg-gray-500/20">
	    </div>	
		<div class="mb-3 text-start">
	      	<label class="form-label">비밀번호 확인</label>
	      	<input type="password" name="verifyNewPwd" maxlength="15" placeholder="공백없이 15자 이내로 입력해주세요." class="form-control block w-full appearance-none rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base dark:bg-gray-500/20">
	    </div>	
    	<div class="text-center">
        	<button class="btn btn-primary custom-button form-button" type="submit" style="background-color: #0090F9; border-color: #0090F9;">비밀번호 변경</button>  
        	<button class="btn btn-primary custom-button form-button" type="button" onclick="window.close()" style="background-color: #0090F9; border-color: #0090F9;">닫기</button>  
        </div>
	</form>
	<c:choose>
	    <c:when test="${errors.curPwd}">
	        <p class="errors-msg">현재 비밀번호를 입력하세요.</p>
	    </c:when>
	    <c:when test="${errors.badCurPwd}">
	        <p class="errors-msg">현재 비밀번호가 일치하지 않습니다.</p>
	    </c:when>
	    <c:when test="${errors.newPwd}">
	        <p class="errors-msg">새 비밀번호를 입력하세요.</p>
	    </c:when>
	    <c:when test="${errors.notMatch}">
            <p class="errors-msg">새 비밀번호와 비밀번호 확인이 일치하지 않습니다.</p>
        </c:when>
	    <c:otherwise>
	        <p class="errors-msg">&nbsp;</p>
	    </c:otherwise>
	</c:choose>
	</main>
</body>
</html>