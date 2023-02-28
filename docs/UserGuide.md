---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

> **Note**<br>
> This is a desktop app.

1. Have Java `11` or above installed in local laptop or Computer.

2. Download the latest version (InternEase v0.0) of `InternEase.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-4/tp/releases).<br>

3. Copy the file to the folder you want to use as the _home folder_ for your InternEaseApp.

4. Start the app by:
    - Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar InternEase.jar` command to run the application.<br>
      or
    - Double-click on the downloaded `InternEase.jar` file.<br>
   > **Note**<br>
   > You should see the app is running now. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `add n/TechCompany j/Software Engineer` : Adds an application for the `Software Engineer` role at `TechCompany`.

    * `list` : Shows all the internships that the user has applied.

    * `delete 2` : Deletes the 2nd internship application in the list of applications.

    * `find TechCompany` : Searches for all application with `COMPANY_NAME` and/or `JOB_TITLE` as `Google`

    * `edit_status 2 s/PENDING` : Changes the status of the 2nd application in the applications list to `Pending offer`.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Deleting an application of internship : `delete`

Deletes the specified application from the list of internships applied

Format: `delete INDEX`

* Deletes the application of internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the internship tracker.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                    |
|------------|-------------------------------------|
| **Clear**  | `clear`                             |
| **Delete** | `delete INDEX`<br> e.g., `delete 2` |
| **Exit**   | `exit`                              |