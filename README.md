# Radius
An android app for fetching json data and displaying the user details in list.

Created a single screen application where it lists the users from the below API. Data will be in JSON format.

https://raw.githubusercontent.com/iranjith4/radius-intern-mobile/master/users.json

# Requirements :
1. List the Users with below mandatory details - 
a. Name, 
b. User Image (from the URL), 
c. User’s Age.
2. Design any UI as per your choice.

# Procedure:
1. Used String request in volley to get the json array in response.
2. Used string manipulation retrieved required data of each user.
3. Used lazy image loading technique for loading and dsiplaying image from url as an aysnc task.
