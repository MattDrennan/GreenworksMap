<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import = "com.GREENWORKS.DAO.*" %> <%-- to access the DAO package java code --%>
    <%@ page import = "com.GREENWORKS.object.*" %> <%-- to access the object package --%>
    <%@ page import = "java.util.Date" %> <%-- (for specification only) to parse and/or format Date values --%>
    <%@ page import = "java.util.*" %> <%-- to access most Java classes (i.e: LinkedList, Scanner, Date)--%>
<!DOCTYPE html>
    <html>
    <head>
        <!--
            Filename: OrlandoEcoMap.com
            Date of Creation: 27 Sep 2021
            Editor: Kashai Bingham
            Edited: 28 Oct 2021
            Version: 2.3
        -->
        
        <meta charset="utf-8" />
        <title>Eco-map - City of Orlando</title>

        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet" />
    </head>


    <body>
        <header>
            <a id="menu" href="">&#9776; Menu</a>
            <a id="logo" href="https://www.orlando.gov"><img src="icons/CityOfOrlando_logo.png" alt="City of Orlando logo"></a>
            <input id="search" type="text" placeholder="Find almost anything on our website">
            <input id="search_button" type="button" value="Search">
            <a id="mobile_search" href=""><img src="icons/search_icon.png"></a>
        </header>
        <!-- adds the mock header for the page including the CoO logo, a search field, and a menu -->

        <section>
            <div>
                <ul class="breadcrumb">
                    <li><a href="https://www.orlando.gov/Home">Home</a></li>
                    <li>Eco-map</li>
                </ul>
            </div>
            <!-- adds a static breadcrumb navigation to show the user the URL path taken to the current page -->

            <h1>Eco-map</h1>            
        </section>
        <div id="map"></div> 
        <!--sets the parameters for the Google Maps API embed -->

        <script	async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9nc7U6UPDjbOZUwgTn1pdDxBqX49zjI4&callback=init"
        ></script>
        <!--script renders the map under the breadcrumb nav --> 
        <script src="scripts/mapEmbed.js"></script> <!--references the init callback to the mapEmbed js file -->
        <div id="foot">
            <footer>
                <div id="left">
                  <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
                  <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
                  <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
                </div>
                <div id="right">
                  © 2021 City of Orlando | 
                Powered by <a href="http://www.opencities.com/">OpenCities</a>
                </div>                           
              </footer>
        </div>
        <!-- simulates the footer as shown on the real CoO site -->
    </body>
</html>