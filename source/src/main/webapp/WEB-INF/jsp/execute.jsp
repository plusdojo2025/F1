<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="まにまに｜タスク実行" />
<%@ include file="header.jsp" %>
<div class="main-wrapper">
	<dialog id="execute-checkModal" >
		<p>現在の内容は削除されますがよろしいですか?</p>
		<div class="modal-buttons">
			<button class="orange-btn" id="reCancelButton">キャンセル</button>
			<form action="<c:url value='/TopPageServlet' />" method="GET">
				<input type="submit" class="light-orange-btn" id="deleteButton" value="削除する">
			</form>
		</div>
		
	</dialog>

	
	<div class="execute-container">
		<h2><c:out value="${currentLog.task.title}" default="(タイトルなし)"/></h2>
		<div id="timer">
			<span id="minutes"></span>
			<span id="unit">分</span>
			<span id="seconds"></span>
			<span id="unit">秒</span>
		</div>
		
		<form method="POST" action="<c:url value='/ToResultServlet' />">
			<input id="duration" type="hidden" name="duration">
			<input type="submit" class="orange-btn" id="completeButton" value="完了">
		</form>
	</div>
	
	<button class="light-orange-btn" id="cancelButton" onclick="">中止</button>
	
</div>
</body>
<script>
document.addEventListener("DOMContentLoaded", function () {
	let second = 0;
	const secondsEl = document.getElementById("seconds");
	const minutesEl = document.getElementById("minutes");
	const durationInput = document.getElementById("duration");

	const dialog = document.getElementById("execute-checkModal");
	const cancelButton = document.getElementById("cancelButton");
	const reCancelButton = document.getElementById("reCancelButton");
	const completeButton = document.getElementById("completeButton");

	// タイマー起動
	const timer = setInterval(() => {
		second++;
		secondsEl.textContent = second % 60 < 10 ? '0' + (second % 60) : second % 60;
		minutesEl.textContent = Math.floor(second / 60) < 10 ? '0' + Math.floor(second / 60) : Math.floor(second / 60);
		durationInput.value = second;
	}, 1000);

	// 中止ボタン → モーダル表示
	cancelButton.addEventListener('click', function () {
		dialog.showModal();
	});

	// モーダルのキャンセルボタン → 閉じる
	reCancelButton.addEventListener('click', function () {
		dialog.close();
	});

	// 完了ボタン → フォーム送信
	completeButton.addEventListener("click", function () {
		clearInterval(timer);
		document.querySelector("form").submit();
	});
});
</script>

</html>