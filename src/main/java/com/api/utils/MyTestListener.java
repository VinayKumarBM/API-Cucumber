package com.api.utils;

import cucumber.api.Result;
import cucumber.api.Result.Type;
import cucumber.api.TestCase;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestCaseFinished;

public class MyTestListener implements ConcurrentEventListener {
	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
	}

	private void handleTestCaseFinished(TestCaseFinished event) {
		TestCase testCase = event.getTestCase();
		Result result = event.result;
		Type status = result.getStatus();
		Throwable error = result.getError();
		String scenarioName = testCase.getName();		
		if(error != null) {
			error.printStackTrace(System.out);
		}
		System.out.println("*****************************************************************************************");
		System.out.println("	Scenario: "+scenarioName+" --> "+status.name());
		System.out.println("*****************************************************************************************");
	}
}