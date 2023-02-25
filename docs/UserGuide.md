---
layout: page
title: sprINT User Guide
---

Welcome to **sprINT's User Guide**. sprINT  is a **desktop application** for managing internship applications,
optimized for use via a Command Line Interface (CLI) while still incorporating the benefits of a Graphical User
Interface (GUI). 

The app is optimized greatly for *fast typers*. If you can type fast, sprINT will be of great assistance in
your internship hunt! 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `sprint.jar` from [here](https://github.com/AY2223S2-CS2103T-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your sprINT application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sprINT.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data already.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add r/SWE Intern c/Goggle e/goggle_careers@gmail.com s/interested d/01-01-2023` : Adds an 
   application for Software Engineer Intern position @ Goggle that I'm interested in. Deadline of application is 1st of
   January.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/COMAPNY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add c/John Doe's Company`.

* Items in square brackets are optional.<br>
  e.g `c/COMPANY_NAME [t/TAG]` can be used as `c/John Doe's Company t/highsalary` or as `c/John Doe's Company` only.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/highsalary`, `t/highsalary t/creditbearing` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `r/ROLE c/COMPANY_NAME`, `c/COMPANY_NAME r/ROLE` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `r/SWE Intern r/Software Intern`, only `r/Software Intern` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an application: `add` `[coming in v1.2]`

Adds an internship application to be tracked.

Format: `add r/ROLE c/COMPANY_NAME e/EMAIL s/STATUS [j/JOB_DESCRIPTION] [d/DEADLINE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Job Description and Deadline are optional fields for an application.

A application can have any number of tags (including 0).
</div>

Examples:
* `add r/SWE Intern c/Goggle e/goggle_careers@gmail.com s/interested d/01-01-2023`
* `add r/Data Analyst Intern c/Bloomberg e/bloomberg_hires@bloomberg.com s/Applied j/Conversion to full time in 12 months`


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add r/ROLE c/COMPANY_NAME e/EMAIL s/STATUS [j/JOB_DESCRIPTION] [d/DEADLINE] [t/TAG]…​` <br> e.g., `add r/Teaching Assistant c/NUS SOC e/ta_portal@nus.edu.sg s/Offered j/CS3230 Tutorial Tutor d/01-01-2023 t/algorithms`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
