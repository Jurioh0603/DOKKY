<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/lunchSelectStyle.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 점심메뉴추천</title>

</head>
<body>
<%@ include file="../../headerFooter/header.jsp" %>

<!-- 사이드여백 -->
<div class="container">
	<div class="gallery-container">
		<br/>
		<br/>
		<br/>
	
		<!-- 게시글베너 -->
		<div class="square-box">
  			<p class="main-text">점메추</p>
  			<p class="sub-text">학원 근처 맛집을 소개해주세요.</p>
			<img src="<%=request.getContextPath() %>/imgs/lunch.svg" alt="SVG Icon" class="r-icon">
		</div>
		
		<!-- 글작성 -->
		<div class="button-container">
	  		<button class="custom-button">
	    		<span><img src="<%=request.getContextPath() %>/imgs/writeIcon.png" alt="write-icon"></span>
	    		<span>작성하기</span>
	  		</button>
		</div>
	
		<!-- 정렬바 -->
		<div class="dropdown" style="float:right;">
	  		<button class="dropbtn">
				<img src="<%=request.getContextPath() %>/imgs/selectIcon.png" alt="select-icon">최신순
			</button>
	  		<div class="dropdown-content">
	  			<a href="#">최신순</a>
	  			<a href="#">추천순</a>
	  			<a href="#">조회순</a>
	  			<a href="#">댓글순</a>
	  		</div>
		</div>
		
		<!-- 검색창 -->
		<div style="display: grid; place-items: center; text-align: center;">
			<form class="search-box" action="" method="get" >
				<input class="search-txt" type="text" name="" placeholder="검색어를 입력하세요."/>
				<input class="search-btn" type="image" src="<%=request.getContextPath() %>/imgs/searchIcon.png" title="search-icon"/>
			</form>
		</div>
		<hr style="clear:both;"/>
		
		<!-- 1 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/cu.jpeg" alt="cu" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EC%94%A8%EC%9C%A0+%EC%97%AD%EC%82%BC%EC%8B%9C%ED%8B%B0%EC%A0%90&oquery=%EC%94%A8%EC%9C%A0+%EA%B0%95%EB%82%A8%EC%97%AD%EC%A0%90&tqi=imOpjwqVOsVssANKIdlssssstP4-200340" title="갤러리텍스트">
					<span>우리학원 로컬맛집 CU</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2024-01-25</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>이윤서</span>
			</div>
		</div>
		
		<!-- 2 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/sushi.jpeg" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EA%B0%95%EB%82%A8%EC%97%AD+%EC%9D%80%ED%96%89%EA%B3%A8&oquery=%EA%B0%95%EB%82%A8%EC%97%AD+%EB%A7%9B%EC%A7%91&tqi=imOEddqo1SossCgVxEZssssstBV-055905" title="갤러리텍스트">
					<span>강남역 은행골 강추!</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>오주리</span>
			</div>
		</div>
		
		<!-- 3 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/meat.png" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EB%B0%94%EC%9A%B0%EA%B3%A8%EC%B0%B8%EC%88%AF%ED%99%94%EB%A1%9C%EA%B5%AC%EC%9D%B4&oquery=%EA%B0%95%EB%82%A8%EC%97%AD+%EC%9D%80%ED%96%89%EA%B3%A8&tqi=imOolsqVOsCssB7%2FmQVssssst2w-469866" title="갤러리텍스트">
					<span>바우골참숯화로구이 숯불냄새 폴폴</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-0625</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>김경애</span>
			</div>
		</div>
		
		<!-- 4 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/exam4.png" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://demo.mangboard.com/board2/?vid=196" title="갤러리텍스트">
					<span>광장 818</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>홍길동</span>
			</div>
		</div>
		
		<!-- 5 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/exam1.jpg" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://demo.mangboard.com/board2/?vid=196" title="갤러리텍스트">
					<span>광장 818</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>홍길동</span>
			</div>
		</div>
		
		<!-- 6 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/exam1.jpg" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://demo.mangboard.com/board2/?vid=196" title="갤러리텍스트">
					<span>광장 818</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>홍길동</span>
			</div>
		</div>
		
		<!-- 7 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/exam1.jpg" alt="Cinque Terre" width="600" height="400">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://demo.mangboard.com/board2/?vid=196" title="갤러리텍스트">
					<span>광장 818</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>홍길동</span>
			</div>
		</div>
		
		<!-- 8 -->
		<div class="gallery-item-box-col-321">
			<div class="gallery-item-img">
				<a target="_blank" href="img_5terre.jpg">
      				<img src="<%=request.getContextPath() %>/imgs/exam1.jpg" alt="Cinque Terre" width="280" height="150">
    			</a>
			</div>
			<div class="gallery-title">
				<a style="text-decoration:none; color:black;" href="https://demo.mangboard.com/board2/?vid=196" title="갤러리텍스트">
					<span>광장 818</span>
				</a>
			</div>
			<div class="gallery-date">
				<span>2023-09-06</span> | <span>143</span>
			</div>
			<div class="gallery-name">
				<span title="홍길동" class="btn-user-info"></span>
				<span>홍길동</span>
			</div>
		</div>
		<div class="clearfix"></div>
		<br/>
		<br/>
		<hr/>
		<!-- 페이지네이션 -->
		<div class="pagination-container">
    		<div class="pagination" style="margin-top:-50px">
		 		<a href="#">&laquo;</a>
		  		<a href="#">1</a>
		  		<a href="#" class="active">2</a>
		  		<a href="#">3</a>
		  		<a href="#">4</a>
		  		<a href="#">5</a>
		  		<a href="#">6</a>
		  		<a href="#">&raquo;</a>
			</div>
		</div>
		<br/>
		<br/>
		<br/>
				
		<!-- footer -->
	</div>
</div>
<%@ include file="../../headerFooter/footer.jsp" %>

<!-- Scripts -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="app.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>	
</body>
</html>