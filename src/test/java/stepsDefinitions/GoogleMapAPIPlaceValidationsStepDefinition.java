package stepsDefinitions;

import hooks.GoogleMapApiHook;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import others.Helpers;
import stepsDefinitions.utils.APIResource;
import stepsDefinitions.utils.DataDrivenExcel;
import stepsDefinitions.utils.Excel;
import stepsDefinitions.utils.TestDataBuild;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepsDefinitions.utils.Constants.*;

@RunWith(Cucumber.class)
public class GoogleMapAPIPlaceValidationsStepDefinition extends Helpers {

    DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<>();
    TestDataBuild dataBuild = new TestDataBuild();
    RequestSpecification givenRS;
    Response response;
    static String placeId;

    @Given("Add Place Payload with {int}")
    public void add_place_payload_with(int row, DataTable dataExcel) throws IOException {
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);
        Excel excel = new Excel(dataFeature.get(0).get(EXCEL_PATH), dataFeature.get(0).get(TAB), true, row);
        data = dataDrivenExcel.readExcel(excel);

        //request
        givenRS = given()
                .spec(requestSpecificationGoogleMapAPI(GOOGLE_MAP_API))
                .body(dataBuild.addPlacePayLoad(data.get(ACCURACY), data.get(NAME), data.get(LANGUAGE),
                        data.get(ADDRESS), data.get(LAT_LOCATION), data.get(LNG_LOCATION), data.get(PHONE_NUMBER), data.get(WEB_SITE)));
    }

    @When("user calls {string} with {string} HTTP request") // you can use '"([^"]*)"' or {String} or {int}
    public void user_calls_with_post_http_request(String API, String method) {
        APIResource resourceAPI = APIResource.valueOf(API);

        //request
        if (method.equalsIgnoreCase(POST)) {
            response = givenRS.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase(GET)) {
            response = givenRS.when().get(resourceAPI.getResource());
        }

    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int status) {
        assertEquals(response.getStatusCode(), status);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_id created maps to user using {string} {int}")
    public void verify_place_id_created_maps_to_using( String aPI, int row, DataTable dataExcel) throws IOException {
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);
        Excel excel = new Excel(dataFeature.get(0).get(EXCEL_PATH), dataFeature.get(0).get(TAB), true, row);
        data = dataDrivenExcel.readExcel(excel);

        placeId = getJsonPath(response, PLACE_ID);
        givenRS = given().spec(requestSpecificationGoogleMapAPI(GOOGLE_MAP_API))
                .queryParam(PLACE_ID, placeId);

        user_calls_with_post_http_request(aPI, GET);
        String actualName = getJsonPath(response, NAME);
        assertEquals(actualName, data.get(NAME));

    }

    /*
    DeletePlaceAPI
     */

    @Given("DeletePlace Payload")
    public void deletePlacePayload() throws IOException {

        if (placeId == null){
            GoogleMapApiHook hook = new GoogleMapApiHook();
            placeId = hook.AddPlace();
        }

        givenRS = given()
                .spec(requestSpecificationGoogleMapAPI(GOOGLE_MAP_API))
                .body(dataBuild.deletePlacePayload(placeId));
    }


}
