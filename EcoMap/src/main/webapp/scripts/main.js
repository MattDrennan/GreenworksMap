$(document).ready(function()
{
    // qTip
    $('[title!=""]').qtip({
        position: {
            my: 'bottom center',
            at: 'top center',
            adjust : {
                x: 0,
                y: -55
            }
        }
    });

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

    // Filter - Options Pressed
    $("[name=subPillarOption]").on("click", function(event)
    {
        // Loop through all markers on map
        for(i = 0; i <= globalMarkers.length - 1; i++)
        {
            // Set up should hide variable
            var shouldHide = false;

            // Loop through all options
            $("[name=subPillarOption]").each(function()
            {
                // If the option value matches the marker type
                if($(this).attr("value") == globalMarkers[i].attr.subpillar && !$(this).is(":checked"))
                {
                    // Don't hide
                    shouldHide = true;
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
    $("[name=viewChange]").on("click", function(event)
    {
        // If hidden
        if($("#listView").is(":hidden"))
        {
            // Show
            $("#listView").show();
            $("#mapView").hide();

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
        }
    });

    // Advanced Button
    $("[name=advancedFilter]").on("click", function(e)
    {
        // Prevent default action
        e.preventDefault();

        // If visible
        if($("#advancedFilter").is(":visible"))
        {
            // Hide
            $("#advancedFilter").hide();
        }
        else
        {
            // Show
            $("#advancedFilter").show();
        }
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
            $("#datepicker").css({right: 0, bottom: 70, position: 'absolute'});
        }

        // If calendar is not visible
        if(!$("#datepicker").datepicker("widget").is(":visible"))
        {
            $("#datepicker").datepicker("show");
        }
        else
        {
            $("#datepicker").datepicker("hide");
        }
    });

    // If click on map
    $("#viewDiv").click(function(e)
    {
        // If filter visible, hide it
        if($("#filter").is(":visible"))
        {
            // Hide
            $("#filter").hide();
        }

        // If advanced filter visible, hide it
        if($("#advancedFilter").is(":visible"))
        {
            // Hide
            $("#advancedFilter").hide();
        }
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

    // Removes subpillars where pillar does not match
    function subpillar_load_list(changed)
    {
        // Get the action
        if(changed == "add")
        {
            var pillarid = $("div[name=addicon] select[name=icon]").val();
            var goto = "div[name=addsub] ";
        }
        else
        {
            var pillarid = $("div[name=editicon] select[name=icon]").val();
            var goto = "div[name=editsub] ";
        }

        // Loop through options and hide / show based on action
        $(goto + "select[name=subpillar] option").each(function(index) {
            if($(this).attr("pillar") != pillarid && $(this).attr("pillar") != "NULL")
            {
                $(this).hide();
            }
            else
            {
                $(this).show();
            }
        });

        // If edit, and changed, set None to selected
        if(changed == "edit")
        {
            $(goto + "select[name=subpillar]").val(0);
        }
    }

    // Load on page load
    subpillar_load_list();

    // Admin - Change Pillar - Limit Results
    $("[name=icon]").on("change", function()
    {
        subpillar_load_list($(this).attr("action"));
    });


});