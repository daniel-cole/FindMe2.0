Feature: Login

Background:
* url appBaseUrl

Scenario: Login as a valid user

	Given path 'api/login'
	And request { username: 'testuser1', password: 'testuser1password' }
	When method post
	Then status 200
