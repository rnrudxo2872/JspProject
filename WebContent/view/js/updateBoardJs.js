const updateForm = document.querySelector(".insertBoard-container");
const DelButton = document.querySelector(".insertBoard-container__dleButton");
const existFile = document.querySelector("#existFile");
const fileInput = document.querySelector(".insertBoard-container form input[type=file]");

console.log(existFile.innerText);

if(existFile.innerText === ""){
	existFile.style.display = "none";

}else{
	DelButton.addEventListener("click",delExistFile);
}

function delExistFile(e){
	e.preventDefault();
	if(confirm("정말 삭제하겠습니까?")){		
		existFile.style.display = "none";
		existFile.innerHTML = "";
	}
}



console.log(existFile.style.display === "")