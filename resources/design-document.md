# Design Document

## 1. Problem Statement

Ascend Nashville is a premier rock climbing gym providing world class climbing walls, fitness classes, and youth league climbing. In order to provide the best customer experience, Ascend will provide a web application available for member/employee access to maintain account information, track climbing progress, along with the ability to view current available routes and ratings.

This design document describes Ascend's new web application that will provide the functionality required to promote a good customer experience. 

## 2. Top Questions to Resolve in Review

Q. What member data will be in the user profile?

A. Membership Id and demographic information

Q. What data will be generated and stored?

A. Membership profiles, list of all memberships (employee), and current climbing routes with difficulty ratings. 

Q. Where will member data come from?

A. Member/Employee input when creating their accounts. Users will also be able to edit their profiles. 

Q. How will member/employee accounts be created?

A. Account will be created using the Amazon Cognito service. Employee and Member accounts will require separate landing pages. 

## 3. Use Cases

U1. As a customer, I want to be able to create an account in order to have access to submit my profile information.

U2. As a customer, I want to be able to create profile information within my account to become a member.

U3. As a customer, I want to be able to edit my profile information in the case that the information changes.

U4. As a customer, I want to be able to view current climbing routes and difficulty ratings to determine which routes to climb.

U5. As a customer, I want to be able to track which routes I have completed to track my progress.

u6. As a customer, I want to be able to view Ascend's current calendar of events to see if I want to participate.

U7. As an employee, I want to be able to create an account in order to access the business side of the webpage.

u8. As an employee, I want to be able to view member profiles in the case a customer needs assistance with their profile.

u9. As an employee, I want to be able to view all current members to see the total members of our gym.

u10. As an employee, I want to be able to create and display current routes available to our members.  

u11. As an employee, I want to be able to view all current routes to ensure accuracy for member viewing.

u12. As an employee, I want to be able to update any route as the routes change or if a mistake is made.

U13. As an employee, I want the ability to create a calendar of events for member viewing. 

U14. As an employee, I want to be able to update a calendar event for member viewing.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating member/employee profile information
* Creating, retrieving, and updating climbing routes
* Creating, retrieving, and updading the calendar of events
* Employee ability to retrieve all members

### 4.2. Out of Scope

* Deleting members/employees
* Community Chat/Blog feature
* Ability for members to rate current routes for all members to view
* Ability for employee/members to view billing information

## 5. Proposed Architecture Overview

The initial iteration will include creating, retrieving, and updating employees/members. This iteration will also include creating, retrieving, and updating climbing routes as well as the business calendar of events. 

We will use API Gateway and Lambda to create the following endpoints: (GetMembers, CreateMembers, UpdateMembers), (GetRoutes, CreateRoutes, UpdateRoutes), (GetCalendar, CreateCalendar, UpdateCalendar). API Gateway and Lambda will handle the creation, update, and retrieval of members/employees, routes, and the calendar to satisfy our requirements. 

Member/Employee, climbing route, and calendar data will be stored in separate tables in DynamoDB. 

Member/Employee account creation and access will be handled using the Amazon Cognito service.

We will provide a web interface for users to navigate with the option to login. The main page will contain the calendar, a link to a separate routes page, and a login prompt with the option to create an account. Customers will be provided with a new page after creating their account that includes the option to input their profile information. Once created, they will be able to view profile details once logged in. 

