<%@ page import="com.GREENWORKS.eco.EcoMap" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <!--
            Filename: admin.jsp
            Date of Creation: 18 Jan 2022
            Editor: Matthew Drennan
            Edited: 18 Jan 2022
            Version: 2.5.5
        -->
        
        <meta charset="utf-8" />
        <title>Eco-Map (Admin Panel) - City of Orlando</title>
        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <%
        EcoMap e = new EcoMap();
        %>

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
                    <li>Eco-Map</li>
                </ul>
            </div>
            <!-- adds a static breadcrumb navigation to show the user the URL path taken to the current page -->
            <h1>Admin Panel</h1>
        </section>
        
        <section>
            <%
            if((String)session.getAttribute("username") != "" && (String)session.getAttribute("username") != null)
            {
            %>
                <p>
                    Welcome, ${username}.
                </p>

                <p>
                    <form action="additem" method="POST">
                        Location Name: <input type="text" name="locationName" />
                        <br />
                        Location Address: <input type="text" name="location" />
                        <br />
                        Zip Code: <input type="number" name="zip" />
                        <br />
                        Icon:
                        <br />
                        <select name="icon">
                            <option value="6">Food</option>
                            <option value="5">Livability</option>
                            <option value="7">Waste</option>
                            <option value="2">Water</option>
                            <option value="1">Energy</option>
                            <option value="4">Buildings</option>
                            <option value="3">Transportation</option>
                        </select>
                        <br />
                        <input type="submit" value="Add!" name="submit" />
                    </form>
                </p>

                <p>
                    <a href="logout">Logout</a>
                </p>
            <%
            }
            else
            {
            %>
                <form action="login" method="POST">
                    Username: <input type="text" name="username" />
                    <br />
                    Password: <input type="text" name="password" />
                    <br />
                    <input type="submit" name="submit" value="Login" />
                </form>
            <%
            }
            %>   
        </section>
        
        <div>
            <footer>
                <div id="left">
                  <a href="https://www.orlando.gov/System-pages/Website-Legal-Notices">Website Legal Notice</a>  | 
                  <a href="https://www.orlando.gov/General-Pages/Sitemap">Sitemap</a> | 
                  <a href="https://www.orlando.gov/Our-Government/News-and-Information/City-Official-Assets">The City Beautiful</a>
                </div>
                <div id="right">
                 &copy; 2021 City of Orlando | 
                Powered by <a href="http://www.opencities.com/">OpenCities</a>
                </div>                           
              </footer>
        </div>
        <!-- simulates the footer as shown on the real CoO site -->
    </body>
</html>