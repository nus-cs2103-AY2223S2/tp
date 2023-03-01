---
layout: page
title: User Guide
---

<br>
<img src="images/logo.png" width="200px"><br>

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI). The app provides a convenient platform for students to easily access and update their modules within presses of a keyboard.<br><br>

## Table of Contents

1. [Quick Start](#quick-start)
1. [Features](#features)
    1. Add a module: `Add`
   1. Remove a module: `Delete`
   1. Change module details: `Edit`
   1. Remove all modules: `Clear`
   1. Display all modules: `List`
   1. Find specific module by module code: `Find`
   1. Viewing help: `Help`
   1. Exiting the program: `Exit`
   1. Saving the data
   1. Editing the data file
3. [Frequently Asked Questions (FAQ)](#faq)
4. [Command Summary](#command-summary) 

--------------------------------------------------------------------------------------------------------------------

## Quick Start <a name="quick-start"></a>

1. Ensure you have Java 11 or above installed in your Computer.
1. Download modtrek.jar here. (To be inserted)
1. Copy the file to the folder you want to use as the home folder for ModTrek
1. Open a command terminal, cd to the folder you put your jar file in, and use java -jar modtrek.jar to run the application
1. A GUI similar to the below should appear in a few seconds.
1. Type commands within the command line interface (CLI) and press enter to execute it. For a list of executable commands, refer to the [Features / Commands Section](#features).
![Ui QuickStart](images/Ui-quickstart.png)

--------------------------------------------------------------------------------------------------------------------

## Features <a name="features"></a>

MODTrek supports the following features:

| Feature              | Description                                                                                                                                                                                                                                                                                                                                                    | Command Syntax                                                                                                                                                                                                                                               |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| add a module         | _Adds a module to the module list. Modules must be distinct.                                                                                                                                                                                                                                                                                                   | `add /m <code> /c <credits> /y <year-semester> (/g <grade>)`                                                                                                                                                                                                 |
| delete a module      | _Deletes the specified module from the module list.                                                                                                                                                                                                                                                                                                            | _To delete specific modules:_ <br>`delete /m <code1> (<code2>) ...` <br><br><br> :exclamation: For `...`, specify one or more module codes of modules to be deleted, separated by space (' '). <br><br><br> _To delete all modules:_ <br>`delete all`        |
| edit a module        | _Edits an existing module in the module list.                                                                                                                                                                                                                                                                                                                  | `edit /m <code> ...`<br><br><br>_Specify one or more parameters in `...` :_<br>- `/m <code>`<br>- `/c <credits>`<br>- `/y <year-semester>`<br>- `/g <grade>`                                                                                                 |
| tag a module         | _Tags a module to include or remove one or more degree requirements (e.g. University Level Requirements, Computer Science Foundation etc) that the module fulfils.                                                                                                                                                                                             | _To include tags:_ `tag /m <code> include <tag1> (<tag2>) ...` <br><br><br> _To remove tags:_ `tag /m <code> remove <tag1> (<tag2>) ...` <br><br><br> :exclamation: For `...`, specify one or more tags to be included or removed, separated by space (' '). |
| list modules         | _Displays all the modules taken by the user.                                                                                                                                                                                                                                                                                                                   | `list`                                                                                                                                                                                                                                                       |
| find a module        | _Displays specific module(s) satisfying the search query (by code, credits, year-semester, and/or grade) and their details previously logged by the user.                                                                                                                                                                                                      | `find (/m <code>) (/c <credits>) (/y <year-semester>) (/g <grade>)`                                                                                                                                                                                          |
| view degree progress | _Displays a summary data on the degree progress (e.g. completion status of each degree requirement, Cumulative Average Point etc).                                                                                                                                                                                                                             | `view progress`                                                                                                                                                                                                                                              |
| exit                 | _Exits the program.                                                                                                                                                                                                                                                                                                                                            | `exit`                                                                                                                                                                                                                                                       |
| save MODTrek data    | _Saves the modified data regarding the modules into the hard disk automatically after any command that changes the data. There is no need to save manually.                                                                                                                                                                                                    | -                                                                                                                                                                                                                                                            |
| edit MODTrek data    | _Editing the data_<br><br>ModTrek data is saved as a JSON file [JAR file location]/data/modtrek.json. Advanced users are welcome to update data directly by editing that data file.<br> <br>:warning: **Caution!** If your changes to the data file makes its format invalid, ModTrek will discard all data and start with an empty data file at the next run. | -                                                                                                                                                                                                                                                            |


### Notes about command format:
- Words enclosed by `<>` are the parameters to be supplied by the user.<br>
  - E.g. in `add /m <code> /c <credits> /y <year-semester> /g <grade>`, `<code>`, `<credits>`, `<year-semester>`, `<grade>` are parameters which can be used as `add /m CS2103T /c 4 /y Y2S2 /g A+`
- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.
- Parameters encapsulated in between brackets ('()') are optional.
--------------------------------------------------------------------------------------------------------------------

## Frequently Asked Questions (FAQ) <a name="faq"></a>

**Q**: Question<br>
**A**: Answer

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                   | Format, Examples                                                                                                                                                                                  |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                  | `add /m <code> /c <credits> /y <year-semester> (/g <grade>)` <br> e.g., `add /m CS2103T /c 4 /y y2s2`                                                                                             |
| **Delete**               | `delete <code1> (<code2>) ...` or `delete all` <br> e.g. `delete CS2100 CS2040S`                                                                                                                  |
| **Edit**                 | `edit /m <existing code> (/m <new code>) (/c <credits>) (/y <year-semester>) (/g <grade>)` <br> e.g., `edit /m ES2660 /m CS2101`                                                                  |
| **Tag**                  | `tag /m <code> include <tag1> (<tag2>) ...` or `tag /m <code> remove <tag1> (<tag2>) ...` <br> e.g., `tag CS2030S include cs_foundation`, `tag ES2660 remove university_level it_professionalism` |
| **List**                 | `list`                                                                                                                                                                                            |
| **Find**                 | `find (/m <code>) (/c <credits>) (/y <year-semester>) (/g <grade>)` <br> e.g., `find A+`                                                                                                          |
| **View Degree Progress** | `view progress`                                                                                                                                                                                   |
| **Exit**                 | `exit`                                                                                                                                                                                            |


<br>**Notes:**
- Parameters encapsulated in between brackets `()` are optional.
- For `edit`, at least one optional parameter has to be specified.
