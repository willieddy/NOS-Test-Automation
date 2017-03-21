# Native One Stop Automated Testing

## Overview
This repository contains a java application that runs automated tests against the Native One Stop website. This is done via the SerenityBDD, jBehave, and Selenium frameworks.

## Repository Contents
- Drivers directory containing executables for browsers in different operating systems. These drivers can be moved to the resources directory under src, probably a better location for them.
- [Serenity.properties](http://serenity-bdd.info/docs/serenity/#_serenity_system_properties_and_configuration) for configuration of jBehave, serenity, and selenium. Properties allowed in this file can be added as system properties for a single deployment of this repository to execute in multiple environments and on multiple browsers without having to redeploy the application with an altered properties file.
- pom.xml is for maven configuration
- src/ contains the java application

## Running the Tests
- To run the contents of this repository, java (1.8+) and maven need to be installed. There are options for each operating system for these services. For mac I recommend [HomeBrew](https://brew.sh/). Lots of resources out there for setting up java and maven on any system.
- For a simple execution, [`mvn verify`](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) will run the tests and generate a report into target/site/serenity located in the base directory. This directory contains a fully functioning static website (begin at index.html)

### [Filtering test runs](http://serenity-bdd.info/docs/serenity/#_jbehave)
To filter using tags, meta tags need to be placed in the story files. 
Simple filtering using tags example:  `mvn verify -Dmetafilter="+feature Reporting"`

### [Advanced filtering](http://jbehave.org/reference/stable/meta-filtering.html) 
More advanced filtering techniques can be employed for more advanced CI features like smoke tests.
Using Groovy script exampe: `mvn clean verify -Dmetafilter="groovy: level ==~ /.*[testing|regression].*/"`  
Filtering in examples table in story file (see section Filtering on Example Scenarios): `{metaByRow=true}`

### [Running in different environments](http://serenity-bdd.info/docs/serenity/#_opening_the_page)
These tests can be run in different environments by editing the `webdriver.base.url` property

## Data Driven Tests
Analysis of questionnaire is data driven where the information is provided from a json file
This file contains four base level objects
 - Two metadata strings (`summary` and `title`) that aren't used for test execution or reporting
 - `answers` array, where each answer object contains data about how to answer an individual question
 - `result` object. This object contains:
    - `totalResourceCount`: an optional value to assert the total available resources
    - `resultAssertionList`: a list of resource titles to assert made it into the results
    - `favorites`: a list of resource titles that will be selected from results and added to favorites, where it will be asserted they are present

## [Serenity with JBehave](http://serenity-bdd.info/docs/serenity/#_implementing_the_tests)
Serenity makes no distinction for reporting between the JBehave-style @Given, @When and @Then annotations and the Serenity-style @Step annotations: both will appear in the test reports. jBehave needs the @Given, @When and @Then-annotated methods to correlate to the statements found in the story files. A method annotated with @Given, @When or @Then can call Serenity @Step methods, or call page objects directly - this application includes the extra level of abstraction provided by the @Step methods to make the work more reusable and maintainable.

## Notes and Gotcha's
- Selenium in general will handle scrolling to a page element automatically. This gets the page element to within the screen resolution. This website contains a menu bar overlay that selenium will complain about if it is covering the element to be acted upon. To remove this issue, a method to scroll to the top of the page is called frequently
- Whitespace around text in the benefit titles can cause xpath issues, use xpath function `normalize-space` to get around this
- Case can be inconsistent between pages (results, favorites), use xpath function `translate(%s,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')` to remove this inconsistency
- Titles containing single quotes are handled via concatenation, see `formatXpathStringInput` method in `NosPageEnum.java`
