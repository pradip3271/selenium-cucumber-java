Feature: Create Giving Page sgtep
	In order to create a Giving page with a valid account
	As a first time fundraiser
	I need to be able to complete the giving page
	
Scenario: Verify copy and inline validation in create Giving page step
	# Navigate to Giving page registration via Comicrelief site
	Given I navigate to giving page registration via cr
	And I maximize browser window
	Then element having css ".intro-copy" should have text as "Enter your email address to get started."
	
	# Enter random email address
	When I clear input field having id "email"
	And I enter random email address into input field having id "email"
	And I click on element having id "btnNext"
	Then element having css "form#create-account>div:nth-child(3)>div:nth-child(2)>div>h1>div" should have text as "Welcome"
	
	# Over 16 registration
	When I click on element having id "over"
	And I enter random first name into input field having id "firstName" 
	And I enter random last name into input field having id "lastName"
	And I enter random password into input field having id "password" 
	And I scroll to end of page
	And I click on element having id "btnNext"
	And I wait 5 seconds for element having id "giving-page" to display
	Then element having css "p.intro-copy" should have text as "Now we know who you are, get your Giving Page up and running."
	
	# Select comic relief generic giving page
	When I click on element having class "campaign-image--19"
	And I wait 3 seconds for element having css ".form__field--userUrlGp" to display

	# validate link field error message
	And I wait 3 seconds for element having id "userUrlGp" to be enabled
	And I clear input field having id "userUrlGp"
	Then element having css "div.userUrlGp>div>div>span>div>p" should have text as "Hey! You need to enter a link - it's how sponsors will find your page."
	
	# validate link field success message
	When I enter random name into input field having id "userUrlGp"
	And I scroll to element having id "userFrTarget"
	And I click on element having id "userFrTarget"
	Then element having css "p.validation__success>span:nth-child(2)" should have text as "This link is available"
	 
	# validate target field
	And I wait 3 seconds for element having id "userFrTarget" to be enabled
	When I clear input field having id "userFrTarget"
	And I click on element having id "btnNext"
	Then element having css "div.amount>div>div>div>span>div>p" should have text as "Please fill in a fundraising target"
	
	When I enter random non numeric into input field having id "userFrTarget"
	And I click on element having id "btnNext"
	Then element having css "div.amount>div>div>div>span>div>p" should have text as "Please fill in a fundraising target" 
	
	# Select "Yes" for "Are you fundraising on behalf of a company, workplace or organisation?"
	When I enter random amount into input field having id "userFrTarget"
	And I click on element having id "partner"
	Then element having css ".userAffiliationOrganisationOption>label" should have text as "Workplace or organisation"
	And element having id "userAffiliationOrganisationOption" should be present
	When I click on element having id "btnNext"
	Then element having css "div.userAffiliationOrganisationOption>div>div>span>div>p" should have text as "Please enter the name of your organisation"
	
	When I enter random organisation name into input field having id "userAffiliationOrganisationOption"
	# click create Giving page button
	And I click on element having id "btnNext"
	Then element having css ".profile-head>h1" should have text as "Comic Relief Giving Page"
	And element having css ".profile-head>h3" should have first name
	And I wait for 5 sec
	
	# Log out
	When I click on link having text "Log out"
	Then element having css ".intro>div>section>h1" should have text as "Log in to your Giving Page"
	 
	
	
	