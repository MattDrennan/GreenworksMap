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

// Set up global marker array
var globalMarkers = [];

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
        },
        CLEANEVENT: {
            url: "https://maps.google.com/mapfiles/ms/icons/green-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        GREENEVENT: {
            url: "https://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        FOODEVENT: {
            url: "https://maps.google.com/mapfiles/ms/icons/red-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        LIVEVENT: {
            url: "https://maps.google.com/mapfiles/ms/icons/blue-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        WASTEEVENT: {
            url: "https://maps.google.com/mapfiles/ms/icons/blue-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        },
        TRANSPORTATIONEVENT: {
            url: "icons/lightblue-dot.png",
            scaledSize: new google.maps.Size(35, 35),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        }
    };
    //creates an enum of formatted custom markers 

    // Loop through markers and add them
    if (markers.length > 0)  {
        for (let i = 0; i < markers.length; i++)
        {
            // Set up variables to add marker
            spid = markers[i][1];
            locid = markers[i][0];
            address = String(markers[i][2]);
            descr = markers[i][3];
            zip = markers[i][4];

            // Add marker to map
            addMarkers(spid, locid, address, descr, zip);
        }
    }
    //loops thru the markers array to add the values to the map

    // addMarkers: Adds markers to the map
    function addMarkers(spid, locid, address, descr, zip)
    {
        var Icon = iconSelect(spid), content = "<h3><b>" + descr + "</b></h3><p>" + address + " " + zip  + "</p>";
    
        geocoder.geocode({'address': address}, function(results, status)
        {
            try
            {
                var coords = results[0].geometry.location;

                if (status === 'OK')
                {
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: coords,
                        icon: Icon,
                        type: spid
                    });

                    var infoWindow = new google.maps.InfoWindow();
                    marker.addListener("click", function(){
                        infoWindow.setContent(content);
                        infoWindow.open(map,marker);
                    });

                    // Add to global array to call later
                    globalMarkers.push(marker);
                }
                else
                {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            }
            catch
            {
                // Catch errors here
            }
        });
    }
    //adds and renders the locations and custom markers to the map

    function iconSelect(pillar) {
        // Set up value
        var value = 1;

        // Check which icon
        switch (pillar) { 
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
            case "8":
                value = Icons.CLEANEVENT;
                break;
            case "9":
                value = Icons.GREENEVENT;
                break;
            case "10":
                value = Icons.FOODEVENT;
                break;
            case "11":
                value = Icons.LIVEVENT;
                break;
            case "12":
                value = Icons.WASTEEVENT;
                break;
            case "13":
                value = Icons.TRANSPORTATIONEVENT;
                break;
        }
        return value;
    }
    //selects the icons based on the corresponding pillar
}