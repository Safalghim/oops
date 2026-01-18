-- ================================
-- DATABASE
-- ================================
CREATE DATABASE IF NOT EXISTS digital_directory;
USE digital_directory;

-- ================================
-- DEPARTMENTS TABLE
-- ================================
CREATE TABLE IF NOT EXISTS departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);

-- ================================
-- STAFF TABLE
-- ================================
CREATE TABLE IF NOT EXISTS staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
        REFERENCES departments(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- ================================
-- SAMPLE DATA (OPTIONAL)
-- ================================
INSERT INTO departments (name, location) VALUES
('IT', 'Block A'),
('HR', 'Block B'),
('Finance', 'Block C'),
('Marketing', 'Head Office');

INSERT INTO staff (name, email, position, department_id) VALUES
('John Doe', 'john@example.com', 'Manager', 1),
('Mary Jane', 'mary@example.com', 'HR Officer', 2);
