<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manimani/タスク新規作製</title>
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

<!-- メイン -->
<main>
<form><!-- 外側の枠 -->
	<p>タスクの新規作成</p>
	<table>
	<tr>
	<td class="leftAlert">ここにアラートを表示</td>
	</tr>
	<tr>
	<td><input type="text" name="title"></td>
	</tr>
	<tr>
	<td><input type="number" name="timeSpan"></td>
	</tr>
	<tr>
	<td>
		<SELECT name="mood" id="moodId">
			<option value=0>リラックス</option>
			<option value=1>集中</option>
			<option value=2>アクティブ</option>
			<option value=3>リフレッシュ</option>
			<option value=4>悲しい</option>
		</SELECT>
	</td>
	</tr>
	<tr>
	<td>
		<SELECT name="category" id="categoryId">
			<option value=0>運動・ストレッチ</option>
			<option value=1>セルフケア</option>
			<option value=2>スキルアップ</option>
			<option value=3>環境リセット</option>
			<option value=4>趣味</option>
			<option value=5>キャリアアップ</option>
		</SELECT>
	</td>
	</tr>
	<tr>
	<td><input type="checkbox" name="isPrivate" value="isPrivate">公開する</td>
	</tr>
	<tr>
	<td>チェックすると他のユーザーに<br>タスク内容が公開されます。</td>
	</tr>
	</table>
	<input type="submit" class="light-orange-btn" name="registBotton" value="リセット">
	<input type="submit" class="orange-btn" name="resetBotton" value="登録">
</form>

</main>

<!-- フッター -->
<footer>

</footer>
</body>
</html>