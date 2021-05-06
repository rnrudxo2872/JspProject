let curId = document.querySelector('.email-container__inputEmail');
let idInfo = document.querySelector('.idSearchInfo');

let searchId = () =>{
	
	if(curId.value.indexOf('@') === -1){
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

let checkId = () => {
	let status = idInfo.querySelector('span');
	console.log(status);
	console.log(status.style.color==='red');
	if(status.style.color === 'red')
		return false;
	else
		return true;
}

curId.addEventListener('keyup',searchId);