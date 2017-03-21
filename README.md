# Native One Stop Automated Testing
## Serenity BDD
### [Interaction with JBehave](http://serenity-bdd.info/docs/serenity/#_implementing_the_tests) 

Serenity makes no distinction between the 
JBehave-style @Given, @When and @Then annotations, and the Serenity-style @Step annotations: both will appear in the 
test reports. However you need to start with the @Given, @When and @Then-annotated methods so that JBehave can find the 
correct methods to call for your stories. A method annotated with @Given, @When or @Then can call Serenity @Step 
methods, or call pageNum objects directly (though the extra level of abstraction provided by the @Step methods tends to 
make the tests more reusable and maintainable on larger projects).

### Configuration

- Stored in project root directory
- File named `serenity.properties`
- Heirarchy for stories configured by `serenity.requirement.types`
  - Epic
  - Story
  
### Running the Tests

- [Maven Goals](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) are configured in pom
for generating report
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
  
### [Data Driven Tests](http://serenity-bdd.info/docs/serenity/#_data_driven_tests)

Analysis of questionnaire is data driven where the information is provided from a json file and run as concurrent tests

### [Filtering test runs](http://serenity-bdd.info/docs/serenity/#_jbehave)
Simple filtering using tags example:  `mvn clean verify -Dmetafilter="+feature Reporting"`

###### [Advanced filtering](http://jbehave.org/reference/stable/meta-filtering.html) 
- Using Groovy script exampe: `mvn clean verify -Dmetafilter="groovy: level ==~ /.*[testing|regression].*/"`  
- In examples (see section Filtering on Example Scenarios): `{metaByRow=true}`
