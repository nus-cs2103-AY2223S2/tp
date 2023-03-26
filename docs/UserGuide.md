# TechTrack User Guide

TechTrack is a powerful internship/job tracking application that combines the flexibility of a Command Line Interface (CLI) with the benefits of a Graphical User Interface (GUI).

Designed for computing students and professionals, TechTrack helps you manage your internship search project by setting goals, tracking deadlines, and providing clear feedback on your progress. Its CLI interface is optimized for speed, efficiency, and ease of use, making it a valuable tool for students who are already familiar with CLI environments.

1. [Quick Start](#quickstart)
2. [Features](#features)
   1. [Add Roles: `add`](#addrole)
   2. [Delete Role: `delete`](#deleterole)
   3. [Edit Role: `edit`](#editrole)
   4. [Find Role: `find`](#findrole)
   5. [Sort by Salary: `salary`](#sortsalary)
   6. [Sort by deadline: `deadline`](#sortdeadline)
   7. [Find by Companies: `company`](#findcompanies)
   8. [View more details: `view`](#view)
3. [Viewing help: `help`](#help)
4. [Exiting program: `exit`](#exit)
5. [FAQ](#faq)
6. [Command Summary](#summary)

# Quick Start
Ensure you have Java `11` or above installed in your Computer.
Download the latest `TechTrack.jar` from here.
Copy the file to the folder you want to use as the home folder for your AddressBook.
Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TechTrack.jar` command to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.


## Features

This section guides you on how to use features available in TechTrack.

<div markdown="block" class="alert alert-info">


**The features of TechTrack can be split into 3 main categories:**

* [Creating Role Info](#creating-patient-info)
* [Retrieving Role Info](#retrieving-patient-info)
* [General Commands](#general-commands)

<sub><sup>[back to top](#back-to-topt)</sup></sub>

## Creating Role Info
The commands in this segment are focused on creating, editing and removing data to and from the application.
These commands are:

* [Adding a Role](#adding-role)
* [Editing a Role](#editing-role)
* [Deleting a Role](#deleting-role)
* [Viewing a role ](#viewing-role)
* [Sorting a role by deadline](#sorting-role-deadline)
* [Sorting a role by salary](#sorting-role-salary)

### Adding a role: `add`

Adds a role to TechTrack.

Format: `add {Prefix}/{Parameter}…​`

**The prefixes and their respective parameters are as follows:**

| Required | Prefix | Parameter           | Restrictions                                                             |
|----------|--------|---------------------|--------------------------------------------------------------------------|
| `Yes`    | r      | ROLE                | Alphanumeric characters and spaces only.                                 |
| `Yes`    | c      | CONTACT             | Numbers only and at least 3 digits.                                      |
| `Yes`    | e      | EMAIL               | Must follow a valid email format. See below for more information.        |
| `Yes`    | coy    | COMPANY             | Follow company format                                                    |
| `Yes`    | jd     | JOB DESCRIPTION     |                                                                          |
| `No`     | t      | TAGS                |                                                                          |
| `Yes`    | $      | SALARY              | Positive integer only.                    **Compulsory for inpatients**. |
| `Yes`    | d      | APPLICATION DEADLINE | Follows YYYY-MM-DD format (i.e. `2023-10-20`). and must not be over      |
| `Yes`    | x      | EXPERIENCE REQUIRED | `dd-MM-yyyy` format only (i.e. `12-06-2022`).                            |


### Adding a role:
Adds a role to the current list of roles
FORMAT: add roleID

### Deleting a role:
Deletes the role from the current list of roles.
FORMAT: delete roleID

### Ranking the roles
Rank the roles based on the priority chosen by the user.
FORMAT: rank roleID LEVEL

### Listing details for ranked roles:
When the role clicks on the role, list details about the internship that they have ranked
FORMAT: list

### Save Data
Saves data to a text file whenever there is a command that changes the role and gets the data when the program is run again.
FORMAT: bye

### Sorting by Deadline

Sort the closest deadline first (e.g. deadline asc) 

![Deadline](images/DeadlineCommand1.png)

Sort the latest deadline first (e.g. deadline desc)

![Deadline](images/DeadlineCommand2.png)

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TechTrack home folder.


## Command Summary

| Action   | Format, Examples                       |
|----------|----------------------------------------|
| add      | add roleID (e.g. add 221574)           |
| delete   | delete roleID (e.g. delete 221574)     |
| list     | list                                   |
| rank     | rank roleID LEVEL (e.g. rank 221574 4) |
| exit     | bye                                    |
| deadline | asc/desc (e.g. deadline asc)           |
