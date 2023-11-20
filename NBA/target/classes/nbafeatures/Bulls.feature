Feature: NBA Functionality

Background:

Given Launch Bulls home page

@Functional
Scenario: Bulls  Footer Links Validation

When Scroll down to the footer
Then Get all the footer links
And Write the links into CSV file
And Send the duplicate links in the report