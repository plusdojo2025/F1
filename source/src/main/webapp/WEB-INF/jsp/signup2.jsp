<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
		<form id="name" method="POST" action="<%= request.getContextPath() %>/SignupConfServlet" autocomplete="off">
		<label for="nickname">ニックネーム</label>
		<input type="text" name="nickname" id="nicname" autocomplete="off" value="<%= request.getAttribute("beforeName") != null ? request.getAttribute("beforeName") : ""%>">
		
		
		<label for="category">目標ジャンル</label>
            <select name="categoryId" id="category" class="company-select" required>
      			<c:forEach var="category" items="${categoryList}">
					<option value="${category.categoryId}">${category.categoryTitle}</option>
				</c:forEach>
            </select>			
			
		
		<label for="goalDetail">目標詳細（２５文字以内）</label>
		<input type="text" name="goalDetail" id="goalDetail" maxlength="25" autocomplete="off" placeholder="例：　目指せ-5kg" value="<%= request.getAttribute("beforeGoal") != null ? request.getAttribute("beforeGoal") : "" %>">
		
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