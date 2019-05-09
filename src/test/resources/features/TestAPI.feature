@testAPI
Feature: This is to test API

  @testAPIDataTable1
  Scenario: Test Post Call to create user from DataTable
    Given user has access to api "https://reqres.in/api/users"
    When user makes a post call 
      |name		|job	| 
      |Sachin	|Tester | 
    Then user validates that response code is "201"
    And user validates the name & job from response
    And user validates the JSON schema with userSchema.json
    
  @testAPIDataTable2
  Scenario: Test Post Call to add book from DataTable
    Given user has access to api "https://jsonplaceholder.typicode.com/posts"
    When user makes a post call 
      |title				|body						|userId	| 
      |title of the book	|Body of the book goes here |095456	| 
    Then user validates that response code is "201"
    And user validates the JSON schema with bookSchema.json  

  @testAPIExcel
  Scenario Outline: Test Post Call from Excel file
    Given user has access to api uri <apiURIKey>
    When user makes a post call with body data from Excel 
    Then user gets the response code <responseCode>
    And user validates the JSON schema from Excel
    Examples:
    |apiURIKey		|responseCode	|
    |addBookDetail	|201			|
    |createUser		|201			|
	
	@testAPIJSON
  Scenario: Test Post Call from JSON file for register success
    Given user has access to api "https://reqres.in/api/register"
    When user makes a post call with JSON body from "registerBody.json" with key "register"
    Then user validates that response code is "200"   
    
  @testAPIJSON
  Scenario: Test Post Call from JSON file for login success
    Given user has access to api "https://reqres.in/api/login"
    When user makes a post call with JSON body from "registerBody.json" with key "login"
    Then user validates that response code is "200"
    
   @testAPIJSON
  Scenario: Test Post Call from JSON file for REGISTER - UNSUCCESSFUL
    Given user has access to api "https://reqres.in/api/register"
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