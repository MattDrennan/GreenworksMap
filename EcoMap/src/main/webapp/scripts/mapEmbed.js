/*
    Filename: mapEmbed.js
    Date of Creation: 28 Oct 2021
    Edited: 27 Nov 2021
    Editor: Kashai Bingham
*/
//test solution from SO: https://stackoverflow.com/questions/46868703/google-maps-api-add-marker-by-address-javascript/46906152

/*concepts and solutions modified from YouTube and StackOverflow: 
    https://www.youtube.com/watch?v=Zxf1mnP5zcw&ab_channel=TraversyMedia
    https://stackoverflow.com/questions/15096461/resize-google-maps-marker-icon-image
*/

//console.log(locations);
function initMap() {
    var orlando = {lat: 28.5384,lng:-81.3789};
    var settings = {
        zoom:10,
        center:orlando
    };

    var map = new google.maps.Map(document.getElementById("map"), settings);
    var geocoder = new google.maps.Geocoder();

    const Icons = {
        FOOD: {
            url: "icons/food.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        LIVABILITY: {
            url: "icons/livability.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        WASTE: {
            url: "icons/waste.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        WATER: {
            url: "icons/water.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        ENERGY: {
            url: "icons/energy.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        BUILDINGS: {
            url: "icons/buildings.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        TRANSPORTATION: {
            url: "icons/transportation.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        }
    };
    //creates an enum of formatted custom markers 
    
    var example = [
    	["Icons.TRANSPORT", "<h3><b>Hyatt Grand Cypress | Wall,  J-1772</b></h3><p>1 Grand Cypress Blvd 32836</p>", "1 Grand Cypress Blvd"],
    	["Icons.FOOD", "<h3><b>Lake Eola Park</b></h3><p>512 East Washington Street 32801</p>", "512 East Washington Street"],
    	["Icons.WASTE", "<h3><b>Engelwood Neighborhood Center Drop-off</b></h3><p>6123 La Costa Drive 32807</p>", "6123 La Costa Drive"]
    ];
    
    var markers = [
        [{lat: 28.3827392697488, lng:-81.51192658984489}, Icons.TRANSPORTATION, "<h3><b>Hyatt Grand Cypress | Wall,  J-1772</b></h3><p>1 Grand Cypress Blvd 32836</p>"],
        [{lat: 28.36139019500751, lng:-81.65126904290888}, Icons.TRANSPORTATION, "<h3><b>The Grove Resort & Spa | J-1772</b></h3><p>15651 Grove Resort Av 34787</p>"],
        [{lat: 28.63589290791497, lng:-81.40665410148256}, Icons.TRANSPORTATION, "<h3><b>Arbors at Maitland Apartments | J-1772</b></h3><p>8636 Villa Pt 32810</p>"],
        [{lat: 28.529192569341674, lng:-81.30176163216836}, Icons.FOOD, "<h3><b>Engelwood Pool/Neighborhood Center</b></h3><p>6123 La Costa Dr 32807</p>"],
        [{lat: 28.54339138017911, lng:-81.37082163216803}, Icons.FOOD, "<h3><b>Lake Eola Park</b></h3><p>512 East Washington Street 32801</p>"],
        [{lat: 28.56785758601878, lng:-81.35732177634839}, Icons.FOOD, "<h3><b>Leu Gardens</b></h3><p>1920 N Forest Ave 32803</p>"],
        [{lat: 28.549277200995455, lng:-81.3723680744954}, Icons.LIVABILITY, "<h3><b>Lake Eola Heights</b></h3><p>406 East Amelia Street 32803</p>"],
        [{lat: 28.56163747511122, lng:-81.36069591875179}, Icons.LIVABILITY, "<h3><b>Colonialtown Community Garden</b></h3><p>1517 Lake Highland Drive 32803</p>"],
        [{lat: 28.502025309871062, lng:-81.31224305571776}, Icons.LIVABILITY, "<h3><b>Citrus Square – resident only</b></h3><p>5625 Hickey Drive 32822</p>"],
        [{lat: 28.528717265309098, lng:-81.30133154751329}, Icons.WASTE, "<h3><b>Engelwood Neighborhood Center Drop-off</b></h3><p>6123 La Costa Drive 32807</p>"],
        [{lat: 28.525619067249835, lng:-81.39507803216848}, Icons.WASTE, "<h3><b>Solid Waste Management Division Drop-off</b></h3><p>1028 South Woods Avenue 32805</p>"],
        [{lat: 28.525573814470956, lng:-81.32556584565985}, Icons.WASTE, "<h3><b>Dover Shores Community Center Drop-off</b></h3><p>1400 Gaston Foster Rd 32812</p>"],
    ];
    //hard codes an array of locations on the map with their respective custom markers
    //addMarkers("Orlando Executive Airport");
    for (let i=0; i < markers.length; i++) {
        addMarkers(markers[i]);
    } 
    //loops thru the markers array to add the values to the map

    function addMarkers(location) {
        var Icon = iconSelect(location[2]);
        //var address = location[2];
        var marker = new google.maps.Marker({
            position: location[0],
            map: map,
            icon: location[1]
        });
    	
    	//geocoder.geocode({address: location});
    
        //uncomment when the info window information is ready
    	var infoWindow = new google.maps.InfoWindow();
        marker.addListener("click", function(){
            infoWindow.setContent(location[2]);
            infoWindow.open(map,marker);
        });
    }
    //adds and renders the locations and custom markers to the map

    function iconSelect(pillar) {
        var value = "";
        switch (pillar) {
            case "livability":
                value = Icons.LIVABILITY;
                break;
            case "food":
                value = Icons.FOOD;
                break;
            case "buildings":
                value = Icons.BUILDINGS;
                break;
            case "waste":
                value = Icons.WASTE;
                break;
            case "water":
                value = Icons.WATER;
                break;
            case "energy":
                value = Icons.ENERGY;
                break;
            case "transport":
                value = Icons.TRANSPORT;
                break;
        }
        return value;
    }
    //selects the icons based on the corresponding pillar
}



/*
function clearMap() {
    marker.setMap(null);
}*/
// clears the map to properly update the markers when filtered