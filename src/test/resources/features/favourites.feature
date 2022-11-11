Feature: Dog API

  Background:
    Given x-api-key and baseURI are already acquired.

  Scenario: Create a vote
    When Onur POST a create "votes" with image_id : "1a2b3c" for sub_id : "onur"
    Then Onur see response has 200 status code

  Scenario: Listing vote for user
    When Onur see listing "votes" for "onur"
    Then Onur see "onur" for sub_id has 200 status code

  Scenario: Listing all vote
    When Onur see all "votes" list
    Then Onur see all list has 200 status code


