<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>まにまに</title>
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
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
		<h1 id="login-logo">
		<img src="<c:url value='/images/manimani-logo.svg'/>" alt="まにまにロゴ">
        </h1>
		<form id="name" method="POST" action="<c:url value='/SignupConfServlet' />" autocomplete="off">
		<label for="nickname">ニックネーム</label>
		<input type="text" name="nickname" id="nickname" autocomplete="off" value="<%= request.getAttribute("beforeName") != null ? request.getAttribute("beforeName") : ""%>">
		
		
		<label for="category">目標ジャンル</label>
            <select name="categoryId" id="category" class="company-select" required>
      			<c:forEach var="category" items="${categoryList}">
					<option value="${category.categoryId}"
					 <c:if test="${category.categoryId == beforeCategory}">selected</c:if>>
					 ${category.categoryTitle}</option>
				</c:forEach>
            </select>			
			
		
		<label for="goalDetail">目標内容（２５文字以内）</label>
		<span class="error" id="goalDetailError"></span>
		<input type="text" name="goalDetail" id="goalDetail" maxlength="25" placeholder="例：　目指せ-5kg" autocomplete="off"  onkeyup="countGoalDetail(value)" value="${beforeGoalDetail != null ? beforeGoalDetail : '' }">
		
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
		document.getElementById("name").addEventListener("submit", function(event){
			
			// 入力値を取得
		    let nickname = document.getElementById("nickname").value.trim();
		    let category = document.getElementById("category").value.trim();
		    //let goalDetail = document.getElementById("goalDetail").value.trim();

		    // エラーメッセージ表示領域
		    let formError = document.getElementById("formError");
		    formError.textContent = "";

		    // 未入力チェック
		    if (!nickname || !category) {
		      formError.textContent = "ニックネームをを入力してください";
		      event.preventDefault(); // フォーム送信中止
		    }
		  });
		
		document.getElementById("back").addEventListener("click", function (){
			window.location.href = "<c:url value='/SignupServlet' />";
		});
	
		function countGoalDetail(value){
			if(value.length > 25){
				document.getElementById('goalDetailError').innerHTML = "入力できるのは25文字までです";
			}else{
				document.getElementById('goalDetailError').innerHTML = "";
			}
		}
	</script>
</body>
</html>