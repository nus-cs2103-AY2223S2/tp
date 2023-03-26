---
layout: page
title: User Guide
---

CoDoc is a desktop app for students in SoC (School of Computing) to connect with each other for the benefit of their course of study. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoDoc can get your contact management tasks done faster than traditional GUI apps and networking with people can be easily done.
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
   9. [exit](#exit)
5. [Data Management](#data-management)
6. [FAQ](#faq)
7. [Command Summary](#command-summary)
8. [Additional Resources](#additional-resources)
   1. [How To Check Java Version](#how-to-check-java-version)

--------------------------------------------------------------------------------------------------------------------
## Quick Start
Currently, our contact list is empty. Let's try adding a fictitious contact into our list to familiarise ourselves with CoDoc. Don't worry, we will remove this fictitious contact at the end.
1. **add** a new contact -> [add command](#add)
   <br>`add n/Bob y/2 c/1 e/e0823741@nus.edu`<br>

   | Parameters         | Description                                             |
   |--------------------|---------------------------------------------------------|
   | n/Bob              | name is Bob                                             |
   | y/2                | year of study is 2                                      |
   | c/1                | course enrolled is Accounting—index 1 on the left panel |
   | e/e0823741@nus.edu | email is e0823741@nus.edu                               |
2. **find** Bob in list of persons -> [find command](#find)
   <br>`find n/Bob y/2 c/Accounting`<br>

   | Parameters         | Description                                 | 
   |--------------------|---------------------------------------------|
   | n/Bob              | find all person with Bob in its name AND... |
   | y/2                | year of study is 2 AND...                   |
   | c/Accounting       | course enrolled is Accounting AND...        |
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
   | c/Accounting       | course enrolled is Accounting AND...        |
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
4. Open a command terminal
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
### find
### view
### edit
### list
### delete
### clear
### help
### exit

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Data Management
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install CoDoc in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoDoc home folder.

**Q**: Where is the data file stored?<br>
**A**: CoDoc data are saved as a JSON file at ***[JAR file location]*****/data/codoc.json**. Advanced users are welcome to update data directly by editing that data file.

:warning: If your changes to the data file makes its format invalid, CoDoc will discard all data and start with an empty data file at the next run.

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## FAQ

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)

## Command Summary 

[ ^Scroll back up to *Tables of Contents*](#table-of-contents)
--------------------------------------------------------------------------------------------------------------------

## Additional Resources
### How To Check Java Version

[ ^Scroll back up to *Downloading CoDoc*](#downloading-codoc)

--------------------------------------------------------------------------------------------------------------------
[ ^Scroll back up to *Tables of Contents*](#table-of-contents)
