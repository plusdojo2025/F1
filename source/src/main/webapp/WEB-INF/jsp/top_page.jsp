<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<main class="suggest-main">
	<span id="tutorial"></span>
	<div id="goal">
		<!-- チュートリアル3 -->
		<!--目立たせたい要素-->
		<div class="goal">

			<div class="topPage-goalDetail top-record">
				<div class="goallogo">
					<div class="goallogo-top"></div>
					<p class="goallogo-main">目標</p>
					<div class="goallogo-under1"></div>
					<div class="goallogo-under2"></div>
				</div>
				<p class="goalDetail-top">
					<c:out value="${goalDetail}" />
				</p>

			</div>
		</div>


		<div class="topPage-record">
			<!--目立たせたい要素-->
			<div class="goal2">
				<div class="topPage-sumDuration top-record">
					<p class="sDuration-top">１日の隙間活用時間</p>
					<p class="sumDuration-top">
						<c:out value="${sumDuration}" />
					</p>
				</div>
			</div>

			<!--目立たせたい要素-->
			<div class="goal3">
				<div class="topPage-consecutiveLogins top-record">
					<div class="cLogin"><p>
						連続ログイン<span class="count"><c:out
								value="${login_user.consecutiveLogins}" /></span>日
					</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="setsumeitext3">
	<div class="setsumeitext3">
		ここで目標の確認、<br>1日のすきま活用時間、連続ログイン日数の<br>確認ができます！
		<div class="traiangl3"></div>
		<div class="traiangl3-back"></div>
	</div>
	</div>


	<div id="task">
		<form id="suggestForm" method="POST" action="/F1/SuggestServlet">
			<!-- チュートリアル1 -->

			<!--目立たせたい要素-->
			<div id="setsumeitext">
				<div class="setsumeitext">
				ここからすきま時間の入力、<br> 気分・作業ジャンルの選択をし<br> 提案ボタンから<br>
				タスク選択に進みます！
				
				<div class="traiangl1"></div>
				<div class="traiangl1-back"></div>
				</div>
			</div>
			<div class="task1">
				<div class="nowTimeSpan">
					<label for="timeSpan">今のすきま時間は？</label><br>
					<p id="topError"></p>
					 <input type="number" class="suggest-info" id="spanTime"
						name="spanTime" min="1" value="1" /><p class="minutes">分</p>
				</div>
			</div>
			<!--目立たせたい要素-->

			<div class="suggestMoodCatgeory">
				<div class="task2">
					<label for="mood"><img
						src="<c:url value='/images/orange-smile.svg' />">今の気分</label><br>
					<select name="moodId" class="suggest-info2">
						<c:forEach var="mood" items="${moodList}">
							<option value="${mood.moodId}">${mood.moodTitle}</option>
						</c:forEach>
					</select>
				</div>
				<!--目立たせたい要素-->
				<div class="task3">
					<label for="category"><img
						src="<c:url value='/images/orange-work.svg' />">作業ジャンル</label><br>
					<select class="suggest-info2" name="categoryId">
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
			</div>
			<!--目立たせたい要素-->
			<div class="task4">
				<input type="submit" class="green-btn" name="suggest" value="提案を受ける">
			</div>

		</form>
	</div>
	<!--モーダル部分-->
	<div class="modaloverlay" id="modaloverlay_id"></div>
</main>
<script src="<c:url value='/js/tutorial.js'/>"></script>
</body>
</html>