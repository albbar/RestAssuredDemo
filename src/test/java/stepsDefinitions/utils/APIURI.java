package stepsDefinitions.utils;

public enum APIURI {
    googleMapAPI("https://rahulshettyacademy.com"),
    calculatorAPI("http://www.dneonline.com/");

    private String uri;

    private APIURI(String uri){ this.uri = uri;}

    public String getURI(){
        return this.uri;
    }
}
