Feature: Gmail send email from drafts

  Background:
    Given I log in with creds:
      | email    | tyled6@gmail.com |
      | password | qwerty23         |

  Scenario Outline: Checking Gmail draft functionality
    When I clear sent
    And I clear drafts
    And I clear bin
    And I compose email with fields: and save it to drafts
      | to      | <to>      |
      | subject | <subject> |
      | message | <message> |
    And I open drafts
    And I open email by <subject>
    Then I check message is <message>
 
    Examples:
      | to                 | subject  | message         |
      | ok130493@gmail.com | subject1 | hello message 1 |
#      | ok130493@gmail.com | subject2 | hello message 2 |
