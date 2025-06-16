<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- タスク削除モーダル -->
<div id="deleteModal" class="modal-overlay">
	<div>
		<form><!-- 外側の枠 -->
			<p class="green-label">削除の確認</p>
			<table>
			<tr>
			<td colspan="2" class="leftAlert">ここにアラートを表示</td>
			</tr>														<!-- 1行目(未入力時アラート) -->
			<tr>
			<td colspan="2">${ e.title }</td>
			</tr>														<!-- 2行目 -->
			<tr>
			<td>所要時間</td>
			<td>${ e.timeSpan}</td>
			</tr>														<!-- 3行目 -->
			<tr>
			<td>気分</td>
			<td>${e.moodTitle}</td>
			</tr>														<!-- 4行目 -->
			<tr>
			<td>作業ジャンル</td>
			<td>${e.categoryTitle}</td>
			</tr>														<!-- 5行目 -->
			<tr>
			<td>公開設定</td>
			<td><input type="checkbox" name="isPrivate" value="${e.isPrivate}">公開する</td>
			</tr>														<!-- 6行目 -->
			<tr>
			<td colspan="2" class="rightAlert">チェックすると他のユーザーに<br>タスク内容が公開されます。</td>
			</tr>														<!-- 5行目（常時表示警告文） -->
			</table>
			<input type="submit" class="light-orange-btn" name="registBotton" value="リセット">
			<input type="submit" class="orange-btn" name="resetBotton" value="登録">
		</form>
	</div>
</div> 
