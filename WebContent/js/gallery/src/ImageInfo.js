export default class ImageInfo{
    data = null;

    constructor({ $target, data, transEvent}) {
        this.$imageInfo = document.createElement('div');
        this.$imageInfo.className = 'ImageInfo';

        this.data = data;
        $target.appendChild(this.$imageInfo);
        
        this.transEvent = transEvent;

        document.addEventListener('click', event => {
            if (event.target === this.$imageInfo) {
                this.$imageInfo.style.display = 'none';
            }
        })
        
       document.addEventListener('keyup', event => {
            if (event.key === 'Escape') {
                this.$imageInfo.style.display = 'none';
            }
        })

        this.render();
    }

    setState(nextData) {
        this.data = nextData;
        this.render();
    }
    
    setTransDesc(convertedDesc){
    	this.data.image.alt = convertedDesc;
    	this.render();
    }

    render() {
        if (this.data.visible === true) {
            const { src,alt } = this.data.image;
       
            this.$imageInfo.innerHTML = `
            <div class="content-wrapper">
                <div class="title">
                    <span></span>
                    <div class="close">x</div>
                </div>
                <img src="${src}" alt="${alt}"/>        
                <div class="description">
                ${alt}
                </div>
            	<div>
            		<button id="transBtn">한글로 번역</button>
            	</div>
            </div>`;
            this.$imageInfo.style.display = 'block';
            
            document.querySelector('#transBtn').addEventListener('click', event => this.transEvent(alt))
            document.querySelector('.close').addEventListener('click', event => {this.$imageInfo.style.display = 'none';})

        } else
            this.$imageInfo.style.display = 'none';
    }
}