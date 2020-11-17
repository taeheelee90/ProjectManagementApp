# ProjectManagementApp
Simple Project Management App with Spring Boot


# Technologies in use 

Database for test purpose
: H2 DB
Database for deployment
: Heroku PostgreSQL
Storage for File management (upload / download)
: AWS S3 storage 


# Entity with relationship
Employee: works as project manager / can have multiple projects
Project: must have one project manager / can have multiple project expenditures
Project Expenditure: must registered for exactly one project


# Key business logic applied
Application has login and signup features.
Projects can be ordered differently. (by name, date or status)
Projects can be searched. (by keyword in project name)
Project status (WAITING, PROCEEDING, COMPLETE) is programmatically calculated by given dates (i.e., start and end dates)
Project has budget and only COMPLETE projects can have budget 0.
Adding new expenditure decrease same amount from project budget.
Total expenditures for project can not exceed budget. (if so, throws exception)
Project Expenditure can be supported with reference file submission. 







