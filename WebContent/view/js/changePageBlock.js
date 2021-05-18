import SearchForm  from "./search/submitSearch.js";

const $searchForm = document.querySelector(".board-container-footer__search");
const pageBlockSelect = document.querySelector(".pageBlockSelector");
const curBoard = document.querySelector(".board-container");

const searchForm = new SearchForm($searchForm);
const curPATH = window.location.pathname;

let searchObj = {};

if(curPATH.indexOf('boardSearch') !== -1){
	let searchParam = location.search.substr(location.search.indexOf('type'));
	searchParam = searchParam.split('&');
	searchParam.map(item => {
		let tmp = item.split('=');
		console.log(tmp)
		searchObj[tmp[0]] = tmp[1];
	})
	console.log(searchObj);
	searchForm.setProp(searchObj);
}

let getOtherBoards = html =>{
	let parser = new DOMParser();
	let doc = parser.parseFromString(html,"text/html");
	
	let docBoard = doc.querySelector(".board-container");
	return docBoard.innerHTML;
}

let getBoard = async(search) =>{
	let changePageSize = pageBlockSelect.value;
	let url = null;
	
	if(search.type === undefined || search.search === undefined)
		url = `shareBoard.jsp?pageNum=1&pageSize=${changePageSize}`;
	else{
		url = `boardSearch.jsp?pageNum=1&pageSize=${changePageSize}&type=${search.type}&search=${search.search}`;
	}
	
    let html = await (await fetch(url).catch(alertErr)).text()
    let parseBoards = await getOtherBoards(html);
    
    curBoard.innerHTML = parseBoards;
}

let changeCountBoard = () => {
		getBoard(searchObj);
}

function alertErr(error){
	console.warn(error);
	let res = new Response();
	alert("url 에러!");
	return res;
}

pageBlockSelect.addEventListener('change',changeCountBoard);