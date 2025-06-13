<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webapp/css/record.css">
</head>
<body>
 <div>
        <p>これまでに活用した合計時間</p>
        <p><c:out value="${durationSum}"/></p>
    </div>
    <div>
        <p>よく行う気分</p>
        <p><c:out value="${mostCategory}"/></p>
    </div>
    <div>
        <p>よく行う作業ジャンル</p>
        <p><c:out value="${mostMood}"/></p>
    </div>

    <table>
        <tr>
            <th>タスク内容</th>
            <th>所要時間</th>
            <th>気分</th>
            <th>作業ジャンル</th>
            <th>満足度</th>
         <c:forEach var="log" items="${history}">
        <tr>
            <td class="taskTitle"><c:out value="${log.title}"/></td>
            <td class="duration"><c:out value="${log.duration}"/></td>
            <td class="mood"><c:out value="${log.moodTitle}"/></td>
            <td class="category"><c:out value="${log.categoryTitle}"/></td>
            <td><span class="rate" style="--percent: ${log.satisfactionLevel / 5 * 100}%;"></span></td>
        </tr>
        </c:forEach>
    </table>
    <input type="button" onclick="" value="TOP画面へ戻る">
</body>
</html>