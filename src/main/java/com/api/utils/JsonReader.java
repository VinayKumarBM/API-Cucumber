package com.api.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

	private static String dataPath = System.getProperty("user.dir") + "/src/test/resources/data/";

	public static String getRequestBody(String jsonFileName, String jsonKey) {

		JSONParser parser = new JSONParser();
		String body = null;
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)parser.parse(new FileReader(dataPath+jsonFileName));
			//System.out.println(String.format("JSON file data: %s", jsonObject.toJSONString()));
			body = jsonObject.get(jsonKey).toString();
			//System.out.println(String.format("Request Body: %s", body));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("JSON file not found at path: " + dataPath+jsonFileName);
		} catch (IOException e) {
			throw new RuntimeException("IOException while reading file: " + jsonFileName);
		} catch (ParseException e) {
			throw new RuntimeException("Parse Exception occured while Parsing: " + jsonFileName);
		}
		return body;
	}
}
