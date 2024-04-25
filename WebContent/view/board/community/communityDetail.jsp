<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/communityDetailStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 자유게시판 글보기</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>

<div class="container">
<br>
<br>
<br>
	<div class="square-box">
		<p class="main-text">자유게시판</p>
		<p class="sub-text">다양한 주제로 함께하는 소통의 공간, 커뮤니티 게시판입니다.</p>
		<img src="<%=request.getContextPath() %>/imgs/community.svg" alt="SVG Icon" class="r-icon">
	</div>
	<hr style="clear:both;"/>
	
   <!-- 글 보기 -->
   <p style="margin-bottom: 5px;">작성자: ${communityData.community.memId}</p>
   <!-- p태그에 id값 부여해서 자바스크립트 실행 -->
   <p><span id="regDate" style="margin-bottom: 5px; margin-right: 5px;">${communityData.community.regDate}</span>
   <span id="hit"><i class="bi bi-eye" style="margin-right: 3px;"></i>${communityData.community.hit}</span></p>
   <br>
   <h2 class="logo">${communityData.community.title}</h2>
	
	<!-- JavaScript 코드(글작성 시간 ~시간전 표시) -->
	<script>
	  // 작성된 시간을 표시할 요소 선택
	  var regDateElement = document.getElementById('regDate');
	  
	  // 작성된 시간 가져오기
	  var regDate = regDateElement.textContent.trim();
	 
	  // 현재 시간
	  var currentDate = new Date();
	  
	  // 작성된 시간을 Date 객체로 변환
	  var regDateWithoutTZ = regDate.replace('KST', '');
	  var postDate = new Date(regDateWithoutTZ);
	  
	  // 현재 시간과 작성된 시간의 차이 계산 (밀리초 단위)
	  var timeDiff = currentDate - postDate;
	  
	  // 밀리초를 시간으로 변환
	  var seconds = Math.floor(timeDiff / 1000);
	  var minutes = Math.floor(seconds / 60);
	  var hours = Math.floor(minutes / 60);
	  var days = Math.floor(hours / 24);
	  
	  // 시간 전에 대한 표시를 작성된 시간 요소에 추가
	  var displayText = '';
	  if (days > 0) {
	    displayText = days + '일 전';
	  } else if (hours > 0) {
	    displayText = hours + '시간 전';
	  } else if (minutes > 0) {
	    displayText = minutes + '분 전';
	  } else {
	    displayText = seconds + '초 전';
	  }
	  regDateElement.textContent = displayText;
	  
	</script>
	
	<div id="output"></div>
	
	<main class="main">
		<div class="post" >
			<p class="post-content" style="white-space:pre;">${communityData.content}</p>
		</div>
		<br>
		<br>
		<br>
	</main>
	<hr style="clear:both;"/>
	<!-- 글목록 버튼 -->
	<form action="/community/list.do" method="post">
		<button class="next">목록</button>
	</form>
	
<!-- 글수정&글삭제 버튼(해당 글 작성자만 보이도록) -->
<c:if test="${authUser != null && (authUser.grade == 9999 || authUser.id == communityData.community.memId)}">
    <div class="form-group row">
        <div class="button-container" style="margin-bottom: 15px; justify-content: flex-end;">
            <c:if test="${authUser.id == communityData.community.memId}">
                <!-- 현재 로그인한 사용자가 글 작성자인 경우에만 수정 버튼이 나오도록 -->
                <form id="editForm" action="/community/modify.do" method="get">
                    <input type="hidden" name="no" value="${communityData.community.bno}">
                    <button type="submit" class="custom-button">글수정</button>
                </form>
            </c:if>
            <!-- 모달 버튼 -->
            <button type="button" class="custom-button" id="deleteModalButton" data-bs-toggle="modal" data-bs-target="#deleteModal">글삭제</button>
        </div>
    </div>
</c:if>
<br/>
<br/>

