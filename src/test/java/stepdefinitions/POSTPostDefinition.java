package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.aflr.utils.RestAssuredExtension;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class POSTPostDefinition {

    private ResponseOptions<Response> response;

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String path, DataTable dataTable) throws URISyntaxException {
        var map = dataTable.asMap();
        response = RestAssuredExtension.postOpsWithBody(path, map);
    }

    @Then("the call got success with a {int} http code")
    public void theCallGotSuccessWithAHttpCode(int httpCode) {
        assertThat(response.statusCode(), is(httpCode));
    }
}
