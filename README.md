# Burger Restaurant Management System

## Introduction
This Java application manages a burger restaurant's orders, customers, and products. It allows users to insert new orders, query data using different criteria, update customer information, and delete orders. The application uses JDBC to interact with a MySQL database.

## Features
- Insert new orders into the database.
- Query data with joins and views.
- Query data with aggregation and group by.
- Update customer information.
- Delete orders by order ID.

## Classes and Their Functions

### 1. `InsertOrder`
This class handles inserting new orders into the database.

**Functions:**
- `insertNewOrder()`: Inserts a new order into the `Orders` and `OrderItems` tables. It prompts the user to input customer ID, product IDs, quantities, and automatically fetches the current date and time for the order.

### 2. `SelectData`
This class provides two query functionalities: one using a join and view, and the other using aggregation and group by.

**Functions:**
- `queryWithJoinAndView(String customerName)`: Queries the database to find orders by a specific customer name, using a join with the `OrderSummary` view and `Customers` table.
- `queryWithAggregationAndGroupBy(String productName)`: Queries the database to find the total quantity sold for a specific product name.

### 3. `UpdateData`
This class handles updating customer information.

**Functions:**
- `updateCustomerInfo(int customerId)`: Updates a customer's name and phone number in the `Customers` table based on the given customer ID.

### 4. `DeleteData`
This class handles deleting orders from the database.

**Functions:**
- `deleteOrder(int orderId)`: Deletes an order from the `Orders` and `OrderItems` tables based on the given order ID.

## Main Class: `Main`

The `Main` class serves as the entry point to the application. It provides a menu for users to select various operations.

**Functions:**
- `main(String[] args)`: Provides a console-based menu to perform insert, query, update, and delete operations.

## How to Use the Program

### Prerequisites
- Java Development Kit (JDK) installed
- MySQL database set up with the necessary tables and views
- JDBC driver for MySQL

### Database Setup
Ensure the following tables and views exist in your MySQL database:

#### Tables
```sql
CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATETIME NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE OrderItems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
```

## View
```sql
CREATE VIEW OrderSummary AS
SELECT 
    o.order_id,
    c.name AS customer_name,
    o.order_date,
    SUM(oi.quantity * oi.item_price) AS total_amount,
    GROUP_CONCAT(p.product_name ORDER BY p.product_name SEPARATOR ', ') AS product_list
FROM Orders o
JOIN Customers c ON o.customer_id = c.customer_id
JOIN OrderItems oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY o.order_id, c.name, o.order_date;
```

# Compiling the Code

Open a terminal and navigate to your project directory.

Compile the Java files:

```sh
javac -d bin src/*.java
```

# Creating the JAR File

Navigate to the project directory:

```sh
cd /path/to/your/project
```

Create the JAR file:

```sh
jar cfe BurgerRestaurant.jar Main -C bin .
```

# Running the Program

Execute the JAR file:

```sh
java -jar BurgerRestaurant.jar
```

# Menu Options

- **Insert New Order:** Adds a new order to the database.
- **Query with Join and View:** Queries orders by customer name using a join and view.
- **Query with Aggregation and Group By:** Queries total quantity sold for a specific product.
- **Update Customer Information:** Updates the name and phone number of a customer.
- **Delete Order:** Deletes an order by order ID.

## Example Usage

### Insert a New Order

1. Run the program.
2. Select option 1.
3. Follow the prompts to input the customer ID, product IDs, and quantities.

### Query Orders by Customer Name

1. Run the program.
2. Select option 2.
3. Enter the customer name.

### Query Total Quantity Sold for a Product

1. Run the program.
2. Select option 3.
3. Enter the product name.

### Update Customer Information

1. Run the program.
2. Select option 4.
3. Enter the customer ID and follow the prompts to update the name and phone number.

### Delete an Order

1. Run the program.
2. Select option 5.
3. Enter the order ID.

## Notes

- Ensure your MySQL database is running and accessible.
- Update the database connection details (DB_URL, USER, PASS) in each class as needed.





