let lat;
let long;

let SuccessCatch = (curPosition) => {
    lat = curPosition.coords.latitude;
    long = curPosition.coords.longitude;
	console.log(lat, long);
	init();
}

let ErrorCatch = () => {
    console.log("GeoCoordinates missing");
}

let askGeo = () => {
    navigator.geolocation.getCurrentPosition(SuccessCatch, ErrorCatch);
}

askGeo();