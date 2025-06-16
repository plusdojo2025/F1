/**
 * 
 */
 
 'use strict'

function submitClick(event){    
    const formId = event.target.id;
    if(formId === 'regist'){
         const privateCheckbox = document.getElementById('isPrivate');
	     if(privateCheckbox.checked){
	         if (!window.confirm('タスクをこの内容で登録しますか？\nこのタスクは他のユーザーに公開されます。')) {
	  	         event.preventDefault();
	  	         return false;
 		      }
	     }else {
             if (!window.confirm('タスクをこの内容で登録しますか？')) {
   	            event.preventDefault();
                return false;
             }
         }
        }
    else{

 const privateCheckbox = document.getElementById('isPrivate');
	 if(privateCheckbox.checked){
	  if (!window.confirm('タスクをこの内容に変更しますか？\nこのタスクは他のユーザーに公開されます。')) {
	  	event.preventDefault();
	  	return false;
 		}
	}else {
  if (!window.confirm('タスクをこの内容に変更しますか？')) {
   	event.preventDefault();
    return false;
    
  }
  }
}
  
}
