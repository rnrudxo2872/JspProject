const getLocation = (option = {}) =>{
    return new Promise((resolve,reject) =>{
        navigator.geolocation.getCurrentPosition(resolve,reject);
    })
}

export const getPosition = async() =>{
    const Position =  await getLocation();
    const PosObject = {
        lat:Position.coords.latitude,
        lon:Position.coords.longitude
    }
    return PosObject;
}

export const fetchXml = async(PosObject) =>{
    const url = `../controller/weatherPage.jsp?lat=${PosObject.lat}&lon=${PosObject.lon}`;
    const textXml = await (await fetch(url).catch(ErrorFunc)).text();
    
    const parser = new DOMParser();
    return parser.parseFromString(textXml,"application/xml");
}

export const getWeather = async() =>{
    const PosObject = await getPosition();
    const xml = await fetchXml(PosObject);

    return xml.getElementsByTagName('status');
}

const ErrorFunc = (err) =>{
    console.warn(err);
    alert('날씨 정보 에러!');
}