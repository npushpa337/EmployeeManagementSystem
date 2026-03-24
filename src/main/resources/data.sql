--Insert department first
insert into department(dept_Name, location) values('HR', 'banglore');
insert into department(dept_Name, location) values('IT', 'hyderabad');
insert into department(dept_Name, location) values('Finance', 'manglore');

--Insert employees next
insert into employee(emp_Name, email, salary, dept_Id) values('pushpa', 'npushpa337@gmail.com', 40000, 1);
insert into employee(emp_Name, email, salary, dept_Id) values('bhavya', 'bhavya227@gmail.com', 46000, 2);
insert into employee(emp_Name, email, salary, dept_Id) values('yashu', 'yashu678@gmail.com', 38000, 3);