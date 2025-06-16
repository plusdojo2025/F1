<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div id="logoutModalOverlay" class="delete-modal">
        <div class="logout-wrapper">
            <p>ログアウトしますか？</p>
            <div class="modal-button-wrapper">
                <button class="cansel-button" onclick="closeLogoutModal()">キャンセル</button>
                <form action="/F1/LogoutServlet" method="POST" class="to-update delete-popup-botton">
                    <input type="submit" value="ログアウト" class="delete-exec-button">
                </form>
            </div>
        </div>
    </div>