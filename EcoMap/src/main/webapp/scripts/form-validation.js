$(function() {
    // Form: Add Item
    $("#addItem").validate({
        rules: {
            locationName: "required",
            location: "required",
            event: {
                required: true,
                range: [0, 1]
            },
            icon: {
                required: true,
                range: [1, 7]
            },
            dateStart: {
                required: $("[name=event]").val() == 1
            },
            dateEnd: {
                required: $("[name=event]").val() == 1
            }
        },
        messages: {
            locationName: "Please enter a location name.",
            location: "Please enter a location address.",
            event: "Please confirm if this is an event.",
            icon: "Please choose a pillar.",
            dateStart: "Please choose a start date for the event.",
            dateEnd: "Please choose a end date for the event."
        },
        submitHandler: function(form) {
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
        }
    });

    // Form: Edit Location
    $("#editLocation").validate({
        rules: {
            locationName: "required",
            location: "required",
            eventEdit: {
                required: true,
                range: [0, 1]
            },
            icon: {
                required: true,
                range: [0, 7]
            },
            dateStartEdit: {
                required: $("[name=eventEdit]").val() == 1
            },
            dateEndEdit: {
                required: $("[name=eventEdit]").val() == 1
            }
        },
        messages: {
            locationName: "Please enter a location name.",
            location: "Please enter a location address.",
            event: "Please confirm if this is an event.",
            icon: "Please choose an icon.",
            dateStart: "Please choose a start date for the event.",
            dateEnd: "Please choose a end date for the event."
        },
        submitHandler: function(form) {
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
                    try {
                        def.resolve({
                            // Get X and Y data
                            x: field[0]['location']['x'],
                            y: field[0]['location']['y']
                        });
                    } catch {
                        alert("Invalid location address.");
                    }
                }
            });
        });

        return def;
    }
});