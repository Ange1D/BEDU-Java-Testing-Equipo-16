Feature: Validate interviewer

  Scenario: Add interviewer
    Given a interviewer with name "Angel" Last name "Test" Email "any@email.com" is Active "true" and is Admin "true"
    When the user save interviewer
    Then the system should save interviewer Email "any@email.com"
