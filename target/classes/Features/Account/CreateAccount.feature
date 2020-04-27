Feature: Account Creation
  As a new customer
  I want to be able to create a new account
  By submitting a name and a job role
  |User  |UserType  |
  |test  |user      |
  |tester|super-user|

  Scenario: Create a new account
    Given I have a valid name and a job role
    When I submit a new user application form
    Then I should get a unique account id



