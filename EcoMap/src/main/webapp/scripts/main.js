$(document).ready(function()
{
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
            if($(this).val() == globalMarkers[i].dateStart || globalMarkers[i].dateStart == "null")
            {
                // Don't hide
                shouldHide = false;
            }

            // If should hide is true
            if(shouldHide)
            {
                // Hide
                globalMarkers[i].setVisible(false);
            }
            else
            {
                // Show
                globalMarkers[i].setVisible(true);
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
    $("#openCalendar").click(function()
    {
        $("#datepicker").datepicker("show");
    });

    // If a filter option is pressed
    $("[class=check]").click(function()
    {
        // Loop through all markers on map
        for(i = 0; i <= globalMarkers.length - 1; i++)
        {
            // Set up should hide variable
            var shouldHide = true;

            // Loop through all options
            $("[class=check]").each(function()
            {
                // If option is checked
                if($(this).is(':checked'))
                {
                    // If the option value matches the marker type
                    if($(this).val() == globalMarkers[i].type)
                    {
                        // Don't hide
                        shouldHide = false;
                    }
                }
            });

            // If should hide is true
            if(shouldHide)
            {
                // Hide
                globalMarkers[i].setVisible(false);
            }
            else
            {
                // Show
                globalMarkers[i].setVisible(true);
            }
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
});
