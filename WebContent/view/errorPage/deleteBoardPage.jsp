<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>
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

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<link href="../../css/dokkyCss/invalidAccessPageStyle.css" rel="stylesheet">
</head>
<body class="text-center">
	<main class="mx-auto my-20 w-full max-w-xl sm:my-32">
		<img class="mb-4" src="../../imgs/errorIMG.png" alt="DOKKY" width="500">
		
		<h1 style="font-weight: 800;">This page does not exist.</h1>
		<p style="color: gray;">죄송합니다. 찾으시려는 페이지는 존재하지 않습니다.</p>
		<button type="button" class="btn btn-outline-primary" onclick="history.back()">이전페이지로 돌아가기</button>
		<!--  <button type="button" class="btn btn-outline-primary" onclick="location.href='/main.do'">게시판으로 돌아가기</button>-->
	</main>
</body>
</html>