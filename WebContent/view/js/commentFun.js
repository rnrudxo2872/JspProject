const commentForm = document.querySelector(".comment-container__form");
const commentInput = document.querySelector("#userComment");
const commentUser = document.querySelector("#user_id").value;
const commentBoardNum = document.querySelector("#board_num").value;
const commentList = document.querySelector(".comment-container__commentList");

let commentListHead = document.querySelector(".commentList-head");

let insertCommentLen = (commentsJson) =>{
	if(commentsJson.length === 1)
	    commentListHead.innerText = `1 comment`;
	else
	    commentListHead.innerText = `${commentsJson.length} comments`;
}

let CommentFunc = {
	Update : (event) =>{
		event.preventDefault();
		console.log(event.target);
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

	update.setAttribute("href", `../controller/updateComment.jsp?idx=${idx}`);
	del.setAttribute("href", `../controller/delComment.jsp?idx=${idx}`);
	
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

let insertHtml = (commentsJson) =>{
	commentsJson.forEach(element =>{
		
	    let commenttail = document.createElement("div");
	    commenttail.setAttribute("class","commentList-tail");

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

	    commentList.appendChild(commenttail);
	})
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
	
	
	let commentsJson = await (await fetch("../controller/insertComment.jsp",fetchData)).json();

	insertCommentLen(commentsJson);
	commentList.innerHTML = commentListHead.outerHTML;
	
	insertHtml(commentsJson);
}

let FetchInsert = (event) =>{
	event.preventDefault();
	if(confirm("덧글을 작성하시겠습니까?")){
		
		insertComment()
	}
}


let initFetch = async() =>{
	let commentsJson = await (await fetch(`../controller/contentComments.jsp?num=${commentBoardNum}`).catch(err)).json();
	
	console.log(commentsJson);
	insertCommentLen(commentsJson);
	insertHtml(commentsJson);
}

let err = (errorArgs) => {
	console.log(errorArgs);
	let res = new Response();
	alert("댓글 파싱 에러!");
	return res;
}

initFetch();
if(commentForm !== null)
	commentForm.addEventListener("submit",FetchInsert);