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

function openEditModal(taskId, title, timeSpan, moodId, categoryId, isPrivate) {
  // モーダル要素取得
  const modal = document.getElementById("editModal");
  modal.style.display = "flex";

  // inputへの値設定
  modal.querySelector('input[name="title"]').value = title;
  modal.querySelector('input[name="timeSpan"]').value = timeSpan;
  modal.querySelector('input[name="taskId"]').value = taskId;

  // セレクトボックスの値設定
  modal.querySelector('select[name="moodId"]').value = moodId;
  modal.querySelector('select[name="categoryId"]').value = categoryId;

  // チェックボックス設定
  modal.querySelector('input[name="isPrivate"]').checked = (isPrivate === "true");
  
  // hiddenフィールドにtaskIdを入れる
  document.getElementById("editTaskId").value = taskId;
}

// 編集モーダルを閉じる関数
function closeEditModal() {
  // エラーメッセージ初期化処理
  const timeSpanContainer = document.getElementById('taskEditTimeSpan');
  const parentContainer = timeSpanContainer.parentElement; // 先にparentContainerを定義する
  const titleInput = document.querySelector('#editModal input[name="title"]');

  let timeSpanError = parentContainer.querySelector('.edit-time-error-msg');
  let titleError = titleInput.parentElement.querySelector('.edit-title-error-msg');

	if (titleError) {
	  titleError.textContent = "";
	}
	if (timeSpanError) {
	  timeSpanError.textContent = "";
	}
  
  //編集モーダルを閉じる
  document.getElementById("editModal").style.display = "none";
}

// 変更内容の確認モーダル
function confirmEditTask() {
  const isPrivate = document.querySelector('input[name="isPrivate"]').checked;
  const titleInput = document.querySelector('#editModal input[name="title"]');
  const titleValue = titleInput.value.trim();
  const alertModal = document.getElementById("editAlertModal");
  const alertText = document.getElementById("editAlertText");
  
  let hasError = false;
  
  // タイトルのエラーメッセージ処理
  let titleError = titleInput.parentElement.querySelector('.edit-title-error-msg');
  if (!titleError) {
    titleError = document.createElement('div');
    titleError.classList.add('account-error-msg', 'edit-title-error-msg');
    titleError.style.color = 'red';
    titleError.style.marginBottom = '5px';
    titleInput.parentElement.insertBefore(titleError, titleInput);
  }
  
  if (titleValue === "") {
    titleError.textContent = 'タスクタイトルを入力してください。';
    titleError.style.display = 'block';
    hasError = true;
  } else if (titleValue.length > 20) {
    titleError.textContent = 'タスクタイトルは20文字以内です。';
    titleError.style.display = 'block';
    hasError = true;
  } else {
    titleError.style.display = 'none';
  }
  
    // 所要時間のエラーメッセージ処理
  const timeSpanContainer = document.getElementById('taskEditTimeSpan');
  const timeSpanInput = timeSpanContainer.querySelector('input[name="timeSpan"]');
  const timeSpanValue = timeSpanInput.value.trim();
  const parentContainer = timeSpanContainer.parentElement; // この中にtaskEditTimeSpanがある

  let timeSpanError = parentContainer.querySelector('.edit-time-error-msg');

  if (!timeSpanError) {
    timeSpanError = document.createElement('span');
    timeSpanError.classList.add('account-error-msg', 'edit-time-error-msg');
    timeSpanError.style.color = 'red';
    timeSpanError.style.marginBottom = '5px';
    parentContainer.insertBefore(timeSpanError, timeSpanContainer);
  }

  if (timeSpanValue === "" || isNaN(timeSpanValue)) {
    timeSpanError.textContent = '所要時間を入力してください。';
    timeSpanError.style.display = 'block';
    hasError = true;
  } else if (parseInt(timeSpanValue) <= 0) {
    timeSpanError.textContent = '所要時間は1以上の数値を入力してください。';
    timeSpanError.style.display = 'block';
    hasError = true;
  } else {
    timeSpanError.style.display = 'none';
  }

  // エラーがあれば確認モーダルを表示しない
  if (hasError) return;
  
  // モーダル表示
  document.getElementById("editModal").style.pointerEvents = "none";
  if (isPrivate) {
    alertText.textContent = "タスクをこの内容に変更しますか？\n このタスクは他のユーザーに公開されます。";
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
  const form = document.getElementById("editTaskForm");
  const titleInput = form.querySelector('input[name="title"]');
  const titleValue = titleInput.value.trim();

  // すでに存在していれば再利用、なければ生成
  let errorMsg = titleInput.parentElement.querySelector('.dynamic-error-msg');
  if (!errorMsg) {
    errorMsg = document.createElement('div');
    errorMsg.classList.add('account-error-msg', 'dynamic-error-msg');
    errorMsg.style.color = 'red';
    errorMsg.style.marginBottom = '5px';
    titleInput.parentElement.insertBefore(errorMsg, titleInput);
  }

  if (titleValue.length > 20) {
    errorMsg.textContent = 'タスクタイトルは20文字以内です。';
    errorMsg.style.display = 'block';
    document.getElementById("editAlertModal").style.display = "none";
    document.getElementById("editModal").style.pointerEvents = "auto";
    return; // フォーム送信を中止
  } else {
    errorMsg.style.display = 'none';
  }

  form.submit(); // バリデーション通過時のみ送信
}

