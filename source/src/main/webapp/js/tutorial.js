 window.onload = function(){
	const tutorial = document.getElementById('tutorial');
	modal_original(tutorial.textContent);
}

//ウィンドウを読み込んでから起動
//説明テキスト要素そのIDを指定
const setting_stext = ['setsumeitext', 'setsumeitext2', 'setsumeitext3', 'setsumeitext4'];
const setting_block = ['task', 'new', 'goal', 'help'];

let i = 0;

 function modal_original(modalswitch){
	
	//モーダルのIDを指定
	let smodalobj = document.getElementById('modaloverlay_id');
	

	if(modalswitch == 'on'){
		// ==== 要素を操作不可にする処理
		const elementsToDisable = document.querySelectorAll(
			'button, input, select, a'
		);
		elementsToDisable.forEach(el => {
			el.classList.add('tutorial-disabled');
			el.setAttribute('disabled', 'true');
		});
		
		// 初期表示
		showStep(i);
		
		smodalobj.style.display = 'block';
		smodalobj.addEventListener('click', overlayclick);
		
	}
	
	function showStep(index) {
		document.getElementById(setting_stext[index]).style.display = 'block';
		document.getElementById(setting_stext[index]).style.backgroundColor = '#ffffff';
		document.getElementById(setting_block[index]).style.zIndex = '1500';

		// 現在のブロック全体をクリック対象にする
		document.getElementById(setting_block[index]).addEventListener('click', overlayclick);
		document.getElementById(setting_stext[index]).addEventListener('click', overlayclick);
	}
	
	//モーダルをクリックしたら起動し、不要な要素を非表示にして次の要素を表示する
	function overlayclick(){
		// 現在のチュートリアルを非表示
		document.getElementById(setting_stext[i]).style.display = 'none';
		document.getElementById(setting_block[i]).style.zIndex = '1';
		document.getElementById(setting_block[i]).removeEventListener('click', overlayclick);

		i++;
		
		if(i < setting_stext.length){
			showStep(i);
		} else {
			smodalobj.style.display = 'none';
			smodalobj.removeEventListener('click', overlayclick);

			// 要素の無効化解除
			document.querySelectorAll('.tutorial-disabled').forEach(el => {
				el.classList.remove('tutorial-disabled');
				el.removeAttribute('disabled');
			});
			
			// チュートリアルがすべて終わったあと
			fetch('TutorialServlet', { method: 'POST' }).then(() => {
			    // 正常にtutorial=offになったら遷移
			    window.location.href = 'TopPageServlet';
			});
		}
	}
}
  