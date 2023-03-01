---
layout: page
title: User Guide
---

InternBuddy is a **desktop app for Computing undergraduate students to manage their internship applications**. It is **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternBuddy can efficiently track your internship applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : List All Internship Entries.

   * `add n/CompanyXYZ r/Software engineering intern s/applied d/2023-03-03` : Adds an entry to track the internship application for Company XYZ for a software engineering role.

   * `delete 3` : Deletes the 3rd internship entry shown in the current list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add n/Apple`.

* Items in square brackets are optional.<br>
  e.g `edit INDEX [n/NAME]` can be used as `edit 2 n/CompanyXYZ` or as `edit 2`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/Apple r/Software Engineer`, `r/Software Engineer n/Apple` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For any parameters that refer to a date (such as `add`, `edit`), they must be specified in the format YYYY-MM-DD<br>
e.g. if the command specifies `edit INDEX [d/DATE]`, then 1 March 2023 should be entered as `2023-03-01` for the parameter `DATE`.

</div>


_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InternBuddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List** | `list`
**Add** | `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE​` <br> e.g., `add n/Apple r/Software Engineer s/new`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [r/ROLE] [s/STATUS] [d/DATE]​`<br> e.g.,`edit 2 s/assessment r/SoftWare Developer`
**Help** | `help`
**Exit** | `exit`


