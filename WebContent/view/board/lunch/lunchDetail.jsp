<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/lunchDetailStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
  	  	
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 점심메뉴추천 글보기</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>

<div class="container">
<br>
<br>
<br>
	<div class="square-box">
		<p class="main-text">점메추</p>
		<p class="sub-text">학원 근처 맛집을 소개해주세요.</p>
		<img src="<%=request.getContextPath() %>/imgs/lunch.svg" alt="SVG Icon" class="r-icon">
	</div>
	<hr style="clear:both;"/>
	
	<!-- 글 보기 -->
	<p style="margin-bottom: 5px;">작성자: ${lunchData.lunch.memId}</p>
	<!-- p태그에 id값 부여해서 자바스크립트 실행 -->
	<p><span id="regDate" style="margin-bottom: 5px; margin-right: 5px;">${lunchData.lunch.formattedRegDate}</span>
	<span id="hit"><i class="bi bi-eye" style="margin-right: 3px;"></i>${lunchData.lunch.hit}</span></p>
	<br>
	<h2 class="logo" style="white-space: pre-wrap; overflow-wrap: break-word;">${lunchData.lunch.title}</h2>
	
	<main class="main">
		<div style="text-align: center; padding: 30px;">
		<img src="${pageContext.request.contextPath}/upload/${lunchData.filerealname}" alt="${lunchData.filerealname}" width="70%" height="70%" >
		</div>
		<div class="post" >
			<p class="post-content" style="white-space: pre-wrap; overflow-wrap: break-word;">${lunchData.content}</p>
		</div>
		<br>
		<br>
		<br>
	</main>
	<hr style="clear:both;"/>
	<!-- 글목록 버튼 -->
	<div class="item">
		<form action="/lunch/list.do" method="post">
			<button class="next">목록</button>
		</form>
		
		<!-- 글수정&글삭제 버튼(해당 글 작성자만 보이도록) -->
		<div style="margin-left: auto;">
			<c:if test="${authUser != null && (authUser.grade == 9999 || authUser.id == lunchData.lunch.memId)}">
		    	<div class="form-group row">
		        	<div class="button-container" style="margin-bottom: 15px; justify-content: flex-end;">
		            	<c:if test="${authUser.id == lunchData.lunch.memId}">
		                	<!-- 현재 로그인한 사용자가 글 작성자인 경우에만 수정 버튼이 나오도록 -->
		                	<form id="editForm" action="/lunch/modify.do" method="get">
		                    	<input type="hidden" name="no" value="${lunchData.lunch.bno}">
		                    	<button type="submit" class="custom-button">글수정</button>
		                	</form>
		            	</c:if>
		            	<!-- 모달 버튼 -->
		            	<button type="button" class="custom-button" id="deleteModalButton" data-bs-toggle="modal" data-bs-target="#deleteModal" style="margin-top:-20px;">글삭제</button>
		        	</div>
		    	</div>
			</c:if>
		</div>
	</div>
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
                <form id="deleteForm" action="/lunch/delete.do" method="post">
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
		<form id="addReplyForm" action="/lunch/reply.do" method="post">
		    <input type="hidden" name="command" value="addReply"> 
		    <input type="hidden" name="no" value="${param.no }"> 
		    <div class="form-group row">
		        <label for="rcontent" class="col-sm-2 col-form-label"><strong>댓글 내용</strong></label>
		        <div class="col-sm-10">
		        <c:if test="${authUser != null}">
		            <textarea name="rcontent" class="form-text1" id="rcontent" style="width: 120%;" onKeyUp="javascript:fnChkByte(this,'1500')"></textarea>
		        </c:if>
		        <c:if test="${authUser == null}">
		            <textarea name="rcontent" class="form-text1" id="rcontent" readonly="readonly" style="width: 120%;">작성하려면 로그인이 필요합니다.</textarea>
		        </c:if>
		        </div>
		    </div>
	    <div class="form-group row">
	        <div class="col-sm-10 offset-sm-2">
	        <c:if test="${authUser != null}">
	            <button id="addReplyButton" class="custom-button float-end">댓글 등록</button>
	        </c:if>
	        <c:if test="${authUser == null}">
	            <button id="addReplyButton" class="custom-button float-end" disabled>댓글 등록</button>
	        </c:if>
	        </div>
	    </div>
		</form>
		<br/>
		<div class="comments-container">
	   		<div class="comments-list">
	        	<c:forEach var="replyItem" items="${lunchData.reply}">
	            	<div class="comment-item">
		            	<div style="margin-top: 18px">
		                	<div class="comment-info">
		                    	<span class="comment-author" style="font-size: 13px;">${replyItem.memid}</span>
		                    	<span class="comment-date" style="font-size: 13px;">${replyItem.formattedRegDate}</span>
		                	</div>
		                	<div class="comment-content"  style="font-size: 18px; margin-top:10px; margin-bottom:5px; white-space: pre-wrap; overflow-wrap: break-word;">${replyItem.rcontent}</div>
	                	</div>
	                	<div class="comment-buttons">
							<c:if test="${authUser != null && (authUser.grade == 9999 || authUser.id == replyItem.memid)}">
	    						<button class="btn-modify-delete edit-reply-btn" data-bno="${replyItem.bno}" data-rno="${replyItem.rno}" data-memid="${replyItem.memid}">수정</button>
	    						<button type="submit" class="btn-modify-delete delete-reply-btn" data-bno="${replyItem.bno}" data-rno="${replyItem.rno}" data-memid="${replyItem.memid}">삭제</button>
							</c:if>
							<hr style="border: none; height: 0.5px; margin-top: 25px;"/>
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
	} else if (seconds > 0) {
		displayText = seconds + '초 전';
	} else {
		displayText = '방금 전';
	}
	regDateElement.textContent = displayText;
	
	document.getElementById("addReplyButton").addEventListener("click", function() {
	    // 댓글 등록 폼 가져오기
	    var addReplyForm = document.getElementById("addReplyForm");
	    
	    // 댓글 내용 가져오기
	    var replyContent = addReplyForm.querySelector("textarea[name='rcontent']").value.trim();
	    
	    // 댓글 내용이 비어 있는지 확인
	    if (replyContent.length === 0) {
	        // 사용자에게 알림
	        alert("최소 1자 이상의 댓글 내용을 입력해주세요.");
	        // 폼 제출을 중지
	        event.preventDefault();
	    }
	    // 폼을 submit하여 댓글 추가 기능 수행
	    //addReplyForm.submit();
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
        
        //원래 댓글 내용 가져오기
        var originalContent = event.target.closest('.comment-item').querySelector('.comment-content').textContent.trim();
        
        // 수정할 내용 입력 받기 (예시로 간단하게 prompt 사용)
        var newContent = prompt('댓글을 수정하세요:', originalContent);
        
        // 사용자가 입력한 내용이 있는 경우에만 수정 요청 보냄
        if (newContent !== null) {
        	 if (newContent.trim().length > 0) {
            // 폼 생성
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/lunch/reply.do?command=updateReply';

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
        }else {
            // 사용자가 내용을 입력하지 않았거나 최소 1자 이상이 아닌 경우 메시지 표시
            alert("댓글을 최소 1자 이상 입력해주세요.");
        }
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
            form.action = '/lunch/reply.do?command=removeReply';

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