<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>내 글 목록</title>
    <link href="../../imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/dokkyCss/memberStyle.css"/>
    <style>
        input[type="checkbox"] {
            transform: scale(1.3); /* 기본 크기의 n배로 확대 */
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
                <div class="dropdown">
                    <a class="nav-link link-dark dropdown-toggle" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">내 글 목록</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="dropdown-item" href="#">Q&A</a></li>
                        <li><a class="dropdown-item" href="#">자유게시판</a></li>
                        <li><a class="dropdown-item" href="#">스터디 모집</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
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
                        <c:forEach items="${studyPage.studyList}" var="study">
                            <tr>
                                <td style="width: 100px">${study.bno}</td>
                                <td><a href="#">${study.title}</a></td>
                                <td>${study.regDate}</td>
                                <td>${study.hit}</td>
                                <td><input type="checkbox" name="selectedPosts" value="${study.bno}"></td>
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
                        <c:forEach begin="1" end="${studyPage.totalPages}" var="page">
                            <a href="?pageNo=${page}" <c:if test="${page == studyPage.currentPage}">class="active"</c:if>>${page}</a>
                        </c:forEach>
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
