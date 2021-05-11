const commentForm = document.querySelector(".comment-container__form");
const commentInput = document.querySelector("#userComment");
const commentUser = document.querySelector("#user_id").value;
const commentBoardNum = document.querySelector("#board_num").value;
const commentList = document.querySelector(".comment-container__commentList");

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

	let commentsJson = await (await fetch("../controller/insertComment.jsp",fetchData)).json();
	
	let commentListHead = document.querySelector(".commentList-head");
	
	if(commentsJson.length === 1)
	    commentListHead.innerText = `1 comment`;
	else
	    commentListHead.innerText = `${commentsJson.length} comments`;
	    	
	    
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

let FetchNow = (event) =>{
	event.preventDefault();
	if(confirm("덧글을 작성하시겠습니까?")){
		
		insertComment()
	}
}

commentForm.addEventListener("submit",FetchNow);