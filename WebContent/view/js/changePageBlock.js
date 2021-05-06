const pageBlockSelect = document.querySelector(".pageBlockSelector");
const curBoard = document.querySelector(".board-container");

let getOtherBoards = html =>{
	let parser = new DOMParser();
	let doc = parser.parseFromString(html,"text/html");
	
	let docBoard = doc.querySelector(".board-container");
	return docBoard.innerHTML;
}

let changeCountBoard = async() => {
	
	let changePageSize = pageBlockSelect.value;
	let url = `shareBoard.jsp?pageNum=1&pageSize=${changePageSize}`;
	
    let html = await (await fetch(url).catch(alertErr)).text()
    let parseBoards = await getOtherBoards(html);
    
    curBoard.innerHTML = parseBoards;
}

function alertErr(error){
	console.warn(error);
	let res = new Response();
	alert("url 에러!");
	return res;
}

pageBlockSelect.addEventListener('change',changeCountBoard);