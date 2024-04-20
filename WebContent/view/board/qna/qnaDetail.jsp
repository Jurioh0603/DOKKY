<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/qnaDetailStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
  	  	
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - Q&A 글보기</title>
</head>	
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>

<div class="container">
<br>
<br>
<br>
	<div class="square-box">
		<p class="main-text">Q&A</p>
		<p class="sub-text">좋은 질문과 답변으로 동료의 시간을 아껴주세요.</p>
		<img src="<%=request.getContextPath() %>/imgs/qna.svg" alt="SVG Icon" class="r-icon">
	</div>
	<hr style="clear:both;"/>
	
	<!-- 글 보기 -->
	<h1 class="logo">게시글 제목</h1>
	<main class="main">
		<div class="post">
			<p class="post-content">게시글 내용이 여기에 들어갑니다.</p>
		</div>
		<br>
		<br>
		<br>
	</main>
	<hr style="clear:both;"/>
    
    
   <!-- 댓글 입력 폼 추가 -->
<form id="addReplyForm"  action="/reply/reply.do" method="post">
    <input type="hidden" name="command" value="addReply">
    <input type="hidden" name="bno" value="${param.bno}">
    <div class="form-group row">
        <label for="rcontent" class="col-sm-2 col-form-label"><strong>댓글 내용</strong></label>
        <div class="col-sm-10">
            <textarea name="rcontent" class="form-text1" id="rcontent"></textarea>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-10 offset-sm-2">
            <button type="submit" class="btn btn-primary float-end">댓글 등록</button>
        </div>
    </div>
</form>

<!-- 댓글 목록 표시 영역 -->
<div id="replyList"></div>

</div>
<br>
<br>
<br>
<br>
<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
<!-- 스크립트 -->
<script>
//댓글 추가 AJAX 요청
// 댓글 추가 AJAX 요청
$(document).on("submit", "#addReplyForm", function(e) {
    e.preventDefault(); // 폼 기본 동작 방지

    $.ajax({
        type: "POST",
        url: "/reply/reply.do?command=addReply", // Handler로 요청
        data: {
            bno: $("#bno").val(),
            memid: '<%= session.getAttribute("memid") %>', // 세션에 저장된 사용자 아이디 사용
            rcontent: $("#rcontent").val()
        },
        success: function() {
            // 댓글 목록 새로고침
            loadReplies();
            // 입력 폼 초기화
            $("#rcontent").val("");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error: " + textStatus, errorThrown);
        }
    });
});


function loadReplies() {
    var bno = $("#bno").val(); // bno 파라미터 값 가져오기

    $.ajax({
        type: "GET",
        url: "/reply/reply.do?command=getRepliesByBno&bno=" + bno, // Handler로 요청
        success: function(data) {
            $("#replyList").html(data); // 댓글 목록 업데이트
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error: " + textStatus, errorThrown);
        }
    });
}

// 댓글 삭제 함수
function removeReply(rno) {
    $.ajax({
        type: "POST",
        url: "/reply/reply.do?command=removeReply", // Handler로 요청
        data: {
            rno: rno
        },
        success: function(data) {
            loadReplies(); // 댓글 삭제 후 목록 다시 로드
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error: " + textStatus, errorThrown);
        }
    });
}
</script>
</body>
</html>