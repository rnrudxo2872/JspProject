export default class selectMenu{
    mapping = {
        init:['환영합니다.','환영합니다.'],
        Thunderstorm: ['천둥치는',"천둥 비가 내리는 날입니다. 야외활동에 주의하세요! 어떤 음식을 드실지 정하셨나요?"],
        Drizzle: ['이슬비 오는',"이슬비가 내리는 날이네요! 어떤 음식을 드실지 정하셨나요?"],
        Rain: ['비오는',"오늘은 비가 오네요! 어떤 음식을 드실지 정하셨나요?"],
        Snow: ['눈오는',"오늘은 눈이 오네요! 어떤 음식을 드실지 정하셨나요?"],
        Mist: ['안개 낀',"안개가 낀 날씨입니다! 어떤 음식을 드실지 정하셨나요?"],
        Smoke: ['연기 있는...',"오늘은... 연기가...? 어떤 음식을 드실지 정하셨나요?"],
        Haze: ['안개 낀',"오늘은 안개가 낀 날입니다! 어떤 음식을 드실지 정하셨나요?"],
        Dust: ['먼지 많은',"먼지가 많은 날이네요ㅠㅠ 어떤 음식을 드실지 정하셨나요?"],
        Fog: ['안개 일렁이는',"오늘 안개가 심한 날이네요! 어떤 음식을 드실지 정하셨나요?"],
        Sand: ['모래 흩날리는',"모래 먼지가 많은 날입니다! 호흡기 질환에 조심하세요. 어떤 음식을 드실지 정하셨나요?"],
        Dust: ['먼지 주의보인 ',"오늘은 먼지가 많은 날이네요ㅠㅠ! 어떤 음식을 드실지 정하셨나요?"],
        Ash: ['재가 날리는 ',"재가 많이 날리는 날입니다! 주의하세요. 어떤 음식을 드실지 정하셨나요?"],
        Tornado: ['돌풍이는 ',"돌풍이 몰아치는 날이에요! 어떤 음식을 드실지 정하셨나요?"],
        Squall: ['바람 많은 ',"바람이 많이 불어요! 조심하세요. 어떤 음식을 드실지 정하셨나요?"],
        Clear: ['화창한 ',"오늘 날씨가 맑네요! 어떤 음식을 드실지 정하셨나요?"],
        Clouds: ['흐린 ',"오늘은 흐리네요! 어떤 음식을 드실지 정하셨나요?"]
    }
    state = {
        visible:true,
        data: null,
        condition:'init'
    }

    constructor({$app,randClick,weathClick}){
        this.$target = document.createElement('div');
        this.$target.className = 'select-container';

        this.$title = document.createElement('h1');
        this.$title.className = 'select-container__title'
        this.$title.innerText = "환영합니다.";

        this.$btnWrapper = document.createElement('div');
        this.$btnWrapper.className = 'btnWrapper';

        this.$randomBtn = document.createElement('button');
        this.$randomBtn.className = 'modeBtn';
        this.$randomBtn.innerText = '음식 랜덤 추천!';
        
        this.$randomBtn.addEventListener('click', e =>{
            randClick();
        })
        
        this.$weatherBtn = document.createElement('button');
        this.$weatherBtn.className = 'modeBtn';
        this.$weatherBtn.addEventListener('click', e => weathClick());

        this.$btnWrapper.appendChild(this.$randomBtn);
        this.$btnWrapper.appendChild(this.$weatherBtn);

        $app.appendChild(this.$target);
        this.render();
    }

    getState() {
        return this.state;
    }

    setState(nextData){
        this.state = nextData;
        this.render();
    }

    render(){
        this.$target.appendChild(this.$title);

        if(this.state.condition !== 'init'){
        	this.$target.appendChild(this.$btnWrapper);
            this.$weatherBtn.innerText = `${this.mapping[this.state.condition][0]}날 먹고싶은 음식!`;
        }
        this.$title.innerText = this.mapping[this.state.condition][1];
        this.$target.style.display = this.state.visible ? 'flex' : 'none';
        console.log(this.$target.style.display);
        
    }
}