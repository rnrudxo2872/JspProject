import selectMenu from './src/selectMenu.js'
import viewSlot from './src/viewSlot.js'

export default class App{
	constructor($app){
		this.selectMenu = new selectMenu({
			$app,
			randClick: async e =>{
				console.log("랜덤!");
				const tmpData = await (await fetch('../controller/food/randomList.jsp'));
			},
			weathClick: e =>{
				console.log("날씨별!");
			}
		})

		this.viewSlot = new viewSlot({$app});
	}
}