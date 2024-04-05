package cucumberoptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {"stepdefinitions"}
        , plugin = "json:target/jsonReports/cucumber-report-2.json"
        // ,tags = "@DeletePlace"
)
public class testRunners {

}
