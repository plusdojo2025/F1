<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<div class="wrapper">
		
		<!-- メイン（ここから） -->
		<main class="account-main">
	    <form method="POST" action="<c:url value='/AccountConfServlet' />" id="accountUpdateForm" class="account-update-form">
		    <div class="account-body">
		
		        <div class="green-title-section">
		            <h1 class="green-title">アカウント情報</h1>
		        </div>
                
                <div class="account-edit-view-section">
                    
                    <div class="account-section under-line">
                        <p class="account-view-title">ニックネーム</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
							<span id = "nicknameError" class="account-error-msg">${errorMessage}</span>
                            <input type="text" id = "nickname" name="nickname" class="account-input" value="${login_user.nickname}">
                        </p>
                    </div>
                    <div class="account-section under-line">
                        <p class="account-view-title">メールアドレス</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                           	<span id = "emailError" class="account-error-msg">${emailErrorMessage}</span>
                            <input type="text" id = "email" name="email" class="account-input" value="${login_user.email}">
                        </p>
                    </div>
                    <div class="account-section">
                        <p class="account-view-title">現在のパスワード</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                        	<span id="formError" class="account-error-msg"></span>
                            <c:if test="${not empty notEqualsErrorMessage}">
                                <span class="account-error-msg">${notEqualsErrorMessage}</span>
                            </c:if>
                            <input type="password" name="beforePassword" id="beforePassword" class="account-input">
                        </p>
                    </div>
                    <div class="account-section new-pw-section">
                        <p class="account-view-title">新しいパスワード</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <%-- <c:if test="${not empty errorMessage}"> --%>
                                <span class="account-error-msg">${errorMessage}</span>
                            <%-- </c:if> --%>
                            <input type="password" name="newPassword" id="newPassword" class="account-input">
                        </p>
                    </div>
                    <div class="account-section under-line new-pw-conf-section">
                        <p class="account-view-title">パスワード（確認）</p>
                        <p class="colon">：</p>
                        <p class="account-view-text">
                            <c:if test="${not empty errorMessage}">
                                <span class="account-error-msg">${errorMessage}</span>
                            </c:if>
                            <input type="password" name="newPasswordConf" id="newPasswordConf" class="account-input">
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
                        	<span class="account-error-msg" id = "goalDetailError"></span>
                            <input type="text" id = "goalDetail" name="goalDetail" class="account-input goal-detail-input" onkeyup="countGoalDetail(value)" value="${login_user.goalDetail}">
                        </p>
                    </div>
                </div>
            </div>
		    <div class="account-button-section">
		    	<a href="<c:url value='/AccountServlet' />" class="button back-to-top-button">
                    キャンセル
                </a>

				<input type="submit" class="button account-update-button account-update-text" value="確認する">
      			
		    </div>
        </form>
	    </main>
	</div>
	<script >
		document.getElementById("accountUpdateForm").addEventListener("submit", function(event){
			let beforePassword = document.getElementById("beforePassword").value.trim();
			let newPassword = document.getElementById("newPassword").value.trim();
			let newPasswordConf = document.getElementById("newPasswordConf").value.trim();
			
			let nickname = document.getElementById("nickname").value.trim();
			let email = document.getElementById("email").value.trim();
			let goalDetail = document.getElementById("goalDetail").value.trim();
			
			let formError = document.getElementById("formError");
			let nicknameError = document.getElementById("nicknameError");
			let emailError = document.getElementById("emailError");
			let goalDetailError = document.getElementById("goalDetailError");
			
			//エラーメッセージ初期化
			formError.textContent = "";
			nicknameError.textContent = "";
			emailError.textContent = "";
			goalDetailError.textContent = "";
			
			//ニックネーム未入力チェック
			if(!nickname){
				nicknameError.textContent = "この項目を入力してください";
				event.preventDefault();//フォーム送信中止
			}
			
			const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			//メールアドレス未入力・形式チェック
			if(!email){
				emailError.textContent = "この項目を入力してください";
				event.preventDefault();//フォーム送信中止
			} else{
				if(!emailRegex.test(email)) {
			    	emailError.textContent = "有効なメールアドレスを入力してください";
			    	event.preventDefault();
				}
			}
			
			//目標内容未入力・文字数チェック
			if(!goalDetail){
				goalDetailError.textContent = "この項目を入力してください";
				event.preventDefault();//フォーム送信中止
			} else{
				if(goalDetail.length > 25) {
			    	goalDetailError.textContent = "入力できるのは25文字までです";
			    	event.preventDefault();
				}
			}
			
			
			// Password入力欄に入力がある場合
			if (beforePassword || newPassword || newPasswordConf) {
				// 空欄チェック
				if (!beforePassword || !newPassword || !newPasswordConf) {
					formError.textContent = "すべてのパスワードの項目を入力してください";
					event.preventDefault();
					return;
				}
				
				//パスワードの強度
				const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
				// パスワード強度チェック
				if (!passwordRegex.test(newPassword)) {
				    formError.textContent = "パスワードは8文字以上で、英大文字・小文字・数字を含めてください";
				    event.preventDefault();
				    return;
				}
				
				// 新しいパスワードの確認
				if (newPassword !== newPasswordConf){
					formError.textContent = "新しいパスワードが一致しません";
					event.preventDefault();
					return;
				}
			}
			
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