import { commentUser, commentListHead } from "./commentFun.js"

export const DeleteCommentFunc = async(event) =>{
	console.log(event.target);
	event.preventDefault();
	
	let This = event.target;
	let Parent = This.parentNode.parentNode.parentNode;

	if(confirm("정말 삭제하겠습니까?")){
		Parent.style.display = "none";
		await fetch(This.href);

		let innerTEXT = commentListHead.innerText
		let lastPosition = innerTEXT.indexOf("comm");
		let commentNum = innerTEXT.substring(0,lastPosition-1);
		
		if(commentNum === 1)
		    commentListHead.innerText = `1 comment`;
		else
			commentListHead.innerText = `${commentNum-1} comments`;
	}
}

export const UpdateCommentFunc = async(event) =>{
	event.preventDefault();
	
	let This = event.target;
	console.log(This)
	let userInter = This.parentNode.outerHTML;
	let prevText = This.parentNode.parentNode.innerText.split("수정삭제")[0];
	let contentContainer = This.parentNode.parentNode;
	let Parent = This.parentNode.parentNode.parentNode;

	let inputUpdateContent = document.createElement("input");
	inputUpdateContent.className = "updateComment";
	inputUpdateContent.setAttribute("type","text");
	inputUpdateContent.value = prevText;
	
	let UserID = Parent.querySelector('.commentUserName').innerText;
	
	inputUpdateContent.addEventListener("keyup",async e =>{
	    if(e.key === 'Enter'){
	    	if(confirm("정말 수정하시겠습니까?")){
	    		
	    		let data = {
	    				user_id:commentUser,
	    				comment:inputUpdateContent.value,
	    				idx:Parent.dataset.id
	    		}
	    		
	    		let fetchData = {
	    			    method: 'POST',
	    			    body: JSON.stringify(data),
	    			    headers: {
	    			        'Content-Type': 'application/json'
	    			   }
	    		}
	    		
	    		let updateCom = await fetch('./updateCommentAction',fetchData);
	    		console.log(updateCom);
	    		
	    		let updateContent = inputUpdateContent.value;
	    		contentContainer.innerHTML = `${updateContent}${userInter}`;
	    	}
	    }
	})
	contentContainer.innerHTML = '';
	contentContainer.appendChild(inputUpdateContent);
}