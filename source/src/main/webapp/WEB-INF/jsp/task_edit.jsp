<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- タスク変更モーダル -->
<div id="editModal" class="modal-overlay">
	<div class="account-main task-edit-main">
	<div class="task-regist-body gray-back-body">
		<div class="white-title-section">
			<h1 class="white-title">タスク内容の変更</h1>
		</div>
		<form method="POST" id="editTaskForm" action="<c:url value='/TaskEditServlet' />" class="task-delete-form">
				
			<div class="green-l-body task-delete-form">
				<div class="task-edidel-item task-formsection task-regist-title-form">
					<span class="account-error-msg">ここにアラートを表示</span>
					<input type="text" class="account-input w-form" name="title">
				</div>
				<div class="task-edidel-item task-formsection align-i-c">
					<p class="task-title-tips">所要時間</p>
					<input type="number" class="account-input task-regist-input" name="timeSpan">
				</div>
				<div class="task-edidel-item task-formsection align-i-c">
					<p class="task-title-tips">気分</p>
					<SELECT name="moodId" id="moodId" class="account-input task-select task-regist-input">
				 		<c:forEach var="mood" items="${moodList}">
				 			<c:choose>
								<c:when test="${mood.moodId == task.moodId}">
									<option value="${mood.moodId}" selected>${mood.moodTitle}</option>
								</c:when>
								<c:otherwise>
									<option value="${mood.moodId}">${mood.moodTitle}</option>
								</c:otherwise>
							</c:choose>
				 		</c:forEach>
					</SELECT>
				</div>
				<div class="task-edidel-item task-formsection align-i-c">
					<p class="task-title-tips">作業ジャンル</p>
					<SELECT name="categoryId" id="categoryId" class="account-input task-select task-regist-input">
				 		<c:forEach var="category" items="${categoryList}">
				 			<c:choose>
				 				<c:when test="${category.categoryId == task.categoryId}">
				 					<option value="${category.categoryId}" selected>${category.categoryTitle}</option>
				 				</c:when>
				 				<c:otherwise>
				 					<option value="${category.categoryId}">${category.categoryTitle}</option>
				 				</c:otherwise>
				 			</c:choose>
				 		</c:forEach>
					</SELECT>
				</div>
				<div class="task-edidel-item task-formsection">
					<p class="task-title-tips">公開設定</p>
					<div>
						<input type="checkbox" name="isPrivate" value="true">公開する
						<p class="account-error-msg is-privae-msg">チェックすると他のユーザーに<br>タスク内容が公開されます。</p>
					</div>
					
				</div>
			</div>
			<div class="task-regist-button-section">
				<button type="button" class="light-orange-btn task-form-btn" onclick="closeEditModal()">キャンセル</button>
				<input type="hidden" name="taskId" id="editTaskId">
				<button type="button" class="green-btn task-form-btn" onclick="confirmEditTask()">変更</button>
			</div>
		</form>
	</div>
</div>
</div> 


<!-- アラート風確認モーダル -->
<div id="editAlertModal" class="modal-overlay" style="display: none;">
  <div class="alert-modal-content">
    <p id="editAlertText">ここに確認メッセージ</p>
    <div class="alert-button-area">
      <button class="light-orange-btn" onclick="cancelEditConfirm()">キャンセル</button>
      <button class="green-btn" onclick="submitEditTask()">OK</button>
    </div>
  </div>
</div>
