Feature: Validating Place APIs
  @AddPlace @Regression
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place payload "<name>" "<language>" "<address>"
    #When use calls "ADD_PLACE_API" with a "POST" http request
    Then  the API call got success with a 200 http code
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created with the name "<name>" using GET_PLACE_API
    Examples:
      |name             | language    | address |
      |Frontline house  | French-IN   | 29, side layout, cohen 09 |
     # |Frontline miamo  | English     | cohen 10 |

  @DeletePlace
  Scenario: Verify delete place functionality
    Given Add Place payload "<name>" "<language>" "<address>"
    #And use calls "ADD_PLACE_API" with a "POST" http request
    And verify place_id created with the name "<name>" using GET_PLACE_API
    And Delete place payload
    Then  the API call got success with a 200 http code
    And "status" in response body is "OK"


