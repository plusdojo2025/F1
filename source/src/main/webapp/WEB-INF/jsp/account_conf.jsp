<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
		<form method="POST" action="/F1/AccountUpdateServlet" class="account-update-form">
   
		    <div class="account-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
                <div class="account-view-section">
                    <div class="account-section under-line">
                        <p class="account-view-title">ニックネーム</p>
                        <p>：${login_user.nickname}</p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p>：${login_user.email}</p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">パスワード</p>
                        <p>：${login_user.password}</p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">目標ジャンル</p>
                        <p>：${login_user.category.categoryTitle}</p>
                    </div>
                    <div class="account-section ">
                        <p class="account-view-title">目標内容</p>
                        <p>：${login_user.goalDetail}</p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="/F1/TopPageServlet" class="button back-to-top-button">
		    		TOPへ戻る
		    	</a>
				<input type="submit" class="button account-update-button account-update-text" value="この内容で確定">
		    	
		    </div>
	    </form>
	    </main>
	</div>

<%@ include file="logout.jsp" %>
<script src="/F1/js/popup.js"></script>
</body>
</html>