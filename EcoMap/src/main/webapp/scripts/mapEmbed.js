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

let markers; //has to be let and not var otherwise the code breaks. 'markers' cannot read the markers stored in jsp
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
        
    for (let i=0; i < markers.length; i++) {
        addMarkers(markers[i]);
    } 
    //loops thru the markers array to add the values to the map

    function addMarkers(location) {
        var spid = location[0], locid = location[1], address = location[2], descr = location[3, zip = 4];
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
            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
          });

        //uncomment when the info window information is ready
    	var infoWindow = new google.maps.InfoWindow();
        marker.addListener("click", function(){
            infoWindow.setContent(content);
            infoWindow.open(map,marker);
        });
    }
    //adds and renders the locations and custom markers to the map

    function iconSelect(pillar) {
        var value = 0; //change so that the first digitis what is evaluated
        switch (pillar.charAt(0)) {
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