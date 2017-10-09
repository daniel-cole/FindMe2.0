package test.findme;

import cucumber.api.CucumberOptions;

//to get proper cucumber html reports you need to enable json; run; disable json; run
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html" }, tags = { "~@ignore" })
public class FindMeTest extends TestBase {

}
