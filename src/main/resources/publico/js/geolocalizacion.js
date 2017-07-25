var x = document.getElementById("ubicacion");
function myUbicacion() {
    navigator.geolocation.getCurrentPosition(showPosition);
}

function showPosition(position) {
    x.innerHTML = "Latitud: " + position.coords.latitude+ "   Longitud: " + position.coords.longitude;
}