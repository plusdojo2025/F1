<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- タスク削除モーダル -->
<div id="deleteModal" class="modal-overlay">
	<div class="task-regist-body">
		<div class="green-title-section">
			<h1 class="white-label">削除の確認</h1>
		</div>
		<form method="POST" action="/F1/TaskDeleteServlet"><!-- 外側の枠 -->
			<div class="task-regist-formbody">
				<div class="task-formsection task-regist-title-form">
					<p class="taskTitle"></p>
				</div>
				<div class="task-formsection">
					<p class="task-title-tips">所要時間</p>
					<p class="taskContent"></p>
				</div>
				<div class="task-formsection">
					<p class="task-title-tips">気分</p>
					<p class="taskContent"></p>
				</div>
				<div class="task-formsection">
					<p class="task-title-tips">作業ジャンル</p>
					<p class="taskContent"></p>
				</div>
				<div class="task-formsection">
					<p class="task-title-tips">公開設定</p>
					<p class="taskContent"></p>
				</div>
			</div>
			<button type="button" class="light-orange-btn" onclick="closeDeleteModal()">キャンセル</button>
			<input type="hidden" id="deleteTaskId" name="taskId">
			<input type="submit" class="orange-btn" name="resetBotton" value="削除">
		</form>
	</div>
</div> 
