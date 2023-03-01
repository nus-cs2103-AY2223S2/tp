---
layout: page
title: User Guide
---

<br>
<img src="images/logo.png" width="200px"><br>

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI). The app provides a convenient platform for students to easily access and update their modules within presses of a keyboard.<br><br>

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features / Commands](#features)
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

## Features / Commands <a name="features"></a>

The following commands are available on the CLI:

| Command  | Description                                                                                                                                                                                                                                                                                                                                                    | Syntax                                                                                                                                                                                                 |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `add`    | _Add a module_<br><br><br>Adds a module to the module list. Modules must be distinct.                                                                                                                                                                                                                                                                          | _For current or future semesters:_<br>`add /m <module code> /c <credits> /y <year-semester>`<br><br><br>_For previous semesters:_<br>`add /m <module code> /c <credits> /y <year-semester> /g <grade>` |
| `delete` | _Remove a module_<br><br><br>Deletes the specified module from the module list.                                                                                                                                                                                                                                                                                | `delete <module code>`                                                                                                                                                                                 |
| `edit`   | _Change module details_<br><br><br>Edits an existing module in the module list.                                                                                                                                                                                                                                                                                | `edit /m <module code> ...`<br><br><br>_Specify one or more parameters in `...` :_<br>- `/m <module code>`<br>- `/c <credits>`<br>- `/y <year-semester>`<br>- `/g <grade>`                             |
| `clear`  | _Remove all modules_<br><br><br>Deletes all modules from the list.                                                                                                                                                                                                                                                                                             | `clear`                                                                                                                                                                                                |
| `list`   | _View all the modules_<br><br><br>Displays all the modules taken by the user.                                                                                                                                                                                                                                                                                  | `list`                                                                                                                                                                                                 |
| `find`   | _Find a specific module by module code_<br><br><br>Displays a specific module and its details previously logged by the user.                                                                                                                                                                                                                                   | `find <module code>`                                                                                                                                                                                   |
| `Exit`   | _Exit the program_<br><br><br>Exits the program.                                                                                                                                                                                                                                                                                                               | `exit`                                                                                                                                                                                                 |
| -        | _Saving the data_<br><br><br>ModTrek data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.                                                                                                                                                                                                  | -                                                                                                                                                                                                      |
| -        | _Editing the data_<br><br>ModTrek data is saved as a JSON file [JAR file location]/data/modtrek.json. Advanced users are welcome to update data directly by editing that data file.<br> <br>:warning: **Caution!** If your changes to the data file makes its format invalid, ModTrek will discard all data and start with an empty data file at the next run. | -                                                                                                                                                                                                      |


### Notes about command format:
- Words enclosed by `<>` are the parameters to be supplied by the user.<br>
  - E.g. in `add /m <module code> /c <credits> /y <year-semester> /g <grade>`, `<module code>`, `<credits>`, `<year-semester>`, `<grade>` are parameters which can be used as `add /m CS2103T /c 4 /y Y2S2 /g A+`
- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.

--------------------------------------------------------------------------------------------------------------------

## Frequently Asked Questions (FAQ) <a name="faq"></a>

**Q**: Question<br>
**A**: Answer

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add /m <module code> /c <credits> /y <year-semester> (/g <grade>)` <br> e.g., `add /m CS2103T /c 4 /y y2s2`
**Delete** | `delete <module code>` <br> e.g. `delete CS2100`
**Edit** | `edit /m <existing module code> (/m <new module code>) (/c <credits>) (/y <year-semester>) (/g <grade>)`<br> e.g., `edit /m ES2660 /m CS2101`
**Clear** | `clear`
**List** | `list`
**Find** | `find`
**Exit** | `exit`

<br>**Notes:**
- Arguments encapsulated in between brackets ('()') are optional.
- For `edit`, at least one optional argument has to be specified.
