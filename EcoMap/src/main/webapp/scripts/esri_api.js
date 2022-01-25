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
        }
    };
    //creates an enum of formatted custom markers 

    //to add a GraphicsLayer instead of a FeatureLayer for marker population
    var gL = new GraphicsLayer();
    map.add(gL);// adds GraphicsLayer - fails to render marker
    
    var graphic;

    for (let i = 0; i < points.length; i++) {
        icon = Markers.TRANSPORTATION;
        addPoints(points[i], icon);
    } //TODO change so the icon is dynamically selected based on the points provided

    function addPoints(point, icon) {
        //TODO add a distinguising method for each of the points and corresponding marker
        graphic = new Graphic ({
            geometry: point,
            symbol: icon
        });
        //add a popupTemplate property for the display content

        gL.add(graphic);
    }
});