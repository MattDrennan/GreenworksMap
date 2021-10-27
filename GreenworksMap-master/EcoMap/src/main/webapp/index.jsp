<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
    <head>
        <!--
            Filename: OrlandoEcoMap.com
            Date of Creation: 27 Sep 2021
            Editor: Kashai Bingham, Cason Fluharty
            Edited: 27 Oct 2021
        -->
        
        <meta charset="utf-8" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no" />
        <title>Eco-map - City of Orlando</title>

        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet" />
    </head>


    <body>
        <header>
            <a id="menu" href="">&#9776; Menu</a>
            <img id="logo" src="icons/CityOfOrlando_logo.png" alt="City of Orlando logo">
            <input id="search" type="text" placeholder="Find almost anything on our website">
            <input id="search_button" type="button" value="Search">
        </header>
        
        <section>
            <div>
                <ul class="breadcrumb">
                    <li><a href="https://www.orlando.gov/Home">Home</a></li>
                    <li>Eco-map</li>
                </ul>
            </div>
            <h1>Eco-map</h1>
            <div id="viewDiv">
                <!-- Was used in Gmaps dev, may not be necessary anymore
                    <iframe
                        width="500"
                        height="350"
                        style="border:0"
                        loading="lazy"
                        allowfullscreen
                        src="https://www.google.com/maps/embed/v1/view?key=AIzaSyD9nc7U6UPDjbOZUwgTn1pdDxBqX49zjI4&center=28.5384,-81.3789&zoom=12">
                    </iframe>
                -->
            </div>
        </section>

	<script	src="https://js.arcgis.com/4.21/"
     async>
	</script>
             
        <footer>
            <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
            <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
            <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
            &copy; 2021 City of Orlando | 
            Powered by <a href="http://www.opencities.com/">OpenCities</a>            
        </footer>

        <script>
            require([
                "esri/config",
                "esri/Map", 
                "esri/views/MapView",
                "esri/layers/FeatureLayer"], 
                function (esriConfig, Map, MapView, FeatureLayer) {
        
                // Cason's API key
                //!! This is not a safe means of accessing a user's API key  !!//
                //!! and is only meant for development and testing purposes. !!//
                esriConfig.apiKey = "AAPK1f66897722e547629d609c4f34ee4f946tJ3cmaFZSGTRstlq9WCLOnn_0Fjgj6239iBnUPcWVld9DJyfKwPmssmsZjlXAnf";
        
                const map = new Map({
                  basemap: "arcgis-topographic" // Basemap layer service
                });
        
                const view = new MapView({
                  map: map,
                  center: [28.4811014,-81.4827487], // Longitude, latitude of Orlando, FL
                  zoom: 11, // Zoom level
                  container: "viewDiv" // Div element
                });
        
                // SQL query array; this is an exmaple, please populate with actual tables/data from SQL database
                const parcelLayerSQL = ["Choose a SQL where clause...", "UseType = 'Residential'",  "UseType = 'Government'", "UseType = 'Irrigated Farm'", "TaxRateArea = 10853", "TaxRateArea = 10860", "TaxRateArea = 08637", "Roll_LandValue > 1000000", "Roll_LandValue < 1000000"];
                let whereClause = parcelLayerSQL[0];
        
                
                // Add SQL UI
                const select = document.createElement("select","");
                select.setAttribute("class", "esri-widget esri-select");
                select.setAttribute("style", "width: 200px; font-family: 'Avenir Next'; font-size: 1em");
                parcelLayerSQL.forEach(function(query){
                    let option = document.createElement("option");
                    option.innerHTML = query;
                    option.value = query;
                    select.appendChild(option);
                });
        
                view.ui.add(select, "top-right");
        
                
                // Listen for changes
                select.addEventListener('change', (event) => {
                    whereClause = event.target.value;
                    queryFeatureLayer(view.extent);
                });
        
                
                // Get query layer and set up query
                const parcelLayer = new FeatureLayer({
                    // Not final; will have to change url to point to specific service (see below)
                    url: "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/LA_County_Parcels/FeatureServer/0",
                    // Info on url services:
                    // https://developers.arcgis.com/javascript/latest/api-reference/esri-layers-FeatureLayer.html#url
                });
        
                function queryFeatureLayer(extent) {
        
                    const parcelQuery = {
                        where: whereClause,  // Set by select element
                        spatialRelationship: "intersects", // Relationship operation to apply
                        geometry: extent, // Restricted to visible extent of the map
                        outFields: ["APN","UseType","TaxRateCity","Roll_LandValue"], // Attributes to return
                        returnGeometry: true
                    };
        
                    
                parcelLayer.queryFeatures(parcelQuery)
                .then((results) => {
                    displayResults(results);
                }).catch((error) => {
                    console.log(error.error);
                });
        
                }
        
                function displayResults(results) {
                // Create a blue polygon
                const symbol = {
                  type: "simple-fill",
                  color: [ 20, 130, 200, 0.5 ],
                  outline: {
                    color: "white",
                    width: .5
                  },
                };
        
                const popupTemplate = {
                  title: "Parcel {APN}",
                  content: "Type: {UseType} <br> Land value: {Roll_LandValue} <br> Tax Rate City: {TaxRateCity}"
                };
        
                // Assign styles and popup to features
                results.features.map((feature) => {
                  feature.symbol = symbol;
                  feature.popupTemplate = popupTemplate;
                  return feature;
                });
        
                // Clear display
                view.popup.close();
                view.graphics.removeAll();
                // Add features to graphics layer
                view.graphics.addMany(results.features);
        
              }
        
            });
          </script>

    </body>
</html>