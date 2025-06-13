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
		margin-top: 5px;s
	}

</style>
</head>
<body>
	<div class="login-container">
		<h1>まにまに</h1>
		<form id="name" method="POST" action="<%= request.getContextPath() %>/SignupConfServlet" autocomplete="off">
		<label for="name">ニックネーム</label>
		<input type="text" name="nickname" id="name" autocomplete="off" value="<%= request.getAttribute("beforeName") != null ? request.getAttribute("beforeName") : ""%>">
		
		
		<label for="category">目標ジャンル</label>
		<select id="category" name="categoryId">
			<option value="1" <%= "運動・ストレッチ".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>運動・ストレッチ</option>
			<option value="2" <%= "セルフケア".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>セルフケア</option>
			<option value="3" <%= "スキルアップ".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>スキルアップ</option>
			<option value="4" <%= "環境リセット".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>環境リセット</option>
			<option value="5" <%= "運動・ストレッチ".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>趣味</option>
			<option value="6" <%= "運動・ストレッチ".equals(request.getAttribute("beforeCategory")) ? "selected" : ""%>>キャリアアップ</option>
		</select>
		
		
		<label for="goal">目標詳細（２５文字以内）</label>
		<input type="text" name="goalDetail" id="goal" autocomplete="off" value="<%= request.getAttribute("beforeGoal") != null ? request.getAttribute("beforeGoal") : "" %>">
		
		<div id="formError" class="error"></div>
		
		<div  class="error">
			<%= request.getAttribute("loginErrorMessage") != null ? request.getAttribute("loginErrorMessage"): "" %>
		</div>
		
		<div class="button-wrapper">
			<button type="button" id="back" class="light-orange-btn">戻る</button>
			<input type="submit" id="check" class="orange-btn" name="login" value="確認する">
		</div>
	
		</form>
	</div>
	
	<script >
		
		document.getElementById("back").addEventListener("click", function (){
			window.location.href = "<%= request.getContextPath() %>/SignupServlet";
		});
	
	</script>
</body>
</html>