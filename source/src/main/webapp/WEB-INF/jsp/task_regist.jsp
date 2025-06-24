<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main>
<%@ include file="header.jsp" %>
<div class="account-main">
	<div class="task-regist-body green-l-body">
		<div class="green-title-section">
			<h1 class="green-label">タスクの新規作成</h1>
		</div>
		<form method="POST" action="<c:url value='/TaskRegistServlet' />" class="task-regist-form"><!-- 外側の枠 -->
			<div class="task-regist-formbody">
				<div class="task-formsection task-regist-title-form">
					<c:if test="${not empty errorMessage}">
						<span class="account-error-msg">${errorMessage}</span>
					</c:if>
					<input type="text" class="account-input w-form" name="title" required>
					<span class="account-error-msg task-regist-rule">【条件】タスクタイトルは20文字以内</span>
				</div>
				<div class="task-formsection align-i-c">
					<p class="task-title-tips">所要時間</p>
					<input type="number" class="account-input task-regist-input" name="timeSpan" min="1" max ="2147483647" value="1" required>
				</div>
				<div class="task-formsection align-i-c">
					<p class="task-title-tips">気分</p>
					<SELECT name="moodId" id="moodId" class="account-input task-select task-regist-input" required>
				 		<c:forEach var="mood" items="${moodList}">
				 			<option value="${mood.moodId}">${mood.moodTitle}</option>
				 		</c:forEach>
					</SELECT>
				</div>
				<div class="task-formsection align-i-c">
					<p class="task-title-tips">作業ジャンル</p>
					<SELECT name="categoryId" id="categoryId" class="account-input task-select task-regist-input" required>
				 		<c:forEach var="category" items="${categoryList}">
				 			<c:choose>
				 				<c:when test="${category.categoryId == login_user.categoryId}">
				 					<option value="${category.categoryId}" selected>${category.categoryTitle}</option>
				 				</c:when>
				 				<c:otherwise>
				 					<option value="${category.categoryId}">${category.categoryTitle}</option>
				 				</c:otherwise>
				 			</c:choose>
				 		</c:forEach>
					</SELECT>
				</div>
				<div class="task-formsection">
					<p class="task-title-tips">公開設定</p>
					<div>
						<input type="checkbox" name="isPrivate" value="true">公開する
						<p class="account-error-msg is-privae-msg">チェックすると他のユーザーに<br>タスク内容が公開されます。</p>
					</div>
					
				</div>
			</div>
			<div class="task-regist-button-section">
				<button type="reset" class="light-orange-btn task-form-btn" name="resetBotton">リセット</button>
				<input type="submit" class="orange-btn task-form-btn" name="registBotton" value="登録">
			</div>
		</form>
	</div>
</div>
</main>

<dialog id="submitModal">
  <p id="modalMessage">このタスクを<span class="highlight">公開</span>します。よろしいですか？</p>
  <div style="text-align: center; margin-top: 10px;">
    <button id="confirmBtn" class="orange3-btn">はい</button>
    <button id="cancelBtn" class="light-orange3-btn">キャンセル</button>
  </div>
</dialog>


<!-- フッター -->
<footer>

</footer>
<script>
window.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('.task-regist-form');
    const titleInput = form.querySelector('input[name="title"]');
    const privateCheckbox = form.querySelector('input[name="isPrivate"]');
    const modal = document.getElementById('submitModal');
    const modalMsg = document.getElementById('modalMessage');
    const confirmBtn = document.getElementById('confirmBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    
    let allowSubmit = false;

    // エラーメッセージ表示用の要素を生成（フォーム内の先頭に追加）
    const errorMsg = document.createElement('div');
    errorMsg.classList.add('account-error-msg');
    errorMsg.style.color = 'red';
    errorMsg.style.marginBottom = '5px';
    errorMsg.style.display = 'none';  // 初期状態は非表示

    titleInput.parentElement.insertBefore(errorMsg, titleInput);

    form.addEventListener('submit', function (e) {
        const titleValue = titleInput.value.trim();

        if (titleValue.length > 20) {
            e.preventDefault(); // フォームの送信を止める
            errorMsg.textContent = 'タスクタイトルは20文字以内です。';
            errorMsg.style.display = 'block';
            return;
        } else {
            errorMsg.style.display = 'none';
        }
        
        if(!allowSubmit){
        	e.preventDefault();
        	modalMsg.innerHTML = privateCheckbox.checked
        		? 'このタスクを<span class="highlight" style="font-size: 25px;">公開</span>します。よろしいですか？'
        		: 'このタスクは非公開として登録されます。よろしいですか？';
        	modal.showModal();
        } 
    });
    
    confirmBtn.addEventListener('click', function(){
    	allowSubmit = true;
    	modal.close();
    	form.requestSubmit();
    });
    
    cancelBtn.addEventListener('click', function(){
    	allowSubmit = false;
    	modal.close();
    });
    
});
</script>

</body>
</html>