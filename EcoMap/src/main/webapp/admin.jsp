<%@ page import="com.GREENWORKS.eco.EcoMap" %>
<%@page import="java.util.ArrayList" %>
<%@ page isELIgnored="false"%>
<%
EcoMap e = new EcoMap();
%>

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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js" integrity="sha512-s5u/JBtkPg+Ff2WEr49/cJsod95UgLHbC00N/GglqdQuLnYhALncz8ZHiW/LxDRGduijLKzeYb7Aal9h3codZA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css" integrity="sha512-LT9fy1J8pE4Cy6ijbg96UkExgOjCqcxAC7xsnv+mLJxSvftGVmmc236jlPTZXPcBRQcVOWoK1IJhb1dAjtb4lQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script>
        $(function()
        {
            // Add event
            $("#dateStart").datetimepicker({
                dateFormat: "yy-mm-dd"
            });
            $("#dateEnd").datetimepicker({
                dateFormat: "yy-mm-dd"
            });

            // Edit event
            $("#dateStartEdit").datetimepicker({
                dateFormat: "yy-mm-dd"
            });
            $("#dateEndEdit").datetimepicker({
                dateFormat: "yy-mm-dd"
            });
        });
        </script>
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
                    <li><a href="/webapp/">Eco-Map</a></li>
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

                <h1>Add Location</h1>

                <p>
                    <form action="additem" method="POST">
                        Location Name: <input type="text" name="locationName" />
                        <br />
                        Location Address: <input type="text" name="location" />
                        <br />
                        Zip Code: <input type="number" name="zip" />
                        <br />
                        Is this an event: <input type="radio" name="event" value="1" /> Yes / <input type="radio" name="event" value="0" CHECKED /> No
                        <span id="eventDates" style="display: none;">
                            <br />
                            Date / Time Start: <input type="text" id="dateStart" name="dateStart" readonly="true" />
                            <br />
                            Date / Time End: <input type="text" id="dateEnd" name="dateEnd" readonly="true" />
                        </span>
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

                <h1>Edit Location</h1>

                <%
                // Check if second edit screen
                if(request.getAttribute("id") != "" && request.getAttribute("id") != null)
                {
                %>
                    <form action="editlocationsave" method="POST">
                        <input type="hidden" name="id" value="${id}" />
                        Location Name: <input type="text" name="locationName" value="${name}" />
                        <br />
                        Location Address: <input type="text" name="location" value="${address}" />
                        <br />
                        Zip Code: <input type="number" name="zip" value="${zip}" />
                        <br />
                        Is this an event: <input type="radio" name="eventEdit" value="1" ${dateStart == null ? '' : 'CHECKED'} /> Yes / <input type="radio" name="eventEdit" value="0" ${dateStart == null ? 'CHECKED' : ''} /> No
                        <span id="eventDatesEdit" ${dateStart == null ? 'style="display:none;"' : ''}>
                            <br />
                            Date / Time Start: <input type="text" id="dateStartEdit" name="dateStartEdit" readonly="true" value="${dateStart}" />
                            <br />
                            Date / Time End: <input type="text" id="dateEndEdit" name="dateEndEdit" readonly="true" value="${dateEnd}" />
                        </span>
                        <br />
                        Icon:
                        <br />
                        <select name="icon">
                            <option value="6" ${iconid == 6 || iconid == 10 ? 'selected' : ''}>Food</option>
                            <option value="5" ${iconid == 5 || iconid == 11 ? 'selected' : ''}>Livability</option>
                            <option value="7" ${iconid == 7 || iconid == 12 ? 'selected' : ''}>Waste</option>
                            <option value="2" ${iconid == 2 || iconid == 8 ? 'selected' : ''}>Water</option>
                            <option value="1" ${iconid == 1 || iconid == 9 ? 'selected' : ''}>Energy</option>
                            <option value="4" ${iconid == 4 || iconid == 9 ? 'selected' : ''}>Buildings</option>
                            <option value="3" ${iconid == 3 || iconid == 13 ? 'selected' : ''}>Transportation</option>
                        </select>
                        <br />
                        <input type="submit" value="Edit!" name="submit" />
                        <input type="submit" value="Cancel" onclick="window.open('admin.jsp');" />
                    </form>
                <%
                }
                else
                {
                %>
                    <p>
                        <form action="editlocation" method="POST">
                            <select name="locationID">
                                <%
                                // Set up variables
                                String id = "";
                                String iconid = "";
                                String address = "";
                                String name = "";
                                String zip = "";

                                // Increment variable
                                int j = 0;

                                // Loop through array
                                ArrayList<String> locations = (ArrayList<String>)  e.getLocations();
                                for (String location : locations)
                                {
                                    if(j == 0)
                                    {
                                        id = location;
                                    }
                                    else if(j == 1)
                                    {
                                        iconid = location;
                                    }
                                    else if(j == 2)
                                    {
                                        address = location;
                                    }
                                    else if(j == 3)
                                    {
                                        name = location;
                                    }
                                    else if(j == 4)
                                    {
                                        zip = location;
                                    }
                                    else if(j == 6)
                                    {
                                %>
                                        <option value="<%=id%>"><%=name%> - <%=address%></option>
                                <%
                                        // Reset
                                        j = -1;
                                    }

                                    // Increment
                                    j++;
                                }
                                %>
                            </select>
                            <br />
                            <input type="submit" name="editChoose" value="Edit" />
                            <input type="submit" name="deleteChoose" value="Delete" />
                        </form>
                    </p>
                <%
                }
                %>

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
                    Password: <input type="password" name="password" />
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
        <script src="scripts/main.js"></script>
    </body>
</html>