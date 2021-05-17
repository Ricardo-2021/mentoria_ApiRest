CREATE TABLE category(
    id  SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE);


