const InsertWeather = document.querySelector('.weatherData');
const InsertIcon = document.querySelector('.weatherIcon');
const setName = 'Coordinates'
const WEATHER_APIKEY = '9ec4f040743c8a068d8372f0f8af47d2';

let getWeather = (lat,long) => {
    fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&lang=kr&appid=${WEATHER_APIKEY}&units=metric`)
        .then(res => {
            return res.json();
        }).then(json => {
            console.log(json);
            let weatherData = json.weather[0];
            let temp = json.main.temp;

            let weatherCondition = weatherData.main;
            let weatherdesc = weatherData.description;
            
            let weathericon = weatherData.icon;
            let iconURL = `http://openweathermap.org/img/wn/${weathericon}.png`;

            InsertWeather.innerHTML = `${weatherdesc}, ${temp}도`;
            InsertIcon.innerHTML = `<img style="max-height: 30px; max-width: 30px;" src=${iconURL}></img>`
    })
}

let SuccessCatch = (curPosition) => {
    let lat = curPosition.coords.latitude;
    let long = curPosition.coords.longitude;

    let coords = {
        lat,
        long
    }

    localStorage.setItem(setName,JSON.stringify(coords));
    getWeather(lat,long);
}

let ErrorCatch = () => {
    console.log("GeoCoordinates missing");
}

let askGeo = () => {
    navigator.geolocation.getCurrentPosition(SuccessCatch, ErrorCatch);
}

let getCoordinate = () => {
    const loadGeo = localStorage.getItem(setName);
    if (loadGeo === null) {
        askGeo();
    } else {
        let geoData = JSON.parse(loadGeo);
        let lat = geoData.lat;
        let long = geoData.long;

        getWeather(lat, long);
    }
}

getCoordinate();