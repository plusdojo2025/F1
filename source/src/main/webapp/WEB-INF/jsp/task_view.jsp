<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %><!-- ヘッダー＆グローバルナビ -->

<main>
<table>
	<thead><!-- テーブルトップ -->
	 <tr>
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
				<input type="hidden" name="" value="${e.taskId}">
				<tr>
				<td>${e.title}</td><!-- タスク名 -->
				<td>${e.timeSpan}</td><!-- 所要時間 -->
				<td>${e.moodTitle}</td><!-- 気分 -->
				<td>"${e.categoryTitle}</td><!-- 作業ジャンル -->
				<td>
				<c:if test="${ e.isPrivate == true }" >
					check
				</c:if>
				</td><!-- 公開判定チェックマーク -->
				<td>
				<button type="button" class="reist-button" onclick="openRegistModal(${e.taskId}, '${e.timeSpan}', ${e.title}, 
				'${e.moodTitle}', '${e.categoryTitle}', '${e.isPrivate}')">変更</button>
				<button type="button" class="delete-button" onclick="openDeleteModal(${e.taskId}, '${e.title}')">削除</button>
				</td><!-- 変更・削除ボタン -->
				</tr>
		</c:forEach>
	</tbody>
</table>
<form action="/webapp/TopPageServlet"><!-- トップに戻るボタン -->
<input type="submit" class="light-orange-btn" name="goTopButton" value="トップへ戻る">
</form>

<form action="/webapp/TaskRegistServlet">
<input type="submit" class="green-btn" name="registTaskButton" value="新規タスクボタン">
</form>

</main>

<footer>

</footer>

</body>
</html>