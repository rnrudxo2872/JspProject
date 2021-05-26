export default class Loading{
    constructor({$target}) {
        this.$LoadingContainer = document.createElement('div');
		this.$LoadingContainer.classList.add('loading-container');
        
        this.visible = true;

        $target.appendChild(this.$LoadingContainer);
        this.render();
    }

    setState(visible) {
        this.visible = visible;
        this.render();
    }

    render() {
        if (this.visible)
            this.$LoadingContainer.innerHTML = '<img src="./logos/logo.png" class="loading"></img>';
        else
            this.$LoadingContainer.style.display = 'none';
    }
}