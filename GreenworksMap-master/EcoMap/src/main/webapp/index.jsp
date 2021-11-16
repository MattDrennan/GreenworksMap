<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
    <head>
        <!--
            Filename: OrlandoEcoMap.com
            Date of Creation: 27 Sep 2021
            Editor: Kashai Bingham, Cason Fluharty
            Edited: 16 Nov 2021
        -->
        
        <meta charset="utf-8" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no" />
        <title>Eco-map - City of Orlando</title>

        <style>
            html, 
            body,
            #viewDiv {
                padding: 0;
                margin: 0;
                height: 100%;
                width: 100%;
            }
        </style>
        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet">
        <script	src="https://js.arcgis.com/4.21/"></script>

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
                basemap: "arcgis-navigation" // Basemap layer service
                });

                //This is the custom FeatureLayer with the coordinates of some of the locations found in the .xlsx/.csv
                const layer = new FeatureLayer({
                    url: "https://services3.arcgis.com/NwOA6aZjvCAq7BpD/arcgis/rest/services/locations_2/FeatureServer/0"
                });
                
                //These are the few values on the .csv used for the layer map. Doesn't seem to overlay for some reason.
                map.add(layer);

                const view = new MapView({
                map: map,
                //Orlando: -81.3789, 28.5384
                center: [-81.3789, 28.5384], // Longitude, latitude of Orlando, FL
                zoom: 11, // Zoom level
                container: "viewDiv" // Div element
                });     
        });
        </script>

    </head>


    <body>
        <header>
            <a id="menu" href="">&#9776; Menu</a>
            <img id="logo" src="icons/CityOfOrlando_logo.png" alt="City of Orlando logo">
            <input id="search" type="text" placeholder="Find almost anything on our website">
            <input id="search_button" type="button" value="Search">
        </header>
        
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
             
        <footer>
            <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
            <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
            <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
            &copy; 2021 City of Orlando | 
            Powered by <a href="http://www.opencities.com/">OpenCities</a>            
        </footer>

    </body>
</html>