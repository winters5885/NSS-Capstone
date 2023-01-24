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

U1. As a customer, I want to be able to create an account.

U2. As a customer, I want to be able to create create profile information within my account.

U3. As a customer, I want to be able to edit my profile information.

U4. As a customer, I want to be able to view current climbing routes and difficulty ratings.

U5. As a customer, I want to be able to track which routes I have completed.

u6. As a customer, I want to be able to view Ascend's current calendar of events.

U7. As an employee, I want to be able to create an account.

u8. As an employee, I want to be able to view all current members.

U9. As an employee, I want to be able to view member profiles.

U10. As an employee, I want to be able to set and display the current routes and difficulty for member viewing.

U11. As an employee, I want the ability to create a calendar of eveents for member viewing. 

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

## 6. API

### 6.1. Public Models

```
// MemberModel

String memberId;
String name;
Integer age;
String gender;
List<String> contactInfo;
String type;
```

```
// RouteModel

Integer routeId;
String difficultyRating;
String type;
```

```
// CalendarModel

String calendarId;
Integer day;
String month;
Integer year;
String eventDetails;
```

### 6.2. Get Member Endpoint

* Accepts `GET` requests to `/members/:memberId`
* Accepts a member ID and returns the corresponding MemberModel.
    * If member ID is not found, throws `MemberNotFoundException`

### 6.3. Create Member Endpoint

* Accepts `POST` requests to `/members`
* Accepts data to create a new member with a provided a name, member ID, age, gender, contact information, and type. Returns the new member profile, including the       customers assigned unique member Id.

### 6.4. Update Member Endpoint

* Accepts `PUT` requests to `/members/:memberId`
* Accepts data to update a member including member name, age, gender, and contact information associated with the specific member. Returns the updated member profile.
    * if the member ID is not found, throws `MemberNotFoundException`

### 6.5. Get Route Endpoint

* Accepts `GET` requests to `/routes/:routeId`
* Accepts a route ID and returns the corresponding RouteModel.
    * If route ID is not found, throws `RouteNotFoundException`

### 6.6. Create Route Endpoint

* Accepts `POST` requests to `/routes`
* Accepts data to create a new route provided a route ID, difficulty rating, and route type. Returns the new route.

### 6.7. Update Route Endpoint

* Accepts `PUT` requests to `routes/:routeId`
* Accepts data to update a route including difficulty rating, and route type. Returns the updated route.

### 6.8. Get Calendar Endpoint

* Accepts `GET` reqeusts to `calendar/:calendarId`
* Accepts a calendar ID and returns the corresponding CalendarModel.
     * If calendar ID is not found, throws `CalendarNotFoundException`

### 6.9. Create Calendar Endpoint

* Accepts `POST` requests to `/calendar`
* Accepts data to create a new calendar including calendar ID, day, month, year, and event details.

### 6.10. Update Calendar Endpoint

* Accepts `PUT` requests to `calendar/:calendarid`
* Accepts data to update the calendar including day, month, year, and event details.

