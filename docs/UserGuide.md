---
layout: page
title: User Guide
---

InternEase is a powerful and innovative desktop app designed to streamline the internship application process for Computer Science undergraduates. With its optimized combination of a Command Line Interface (CLI) and Graphical User Interface (GUI), InternEase offers users the best of both worlds - the speed and efficiency of a CLI for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface. Whether you're a seasoned CLI user or a first-time applicant, InternEase makes it easy to keep track of your progress, deadlines, and follow-up actions, so you can focus on landing your dream internship.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding contact details: `add_contact`

Adds the contact details of a company to a specified application.

Format: `add_contact INDEX p/PHONE NUMBER e/EMAIL`

Examples:
* `add_contact INDEX p/87654321 e/abc@gmail.com` adds the contact number `87654321` and email `abc@gmail.com` to the application specified by the `INDEX` in the list of applications.
* `add_contact INDEX e/someemail@gmail.com` adds the email `someemail@gmail.com` to the application specified by the `INDEX` in the list of applications.

### Edit application status : `edit_status`

Edits the application status.

Format: `edit_status INDEX s/STATUS`
- Edits the status of the specified `INDEX` to the specified `STATUS`.
- The index refers to the index number shown in the displayed internship list. 	
- The index must be a positive integer 1, 2, 3, …​
- Available status: NA, PENDING, RECEIVED, REJECTED, NO
  - NA: Internship application is not submitted.
  - PENDING: Internship application submitted, outcome has not been released.
  - RECEIVED: Offer received.
  - REJECTED: Offer rejected.
  - NO: Application rejected.

Examples:
* `edit_status 2 s/PENDING` Changes the status of the 2nd application in the applications list to `PENDING` (Internship application submitted, outcome has not been released).

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Contact** | `add_contact INDEX p/PHONE NUMBER e/EMAIL` <br> e.g., `add_contact INDEX p/87654321 e/abc@gmail.com `
**Edit Status** | `edit_status INDEX s/STATUS` <br> e.g., `edit_status 2 s/PENDING`