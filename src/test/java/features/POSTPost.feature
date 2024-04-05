Feature:
    Verify different POST operations using REST-assured

  Scenario: Verify post operation
    Given I perform POST operation for "/posts"
      | id      | 1     |
      | author  | Angel |
    Then the call got success with a 200 http code
