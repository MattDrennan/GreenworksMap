<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
    <head>
        <!--
            Filename: OrlandoEcoMap.com
            Date of Creation: 27 Sep 2021
            Editor: Kashai Bingham
            Edited: 29 Sep 2021
        -->
        
        <meta charset="utf-8" />
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
            <div>
                <article>
                    <iframe
                        width="600"
                        height="450"
                        style="border:0"
                        loading="lazy"
                        allowfullscreen
                        src="https://www.google.com/maps/embed/v1/view?key=ab09a04f93fd18d7&center=28.5384,8133789&zoom=8">
                    </iframe>
                </article>
            </div>
        </section>

        <div id="bottom">        
            <footer>
                <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
                <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
                <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
                &copy; 2021 City of Orlando | 
                Powered by <a href="http://www.opencities.com/">OpenCities</a>            
            </footer>
        </div>
    </body>
</html>