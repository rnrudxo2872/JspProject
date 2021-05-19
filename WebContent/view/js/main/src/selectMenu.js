export default class selectMenu{
    mapping = {
        Thunderstorm: "천둥 비가 내리는 날입니다. 야외활동에 주의하세요! 어떤 음식을 드실지 정하셨나요?",
        Drizzle: "이슬비가 내리는 날이네요! 어떤 음식을 드실지 정하셨나요?",
        Rain: "오늘은 비가 오네요! 어떤 음식을 드실지 정하셨나요?",
        Snow: "오늘은 눈이 오네요! 어떤 음식을 드실지 정하셨나요?",
        Mist: "안개가 낀 날씨입니다! 어떤 음식을 드실지 정하셨나요?",
        Smoke: "오늘은... 연기가...? 어떤 음식을 드실지 정하셨나요?",
        Haze: "오늘은 안개가 낀 날입니다! 어떤 음식을 드실지 정하셨나요?",
        Dust: "먼지가 많은 날이네요ㅠㅠ 어떤 음식을 드실지 정하셨나요?",
        Fog: "오늘 안개가 심한 날이네요! 어떤 음식을 드실지 정하셨나요?",
        Sand: "모래 먼지가 많은 날입니다! 호흡기 질환에 조심하세요. 어떤 음식을 드실지 정하셨나요?",
        Dust: "오늘은 먼지가 많은 날이네요ㅠㅠ! 어떤 음식을 드실지 정하셨나요?",
        Ash: "재가 많이 날리는 날입니다! 주의하세요. 어떤 음식을 드실지 정하셨나요?",
        Tornado: "돌풍이 몰아치는 날이에요! 어떤 음식을 드실지 정하셨나요?",
        Squall: "바람이 많이 불어요! 조심하세요. 어떤 음식을 드실지 정하셨나요?",
        Clear: "오늘 날씨가 맑네요! 어떤 음식을 드실지 정하셨나요?",
        Clouds: "오늘은 흐리네요! 어떤 음식을 드실지 정하셨나요?"
    }

    constructor({$app,randClick,weathClick}){
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

    setState(nextData){
    	console.log(nextData);
    	this.$title.innerText = this.mapping[nextData];

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