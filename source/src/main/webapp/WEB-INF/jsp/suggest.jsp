<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webapp/css/suggest.css">
</head>
<body>

<%@ include file="header.jsp"%>
<div class="suggestTask">
	<table id="mineTasks" >
		<caption class="green-title-section suggestTitle" >${login_user.nickname}さんの登録したタスク</caption>
		<c:choose>
		<c:when test="${not empty suggestTaskList}">
			<c:forEach var="task" items="${suggestTaskList}">
				<tr>
					<td class="td-suggest white-title-section">
					<form method="POST" action="<c:url value='/SuggestExecuteServlet' />">
							<input type="hidden" name="taskId" value="${task.taskId}">
							<input type="submit" value="${task.title}"  class="taskSelect">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="white-title-section taskNone">
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div class="suggestTask">
	<table id="otherTasks">
		<caption class="green-title-section suggestTitle">他のユーザからの提案</caption>
		<c:choose>
		<c:when test="${not empty suggestOtherTaskList}">
			<c:forEach var="task" items="${suggestOtherTaskList}">
				<tr>
					<td class="td-suggest white-title-section">
						<form method="POST" action="<c:url value='/SuggestExecuteServlet' />">
							<input type="hidden" name="taskId" value="${task.taskId}">
							<input type="submit" value="${task.title}" class="taskSelect">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
				<td class="white-title-section taskNone">
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div id="botton-suggest">
	<form method="GET" action="<c:url value='/TopPageServlet' />">
		<input class="light-orange-button back-to-top-button" type="submit" onclick="" value="キャンセル">
	</form>
	
	<form method="GET" action="<c:url value='/TaskRegistServlet'/>">
		<input type="submit" class="green-btn" onclick="" value="タスク新規作成">
	</form>
	</div>

</body>
</html>