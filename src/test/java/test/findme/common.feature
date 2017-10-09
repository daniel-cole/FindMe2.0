@ignore
Feature: common feature for retrieving an authorization token for subsequent requests

Background:
* url appBaseUrl

Scenario:
	Given path 'api/login'
	And request { username: 'testuser1', password: 'testuser1password' }
	When method post
	Then status 200
	
* def tokenEnabled = responseHeaders['Authorization'][0]