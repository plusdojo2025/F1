document.getElementById('suggestForm').onsubmit = function (event){
  const timeSpan = document.getElementById('spanTime').value;
    
    
    if (timeSpan.trim() === '' ){
        document.getElementById('topError').textContent = 'この項目を入力してください。';
        event.preventDefault();
    }
}