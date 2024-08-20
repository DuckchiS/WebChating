<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
회원가입 페이지
<a href="/">홈페이지</a>
        <h2>회원가입</h2>
        <form action="/user/signupProc" method="post" name="signup">
            <table>
                <tr>
                    <td><label for="u_nickname">닉네임:</label></td>
                    <td><input id="u_nickname" type="text" name="u_nickname" placeholder="nickname" required></td>
                </tr>
                <tr>
                    <td><label for="u_id">아이디:</label></td>
                    <td><input id="u_id" type="text" name="u_id" placeholder="id" required></td>
                </tr>
                <tr>
                    <td><label for="u_pw">비밀번호:</label></td>
                    <td><input id="u_pw" type="password" name="u_pw" placeholder="password" required></td>
                </tr>
                <tr>
                    <td><label for="u_name">이름:</label></td>
                    <td><input id="u_name" type="text" name="u_name" placeholder="name" required></td>
                </tr>
                <tr>
                    <td><label for="u_email">이메일:</label></td>
                    <td><input id="u_email" type="text" name="u_email" placeholder="email" required></td>
                </tr>
                <tr>
                    <td><label for="u_phone">전화번호:</label></td>
                    <td><input id="u_phone" type="text" name="u_phone" placeholder="phoneNumber" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="회원가입">
                    </td>
                </tr>
            </table>
        </form>
</body>
</html>