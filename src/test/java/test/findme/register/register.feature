Feature: Registration

Background:
* url appBaseUrl

Scenario: Register a new user

	Given path 'api/register'
	And request { username: 'testuser', password: 'supersafepassword', email: 'test@testmail.com' }
	When method post
	Then status 201
	And match response == { success: 'created new user: testuser' }
	
Scenario: Register a user when the username is already taken

	Given path 'api/register'
	And request { username: 'testuser', password: 'supersafepassword', email: 'test2@testmail.com' }
	When method post
	Then status 409
	
Scenario: Register a user when the email is already taken

	Given path 'api/register'
	And request { username: 'testuser2', password: 'supersafepassword', email: 'test@testmail.com' }
	When method post
	Then status 409
