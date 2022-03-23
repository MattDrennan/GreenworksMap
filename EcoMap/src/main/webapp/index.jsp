<%@ page import="com.GREENWORKS.eco.data.*" %>
<%@page import="java.util.List" %>
<%@ page isELIgnored="false"%>
<%
// Get pin data
SessionAssistant sa = new SessionAssistant();
Data data = new Data(); 
%>

<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="initial-scale=1.0, user-scalable=1">
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
        <!-- CRED FILE -->
        <script src="cred.js"></script>
        
        <script>
            // Dates to be highlighted
            var eventDates = {};
            <%
            // Loop through array
            for (Pin location : data.getPinList())
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
            for (Pin location : data.getPinList())
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

        <div id="mapView">
            <section>
                <!-- Calendar Field -->
                <input type="text" id="datepicker" style="position: absolute; bottom: 0; visibility: hidden;">

                <div class="map_options">
                    <div id="filter_items">
                        <figure>
                            <a href="#/" name="openFilter">
                                <img id="filter_icon" src="icons/filter.png" alt=" ">
                                <div id="fc_background"><figcaption>Filter</figcaption></div>
                            </a>
                        </figure>
                    </div>

                    <div id="calendar_items">
                        <figure>
                            <a href="#/" name="openCalendar" type="mobile">
                                <img id="calendar_icon" src="icons/calendar.png" alt=" ">
                                <div id="fc_background"><figcaption>Events</figcaption></div>
                            </a>
                        </figure>
                    </div>

                    <div id="list_view_items">
                        <figure>
                            <a href="#/" name="viewChange">
                                <img src="icons/list.png" alt=" ">
                                <div id="fc_background"><figcaption>List</figcaption></div>
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

            <div id="filterDesktop">
                <span class="pillars">
                    <span class="options" id="filterEnergy" value="1">
                        <img src="icons/energy.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterWater" value="2">
                        <img src="icons/water.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterTransportation" value="3">
                        <img src="icons/transportation.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterBuildings" value="4">
                        <img src="icons/buildings.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterLivability" value="5">
                        <img src="icons/livability.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterFood" value="6">
                        <img src="icons/food.png" width="64px" height="64px" />
                    </span>
                    <span class="options" id="filterWaste" value="7">
                        <img src="icons/waste.png" width="64px" height="64px" />
                    </span>
                </span>
            </div>

            <span id="itemsDesktop">
                <figure>
                    <a href="#/" name="viewChange">
                        <img src="icons/list.png" alt=" ">
                        <figcaption>List</figcaption>
                    </a>
                </figure>

                <figure>
                    <a href="#/" name="openCalendar" type="desktop">
                        <img id="calendar_icon" src="icons/calendar.png" alt=" ">
                    <figcaption>Events</figcaption>
                    </a>
                </figure>
            </span>
            
            <!-- Map -->
            <div id="viewDiv"></div>
        </div> 

        <!-- List View (Hidden by default) -->
        <div id="listView" style="display: none; text-align: center;">
            <div class="viewChange">
                <a href="#/" name="viewChange" class="button">Map View</a> <a href="#/" id="showAll" class="button">All</a> <a href="#/" id="showEvents" class="button">Show Events</a>    <a href="#/" id="showLocations" class="button">Show Locations</a>
            </div>
            <div class="viewChangeSpace"></div>
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