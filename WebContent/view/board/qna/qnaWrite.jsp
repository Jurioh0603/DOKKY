<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/writeStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - Q&A 글작성</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>
<br>
<br>
<br>

<!-- 페이지소개 -->
<div class="container">
	<div class="content">
		<h1 class="title">궁금증 해결하기</h1>
		<h2 class="user">DOKKY에서 당신의 궁금증을 해결해보세요.</h2>     
		<br>
		<br>
		<br>
	</div>

	<!-- 글쓰기 -->
<form id="writeForm" action="/qna/write.do" method="post">
        <div class="form-group row">
            <label for="inputTitle" class="col-sm-2 col-form-label"><strong>제목</strong></label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-text" id="inputTitle" onKeyUp="javascript:fnChkByte(this,'250')">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
            <div class="col-sm-10" style="white-space:pre;">
                <textarea name="content" class="form-text1" id="inputContent" onKeyUp="javascript:fnChkByte(this,'3500')"></textarea>
            </div>
        </div>

        <!-- 글 등록과 취소 -->
        <div class="form-group row">
            <label class="col-sm-2"></label>
            <div class="col-sm-10">
                <div class="button-container">
                    <button type="submit" onclick="submitForm()" class="custom-button">등록</button>
                    <button type="button" onclick="cancel()" class="custom-button" style="margin-left:3px;">취소</button>
                </div>
            </div>
        </div>
    </form>
</div>
<br>
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script>
    function submitForm() {
        var title = document.getElementById('inputTitle').value;
        var content = document.getElementById('inputContent').value;

        // 제목 또는 내용 중 하나라도 입력되지 않은 경우
        if (title.trim() === '' || content.trim() === '') {
            alert('제목 또는 내용을 입력하세요.');
            return false; // 제출을 막음
        }

        // 폼을 직접 제출
        document.getElementById('writeForm').submit();
    }
    
    function cancel() {
        window.location.href = '/qna/list.do'; // 목록 페이지의 URL로 리디렉션
    }
    
    function fnChkByte(obj, maxByte)
    {
        var str = obj.value;
        var str_len = str.length;


        var rbyte = 0;
        var rlen = 0;
        var one_char = "";
        var str2 = "";


        for(var i=0; i<str_len; i++)
        {
            one_char = str.charAt(i);
            if(escape(one_char).length > 4) {
                rbyte += 3;                                     
            }else{
                rbyte++;                                       
            }
            if(rbyte <= maxByte){
                rlen = i+1;                                        
            }
         }
         if(rbyte > maxByte)
         {
            alert("최대 " + maxByte + "byte를 초과할 수 없습니다.")
            str2 = str.substr(0,rlen);                               
            obj.value = str2;
            fnChkByte(obj, maxByte);
         }
         else
         {
            document.getElementById('byteInfo').innerText = rbyte;
         }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html> 