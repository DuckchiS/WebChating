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
                    <td><label for="m_nickname">닉네임:</label></td>
                    <td><input id="m_nickname" type="text" name="m_nickname" placeholder="nickname" required></td>
                </tr>
                <tr>
                    <td><label for="m_id">아이디:</label></td>
                    <td><input id="m_id" type="text" name="m_id" placeholder="id" required></td>
                </tr>
                <tr>
                    <td><label for="m_pw">비밀번호:</label></td>
                    <td><input id="m_pw" type="password" name="m_pw" placeholder="password" required></td>
                </tr>
                <tr>
                    <td><label for="m_name">이름:</label></td>
                    <td><input id="m_name" type="text" name="m_name" placeholder="name" required></td>
                </tr>
                <tr>
                    <td><label for="m_email">이메일:</label></td>
                    <td><input id="m_email" type="text" name="m_email" placeholder="email" required></td>
                </tr>
                <tr>
                    <td><label for="m_number">전화번호:</label></td>
                    <td><input id="m_number" type="text" name="m_number" placeholder="phoneNumber" required></td>
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