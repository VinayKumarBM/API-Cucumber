@bookerAPI @updateBooking
Feature: To update a booking in restful-booker

  Background: create an auth token
    Given user has access to endpoint "/auth"
    When user creates a auth token with credential "admin" & "password123"
    Then user should get the response code 200

  @updateBookingDataTable
  Scenario Outline: To update a booking using cucumber Data Table
    Given user has access to endpoint "/booking"
    When user makes a request to view booking IDs
    And user updates the details of a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"

    Examples: 
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Rambo    |      10000 | true        | 2021-05-15 | 2021-06-11 | Breakfast       |
      | Rocky     | Balboa   |       2006 | false       | 2021-06-01 | 2021-07-10 | Dinner          |

  @updateBookingFromExcel
  Scenario Outline: To create and update a new booking using Excel data
    Given user has access to endpoint "/booking"
    And user creates a booking using data "<createKey>" from Excel
    When user updates the booking details using data "<updateKey>" from Excel
    Then user should get the response code 200
    And user validates the response with JSON schema from Excel

    Examples: 
      | createKey      | updateKey      |
      | createBooking1 | updateBooking1 |
      | createBooking2 | updateBooking2 |

  @updateBookingFromJSON
  Scenario Outline: To update a booking using JSON data
    Given user has access to endpoint "/booking"
    When user makes a request to view booking IDs
    And user updates the booking details using data "<dataKey>" from JSON file "<JSONFile>"
    Then user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"

    Examples: 
      | dataKey        | JSONFile         |
      | updateBooking1 | bookingBody.json |
      | updateBooking2 | bookingBody.json |

  @partialUpdateBooking
  Scenario: To partially update a booking
    Given user has access to endpoint "/booking"
    When user makes a request to view booking IDs
    And user makes a request to update first name "John" & Last name "Wick"
    Then user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"