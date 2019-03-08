package com.api.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty:target/cucumber/cucumber.txt", 
				"html:target/cucumber/cucumber-html-report",
				"json:target/cucumber/cucumber.json"
				}
		,features= {"src/test/resources/features"}
		,glue = {"com.api.stepdefinition"}
		//,dryRun = true
		,monochrome = true
		,tags= {"@testAPI"}
		)
public class TestRunner {

}