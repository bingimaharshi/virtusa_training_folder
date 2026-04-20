-- ============================================
-- Online Retail Sales Analysis Database
-- ============================================

CREATE DATABASE IF NOT EXISTS retail_sales;
USE retail_sales;

CREATE TABLE IF NOT EXISTS Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(80) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE IF NOT EXISTS Order_Items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

INSERT INTO Customers (name, city) VALUES
('Arjun Sharma', 'Mumbai'),
('Priya Nair', 'Bangalore'),
('Rahul Gupta', 'Delhi'),
('Sneha Reddy', 'Hyderabad'),
('Vikram Singh', 'Chennai'),
('Anjali Mehta', 'Pune'),
('Karthik Rao', 'Kolkata'),
('Divya Iyer', 'Ahmedabad'),
('Rohit Joshi', 'Jaipur'),
('Meena Pillai', 'Mumbai');

INSERT INTO Products (name, category, price) VALUES
('Wireless Earbuds', 'Electronics', 2499.00),
('Running Shoes', 'Footwear', 3999.00),
('Organic Green Tea', 'Grocery', 349.00),
('Yoga Mat', 'Sports', 899.00),
('Laptop Stand', 'Electronics', 1599.00),
('Cotton Kurta', 'Clothing', 799.00),
('Face Moisturizer', 'Beauty', 549.00),
('Stainless Water Bottle', 'Kitchen', 449.00),
('Novel - Midnight Library', 'Books', 399.00),
('Bluetooth Speaker', 'Electronics', 4499.00),
('Protein Powder', 'Health', 1899.00),
('Desk Lamp', 'Home Decor', 1199.00);

INSERT INTO Orders (customer_id, order_date) VALUES
(1, '2024-01-05'), (2, '2024-01-12'), (3, '2024-01-18'),
(4, '2024-02-02'), (5, '2024-02-14'), (1, '2024-02-20'),
(6, '2024-02-28'), (7, '2024-03-05'), (8, '2024-03-10'),
(2, '2024-03-15'), (9, '2024-03-20'), (10, '2024-03-25'),
(3, '2024-04-01'), (4, '2024-04-08'), (5, '2024-04-15'),
(1, '2024-04-22'), (6, '2024-05-01'), (7, '2024-05-10'),
(8, '2024-05-18'), (2, '2024-05-25');

INSERT INTO Order_Items (order_id, product_id, quantity) VALUES
(1, 1, 1), (1, 5, 1),
(2, 2, 1), (2, 4, 2),
(3, 10, 1),
(4, 7, 2), (4, 8, 3),
(5, 3, 4), (5, 9, 1),
(6, 11, 1), (6, 12, 1),
(7, 1, 2),
(8, 6, 3), (8, 7, 1),
(9, 5, 1), (9, 4, 1),
(10, 2, 1), (10, 10, 1),
(11, 9, 2), (11, 3, 5),
(12, 8, 2), (12, 12, 1),
(13, 11, 2),
(14, 1, 1), (14, 6, 2),
(15, 3, 3), (15, 7, 1),
(16, 10, 1),
(17, 4, 1), (17, 5, 1),
(18, 2, 2),
(19, 12, 2), (19, 9, 1),
(20, 1, 1), (20, 11, 1);

-- ============================================
-- ANALYTICAL QUERIES
-- ============================================

-- 1. Top Selling Products by Quantity Sold
SELECT
    p.name AS product_name,
    p.category,
    SUM(oi.quantity) AS total_units_sold,
    SUM(oi.quantity * p.price) AS total_revenue
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.product_id, p.name, p.category
ORDER BY total_units_sold DESC
LIMIT 5;

-- 2. Most Valuable Customers by Total Spend
SELECT
    c.customer_id,
    c.name AS customer_name,
    c.city,
    COUNT(DISTINCT o.order_id) AS total_orders,
    SUM(oi.quantity * p.price) AS total_spend
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.customer_id, c.name, c.city
ORDER BY total_spend DESC
LIMIT 5;

-- 3. Monthly Revenue Calculation
SELECT
    DATE_FORMAT(o.order_date, '%Y-%m') AS month,
    COUNT(DISTINCT o.order_id) AS total_orders,
    SUM(oi.quantity * p.price) AS monthly_revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY month;

-- 4. Category-wise Sales Analysis
SELECT
    p.category,
    COUNT(DISTINCT oi.order_id) AS orders_containing_category,
    SUM(oi.quantity) AS total_units_sold,
    SUM(oi.quantity * p.price) AS category_revenue,
    ROUND(AVG(p.price), 2) AS avg_product_price
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.category
ORDER BY category_revenue DESC;

-- 5. Inactive Customers (No orders in last 60 days)
SELECT
    c.customer_id,
    c.name,
    c.city,
    MAX(o.order_date) AS last_order_date,
    DATEDIFF(CURDATE(), MAX(o.order_date)) AS days_since_last_order
FROM Customers c
LEFT JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name, c.city
HAVING last_order_date IS NULL OR DATEDIFF(CURDATE(), last_order_date) > 60
ORDER BY days_since_last_order DESC;

-- 6. Average Order Value per Customer
SELECT
    c.name,
    COUNT(DISTINCT o.order_id) AS total_orders,
    SUM(oi.quantity * p.price) AS total_spend,
    ROUND(SUM(oi.quantity * p.price) / COUNT(DISTINCT o.order_id), 2) AS avg_order_value
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.customer_id, c.name
ORDER BY avg_order_value DESC;

-- 7. Products Never Ordered
SELECT p.product_id, p.name, p.category, p.price
FROM Products p
LEFT JOIN Order_Items oi ON p.product_id = oi.product_id
WHERE oi.product_id IS NULL;

-- 8. Best Performing City by Revenue
SELECT
    c.city,
    COUNT(DISTINCT c.customer_id) AS customers,
    SUM(oi.quantity * p.price) AS total_revenue
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.city
ORDER BY total_revenue DESC;
