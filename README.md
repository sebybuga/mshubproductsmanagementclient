# hubproductsmanagement
An API that acts as a product hub management tool. The store components has been simplified. The application is a demo showing API development skills.

Prerequisites: Java JDK 17, Maven 3.X.X

Installation steps:
1. Clone repository https://github.com/sebybuga/hubproductsmanagement.git
2. Install MySQL 8.3.0
3. Create DB user hubproductsmanagement as defined in src\main\resources\application.properties.
4. Create new database hubproductsmanagement with collation utf32_bin and assign full privileges to the created user.
5. Build sources with Maven (clean, install).
6. Start microservice. Migrations should create and populate tables in hubproductsmanagement database. 
7. Import Postman collection src\main\resources\postman\Hub_Products_Management.postman_collection.json.

