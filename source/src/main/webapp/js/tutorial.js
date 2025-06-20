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
		//最初の要素の表示
		document.getElementById(setting_stext[i]).style.display = 'block';
	document.getElementById(setting_stext[i]).style.backgroundColor = '#ffffff';
		document.getElementById(setting_block[i]).style.zIndex = '1500';
		smodalobj.style.display = 'block';
		//イベントを定義
		smodalobj.addEventListener('click', overlayclick);
		
	}
	
	//モーダルをクリックしたら起動し、不要な要素を非表示にして次の要素を表示する
	function overlayclick(){
		//現在の表示を消す
		if(i === 0){
			document.getElementById(setting_stext[i]).style.zIndex = '1';
			document.getElementById(setting_stext[i]).style.backgroundColor = 'rgb(244 244 244)';
			document.getElementById(setting_block[i]).style.zIndex = '1';
		}
		document.getElementById(setting_stext[i]).style.display = 'none';
		document.getElementById(setting_block[i]).style.zIndex = '1';
		i++;
		if(i < setting_stext.length ){
			document.getElementById(setting_stext[i]).style.display = 'block';
			document.getElementById(setting_block[i]).style.zIndex = '1500';
		}
		else{
			//モーダルの表示を消し、イベントを解除する
			smodalobj.style.display = 'none';
			smodalobj.removeEventListener('click', overlayclick);
		}
		
}
  }
  