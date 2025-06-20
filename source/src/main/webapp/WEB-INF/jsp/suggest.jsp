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

	<table id="mineTasks">
		<caption>${login_user.nickname}さんの登録したタスク</caption>
		<c:choose>
		<c:when test="${not empty suggestTaskList}">
			<c:forEach var="task" items="${suggestTaskList}">
				<tr>
					<td>
						<form method="POST" action="<c:url value='/SuggestExecuteServlet' />">
							<input type="hidden" name="taskId" value="${task.taskId}">
							<input type="submit" value="${task.title}">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	
	<table id="otherTasks">
		<caption>他のユーザからの提案</caption>
		<c:choose>
		<c:when test="${not empty suggestTaskList}">
			<c:forEach var="task" items="${suggestOtherTaskList}">
				<tr>
					<td>
						<form method="POST" action="<c:url value='/SuggestExecuteServlet' />">
							<input type="hidden" name="taskId" value="${task.taskId}">
							<input type="submit" value="${task.title}">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	
	<form method="GET" action="<c:url value='/TopPageServlet' />">
		<input class="light-orange-button" type="submit" onclick="" value="キャンセル">
	</form>
	
	<form method="GET" action="<c:url value='/TaskRegistServlet' />">
		<input class="green-btn" type="submit" onclick="" value="タスク新規作成">
	</form>

</body>
</html>