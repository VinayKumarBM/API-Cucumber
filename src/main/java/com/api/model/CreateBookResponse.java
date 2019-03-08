package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "id","title" }) // If these value does not come in response it will not throw error
public class CreateBookResponse {

	private String title;
	private String body;
	
	@JsonProperty("userId") // This will help us to handle properties that are different from Java style
	private String userID;
	private Integer id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUserId() {
		return userID;
	}

	public void setUserId(String userId) {
		this.userID = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
    public String toString() {
		return "CreateBookResponse[ id = "+id+", useId = "+userID+", title = "+title+", body = "+body+"]";
	}
}