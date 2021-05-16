let curId = document.querySelector('.email-container__inputEmail');
let idInfo = document.querySelector('.idSearchInfo');
let joinForm = document.querySelector('.joinForm');

const DOMAIN = ['.com','.co.kr','net'];

let isValid  = (str) =>{
	
	const start = str.indexOf('@');
	let end = -1;
	
	for(var i = 0; i < DOMAIN.length; i++){
		if(str.indexOf(DOMAIN[i]) !== -1){
			end = str.indexOf(DOMAIN[i]);
		}
	}
	
	if(start === -1)
		return false;
	
	if(str - start < 5)
		return false;
	
	if(end === -1)
		return false;
	
	if(end - start < 5)
		return false;
	
	return true;
}

let searchId = () =>{
	if(!isValid(curId.value)){
		idInfo.innerHTML = '<span style="color:red">적절한 아이디가 아닙니다!</span>';
	}else{
		
		let param = `id=${curId.value}`;
		
		$.ajax({
			type:"POST",
			url:"../controller/checkId.jsp",
			data:param,
			dataType:"xml",
			success:function(args){
				let ret = [];
				
				$(args).find("status").each(function(){
					console.log($(this).text())
					ret.push($(this).text());
				});
				console.log(ret);
				$(".idSearchInfo").html(`<span style="color:${ret[1]}">${ret[0]}</span>`);
			}
		})
	}
	
}

let checkId = (e) => {
	let status = idInfo.querySelector('span');
	console.log(status);
	console.log(status.style.color==='red');
	if(status.style.color === 'red')
		e.preventDefault();
	
}

joinForm.addEventListener('submit',checkId);
curId.addEventListener('keyup',searchId);