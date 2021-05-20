let tmp = 1;
let foods;

const decryptEffect = (element) => {
    const effect = setInterval(() => {
        element.innerText = foods[Math.floor(Math.random() * foods.length)].name;
    }, 10);

    setTimeout(() => {
        const random = Math.floor(Math.random() * foods.length);

        //1초후 멈춘다.
        clearInterval(effect);
        element.classList.add("done");
        element.innerText = foods[random].name;
        foods.splice(random, 1);
        console.log(foods);
    }, 1500);
}

const lottery = () => {
    document.querySelectorAll(".ball").forEach(element => {
        element.classList.remove("done");
        decryptEffect(element);
    });
}

export const soltFunc = (e, data, $findBtn) => {
    console.log("룰렛!");
    if (tmp === 1) {
        console.log(data);
        foods = data;
        tmp = 0;
        e.target.classList.add("hide");
        lottery();
        setTimeout(function () {
            (tmp = 1), e.target.classList.remove("hide");
            $findBtn.classList.remove("hide");
        }, 2000);
    }
};
