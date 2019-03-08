@testAPI
Feature: This is to test API

  @testAPIDataTable
  Scenario: Test Post Call from DataTable
    Given user has access to api "/api/users"
    When user makes a post call 
      |name		|job	| 
      |Sachin	|Tester | 
    Then user validates that response code is "201"
    And user validates the name & job from response 

	@testAPIExcel
  Scenario Outline: Test Post Call from Excel file
    Given user has access to api uri <apiURIKey>
    When user makes a post call with body data from Excel 
    Then user gets the response code <responseCode>
    And user validates the JSON schema with <JSONSchema>
    Examples:
    |apiURIKey		|JSONSchema				|responseCode	|
    |addBookDetail	|bookSchema.json		|201			|
    |createUser		|userSchema.json		|201			|
	
	@testAPIJSON
  Scenario: Test Post Call from JSON file for register success
    Given user has access to api "/api/register"
    When user makes a post call with JSON body from "registerBody.json" with key "register"
    Then user validates that response code is "201"   
    
  @testAPIJSON
  Scenario: Test Post Call from JSON file for login success
    Given user has access to api "/api/login"
    When user makes a post call with JSON body from "registerBody.json" with key "login"
    Then user validates that response code is "200"
    
   @testAPIJSON
  Scenario: Test Post Call from JSON file for REGISTER - UNSUCCESSFUL
    Given user has access to api "/api/register"
    When user makes a post call with JSON body from "registerBody.json" with key "unsuccessful"
    Then user validates that response code is "400"
    
  @testAPIPosts
  Scenario: Test Post Call from JSON file for posts scenario
    Given user has access to uri "https://jsonplaceholder.typicode.com/posts"
    When user makes a post call with JSON body from "postBody.json" with key "book"
    Then user validates that response code is "201"   
    And user validates the create "book" response schema
    
  @testAPIPosts
  Scenario: Test Post Call from JSON file for authors scenario
    Given user has access to uri "https://reqres.in/api/users"
    When user makes a post call with JSON body from "postBody.json" with key "user"
    Then user validates that response code is "201"  
    And user validates the create "user" response schema