<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

	<div>
		<p>目標</p>
        <p><c:out value="${goalDetail}"/>1</p>
	</div>
	<div>
		<p>１日の隙間活用時間</p>
		<p><c:out value="${sumDuration}"/>2</p>
	</div>
	<div>
		<p>連続ログイン<c:out value="${consecutiveLogins}"/>日</p>
	</div>
	
	<form id="suggestForm" method="POST" action="/F1/SuggestServlet">
	 	<label for="timeSpan">今のすきま時間は？</label>
	 	<input type="number" id="timeSpan" name="timeSpan" min="1" value="1"/>
	 	
	 	<label for="mood">今の気分</label>
	 	<select name="moodId">
	 		<c:forEach var="mood" items="${moodList}">
	 			<option value="${mood.moodId}">${mood.moodTitle}</option>
	 		</c:forEach>
	 	</select>
	 	
	 	<label for="category">作業ジャンル</label>
	 	<select name="categoryId">
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
	 	
	 	<input type="submit" class="green-btn" name="suggest" value="提案を受ける">
	 	
 	</form>

</body>
</html>