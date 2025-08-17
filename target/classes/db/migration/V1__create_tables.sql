CREATE TABLE wedding_list (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    presence VARCHAR(255) NOT NULL,
    guest TEXT[], -- Postgres array type for list of guest values
    beverage TEXT[] -- Postgres array type for list of beverage values
);
