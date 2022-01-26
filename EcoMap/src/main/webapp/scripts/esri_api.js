var globalMarkers = [];

require([
    "esri/config",
    "esri/Map", 
    "esri/views/MapView",
    "esri/layers/FeatureLayer",
    "esri/Graphic",
    "esri/layers/GraphicsLayer",
    "esri/symbols/PictureMarkerSymbol",
    "esri/rest/locator"
],
    function (esriConfig, Map, MapView, FeatureLayer, Graphic, GraphicsLayer, PictureMarkerSymbol) {

    // Map Key
    esriConfig.apiKey = mapKey;

    const map = new Map({
    basemap: "arcgis-navigation" // Basemap layer service
    });

    //This is the custom FeatureLayer with the coordinates of some of the locations found in the .xlsx/.csv
    /*const layer = new FeatureLayer({
        url: "https://services3.arcgis.com/NwOA6aZjvCAq7BpD/arcgis/rest/services/locations_2/FeatureServer/0"
    });*/
    
    //These are the few values on the .csv used for the layer map. Doesn't seem to overlay for some reason.
    //map.add(layer); 

    const view = new MapView({
        map: map,
        //Orlando: -81.3789, 28.5384
        center: [-81.3789, 28.5384], // Longitude, latitude of Orlando, FL
        zoom: 9, // Zoom level
        container: "viewDiv" // Div element
    });

    var Markers = {
        FOOD: {
            type: "picture-marker",
            url: "icons/food.png",
            width: "35px",
            height: "35px"
        },
        LIVABILITY: {
            type: "picture-marker",
            url: "icons/livability.png",
            width: "35px",
            height: "35px"
        },
        WASTE: {
            type: "picture-marker",
            url: "icons/waste.png",
            width: "35px",
            height: "35px"
        },
        WATER: {
            type: "picture-marker",
            url: "icons/water.png",
            width: "35px",
            height: "35px"
        },
        ENERGY: {
            type: "picture-marker",
            url: "icons/energy.png",
            width: "35px",
            height: "35px"
        },
        BUILDINGS: {
            type: "picture-marker",
            url: "icons/buildings.png",
            width: "35px",
            height: "35px"
        },
        TRANSPORTATION: {
            type: "picture-marker",
            url: "icons/transportation.png",
            width: "35px",
            height: "35px"
        },
        CLEANEVENT: {
            type: "picture-marker",
            url: "https://maps.google.com/mapfiles/ms/icons/green-dot.png",
            width: "35px",
            height: "35px"
        },
        GREENEVENT: {
            type: "picture-marker",
            url: "https://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
            width: "35px",
            height: "35px"
        },
        FOODEVENT: {
            type: "picture-marker",
            url: "https://maps.google.com/mapfiles/ms/icons/red-dot.png",
            width: "35px",
            height: "35px"
        },
        LIVEVENT: {
            type: "picture-marker",
            url: "https://maps.google.com/mapfiles/ms/icons/blue-dot.png",
            width: "35px",
            height: "35px"
        },
        WASTEEVENT: {
            type: "picture-marker",
            url: "https://maps.google.com/mapfiles/ms/icons/blue-dot.png",
            width: "35px",
            height: "35px"
        },
        TRANSPORTATIONEVENT: {
            type: "picture-marker",
            url: "icons/lightblue-dot.png",
            width: "35px",
            height: "35px"
        }
    };
    //creates an enum of formatted custom markers 

    //to add a GraphicsLayer instead of a FeatureLayer for marker population
    var gL = new GraphicsLayer();
    map.add(gL);// adds GraphicsLayer - fails to render marker
    
    var graphic;

    for (let i = 0; i < points.length; i++) {
        addPoints(points[i]);
    } //TODO change so the icon is dynamically selected based on the points provided

    // Set up pop up
    view.popup.autoOpenEnabled = false;

    // Get the screen point from the view's click event
    view.on("click", function (event)
    {
        var screenPoint = {
            x: event.x,
            y: event.y
        };
   
        // Search for graphics at the clicked location
        // https://community.esri.com/t5/arcgis-api-for-javascript-questions/esri-javascript-4x-graphicslayer-on-click-event/m-p/482838
        view.hitTest(screenPoint).then(function (response)
        {
            if (response.results.length)
            {
                try
                {
                    var graphic = response.results.filter(function (result)
                    {
                        // Check if the graphic belongs to the layer of interest
                        return result.graphic.layer === gL;
                    })[0].graphic;

                    // Pop up
                    view.popup.open({
                        // Set the popup's title to the coordinates of the location
                        title: graphic.attr.name,
                        location: event.mapPoint,
                        content: graphic.attr.content
                    });
                }
                catch
                {
                    // Empty
                }
            }
        });
    });

    function addPoints(point) {
        //TODO add a distinguising method for each of the points and corresponding marker
        graphic = new Graphic ({
            geometry: point,
            symbol: iconSelect(point['dbType']),
            attr: point
        });
        //add a popupTemplate property for the display content

        // Add to global
        globalMarkers.push(graphic);

        gL.add(graphic);
    }

    function iconSelect(pillar) {
        // Set up value
        var value = 1;

        // Check which icon
        switch (pillar) { 
            case "1":
                value = Markers.ENERGY;
                break;
            case "2":
                value = Markers.WATER;
                break;
            case "3":
                value = Markers.TRANSPORTATION;
                break;
            case "4":
                value = Markers.BUILDINGS;
                break;
            case "5":
                value = Markers.LIVABILITY;
                break;
            case "6":
                value = Markers.FOOD;
                break;
            case "7":
                value = Markers.WASTE;
                break;
            case "8":
                value = Markers.CLEANEVENT;
                break;
            case "9":
                value = Markers.GREENEVENT;
                break;
            case "10":
                value = Markers.FOODEVENT;
                break;
            case "11":
                value = Markers.LIVEVENT;
                break;
            case "12":
                value = Markers.WASTEEVENT;
                break;
            case "13":
                value = Markers.TRANSPORTATIONEVENT;
                break;
        }
        return value;
    }
    //selects the icons based on the corresponding pillar
});