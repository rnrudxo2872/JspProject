import { soltFunc } from "./event/foodSelect.js";

export default class viewSlot{
    state = {
        visible:false,
        data: null
    }

    constructor({$app,PrevClick}){

        this.$target = document.createElement('div');
        this.$target.id = 'slotWrapper';
        
        this.$title = document.createElement('h1');
        this.$title.id = 'title';
        this.$title.innerText = '음식추천';

        this.$slot = document.createElement('div');
        this.$slot.id = 'foods';

        this.$ball = document.createElement('div');
        this.$ball.className = 'ball blue';
        this.$ball.innerText = '?';

        this.$findBtn = document.createElement('button');
        this.$findBtn.id = 'findBtn';
        this.$findBtn.className = 'btn';
        this.$findBtn.innerText = '찾기!';
        this.$findBtn.classList.add("hide");

        this.$btn = document.createElement('button');
        this.$btn.id = 'slotBtn';
        this.$btn.className = 'btn';
        this.$btn.innerText = '추첨!';
        
        this.$prevBtn = document.createElement('button');
        this.$prevBtn.id = 'prevBtn';
        this.$prevBtn.className = 'btn';
        this.$prevBtn.innerText = '뒤로!';
        this.$prevBtn.addEventListener('click', e => PrevClick());

        $app.appendChild(this.$target);

        this.setData(this.state);
    }

    setData(nextData){
        this.state = nextData;
        this.render();
    }

    render(){
        this.$target.style.display = this.state.visible ? 'flex' : 'none';
        
        this.$target.appendChild(this.$title);
        this.$target.appendChild(this.$slot);
        this.$target.appendChild(this.$findBtn);
        this.$target.appendChild(this.$btn);
        this.$target.appendChild(this.$prevBtn);
        this.$slot.appendChild(this.$ball);

        this.$btn.addEventListener('click', e => {
            soltFunc(e, this.state.data, this.$findBtn);
        })
    }
}