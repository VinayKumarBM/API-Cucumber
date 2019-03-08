package com.api.utils;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ResponseHandler {
	
	public static <T> T  deserializedResponse(Response response, Class T ) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		T responseDeserialized = (T) mapper.readValue(response.asString(), T);
		System.out.println(responseDeserialized.toString());
		return responseDeserialized;
	}

}
