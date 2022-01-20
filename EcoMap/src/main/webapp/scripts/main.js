$(document).ready(function()
{
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
