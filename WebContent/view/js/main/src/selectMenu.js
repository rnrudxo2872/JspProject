export default class selectMenu{
    constructor({$app,randClick,weathClick}){
    	console.log("선택 메뉴 활성화");
        this.$target = document.createElement('div');
        this.$target.className = 'select-container';

        this.$title = document.createElement('h1');
        this.$title.className = 'select-container__title'
        this.$title.innerText = "환영합니다.";

        this.$randomBtn = document.createElement('button');
        this.$randomBtn.className = 'select-container__btn';
        this.$randomBtn.innerText = '음식 랜덤 추천!';

        this.randClick = randClick;
        this.weathClick = weathClick;

        //this.$target.appendChild()

        $app.appendChild(this.$target);
        this.render();
    }

    render(){
        this.$target.appendChild(this.$title);
        this.$target.appendChild(this.$randomBtn);

        this.$randomBtn.addEventListener('click', e =>{
            this.randClick();
        })
    }
}