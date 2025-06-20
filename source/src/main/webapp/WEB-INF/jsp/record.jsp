<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<div class="statistics">
	<div class="sumDuration">
            <p class="statisticsCategory">これまでに活用した合計時間
            <img src="images/time.svg">
            </p>
       <p class="statisticsData"><c:out value="${durationSum}"/></p> 
        </div>
	<div class="mostCategory">
            <p class="statisticsCategory">よく行う気分
                <img src="<c:url value='/images/smile.svg' />">
            </p>
            <p class="statisticsData"><c:out value="${mostMood.moodTitle}"/></p>
        </div>
	<div class="mostMood">
        <p class="statisticsCategory">よく行う作業ジャンル
             <img src="<c:url value='/images/work.svg' />">
        </p>
        <p class="statisticsData"><c:out value="${mostCategory.categoryTitle}"/></p> 
    </div>
</div>
<div class="logList">
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
			<c:if test="${empty history}">
			<tr>
		<td colspan=5 class="white-title-section lognone">実績がまだありません。</td>
		</tr>
	</c:if>
	</table>
	</div >
	<a href="<c:url value='/TopPageServlet' />" class="light-orange-btn"> TOP画面へ戻る</a>
</body>
</html>