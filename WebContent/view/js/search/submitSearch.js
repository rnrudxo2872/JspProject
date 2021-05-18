export default class SearchForm {
    constructor($form) {
        const $SearchType = document.createElement('select');
        $SearchType.className = 'searchType';
        $SearchType.name = 'type';
        
        const Types = ['제목', '내용', '제목+내용', '작성자'];
        
        $SearchType.innerHTML =
        `${Types.map((Type,idx) => {
        return `<option value="${idx+1}">${Type}</option>`
        }).join('')}`;

        $SearchType.firstChild.setAttribute('selected', 'selected');

        const $searchBox = document.createElement('input');
        $searchBox.type = 'text';
        $searchBox.name = 'search';
        $searchBox.placeholder = '게시물 검색'

        const $btn = document.createElement('input');
        $btn.type = 'submit';
        $btn.value = '검색'

        $form.appendChild($SearchType);
        $form.appendChild($searchBox);
        $form.appendChild($btn);
        
        $form.addEventListener('submit', e => {
        	 if (100 < $searchBox.value.length || $searchBox.value.length < 2) {
                 alert('검색어는 최소 2글자 이상, 100글자 이하입니다.')
                 e.preventDefault();
             } else {
                const searchTerm = $searchBox.value;
                const searchType = $SearchType.selectedOptions[0].dataset.id;
                
            }
        })
    }
}