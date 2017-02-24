Find Resources

Meta:
@feature Resource Finder

Narrative:
As a user
I want to use the resource finder
So that I can find government benefits that apply to me

Scenario: Find benefits using benefit finder
Given I am on the Native One Stop resource finder page
When I complete the resource finder questionnaire
Then I should see available benefits
