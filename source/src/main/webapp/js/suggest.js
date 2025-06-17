document.addEventListener("DOMContentLoaded", function () {
    let second = 0;　//タイマーの初期化
    let preventUnload = true;

    const secondsEl = document.getElementById("seconds");
    const minutesEl = document.getElementById("minutes");
    const durationInput = document.getElementById("duration");
    const modal = document.getElementById("confirmModal");
	const yesBtn = document.getElementById("confirmYes");
	const noBtn = document.getElementById("confirmNo");

    function pad(value) {
        return value > 9 ? value : '0' + value;
    }
    
	//カウントアップ処理
    const timer = setInterval(() => {
        second++;
        secondsEl.textContent = pad(second % 60);
        minutesEl.textContent = pad(Math.floor(second / 60));
        durationInput.value = second;
    }, 1000);

	//完了ボタンの処理
    document.getElementById("completeButton").addEventListener("click", () => {
        preventUnload = false;
        clearInterval(timer);
        document.querySelector("form").submit();
    });

	//中止ボタンの処理
    document.getElementById("cancelButton").addEventListener("click", () => {
        if (confirm("本当に中止しますか？")) {
            preventUnload = false;
            clearInterval(timer);
            window.location.href = "/F1/TopPageServlet";
        }
    });
	
	//ページを離れるときの警告
    window.addEventListener("beforeunload", function (e) {
        if (preventUnload) {
            e.preventDefault();
            e.returnValue = '';
        }
    });
    
    // 中止ボタン押下時
	document.getElementById("cancelButton").addEventListener("click", () => {
	    modal.classList.remove("hidden");
	});
	
	// 「はい」選択時
	yesBtn.addEventListener("click", () => {
	    preventUnload = false;
	    clearInterval(timer);
	    window.location.href = "/F1/TopPageServlet";
	});
	
	// 「いいえ」選択時
	noBtn.addEventListener("click", () => {
	    modal.classList.add("hidden");
	});
});
