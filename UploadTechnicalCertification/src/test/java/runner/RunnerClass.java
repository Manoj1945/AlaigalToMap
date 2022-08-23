package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"C:\\Users\\Acer\\Downloads\\MaplogikCucumberScenarios-master\\src\\test\\java\\features"},
glue = {"stepdefinition","hooks"}, dryRun = false,
plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
monochrome = true)
public class RunnerClass extends AbstractTestNGCucumberTests {

}
