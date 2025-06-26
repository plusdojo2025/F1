<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="まにまに｜アカウント変更情報確認" />
<%@ include file="header.jsp" %>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
		<form method="POST" action="<c:url value='/AccountUpdateServlet' />" class="account-update-form">
   
		    <div class="account-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
                <div class="account-view-section">
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">ニックネーム</p>
                        <p class="account-view-text">：${update_user.nickname}</p>
                    </div>
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p class="account-view-text">：${update_user.email}</p>
                    </div>
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">パスワード</p>
                        <c:choose>
							<c:when test="${passwordCheck == true}">
								<p class="account-view-text">：${update_user.password}</p>
							</c:when>
							<c:otherwise>
								<p class="account-view-text">：パスワードの変更はありません。</p>
							</c:otherwise>
						</c:choose>
                        
                    </div>
                    <div class="account-section account-view-section under-line">
                        <p class="account-view-title">目標ジャンル</p>
                        <p class="account-view-text">：${update_user.category.categoryTitle}</p>
                    </div>
                    <div class="account-section account-view-section">
                        <p class="account-view-title">目標内容</p>
                        <p class="account-view-text">：${update_user.goalDetail}</p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="<c:url value='/AccountEditServlet' />" class="button back-to-top-button">
		    		変更画面に戻る
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