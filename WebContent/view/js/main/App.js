import { getPosition, getWeather } from './src/getPosition.js';
import selectMenu from './src/selectMenu.js'
import viewSlot from './src/viewSlot.js'

export default class App{
	constructor($app){
		this.selectMenu = new selectMenu({
			$app,
			randClick: async e =>{
				console.log("랜덤!");
				const tmpData = await (await fetch('../controller/food/randomList.jsp')).json();
				console.log(tmpData);
				
                this.viewSlot.setData({
					visible: true,
                    data: tmpData
                })
                
                this.selectMenu.setState({
                    visible: false
                })
			},
			weathClick: e =>{
				console.log("날씨별!");
			}
		})

		this.viewSlot = new viewSlot({$app});

		this.setInit();
	}

	setWeatherData(data){
		return {
			local:data[0].innerHTML,
			temp:data[1].innerHTML,
			main:data[2].innerHTML,
			desc:data[3].innerHTML,
			icon:data[4].innerHTML
		}
	}

	async setInit(){
		this.position = await getPosition();
		const data = await getWeather();
		
		this.ConditionData = this.setWeatherData(data);

        this.selectMenu.setState({
            visible:true,
            data:null,
            condition: data[2].innerHTML
        });
	}
}