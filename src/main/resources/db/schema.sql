DROP TABLE Deaartment IF EXISTS;
DROP TABLE Employees IF EXISTS;
DROP TABLE Projects IF EXISTS;

CREATE TABLE department (
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(80)
);

CREATE TABLE employees (
	id INTEGER IDENTITY PRIMARY KEY,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	title VARCHAR(20),
	department_id INTEGER NOT NULL
);

ALTER TABLE employees ADD CONSTRAINT fk_emp_department FOREIGN KEY (department_id) REFERENCES department (id);

CREATE TABLE projects (
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(150),
	start_date DATE,
	end_date DATE,
	status VARCHAR(20),
	project_manager INTEGER NOT NULL,
	handling_department INTEGER NOT NULL	
);

ALTER TABLE projects ADD CONSTRAINT fk_project_dep FOREIGN KEY (project_manager) REFERENCES employees (id);
ALTER TABLE projects ADD CONSTRAINT fk_project_emp FOREIGN KEY (handling_department) REFERENCES department (id);

