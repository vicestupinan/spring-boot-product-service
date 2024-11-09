CREATE TABLE category (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    category_id UUID REFERENCES category(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_variant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID REFERENCES product(id),
    color VARCHAR(255) NOT NULL,
    size VARCHAR(255) NOT NULL,
    stock INTEGER NOT NULL,
    price BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO category (name) VALUES
    ('Electronics'),
    ('Books'),
    ('Clothing'),
    ('Home & Kitchen'),
    ('Sports & Outdoors');

INSERT INTO product (name, category_id) VALUES
    ('Smartphone', (SELECT id FROM category WHERE name = 'Electronics')),
    ('Laptop', (SELECT id FROM category WHERE name = 'Electronics')),
    ('Fantasy Novel', (SELECT id FROM category WHERE name = 'Books')),
    ('Cookbook', (SELECT id FROM category WHERE name = 'Books')),
    ('T-shirt', (SELECT id FROM category WHERE name = 'Clothing')),
    ('Jeans', (SELECT id FROM category WHERE name = 'Clothing')),
    ('Blender', (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    ('Microwave', (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    ('Tennis Racket', (SELECT id FROM category WHERE name = 'Sports & Outdoors')),
    ('Camping Tent', (SELECT id FROM category WHERE name = 'Sports & Outdoors'));

INSERT INTO product_variant (product_id, color, size, stock, price) VALUES
    ((SELECT id FROM product WHERE name = 'Smartphone'), 'Black', '64GB', 100, 69999),
    ((SELECT id FROM product WHERE name = 'Smartphone'), 'Silver', '128GB', 50, 79999),
    ((SELECT id FROM product WHERE name = 'Laptop'), 'Gray', '15-inch', 80, 119999),
    ((SELECT id FROM product WHERE name = 'Laptop'), 'Black', '13-inch', 60, 99999),
    ((SELECT id FROM product WHERE name = 'Fantasy Novel'), 'N/A', 'N/A', 200, 1999),
    ((SELECT id FROM product WHERE name = 'Fantasy Novel'), 'N/A', 'N/A', 150, 2999),
    ((SELECT id FROM product WHERE name = 'Cookbook'), 'N/A', 'N/A', 120, 1599),
    ((SELECT id FROM product WHERE name = 'T-shirt'), 'Red', 'M', 200, 1999),
    ((SELECT id FROM product WHERE name = 'T-shirt'), 'Blue', 'L', 150, 2199),
    ((SELECT id FROM product WHERE name = 'Jeans'), 'Blue', 'M', 100, 2499),
    ((SELECT id FROM product WHERE name = 'Jeans'), 'Black', 'L', 120, 2799),
    ((SELECT id FROM product WHERE name = 'Blender'), 'Black', 'Standard', 75, 4999),
    ((SELECT id FROM product WHERE name = 'Blender'), 'White', 'Premium', 50, 7999),
    ((SELECT id FROM product WHERE name = 'Microwave'), 'Silver', 'Medium', 60, 5999),
    ((SELECT id FROM product WHERE name = 'Microwave'), 'Black', 'Large', 40, 7999),
    ((SELECT id FROM product WHERE name = 'Tennis Racket'), 'Red', 'Standard', 150, 2499),
    ((SELECT id FROM product WHERE name = 'Tennis Racket'), 'Blue', 'Pro', 100, 3499),
    ((SELECT id FROM product WHERE name = 'Camping Tent'), 'Green', '4-person', 80, 7999),
    ((SELECT id FROM product WHERE name = 'Camping Tent'), 'Blue', '6-person', 60, 9999);