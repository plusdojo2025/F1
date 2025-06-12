<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login-container">
		<h1>まにまに</h1>
		<form id="loginForm" method="POST" action="/F1/LoginServlet">
		<label for="email">メールアドレス</label>
		<input type="text" name="email" id="email" value="<%= request.getAttribute("beforeEmail") != null ? request.getAttribute("beforeEmail") : ""%>">
		<div id="emailError" class="error">
			<%= request.getAttribute("emailErrorMessage") != null  ? request.getAttribute("emailErrorMessage") : "" %>
		</div>
		
		<label for="pw">パスワード</label>
		<input type="password" name="password" id="password" value="<%= request.getAttribute("beforePassword") != null ? request.getAttribute("beforPassword") : ""%>">
		<div id="passwordError" class="error">
			<%= request.getAttribute("passwordErrorMessage") != null  ? request.getAttribute("passwordErrorMessage") : "" %>
		</div>
		
		<label for="passwordConfirm">パスワード（確認用）</label>
		<input type="password" name="passwordConfirm" id="passwordConfirm" value="<%= request.getAttribute("beforePasswordConfirm") != null ? request.getAttribute("beforePasswordConfirm") : "" %>">
		<div id="passwordConfirmError" class="error">
		    <%= request.getAttribute("passwordConfirmErrorMessage") != null ? request.getAttribute("passwordConfirmErrorMessage") : "" %>
		</div>
		
		
		<div  class="error">
			<%= request.getAttribute("loginErrorMessage") != null ? request.getAttribute("loginErrorMessage"): "" %>
		</div>
		
		<input type="submit" class="light-orange-btn" name="login" value="次へ">
	
	</form>
	</div>
</body>
</html>