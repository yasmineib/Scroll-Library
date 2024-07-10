# Lab03-Hunter-Group04-A2

Note: (the admin has all the same permissions that the user does and other extra permissions)\
Note: (in the main menu, we can press q to exit the program.)\
Note: (if you are currently performing an operation then after you are done, the program asks you if you wanna go back to the main menu using "m", or "q" to quit)\
Note: (if you are a guest, you can only view scrolls and you are not able to preview, download or search scrolls with a filter even though this is part of the "View scrolls" functionality for general users and admin )\
Note: (if you wanna do testing or change passwords, quit the current program, delete database.db and then do gradle run so that teh database is refreshed)\
Note: (if deleting database.db and building does not make the apllication build then please comment the tests that are causing errors out )

To start the program, we enter at the command line and use the following gradle commands:

gradle clean\
gradle build\
gradle run\
gradle --refresh dependencies\
gradle run --console = plain

When the program starts running and asking for user input,
the program asks if the customer to login as a guest or not.

If they say yes:\
&ensp; &ensp; -> It identifies the user as a guest and asks if them if they would like to View Scrolls (this is the only functionality available to guests)\
If they say no:\
&ensp; &ensp; It asks the customer if they would like to create a new account\
&ensp; &ensp; &ensp; &ensp; If they say yes:\
&ensp; &ensp; &ensp; &ensp; &ensp; &ensp; It asks the user to enter their full name, email, password and username and registers them to the database\
&ensp; &ensp; &ensp; &ensp; If they say no:\
&ensp; &ensp; &ensp; &ensp; &ensp; &ensp; It asks the user to enter their id, username and password to authenticate them and give them appropriate access to the application

The followng is the general structure of how the program works:

If you are a general user:\
There is an "opening screen" that displays the options: 
1) Update your profile information\
-> If they want update their profile information:
Ask the user for their id and asks them if they want to update their id, username, password, personal details or go back to the main menu. Deepending on what the user chooses to update their personal credentials get updated accordingly and stored to the database. (PS: If the personal credentials already exist in the databse it prompts the user to enter different information to the personal crednetials that are stored.)\
2) View scrolls\
-> If they want to view scrolls:\
&ensp; &ensp; It asks the user if they want to preview, download or search scrolls with a filter
3) Add/upload a scroll\
-> If they want to add or upload a scroll:\
&ensp; &ensp; It tells the user which file path to add and then asks them which file to add and it will ask them to give a unique name and if it is valid then it will be added to the database in the library\
4) Edit a scroll\
-> If they want to edit a scroll:\
&ensp; &ensp; Ask them which scroll they want to update and if they are the owner of the scroll then they will be allowed to update the name or contents of the scroll. If they are not the owner/uploader of the scroll then they will be denied from editing it.
5) Remove a scroll\
-> If they want to remove a scroll:\
&ensp; &ensp; Ask the user if they want to remove the scroll and they will only be able to remove the scroll if they are the owner/uploader of the scroll

If you are an admin:\
There is an "opening screen" that displays further additional options along with the basic options for the general user:\
(note: the admin is able to perform all the operations that a general user can as mentioned above)\
6) View all users (and their profiles)\
-> If they want to view all users:\
&ensp; &ensp; It displays the id, role, username, password, full name and email of all the users registered in the database\
7) Add a user\
-> If they want to add a user:\
&ensp; &ensp; It asks to enter the new user's full name, email, phone number, and akss them if they are an admin. It then asks them to enter their username and password. After the new user is added they get logged in as this user. If they want to continue as the original user they were logged in as before, they will have to restart the application. 
8) Delete a user:\
-> If they want to delete a user:\
&ensp; &ensp; It deletes the user from the database\
9) View the number of uploads and downloads for each scroll\
-> If they want to view total uploads and downloads for each scroll:\
&ensp; &ensp; It gives a list of the scrolls and the total number of uploads and downloads for the given scrolls


Each operation is explained in more detail below:

1. Update Your Profile Information:

This feature allows users to modify their profile details. Users can update information like their username, password, email address, or other personal details. Updating profile information ensures that users can keep their account details accurate and up to date.

2. View Scrolls:

Viewing scrolls is a fundamental function of the application. It enables users to see a list of all available scrolls in the virtual library. This list typically includes scroll titles, authors, upload dates, and other relevant information. Viewing scrolls is essential for users to decide which scrolls they want to download, edit, or preview.

3. Add/Upload a Scroll:

Uploading a scroll is the process by which users contribute their own content to the virtual library. It typically involves providing a scroll title, content, and possibly other attributes such as an optional password to protect the scroll. This functionality is often restricted to authorized users (e.g., admins, registered users) and can include checks to ensure the scroll adheres to certain guidelines.

4. Edit a Scroll:

Editing a scroll is the capability to make changes to a scroll that a user has previously uploaded. Users can modify the scroll's content or other attributes. This feature is essential for users who want to update their content or correct errors in their scrolls.

5. Remove a Scroll:

Removing a scroll means deleting it from the virtual library. Users who have uploaded a scroll may want to remove it for various reasons, such as it being outdated or no longer relevant. This feature should be available to users who have uploaded the scroll, and it typically involves confirming the deletion to prevent accidental removal.

6. View All Users (and Their Profiles):

This feature allows administrators or users with specific privileges to see a list of all registered users in the system. It typically includes user information like usernames, email addresses, and roles. Access to this functionality may be restricted to authorized users to maintain privacy and security.

7. Add a User:

Adding a user is an administrative function that allows authorized users (e.g., admins) to create new user accounts. This feature typically requires inputting user details like a username, password, email address, and user role. Proper authentication and authorization are essential to ensure the security of this operation.

8. Delete a User:

Deleting a user is another administrative function that permits authorized users to remove user accounts from the system. This action should be carefully executed to maintain data integrity and security. It might also involve reassigning any scrolls or content owned by the deleted user.

9. View the Number of Uploads and Downloads for Each Scroll:

This feature displays statistical information about each scroll in the virtual library. Users can see how many times a particular scroll has been uploaded (contributed) or downloaded by other users. This functionality can help users gauge the popularity or relevance of specific scrolls and might be used to organize the virtual library.
