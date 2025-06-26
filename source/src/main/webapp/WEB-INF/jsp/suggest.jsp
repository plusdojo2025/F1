<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="pageTitle" value="まにまに｜タスク提案" />
<%@ include file="header.jsp"%>
<div class="main-wrapper">
<div class="suggestTask  white-title-section">
	<table id="mineTasks" >
		<caption class="green-title-section suggestTitle" >${login_user.nickname}さんの登録したタスク</caption>
		<c:choose>
		<c:when test="${not empty suggestTaskList}">
			<c:forEach var="task" items="${suggestTaskList}">
				<tr>
					<td class="td-suggest">
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
				<td class="taskNone">
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div class="suggestTask white-title-section">
	<table id="otherTasks">
		<caption class="green-title-section suggestTitle">他のユーザからの提案</caption>
		<c:choose>
		<c:when test="${not empty suggestOtherTaskList}">
			<c:forEach var="task" items="${suggestOtherTaskList}">
				<tr>
					<td class="td-suggest">
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
				<td class="taskNone">
					<p>該当するタスクがありませんでした。</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div id="botton-suggest">
	<form method="GET" action="<c:url value='/TopPageServlet' />">
		<input class="light-orange-button back-to-top-button button" type="submit" onclick="" value="キャンセル">
	</form>
	
	<!-- タスク新規作成ボタン -->
	<form action="<c:url value='/TaskRegistServlet' />" class="RightPositionBtn">
	  <button type="submit" class="task-create-btn">
	    <img src="<c:url value='/images/white-plus.svg' />" class="task-create-icon" alt="＋">
	    タスク新規作成
	  </button>
	</form>
	</div>
</div>
</body>
</html>