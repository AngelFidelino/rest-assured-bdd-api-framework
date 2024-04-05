package stepdefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.aflr.enums.ApiResources;
import org.aflr.pojo.Location;
import org.aflr.pojo.Place;
import org.aflr.utils.RestAssuredSpecsUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PlaceValidationStepDefinition {
    private Response response;
    private JsonPath jsonPathResponse;

    private Place getPlace(String name, String language, String address) {
        Place p = new Place();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    @Given("Add Place payload {string} {string} {string}")
    public void add_place(String name, String language, String address) throws FileNotFoundException {
        Place place = getPlace(name, language, address);
        response = given().spec(RestAssuredSpecsUtil.getRequestSpec()).body(place).when()
                .post(ApiResources.ADD_PLACE_API.getUri()).then().spec(RestAssuredSpecsUtil.getResponseSpec()).extract()
                .response();
        jsonPathResponse = new JsonPath(response.asString());
    }

    @Then("the API call got success with a {int} http code")
    public void the_api_call_got_success_with_a_http_code(Integer int1) {
        assertEquals(200, response.getStatusCode());
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String prop, String expectedValue) {
        assertEquals(jsonPathResponse.getString(prop), expectedValue);
    }

    @And("verify place_id created with the name {string} using GET_PLACE_API")
    public void verify_place_id_created_with_the_name_using_get_place_api(String placeName)
            throws FileNotFoundException {
        //placeId = jsonPathResponse.getString("place_id");
        final Response response = given().spec(RestAssuredSpecsUtil.getRequestSpec())
                .queryParam("place_id", jsonPathResponse.getString("place_id")).when()
                .get(ApiResources.GET_PLACE_API.getUri()).then().extract().response();
        JsonPath jsonPath = new JsonPath(response.asString());
        assertEquals(placeName, jsonPath.getString("name"));
    }

    @Given("Delete place payload")
    public void delete_place() throws FileNotFoundException {
        JsonObject o = new JsonObject();
        o.add("place_id", jsonPathResponse.getString("place_id"));
        response = given().spec(RestAssuredSpecsUtil.getRequestSpec()).body(o.toString()).when()
                .post(ApiResources.DELETE_PLACE_API.getUri()).then().log().all()
                .spec(RestAssuredSpecsUtil.getResponseSpec()).extract().response();
    }
}
