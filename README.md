# E-Restaurant

**Deployed:** https://erestaurant.herokuapp.com/

**Summary:** users order dishes to their addresses and get it delivered after some time. ERestaurant workers with different roles
serve them.

### Customer
When customers go to main page they see the menu. They can choose dishes by selecting dish category or full dishes list. 
Here they fill up their shopping cart by choosing dish they want to order.
![alt text](https://cloud.githubusercontent.com/assets/11503436/16852436/cfee8ee8-4a10-11e6-8c3b-72ca1370e0ff.png "Customer use case diagram")
Customers can also update their personal info (like name, delivering address, birthdate) by clicking on their name in the top
right corner.

### Order
After customers finish with choosing dishes, they can specify its quantity on the shopping cart page. They can also edit or confirm 
their order there if they found any mistakes in delivering address etc. When they confirm their order they have only to wait for 
delivery staff with their dishes.

 At the same time when customer confirms order every kitchen-made dish goes to the kitchen and waiting to be done by kitchen staff 
and all non-kitchen-made dishes skip kitchen and drops directly to the order.

 ![alt text](https://cloud.githubusercontent.com/assets/11503436/16852466/f1f77c52-4a10-11e6-8f73-0c350bd7f692.png "Order use case diagram")
 When the order consists of only kitchen-done dishes it has *'KITCHEN_DONE'* status, when it consists of both kitchen-done and
non-kitchen-done dishes its status is set to *'NON-KITCHEN_DONE'*.

### Kitchen & Delivery Staff
Kitchen Staff have access to **kitchen pending screen** page where they view all the dishes waiting to be prepared. Just when they cook it 
they mark it as ‘done’ with corresponding button and this dish disappear from kitchen pending screen.

 ![alt text](https://cloud.githubusercontent.com/assets/11503436/16852543/398cb776-4a11-11e6-8028-129e5067b7ee.png "Kitchen & Delivery Staff use case diagram")
When all the kitchen-done dishes from the same order are ready the status of the order changes to *'READY_FOR_SHIPMENT'* and order 
appears in the **delivery pending screen** that is available to Delivery Staff.

 Then Delivery Staff takes it for shipping and changes it status to *'DELIVERING'*. After they came back with money they set that order status to *'DELIVERED'* and it disappears
from delivery pending screen.

### Administrator
There is an administrator in ERestaurant. Administrator's duty is managing the menu - to add new dishes and set them an images, edit 
existing ones, set prices, mark dishes as available/unavailable.
Administrator also can manage customers and block/unblock them.
 
![alt text](https://cloud.githubusercontent.com/assets/11503436/16851919/5dec5fe8-4a0e-11e6-842a-bbbb36ec66f5.png "Administrator use case diagram")

### Business Analyst
Another employee role in ERestaurant is Business Analyst. He generates 3 different types of reports about restaurant statistics 
during the specified period of time – how many dishes were sold, what is the most profitable dish category and the total income.

 ![alt text](https://cloud.githubusercontent.com/assets/11503436/16852070/1924da6a-4a0f-11e6-9413-8bd01f60316b.png "Business Analyst use case diagram")

### Super User
The Super User is the master of the restaurant.
 He hires and dismisses employees (by blocking them). He can view the customer list, 
block/unblock them abd he can also delete them permanently from system. He can view the menu (but not add new or edit existing dishes) 
as well as generate different reports to see the restaurant statistics.

 ![alt text](https://cloud.githubusercontent.com/assets/11503436/16852645/9e0d445e-4a11-11e6-81af-b07efcccb33f.png "Super User use case diagram")
 There is only one Super User in ERestaurant system, his role couldn't be changed and there is no possibility to add another employee 
with the role of Super User.

# Project Architecture
My application is flexible and reusable because it is based on three-tier architecture:
- *Presentation layer*,
- *Business layer* and
- *Persistence layer*.
![alt text](https://cloud.githubusercontent.com/assets/11503436/16853194/1a5b80c8-4a14-11e6-860f-37db835b54e5.png "Project structure diagram")

In Persistence layer is used **SQL** for inserting *Customer*, *Employee*, *Dish*, *Role*, *Photo*, *DishCategory*, *OrderStatus* 
and *Dish* entities. That data is inserted with ***db/dml_h2.sq*** script.
Persistence layer cooperate with Business layer with **JPA**, **HQL** and **Hibernate**.

In Business layer **Spring Framework** is used as dependency injection and inversion of control container. It collaborates with 
Persistence layer and Presentation layer directly. Here **DAO pattern** is used to provide some specific data operations without 
exposing details of the database. In **Service Layer** the main business logic is implemented.

In Presentation layer such technologies as **JSF**, **PrimeFaces**, **HTML** and **CSS** are used. **JSF** collaborates with 
**Service Layer** using **Managed Beans**.

Project is written in **Java 8** and built with **Maven**. It runs under lightweight **Jetty** HTTP Web Server, but can be easily 
replaced to Tomcat or any other Web Server.
I tested my service methods with **JUnit** and used **Log4J** as a logging tool.


# Database Architecture
Here is the simplified structure of the database:

 ![alt text](https://cloud.githubusercontent.com/assets/11503436/16853481/2a75409c-4a15-11e6-931b-9af95045457f.png "Database structure diagram")

Main tables are **Customer**, **Employee**, **Orders**, **Dish** and **OrderDishes** (as a linking table).
**Photo**, **Role**, **OrderStatus** and **DishCategory** are subsidiary tables.

 Here is shown only the main fields and relations.

# Accounts
## Customers
Some registered **_customers' accounts_**:
+ olga.romanova@gmail.com:pass1
+ igor.shevchenko@yahoo.com:pass2
+ kate.belova@gmail.com:pass3

## Employees
**_Employees signing-in form_** is available on different URL https://erestaurant.herokuapp.com/employeeSignIn.xhtml or by link:
![alt text](https://cloud.githubusercontent.com/assets/11503436/16853714/3fc5f058-4a16-11e6-87ec-b99697361d69.png "Employee Login Link")
+ **Super User** super@erestaurant.com:pass1
+ **Administrator** admin@erestaurant.com:pass2
+ **Kitchen Staff** kitchen@erestaurant.com:pass3
+ **Delivery Staff** delivery@erestaurant.com:pass4
+ **Business Analyst** business@erestaurant.com:pass5
