<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
	    <form method="POST" action="/F1/AccountConfServlet" class="account-update-form">
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
                            <input type="text" name="nickname" class="account-input" value="${login_user.nickname}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
             				<c:if test="${not empty emailErrorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="text" name="email" class="account-input" value="${login_user.email}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">パスワード</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="password" name="password" class="account-input" value="${login_user.password}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">目標ジャンル</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <select name="categoryId" class="account-input" required>
                            <c:forEach var="category" items="${categoryList}">
	                            <c:choose>
									<c:when test="${category.categoryId == login_user.category.categoryId}">
										<option value="${category.categoryId}" selected>${category.categoryTitle}</option>
									</c:when>
									<c:otherwise>
										<option value="${category.categoryId}">${category.categoryTitle}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
                            </select>
                        </p>
                    </div>
                    <div class="account-section ">
                        <p class="account-view-title">目標内容</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <input type="text" name="goalDetail" class="account-input goal-detail-input" value="${login_user.goalDetail}">
                        </p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="/F1/TopPageServlet" class="button back-to-top-button">
                    キャンセル
                </a>

				<input type="submit" class="button account-update-button account-update-text" value="確認する">
      			
		    </div>
        </form>
	    </main>
	</div>
</body>
</html>