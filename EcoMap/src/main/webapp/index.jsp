<%@ page import="com.GREENWORKS.eco.data.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page isELIgnored="false"%>
<%
// Get pin data
SessionAssistant sa = new SessionAssistant();
ArrayList<Pin> locationsArrayList = sa.getAllPins();
%>

<html>
    <head>
        <!--
            Filename: index.jsp
            Date of Creation: 27 Sep 2021
            Editor: Kashai Bingham
            Edited: 29 Nov 2021
            Version: 2.5.5
        -->
        
        <meta charset="utf-8" />
        <title>Eco-Map - City of Orlando</title>
        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet" />
        <!-- JQUERY -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <!-- ARCGIS -->
        <link rel="stylesheet" href="https://js.arcgis.com/4.22/esri/themes/light/main.css">
        <script src="https://js.arcgis.com/4.22/"></script>
        <script src="scripts/esri_api.js"></script>
        <script src="scripts/jquery.grayscale.js"></script>
        <!-- CRED FILE -->
        <script src="cred.js"></script>
        
        <script>
            // Dates to be highlighted
            var eventDates = {};
            <%
            // Loop through array
            for (Pin location : locationsArrayList)
            {
                // Is an event - check to make sure dates are not set to null
                if(location.getStartDate() != null && location.getEndDate() != null)
                {
            %>

                     eventDates[new Date("<%=location.getStartDate()%>")] = new Date("<%=location.getEndDate()%>");
            <%
                }
            }
            %>
        </script>

        <script>
        $(function()
        {
            $("#datepicker").datepicker({
                beforeShowDay: function(d)
                {
                    // Get day
                    var highlight = eventDates[d];

                    // Do we need to highlight day
                    if(highlight)
                    {
                        return [true, "calendar-highlight", "Tooltip Text Here"];
                    }
                    else
                    {
                        return [true, "", ""];
                    }
                },
                onSelect: function(d, c)
                {
                    $(this).val(d);
                    $(this).change();
                    c.inline = true;
                },
                onClose: function(d, c)
                {
                    // Loop through all markers on map
                    for(i = 0; i <= globalMarkers.length - 1; i++)
                    {
                        c.inline = false;
                        
                        // Show
                        globalMarkers[i].visible = true;
                    }
                },
                dateFormat: "yy-mm-dd",
            });
        });
        </script>
    </head>

    <body>
        <script>
            // Create set up variables
            var points = [];

            <%
            // Set up point object count
            int j = 0;

            // Loop through array
            for (Pin location : locationsArrayList)
            {
            %>
                points[<%=j%>] = {
                    index: <%=j%>,
                    type: "point",
                    longitude: "<%=location.getCoordinates().split(",")[0]%>",
                    latitude: "<%=location.getCoordinates().split(",")[1]%>",
                    dbID: "<%=location.getId()%>",
                    dbType: "<%=location.getIconId()%>",
                    dbAddress: "<%=location.getLocationAddress()%>",
                    name: "<%=location.getLocationName()%>",
                    dateStart: "<%=location.getStartDate()%>",
                    dateEnd: "<%=location.getEndDate()%>",
                    content: "<%=location.getThumbnailHTML()%><%=location.getLocationAddress()%><br /><br /><%=location.getContent()%><%=location.getLinkHTML()%>",
                    thumbnail: "<%=location.getThumbnail()%>",
                    link: "<%=location.getLink()%>"
                };
            <%
                // Increment object count
                j++;
            }
            %>
        </script>

        <!-- Show List Button -->
        <a href="#/" id="viewChange">[List View]</a>

        <div id="mapView">
            <section>
                <h1>Eco-Map</h1>
                <div class="map_options">
                    <div id="filter_items">
                        <figure>
                            <a href="#/" name="openFilter">
                                <img id="filter_icon" src="icons/filter.png" alt=" ">
                                <div id="fc_background"><figcaption>Filter</figcaption></div>
                            </a>
                        </figure>
                    </div>
                    <!--solution found on StackOverflow: https://stackoverflow.com/questions/5003867/how-to-call-javascript-function-instead-of-href-in-html/5003904-->

                    <input type="text" id="datepicker" style="visibility:hidden; margin-left: 100px;">
                    <div id="calendar_items">
                        <figure>
                            <a href="#/" id="openCalendar">
                                <img id="calendar_icon" src="icons/calendar.png" alt=" ">
                                <div id="fc_background"><figcaption>Events</figcaption></div>
                            </a>
                        </figure>
                    </div>
                </div>
            </section>

            <div id="filter">
                <div id="toolTip"> 
                    <p>
                        <span class="pillars">
                            <span class="options" name="filterEnergy" value="1">
                                <img src="icons/energy.png" width="64px" height="64px" /> <label class="label">Clean Energy</label>
                            </span>
                            <span class="options" name="filterWater" value="2">
                                <img src="icons/water.png" width="64px" height="64px" /> <label class="label">Clean Water</label>
                            </span>
                            <span class="options" name="filterTransportation" value="3">
                                <img src="icons/transportation.png" width="64px" height="64px" /> <label class="label">Electic & Alt. Transportation</label>
                            </span>
                            <span class="options" name="filterBuildings" value="4">
                                <img src="icons/buildings.png" width="64px" height="64px" /> <label class="label">Green Buildings</label>
                            </span>
                            <span class="options" name="filterLivability" value="5">
                                <img src="icons/livability.png" width="64px" height="64px" /> <label class="label">Livability</label>
                            </span>
                            <span class="options" name="filterFood" value="6">
                                <img src="icons/food.png" width="64px" height="64px" /> <label class="label">Local Food Systems</label>
                            </span>
                            <span class="options" name="filterWaste" value="7">
                                <img src="icons/waste.png" width="64px" height="64px" /> <label class="label">Zero Waste</label>
                            </span>
                        </span>
                    </p>
                    <div id="tailShadow"></div> 
                    <div id="tail1"></div> 
                    <div id="tail2"></div> 
                </div> 
            </div>
    
            <!-- Map -->
            <div id="viewDiv"></div>
        </div> 

        <!-- List View (Hidden by default) -->
        <div id="listView" style="display: none; text-align: center;">
            <a href="#/" id="showAll">[All]</a> <a href="#/" id="showEvents">[Show Events]</a>    <a href="#/" id="showLocations">[Show Locations]</a>
            <script>
                $.each(points, function(i, index)
                {
                    if(index['dateStart'] != null && index['dateStart'] != null && index['dateStart'] != "null" && index['dateStart'] != "null")
                    {
                        // Convert dates
                        var dateStart = index['dateStart'];
                        var dateEnd = index['dateEnd'];

                        var momentStart = moment(dateStart);
                        var momentEnd = moment(dateEnd);

                        momentStart = momentStart.format("MMMM Do YYYY, h:mm a");
                        momentEnd = momentEnd.format("MMMM Do YYYY, h:mm a");

                        document.write('<div name="eventList" id="list_' + index['index'] + '"><img src="' + iconSelect(index['dbType'])['url'] + '" width="32px" height="32px" />' + index['name'] + '<br />' + momentStart + ' - ' + momentEnd + '<br />' + index['content'] + '<br /><br /></div>');
                    }
                    else
                    {
                        document.write('<div name="locationList" id="list_' + index['index'] + '"><img src="' + iconSelect(index['dbType'])['url'] + '" width="32px" height="32px" />' + index['name'] + '<br />' + index['content'] + '<br /><br /></div>');
                    }
                });
            </script>
            <div id="listViewMessage"></div>
        </div>
        
        <!-- JQuery Code -->
        <script src="scripts/main.js"></script>
    </body>
</html>