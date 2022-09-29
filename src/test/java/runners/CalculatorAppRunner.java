package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\alberto.barboza\\IdeaProjects\\RestAssureDemo-master\\src\\test\\resources\\features\\calculatorApp.feature",
        glue = "stepsDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class CalculatorAppRunner {
}
