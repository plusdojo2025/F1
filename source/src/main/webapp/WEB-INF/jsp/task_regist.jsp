<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main>
<%@ include file="header.jsp" %>
<div class="account-main">
	<div class="task-regist-body">
		<div class="green-title-section">
			<h1 class="green-label">タスクの新規作成</h1>
		</div>
		<form method="POST" action="/F1/TaskRegistServlet"><!-- 外側の枠 -->
			<div class="task-regist-formbody">
				<div class="task-formsection task-regist-title-form">
					<span class="account-error-msg">ここにアラートを表示</span>
					<input type="text" class="account-input" name="title">
				</div>
				<div class="task-formsection">
					<p>所要時間</p>
					<input type="number" name="timeSpan">
				</div>
				<div class="task-formsection">
					<p>気分</p>
					<SELECT name="moodId" id="moodId">
				 		<c:forEach var="mood" items="${moodList}">
				 			<option value="${mood.moodId}">${mood.moodTitle}</option>
				 		</c:forEach>
					</SELECT>
				</div>
				<div class="task-formsection">
					<p>作業ジャンル</p>
					<SELECT name="categoryId" id="categoryId">
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
					<p>公開設定</p>
					<div>
						<input type="checkbox" name="isPrivate" value="isPrivate">公開する
					</div>
					<p>チェックすると他のユーザーに<br>タスク内容が公開されます。</p>
				</div>
			</div>
			<button type="submit" class="light-orange-btn" name="registBotton">リセット</button>
			<input type="submit" class="orange-btn" name="resetBotton" value="登録">
		</form>
	</div>
</div>
</main>

<!-- フッター -->
<footer>

</footer>
</body>
</html>