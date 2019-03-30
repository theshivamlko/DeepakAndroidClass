# FunEduLearn API v0.2
**Host**: `https://api.demo.com`

**Notes:** 

 - Any sub-route followed by '`:`' symbol means a token has to be
   supplied.
  - Every request and response is in JSON format.
  - Subject wherever mentioned has these values: 0 (Physics), 1 (Chemistry), 2(Maths), 3 (GK)
  - If any request's response is `Status: 5xx` at any point, please contact me [
here](mailto:harshsinghmandan@gmail.com), so I can look into it.

# Routes:

## Authentication routes:

 - **POST** `/auth/login`: Log-in with credentials. Required parameters are `email`:`String` and `password`:`String`. If everything is okay, status `200` will be sent in response along with a user object in which the jwttoken is to be saved and sent in every request from then on. 
 - On every login following details are also sent:
	 - Wallet balance
	 - Wallet history or logs
	 - List of tests available
	 - Complete profile info
 
	 ***Possible Responses:***  `Status : 200` - You're logged in!
 `Status : 401` - Invalid credentials.
 `Status : 301` - One or both the fields are missing.

 - **POST** `/auth/register/`: This is a two step process. The required parameters for the first step are are: `email`:`String`, `password`:`String`, `phone_number`:`Number` & `name`: `String`, Another param which is optional is `referral_code`:`String` (If a non empty string is sent and referral code is invalid an error will be returned). If everything is okay, an OTP will be sent to the number and a `signup_id` will be sent in response. For step 2, again send a **POST** request on this route with the parameters: `OTP`: `String` and `signup_id`: `String` (same signup_id which was given in response. A new account will be created if the `OTP` and `signup_id` matches. The created account's details will be sent in response. Save the **`user.jwttoken`** as it is required to send it in the header as `Authorization` at every request.
  ***Possible Responses:***  `Status : 200` - Request successful!
 `Status : 301` - One or more fields are missing.
 `Status : 302` - Unknown error, try again later.
 `Status : 302` - User already registered with this **number**.
 `Status : 302` - Wrong referral code
 `Status : 302` - User already registered with this **email**.
 `Status : 400` - Problem sending OTP right now, try again later.


 - **POST** `/auth/logout`: Logout and delete jwt token.
   ***Possible Responses:***  `Status : 200` - Logout successful!
 `Status : 401` - Autorization error, you need to be logged in to log out (make sure you have included jwttoken's value in Authorization header).

 - **POST** `/auth/forgot-password`: Forgot password route. Requires only one parameter: `phone_number`:`String`. An OTP will be sent to the phone number and a `resetPasswordSession` will be sent in the response which remains valid for 10 minutes. This `resetPasswordSession` is required in the next step.
  ***Possible Responses:***  `Status : 200` - Request successful!
 `Status : 301` - phone_number field is missing.
 `Status : 400` - Can't send OTP right now, try again later.

 - **POST** `/auth/reset-password/`: This is also a two step process. Step 1's required parameters are: `otp`: `String` and `resetPasswordSession`: `String`.If both the parameters are correct a new `resetPasswordSession` will be generated. In step 2, to set a new password you have to send the new `resetPasswordSession`:`String` and `password`:`String`.
  ***Possible Responses:***  `Status : 200` - Request successful!
 `Status : 401` - One or both the fields are missing.
 `Status : 401` - OTP is incorrect.
 `Status : 402` - OTP has expired, request a new OTP again.
 `Status : 403` - resetPasswordSession is incorrect.
 
 - **POST** `/auth/loggedin`: Tells you're logged in.
   ***Possible Responses:***  `Status : 200` - You're logged in!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## User routes:

 - **POST** `/user/changepassword`: Logged in user can update password by sending `oldpassword`:`String` and `newpassword` :`String`.
   ***Possible Responses:***  `Status : 200` - Password changed successfully!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).
  `Status : 400` - Wrong old password.

 - **POST** `/user/updateprofilephoto`: Use simple form upload method to upload a image file to use as the user profile photo. On successful upload the filename will be sent back. Images can be accessed at https://api.funedulearn.com/images/imagename.ext
   ***Possible Responses:***  `Status : 200` - Profile photo updated successfully!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

- **GET** `/user/`: Get user profile info.
   ***Possible Responses:***  `Status : 200` - User profile info in response!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

 - **POST** `/user/updateinfo`: Update profile details. Send the properties in JSON body which you want to update. Eligibe properties are: **(Only send the data you want to update)**
```json
{
  email: { type: String},
  phone_number: { type: String},
  name: { type: String},
  class: {type: String},
  school: {type: String},
  device_token_gcm: {type: String},
  parent_detail: [{
    name: {type: String},
    relation: {type: String},
    phone_number: {type: String},
  }],
  location: {
    state: {type: String},
    city: {type: String},
    address: {type: String},
  },
  settings: {
    language: {type: String},
    push_notifications: {type: Boolean},
  },
}
 ```
   ***Possible Responses:***  `Status : 200` - Updated details successfully!
   `Status : 400` - Not allowed to modify these properties.
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

 - **GET** `/user/reports`: Return all the data related to performance 
 You'll get the following data in response will all the data filled:
 ```json
 gk: {
            scoreTotal: 0, 
            scoreAchieved: 0, 
            count: 0,
            win_count: 0, 
            win_amount: 0, 
            average_question_time: 0,
            total_questions_count: 0, 
            correct_answered_count: {
              easy: 0,
              medium: 0,
              hard: 0,
            },
            tests: [], //Array of tests containing- date, score (for performance over time graph)
          },
          maths: {
            scoreTotal: 0, 
            scoreAchieved: 0, 
            count: 0, 
            win_count: 0, 
            win_amount: 0, 
            average_question_time: 0, 
            total_questions_count: 0,
            correct_answered_count: {
              easy: 0,
              medium: 0,
              hard: 0,
            },
            correct_topics_count: {},
            tests: [], //Array of tests containing- date, score (for performance over time graph)
          },
          physics: { 
            scoreTotal: 0, 
            scoreAchieved: 0, 
            count: 0, 
            win_count: 0, 
            win_amount: 0, 
            average_question_time: 0, 
            total_questions_count: 0,
            correct_answered_count: {
              easy: 0,
              medium: 0,
              hard: 0,
            },
            correct_topics_count: {},
            tests: [], //Array of tests containing- date, score (for performance over time graph)
          },
          chemistry: {
            scoreTotal: 0, 
            scoreAchieved: 0, 
            count: 0, 
            win_count: 0,
            win_amount: 0,  
            average_question_time: 0, 
            total_questions_count: 0,
            correct_answered_count: {
              easy: 0,
              medium: 0,
              hard: 0,
            },
            correct_topics_count: {},
            tests: [], //Array of tests containing- date, score (for performance over time graph)
          }
 ```
   ***Possible Responses:***  `Status : 200` - Profile photo updated successfully!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## Test/Quiz routes:

- **GET** `/test/get-tests`: Will return list of all the available tests.
   ***Possible Responses:***  `Status : 200` - You'll get a list of all the tests!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

- **POST** `/test/new-test`: Start a new test or quiz by sending the  `test_id`:`String` of a test. The `test_id` is one of the ids you get from `/get-tests`. As soon as you get the response the test will start. There is a server side clock that will check for time when the test is submitted.
   ***Possible Responses:***  `Status : 200` - You'll get test details!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

- **POST** `/test/submit-test`: Send the quiz/test answers by sending `activetest_id`:`String` that you'll get when the test starts. They request needs to be in this form:
```json
	{
			"activetest_id": "5ahcffff44577",
			"answers": [{
			"id": "questionid",
			"answer":"a" (or "b" or "c" or "d"),
			"time_taken":20, (inseconds)
			"skipped":false (boolean)
			}, 
			{..}, 
			{..},
			..
			]
	} 
```
	The response should be in the order in which the questions were sent.
- 
   ***Possible Responses:***  `Status : 200` - You'll get the result!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## Leaderboard routes:

- **POST** `/leaderboard/topwinners`: Will return list of all the available tests. Requires only one parameter: `count`:`Number` which denotes the top n winners to be returned. This only returns yesterdays winner for now.
   ***Possible Responses:***  `Status : 200` - You'll get a list top users!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## Notifications routes:

- **GET** `/notifications`: Returns all the notifications in the following format: 
```json
  type:   { type: Number},
  message: {type: String},
  global: {type: Boolean},
  user_id: { type: ObjectId},
  read: {type: Boolean},
  created_at: { type: Date },
```
   ***Possible Responses:***  `Status : 200` - You'll get all notifications.
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).
 
 - **POST** `/notifications/markasread`: Will mark all the sent notifications as read. Send notifications id in an array: `id`:`[Array]`
   ***Possible Responses:***  `Status : 200` - You'll get a list top users!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## FAQ, Rules and Prviacy routes:

