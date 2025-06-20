<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
	    
		    <div class="account-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
                <div class="account-view-section">
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">ニックネーム</p>
                        <p class="account-view-text">：${login_user.nickname}</p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p class="account-view-text">：${login_user.email}</p>
                    </div>
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">パスワード</p>
                        <p class="account-view-text">：登録済み</p>
                    </div>
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">目標ジャンル</p>
                        <p class="account-view-text">：${login_user.category.categoryTitle}</p>
                    </div>
                    <div class="account-section account-view-section">
                        <p class="account-view-title">目標内容</p>
                        <p class="account-view-text">：${login_user.goalDetail}</p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="/F1/TopPageServlet" class="button back-to-top-button">TOPへ戻る</a>
		    	<a href="/F1/AccountEditServlet" class="button account-update-button">
                    <span class="pencil-back-ground">
                        <img src="images/pencil.svg" alt="">
                    </span>
                    <span class="account-update-text">
                        内容を変更する
                    </span>
                </a>
				<button type="button" class="button logout-button" onclick="openLoguotModal()">
        			
                    <div class="pencil-back-ground">
                        <img src="images/logout.svg" alt="">
                    </div>
                    <p class="account-update-text logout-button-text">
                        ログアウト
                    </p>
      			</button>
		    </div>
	    </main>
	</div>

<%@ include file="logout.jsp" %>
<script src="/F1/js/popup.js"></script>
</body>
</html>