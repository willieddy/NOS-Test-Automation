Navigate

Meta:
@feature Navigate

Narrative:
As a user
I want to be able to navigate around the nativeonestop.gov site
So that I can find the desired site functionality

Scenario: navigate around site
Given I am on the Native One Stop home page
When I click on <navBarPath> on the top navigation bar
Then I should see the <navBarPath> page
Examples:
|navBarPath|
|Home|
|Resources|
|Resources,Resource Finder|
|Resources,Browse by Category|
|Resources,Browse by Agency|
|About|
|About,Overview|
|About,Privacy Policy|
|Help|
|Help,FAQs for Individuals|
|Help,FAQs for Tribes|
