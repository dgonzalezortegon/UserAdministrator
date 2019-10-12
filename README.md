# UserAdministrator
User Administrator


Requirements
--------------

PostgreSQL 11.x
Java 7
Hibernate 5


3th party Libraries
---------------------------
Lombok --> org.projectlombok
Spring SpringBoot
SpringSecurity 
Dozer
H2
GSON

RUN & USER MANUAL
#################

there is a script to run the app.   "run.bat" on Windows

The script :  build the app and run the console!!

console Commands
---------------------

help   --> the commands
quit   --> exit 
whoami --> the current user
Create an User (username is case sensitive)   --> create {username:'userTest',password:'pass',lastname:'test',email:'test@mail.com'}
Update an user       --> update userTest {password:'hola',lastname:'test',email:'test@mail.com'}
Delete an user       -->  delete username
Get an User          --> get username
All users            --> all
Create a new Account --> newiban username iban


DOC IMPLEMENTATION
####################
A New Document 

NEXT FEATURES
##############

- SEND MAIL FOR THE USERS, when the user account is enabled
- Password in MD5
