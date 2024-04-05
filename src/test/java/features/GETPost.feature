Feature:
    Verify different GET operations using REST-assured
  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Angel"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names "Angel,Oli"

