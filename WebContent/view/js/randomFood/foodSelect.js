let tmp = 1;
let foods;

function decryptEffect(element) {
    const effect = setInterval(() => {
        element.innerText = foods[Math.floor(Math.random() * foods.length)];
    }, 10);

    setTimeout(() => {
        const random = Math.floor(Math.random() * foods.length);

        //1초후 멈춘다.
        clearInterval(effect);
        element.classList.add("done");
        element.innerText = foods[random];
        foods.splice(random, 1);
        console.log(foods);
    }, 1500);
}

function lottery() {
    document.querySelectorAll(".ball").forEach(element => {
        element.classList.remove("done");
        decryptEffect(element);
    });
}

document.getElementById("slotBtn").addEventListener("click", e => {
    if (tmp === 1) {
        foods =['네이버','카카오','배민','라인','쿠팡'];
        tmp = 0;
        e.target.classList.add("hide");
        lottery();
        setTimeout(function () {
            (tmp = 1), e.target.classList.remove("hide");
        }, 2000);
    }
});
