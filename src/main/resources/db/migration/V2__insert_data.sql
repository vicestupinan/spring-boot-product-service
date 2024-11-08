INSERT INTO category (name) VALUES
    ('Electronics'),
    ('Books'),
    ('Clothing'),
    ('Home & Kitchen'),
    ('Sports & Outdoors');

INSERT INTO product (name, price, category_id) VALUES
    ('Smartphone', 69900, (SELECT id FROM category WHERE name = 'Electronics')),
    ('Laptop', 99900, (SELECT id FROM category WHERE name = 'Electronics')),
    ('Fantasy Novel', 1500, (SELECT id FROM category WHERE name = 'Books')),
    ('Cookbook', 2000, (SELECT id FROM category WHERE name = 'Books')),
    ('T-shirt', 2500, (SELECT id FROM category WHERE name = 'Clothing')),
    ('Jeans', 3500, (SELECT id FROM category WHERE name = 'Clothing')),
    ('Blender', 4500, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    ('Microwave', 7000, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    ('Tennis Racket', 5000, (SELECT id FROM category WHERE name = 'Sports & Outdoors')),
    ('Camping Tent', 15000, (SELECT id FROM category WHERE name = 'Sports & Outdoors'));
