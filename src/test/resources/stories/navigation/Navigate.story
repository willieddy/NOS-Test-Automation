Navigate

Meta:
@feature Navigate

Narrative:
As a user
I want to be able to navigate around the nativeonestop.gov site
So that I can find the desired site functionality

Scenario: navigate around site
Given I am on the Native One Stop home page
When I click on <navBarButton> on the top navigation bar
Then I should see the <pageName> page
Examples:
|pageName|navBarButton|
|WELCOME TO NATIVE ONE STOP|Home|
|Resource Finder|Resources|
|Resource Finder|Resources,Resource Finder|
|Browse by Category|Resources,Browse by Category|
|Browse by Agency|Resources,Browse by Agency|
|Overview|About|
|Overview|About,Overview|
|Privacy Policy|About,Privacy Policy|
|FAQs for Individuals|Help|
|FAQs for Individuals|Help,FAQs for Individuals|
|FAQs for Tribes|Help,FAQs for Tribes|
