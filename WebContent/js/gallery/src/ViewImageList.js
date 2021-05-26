export default class ViewImageList{
    data = null;

    constructor({ $target, data, Onclick }) {
        this.$foodContainer = document.createElement('div');
        this.$foodContainer.className = 'food-contanier';
        this.Onclick = Onclick;
        this.data = data;
        
        $target.appendChild(this.$foodContainer)
        this.render();
    }

    setState(nextData) {
        this.data = nextData;
        this.render();
    }

    render() {
        if (this.data.visible) {
            this.$foodContainer.innerHTML = `${this.data.image.map(item => `<img class="foodBlock" alt="${item.alt}" src="${item.src}"></img>`).join('')}`;
            this.$foodContainer.querySelectorAll('.foodBlock').forEach(($item, index) => {
                $item.addEventListener('click', e => this.Onclick(this.data.image[index]));
            })
            this.$foodContainer.style.display = 'grid';
        } else {
            this.$foodContainer.style.display = 'none';
        }
    }
}