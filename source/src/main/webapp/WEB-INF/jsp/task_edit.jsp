<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- タスク変更モーダル -->
<div id="editModal" class="modal-overlay">
	<div>
	<p class="white-label">タスクの変更</p>
	<table>
		<tr>
			<td colspan="2" class="leftAlert">ここにアラートを表示</td>
		</tr>														<!-- 1行目(未入力時アラート) -->
		<tr>
			<td colspan="2"><input type="text" name="title" value="${ e.title }"></td>
		</tr>														<!-- 2行目 -->
		<tr>
			<td>所要時間</td>
			<td><input type="number" name="timeSpan" value="${ e.timeSpan }"></td>
		</tr>														<!-- 3行目 -->
		<tr>
			<td>気分</td>
			<td>
				<SELECT name="mood" id="${ e.moodId }">
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
				<SELECT name="category" id="${ e.categoryId }">
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
			<td><input type="checkbox" name="isPrivate" value="${e.isPrivate}">公開する</td>
		</tr>														<!-- 6行目 -->
		<tr>
			<td colspan="2" class="rightAlert">チェックすると他のユーザーに<br>タスク内容が公開されます。</td>
		</tr>														<!-- 5行目（常時表示警告文） -->
	</table>
	<div class="BtnSideBy">
		<form action="/webapp/TaskRegistServlet" class = "LeftPositionBtn">
			<input type="submit" class="olight-orange-btn" name="cancelBotton" value="キャンセル">
		</form>
		
		<form action="/webapp/TaskRegistServlet" class = "RightPositionBtn">
			<input type="submit" class="orange-btn" name="changeBotton" value="変更">
		</form>
	</div>
	</div>
</div> 
