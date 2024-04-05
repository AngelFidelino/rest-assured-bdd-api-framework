Feature: Verify different DELETE operations using REST-assured
  Test the delete operation
  Scenario:
    Given user perform POST operation for "/posts" with body as
      | id | title          | author  |
      | 3  | Api testing | Angel   |
    And user perform DELETE operation for "posts/{postId}"
      | postId  |
      | 3       |
      | 6       |
    And user perform GET operation with path parameter for "posts/{postId}"
      | postId  |
      | 3       |
    Then user should not see the body with the title Api testing
