<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<div class="statistics">
	<div class="sumDuration">
            <p class="statisticsCategory">これまでに活用した合計時間
            <img src="<c:url value='/images/time.svg' />">
            </p>
       <p class="statisticsData"><c:out value="${durationSum}"/></p> 
        </div>
	<div class="mostCategory">
            <p class="statisticsCategory">よく行う気分
                <img src="<c:url value='/images/smile.svg' />">
            </p>
            <p class="statisticsData"><c:out value="${mostMood.moodTitle}"/></p>
            <c:if test="${empty history}">
		<p class="statisticsData">まだありません</p>
		</c:if>
        </div>
	<div class="mostMood">
        <p class="statisticsCategory">よく行う作業ジャンル
             <img src="<c:url value='/images/work.svg' />">
        </p>
        <p class="statisticsData"><c:out value="${mostCategory.categoryTitle}"/></p> 
        <c:if test="${empty history}">
		<p class="statisticsData">まだありません</p>
		</c:if>
    </div>
</div>
<div class="logList">
	<table>
		<tr>
			<th class="th-record">タスク内容</th>
			<th class="th-record">所要時間</th>
			<th  class="th-record">気分</th>
			<th class="th-record">作業ジャンル</th>
			<th class="th-record">満足度</th>
			<c:forEach var="log" items="${history}">
				<tr>
					<td class="taskTitle-record  td-record"><span class="taskName-record"><c:out  value="${log.title}" /></span></td>
					<td class="duration-record td-record"><c:out value="${log.durations}" /></td>
					<td class="mood-record td-record"><c:out value="${log.moodTitle}" /></td>
					<td class="category-record td-record"><c:out value="${log.categoryTitle}" /></td>
					<td  class="td-record"><span class="rate"
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
	<a href="<c:url value='/TopPageServlet' />" class="light-orange-btn to-top-btn"> TOPへ戻る</a>
</body>
</html>