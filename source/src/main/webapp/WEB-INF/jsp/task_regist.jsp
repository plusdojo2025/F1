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
	<td colspan="2" class="leftAlert">ここにアラートを表示</td>
	</tr>														<!-- 1行目(未入力時アラート) -->
	<tr>
	<td　colspan="2"><input type="text" name="title"></td>
	</tr>														<!-- 2行目 -->
	<tr>
	<td>所要時間</td>
	<td><input type="number" name="timeSpan"></td>
	</tr>														<!-- 3行目 -->
	<tr>
	<td>気分</td>
	<td>
		<SELECT name="mood" id="moodId">
			<option value=0>リラックス</option>
			<option value=1>集中</option>
			<option value=2>アクティブ</option>
			<option value=3>リフレッシュ</option>
			<option value=4>悲しい</option>
		</SELECT>
	</td>
	</tr>														<!-- 4行目 -->
	<tr>
	<td>作業ジャンル</td>
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
	</tr>														<!-- 5行目 -->
	<tr>
	<td>公開設定</td>
	<td><input type="checkbox" name="isPrivate" value="isPrivate">公開する</td>
	</tr>														<!-- 6行目 -->
	<tr>
	<td colspan="2" class="rightAlert">チェックすると他のユーザーに<br>タスク内容が公開されます。</td>
	</tr>														<!-- 5行目（常時表示警告文） -->
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