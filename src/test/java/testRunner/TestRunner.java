package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		//features = ".//Features//Customers.feature", //run one specific feature
		//features = {".//Features//Login.feature",".//Features//Customers.feature"}, //run multiple specific feature
		features = {".//Features/"}, //runs all feature file
        glue = {"stepDefinitions"},
        //tags= "@sanity",
        // tags= "@sanity,@regression", //scenarios beloging to both tags will be executed
        dryRun=false,
        monochrome=true,
        plugin= {"pretty","html:target/cucumber-report.html"}
		
		)


public class TestRunner {

}
