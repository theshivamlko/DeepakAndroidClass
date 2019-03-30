# Chatter-mail API Documenation

Host: http://54.86.172.79

Notes:

- Every request and response is in JSON format.
- Included jwttoken's value in Bearer Authorization on in every request from then on (Except login and Register API)

## Routes:

 - POST /api/login:  Log-in with credentials. Required parameters are email:String and password:String.  If everything is okay, status 200 will be sent in response jwttoken is to be saved and sent in every request from then on.

- POST /api/register/: Send the properties in JSON body which you want to register.
If everything is okay, status 200 will be sent in response along with a user object in which the jwttoken is to be saved and sent in every request from then on.

	{
		"firstName":"String",   Required
		"lastName": "String",   Required
		"email":"String", 	    valid mail is Required	
		"password":"String", 	Required
		"mobileNo": "Number"  	Optional valid mobile number is required
	}


- POST /api/linked:  Linked with mail. Send the properties in JSON body which you want to link mail accounts at same time after submit mail is fetch on mailData table
	
	access token and refresh token is given by Gmail API it should be valid 

	{
	  "linked_account" : [
			{
				"provider":"String",     Required ex:- "gmail" 
				"mail": "String",        Required ex:- "example@gmail.com" 
				"accessToken":"String",  valid Required
				"refreshToken":"String"  valid Required
			}
		]
	}

## User routes:

- GET /api/profile: Get user profile info. Possible Responses: Status : 200 - User profile info in response! Status : 401 - Autorization error, you need to log in. (make sure you have included jwttoken's value in Bearer Authorization on header).

- POST /api/editProfile: Update profile details. Send the properties in JSON body which you want to update. Eligibe properties are: (Only send the data you want to update and make sure you have included jwttoken's value in Bearer Authorization on header)

	{
		"firstName":"String",   
		"lastName": "String",   
		"mobileNo": "Number"  	
	}

- POST /api/filterMailByLabel: Return all the data related to performance by choosing labelType like: category_promotions, category_personal, inbox, important, spam, sent, Draft, starred, unread and provider like: gmail, outlook etc.(make sure you have included jwttoken's value in Bearer Authorization on header)

	{
		"provider": "String",    				Required example: gmail
		"labelType":"String"       				Required example: category_promotions
	}

- POST /api/userAction: This API count the action of user like how much time user: read, forward, reply, click_on_link, click_on_image

	{	
		
		"type": "String",        Required example: "click_on_image"
		"messageId":"String"   Required example: "169856beff39881a"
	}

- POST /api/readMail: when user read mail from App its also unmark on mail inbox its do this accessToken	

	{
		"messageId":"String",   			Required
		"read": "Boolean",					Required   
		"mail":"String"  					Required example: "lalit.emaster@gmail.com"
	}	

- GET /api/search?check=value: Get data accounding to search from all save data from DB	
