<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
로그인 페이지
		<a href="/">홈페이지</a>
        <form action="loginProc" method="post" name="login" onsubmit="return validateForm()">
            <table>
                <tr>
                    <td><label for="username">아이디:</label></td>
                    <td><input type="text" id="username" name="username" required></td>
                </tr>
                <tr>
                    <td><label for="password">비밀번호:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="로그인"></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="/user/signup">회원가입하러가기</a></td>
                </tr>
            </table>
        </form>
</body>
</html>