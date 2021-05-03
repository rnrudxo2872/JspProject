const pageBlockSelect = document.querySelector(".pageBlockSelector");
const curBoard = document.querySelector(".board-container");

let changeCountBoard = () => {
	console.log(pageBlockSelect.value);
	let changePageSize = pageBlockSelect.value;
	fetch(`shareBoard.jsp?pageNum=1&pageSize=${changePageSize}`)
	.then(res => {
		return res.text();
	})
	.then(html =>{
		let parser = new DOMParser();
		let doc = parser.parseFromString(html,"text/html");

		let docBoard = doc.querySelector(".board-container").innerHTML;
		curBoard.innerHTML = docBoard;
	})
}

pageBlockSelect.addEventListener('change',changeCountBoard);