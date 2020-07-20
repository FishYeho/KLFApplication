# KLFApplication

This repository contains all of Yehoshua Fish's work for the KLF coding assignment.

# Description
I decided to do the bonus challenge, which was to combine all three modules together into one module.
The application was developed majorly using Java. It's a Java web application that uses a MySQL database to contain all of the required data.
The front-end of the application was all done using JSF (Java Server Faces) and connected to the database using the JPA (Java Persistence API).
In order to make the front-end look nice, I used the PrimeFaces framework.
The application runs on a Payara server that must be installed and running in order for the application to function.

# How to run the project
In order to run the project, these are the steps that you must follow:

-Have a Payara server up and running

-Run the CreateDatabase.sql file which can be found in the src/test/resources folder

-Run the CreateTables.sql file which can be found in the src/test/resources folder

-Clean and build the project in Netbeans or your preferred IDE

-Run the project in Netbeans or your preferred IDE

-When you run the project, two test users will be created for you:

-User 1: Username jimbo Password: jimbo

-User 2: Username: itsmrbob Password: itsmrbob

# Bugs
This is a list of bugs that are currently affecting the application:

-Users are not properly redirected to the index page upon successful login

-The reports page is not functional

-The editing users feature is not entirely complete
