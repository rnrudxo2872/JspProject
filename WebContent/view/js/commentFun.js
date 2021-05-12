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
	commentList.innerHTML = commentListHead.innerHTML;
	
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
commentForm.addEventListener("submit",FetchInsert);