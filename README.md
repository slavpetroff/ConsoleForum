# ConsoleForum
Exam Project
OOP Exam – Console Forum
Back in the beginning of the Millenium, Stamat got his first girlfriend. Not so long after, unfortunately, the Surecheck had two lines and Stamat got very worried. Luckily, it was just at the dawn of the TeenProblem internet forum, so he could easily share his problem with the rest of the world. Inspired by the result, Stamat wanted to make his own forum. Back at the days, he didn't know about databases and it was right after the first .NET framework was released, so he decided to persist all the data in memory. After a hard time of headbanging, he managed to make an engine and a simple registration. Your task is to help Stamat to build a fully functional forum with login, posting questions, answers, etc.
A forum has users, questions and answers. Users can be guests, registered or admins. Guests can register, login, view all questions, open a question, and exit the forum. Registered users can login and after login they can logout, view all questions, open a question, post questions, post answers to questions, choose best answers to questions they posted, exit the forum. Admins can login and after login they can logout, view all questions, open a question, post questions, post answers to questions, choose best answers to all questions, exit the forum. Questions have id, author, title and body (content) as well as answers. Answers have id, author and body.
Carefully read all the code from the given skeleton – you are going to need it, as well as the description below and the notes at the end.
You are NOT allowed to edit the contents of the Interfaces and Utility namespaces. You are NOT allowed to directly edit the Forum, CommandFactory and CommandException classes.

PART I – Building the Forum Functionality :
Implement the Necessary Classes
Using existing interfaces, implement the necessary classes (Question, Answer, BestAnswer, User and Administrator) in order for the forum to function properly. Avoid code duplication by applying the best practices of Object-Oriented Programming.

Implement the Forum Commands :

The forum somewhat works, but the number of commands it supports is too insufficient. Add the following commands and implement their functionality, following the model of command implementation in the forum.
•	Register {username} {password} {email} – should register a user. The username and email should be unique, so registering with the same email or username is not allowed.
o	If it is successful
User {username} with Id: {id} successfully registered
o	If the username or email is already taken:
User with the same mail or username already exists
•	Register {username} {password} {email} administrator – should register an administrator. This command should be allowed only when no users are registered yet. The engine uses this when it is started to create a default admin user. This command should print on the console the following result:
o	If no permission to register an administrator:
Cannot register administrator

Sample Input :

register pesho 123 12@12.com
register pesho 456 abv@abv.abv
register gosho 123 gosh@a.bg administrator

Sample Output

User pesho with Id: 2 successfully registered
User with the same mail or username already exists
Cannot register administrator

•	Login {username} {password} – logins a user. 
o	If another user is already logged in, cannot login the second user
Already logged in. In order to switch to another user - logout first
o	If username or password is invalid
Wrong username/password or username not registered
o	If successful
User {username} successfully logged in
•	Logout – logouts a user. Should be allowed only when a user is logged in.
o	If successful
Logout success
o	If no user is logged in
Operation not permitted. You have to login first
Sample Input

register pesho 123 12@12.com
register gosho 123 g@abv.bg
login pesho 456
login pesho 123
login gosho 123
logout
logout

Sample Output :

User pesho with Id: 2 successfully registered
User gosho with Id: 3 successfully registered
Wrong username/password or username not registered
User pesho with Id: 2 successfully logged in
Already logged in. In order to switch to another user - logout first
Logout success
Operation not permitted. You have to login first

•	ShowQuestions – should show all questions in the forum. Should work for all users.
o	If no questions
No questions
o	If there are some questions – each question in the following format, ordered in ascending order  by Id:

[ Question ID: {id} ]
Posted by: {author}
Question Title: {title}
Question Body: {body}
====================
{answers}
	If there are no answers, print:
No answers
	If there are some answers, print:
Answers:
{list of answers}
•	The best answer (if there is any) should be in the following format, always on top:
********************
[ Answer ID: {id} ]
Posted by: {author}
Answer Body: {body}
--------------------
********************
•	All other answers should be in the following format, ordered in ascending order by Id:
[ Answer ID: {id} ]
Posted by: {author}
Answer Body: {body}
--------------------
•	OpenQuestion {questionId} – displays the specified question, following the same format as in ShowQuestions command. Should work for all users.
o	If the id is not valid
No such question
•	PostQuestion {title} {body} – saves the question in the system the current user as author. Should work only after login.
o	If no user is logged in
Operation not permitted. You have to login first
o	If successful
Question with Id: {id} successfully posted
•	PostAnswer {body} – saves the answer in the system to the question that has been opened. Works only after login and when a question is opened.
o	If no user is logged in
Operation not permitted. You have to login first
o	If no question is opened
Operation not permitted. You have to open a question first
o	If successful
Answer with Id: {id} successfully posted
Sample Input
register pesho 123 pesho@abv.bg
login pesho 123
showquestions
postQuestion helloooooo i'mpeshoooo
SHOWQUESTIONs
logout
register gosho 123 g@g.bg
login gosho 123
ShowQuestions
OpenQuestion 1
PostAnswer hii'mgosho
PostQuestion homework help
ShowQuestions
	Sample Output
