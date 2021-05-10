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

function updateSubmit(event){
	alert("dd");
	let fileChange = document.querySelector("#fileChange");
	
	/*//파일을 삭제하여 수정하였다면 삭제된 것.
	if(fileInput.value == "" && fileChange.value == "1" && existFile.style.display == "none"){
		fileChange.value = "delete";
		
	//파일을 변경 안한 것.
	}else if(fileInput.value == "" && fileChange.value == "1"){
		fileChange.value = "stable";
		
	//기존에 파일이 있지만 교체할 것인가?
	}else*/
	if(fileInput.value != "" && fileChange.value == "1"){
		if(!confirm("파일을 교체하시겠습니까?")){
			event.preventDefault();
			
		}
	}
}

updateForm.addEventListener("submit",updateSubmit);

console.log(existFile.style.display === "")