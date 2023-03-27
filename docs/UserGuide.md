---
layout: page
title: User Guide
---

#### CoDoc is a desktop app for students in SoC (School of Computing) to connect with each other for the benefit of their course of study. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoDoc can get your contact management tasks done faster than traditional GUI apps and networking with people can be easily done.
--------------------------------------------------------------------------------------------------------------------
# Table of Contents
1. [Quick Start](#quick-start)
2. [Downloading CoDoc](#downloading-codoc)
3. [Navigation](#navigation)
4. [Commands](#commands)
   1. [add](#add)
   2. [find](#find)
   3. [view](#view)
   4. [edit](#edit)
   5. [list](#list)
   6. [delete](#delete)
   7. [clear](#clear)
   8. [help](#help)
   9. [exit](#exit--exit)
5. [Data Management](#data-management)
6. [FAQ](#faq)
7. [Upcoming Features](#)
8. [Command Summary](#command-summary)
8. [Additional Resources](#additional-resources)
   1. [How To Check Java Version](#how-to-check-java-version)
   2. [How to Open CoDoc](#how-to-open-codoc)

--------------------------------------------------------------------------------------------------------------------
## Quick Start
Currently, our contact list is empty. Let's try adding a fictitious contact into our list to familiarise ourselves with CoDoc. Don't worry, we will remove this fictitious contact at the end.
1. **add** a new person -> [add command](#add)
   <br>`add n/Bob y/2 c/1 e/e0823741@nus.edu`<br>

   | Parameters         | Description                                             |
   |---------------------------------------------------------|---------------------------------------------------------|
   | n/Bob              | person name is Bob                                      |
   | y/2                | year of study is 2                                      |
   | c/1                | course enrolled is Accounting—index 1 on the left panel |
   | e/e0823741@nus.edu | email is e0823741@nus.edu                               |
2. **find** Bob in list of persons -> [find command](#find)
   <br>`find n/Bob y/2 c/Accounting`<br>

   | Parameters         | Description                                 | 
   |--------------------|---------------------------------------------|
   | n/Bob              | find all person with Bob in its name AND... |
   | y/2                | year of study is 2 AND...                   |
   | c/1                | course enrolled is Accounting AND...        |
3. **view** person at index 1 (should be Bob if you started from an empty list)
   <br>`view 1`<br><br>
4. **view** current person information -> [view command](#view)
   <br>`view m`
   <br>`view s`
   <br>`view c`<br>

   | Parameters         | Description                       |
   |--------------------|-----------------------------------|
   | view m             | view person's list of modules     |
   | view s             | view person's list of skills      |
   | view c             | view person's contact information |
5. **edit** current contact -> [edit command](#edit)
    <br>`edit n/Bob Sim m+/AY2223S2 CS2109S s+/PYTHON`<br>

   | Parameters           | Description                                      |
   |----------------------|--------------------------------------------------|
   | n/Bob Sim            | change name to Bob Sim                           |
   | m+/AY2223S2 CS2109S  | add AY2223S2 CS2109S to Bob Sim's set of modules |
   | s+/python            | add PYTHON to Bob Sim's set of skills            |
6. **find** Bob in list of persons -> [find command](#find)
    <br>`find n/Bob Sim y/2 c/Accounting m/AY2223S2 CS2109S s/PYTHON`<br>

   | Parameters         | Description                                 |
   |--------------------|---------------------------------------------|
   | n/Bob              | find all person with Bob in its name AND... |
   | y/2                | year of study is 2 AND...                   |
   | c/1                | course enrolled is Accounting AND...        |
   | m/AY2223S2 CS2109S | AY2223S2 CS2109S in their set of modules    |
   | s/PYTHON           | PYTHON in their set of skills               |
7. **delete** person at index 1 (should be Bob if you started from an empty list) -> [delete command](#delete)
    <br>`delete 1`<br><br>
8. **list** all person -> [list command](#list)
    <br>`list`<br><br>
9. **exit** CoDoc -> [exit command](#exit)
    <br>`exit`<br><br>
   
[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Downloading CoDoc
1. Ensure you have `Java 11` or above installed in your Computer. -> [How To Check Java Version](#how-to-check-java-version)
   * If not, you can download it from [here](https://www.oracle.com/java/technologies/downloads/#java11) 
2. Download the latest version of `codoc.jar` [here](https://github.com/AY2223S2-CS2103T-F12-2/tp/releases/tag/v1.3.trial).
3. Copy the file to the folder you want to use as the _home folder_ for CoDoc.<br>
   For example,
   1. Create a new folder in your Desktop—this folder will then be the _home folder_.
   2. Place `codoc.jar` into the newly created folder.
4. Open a command terminal -> [How to Open CoDoc](#how-to-open-codoc)
   1. `cd` into the folder you put the jar file in.
   2. `java -jar codoc.jar` to run the application.<br>
   3. A window similar to the one below should appear in a few seconds.
   <img src="images/Ui.png"/>
   <br>

[ ^Scroll back to *Tables of Contents*](#table-of-contents)

## Navigation
[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Commands
### add
`add n/NAME e/EMAIL y/YEAR c/COURSE_INDEX [OPTIONAL/PARAMETER]...`<br>
>
> **Compulsory parameters:**
> - n/NAME (eg. n/John Doe)
> - e/EMAIL (eg. e/johndoe123@email.com)
> - y/YEAR (eg. ay/3, ay/prof, ay/alum)
> - c/COURSE_INDEX (eg. c/1)
>
> **Optional parameters:**
> - g/GITHUB (eg.g/johnny)
> - l/LINKED (eg. l/linkedin.com/in/john-doe)
> - m/MODULE YEAR (eg. m/AY2223S1 CS2103T, m/AY2122S2 CS2101)
> - s/SKILL (eg. s/C++, s/Rust)
>
> 💡 **Tip:** A person can have any number of [OPTIONAL/PARAMETER] (including 0)
>
> **Examples:**
> - `add` n/Betsy Crowe e/betsycrowe@example.com y/prof c/1 g/betsy123 l/linkedin.com/in/betsy-123
> - `add` n/John Doe e/johnd@example.com y/2 c/3
    > ![Add Example](images/UiAddContact.jpg)

### find
`find [OPTIONAL/PARAMETER]...`<br>
>> find KEYWORD [MORE_KEYWORDS]
> * The search is case-insensitive. e.g `hans` will match `Hans`
> * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
> * Only the name is searched.
> * Only full words will be matched e.g. `Han` will not match `Hans`
> * Persons matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
>
> **Examples:**
> - `find John` returns `john` and `John Doe`
> - `find alex david` returns `Alex Yeoh`, `David Li`<br>
    ![result for 'find alex david'](images/findAlexDavidResult.jpg)

### view
`view <PARAMETERS>`<br>

### edit
`edit [OPTIONAL/PARAMETER]...`<br>
> - Edits the person displayed in the view panel
> - At least one of the optional fields must be provided.
> - Existing values will be updated to the input values.
> - When editing skills, the existing skills of the person will be removed i.e adding of skills is not cumulative.
> - You can remove all the person’s skills by typing s/ without specifying any skills after it
>
> **Examples:**
> - edit g/91234567 e/johndoe@example.com will edit the github username and email address of the person to be 91234567 and johndoe@example.com respectively.
> - edit n/Betsy Crower s/ Edits the name of the person to be Betsy Crower and clears all existing skills.

### list
`list`<br>
> * Shows all persons stored.<br>

### delete
`delete <INDEX>`<br>
> * Deletes the person at the specified INDEX.<br>
> * INDEX refers to an index currently shown in the displayed person list.<br>
> * INDEX must be a positive integer 1, 2, 3, ...<br>
> * E.g. `delete 1` will delete the current person at index 1 as shown in the list panel.<br>

### clear
`clear`<br>
> * :warning: Clears **all** entries from CoDoc.<br>

### help:
`help`<br>
> <img src="images/UiHelpMenu.png"/>
> * Opens help menu <br>
> * Equivalent to clicking Help > Help F1 via the GUI <br>

### exit:
`exit`<br>
> * Exits the program. <br>
> * Equivalent to clicking the close button via the GUI. <br>

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Data Management
**Q**: How do I save my person list?<br>
**A**: CoDoc automatically saves your person list after every successful `add`, `edit` and `clear`.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install CoDoc in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoDoc home folder.

**Q**: Where is the data file stored?<br>
**A**: CoDoc data are saved as a JSON file at ***[JAR file location]*****/data/codoc.json**. Advanced users are welcome to update data directly by editing that data file.

:warning: If your changes to the data file makes its format invalid, CoDoc will discard all data and start with an empty data file at the next run.

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## FAQ

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Upcoming Features

### Automated bulk edits `[coming in v2.0]`
_Details coming soon ..._

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

<br>
[ ^Scroll back up to *Tables of Contents*](#table-of-contents)


## Command Summary 

| Actions                          | Description                                                                                                                                 |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Add a person                     | add n/Bob Sim y/2 c/1 e/e0823741@nus.edu g/bobabob l/linkedin.com/in/bom-sim-086g93847 m/ay2223s2 CS2103T m/AY2223S2 cs2101 s/python s/java |
| Clear all persons                | clear                                                                                                                                       |
| Delete person at index 3         | delete 3                                                                                                                                    |
| Edit contact in the right panel  | edit n/David m+/AY2223S2 CS2109S s-/python                                                                                                  |
| Find by attributes               | find n/David c/2 m/CS2109S s/java                                                                                                           |
| List the full list of contacts   | list                                                                                                                                        |
| View contact                     | view 3                                                                                                                                      |
| View tab                         | view c, view m, view s                                                                                                                      |
| View user guide and command list | help                                                                                                                                        |

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Additional Resources
### How To Check Java Version
[ ^Scroll back up to *Downloading CoDoc*](#downloading-codoc)
### How to Open CoDoc
[ ^Scroll back up to *Downloading CoDoc*](#downloading-codoc)

--------------------------------------------------------------------------------------------------------------------
[ ^Scroll back up to *Tables of Contents*](#table-of-contents)
