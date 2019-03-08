package com.api.stepdefinition;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;

import com.api.model.CreateBookResponse;
import com.api.model.CreateUserResponse;
import com.api.utils.ExcelUtils;
import com.api.utils.JsonReader;
import com.api.utils.ResponseHandler;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestAPIStepDefs {
	private Response response;
	private RequestSpecification request;
	private Map<String,String> excelDataMap;
	private List<Map<String, String>> dataTableMap;
	private static StringWriter requestWriter,responseWriter;
	private static PrintStream requestCapture,responseCapture;
	private static final String baseUri = "https://reqres.in";
	private static String endpointUri;

	@Before
	public void setup() {
		requestWriter = new StringWriter();
		responseWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
	}

	public RequestSpecification requestSetup() {
		return RestAssured.given()
				.filter(new RequestLoggingFilter(requestCapture))
				.filter(new ResponseLoggingFilter(responseCapture));
	}

	public void printRequestAndResponse() {
		System.out.println("Request ==> \n"+requestWriter);
		System.out.println("Response ==> \n"+responseWriter);
	}

	@Given("^user has access to api \"(.*)\"$")
	public void user_has_access_to_api(String basePath) {
		//RestAssured.baseURI = "https://reqres.in";
		//RestAssured.basePath = "https://reqres.in"
		endpointUri = baseUri+basePath;
	}

	@When("^user makes a post call$")
	public void user_makes_a_call(DataTable dataTable) {
		dataTableMap = dataTable.asMaps(String.class,String.class);
		JSONObject jsonObject = new JSONObject();
		for(Entry<String, String> map : dataTableMap.get(0).entrySet()) {
			jsonObject.put(map.getKey(),map.getValue());
		}		
		request = requestSetup();
		response = request.contentType("application/json")
				.accept("application/json")
				.body(jsonObject.toJSONString())
				.post(endpointUri);
		printRequestAndResponse();
	}

	@Then("^user validates that response code is \"(.*)\"$")
	public void user_validates_that_response_code_is(String responseCode) {
		Assert.assertEquals("Response code did not match", Integer.parseInt(responseCode), response.getStatusCode());
	}

	@Then("^user validates the name & job from response$")
	public void user_validates_the_name_job_from_response() {
		JsonPath jsonPath = response.jsonPath();
		System.out.println("name: "+jsonPath.get("name"));
		System.out.println("job: "+jsonPath.get("job"));
		Assert.assertTrue("Name did not match", dataTableMap.get(0).get("name").equals(jsonPath.get("name")));
		Assert.assertTrue("Job did not match", dataTableMap.get(0).get("job").equals(jsonPath.get("job")));
		System.out.println("Successfully validated the response.");
	}

	@When("^user makes a post call with JSON body from \"(.*)\" with key \"(.*)\"$")
	public void user_makes_a_post_call_with_JSON_body_from_key(String JSONfileName, String jsonKey) {
		request = requestSetup();
		response = request.contentType("application/json")
				.body(JsonReader.getRequestBody(JSONfileName,jsonKey))
				.post(endpointUri);
		printRequestAndResponse();
	}

	@Given("^user has access to api uri (.*)$")
	public void user_has_access_to_api_uri(String excelKey) {
		excelDataMap = ExcelUtils.getData(excelKey);
	}

	@When("^user makes a post call with body data from Excel$")
	public void user_makes_a_post_call_with_body_data_from_Excel() { 
		request = requestSetup();
		response = request.contentType("application/json")
				.body(excelDataMap.get("requestBody"))
				.post(excelDataMap.get("URL"));
		printRequestAndResponse();
	}

	@Then("^user gets the response code (.*)$")
	public void user_gets_the_response_code(String responseCode) {
		Assert.assertEquals("Response code did not match", Integer.parseInt(responseCode), response.getStatusCode());
	}

	@Then("^user validates the JSON schema with (.*)$")
	public void user_validates_the_JSON_schema_with(String responseJSON) {
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+responseJSON));
		System.out.println("Successfully Validated schema from "+responseJSON);
	}

	@Given("^user has access to uri \"(.*)\"$")
	public void user_has_access_to_uri(String uri) {
		endpointUri = uri;
	}

	@Then("^user validates the create \"(.*)\" response schema$")
	public void user_validates_the_response_schema_of_create(String responseToValidate) throws Throwable {
		if(responseToValidate.equalsIgnoreCase("book")) {
			CreateBookResponse bookResponse = ResponseHandler.deserializedResponse(response, CreateBookResponse.class);
			Assert.assertTrue("Id was not generated: ", StringUtils.isNotBlank(String.valueOf(bookResponse.getId())));
		}
		else if(responseToValidate.equalsIgnoreCase("user")) {
			CreateUserResponse userResponse = ResponseHandler.deserializedResponse(response, CreateUserResponse.class);
			Assert.assertTrue("Id was not generated: ", StringUtils.isNotBlank(userResponse.getId()));
			Assert.assertTrue("Time Stamp was not generated: ", StringUtils.isNotEmpty(userResponse.getCreatedAt()));
		}
		System.out.println("Successfully Validated schema of create "+responseToValidate+" response.");
	}
}