<!-- 모달 창 -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">글 삭제</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                정말로 삭제하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <!-- 삭제 버튼 클릭 시 폼 제출 -->
                <form id="deleteForm" action="/community/delete.do" method="post">
                    <input type="hidden" name="no" value="${param.no}">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 모달 창 끝 -->

 <!-- 댓글 -->
   <div class="comment-form">
		<form id="addReplyForm" action="/community/reply.do" method="post">
		    <input type="hidden" name="command" value="addReply"> 
		    <input type="hidden" name="no" value="${param.no }"> 
		    <div class="form-group row">
		        <label for="rcontent" class="col-sm-2 col-form-label"><strong>댓글 내용</strong></label>
		        <div class="col-sm-10">
		        <c:if test="${authUser != null}">
		            <textarea name="rcontent" class="form-text1" id="rcontent">${param.content}</textarea>
		        </c:if>
		        <c:if test="${authUser == null}">
		            <textarea name="rcontent" class="form-text1" id="rcontent" readonly="readonly">작성하려면 로그인이 필요합니다.</textarea>
		        </c:if>
		        </div>
		    </div>
		    
			    <div class="form-group row">
			        <div class="col-sm-10 offset-sm-2">
			    	<c:if test="${authUser != null}">
			            <button id="addReplyButton" class="btn btn-primary float-end">댓글 등록</button>
					</c:if>
			    	<c:if test="${authUser == null}">
			            <button id="addReplyButton" class="btn btn-primary float-end" disabled>댓글 등록</button>
					</c:if>
			        </div>
			    </div>
		</form>

		<div class="comments-container">
	   		<div class="comments-list">
	        	<c:forEach var="replyItem" items="${communityData.reply}">
	            	<div class="comment-item">
	                	<div class="comment-info">
	                    	<span class="comment-author">${replyItem.memid}</span>
	                    	<span class="comment-date">${replyItem.date}</span>
	                	</div>
	                	<div class="comment-content">
	                    	${replyItem.rcontent}
	                	</div>
	                	<div class="comment-buttons">
							<c:if test="${authUser != null && (authUser.grade == 9999 || authUser.id == replyItem.memid)}">
	    						<button class="btn btn-warning edit-reply-btn" data-bno="${replyItem.bno}" data-rno="${replyItem.rno}" data-memid="${replyItem.memid}">수정</button>
	    						<button type="submit" class="btn btn-danger delete-reply-btn" data-bno="${replyItem.bno}" data-rno="${replyItem.rno}" data-memid="${replyItem.memid}">삭제</button>
							</c:if>
						</div>
	                </div>
	        	</c:forEach>
	    	</div>
		</div>
		<br/>
		<br/>
		<br/>
	</div>
</div>   
     
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>

<script>
	document.getElementById("addReplyButton").addEventListener("click", function() {
	    // 댓글 등록 폼 가져오기
	    var addReplyForm = document.getElementById("addReplyForm");
	    
	    // 폼을 submit하여 댓글 추가 기능 수행
	    addReplyForm.submit();
	});
	
	// 수정 버튼 클릭 시 이벤트 처리
	// 수정 버튼 클릭 시 이벤트 처리
	document.addEventListener('click', function(event) {
    // 수정 버튼인 경우
    if (event.target.classList.contains('edit-reply-btn')) {
        // 수정할 댓글의 번호
        var replyNo = event.target.dataset.rno;
        // 게시물 번호
        var boardNo = event.target.dataset.bno;
        
        // 수정할 내용 입력 받기 (예시로 간단하게 prompt 사용)
        var newContent = prompt('댓글을 수정하세요:');
        
        // 사용자가 입력한 내용이 있는 경우에만 수정 요청 보냄
        if (newContent !== null) {
            // 폼 생성
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/community/reply.do?command=updateReply';

            // 파라미터 추가
            var replyNoInput = document.createElement('input');
            replyNoInput.type = 'hidden';
            replyNoInput.name = 'rno';
            replyNoInput.value = replyNo;
            form.appendChild(replyNoInput);

            var boardNoInput = document.createElement('input');
            boardNoInput.type = 'hidden';
            boardNoInput.name = 'bno';
            boardNoInput.value = boardNo;
            form.appendChild(boardNoInput);

            var contentInput = document.createElement('input');
            contentInput.type = 'hidden';
            contentInput.name = 'rcontent';
            contentInput.value = newContent;
            form.appendChild(contentInput);

            // 폼을 바디에 추가하고 서브밋
            document.body.appendChild(form);
            form.submit();
        }
    }
});


	
	//삭제 버튼 클릭 시 이벤트 처리
	document.addEventListener('click', function(event) {
    // 삭제 버튼인 경우
    if (event.target.classList.contains('delete-reply-btn')) {
        // 삭제할 댓글의 번호
        var replyNo = event.target.dataset.rno;
        // 게시물 번호
        var boardNo = event.target.dataset.bno;
        
        // 사용자에게 삭제 확인 메시지 표시
        if (confirm('댓글을 삭제하시겠습니까?')) {
            // 폼 생성
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/community/reply.do?command=removeReply';

            // 파라미터 추가
           
            var replyNoInput = document.createElement('input');
            replyNoInput.type = 'hidden';
            replyNoInput.name = 'rno';
            replyNoInput.value = replyNo;
            form.appendChild(replyNoInput);

            var boardNoInput = document.createElement('input');
            boardNoInput.type = 'hidden';
            boardNoInput.name = 'bno';
            boardNoInput.value = boardNo;
            form.appendChild(boardNoInput);

            // 폼을 바디에 추가하고 서브밋
            document.body.appendChild(form);
            form.submit();
        }
    }
});
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>