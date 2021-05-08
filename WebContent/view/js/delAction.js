function delFun(){
	let curPw = document.querySelector("div.form-container form input[name=pw]").value;
	let infoText = confirm("정말로 탈퇴하시겠습니까?");
	
	if(infoText){
		let form = document.querySelector("div.form-container form");
		form.action="../controller/userDelPro.jsp";
		form.submit();
	}
}