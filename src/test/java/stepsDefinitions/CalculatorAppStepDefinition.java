package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import others.Helpers;
import stepsDefinitions.utils.APIResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static stepsDefinitions.utils.Constants.CALCULATOR_API;
import static stepsDefinitions.utils.Constants.CALCULATOR_ADD_REQUEST;

public class CalculatorAppStepDefinition extends Helpers {

    RequestSpecification givenRS;
    Response response;

    @Given("configure the sum between two numbers")
    public void configure_the_sum_between_two_numbers() throws IOException {
        File file = new File(CALCULATOR_ADD_REQUEST);
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

        givenRS = given()
                .spec(requestSpecificationCalculatorApp(CALCULATOR_API))
                .body(requestBody);
    }

    @When("user calls {string} API resource")
    public void user_calls_API_Resource(String API) {
        APIResource resourceAPI = APIResource.valueOf(API);
        response = givenRS
                .when().post(resourceAPI.getResource());
    }

    @Then("the API response is success with status code {int}")
    public void the_API_response_is_success_with_status_code(int status) {
        assertEquals(response.getStatusCode(), status);
    }


    @Then("verify the sum result")
    public void verifyTheSumResult() {
        givenRS.then().body("//*AddResult.text()", equalTo("13"));
    }
}
