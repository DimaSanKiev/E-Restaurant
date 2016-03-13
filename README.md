# E-Restaurant

**Summary:** users order dishes to their addresses and get it delivered after some time. 

### Customer
When customers go to main page they see the menu. They can choose dishes by category of full dishes list. Here they fill up their shopping cart when choose dish they want to order.
![alt text](https://raw.githubusercontent.com/DimaSanKiev/E-Restaurant/master/files/description/use-case-diagram1.jpg "Logo Title Text 1")

### Order
After customers finish with choosing dishes, they can specify its quantity on the shopping cart page. They can also edit or confirm their order there. Then every kitchen-made dish goes to the kitchen.

### Kitchen & Delivery Staff
Kitchen Staff have access to kitchen pending screen page where they see all the dishes waiting to be prepared. Just when they cook it they mark it as ‘done’ and this dish disappear from kitchen pending screen.

### Administrator
There is an administrator in ERestaurant. Administrator's duty is managing the menu - to add new dishes, edit existing ones, set prices, mark dishes as available/unavailable.
Administrator also can manage customers and block them.

### Business Analyst
Another employee role in ERestaurant is Business Analyst. He makes reports about restaurant statistics - how many dishes were sold, what is the most profitable dish category and the total income of specified period of time.

### Super User
The Super User is the master of the restaurant. He hires and dismisses employees. There is only one Super User in ERestaurant system.

## Project Architecture
My application is flexible and reusable because it is based on three-tier architecture:
- *Presentation layer*,
- *Business layer* and
- *Persistence layer*.

In Persistence layer is used **SQL** for modelling data and creating DB structure.
Persistence layer cooperate with Business layer with **JPA**, **JP QL** and **EclipseLink**.

In Business layer **Spring Framework** is used as dependency injection and inversion of control container. It collaborates with Persistence layer and Presentation layer directly. Here **DAO pattern** is used to provide some specific data operations without exposing details of the database.

In Presentation layer such technologies as **JSF**, **PrimeFaces**, **HTML** and **CSS** are used.

Project is written in **Java 8** and built with **Maven**. It runs under lightweight **Jetty** HTTP Web Server, but can be easily replaced to Tomcat or any other Web Server.
I tested my service methods with **JUnit** and used **Log4J** as a logging tool.

## Database Architecture
Here is the structure of the database.
Main tables are **Customer**, **Employee**, **Orders**, **Dish** and **OrderDishes** as a linking table.
**Role**, **OrderStatus** and **DishCategory** are subsidiary tables.
Here is shown only the main fields and relations.