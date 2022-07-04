"# hotel.github.io" 
## Building "Hoa An motel mobile application" on Android operating system using Java language combined with NoSQL FireBase database to improve application access performance with the fastest possible speed. 
## With the requirements of the topic, the function can be divided into the following 3 groups of users:
## Booker object (Customer): This object has the right to book and contact the innkeeper, search for rooms by price, room name, ... View the location of the inn on the integrated google map, can click on the directions to get directions to the room that inn.
## Innkeeper object: This object has the right to add, edit, delete rooms, accept, moderate customers who have booked rooms, view approved lists, statistics: number of rooms available, booked and sold out, add customer information is in that room. Convenient for management.
## ADMIN object: Administrator or user with a system administrator account. In addition to the above basic functions, the administrator also has the following important functions: Manage the list of inns: inn owners, inn rooms such as adding, editing, deleting inns and rooms, View and delete the list of emails, innkeeper names that have been granted and have been logged into the system, Create an account and inn for the innkeeper.
## Home interface for customers to surf and search for accommodation: When opening the APP, the Home interface will appear. Here customers can see all the rooms of each hostel, can search for rooms by name and price.
![image](https://user-images.githubusercontent.com/65931654/177086964-72d22442-9c3b-47da-99f0-3a2694b4fd5a.png)
## ListMotel interface:Clicking on ListMotel the interface as shown below will appear. Used so that customers can find the accommodation they are looking for. And then click on the innkeeper found, to see the list of motel rooms and make a reservation, contact. This interface supports us to search for inns by inn name
![image](https://user-images.githubusercontent.com/65931654/177087331-fb6a3e72-8426-47d4-a49e-4ed3c86b4ab1.png)
## Room list interface: This is the interface of the list of rooms in an inn after we click on that inn. Here each room shows its room status
![image](https://user-images.githubusercontent.com/65931654/177088266-34027b68-5724-4f36-aa44-59f4f1fda741.png)
## Room View interface: We can book a room here and contact the innkeeper by phone. In the inn room state, there are 3 room states: available, booked, and out of room. Each room status corresponds to a different View button “Booking”. For the room status "ROOM AVAILABLE", the "Reservation" button has a blue background, black text. As for the room status "BOOKED", the "Reservation" button has a red background, white text. And the room status "OUT OF ROOM", then hide the button "Reservation". In this interface, all information of the inn room is displayed: room name, price, status, location, phone number, room introduction, room area.
## ++ View interface state "Room AVAILABLE":
![image](https://user-images.githubusercontent.com/65931654/177088328-9042415b-5285-4e13-b0e8-1bcf86e4e9e5.png)
## ++ Interface View status "SET":
![image](https://user-images.githubusercontent.com/65931654/177088466-a28f42cb-80b2-43ae-8431-513fefd91cbf.png)
## ++ View interface state "OUT OF ROOM":
![image](https://user-images.githubusercontent.com/65931654/177088510-5a14bddb-1946-4d30-90c6-c6f26fa53ae8.png)
## The interface displays the location of the inn and google map directions:Get the location on the map, show the address on it. When clicking on the red location button, the address will appear
![image](https://user-images.githubusercontent.com/65931654/177088824-39900d11-d39d-435f-85a9-cbe6dfa39a5a.png)
##  The google map interface shows directions to the inn:Below is the interface showing directions from my current location to the location of the motel room. Under the interface showing the location of the inn, there are 2 buttons for directions. I choose 1 of 2 buttons to get directions to the inn
![image](https://user-images.githubusercontent.com/65931654/177088904-8acf4d9e-7754-43f5-8eb7-98e39ebf6ee5.png)
## Booking interface:Enter correct, accurate and complete information set out. No information is missing. Then press the button “Reservation”. If we enter some missing information and press the "Reservation" button, it will report an error and ask us to enter the entry that has not been entered.
![image](https://user-images.githubusercontent.com/65931654/177088953-aefc6d04-e003-44e8-aceb-b1cb00bfeee8.png)
## Contact interface:When clicking on the contact button, it will be based on the phone number the owner has and suggest 3 options: choose zalo, call and skype. It's up to me to choose what's right for me
![image](https://user-images.githubusercontent.com/65931654/177089005-bfb608e8-1297-45dd-96fa-3607edbdf4d6.png)
## Search_Near . interface: Displays inns that are close to my current location. And also show the name of the inn when clicking on a red dot on the map, showing the nearby inns under the map
![image](https://user-images.githubusercontent.com/65931654/177089175-82cde007-9e3b-4741-a396-74eb9e095e79.png)
## The innkeeper's login interface: The innkeeper needs to fill in the email and password registered on the system, complete it and click on the login button. If you do not enter email or password or both email and password and press the login button, then inside the 2 boxes to enter email and password content, an error will be displayed: please enter email, please enter password. After entering the email and password completely and correctly, we can log in. And if we enter an incorrect email or password, it also gives an error
![image](https://user-images.githubusercontent.com/65931654/177091992-c41882c7-4788-4716-b8a0-988b8a6af167.png)
## The main interface of the innkeeper: 
![image](https://user-images.githubusercontent.com/65931654/177092176-c70d792e-0d58-40c9-8483-403b767e3d88.png)
## Window interface of the functions performed by the landlord (Navigation Drawer): When clicking on the Navigation Drawer, the interface will be opened, inside there are: name of the innkeeper, home, reservation management, approved list and logout. Each one performs a different function
![image](https://user-images.githubusercontent.com/65931654/177092308-ef0336eb-ea1e-4cf5-839f-5c306121d4a1.png)
## Reservation management interface: Display a list of customers who have booked a room, including information: name, customer phone number
![image](https://user-images.githubusercontent.com/65931654/177093393-720289a4-4d09-4367-babb-7936a036c364.png)
## Detailed interface of customer information (Approve or Do not approve): When clicking on a customer it will switch to an interface showing full details of the customer. Then we will have 2 buttons "Approve" and "Do not approve". If we find that customer is full of information and trustworthy, we choose "Approve" and otherwise choose "Do not approve".
![image](https://user-images.githubusercontent.com/65931654/177093567-80a80818-c7ed-4e8c-9b3f-40dfe3b233e2.png)
## Approved list interface: Show the innkeeper all approved information of the customer, which includes: customer name, customer phone number, name of guest room, IDNN/MSSV number, year of birth, hometown.
![image](https://user-images.githubusercontent.com/65931654/177093660-870817f9-697e-41b4-9d61-d6c4e8919c1f.png)
## Logout interface: When you click log out, the system will ask if you want to log out. If you want to select “Yes”, otherwise, select “No”.
![image](https://user-images.githubusercontent.com/65931654/177093799-54f4d218-fdf3-4d78-90ab-599ad256dce4.png)
## The innkeeper's inn list interface: When clicking on the innkeeper, a list of the inn's rooms will be displayed. And attached to the root below is a plus sign with a pink background that represents the function of adding an inn room of the innkeeper
![image](https://user-images.githubusercontent.com/65931654/177093950-6e024571-1f22-4ff5-b171-c7f7c69219a6.png)
## New inn registration interface: The innkeeper fills in the required information and then presses the “UPLOAD” button to add a room for the innkeeper. If one of those information is missing, an error message will be displayed, asking us to enter the missing information
![image](https://user-images.githubusercontent.com/65931654/177094161-5106f251-f11d-4053-9edd-101268d26a2d.png)
## Room details interface before clicking the "Add" Button: This is the detailed interface of the inn when the status is out of room. When the room is full, below will have the customer information in that room. And the innkeeper can add the information of other customers who are sharing with that customer. First the "Add" button will appear, then we click the "Add" button, it will show the edit text for us to enter information and the "Add" button will now switch to the "Confirm" button.
![image](https://user-images.githubusercontent.com/65931654/177094492-c5282e8c-00ae-4171-8dae-42c36aa8a06d.png)
## Room details interface after clicking the "Add" button:
![image](https://user-images.githubusercontent.com/65931654/177095432-f35703fb-9a99-4033-af38-c30f12a42a5d.png)
## The detailed interface of the inn when the state is "ROOM AVAILABLE" and "BOOKED": 
![image](https://user-images.githubusercontent.com/65931654/177095558-01a2ac10-c1a9-4aad-96de-5bd7f63c6c1d.png)
## Room repair interface:When you click the "Edit" button, the interface to edit the inn will appear. We will edit the content we need and want to edit. And then we click the button “Upload” the content will be updated again with the content we just edited
![image](https://user-images.githubusercontent.com/65931654/177095715-f8bf64a7-c3e3-4618-b1ab-409dcb4de415.png)
## Interface to delete the inn: When the innkeeper clicks the “Delete” button, the system will ask the innkeeper if he wants to delete the room. If the landlord wants to delete, select “Delete” or vice versa, “No”.
![image](https://user-images.githubusercontent.com/65931654/177095912-8e9f62e3-9222-4969-b0fc-2c6113e92809.png)
## Stats interface: Statistics of the inn in 3 states: room available, booked, out of room. Statistics for the innkeeper to know the number of each of his room status is how much
![image](https://user-images.githubusercontent.com/65931654/177096039-7d2c375b-afc6-4766-a8f6-a74916129aed.png)
## Admin main page interface: Here admin has 4 main functions: manage email list, register innkeeper, manage list of innkeepers and finally log out from the admin page.
![image](https://user-images.githubusercontent.com/65931654/177096516-a79193bd-def4-45f6-9bed-0e7ad06368f3.png)
## Email list interface: Displays the inn name and the email corresponding to that inn. Each inn will have a name and an email assigned by the Admin for use.
![image](https://user-images.githubusercontent.com/65931654/177096836-46e31659-6857-436a-a422-62fd4401bdb9.png)
## Interface to delete email and landlord's name: When we press and hold an email and a certain host name for about 3 seconds, a dialog box will appear asking if we want to delete the innkeeper or not. If you want to delete, select “Yes” otherwise, select “No”.
![image](https://user-images.githubusercontent.com/65931654/177097403-2bb0eedb-bba6-4e6f-a994-183b74b186e6.png)
## Landlord registration interface for each inn: The first interface is to register for an innkeeper account. Admin must enter full email and password information to create for the innkeeper. If you enter the wrong email format, you will receive an error message. If you enter one of the above two missing, it will also report an error. Complete and correct input is required. In this interface, the Admin performs 2 consecutive functions: both register an email for the innkeeper and then at the same time create an inn for the innkeeper.
## ++ Account registration interface for innkeepers
![image](https://user-images.githubusercontent.com/65931654/177098664-3a5f59b3-3db0-4412-85ef-20b8ac63ef4c.png)
## ++ Interface for adding inns: The second interface after registering an account is the interface to add an inn. Admin must add full and correct inn name and inn picture.
![image](https://user-images.githubusercontent.com/65931654/177098885-38c33deb-76d4-43cf-9598-d27371610b7c.png)
## Hostel list interface: Here, there are full functions of the innkeeper: add, edit, delete rooms of the inn. And there is one more function that deletes the whole inn. When clicking on any inn, it will show all the rooms that the inn has added. Admin can perform the functions of adding, editing and deleting rooms as the owner's rights
![image](https://user-images.githubusercontent.com/65931654/177099085-9707abe9-fadf-4673-8c56-7a273674cd92.png)
## Interface to delete the inn:
![image](https://user-images.githubusercontent.com/65931654/177099186-e331d037-d8b6-4085-b307-1091b4a2e9f7.png)
## Admin logout interface: When on the main page of the Admin, we click on the logout interface, the system will ask if the Admin wants to log out or not. If desired, select “Yes” otherwise, select “No”.
![image](https://user-images.githubusercontent.com/65931654/177099367-2949b5fe-aabd-4bc1-8c0f-ea00578ee5ad.png)
