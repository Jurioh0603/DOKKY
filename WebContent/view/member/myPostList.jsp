<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>내 글 목록</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="../../css/dokkyCss/memberStyle.css"/>

<style>
    input[type="checkbox"] {
        transform: scale(1.7); /* 기본 크기의 1.7배로 확대 */
    }
</style>

</head>
<body>
<%@ include file="../headerFooter/header.jsp" %>

<div class="d-flex">
	<div class="d-flex flex-column flex-shrink-0 ps-5 pt-5 side-bar ms-5" style="width: 280px;">
  		<ul class="nav nav-pills flex-column mb-auto">
    		<li class="nav-item l1">
      			<a href="../member/myPage.jsp" class="nav-link link-dark">내 정보</a>
    		</li>
    		<li class="nav-item l1">
	      		<a class="nav-link link-dark active">내 글 목록</a>
	    	</li>
  		</ul>
	</div>

	<hr/>

	<div class="m-5 flex-grow-1">
		<h2>내 글 목록</h2>
		<h6 style="color: gray;">제목 클릭 시 글 링크로 이동합니다.</h6>
		<div class="container mt-4">
			<form action="#" method="post">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">글번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성일자</th>
							<th scope="col">조회수</th>
							<th scope="col">선택</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="1" end="10">
							<tr>
								<td style="width: 100px">1</td>
								<td><a href="#">안녕하세요</a></td>
								<td>2024-04-11</td>
								<td>12</td>
								<td><input type="checkbox"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-end mb-5">
					<button type="button" class="btn btn-primary me-2" onclick="selectAll()">모두 선택</button>
				    <button type="submit" class="btn btn-danger">삭제</button>
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
         			</div>
      			</div>
			</form>
		</div>
	</div>
</div>

<script>
    var allChecked = false;

    function selectAll() {
        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = !allChecked;
        });
        allChecked = !allChecked;
    }
</script>

<%@ include file="../headerFooter/footer.jsp" %>
</body>
</html>