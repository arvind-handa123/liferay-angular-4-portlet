create table EMP_Employee (
	uuid_ VARCHAR(75) null,
	empId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	city VARCHAR(75) null,
	dob DATE null,
	contactNumber VARCHAR(75) null,
	organization VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);