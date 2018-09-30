package com.shiftedtech.qa.scripts.Hybrid_BDD_POM.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run it from the Command Line Interface, the following codes/syntax are used.
 * mvn clean test -Dcucumber.options="--tags @debug"                   //Only debug
 * mvn clean test -Dcucumber.options="--tags @debug --tags @smoke"     //AND
 * mvn clean test -Dcucumber.options="--tags @debug,@smoke"            //OR
 */

/**
 * This is an example of Hybrid Framework (A mixture of Page Object Model, some level of Keyword
 * Driven Testing and Behavioral Driven Testing)
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@debug" },
        //tags = { "@smoke" },         /* smoke test only */
        //tags = { "@smoke,@debug" }, /* debug or smoke test */
        //tags = { "@smoke","@debug" },  /* debug and smoke test */
        //monochrome = true,
        features = "src/test/resources/features/LoginStepsEx.feature",
        //features = "src/test/resources/features/LoginFunctionality2.feature",
        //features = {"src/test/resources/features/LoginFunctionality2.feature",
        //            "src/test/resources/features/LoginFunctionality.feature"},
        glue =  {"com.shiftedtech.qa.scripts.Hybrid_BDD_POM",
                "com.shiftedtech.qa.framework.Hybrid_Framework.hooks"},
        //dryRun = true,
        plugin={
                "pretty:target/cucumber-test-report/cucumber-pretty.txt",
                "html:target/cucumber-test-report",
                "json:target/cucumber-test-report/cucumber-report.json",
                "junit:target/cucumber-test-report/test-report.xml"
        }
)
public class BDD_Runner_JUnit {
}
