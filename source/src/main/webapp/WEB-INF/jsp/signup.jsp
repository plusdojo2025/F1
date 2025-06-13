<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>まにまに</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
<style>
	.error {
		color: red;
		font-size: 0.85em;
		margin-top: 5px;
	}

</style>
</head>
<body>
	<div class="login-container">
		<h1>まにまに</h1>
		<form id="loginForm" method="POST" action="/F1/Signup2Servlet" autocomplete="off">
		<label for="email">メールアドレス</label>
		<input type="text" name="email" id="email" autocomplete="off" value="<%= request.getAttribute("beforeEmail") != null ? request.getAttribute("beforeEmail") : ""%>">
		
		
		<label for="pw">パスワード</label>
		<input type="password" name="password" id="password" autocomplete="new-password" value="<%= request.getAttribute("beforePassword") != null ? request.getAttribute("beforPassword") : ""%>">

		
		<label for="passwordConfirm">パスワード（確認用）</label>
		<input type="password" name="passwordConfirm" id="passwordConfirm" autocomplete="new-password" value="<%= request.getAttribute("beforePasswordConfirm") != null ? request.getAttribute("beforePasswordConfirm") : "" %>">
		
		<div id="formError" class="error"></div>
		
		<div  class="error">
			<%= request.getAttribute("loginErrorMessage") != null ? request.getAttribute("loginErrorMessage"): "" %>
		</div>
		
		<input type="submit" class="light-orange-btn" name="login" value="次へ">
	
		</form>
	</div>
	
	<script >
		document.getElementById("loginForm").addEventListener("submit", function(event){
			
			let email = document.getElementById("email").value.trim();
			let password = document.getElementById("password").value.trim();
			let passwordConfirm = document.getElementById("passwordConfirm").value.trim();
			
			let formError = document.getElementById("formError")

			
			//エラーメッセージ初期化
			formError.textContent = "";
			
			
			//空欄チェック
			if (!email || !password || !passwordConfirm){
				formError.textContent = "すべての項目を入力してください";
				event.preventDefault();
			}
			
			//パスワード確認
			if (password !== passwordConfirm){
				formError.textContent = "パスワードが一致しません";
				event.preventDefault();
			}
			
			if (hasError){
				event.preventDefault();
			}
		});
	</script>
</body>
</html>