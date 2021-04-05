@bookerAPI @deleteBooking
Feature: To delete a booking in restful-booker

  Background: create an auth token
    Given user has access to endpoint "/auth"
    When user creates a auth token with credential "admin" & "password123"
    Then user should get the response code 200

  @deleteBookingIDs
  Scenario: To delete a booking
    Given user has access to endpoint "/booking"
    And user makes a request to view booking IDs
    When user makes a request to delete booking with basic auth "admin" & "password123"
    Then user should get the response code 201

  @e2eTest
  Scenario Outline: To perform a CURD operation on restful-booker
    Given user has access to endpoint "/booking"
    When user creates a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    And user validates the response with JSON schema "createBookingSchema.json"
    And user updates the details of a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"
    And user makes a request to view details of a booking ID
    And user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"
    And user makes a request to delete booking with basic auth "admin" & "password123"
    And user should get the response code 201

    Examples: 
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Doe      |       1200 | true        | 2021-05-05 | 2021-05-15 | Breakfast       |
