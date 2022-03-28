<%@ page import="com.GREENWORKS.eco.data.*" %>
<%@page import="java.util.List" %>
<%@ page isELIgnored="false"%>
<%
// Get pin data
Data data = new Data();
%>

<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Eco-Map (Admin Panel) - City of Orlando</title>
        <!-- Stylesheets -->
        <link href="stylesheets/ecomap_stylesheet.css" rel="stylesheet" />
        <link href="stylesheets/admin-login.css" rel="stylesheet" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <!-- JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <!-- Form Validation -->
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
        <!-- Time Addon -->
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
            <%
            if((String)session.getAttribute("username") != "" && (String)session.getAttribute("username") != null)
            {
            %>
                <p>
                    <fieldset>
                        <legend>Admin Panel</legend>

                        <div class="item-container">
                            Welcome, ${username}.
                        </div>

                        <div class="item-container">
                            <a href="#add_location_box" class="button">Add Location</a> <a href="#edit_location_box" class="button">Edit Location</a> <a href="logout" class="button">Logout</a>
                        </div>
                    </fieldset>
                </p>

                <p id="add_location_box">
                    <fieldset>
                    <legend>Add Location</legend>

                    <form action="additem" method="POST" id="addItem">
                        <input type="hidden" name="coord" />

                        <div class="input-container">
                            Location Name: <input type="text" name="locationName" />
                        </div>

                        <div class="input-container">
                            Location Address: <input type="text" name="location" />
                        </div>

                        <div class="input-container">
                            Thumbnail: <input type="text" name="thumbnail" />
                        </div>

                        <div class="input-container">
                            Website Link: <input type="text" name="link" />
                        </div>

                        <div class="input-container">
                            Is this an event: <input type="radio" name="event" value="1" /> Yes / <input type="radio" name="event" value="0" CHECKED /> No
                        </div>

                        <span id="eventDates" style="display: none;">
                            <div class="input-container">
                                Date / Time Start: <input type="text" id="dateStart" name="dateStart" readonly="true" />
                            </div>

                            <div class="input-container">
                                Date / Time End: <input type="text" id="dateEnd" name="dateEnd" readonly="true" />
                            </div>
                        </span>

                        <div class="input-container">
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
                        </div>

                        <div class="input-container">
                            Subpillar:
                            <br />
                            <select name="subpillar">
                                <option value="0" SELECTED>None</option>

                                <%
                                // Loop through array
                                for (SubPillar subPillar : data.getSubPillarList())
                                {
                                %>

                                <option value="<%=subPillar.getSubPillarId()%>"><%=subPillar.getName()%></option>

                                <%
                                }
                                %>
                            </select>
                        </div>

                        <div class="input-container">
                            Content:
                            <br />
                            <textarea width="75%" rows="10" name="content"></textarea>
                        </div>

                        <div class="button-container">
                            <input type="submit" value="Add!" name="submit" />
                        </div>
                    </form>
                    </fieldset>
                </p>

                <%
                // Check if second edit screen
                if(request.getAttribute("id") != "" && request.getAttribute("id") != null)
                {
                %>
                    <p id="edit_location_box2">
                        <fieldset>
                            <legend>Edit Location: Editing ${name}</legend>

                            <form action="editlocationsave" method="POST" id="editLocation">
                                <input type="hidden" name="id" value="${id}" />
                                <input type="hidden" name="coord" value="${coord}" />

                                <div class="input-container">
                                    Location Name: <input type="text" name="locationName" value="${name}" />
                                </div>

                                <div class="input-container">
                                    Location Address: <input type="text" name="location" value="${address}" />
                                </div>

                                <div class="input-container">
                                    Thumbnail: <input type="text" name="thumbnail" value="${thumbnail}" />
                                </div>

                                <div class="input-container">
                                    Website Link: <input type="text" name="link" value="${link}" />
                                </div>

                                <div class="input-container">
                                    Is this an event: <input type="radio" name="eventEdit" value="1" ${dateStart == null ? '' : 'CHECKED'} /> Yes / <input type="radio" name="eventEdit" value="0" ${dateStart == null ? 'CHECKED' : ''} /> No
                                </div>

                                <span id="eventDatesEdit" ${dateStart == null ? 'style="display:none;"' : ''}>
                                    <div class="input-container">
                                        Date / Time Start: <input type="text" id="dateStartEdit" name="dateStartEdit" readonly="true" value="${dateStart}" />
                                    </div>

                                    <div class="input-container">
                                        Date / Time End: <input type="text" id="dateEndEdit" name="dateEndEdit" readonly="true" value="${dateEnd}" />
                                    </div>
                                </span>

                                <div class="input-container">
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
                                </div>

                                <div class="input-container">
                                    Subpillar:
                                    <br />
                                    <select name="subpillar">
                                        <option value="0" SELECTED>None</option>
        
                                        <%
                                        // Loop through array
                                        for (SubPillar subPillar : data.getSubPillarList())
                                        {
                                        %>
        
                                        <option value="<%=subPillar.getSubPillarId()%>" ${request.getAttribute("subpillar") == subPillar.getSubPillarId() ? 'selected' : ''}><%=subPillar.getName()%></option>
                                        
                                        <%
                                        }
                                        %>
                                    </select>
                                </div>

                                <div class="input-container">
                                    Content:
                                    <br />
                                    <textarea width="75%" rows="10" name="content">${content}</textarea>
                                </div>

                                <div class="button-container">
                                    <input type="submit" value="Edit!" name="submit" id="editLocationSubmit" /> 
                                    <input type="submit" value="Cancel" onclick="window.open('admin.jsp');" />
                                </div>
                            </form>
                        </fieldset>
                    </p>
                <%
                }
                else
                {
                %>
                    <p id="edit_location_box">
                        <fieldset>
                            <legend>Edit Location</legend>

                            <form action="editlocation#edit_location_box2" method="POST">
                                <div class="input-container"></div>
                                    <select name="locationID">
                                        <%
                                        // Loop through array
                                        for (Pin location : data.getPinList())
                                        {
                                        %>
                                            <option value="<%=location.getId()%>"><%=location.getLocationName()%> - <%=location.getLocationAddress()%></option>
                                        <%
                                        }
                                        %>
                                    </select>
                                </div>

                                <div class="button-container">
                                    <input type="submit" name="editChoose" value="Edit" /> 
                                    <input type="submit" name="deleteChoose" value="Delete" />
                                </div>
                            </form>
                        </fieldset>
                    </p>
                    <p id="clean_database_box">
                        <fieldset>
                            <legend>Clean Database</legend>

                            <form action="cleandatabase" method="POST">
                                <div class="button-container">
                                    <input type="submit" name="cleanDatabase" value="Clean Database" /><p>This button will remove old events and redudant/problem entries from the database. Old events will be moved to the old_events table and redudant/problem entries will be moved to the problem_locations table.</p>
                                </div>
                            </form>
                        </fieldset>
                    </p>
                <%
                }
            }
            else
            {
            %>
            <div id="admin-login-container">

                <form action="login" method="POST">
                    <div class="input-container">
                        <label for="username">Username:</label>
                        <input type="text" name="username" />
                    </div>
                    <div class="input-container">
                        <label for="password">Password:</label>
                        <input type="password" name="password" />
                    </div>
                    <div class="button-container">
                        <input class="form-submit-btn" type="submit" name="submit" value="Login" />
                    </div>

                    ${message}
                </form>
            </div>
            <%
            }
            %>
        </section>
        
        <!-- JQuery Code -->
        <script src="scripts/main.js"></script>
        <script src="scripts/form-validation.js"></script>
    </body>
</html>