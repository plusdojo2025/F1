<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<form method="POST" action="/F1/ResultServlet">
		<div class="execute-container">
			<h2><c:out value="${currentlog.task.Title}"/></h2>
			
			<div class="result-time">
				<p>今回の実行時間</p>
				<span id="minutes"></span>分<span id="seconds"></span>秒
			</div>
			
			<label for="satisfactionLevel">満足度の登録</label>
			<input type="range" name="satisfactionLevel" min="0" max="6" step ="1">
		</div>
	
		<input type="submit" class="orange-btn" id="registButton" onclick="" value="満足度を登録">
	</form>
</body>

<script>
const second = parseInt(<c:out value="${currentLog.duration}"/>);

window.onload = function onLoad(){
	 document.getElementById('seconds').innerHTML = pad(second % 60);
	 document.getElementById('minutes').innerHTML = pad(parseInt(second / 60, 10));
}

function pad(value) {
	  return value > 9 ? value : '0' + value;
}

</script>
</html>