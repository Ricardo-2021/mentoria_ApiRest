CREATE TABLE car(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    available boolean NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
);