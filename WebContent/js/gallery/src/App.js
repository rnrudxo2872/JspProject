import ViewImageList from "./ViewImageList.js";
import ImageInfo from "./ImageInfo.js"
import Loading from "../../main/loading.js"
import { api } from "./api.js";

export default class App {
    imageSource = null;

    constructor($target) {
        this.$target = $target;

        this.ViewImageList = new ViewImageList({
            $target,
            data:{
            	visible:false,
            	image:null
            },
            Onclick: image => {
                this.ImageInfo.setState({
                    visible: true,
                    image
                });
            }
        });

        this.ImageInfo = new ImageInfo({
            $target,
            data: {
                visible: false,
                image: null
            },
            transEvent: async(desc) =>{
            	const data = {eng:desc};
            	const option = {
            		    method: 'POST',
            		    body: JSON.stringify(data),
            		    headers: {
            		        'Content-Type': 'application/json'
            		   }
            	}
            	const resObj = await api.papagoTranslate(option);
            	console.log(resObj);
            	this.ImageInfo.setTransDesc(resObj.message.result.translatedText);
            }
        });

        this.initState();
    }

    async initState() {
    	this.LoadingView = new Loading({$target:this.$target});
    	
        this.imageSource = await api.scrapImageSource();
        
        this.LoadingView.setState(false);
        this.ViewImageList.setState({visible:true, image:this.imageSource});
    }

}
