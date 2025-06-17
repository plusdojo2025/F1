<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

	<dialog id="execute-checkModal" >
		<p>現在の内容は削除されますがよろしいですか?</p>
		
		<button class="orange-btn" id="reCancelButton">キャンセル</button>
		<form>
			<input type="submit" class="light-orange-btn" id="deleteButton" value="削除する">
		</form>
	</dialog>

	<div class="execute-container">
		<h2><c:out value="${currentlog.task.Title}"/></h2>
		<div id="timer">
			<span id="minutes"></span>分<span id="seconds"></span>秒
		</div>
		
		<form method="POST" action="">
			<input id="duration" type="hidden" name="duration">
			<input type="button" class="orange-btn" id="completeButton" onclick="" value="完了">
		</form>
	</div>
	
	<button class="light-orange-btn" id="cancelButton" onclick="">中止</button>
	

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