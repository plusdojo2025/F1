<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %><!-- ヘッダー＆グローバルナビ -->

<main>
<div class="green-l-body task-view-body">
	<table>
		<thead><!-- テーブルトップ -->
		 <tr class="Ttop">
		 <th>タスク内容</th>
		 <th>所要時間</th>
		 <th>気分</th>
		 <th>作業ジャンル</th>
		 <th>公開する</th>
		 <th></th>
		 </tr>
		</thead><!-- テーブルトップ -->
		<tbody>
			<c:forEach var="e" items="${taskList}" ><!-- 出力繰り返し -->
					<input type="hidden" name="taskslot" value="${e.taskId}">
					<tr class="Tbody">
					<td><div class="taskTitle">${e.title}</div></td><!-- タスク名 -->
					<td>${e.timeSpan}</td><!-- 所要時間 -->
					<td>${e.moodTitle}</td><!-- 気分 -->
					<td>${e.categoryTitle}</td><!-- 作業ジャンル -->
					<td>
					<c:if test="${ e.isPrivate == true }" >
						check
					</c:if>
					</td><!-- 公開判定チェックマーク -->
					<td>
					<button type="button" class="edit-button" onclick="openEditModal(${e.taskId}, '${e.title}', ${e.timeSpan}, 
					'${e.moodTitle}', '${e.categoryTitle}', '${e.isPrivate}')">変更</button>
					<button type="button" class="delete-button" onclick="openDeleteModal(${e.taskId}, '${e.title}', '${e.timeSpan}',
					 '${e.moodTitle}', '${e.categoryTitle}', '${e.isPrivate}')">削除</button>
					</td><!-- 変更・削除ボタン -->
					</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="BtnSideBy">
<form action="/F1/TopPageServlet" class = "LeftPositionBtn"><!-- トップに戻るボタン -->
<input type="submit" class="light-orange-btn" name="goTopButton" value="Topへ戻る">
</form>

<form action="/F1/TaskRegistServlet" class = "RightPositionBtn green-btn">
<img src="<c:url value='/images/white-plus.svg' />">
<input type="submit" name="registTaskButton" value="新規タスクボタン">
</form>
</div>

</main>

<footer>

</footer>
<%@ include file="task_edit.jsp" %>
<%@ include file="task_delete.jsp" %>
<script src="/F1/js/popup.js"></script>
</body>
</html>