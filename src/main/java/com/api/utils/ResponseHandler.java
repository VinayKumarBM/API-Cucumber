package com.api.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ResponseHandler {

	public static <T> T  deserializedResponse(Response response, Class T ){
		ObjectMapper mapper = new ObjectMapper();
		T responseDeserialized = null;
		try {
			responseDeserialized = (T) mapper.readValue(response.asString(), T);
			String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDeserialized); // Pretty print JSON
			System.out.println("Handling Response: \n"+responseDeserialized.toString());
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return responseDeserialized;
	}

}
