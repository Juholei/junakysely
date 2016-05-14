CREATE TABLE comments
(id SERIAL PRIMARY KEY,
 comment TEXT NOT NULL,
 created TIMESTAMP DEFAULT current_timestamp NOT NULL);
