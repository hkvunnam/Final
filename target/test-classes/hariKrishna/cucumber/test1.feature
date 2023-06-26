#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Loging

Background:
Given I Landed on Website

  

  @tag1
  Scenario: Login with wrong credential
    Given I gave "hk@email.com" and "akjdbcisadvb"
    Then I validate the outcomes "Incorrect email or password."


  @tag2
  Scenario Outline: Login with wrong credentials
    Given I given <name> and <value>
    Then I validate the outcomes "Incorrect email or password."

    Examples: 
      | name  				 |			  value |
      | hk@email.com   |   akjdvbwivb |
      | vunn@email.com |   lkehvnq934 |
