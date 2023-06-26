package hariKrishna.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\hariKrishna\\cucumber", glue = "hariKrishna.stepdefinition", monochrome = true, plugin = {
		"html:reports/cucumber.html" })
public class Runner extends AbstractTestNGCucumberTests {

}