User pesho with Id: 2 successfully registered
User pesho successfully logged in
No questions
Question with Id: 1 successfully posted
[ Question ID: 1 ]
Posted by: pesho
Question Title: helloooooo
Question Body: i'mpeshoooo
====================
No answers
Logout success
User gosho with Id: 3 successfully registered
User gosho successfully logged in
[ Question ID: 1 ]
Posted by: pesho
Question Title: helloooooo
Question Body: i'mpeshoooo
====================
No answers
[ Question ID: 1 ]
Posted by: pesho
Question Title: helloooooo
Question Body: i'mpeshoooo
====================
No answers
Answer with Id: 1 successfully posted
Question with Id: 2 successfully posted
[ Question ID: 1 ]
Posted by: pesho
Question Title: helloooooo
Question Body: i'mpeshoooo
====================
Answers:
[ Answer ID: 1 ]
Posted by: gosho
Answer Body: hii'mgosho
--------------------
[ Question ID: 2 ]
Posted by: gosho
Question Title: homework
Question Body: help
====================
No answers

•	MakeBestAnswer {answerId} – makes the specified answer the best answer for the opened question. A question can have only one best answer.
o	If no user is logged in
Operation not permitted. You have to login first
o	If no question is opened
Operation not permitted. You have to open a question first
o	If the id is not valid
No such answer
o	If the user is not the author of the question or an administrator
You do not have enough permission to perform the desired operation
o	If successful
Answer with Id: {id} successfully made best answer
•	Exit – stops the forum execution. Should work for all users.
•	Any other command should print
Invalid command!
Additional Notes
•	The engine splits the command data by whitespace, so no whitespace is allowed in the parameters.
•	The id of the users, questions and answers is a positive integer number starting from 1 and incrementing by 1 for every new entry. There is a different id counter for the different classes of objects – one for users, one for questions and one for answers. When an entry is edited it should preserve its original id.
•	There is already registered one admin user with id 1, so the next registered user will have id 2 and so on.
•	The delimiter ('=', '-', '~' or '*') is exactly 20 symbols.
•	The command names should be case insensitive.
•	A question is considered opened after the OpenQuestion command and before Logout or ShowQuestions command.
•	After an answer is made "best answer" it will be printed as first answer, all other answers preserve their order by id.


Sample Input
register pesho 123 12@12.com
login pesho 123
PostQuestion WTF! textExplain
logout
register pepi 123 12@.gmail.com
logout
login pepi 123
OpenQuestion 1
PostAnswer NemashGriji
PostAnswer PakAz
MakeBestAnswer 2
logout
login pesho 123
MakeBestAnswer 2
OpenQuestion 1
MakeBestAnswer 2
ShowQuestions
exit


Sample Output
User pesho with Id: 2 successfully registered
User pesho successfully logged in
Question with Id: 1 successfully posted
Logout success
User pepi with Id: 3 successfully registered
Operation not permitted. You have to login first
User pepi successfully logged in
[ Question ID: 1 ]
Posted by: pesho
Question Title: WTF!
Question Body: textExplain
====================
No answers
Answer with Id: 1 successfully posted
Answer with Id: 2 successfully posted
You do not have enough permission to perform the desired operation
Logout success
User pesho successfully logged in
Operation not permitted. You have to open a question first
[ Question ID: 1 ]
Posted by: pesho
Question Title: WTF!
Question Body: textExplain
====================
Answers:
[ Answer ID: 1 ]
Posted by: pepi
Answer Body: NemashGriji
--------------------
[ Answer ID: 2 ]
Posted by: pepi
Answer Body: PakAz
--------------------
Answer with Id: 2 successfully made best answer
[ Question ID: 1 ]
Posted by: pesho
Question Title: WTF!
Question Body: textExplain
====================
Answers:
********************
[ Answer ID: 2 ]
Posted by: pepi
Answer Body: PakAz
--------------------
********************
[ Answer ID: 1 ]
Posted by: pepi
Answer Body: NemashGriji
--------------------


 
Part II – Forum Extension
Note: This problem does not take part in the Judge System evaluation. 
Extend the forum so that every time a user is prompted to enter a command, a header appears. The header should be in one of the two formats, depending on whether the user is logged or not:
~~~~~~~~~~~~~~~~~~~~
Hey stranger, care to login/register?
Hot questions: {count}, Active users: {count}
~~~~~~~~~~~~~~~~~~~~		~~~~~~~~~~~~~~~~~~~~
Welcome, {username}!
Hot questions: {count}, Active users: {count}
~~~~~~~~~~~~~~~~~~~~
•	Username is the logged user's username
•	The hot question's count represents the number of all questions in the forum with a best answer
•	The active users' count represents the number of all users in the forum with at least 3 answers
The header should appear each time before the user enters a command.
Reminder: You are not allowed to edit the Forum class. 



Sample Input /  Output
register batko 123 bateto@abv.bg
register kakata 567 kaka@yahoo.com
register penio 789 penio@gmail.ru
login batko 123
postQuestion baba_marta_party at_20:30_:)
postQuestion OOP_Exceptions what_is_exception?
OpenQuestion 2
postAnswer i_really_have_no_idea
OpenQuestion 1
PostAnswer 1 anybody_coming?
PostAnswer 1 I_sure_am_:^)
postAnswer 1 See_you_after_the_exam!
MakeBestAnswer 3		…
~~~~~~~~~~~~~~~~~~~~
Welcome, batko!
Hot questions: 1, Active users: 1
~~~~~~~~~~~~~~~~~~~~
