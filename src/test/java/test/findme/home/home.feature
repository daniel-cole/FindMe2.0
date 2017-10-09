Feature: Access the user home path

Background:
* def setup = callonce read('../common.feature')
* url appBaseUrl

Scenario: Retrieve home information for user
	
	* configure headers = { Authorization: '#(setup.tokenEnabled)' }
	Given path 'api/home'
	When method get
	Then status 200
	And match response == { response: 'hello testuser1' }
	
Scenario: Attempt to retrieve home information as a disabled user


