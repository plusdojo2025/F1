<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="logoutModalOverlay" class="delete-modal">
	<div class="delete-wrapper">
	    <p>ログアウトしますか？</p>
	    <div class="button-wrapper">
			<button class="cansel-button" onclick="closeLogoutPopup()">キャンセル</button>
			<form action="LogoutServlet" method="POST" class="to-update delete-popup-dbotton">
			    <input type="submit" value="ログアウト" class="delete-exec-button">
			</form>
	    </div>
	</div>
</div>