Feature: Upload a file

Background:
* def setup = callonce read('../common.feature')
* url appBaseUrl

Scenario: Upload a valid file (.jpg)
	
	* configure headers = { Authorization: '#(setup.tokenEnabled)' }
	Given path 'api/upload'
	And multipart field file = read('good_file.jpg')
	And multipart field name = 'goodfile'
	When method post
	Then status 200

Scenario: Attempt to upload with no file

	* configure headers = { Authorization: '#(setup.tokenEnabled)' }
	Given path 'api/upload'
	And multipart field file = 'not a real file'
	And multipart field name = 'badfile'
	When method post
	Then status 400
	
	
Scenario: Attempt to upload with no authentication

	Given path 'api/upload'
	And multipart field file = read('good_file.jpg')
	And multipart field name = 'goodfile'
	When method post
	Then status 403
