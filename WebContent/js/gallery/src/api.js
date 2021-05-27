export const api = {
    scrapImageSource: async() => await (await fetch('./imageSource.json').catch(ImageError)).json(),
    papagoTranslate: async(option) => await (await fetch('./translatePapa.json', option).catch(TranslateError)).json()
}

const ImageError = (err) => {
    console.warn(err);
    alert("이미지 불러오기 에러!");
}

const TranslateError = (err) => {
	console.warn(err);
    alert("번역 에러!");
}