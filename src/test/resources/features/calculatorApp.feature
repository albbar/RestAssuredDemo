@ValidationCalculatorApp
Feature: Validating calculator API

  @SumOperation
  Scenario: Verify if the calculator do a correct sum
    Given configure the sum between two numbers
    When user calls "AddOperation" API resource
    Then the API response is success with status code 200
    And verify the sum result