- **GET** `/appinfo`: Returns all the notifications.
   ***Possible Responses:***  `Status : 200` - You'll get all data.
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).


## Wallet routes:

- **GET** `/wallet`: Will return wallet balance along with the last 5 logs.
   ***Possible Responses:***  `Status : 200` - You'll get the wallet balance along with logs!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).
 
- **GET** `/wallet/logs`: Will return all the previous logs in following format:
```json
  transaction_type:   { type: Number}, //Transaction type: //0 - LP Deduction //1 - MC Deduction //2 - LP Addition //3 - MC Addition
  amount: {type: Number},
  wallet_id:      { type: ObjectId},
  comment: 		{type: String},
  created_at:   { type: Date},
```
   ***Possible Responses:***  `Status : 200` - You'll get a list of all the logs!
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

- **POST** `/generate_checksum`: Will generate a checksum for payTM payment gateway along with starting a new transaction. Requires `amount`:`Number` in JSON body which is the amount for which the transaction is to be started.
   ***Possible Responses:***  `Status : 200` - You'll get checksum along with the transaction_ID!
 `Status : 500` - Checksum generation error.
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

- **POST** `/verify_checksum`: Will verify the checksum as well as the transaction status directly from PayTM to see if the transaction's successfull. Requires `transaction_id`:`String` and `CHECKSUMHASH`:`String`.
   ***Possible Responses:***  `Status : 200` - Checksum verified!
 `Status : 409` - Wrong transaction ID.
 `Status : 500` - Can't contact payTM right now.
 `Status: 416` - Wrong checksum.
 `Status: 400` - Checksum or transaction_id missing.
 `Status : 401` - Autorization error, you need to log in. (make sure you have included jwttoken's value in Authorization header).

## End
