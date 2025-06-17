<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %><!-- ヘッダー＆グローバルナビ -->

<!-- メイン -->
<main>
<form method="POST" action="/F1/TaskRegistServlet"><!-- 外側の枠 -->
	<p class="green-label">タスクの新規作成</p>
	<table>
	<tr>
		<td colspan="2" class="leftAlert">ここにアラートを表示</td>
	</tr>														<!-- 1行目(未入力時アラート) -->
	<tr>
		<td colspan="2"><input type="text" name="title"></td>
	</tr>														<!-- 2行目 -->
	<tr>
		<td>所要時間</td>
		<td><input type="number" name="timeSpan"></td>
	</tr>														<!-- 3行目 -->
	<tr>
		<td>気分</td>
		<td>
			<SELECT name="moodId" id="moodId">
		 		<c:forEach var="mood" items="${moodList}">
		 			<option value="${mood.moodId}">${mood.moodTitle}</option>
		 		</c:forEach>
			</SELECT>
		</td>
	</tr>														<!-- 4行目 -->
	<tr>
		<td>作業ジャンル</td>
		<td>
			<SELECT name="categoryId" id="categoryId">
		 		<c:forEach var="category" items="${categoryList}">
		 			<c:choose>
		 				<c:when test="${category.categoryId == login_user.categoryId}">
		 					<option value="${category.categoryId}" selected>${category.categoryTitle}</option>
		 				</c:when>
		 				<c:otherwise>
		 					<option value="${category.categoryId}">${category.categoryTitle}</option>
		 				</c:otherwise>
		 			</c:choose>
		 		</c:forEach>
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
	<button type="submit" class="light-orange-btn" name="registBotton">リセット</button>
	<input type="submit" class="orange-btn" name="resetBotton" value="登録">
</form>

</main>

<!-- フッター -->
<footer>

</footer>
</body>
</html>