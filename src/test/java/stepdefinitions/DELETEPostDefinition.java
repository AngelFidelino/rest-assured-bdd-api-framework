package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.aflr.utils.RestAssuredExtension;
import org.hamcrest.core.IsNot;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class DELETEPostDefinition {

    private ResponseOptions<Response> response;

    @Given("^user perform POST operation for \"([^\"]*)\" with body as$")
    public void userPerformPOSTOperationForWithBodyAs(String path, DataTable table) {
        var column = table.cells().get(0);
        Map<String, String> map = new HashMap<>();
        map.put("id", column.get(0));
        map.put("title", column.get(1));
        map.put("auhtor", column.get(2));
        assertThat(RestAssuredExtension.postOpsWithBody(path, map).statusCode(), is(200));
    }

    @And("^user perform DELETE operation for \"([^\"]*)\"$")
    public void userPerformDELETEOperationFor(String path, DataTable table) {
        var column = table.cells().get(0);
        Map<String, String> map = new HashMap<>();
        map.put("postId", column.get(0));
        RestAssuredExtension.deleteOpsWithPathParam(path, map);
    }

    @And("^user perform GET operation with path parameter for \"([^\"]*)\"$")
    public void userPerformGETOperationWithPathParameterFor(String path, DataTable table) {
        var column = table.asList();
        Map<String, String> map = new HashMap<>();
        map.put("postId", column.get(1));
        response = RestAssuredExtension.getOpsWithPathParam(path, map);
    }

    @Then("^user should not see the body with the title ([\\w\\s]+)$")
    public void userShouldNotSeeTheBodyWithTheTitle(String title) {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
}
