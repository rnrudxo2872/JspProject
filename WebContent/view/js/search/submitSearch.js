export default class SearchForm {
    constructor($form) {
    	
    	this.$target = $form;
    	
        this.$SearchType = document.createElement('select');
        this.$SearchType.className = 'searchType';
        this.$SearchType.name = 'type';
        
        this.Types = ['제목', '내용', '제목+내용', '작성자'];
        
        this.$searchBox = document.createElement('input');
        this.$searchBox.type = 'text';
        this.$searchBox.name = 'search';
        this.$searchBox.placeholder = '게시물 검색'

        this.$btn = document.createElement('input');
        this.$btn.type = 'submit';
        this.$btn.value = '검색'
        
        $form.addEventListener('submit', e => {
        	 if (100 < this.$searchBox.value.length || this.$searchBox.value.length < 2) {
                 alert('검색어는 최소 2글자 이상, 100글자 이하입니다.')
                 e.preventDefault();
             }
        })
        
        this.render();
    }
    
    setProp(nextData){
    	console.log(this.$SearchType[Number(nextData.type)-1]);
    	this.nowType = Number(nextData.type)-1;
    	this.nowWord = decodeURI(nextData.search);
    	
    	this.render();
    }
    
    render(){
    	this.$SearchType.innerHTML =
            `${this.Types.map((Type,idx) => {
            return `<option value="${idx+1}">${Type}</option>`
            }).join('')}`;
    	
    	if(this.nowType){
    		this.$SearchType[this.nowType].setAttribute('selected','selected');
    	}
    	if(this.nowWord){
    		this.$searchBox.value = this.nowWord;
    	}
    	
    	this.$target.appendChild(this.$SearchType);
    	this.$target.appendChild(this.$searchBox);
    	this.$target.appendChild(this.$btn);
    }
}