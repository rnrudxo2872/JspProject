export const api = {
    scrapImageSource: async() => await (await fetch('./imageSource.json').catch(ImageError)).json()
}

const ImageError = (err) => {
    console.warn(err);
    alert("이미지 불러오기 에러!");
}