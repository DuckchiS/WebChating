<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채팅방 - ${param.chatRoomNo}</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>채팅방 번호: ${param.chatRoomNo}</h1>
    
    <div id="messageList">
        <ul>
            <!-- 서버에서 받아온 메시지 목록을 표시 -->
        </ul>
    </div>

    <form id="sendMessageForm">
        <input type="text" id="chatMessage" placeholder="메시지를 입력하세요" required>
        <button type="submit">보내기</button>
    </form>

    <script>
        const chatRoomNo = ${param.chatRoomNo}; // 채팅방 번호
        const socket = new WebSocket("ws://localhost:8080/ws/chat");

        // 웹소켓 메시지 수신 처리
        socket.onmessage = function(event) {
            const message = event.data;
            $('#messageList ul').append('<li>' + message + '</li>');
            $('#messageList').scrollTop($('#messageList')[0].scrollHeight); // 자동 스크롤
        };

        // 메시지 전송
        $('#sendMessageForm').on('submit', function(event) {
            event.preventDefault();
            const message = $('#chatMessage').val();
            socket.send(message); // 웹소켓을 통해 메시지 전송
            $('#chatMessage').val(''); // 입력창 초기화
        });

        // 웹소켓 연결 종료 시
        socket.onclose = function() {
            alert('서버와의 연결이 종료되었습니다.');
        };

        // 웹소켓 오류 처리
        socket.onerror = function(error) {
            console.error('WebSocket error: ', error);
        };
    </script>
</body>
</html>