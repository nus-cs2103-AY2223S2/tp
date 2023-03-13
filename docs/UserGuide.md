# TechTrack User Guide

TechTrack is a powerful internship/job tracking application that combines the flexibility of a Command Line Interface (CLI) with the benefits of a Graphical User Interface (GUI).

Designed for computing students and professionals, TechTrack helps you manage your internship search project by setting goals, tracking deadlines, and providing clear feedback on your progress. Its CLI interface is optimized for speed, efficiency, and ease of use, making it a valuable tool for students who are already familiar with CLI environments.

# Quick Start
Ensure you have Java `11` or above installed in your Computer.
Download the latest `TechTrack.jar` from here.
Copy the file to the folder you want to use as the home folder for your AddressBook.
Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TechTrack.jar` command to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.


# Features
- Adding a role: 'add'
- Deleting a role: 'delete'
- Ranking the roles
- Viewing details for a role: 'view'
- Save data
- ....
  (role means internship role)


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

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TechTrack home folder.


## Command Summary

| Action   | Format, Examples                            |
|----------|---------------------------------------------|
| add      |add roleID (e.g. add 221574)                 |
| delete   |delete roleID (e.g. delete 221574)           |
| list     |list                                         |
| rank     |rank roleID LEVEL (e.g. rank 221574 4)       |
| bye      |bye                                          |
|----------|---------------------------------------------|
