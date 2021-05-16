const commentForm = document.querySelector(".comment-container__form");
const commentInput = document.querySelector("#userComment");
const commentUser = document.querySelector("#user_id").value;
const commentBoardNum = document.querySelector("#board_num").value;
const commentList = document.querySelector(".comment-container__commentList");

let footer =  document.querySelector('.footer-container');
let commentListHead = document.querySelector(".commentList-head");

let count = 0;

let insertCommentHead = async() =>{
	let numberOfComment = await (await fetch(`../controller/comment/getNumberOfComment.jsp?num=${commentBoardNum}`)).text()
	console.log(numberOfComment);
	if(numberOfComment == 1)
	    commentListHead.innerText = `1 comment`;
	else
		commentListHead.innerText = `${numberOfComment} comments`;
}


let CommentFunc = {
	Update : async(event) =>{
		event.preventDefault();
		console.log(event.target);
		
		let This = event.target;
		
		let userInter = This.parentNode.outerHTML;
		console.log(userInter);
		
		let contentContainer = This.parentNode.parentNode;
		let Parent = This.parentNode.parentNode.parentNode;

		let inputUpdateContent = document.createElement("input");
		inputUpdateContent.className = "updateComment";
		inputUpdateContent.setAttribute("type","text");
		
		let UserID = Parent.querySelector('.commentUserName').innerText;
		console.log(Parent);
		
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
		    		
		    		let updateCom = await fetch('../controller/comment/updateComment.jsp',fetchData);
		    		console.log(updateCom);
		    		
		    		let updateContent = inputUpdateContent.value;
		    		contentContainer.innerHTML = `${updateContent}${userInter}`;
		    	}
		    }
		})
		contentContainer.innerHTML = '';
		contentContainer.appendChild(inputUpdateContent);
		/*let index = contentContainer.innerText.indexOf("수정");
		let content = contentContainer.innerText.substring(0,index)
		console.log(contentContainer.innerText.substring(0,index));*/
	},
	Delete : async(event) =>{
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
} 

let insertCommentButton = (idx) => {
	let btnContainer = document.createElement("span");

	let update = document.createElement("a");
	let del = document.createElement("a");

	update.setAttribute("href", `../controller/comment/updateComment.jsp?idx=${idx}`);
	del.setAttribute("href", `../controller/comment/delComment.jsp?idx=${idx}`);
	
	update.innerText = "수정";
	del.innerText = "삭제";
	
	update.style.marginLeft = "10px";
	update.style.marginRight = "10px";
	
	update.addEventListener("click", CommentFunc.Update)
	del.addEventListener("click", CommentFunc.Delete);

	btnContainer.appendChild(update);
	btnContainer.appendChild(del);

	return btnContainer;
}

let insertHtml = (commentsJson, insert) =>{
	if(commentsJson !== null){
		commentsJson.forEach(element =>{
	
		    let commenttail = document.createElement("div");
		    commenttail.setAttribute("class","commentList-tail");
		    console.log(element);
		    commenttail.setAttribute("data-id",element.idx);
		    
		    let userName = document.createElement("div");
		    userName.setAttribute("class","commentUserName");
		    userName.innerText = element.user_id;

		    let date = document.createElement("div");
		    date.setAttribute("class","commentDate");
		    date.innerText = element.date;

		    let commentContent = document.createElement("div");
		    commentContent.setAttribute("class","commentContent");
		    commentContent.innerText = element.comment;
		    
		    //console.log(element.idx);
		    if(commentUser === element.user_id){
		    	let tempElement = insertCommentButton(element.idx);
		    	commentContent.appendChild(tempElement);
		    }

		    commenttail.appendChild(userName);
		    commenttail.appendChild(date);
		    commenttail.appendChild(commentContent);
		    
		    if(insert){
		    	commentList.prepend(commenttail);
		    }else{
		    	commentList.appendChild(commenttail);
		    }
		})
	}
	
}

let insertComment = async() =>{
	
	let data = {
			user_id:commentUser,
			comment:commentInput.value,
			board_num:commentBoardNum
	}
	
	let fetchData = {
		    method: 'POST',
		    body: JSON.stringify(data),
		    headers: {
		        'Content-Type': 'application/json'
		   }
	}
	
	commentInput.value = "";
	
	
	let commentsJson = await (await fetch("../controller/comment/insertComment.jsp",fetchData)).json();

	//insertCommentLen(commentsJson);
	//commentList.innerHTML = commentListHead.outerHTML;
	
	insertHtml(commentsJson, true);
	insertCommentHead();
}

let FetchInsert = (event) =>{
	event.preventDefault();
	if(confirm("덧글을 작성하시겠습니까?")){
		
		insertComment()
	}
}


const bottomObserver = new IntersectionObserver(targetSearch);

bottomObserver.observe(footer);


let initFetch = async() =>{
	
	let commentsJson = await (await fetch(`../controller/comment/contentComments.jsp?num=${commentBoardNum}&start=${count}`).catch(err)).json();
	
	count += 5;
	//insertCommentLen(commentsJson);
	insertHtml(commentsJson);
	
	if(commentsJson == null)
		bottomObserver.unobserve(footer);
	
}

function targetSearch(entries){
    entries.forEach(element => {
        if (!element.isIntersecting)
            return;
        
        initFetch();
    });
}

let err = (errorArgs) => {
	console.log(errorArgs);
	let res = new Response();
	alert("댓글 파싱 에러!");
	return res;
}

insertCommentHead();

if(commentForm !== null)
	commentForm.addEventListener("submit",FetchInsert);