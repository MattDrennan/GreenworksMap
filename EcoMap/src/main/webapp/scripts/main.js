$(document).ready(function()
{
    // If window is resized
    $(window).resize(function()
    {
        // Prevent mobile responsive glitch
        if($(window).width() >= 768)
        {
            $("#filter").hide();
        }
    });

    // Filter - Options Pressed
    $(".options").on("click", function(event)
    {
        $(this).find("img").toggleClass("grayscale");

        // Loop through all markers on map
        for(i = 0; i <= globalMarkers.length - 1; i++)
        {
            // Set up should hide variable
            var shouldHide = false;

            // Loop through all options
            $(".options").each(function()
            {
                // If option is checked
                if($(this).find("img").hasClass("grayscale"))
                {
                    // If the option value matches the marker type
                    if($(this).attr("value") == globalMarkers[i].attr.dbType)
                    {
                        // Don't hide
                        shouldHide = true;
                    }
                }
            });

            // If should hide is true
            if(shouldHide)
            {
                // Hide
                globalMarkers[i].visible = false;
            }
            else
            {
                // Show
                globalMarkers[i].visible = true;
            }
        }
    });

    // List View - Filter Events
    $("#showEvents").on("click", function(event)
    {
        $("#listViewMessage").text("");
        $("[name=eventList]").show();
        $("[name=locationList]").hide();

        // Show message if none
        if($("[name=eventList]").length == 0)
        {
            $("#listViewMessage").text("Nothing to show.");
        }
    });

    // List View - Filter Locations
    $("#showLocations").on("click", function(event)
    {
        $("#listViewMessage").text("");
        $("[name=locationList]").show();
        $("[name=eventList]").hide();

        // Show message if none
        if($("[name=locationList]").length == 0)
        {
            $("#listViewMessage").text("Nothing to show.");
        }
    });

    // List View - Filter All
    $("#showAll").on("click", function(event)
    {
        $("#listViewMessage").text("");
        $("[name=eventList]").show();
        $("[name=locationList]").show();

        // Show message if none
        if($("[name=locationList]").length == 0 && $("[name=eventList]").length == 0)
        {
            $("#listViewMessage").text("Nothing to show.");
        }
    });

    // Change View Button
    $("#viewChange").on("click", function(event)
    {
        // If hidden
        if($("#listView").is(":hidden"))
        {
            // Show
            $("#listView").show();
            $("#mapView").hide();
            $("#viewChange").text("[Map View]");

            // Show message if none
            if($("[name=locationList]").length == 0 && $("[name=eventList]").length == 0)
            {
                $("#listViewMessage").text("Nothing to show.");
            }
        }
        else
        {
            // Hide
            $("#listView").hide();
            $("#mapView").show();
            $("#viewChange").text("[List View]");
        }
    });

    // Add Item - Form Submit
    $("#addItem input[type=submit]").on("click", function(event)
    {
        event.preventDefault();

        // Get coords for address
        var addressInfo = getAddress($("#addItem input[name=location]").val()).done(function(returndata)
        {
            // Get coord data and put in hidden field
            $("input[name=coord]").val(returndata.x + "," + returndata.y);

            // Send form data
            $.ajax({   
                type: "POST",
                data : $("#addItem").serialize(),
                url: "additem",   
                success: function(data)
                {
                    window.location.replace("admin.jsp");                
                }   
            });
        });
    });

    // Edit Item - Form Submit
    $("#editLocationSubmit").on("click", function(event)
    {
        event.preventDefault();

        // Get coords for address
        var addressInfo = getAddress($("#editLocation input[name=location]").val()).done(function(returndata)
        {
            // Get coord data and put in hidden field
            $("input[name=coord]").val(returndata.x + "," + returndata.y);

            // Send form data
            $.ajax({   
                type: "POST",
                data : $("#editLocation").serialize(),
                url: "editlocationsave",   
                success: function(data)
                {
                    window.location.replace("admin.jsp");               
                }   
            });
        });
    });

    // If date picker (first option) is changed
    $("#datepicker").on("change", function()
    {
        // Loop through all markers on map
        for(i = 0; i <= globalMarkers.length - 1; i++)
        {
            // Set up should hide variable
            var shouldHide = true;

            // If the option value matches the marker type
            // Allow other locations to stay on map
            if($(this).val() == globalMarkers[i].attr.dateStart.split(" ")[0] || globalMarkers[i].attr.dateStart == "null")
            {
                // Don't hide
                shouldHide = false;
            }

            // If should hide is true
            if(shouldHide)
            {
                // Hide
                globalMarkers[i].visible = false;
            }
            else
            {
                // Show
                globalMarkers[i].visible = true;
            }
        }
    });

    // If date picker (first option) is changed
    $("#dateStart, #dateStartEdit").on("change", function()
    {
        // Only allow one day option
        $("#dateEnd, #dateEndEdit").datetimepicker("option", "minDate", $(this).val());
        $("#dateEnd, #dateEndEdit").datetimepicker("option", "maxDate", $(this).val());
    });

    // If edit an event is pressed
    $("input[name=eventEdit]").click(function()
    {
        // If no
        if($("input[name=eventEdit]:checked").val() == 0)
        {
            // Hide
            $("#eventDatesEdit").hide();

            // Set null fields
            $("#dateStartEdit").val("");
            $("#dateEndEdit").val("");
        }
        else
        {
            // Show
            $("#eventDatesEdit").show();
        }
    });

    // If add an event is pressed
    $("input[name=event]").click(function()
    {
        // If no
        if($("input[name=event]:checked").val() == 0)
        {
            // Hide
            $("#eventDates").hide();

            // Set null fields
            $("#dateStart").val("");
            $("#dateEnd").val("");
        }
        else
        {
            // Show
            $("#eventDates").show();
        }
    });

    // Click events link
    $("[name=openCalendar]").click(function()
    {
        // If mobile
        if($(this).attr("type") == "mobile")
        {
            // Move datepicker near filter button
            $("#datepicker").css({left: 90, top: 120, position: 'relative'});
        }
        else
        {
            // Move datepicker near filter button
            $("#datepicker").css({right: 0, bottom: 40, position: 'absolute'});
        }

        $("#datepicker").datepicker("show");
    });

    // Click filter menu button
    $("[name=openFilter],[name=closeFilter]").click(function(e)
    {
        // Prevent default action
        e.preventDefault();

        // If visible
        if($("#filter").is(":visible"))
        {
            // Hide
            $("#filter").hide();
        }
        else
        {
            // Show
            $("#filter").show();
        }
    });

    // getAddress: Get's JSON data from address
    function getAddress(address)
    {
        // Set up return variables
        var x = 0;
        var y = 0;
        var def = $.Deferred();

        // Get JSON from URL (ARCGIS)
        $.getJSON('https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/findAddressCandidates?singleLine=' + address + '&forStorage=false&f=pjson').done(function(data)
        {
            // Loop through JSON data
            $.each(data, function(i, field)
            {
                // There are two sets of data, candidates contains the x and y variable
                if(i == "candidates")
                {
                    def.resolve({
                        // Get X and Y data
                        x: field[0]['location']['x'],
                        y: field[0]['location']['y']
                    });
                }
            });
        });

        return def;
    }
});