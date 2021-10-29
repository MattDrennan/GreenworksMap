<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import = "com.GREENWORKS.DAO.*" %> <%-- to access the DAO package java code --%>
    <%@ page import = "com.GREENWORKS.object.*" %> <%-- to access the object package --%>
    <%@ page import = "java.util.Date" %> <%-- (for specification only) to parse and/or format Date values --%>
    <%@ page import = "java.util.*" %> <%-- to access most Java classes (i.e: LinkedList, Scanner, Date)--%>
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
        <link href="scripts/map.js" rel="script" defer/>
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
            <div id="map">
                    <iframe
                        width="500"
                        height="350"
                        style="border:0"
                        loading="lazy"
                        allowfullscreen
                        src="https://www.google.com/maps/embed/v1/view?key=AIzaSyD9nc7U6UPDjbOZUwgTn1pdDxBqX49zjI4&center=28.5384,-81.3789&zoom=12">
                    </iframe>
            </div>
        </section>

	<script	src="https://maps.google.com/maps/api/js?key=AIzaSyCUT1_Ic9Ub7dI4otYSNTTOy8zn1XK5mE&callback=initMap&v=weekly"
     async>
	</script>
             
        <footer>
            <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
            <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
            <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
            &copy; 2021 City of Orlando | 
            Powered by <a href="http://www.opencities.com/">OpenCities</a>            
        </footer>
    </body>
</html>