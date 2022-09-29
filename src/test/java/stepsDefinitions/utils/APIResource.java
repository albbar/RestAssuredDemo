package stepsDefinitions.utils;

public enum APIResource {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    AddOperation("/calculator.asmx");

    private String resource;
    private APIResource(String resource) {
        this.resource = resource;
    }

    public String getResource(){
        return this.resource;
    }
}
