package com.expleo.project4;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources",
                    glue = "com.expleo.project4.StepDefs")
public class project4Test {
}
