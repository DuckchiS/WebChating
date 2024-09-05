<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.webchat.dto.UserDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채팅방 목록</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>채팅방 목록</h1>

    <div id="chatRoomList">
        <ul>
            <c:forEach var="room" items="${chatRooms}">
                <li><a href="/chating/chatRoom/${room.chatRoomNo}">${room.chatRoomNo}번 방</a></li>
            </c:forEach>
        </ul>
    </div>

    <h2>채팅방 생성</h2>
    <form id="createRoomForm">
        <label for="nickname">사용자 닉네임:</label>
        <input type="text" id="nickname" name="nickname" required>
        <button type="submit">채팅방 생성</button>
    </form>

    <script>
        $(document).ready(function() {
            const userNickname = '<c:out value="${loggedInUser.nickname}" />';

            function loadChatRooms() {
                if (userNickname !== "") {
                    $.ajax({
                        url: '/chating/rooms/user/' + userNickname,
                        type: 'GET',
                        success: function(data) {
                            $('#chatRoomList ul').empty();
                            $.each(data, function(index, room) {
                                $('#chatRoomList ul').append('<li><a href="/chating/chatRoom/' + room.chatRoomNo + '">' + room.chatRoomNo + '번 방</a></li>');
                            });
                        },
                        error: function() {
                            alert('채팅방 목록을 불러오지 못했습니다.');
                        }
                    });
                } else {
                    alert('유저 닉네임을 찾을 수 없습니다.');
                }
            }

            loadChatRooms();

            $('#createRoomForm').on('submit', function(event) {
                event.preventDefault();
                const nickname = $('#nickname').val();
                $.ajax({
                    url: '/chating/rooms',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ userNickname: nickname }),
                    success: function(response) {
                        alert(response.message);
                        loadChatRooms();
                    },
                    error: function() {
                        alert('채팅방을 생성하지 못했습니다.');
                    }
                });
            });
        });
    </script>
</body>
</html>