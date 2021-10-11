/**
* Defines icon types and locations for use on the EcoMap.
* @author Cason Fluharty
* @version 1.0
* @created October 8, 2021
* @edited October 11, 2021
*/

let map;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: new google.maps.LatLng(28.538336, -81.379234),
        zoom: 10,
    });

    const iconBase = 
        "EcoMap/src/main/webapp/icons/";
    const icons = {
        cleanEnergy: {
            icon: iconBase + "GreenWorksIcons_Individual_CleanEnergy.png",
        },
        greenBuildings: {
            icon: iconBase + "GreenWorksIcons_Individual_GreenBuildings.png",
        },
        livability: {
            icon: iconBase + "GreenWorksIcons_Individual_Livability.png",
        },
        localFoodSystems: {
            icon: iconBase + "GreenWorksIcons_Individual_LocalFoodSystems.png",
        },
        solidWaste: {
            icon: iconBase + "GreenWorksIcons_Individual_SolidWaste.png",
        },
        transportation: {
            icon: iconBase + "GreenWorksIcons_Individual_Transportation.png",
        },
        water: {
            icon: iconBase + "GreenWorksIcons_Individual_Water.png",
        }
    };

    /* Features const is an array (use [] to define it), but we should investigate*
     * writing a script that makes its length and the marker positions modifiable.*/
    const features = [
        {
            //!!This is a test value. Remove for official use!!//
            position: new google.maps.LatLng(28.538341, -81.379239),
            type: "water",
        },
        {
            //!!This is a test value. Remove for official use!!//
            position: new google.maps.LatLng(28.538331, -81.379229),
            type: "solidWaste",
        },
        ];

    //Create markers and populate map
    for (let i = 0; i < features.length; i++) {
        const marker = new google.maps.Marker({
            position: features[i].position,
            icon: icons[features[i].type].icon,
            map: map,
        });
    }
}