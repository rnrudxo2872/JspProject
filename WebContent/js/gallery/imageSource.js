const $target = document.querySelector('.food-contanier');

async function init(){
	$target.classList.remove('food-contanier');
	$target.classList.add('loading-container');
	$target.innerHTML = '<img src="./logos/logo.png" class="loading"></img>';
	
	const imageSource = await (await fetch('./imageSource.json').catch(ImageError)).json();
	
    $target.classList.add('food-contanier');
	$target.classList.remove('loading-container');
	$target.innerHTML = `${imageSource.map(item => `<img class="foodBlock" alt="" src="${item.src}"></img>`).join('')}`;
}

function ImageError(err){
	console.warn(err);
	alert("이미지 불러오기 에러!");
}

init();
