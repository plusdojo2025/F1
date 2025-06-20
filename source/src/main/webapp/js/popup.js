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

// 削除モーダルを閉じる関数
function closeDeleteModal() {
  document.getElementById("deleteModal").style.display = "none";
}


// タスク編集画面用モーダル表示

function openEditModal(taskId, title, timeSpan, moodTitle, categoryTitle, isPrivate) {
  // モーダル要素取得
  const modal = document.getElementById("editModal");
  modal.style.display = "flex";

  // inputへの値設定
  modal.querySelector('input[name="title"]').value = title;
  modal.querySelector('input[name="timeSpan"]').value = timeSpan;
  modal.querySelector('input[name="taskId"]').value = taskId;

  // セレクトボックスの値設定
  modal.querySelector('select[name="moodId"]').value = getMoodIdByTitle(moodTitle);
  modal.querySelector('select[name="categoryId"]').value = getCategoryIdByTitle(categoryTitle);

  // チェックボックス設定
  modal.querySelector('input[name="isPrivate"]').checked = (isPrivate === "true");
  
  // hiddenフィールドにtaskIdを入れる
  document.getElementById("editTaskId").value = taskId;
}

// 編集モーダルを閉じる関数
function closeEditModal() {
  document.getElementById("editModal").style.display = "none";
}

// 変更内容の確認モーダル
function confirmEditTask() {
  const isPrivate = document.querySelector('input[name="isPrivate"]').checked;

  const alertModal = document.getElementById("editAlertModal");
  const alertText = document.getElementById("editAlertText");

  // 背景をロックし、編集モーダルは一時的に非活性
  document.getElementById("editModal").style.pointerEvents = "none";

  if (isPrivate) {
    alertText.textContent = "タスクをこの内容に変更しますか？\nこのタスクは他のユーザーに公開されます。";
  } else {
    alertText.textContent = "タスクをこの内容に変更しますか？";
  }

  alertModal.style.display = "flex";
}

// 変更内容の確認モーダルを閉じる関数
function cancelEditConfirm() {
  document.getElementById("editAlertModal").style.display = "none";
  document.getElementById("editModal").style.pointerEvents = "auto";
}

// タスクの更新の実行
function submitEditTask() {
  document.getElementById("editTaskForm").submit();
}
