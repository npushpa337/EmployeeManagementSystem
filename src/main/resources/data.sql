--Insert department first
insert into department(dept_Id, dept_Name) values(1, 'HR');
insert into department(dept_Id, dept_Name) values(2, 'IT');
insert into department(dept_Id, dept_Name) values(3, 'Finance');

--Insert employees next
insert into employee(emp_Name, email, salary, dept_Id) values('pushpa', 'npushpa337@gmail.com', 40000, 1);
insert into employee(emp_Name, email, salary, dept_Id) values('bhavya', 'bhavya227@gmail.com', 46000, 2);
insert into employee(emp_Name, email, salary, dept_Id) values('yashu', 'yashu678@gmail.com', 38000, 1);
insert into employee(emp_Name, email, salary, dept_Id) values('sowmya', 'sowmya45@gmail.com', 42000, 3);