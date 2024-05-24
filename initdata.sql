

-- Customers
INSERT INTO Customers (name, phone) VALUES 
('John', '1234567890'),
('Jane', '0987654321'),
('Mike', '1122334455'),
('Emily', '5566778899'),
('Anna', '6677889900');

-- Products
INSERT INTO Products (product_name, price, category) VALUES 
('Classic Burger', 5.99, 'Burger'),
('Cheese Burger', 6.99, 'Burger'),
('Chicken Burger', 6.49, 'Burger'),
('Fries', 2.49, 'Side'),
('Coke', 1.99, 'Drink'),
('Pepsi', 1.99, 'Drink'),
('Salad', 3.99, 'Side');

-- Orders
INSERT INTO Orders (customer_id, order_date) VALUES 
(1, '2024-05-24 12:00:00'),
(2, '2024-05-24 12:05:00'),
(3, '2024-05-24 12:10:00'),
(4, '2024-05-24 12:15:00'),
(5, '2024-05-24 12:20:00');

-- OrderItems
INSERT INTO OrderItems (order_id, product_id, quantity, item_price) VALUES 
(1, 1, 1, 5.99),
(1, 4, 1, 2.49),
(1, 5, 1, 1.99),
(2, 2, 1, 6.99),
(2, 5, 1, 1.99),
(3, 3, 1, 6.49),
(4, 1, 1, 5.99),
(4, 6, 1, 1.99),
(4, 7, 1, 3.99),
(5, 2, 1, 6.99),
(5, 4, 1, 2.49),
(5, 6, 1, 1.99);