<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>まにまに | ログイン</title>
	<link rel="stylesheet" href="<c:url value='/css/login.css' />">
	<link rel="icon" href="<c:url value='/images/manimani-favicon.png' />" type="image/png">
	<style>
		.error{
			color: red;
			font-size: 0.9em;
			white-space: nowrap;
		}
	</style>
</head>
<body>

	<div class="login-container">
	<h1 id="login-logo">
		<img src="<c:url value='/images/manimani-logo.svg'/>" alt="まにまにロゴ">
    </h1>
	<form id="loginForm" method="POST" action="<c:url value='/LoginServlet' />">
		<label for="email">メールアドレス</label>
		<input type="text" name="email" id="email" value="<%= request.getAttribute("beforeEmail") != null ? request.getAttribute("beforeEmail") : ""%>">
		<div id="emailError" class="error">
			<%= request.getAttribute("emailErrorMessage") != null  ? request.getAttribute("emailErrorMessage") : "" %>
		</div>
		
		<label for="pw">パスワード</label>
		<input type="password" name="password" id="password" value="<%= request.getAttribute("beforePassword") != null ? request.getAttribute("beforePassword") : "" %>">
		<div id="passwordError" class="error">
			<%= request.getAttribute("passwordErrorMessage") != null  ? request.getAttribute("passwordErrorMessage") : "" %>
		</div>
		
		<div  class="error">
			<%= request.getAttribute("loginErrorMessage") != null ? request.getAttribute("loginErrorMessage"): "" %>
		</div>
		
		<input type="submit" class="green-btn" name="login" value="ログイン">
		<p style="font-size: 40px;">or</p>
		<div class="container">
			<a href="<c:url value='/SignupServlet' />" type="submit" class="login-orange-btn" name="signup" >新規作成</a>
		</div>
		
	
	</form>
	</div>
	<script >
		document.getElementById("loginForm").addEventListener("submit", function(event){
			
			let hasError = false;
			
			const email = document.getElementById("email").value.trim();
			const password = document.getElementById("password").value.trim();
			const emailError = document.getElementById("emailError");
			const passwordError = document.getElementById("passwordError");
			
			//エラーメッセージ初期化
			emailError.textContent = "";
			passwordError.textContent = "";
			
			
			if (!email){
				emailError.textContent = "この項目を入力してください";
				hasError = true;
			} 
			
			if (!password){
				passwordError.textContent = "この項目を入力してください";
				hasError = true;
			}
			
			if(hasError){
				event.preventDefault();
			}
		});
		
	</script>
</body>
</html>