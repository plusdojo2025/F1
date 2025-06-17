<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<div>
		<p>
			これまでに活用した合計時間 <img src="../../images/time.svg">
		</p>
		<p>
			<c:out value="${durationSum}" />
		</p>
	</div>
	<div>
		<p>
			よく行う気分<img src="../../images/smile.svg">
		</p>
		<p>
			<c:out value="${mostCategory}" />


		</p>
	</div>
	<div>
		<p>
			よく行う作業ジャンル <img src="../../images/work.svg">
		</p>
		<p>
			<c:out value="${mostMood}" />
		</p>
	</div>

	<table>
		<tr>
			<th>タスク内容</th>
			<th>所要時間</th>
			<th>気分</th>
			<th>作業ジャンル</th>
			<th>満足度</th>
			<c:forEach var="log" items="${history}">
				<tr>
					<td class="taskTitle"><c:out value="${log.title}" /></td>
					<td class="duration"><c:out value="${log.duration}" /></td>
					<td class="mood"><c:out value="${log.moodTitle}" /></td>
					<td class="category"><c:out value="${log.categoryTitle}" /></td>
					<td><span class="rate"
						style="--percent: ${log.satisfactionLevel / 5 * 100}%;"></span></td>
				</tr>
			</c:forEach>
	</table>
	<a href="/F1/SuggestServlet" class="light-orange-btn"> TOP画面へ戻る</a>
</body>
</html>