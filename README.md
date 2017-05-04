# E-Restaurant

**Deployed:** https://erestaurant.herokuapp.com/

**Summary:** users choose dishes, make orders to their addresses and get them delivered after some time. ERestaurant workers with different roles
serve them.

## Description
### Customer
When customers go to main page they see the menu. They can choose dishes by selecting from dish category or from the full dishes list. 
Here they fill up their shopping cart by choosing dish they want to order.
<p align="center">
 <img alt="Customer use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16852436/cfee8ee8-4a10-11e6-8c3b-72ca1370e0ff.png width=700 />
</p>
Customers can also update their personal info (like name, delivering address, birthdate) by clicking on their name in the top
right corner.

### Order
After customers finish with choosing dishes, they can specify its quantity on the shopping cart page. They can also edit or confirm 
their order there if they found any mistakes in delivering address etc. When they confirm their order they have only to wait for 
delivery staff with their dishes.

 At the same time when customer confirms order every kitchen-made dish goes to the kitchen and waiting to be done by kitchen staff 
and all non-kitchen-made dishes skip kitchen and drop directly to the order.
<p align="center">
 <img alt="Order use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16852466/f1f77c52-4a10-11e6-8f73-0c350bd7f692.png />
</p>
 When the order consists of only kitchen-done dishes it has *'KITCHEN_DONE'* status, when it consists of both kitchen-done and
non-kitchen-done dishes its status is set to *'NON-KITCHEN_DONE'*.

### Kitchen & Delivery Staff
Kitchen Staff have access to **kitchen pending screen** page where they view all the dishes waiting to be prepared. Just when they cook it 
they mark it as ‘done’ with the corresponding button and this dish disappears from kitchen pending screen.
<p align="center">
 <img alt="Kitchen & Delivery Staff use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16852543/398cb776-4a11-11e6-8028-129e5067b7ee.png width=700 />
</p>
When all the kitchen-done dishes from the same order are ready the status of the order changes to *'READY_FOR_SHIPMENT'* and order 
appears in the **delivery pending screen** that is available to Delivery Staff.

 Then Delivery Staff takes it for shipping and changes its status to *'DELIVERING'*. After they come back with money they set that order status to *'DELIVERED'*, it disappears
from delivery pending screen and become available to Business Analysts for accounting.

### Administrator
There is an administrator in ERestaurant. Administrator's duty is managing the menu - to add new dishes and set them an images, edit 
existing ones, set and update prices, mark dishes as available/unavailable.
Administrator also can manage customers and block/unblock them.
<p align="center">
 <img alt="Administrator use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16851919/5dec5fe8-4a0e-11e6-842a-bbbb36ec66f5.png width=500 />
</p>

### Business Analyst
Another employee role in ERestaurant is Business Analyst. He generates 3 different types of reports about restaurant statistics 
during the specified period of time – how many dishes were sold, what is the most profitable dish category and the total income.
<p align="center">
 <img alt="Business Analyst use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16852070/1924da6a-4a0f-11e6-9413-8bd01f60316b.png width=500 />
</p>

### Super User
The Super User is the master of the restaurant.
 He hires and dismisses employees (by blocking them). He can view the customers list, block/unblock them and can also delete them permanently from system. 
He can view the dishes list (but not add new or edit existing dishes) as well as generate different reports to see the restaurant statistics.
<p align="center">
 <img alt="Super User use case diagram" src=https://cloud.githubusercontent.com/assets/11503436/16852645/9e0d445e-4a11-11e6-81af-b07efcccb33f.png width=500 />
</p>
 There is only one Super User in ERestaurant system, his role couldn't be changed and there is no possibility to add another employee 
with the role of Super User.

</br>
## Project Architecture
My application is flexible and reusable because it is based on three-tier architecture:
- *Presentation layer*,
- *Business layer* and
- *Persistence layer*.
<p align="center">
 <img alt="Project structure diagram" src=https://cloud.githubusercontent.com/assets/11503436/16853194/1a5b80c8-4a14-11e6-860f-37db835b54e5.png width=700 />
</p>

In Persistence layer **SQL** is used for inserting *Customer*, *Employee*, *Dish*, *Role*, *Photo*, *DishCategory*, *OrderStatus* 
and *Dish* entities. That data is inserted with **`db/ddl_h2.sql`** script.
Persistence layer cooperate with Business layer using **JPA**, **HQL** and **Hibernate**.

In Business layer **Spring Framework** is used as dependency injection and inversion of control container. It collaborates with 
Persistence layer and Presentation layer directly. Here **DAO pattern** is used to provide some specific data operations without 
exposing details of the database. In **Service Layer** the main application's business logic is implemented.

In Presentation layer such technologies as **JSF**, **PrimeFaces**, **HTML** and **CSS** are used. **JSF** collaborates with 
**Service Layer** via **Managed Beans**.

Project is written in **Java 8** and built with **Maven**. It runs under lightweight **Jetty** HTTP Web Server, but can be easily 
replaced to Tomcat or any other Web Server.
I tested my service methods with **JUnit** and used **Log4J** as a logging tool.


## Database Architecture
Here is the simplified structure of the database:
<p align="center">
 <img alt="Database structure diagram" src=https://cloud.githubusercontent.com/assets/11503436/16853481/2a75409c-4a15-11e6-931b-9af95045457f.png width=700 />
</p>

Main tables are **Customer**, **Employee**, **Orders**, **Dish** and **OrderDishes** (as a linking table).
**Photo**, **Role**, **OrderStatus** and **DishCategory** are subsidiary tables.

 On this diagram are shown only the main fields and relations. Detailed database schema are available here: `files/db_schema.png`.

## How to run
1. Clone this repository or download sources as ZIP archive.
2. Call from the command line:

    `mvn compile`

3. Start H2 database in Server mode:

    `java -cp files/h2/h2-1.4.192.jar org.h2.tools.Server`

4. Run a webapp with Jetty plugin: 

    `mvn jetty:run`

5. Go to [http://localhost:8080/](http://localhost:8080/) in your browser.

## Accounts
### Customers
Some registered **_customers' accounts_**:
+ olga.romanova@gmail.com:pass1
+ igor.shevchenko@yahoo.com:pass2
+ kate.belova@gmail.com:pass3

### Employees
**_Employees signing-in form_** is available on different URL https://erestaurant.herokuapp.com/employeeSignIn.xhtml or by link from the main page:
<p align="center">
 <img alt="Employee Login Link Button" src=https://cloud.githubusercontent.com/assets/11503436/16853714/3fc5f058-4a16-11e6-87ec-b99697361d69.png width=800 />
</p>
+ **Super User** super@erestaurant.com:pass1
+ **Administrator** admin@erestaurant.com:pass2
+ **Kitchen Staff** kitchen@erestaurant.com:pass3
+ **Delivery Staff** delivery@erestaurant.com:pass4
+ **Business Analyst** business@erestaurant.com:pass5

#### Reports
Using on **https://erestaurant.herokuapp.com/superPanel.xhtml** to see reports diagrams with some test data (*that are available to* ***Business Analyst*** *and* ***Super User*** *roles*) you can use interval from **10.07.2016** to **19.07.2016**:

<p align="center">
 <img alt="Report Test Interval" src=https://cloud.githubusercontent.com/assets/11503436/17001343/4de5f3aa-4ecf-11e6-9202-fea40638deeb.png width=400 />
</p>

Using on **[http://localhost:8080/reports.xhtml](http://localhost:8080/reports.xhtml)** just input *9 days before current date* for **Start Date** and *current date* for **End Date**.
