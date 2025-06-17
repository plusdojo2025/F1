<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

	<dialog id="execute-checkModal" >
		<p>現在の内容は削除されますがよろしいですか?<p>
		
		<button class="orange-btn" id="reCancelButton">キャンセル</button>
		<form>
			<input type="submit" class="light-orange-btn" id="deleteButton" value="削除する">
		</form>
	</dialog>

	<div class="execute-container">
		<h2><c:out value="${currentlog.task.Title}"/></h2>
		<div id="timer">
			<span id="minutes"></span>分<span id="seconds"></span>秒
		</div>
		
		<form method="POST" action="">
			<input id="duration" type="hidden" name="duration">
			<input type="button" class="orange-btn" id="completeButton" onclick="" value="完了">
		</form>
	</div>
	
	<button class="light-orange-btn" id="cancelButton" onclick="">中止</button>
	

</body>
<script>
var second = 0;
function pad(value) {
  return value > 9 ? value : '0' + value;
}
setInterval(function() {
  document.getElementById('seconds').innerHTML = pad(++second % 60);
  document.getElementById('minutes').innerHTML = pad(parseInt(second / 60, 10));
  document.getElementById('duration').innerHTML= second;
}, 1000);

cancelButton.addEventListener('click', function () {
    dialog.show();
  });
  
reCancelButton.addEventListener('click', function () {
    dialog.close();
  });

</script>

</html>