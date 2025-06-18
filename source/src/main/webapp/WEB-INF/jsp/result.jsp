<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
	<form method="POST" action="/F1/ResultServlet">
		<div class="execute-container">
			<h2><c:out value="${currentLog.task.title}"/></h2>
			
			<div class="result-time">
				<p id="sp" style="font-size: 30px">今回の実行時間</p>
				<span id="minutes"></span>
				<span id="unit">分</span>
				<span id="seconds"></span>
				<span id="unit">秒</span>
			</div>
			
			<div class="range-wrapper">
				<label class="range-label" id="result-label" for="satisfactionLevel">満足度の登録</label><br>
			</div>
			
			<div class="slider-with-labels">
				<input type="range" name="satisfactionLevel" min="0" max="5" step ="1">
			</div>
			
			<div class="labels">
				<span>0</span><span>1</span><span>2</span><span>3</span><span>4</span><span>5</span>
			</div>
			
		</div>
		
		<div class="result-orange-button">
			<input type="submit" class="orange-btn " id="registButton" onclick="" value="満足度を登録">
		</div>
		
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