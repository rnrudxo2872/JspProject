let searchId = () =>{
	let curId = document.getElementsByName("id");
	console.log("searchId : " + curId[0].value);
	
	let param = `id=${curId[0].value}`;
	
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
			
			$(".idSearchInfo").html(`<span style="color:${ret[1]}">${ret[0]}</span>`);
		}
	})
	
}