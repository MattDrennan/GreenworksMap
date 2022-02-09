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
        <section>
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
                    <form action="additem" method="POST" id="addItem">
                        <input type="hidden" name="coord" />
                        Location Name: <input type="text" name="locationName" />
                        <br />
                        Location Address: <input type="text" name="location" />
                        <br />
                        Thumbnail: <input type="text" name="thumbnail" />
                        <br />
                        Website Link: <input type="text" name="link" />
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
                        Content:
                        <br />
                        <textarea width="75%" rows="10" name="content"></textarea>
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
                    <form action="editlocationsave" method="POST" id="editLocation">
                        <input type="hidden" name="id" value="${id}" />
                        <input type="hidden" name="coord" value="${coord}" />
                        Location Name: <input type="text" name="locationName" value="${name}" />
                        <br />
                        Location Address: <input type="text" name="location" value="${address}" />
                        <br />
                        Thumbnail: <input type="text" name="thumbnail" value="${thumbnail}" />
                        <br />
                        Website Link: <input type="text" name="link" value="${link}" />
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
                        Content:
                        <br />
                        <textarea width="75%" rows="10" name="content">${content}</textarea>
                        <br />
                        <input type="submit" value="Edit!" name="submit" id="editLocationSubmit" />
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
                                // Loop through array
                                for (Pin location : locationsArrayList)
                                {
                                %>
                                    <option value="<%=location.getId()%>"><%=location.getLocationName()%> - <%=location.getLocationAddress()%></option>
                                <%
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
        
        <!-- JQuery Code -->
        <script src="scripts/main.js"></script>
    </body>
</html>