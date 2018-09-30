package com.shiftedtech.qa.scripts.Hybrid_BDD_POM.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Debajyoti Paul on 4/3/2018 at 6:10 PM
 */

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
        glue = {"com.shiftedtech.qa.scripts.Hybrid_BDD_POM.steps",
                "com.shiftedtech.qa.framework.Hybrid_Framework.hooks"},
        //dryRun = true,
        plugin={
                "pretty:target/cucumber-test-report/cucumber-pretty.txt",
                "html:target/cucumber-test-report",
                "json:target/cucumber-test-report/cucumber-report.json",
                "junit:target/cucumber-test-report/test-report.xml"
        }
)

public class BDD_Runner_TestNg {

    private TestNGCucumberRunner cucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass(){
        cucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", dataProvider = "features", description = "Runs cucumber features")
    public void feature(CucumberFeatureWrapper cucumberFeature){
        cucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features(){
        return cucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass(){
        cucumberRunner.finish();
    }
}
