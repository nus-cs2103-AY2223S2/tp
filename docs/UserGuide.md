---
layout: page
title: User Guide
---

<br>
<img src="images/logo.png" width="200px"><br>

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI). The app provides a convenient platform for students to easily access and update their modules within presses of a keyboard.<br><br>

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
    1. Add a module: `add`
    2. Remove a/all module: `delete`
    3. Change module details: `edit`
    4. Tag a module: `tag`
    5. Display all modules: `list`
    6. Find specific module by module code: `find`
    7. Exiting the program: `exit`
    8. Saving the data
    9. Editing the data file
3. [Frequently Asked Questions (FAQ)](#faq)
4. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start <a name="quick-start"></a>

1. Ensure you have Java 11 or above installed in your Computer.
1. Download `modtrek.jar` here. (To be inserted)
1. Copy the file to the folder you want to use as the _home folder_ for ModTrek
1. Open a command terminal, `cd` to the folder you put your jar file in, and use `java -jar modtrek.jar` to run the application
1. A GUI similar to the below should appear in a few seconds.
1. Type commands within the command line interface (CLI) and press enter to execute it. For a list of executable commands, refer to the [Features / Commands Section](#features).
![Ui QuickStart](images/Ui-quickstart.png)

--------------------------------------------------------------------------------------------------------------------

## Features <a name="features"></a>

<div markdown="span" class="alert alert-info">

### Notes about command format:
- Words enclosed by `<>` are the parameters to be supplied by the user.<br>
   - E.g. in `add /m <code> /c <credits> /y <year-semester> /g <grade> /t <tag>...`, `<code>`, `<credits>`, `<year-semester>`, `<grade>`, `<tag>` are parameters which can be used as `add /m CS2103T /c 4 /y Y2S2 /g A+ /t University Level Requirements /t Computer Science Foundation`
- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `delete all`) will be ignored.
- Parameters encapsulated in between brackets ('()') are optional.

</div>

### MODTrek supports the following features:

#### Add a Module : `add`

Adds a module to the module list. Modules must be distinct.

Command syntax: `add /m <code> /c <credits> /y <year-semester> (/g <grade>) (/t <tag>...)`

:exclamation: For `...`, specify one or more tags to be added, separated by space (' ').

Example: `add /m CS2103T /c 4 /y y2s2 /g A /t University Level Requirements`

#### Delete a Module : `delete`

Deletes all/the specified module(s) from the module list.

Command syntax: _To delete specific modules:_ `delete /m <code1> (/m <code2>) ...` <br><br> :exclamation: For `...`, specify one or more module codes of modules to be deleted, separated by space (' '). <br><br>_To delete all modules:_ `delete all`

Example: `delete /m CS2100 /m CS2040S`

#### Edit a Module : `edit`

Edits an existing module in the module list.

Command syntax: `edit <code> ...`<br><br>_Specify one or more parameters in `...` :_<br>- `/m <new code>`<br>- `/c <credits>`<br>- `/y <year-semester>`<br>- `/g <grade>`<br>- `/t <tag>`

Example: `edit ES2660 /m CS2101 /g B+`

:exclamation: Past data will be overridden and not be saved

#### Tag a Module : `tag`

Tags a module to include or remove one or more degree requirements (e.g. University Level Requirements, Computer Science Foundation etc) that the module fulfils.

Command syntax:_To include tags:_ `tag /m <code> include <tag1> (<tag2>) ...` <br><br>_To remove tags:_ `tag /m <code> remove <tag1> (<tag2>) ...` <br><br>:exclamation: For `...`, specify one or more tags to be included or removed, separated by space (' ').

Example:
* `tag CS2030S include cs_foundation`
* `tag ES2660 remove university_level it_professionalism`

#### List modules : `list`

Displays all the modules taken by the user.

Command syntax: `list`

#### Find modules : `find`

Displays specific module(s) satisfying the search query (by code, credits, year-semester, and/or grade) and their details previously logged by the user.

Command syntax: `find (/m <code>) (/c <credits>) (/y <year-semester>) (/g <grade>)`

Example:
* `find /c 4 /g A+`
* `find /y y2s2`

#### Exit MODTrek : `exit`

Exits the program.

Command syntax: `exit`

#### Saving MODTrek data

Saves the modified data regarding the modules into the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing MODTrek data

ModTrek data is saved as a JSON file [JAR file location]/data/modtrek.json. Advanced users are welcome to update data directly by editing that data file.<br> <br>:warning: **Caution!** If your changes to the data file makes its format invalid, ModTrek will discard all data and start with an empty data file at the next run.

--------------------------------------------------------------------------------------------------------------------

## Frequently Asked Questions (FAQ) <a name="faq"></a>

**Q**: Why is it that I can add in modules not offered in NUS<br>
**A**: Unfortunately we do not have a database to store all the available modules in NUS.
We can only check if the module code is formatted correctly, and we trust users to key in modules that
are provided only by NUS.

--------------------------------------------------------------------------------------------------------------------

## Command Summary <a name="command_summary"></a>


| Action                   | Format, Examples                                                                                                                                                                                  |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                  | `add /m <code> /c <credits> /y <year-semester> (/g <grade>) (/t <tag>...)` <br> e.g., `add /m CS2103T /c 4 /y y2s2 /g A /t University Level Requirements`                                                                                             |
| **Delete**               | `delete /m <code1> (/m <code2>) ...` or `delete all` <br> e.g. `delete /m CS2100 /m CS2040S`                                                                                                                  |
| **Edit**                 | `edit <existing code> (/m <new code>) (/c <credits>) (/y <year-semester>) (/g <grade>) (/t <tag>...)` <br> e.g., `edit ES2660 /m CS2101`                                                                  |
| **Tag**                  | `tag /m <code> include <tag1> (<tag2>) ...` or `tag /m <code> remove <tag1> (<tag2>) ...` <br> e.g., `tag CS2030S include cs_foundation`, `tag ES2660 remove university_level it_professionalism` |
| **List**                 | `list`                                                                                                                                                                                            |
| **Find**                 | `find (/m <code>) (/c <credits>) (/y <year-semester>) (/g <grade>)` <br> e.g., `find A+`                                                                                                          |
| **View Degree Progress** | `view progress`                                                                                                                                                                                   |
| **Exit**                 | `exit`                                                                                                                                                                                            |


<br>**Notes:**
- Parameters encapsulated in between brackets `()` are optional.
- For `edit`, at least one optional parameter has to be specified.
