SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS farmers (
id int PRIMARY KEY auto_increment,
  farmerName VARCHAR,
  location VARCHAR,
quantity INTEGER,
breed VARCHAR
);