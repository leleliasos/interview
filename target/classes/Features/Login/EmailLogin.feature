Feature: Email Login
As a client with an existing email account
I want to be able to login into the system when given correct credentials
|User  |UserType  |
|test  |user      |
|tester|super-user|

  Scenario: Login with an existing account
  Given I have an existing account
  When I submit a registered email along with the correct password
  Then I should get an authentication token
