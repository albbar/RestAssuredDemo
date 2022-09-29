package stepsDefinitions.utils;

import pojos.googleMapAPIPojo.googleMapApiAddPlace;
import pojos.googleMapAPIPojo.googleMapApiLocation;

import java.util.ArrayList;
import java.util.List;

import static stepsDefinitions.utils.Constants.PLACE_ID;

public class TestDataBuild {

    public googleMapApiAddPlace addPlacePayLoad(String accuracy, String name, String language, String address, String latLocation, String lngLocation, String phoneNumber, String website){

        /*
        Set the data on the googleMapApiAssPlace object
        */

        //Create a type list
        List<String> typesList = new ArrayList<String>();
        typesList.add("shoe park");
        typesList.add("shop");

        //create location
        latLocation = latLocation.replace(",", ".");
        lngLocation = lngLocation.replace(",", ".");
        googleMapApiLocation location = new googleMapApiLocation();
        location.setLat(Double.parseDouble(latLocation));
        location.setLng(Double.parseDouble(lngLocation));

        //Add place
        googleMapApiAddPlace addPlace = new googleMapApiAddPlace();
        addPlace.setLocation(location);
        addPlace.setAccuracy(Integer.parseInt(accuracy));
        addPlace.setName(name);
        addPlace.setPhone_number(phoneNumber);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setTypes(typesList);
        addPlace.setWebsite(website);

        return addPlace;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n    \""+PLACE_ID+"\":\""+placeId+"\"\r\\n}\r\n";
    }

}
