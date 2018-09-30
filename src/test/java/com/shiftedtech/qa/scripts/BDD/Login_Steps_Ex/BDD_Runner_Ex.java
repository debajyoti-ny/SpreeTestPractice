package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 5:49 PM
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        //tags = { "@debug" },
        //tags = { "@smoke" },         /* smoke test only */
        //tags = { "@smoke,@debug" }, /* debug or smoke test */
        //tags = { "@smoke","@debug" },  /* debug and smoke test */
        //monochrome = true,
        features = "src/test/resources/features/LoginStepsEx.feature",
        //features = "src/test/resources/features/LoginFunctionality2.feature",
        //features = {"src/test/resources/features/LoginFunctionality2.feature",
        //            "src/test/resources/features/LoginFunctionality.feature"},
        glue = {"com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex"},
        //dryRun = true,
        plugin={
                "pretty:target/cucumber-test-report/cucumber-pretty.txt",
                "html:target/cucumber-test-report",
                "json:target/cucumber-test-report/cucumber-report.json",
                "junit:target/cucumber-test-report/test-report.xml"
        }
)
public class BDD_Runner_Ex {
}
