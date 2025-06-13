<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
	<div class="login-container">
		<h2>登録内容の確認</h2>
		
		<table>
			<tr><th>ニックネーム</th><td>${signup_user.nickname}</td></tr>
			<tr><th>メールアドレス</th><td>${signup_user.email}</td></tr>
			<tr><th>パスワード</th><td>${signup_user.password}</td></tr>
			<tr><th>目標ジャンル</th><td>${signup_user.categoryId}</td></tr>
			<tr><th>目標内容</th><td>${signup_user.goalDetail}</td></tr>
		</table>
		
		<button type="button" id="back" class="light-orange-btn">戻る</button>
		<input type="submit" id="check" class="orange-btn" name="login" value="この内容で登録">
	</div>
</body>
</html>













