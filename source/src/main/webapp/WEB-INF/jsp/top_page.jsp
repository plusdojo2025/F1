<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<main class="suggest-main">
	<div class="topPage-goalDetail top-record">
		<p>目標</p>
        <p><c:out value="${goalDetail}"/>1</p>
	</div>
	<div class="topPage-record">
		<div class="topPage-sumDuration top-record">
			<p>１日の隙間活用時間</p>
			<p><c:out value="${sumDuration}"/>2</p>
		</div>
	</div>
	<div class="topPage-consecutiveLogins top-record">
		<p>連続ログイン<c:out value="${consecutiveLogins}"/>日</p>
	</div>
	
	<form id="suggestForm" method="POST" action="/F1/SuggestServlet">
	 	<label for="timeSpan">今のすきま時間は？</label>
	 	<input type="number" class="suggest-info" id="spanTime" name="spanTime" min="1" value="1"/>
	 	
		<div class="suggestMoodCatgeory">
			<label for="mood">今の気分</label>
			<select name="moodId" class="suggest-info">
				<c:forEach var="mood" items="${moodList}">
					<option value="${mood.moodId}">${mood.moodTitle}</option>
				</c:forEach>
			</select>
			
			<label for="category">作業ジャンル</label>
			<select  class ="suggest-info" name="categoryId">
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
			</select>
	 	</div>
	 	<input type="submit" class="green-btn" name="suggest" value="提案を受ける">
		
 	</form>
</main>
</body>
</html>