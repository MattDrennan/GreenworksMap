/*
    Filename: mapEmbed.js
    Date of Creation: 28 Oct 2021
    Edited: 27 Nov 2021
    Editor: Kashai Bingham
*/

/*concepts and solutions modified from YouTube and StackOverflow: 
    https://www.youtube.com/watch?v=Zxf1mnP5zcw&ab_channel=TraversyMedia
    https://stackoverflow.com/questions/15096461/resize-google-maps-marker-icon-image
    https://stackoverflow.com/questions/46868703/google-maps-api-add-marker-by-address-javascript/46906152
*/

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
        
    /*var markers = [
         [31, 26489, "1000 W Buena Vista Dr", "Disney's Coronado Springs Resort | J-1772", 32830],
         [31, 26574, "15651 Grove Resort Av", "The Grove Resort & Spa | J-1772", 34787],
         [51, 72534, "100 Rosearden Drive", "Dickson Azalea Park", 32803],
         [51, 72603, "300 S Summerlin Ave", "Constitution Green Park", 32801],
         [62, 83226, "406 E. Amelia Street", "Lake Eola Heights Community Garden", 32803],
         [62, 83246, "Mai Kai Condominium Garden", "1935 Conway Rd", 32812],
         [71, 73114, "6123 La Costa Drive",	"Engelwood Neighborhood Center Drop-off", 32807],
         [71, 73144, "2200 Lee Road", "Lake Fairview Park Drop-off", 32810]
     ];*/
    //test values only - list should be dynamically called from jsp

    if (markers.length > 0)  {
        for (let i=0; i < markers.length; i++) {
            //array = markers[i].split(",");

            spid = markers[i][0];
            locid = markers[i][1];
            address = String(markers[i][2]);
            descr = markers[i][3];
            zip = markers[i][4];

            addMarkers(spid, locid, address, descr, zip);
        }
    }
    //loops thru the markers array to add the values to the map

    function addMarkers(spid, locid, address, descr, zip) {
        var Icon = iconSelect(spid), content = "<h3><b>" + descr + "</b></h3><p>" + address + " " + zip  + "</p>";
    
        geocoder.geocode({'address': address}, function(results, status) {
            var coords = results[0].geometry.location;
            if (status === 'OK') {
              map.setCenter(results[0].geometry.location);
              var marker = new google.maps.Marker({
                map: map,
                position: coords,
                icon: Icon
              });

              var infoWindow = new google.maps.InfoWindow();
              marker.addListener("click", function(){
                  infoWindow.setContent(content);
                  infoWindow.open(map,marker);
              });
            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
          });

    }
    //adds and renders the locations and custom markers to the map

    function iconSelect(pillar) {
        var value = 0, test = pillar.toString()[0]; //change so that the first digit is what is evaluated
        switch (test) { 
            case "1":
                value = Icons.ENERGY;
                break;
            case "2":
                value = Icons.WATER;
                break;
            case "3":
                value = Icons.TRANSPORTATION;
                break;
            case "4":
                value = Icons.BUILDINGS;
                break;
            case "5":
                value = Icons.LIVABILITY;
                break;
            case "6":
                value = Icons.FOOD;
                break;
            case "7":
                value = Icons.WASTE;
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