<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/account.css' />">
<link rel="stylesheet" href="<c:url value='/css/record.css' />">
<link rel="stylesheet" href="<c:url value='/css/suggest.css' />">
<link rel="stylesheet" href="<c:url value='/css/support.css' />">
<link rel="stylesheet" href="<c:url value='/css/task.css' />">

</head>
<body>

	<header class="header">
		<a href="/F1/TopPageServlet"> <img class="logo-img"
			src="<%=request.getContextPath()%>/images/manimani-logo.svg"
			alt="名刺管理アプリケーション">
		</a>
		<nav class="nav">
			<ul class="nav-ul">
				<li>
					<!-- チュートリアル2 --> <!--目立たせたい要素-->
					<div id="new">
						<a href="/F1/TaskRegistServlet"> <img
							src="<c:url value='/images/plus.svg'/>" alt="タスクの新規登録">
							タスクの新規作成
						</a>
					</div>
						<div id="setsumeitext2">
						<div class="setsumeitext2">
							<div class="traiangl2"></div>
							<div class="traiangl2-back"></div>
							ここからタスクの新規作成が<br>出来ます！
						</div>
					</div>
				</li>
				<li><a href="/F1/TaskViewServlet"> <img
						src="<c:url value='/images/task-list.svg' />" alt="タスク一覧">
						タスク一覧
				</a></li>
				<li><a href="/F1/RecordServlet"> <img
						src="<c:url value='/images/medal.svg' />" alt="実績"> 実績
				</a></li>
				<li><a href="/F1/AccountServlet"> <img
						src="<c:url value='/images/account.svg' />" alt="アカウント">
						アカウント
				</a></li>
				<li>
					<!-- チュートリアル4 --> <!--目立たせたい要素-->
					<div id="help">
						<a href="/F1/HelpServlet"> <img
							src="<c:url value='/images/question.svg' />" alt="ヘルプ"> ヘルプ
						</a>
					</div>
					<div id="setsumeitext4">
					<div class="setsumeitext4">
						<div class="traiangl4"></div>
						<div class="traiangl4-back"></div>
						他の操作に関する説明はこのヘルプボタンから確認することができます！
						</div>
					</div>
				</li>
			</ul>
		</nav>

	</header>