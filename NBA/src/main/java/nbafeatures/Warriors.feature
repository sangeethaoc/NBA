Feature: NBA Functionality

Background:

Given Launch Warriors home page

@Functional
Scenario: TC001

When Go to Shop Menu and click on Men's item
And Find all jacket details
Then Write all jacket details in text file
And Send the file in the report

@Functional
Scenario: TC002

When Go to Menu and click on News & Features
And Count total number of videos feeds
Then Find total number of video feeds greater than three days