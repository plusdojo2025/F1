<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manimani/タスク一覧</title>
</head>
<body>
<!-- ヘッダー -->
<header><!-- グローバルナビ -->
<h1>
<a href="/webapp/TopPageServlet">まにまにLOGO</a>
</h1>
<ul>	
    <li><a href="/webapp/TaskRegistServlet">タスクの新規作成</a></li>
    <li><a href="/webapp/TaskViewServlet">タスク一覧</a></li>
    <li><a href="/webapp/RecordServlet">実績</a></li>
    <li><a href="/webapp/AccountServlet">アカウント</a></li>
    <li><a href="/webapp/TopPageServlet">ヘルプ</a></li>
</ul>
</header><!-- グローバルナビ -->

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
		<c:forEach var="e" items="${Tasks}" ><!-- 出力繰り返し -->
		<input type="hidden" name="" value="${e.number}">
		<tr>
		<td><input type="text" name="title" value="${e.title}"></td><!-- タスク名 -->
		<td><input type="text" name="timeSpan" value="${e.timeSpan}"></td><!-- 所要時間 -->
		<td><input type="text" name="categoryTitle" value="${category_title}"></td><!-- 気分 -->
		<td><input type="text" name="moodTitle" value="${e.mood_title}"></td><!-- 作業ジャンル -->
		<td>
		<c:if test="${ e.satisfaction_level == true }" >
			check
		</c:if>
		</td><!-- 公開判定チェックマーク -->
		<td>
		<input type="submit" class="green-btn" name="chengeButton" value="変更">
		<input type="submit" class="orange-btn" name="deleteButton" value="削除">
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