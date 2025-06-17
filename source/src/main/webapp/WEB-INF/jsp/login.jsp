<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>まにまに</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
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
			<button type="button" id="togglePassword" style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%); background: none; border: none; cursor: pointer;">
              👁
            </button>
			
		</div>
		
		<div  class="error">
			<%= request.getAttribute("loginErrorMessage") != null ? request.getAttribute("loginErrorMessage"): "" %>
		</div>
		
		<input type="submit" class="green-btn" name="login" value="ログイン">
		<p style="font-size: 40px;">or</p>
		<div class="container">
			<a  href="/F1/SignupServlet" type="submit" class="login-orange-btn" name="signup" >新規作成</a>
		</div>
		
	
	</form>
	</div>
	<script >
		document.getElementById("loginForm").addEventListener("submit", function(event){
			
			const clickedBtn = docment.getElemntById("clickedBtn").value;
			
			if (clickedBtn === "signup"){
				return;
			}
			
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
		
		document.getElementById("togglePassword").addEventListener("click", function () {
		    const pwField = document.getElementById("password");
		    const isHidden = pwField.type === "password";
		    pwField.type = isHidden ? "text" : "password";
		    this.textContent = isHidden ? "🙈" : "👁"; // アイコンを切り替える
		  });
	</script>
</body>
</html>