![image](https://user-images.githubusercontent.com/66507929/214695579-07f1e4e4-5f86-480d-af4f-95d2ab4046c8.png)

![image](https://user-images.githubusercontent.com/66507929/214700132-387d74f2-596a-44a1-89fa-99d8c4e93019.png)

![image](https://user-images.githubusercontent.com/66507929/214724503-9ee8b22c-cdad-492b-bd21-cfacdbda4c8b.png)

## 6. API

### 6.1. Public Models

```
// MemberModel

String memberId;
String name;
Integer age;
String gender;
List<String> contactInfo; (email, phone number,address)
String type; (employee, member)
```

```
// RouteModel

Integer routeId;
String difficultyRating;
String type; (boulder, top rope)
```

```
// CalendarModel

String calendarId;
Date date;
String eventDetails;
```

### 6.2. Get Member Endpoint

* Accepts `GET` requests to `/members/:memberId`
* Accepts a member ID and returns the corresponding MemberModel including memberId, name, age, gender, contactInfo, type.
    * If member ID is not found, throws `MemberNotFoundException`

![image](https://user-images.githubusercontent.com/66507929/214466714-0fabb98e-4568-4cfe-b354-f77f24765487.png)

### 6.3. Create Member Endpoint

* Accepts `POST` requests to `/members`
* Accepts data to create a new member with a provided a name, member ID, age, gender, contact information, and type. Returns the new MemberModel, including the           customers assigned unique member Id, name, age, gender, contactInfo, type.

![image](https://user-images.githubusercontent.com/66507929/214467402-331bcaa2-1bec-46b8-b5da-701f0d730cdd.png)

### 6.4. Update Member Endpoint

* Accepts `PUT` requests to `/members/:memberId`
* Accepts data to update a member including member name, age, gender, and contact information associated with the specific member. Returns the updated MemberModel       including memberId, name, age, gender, contactInfo, type.
    * if the member ID is not found, throws `MemberNotFoundException`

![image](https://user-images.githubusercontent.com/66507929/214468023-7ecbfde0-19f5-45b1-ae34-f7d4cf3a8c90.png)

### 6.5. Get Route Endpoint

* Accepts `GET` requests to `/routes/:routeId`
* Accepts a route ID and returns the corresponding RouteModel with routeID, difficulty rating, and type.
    * If route ID is not found, throws `RouteNotFoundException`

![image](https://user-images.githubusercontent.com/66507929/214468738-07c8be46-0d38-49e1-a25d-9df81f5f09eb.png)

### 6.6. Create Route Endpoint

* Accepts `POST` requests to `/routes`
* Accepts data to create a new route provided a route ID, difficulty rating, and route type. Returns the new RouteModel with route ID, difficulty rating, and type.

### 6.7. Update Route Endpoint

* Accepts `PUT` requests to `routes/:routeId`
* Accepts data to update a route including difficulty rating, and route type. Returns the updated RouteModel with route ID, difficulty rating, and type.

### 6.8. Get Calendar Endpoint

* Accepts `GET` reqeusts to `calendar/:calendarId`
* Accepts a calendar ID and returns the corresponding CalendarModel including calendar ID, date, and event details.
     * If calendar ID is not found, throws `CalendarNotFoundException`

![image](https://user-images.githubusercontent.com/66507929/214469089-0140dac0-ce07-4548-8662-1ed12ef1149e.png)

### 6.9. Create Calendar Endpoint

* Accepts `POST` requests to `/calendar`
* Accepts data to create a new calendar including calendar ID, day, month, year, and event details. Returns the corresponding CalendarModel including calendar ID,       date, and event details.

### 6.10. Update Calendar Endpoint

* Accepts `PUT` requests to `calendar/:calendarid`
* Accepts data to update the calendar including day, month, year, and event details. Returns the corresponding CalendarModel including calendar ID, date, and event       details.

## Tables

### 7.1. `members`

```
memberid // partition key, string
name // string
age // number
gender // string
contactInfo // list
type // string
```

### 7.2. `routes`

```
routeId // partition key, number
difficultyRating // string
type // string
```
### 7.3. `calendar`

```
calendarId // partition key, string
day // number
month // string
year // number
eventDetails // string
```

## 8. Pages

### Member Views

![image](https://user-images.githubusercontent.com/66507929/214391575-044337b6-9e60-4f8e-b8ed-0a4310cf9877.png)
![image](https://user-images.githubusercontent.com/66507929/214393100-0f03ad6a-91ab-400b-b396-eb7fa15a40e0.png)
![image](https://user-images.githubusercontent.com/66507929/214395411-50c7e264-7543-498e-8944-73a289766e5e.png)
![image](https://user-images.githubusercontent.com/66507929/214410304-9f0330c5-fdc2-4c73-a583-13969930f082.png)

### Employee Views

![image](https://user-images.githubusercontent.com/66507929/214414859-17195d7b-1551-4313-acb9-d4398c1f50d7.png)
![image](https://user-images.githubusercontent.com/66507929/214397587-6259f527-3a9b-4980-8684-d6d5985ef239.png)
![image](https://user-images.githubusercontent.com/66507929/214406192-c908f4ae-c72f-48bf-8724-41d8372839eb.png)
![image](https://user-images.githubusercontent.com/66507929/214408028-6dfb5fab-42e6-46da-aa97-ad62c3f0af7d.png)
