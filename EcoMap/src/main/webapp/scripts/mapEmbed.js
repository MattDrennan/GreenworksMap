/*
    Filename: mapEmbed.js
    Date of Creation: 28 Oct 2021
    Edited: 3 Nov 2021
    Editor: Kashai Bingham
*/

/*concepts and solutions modified from YouTube and StackOverflow: 
    https://www.youtube.com/watch?v=Zxf1mnP5zcw&ab_channel=TraversyMedia
    https://stackoverflow.com/questions/15096461/resize-google-maps-marker-icon-image
*/
function init() {
    var orlando = {lat: 28.5384,lng:-81.3789};
    var settings = {
        zoom:10,
        center:orlando
    };

    var map = new google.maps.Map(document.getElementById("map"), settings);
    var icons = [
        {
            url: "icons/GreenWorksIcons_Individual_CleanEnergy.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_GreenBuildings.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_Livability.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_LocalFoodSystems.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_SolidWaste.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_Transportation.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        {
            url: "icons/GreenWorksIcons_Individual_Water.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
    ];/*
    var markers = [
        [{lat: 28.3827392697488, lng:-81.51192658984489}, icons[5], "<h3><b>Hyatt Grand Cypress | Wall,  J-1772</b></h3><p>1 Grand Cypress Blvd 32836</p>"],
        [{lat: 28.36139019500751, lng:-81.65126904290888}, icons[5], "<h3><b>The Grove Resort & Spa | J-1772</b></h3><p>15651 Grove Resort Av 34787</p>"],
        [{lat: 28.63589290791497, lng:-81.40665410148256}, icons[5], "<h3><b>Arbors at Maitland Apartments | J-1772</b></h3><p>8636 Villa Pt 32810</p>"],
        [{lat: 28.529192569341674, lng:-81.30176163216836}, icons[2], "<h3><b>Engelwood Pool/Neighborhood Center</b></h3><p>6123 La Costa Dr 32807</p>"],
        [{lat: 28.54339138017911, lng:-81.37082163216803}, icons[2], "<h3><b>Lake Eola Park</b></h3><p>512 East Washington Street 32801</p>"],
        [{lat: 28.56785758601878, lng:-81.35732177634839}, icons[2], "<h3><b>Leu Gardens</b></h3><p>1920 N Forest Ave 32803</p>"],
        [{lat: 28.549277200995455, lng:-81.3723680744954}, icons[3], "<h3><b>Lake Eola Heights</b></h3><p>406 East Amelia Street 32803</p>"],
        [{lat: 28.56163747511122, lng:-81.36069591875179}, icons[3], "<h3><b>Colonialtown Community Garden</b></h3><p>1517 Lake Highland Drive 32803</p>"],
        [{lat: 28.502025309871062, lng:-81.31224305571776}, icons[3], "<h3><b>Citrus Square – resident only</b></h3><p>5625 Hickey Drive 32822</p>"],
        [{lat: 28.528717265309098, lng:-81.30133154751329}, icons[4], "<h3><b>Engelwood Neighborhood Center Drop-off</b></h3><p>6123 La Costa Drive 32807</p>"],
        [{lat: 28.525619067249835, lng:-81.39507803216848}, icons[4], "<h3><b>Solid Waste Management Division Drop-off</b></h3><p>1028 South Woods Avenue 32805</p>"],
        [{lat: 28.525573814470956, lng:-81.32556584565985}, icons[4], "<h3><b>Dover Shores Community Center Drop-off</b></h3><p>1400 Gaston Foster Rd 32812</p>"],
    ];
    //creates an array of locations on the map with their respective custom markers

    
    for (let i=0; i < markers.length; i++) {
        addMarkers(markers[i]);
    } */
    //loops thru the markers array to add the values to the map



    function addMarkers(location) {
        var marker = new google.maps.Marker({
            position: location[0],
            map: map,
            icon: location[1],
            title: location[2]
        });

        
        //uncomment when the info window information is ready 
        infoWindow = new google.maps.InfoWindow({content:""});
        marker.addListener("click", function(){
            infoWindow.setContent(marker.getTitle());
            infoWindow.open(map,marker);
        })
         
    }
    //adds and renders the locations and custom markers to the map
}