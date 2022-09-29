package others;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepsDefinitions.utils.APIURI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static stepsDefinitions.utils.Constants.KEY;
import static stepsDefinitions.utils.Constants.GOOGLE_MAP_API_KEY;

public class Helpers {

    private static RequestSpecification req;

    public static String extracField(String response, String field) {
        JsonPath js = new JsonPath(response); //for parsing Json
        String data = js.getString(field);
        return data;
    }

    public static String getJsonPath(Response response, String key) {
        String strResponse = response.asString();
        JsonPath js = new JsonPath(strResponse);
        return js.get(key).toString();
    }

    public static Integer extractIntData(Response response, String key) {
        String strResponse = response.asString();
        JsonPath js = new JsonPath(strResponse);
        return js.getInt(key);
    }

    public RequestSpecification requestSpecificationGoogleMapAPI(String uri) throws IOException {

        APIURI apiUri = APIURI.valueOf(uri);

        if (req == null){
            PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));
            req = new RequestSpecBuilder().setBaseUri(apiUri.getURI())
                    .addQueryParam(KEY, GOOGLE_MAP_API_KEY)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(JSON).build();
            return req;
        }
        return req ;
    }

    public RequestSpecification requestSpecificationCalculatorApp(String uri) throws IOException {

        APIURI apiUri = APIURI.valueOf(uri);

        if (req == null){
            PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));
            req = new RequestSpecBuilder().setBaseUri(apiUri.getURI())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType("text/xml")
                    .setAccept(XML).build();
            return req;
        }
        return req ;
    }
}
