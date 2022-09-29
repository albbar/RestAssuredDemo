package runners;


import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/googleMapAPIPlaceValidations.feature",
        glue = "stepsDefinitions",
        tags = "@DeletePlace",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class GoogleMapAPIPlaceValidationsRunner {

}
