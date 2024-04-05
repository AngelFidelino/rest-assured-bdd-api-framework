package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.aflr.utils.RestAssuredExtension;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.is;


public class GETPostDefinition {

    private ResponseOptions<Response> response;

    @Given("I perform GET operation for {string}")
    public void i_perform_get_operation_for(String path) throws URISyntaxException {
        response = RestAssuredExtension.getOps(path);
    }

    @Then("I should see the author name as {string}")
    public void i_should_see_the_author_name_as(String name) {
        assertThat(response.getBody().jsonPath().get("author"), is(name));
    }

    @Then("I should see the author names {string}")
    public void i_should_see_the_author_names(String names) {
        assertThat(response.statusCode(), is(200));
        assertThat(response.getBody().jsonPath().get("author"), containsInRelativeOrder(names.split(",")));
    }
}
