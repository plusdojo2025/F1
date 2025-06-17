<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>アカウント情報編集 | まにまに</title>
	<link rel="stylesheet" type="text/css" href="account.css">
</head>
<body>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
	    <form action="/F1/AccountConfServlet" class="account-update-form">
		    <div class="account-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
                
                <div class="account-view-section">
                    
                    <div class="account-section under-line">
                        <p class="account-view-title">ニックネーム</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="text" name="nickname" class="account-input" value="${user.nickname}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="text" name="email" class="account-input" value="${user.email}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">パスワード</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="password" name="password" class="account-input" value="${user.password}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">目標ジャンル</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <select name="categoryId" class="account-input" required>
                                <option value="${category.category_id}">${category.category_title}</option>
                            </select>
                        </p>
                    </div>
                    <div class="account-section ">
                        <p class="account-view-title">目標内容</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <input type="text" name="goalDetail" class="account-input goal-detail-input" value="${user.goalDetail}">
                        </p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="/F1/TopPageServlet" class="button back-to-top-button">
                    キャンセル
                </a>

				<button type="submit" class="button account-update-button">
                    <p class="account-update-text">
                        確認する
                    </p>
      			</button>
		    </div>
        </form>
	    </main>
	</div>
</body>
</html>