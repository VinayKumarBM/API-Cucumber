package com.api.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void testStart(Scenario scenario) {
		System.err.println("\n\nScenario: "+scenario.getName());
		System.err.println("*****************************************************************************************");
		System.err.println("\n\t\t\t--{		-TEST STARTS-		}--\n");
		System.err.println("*****************************************************************************************");
	}
	
	@After
	public void testEnds(Scenario scenario) {
		System.err.println("*****************************************************************************************");
		System.err.println("\n\t\t\t--{		-TEST "+scenario.getStatus().toString().toUpperCase()+"-		}--\n");
		System.err.println("*****************************************************************************************");
	}
}
