<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <!-- 서버에서 받아온 채팅방 목록을 여기에 표시 -->
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
            // 채팅방 목록 불러오기
            function loadChatRooms() {
                $.ajax({
                    url: '/chating/rooms/user/${sessionScope.loggedInUser.userNickname}',
                    type: 'GET',
                    success: function(data) {
                        $('#chatRoomList ul').empty();
                        $.each(data, function(index, room) {
                            $('#chatRoomList ul').append('<li><a href="/chating/rooms/' + room.chatRoomNo + '">' + room.chatRoomNo + '번 방</a></li>');
                        });
                    },
                    error: function() {
                        alert('채팅방 목록을 불러오지 못했습니다.');
                    }
                });
            }

            loadChatRooms();

            // 채팅방 생성
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
                        loadChatRooms(); // 새로 만든 방 목록에 추가
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