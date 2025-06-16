<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>アカウント管理 | まにまに</title>
	<link rel="stylesheet" href="/webapp/css/account.css">
</head>
<body>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="login-main">
	    
		    <div class="account-body login-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
		        <div class="account-section">
		            <p>ニックネーム</p>
		            <p>${user.nickname}</p>
		        </div>
		        <div class="account-section">
		            <p>メールアドレス</p>
		            <p>${user.email}</p>
		        </div>
		        <div class="account-section">
		            <p>パスワード</p>
		            <p>${user.password}</p>
		        </div>
				<div class="account-section">
		            <p>目標ジャンル</p>
		            <p>${category.category_title}</p>
		        </div>
				<div class="account-section">
		            <p>目標内容</p>
		            <p>${user.goalDetail}</p>
		        </div>

            </div>
		    <div class="account-button-section">
		    	<a href="/F1/TopPageServlet" class="back-to-top-button">TOPへ戻る</a>
		    	<a href="/F1/AccountUpdateServlet" class="account-update-button">内容を変更する</a>
				<button type="button" class="logout-button" onclick="openLoguotModal()">
        			ログアウト
      			</button>
		    </div>
	    </main>
	</div>
<%@ include file="logout.jsp" %>
<script src="/F1/js/popup.js"></script>
</body>
</html>