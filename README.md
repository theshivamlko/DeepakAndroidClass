# Chatter-mail API Documenation

Host: http://54.86.172.79

Notes:

- Every request and response is in JSON format.
- Included jwttoken's value in Bearer Authorization on in every request from then on (Except login and Register API)

## Routes:

 - POST /auth/login:  Log-in with credentials. Required parameters are email:String and password:String.  If everything is okay, status 200 will be sent in response jwttoken is to be saved and sent in every request from then on.

 {
 	"email"			: "example@gmail.com",
 	"password"		: "String",
     "deviceType"   : "String",
     "deviceToken"  : "String",
     "deviceId"     : "String"
        
 }

- POST /auth/register/: Send the properties in JSON body which you want to register.
If everything is okay, status 200 will be sent in response along with a user object in which the jwttoken is to be saved and sent in every request from then on.

	{
		"firstName":"String",   Required
		"lastName": "String",   Required
		"email":"String", 	    valid mail is Required	
		"password":"String", 	Required
		"mobileNo": "Number"  	Optional valid mobile number is required
		"device"		:[{
		
			"deviceType":"String",
			"deviceToken":"String",
			"deviceId":"String"
		}]	
	}


- POST /api/linked:  Linked with mail. Send the properties in JSON body which you want to link mail accounts at same time after submit mail is fetch on mailData table
	
	json is given by Gmail API it should be valid 


	{
	  "linked_account" : [
			{
				"provider":"String",     Required ex:- "gmail" 
				"mail": "String",        Required ex:- "example@gmail.com" 
				"json":{
	                "access_token" : "ya29.GlzhBr7shO4jP7DD2Tc_jIcZS4jUraRj1XBKWepuSjJKwsb74iqUE0a3QAOyhgSq6VmgLRINNdnAlcu3tkSO_ELv-GDvlQ7GdwYrd-QTy8SrYPySbrs7bPsBA",
	                "expires_in" : 3600,
	                "scope" : "https://www.googleapis.com/auth/gmail.readonly https://www.googleapis.com/auth/userinfo.profile https://mail.google.com/ https://www.googleapis.com/auth/gmail.modify",
	                "token_type" : "Bearer",
	                "id_token" : "eyJGciOiJSUzI1NiIsImtpZCI6ImE0MzEzZTdmZDFlOWUyYTRkZWQzYjI5MmQyYTdmNGU1MTk1NzQzMDgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1NDU1OTMzMTI5NzUtYzY2YWo4bTU3Y2htajN2dmdkaWlvcWZmNG5qM2IzMDAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI1NDU1OTMzMTI5NzUtYzY2YWo4bTU3Y2htajN2dmdkaWlvcWZmNG5qM2IzMDAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTcyMjQ4NDUzODAzNTgyNTk0MDciLCJhdF9oYXNoIjoicmkwLWRuMjE3b0I5eUxmMlk2NU9nUSIsIm5hbWUiOiJsYWxpdCBrdW1hciIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vLWgtemlhVHk1a0UwL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUFBL0FDSGkzcmNGY1FqeGJOcEV0Z3JLbkU1OWFjVmQwY1pNVWcvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6ImxhbGl0IiwiZmFtaWx5X25hbWUiOiJrdW1hciIsImxvY2FsZSI6ImVuLUdCIiwiaWF0IjoxNTU0MzU1MzE3LCJleHAiOjE1NTQzNTg5MTd9.Xrwk-Cc4t3gjPTbq4WVm59-3w1qP1BPoMXWJEwVyFzDha7CnuqZCJznO0T-hXVt19kf3MZ671WBm27WcWKWu1bumcIFGj6fvRvA1HS9NV4YKD-A3mtgqyZCRYExGZxe7VOC3YUmzbVZ95O3CDHFC3vAvAdx7sokyNhbz4j0tWqPBUrIGZkUT32DiW7UlvL5XCGB_xfrSrn94OWSp-D5FwohrYwvpZckfCjm_0ld5dMdKAy_7l8U2NFQkROGnzCEzlEMRy_tle10KEwVdGil4V_Czo8m3MXQSoaiK9qeIdZgCIp1csilurK2KA4UoEdO0Y4KlJY_O6V3bo18PGRjEYQ",
	                "created" : 155435316,
	                "refresh_token" : "1/TvnGsVIUuMzj-3oXTzLP1XRv0v3XFoQZskW6czy9M"
				}
			}
		]
	}

## User routes:

- GET /user/: Get user profile info. Possible Responses: Status : 200 - User profile info in response! Status : 401 - Autorization error, you need to log in. (make sure you have included jwttoken's value in Bearer Authorization on header).

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
