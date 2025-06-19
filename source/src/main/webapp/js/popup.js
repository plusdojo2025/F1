/**
 * Popup用関数
 */
 
'use strict';

// ログアウト用
function openLoguotModal() {    
  document.getElementById("logoutModalOverlay").style.display = "flex";
}
function closeLogoutModal() {
  document.getElementById("logoutModalOverlay").style.display = "none";
}

// タスク削除画面用モーダル表示

function openDeleteModal(taskId, title, timeSpan, moodTitle, categoryTitle, isPrivate) {
  // モーダル要素取得
  const modal = document.getElementById("deleteModal");
  modal.style.display = "flex";

  // 各項目の表示
  modal.querySelector(".taskTitle").textContent = title;
  modal.querySelectorAll(".taskContent")[0].textContent = timeSpan + "分";
  modal.querySelectorAll(".taskContent")[1].textContent = moodTitle;
  modal.querySelectorAll(".taskContent")[2].textContent = categoryTitle;
  modal.querySelectorAll(".taskContent")[3].textContent = isPrivate === "true" ? "公開する" : "公開しない";

  // hiddenフィールドにtaskIdを入れる
  document.getElementById("deleteTaskId").value = taskId;
}

// モーダルを閉じる関数（例：背景クリックやキャンセルボタン）
function closeDeleteModal() {
  document.getElementById("deleteModal").style.display = "none";
}
