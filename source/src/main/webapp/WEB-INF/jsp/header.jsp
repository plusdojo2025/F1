<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/account.css' />">
<link rel="stylesheet" href="<c:url value='/css/record.css' />">
<link rel="stylesheet" href="<c:url value='/css/suggest.css' />">
<link rel="stylesheet" href="<c:url value='/css/support.css' />">
<link rel="stylesheet" href="<c:url value='/css/task.css' />">

</head>
<body>
	<header>
		<a href="/F1/TopPageServlet">まにまに</a>
		<ul>
				<li><a href="/F1/TaskRegistServlet">タスクの新規登録
			<img src="<c:url value='/images/plus.svg'/>"></a></li>
			<li><a href="/F1/TaskViewServlet">タスク一覧 
			<img src="<c:url value='/images/task-list.svg' />"></a></li>
			<li><a href="/F1/RecordServlet">実績 
				<img src="<c:url value='/images/medal.svg' />"></a></li>
			<li><a href="/F1/AccountServlet">アカウント 
				<img src="<c:url value='/images/account.svg' />"></a></li>
			<li><a href="/F1/HelpServlet">ヘルプ 
				<img src="<c:url value='/images/question.svg' />"></a></li>
		</ul>

	</header>
