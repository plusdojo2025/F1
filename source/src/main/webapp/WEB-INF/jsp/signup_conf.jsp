<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
</head>
<body>
	<div class="login-container2">
		<h2 class="card-label">登録内容の確認</h2>
		
		<table>
			<tr>
			  <th>ニックネーム</th>

			  <td class="ie">:&nbsp;&nbsp;&nbsp;&nbsp;${signup_user.nickname}</td>
			</tr>
			<tr>
			  <th>メールアドレス</th>
			  <td class="ie">:&nbsp;&nbsp;&nbsp;&nbsp;${signup_user.email}</td>
			</tr>
			<tr>
			  <th>パスワード</th>
			  <td class="ie">:&nbsp;&nbsp;&nbsp;&nbsp;${signup_user.password}</td>
			</tr>
			<tr>
			  <th>目標ジャンル</th>
			  <td class="ie">:&nbsp;&nbsp;&nbsp;&nbsp;${signupCategory.categoryTitle}</td>
			</tr>
			<tr>
			  <th>目標内容</th>
			  <td class="ie">:&nbsp;&nbsp;&nbsp;&nbsp;${signup_user.goalDetail}</td>
			</tr>
		</table>
	<form action="<c:url value='/SignupExecuteServlet' />" method="post">
	    <input type="hidden" name="action" value="signup_complete">
	    <input type="hidden" name="nickname" value="${signup_user.nickname}">
	    <input type="hidden" name="email" value="${signup_user.email}">
	    <input type="hidden" name="password" value="${signup_user.password}">
	    <input type="hidden" name="categoryId" value="${signup_user.categoryId}">
	    <input type="hidden" name="goalDetail" value="${signup_user.goalDetail}">
	
	    <div class="button-wrapper">
	        <button type="button" id="back2" class="light-orange-btn">戻る</button>
	        <input type="submit" id="check2" class="orange-btn" value="この内容で登録">
	    </div>
	</form>
	</div>
	
	<script >
		
		document.getElementById("back2").addEventListener("click", function (){
			window.location.href = "<c:url value='/Signup2Servlet' />";
		});
	
	</script>
</body>
</html>













