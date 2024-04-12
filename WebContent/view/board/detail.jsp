<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="../css/dokkyCss/detail.css"> <!-- 스타일 시트 링크 -->
  	  	  	  	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
  	  	
  	  	
  	<link href="../imgs/DokkyLogo.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - All That Developer</title>
  	
  </head>
  <body>
  <%@ include file="../headerFooter/header.jsp" %>

  <div class="container">
    
		<br>
        <br>
        <br>
            <div class="square-box">
           <p class="main-text">게시글</p>
           <p class="sub-text">좋은 질문과 답변으로 동료의 시간을 아껴주세요.</p>
             <img src="../imgs/QA.svg" alt="SVG Icon" class="r-icon">
      </div>
        <hr style="clear:both;"/>
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
            
            <div class="comment-form">
                <form action="#" method="POST">
                    <div class="form-group">
                        <label for="comment">댓글</label>
                        <textarea id="comment" name="comment" rows="4" required></textarea>
                    </div>
                    <button type="button">댓글 작성</button>
                </form>
            </div>
		</div>       

  <div class="pagination-container">
          <div class="pagination">
             <a href="#">&laquo;</a>
              <a href="#">1</a>
              <a href="#" class="active">2</a>
              <a href="#">3</a>
              <a href="#">4</a>
              <a href="#">5</a>
              <a href="#">6</a>
              <a href="#">&raquo;</a>
              <br>
              <br>
              <br>
         </div>
      </div>
      </div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<%@ include file="../headerFooter/footer.jsp" %>

</body>
</html>