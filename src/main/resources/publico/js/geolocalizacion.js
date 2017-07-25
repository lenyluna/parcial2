var x = document.getElementById("ubicacion");
var siglas = document.getElementById("pais")
function myUbicacion() {
    navigator.geolocation.getCurrentPosition(showPosition);
}

function showPosition(position) {
    x.innerHTML = "Latitud: " + position.coords.latitude+ "   Longitud: " + position.coords.longitude;
    var requestURL = 'http://ws.geonames.org/countryCodeJSON?lat=19.42&lng=-70.67&username=demo'
    var request = new XMLHttpRequest();
    request.open('GET',requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function () {
        var city = request.response;
        verCiudad(city);
    }
}
function verCiudad(jsonObj) {
   siglas.value=jsonObj['countryCode'];
}
