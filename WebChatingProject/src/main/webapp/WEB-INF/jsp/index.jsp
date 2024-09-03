<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>홈페이지</title>
</head>
<body>
    일단 웹 채팅 홈페이지입니다.
    <!-- 로그인 상태 체크 -->
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal != null}">
            <p>안녕하세요, ${pageContext.request.userPrincipal.name}님!</p> <!-- 로그인된 사용자 정보 출력 -->
            <a href="/board/list">게시판</a>
            <a href="/chating/chatRoomList">채팅방 목록</a>
            <a href="/user/logout">로그아웃</a>
        </c:when>
        
        <c:otherwise>
            <a href="/board/list">게시판</a>
            <a href="/user/login">로그인</a>
            <a href="/user/signup">회원가입</a>
        </c:otherwise>
    </c:choose>
</body>
</html>