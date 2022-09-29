@ValidationPlaceAPI
Feature: Validating place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is begin Successfully added using AddPlaceAPI
    Given Add Place Payload with <Row>
      | Excel Path                        | Tab      |
      | src/test/resources/data/data.xlsx | AddPlace |
    When user calls "AddPlaceAPI" with "POST" HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to user using "GetPlaceAPI" <Row>
      | Excel Path                        | Tab      |
      | src/test/resources/data/data.xlsx | AddPlace |

    Examples:
      | Row |
      | 1   |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "POST" HTTP request
    Then the API call is success with status code 200