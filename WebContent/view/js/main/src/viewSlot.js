export default class viewSlot{
    state = {
        visible:false,
        data:null
    }

    constructor({$app}){

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

        this.$btn = document.createElement('button');
        this.$btn.id = 'slotBtn';
        this.$btn.innerText = '시작!';
        
        $app.appendChild(this.$target);

        this.setData(this.state);
    }

    setData(nextData){
        this.state = nextData;
        this.render();
    }

    render(){
        this.$target.style.display = this.state.visible ? 'block' : 'none';
        
        this.$target.appendChild(this.$title);
        this.$target.appendChild(this.$slot);
        this.$target.appendChild(this.$btn);
        this.$slot.appendChild(this.$ball);
    }
}