# Native One Stop Automated Testing

## Overview
This repository contains a java application that runs automated tests against the Native One Stop website. This is done via the SerenityBDD, jBehave, and Selenium frameworks. 

## Repository Contents
- The repository contains at its root a drivers directory containing executables for browsers in different operating systems. These drivers can be moved to the resources directory under src, probably a better location for them.
- A file named serenity.properties. Selecting the web driver is done via [Serenity configuration](http://serenity-bdd.info/docs/serenity/#_serenity_system_properties_and_configuration) in this file. Properties in this file can be added as system properties for a single deployment of this repository to execute in multiple environments and on multiple browsers.
- pom.xml is for maven configuration
- src/ contains the java application

## Running the Tests
- [Maven Goals](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) are configured in pom for generating report
- At time of writing:
```         
<plugin>
    <groupId>net.serenity-bdd.maven.plugins</groupId>
    <artifactId>serenity-maven-plugin</artifactId>
    <version>${serenity.version}</version>
    <executions>
        <execution>
            <id>serenity-reports</id>
            <phase>post-integration-test</phase>
            <goals>
                <goal>aggregate</goal>
            </goals>
        </execution>
    </executions>
</plugin>
``` 
  
## [Data Driven Tests](http://serenity-bdd.info/docs/serenity/#_data_driven_tests)
Analysis of questionnaire is data driven where the information is provided from a json file and run as concurrent tests

## [Filtering test runs](http://serenity-bdd.info/docs/serenity/#_jbehave)
Simple filtering using tags example:  `mvn clean verify -Dmetafilter="+feature Reporting"`

### [Advanced filtering](http://jbehave.org/reference/stable/meta-filtering.html) 
- Using Groovy script exampe: `mvn clean verify -Dmetafilter="groovy: level ==~ /.*[testing|regression].*/"`  
- In examples (see section Filtering on Example Scenarios): `{metaByRow=true}`

## [Serenity with JBehave](http://serenity-bdd.info/docs/serenity/#_implementing_the_tests)
Serenity makes no distinction for reporting between the JBehave-style @Given, @When and @Then annotations and the Serenity-style @Step annotations: both will appear in the test reports. jBehave needs the @Given, @When and @Then-annotated methods to correlate to the statements found in the story files. A method annotated with @Given, @When or @Then can call Serenity @Step methods, or call page objects directly - this application includes the extra level of abstraction provided by the @Step methods to make the work more reusable and maintainable.
