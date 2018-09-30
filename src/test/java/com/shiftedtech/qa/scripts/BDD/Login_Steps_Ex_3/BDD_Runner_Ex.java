package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 5:49 PM
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        //tags = { "@debug" },
        tags = { "@smoke" },         /* smoke test only */
        //tags = { "@smoke,@debug" }, /* debug or smoke test */
        //tags = { "@smoke","@debug" },  /* debug and smoke test */
        //monochrome = true,
        features = "src/test/Resources/features/LoginStepsEx.feature",  //for Spree Search functionality
        //features = "src/test/Resources/features/LoginStepsEx3.feature",
        //features = "src/test/Resources/features/LoginSteps.feature",
        //features = {"src/test/Resources/features/LoginSteps.feature",
        //            "src/test/Resources/features/LoginStepsEx.feature"},
        glue = {"com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3"},
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
