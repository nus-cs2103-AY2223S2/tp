---
layout: page
title: User Guide
---

InternEase is a powerful and innovative desktop app designed to streamline the internship application process for Computer Science undergraduates. With its optimized combination of a Command Line Interface (CLI) and Graphical User Interface (GUI), InternEase offers users the best of both worlds - the speed and efficiency of a CLI for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface. Whether you're a seasoned CLI user or a first-time applicant, InternEase makes it easy to keep track of your progress, deadlines, and follow-up actions, so you can focus on landing your dream internship.

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
    - Opening a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar InternEase.jar` command to run the program.<br>
      or
    - Double-clicking the downloaded `InternEase.jar` file.<br>
   > **Note**<br>
   > You should see the app is running now. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `add n/TechCompany j/Software Engineer` : Adds an application for the `Software Engineer` role at `TechCompany`.

    * `list` : Displays all the internships that the user has applied for.

    * `delete 2` : Deletes the second internship application in the list of applications.

    * `find TechCompany` : Searches for all application with `COMPANY_NAME` and/or `JOB_TITLE` as `Google`

    * `edit_status 2 s/PENDING` : Changes the status of the 2nd application in the applications list to `Pending offer`.

    * `exit` : Exits the application.

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

### Main features: Tracking applied internships

### Viewing help: `help`
Shows a user guide message on how to use the internship tracker.

Format: help

### Displaying list of internship applications:`list`

Displays a list of applied internships

Format: `list`

Examples:

* `list` shows all the internships that the user has applied for with 1 indexing.
* If there are no internships applied for at the moment,
  "No applications at the moment" will be shown.

### Adding an application: `add`

Adds an application to the internship tracker.

Format: ` add n/COMPANY_NAME j/JOB_TITLE`

Examples:
* `add n/Facebook j/Product Manager` adds an application for the Product Manager role at Facebook.
* `add n/LinkedIn j/Software Engineer` adds an application for the Software Engineer role at LinkedIn.

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

### Deleting an application of internship : `delete`

Deletes the specified internship application from the list of internships applied

Format: `delete INDEX`

* Deletes the application of internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` Deletes the 2nd internship application in the list of applications.

### Clearing internship application entries with keyword: `clear_by`

Clear all relevant internship application entries from the internship tracker with specific keyword

Format: `clear_by n/COMPANY_NAME` OR `clear_by j/JOB_TITLE` OR `clear_by s/STATUS`

* Clears all internship applications with the specified keyword - COMPANY_NAME, JOB_TITLE or STATUS.
* Three types of clear_by features are provided, but can only execute independently.

Examples:
* `clear_by n/Meta` Clears all application with COMPANY_NAME as Meta.
* `clear_by j/Software engineer` Clears all application with JOB_TITLE as Software Engineer.
* `clear_by s/REJECTED` Clears all rejected application (with STATUS as REJECTED).

### Search for an application : `find`
Searches the list of internships applied by keyword (status, role, company)

Format: `find KEYWORD`

Searches for the application with the specified `KEYWORD`.
The keyword refers to the status, role or company that the user intends to look for.

Examples:
`find Google` searches for all application with `COMPANY_NAME` and/or `JOB_TITLE` as Google

### Clearing all internship application entries : `clear`

Clears all internship application entries from the internship tracker.

Format: `clear`

### Revert recent deleted an internship application : `revert`

Reverts recent delete command and restores the relevant data to the end of the current internship applications list.

Format: `revert`

Examples:
1. Assume the most recent delete command was `delete 2` which has data `n/Tech j/Job`, the data was removed from the applications list.
2. Command `revert` restores the entries at the back the application list, which has effect similar to `add  n/Tech j/Job`.
** This command is only able to restore current session's data, all the deleted / cleared data will be permanently deleted if command `exit` is executed.**

### Revert recent deleted or cleared internship applications : `revert_all`

Reverts all recent deleted command or cleared command and restores the affected data back to the end of the current internship applications list.

Format: `revert_all`

** This command is only able to restore current session's data, all the deleted / cleared data will be permanently deleted if command `exit` is executed.**

### Side features: Planning to apply internships 

### Displaying list of tasks (todos and notes):`list_task`

Displays the todo list and the note list together.

Format: `list`

Examples:

* `list_task` shows all the todos and notes that the user has written for together in one window.
* If there are no todo and note at the moment, `No task (todo and note) at the moment` will be shown.
* If there is either no todo or note, it will show `No todo at the moment` or `No note at the moment` respectively. The other list will be displayed normally.

### Search for a task (todo and notes) : `find_task`

Searches the recorded lists of todos and notes by keyword (status, role, company)

Format: `find KEYWORD`

Searches for the application with the specified `KEYWORD`.
The keyword refers to the status, role or company that the user intends to look for.

Examples:
`find Google` searches for all application with `COMPANY_NAME` and/or `JOB_TITLE` as Google

### Displaying list of internship applications:`list_todo`

Displays a list of applied internships

Format: `list`

Examples:

* `list` shows all the internships that the user has applied for with 1 indexing.
* If there are no internships applied for at the moment,
  "No applications at the moment" will be shown.

### Adding an application: `add`

Adds an application to the internship tracker.

Format: ` add n/COMPANY_NAME j/JOB_TITLE`

Examples:
* `add n/Facebook j/Product Manager` adds an application for the Product Manager role at Facebook.
* `add n/LinkedIn j/Software Engineer` adds an application for the Software Engineer role at LinkedIn.

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

### Deleting an application of internship : `delete`

Deletes the specified internship application from the list of internships applied

Format: `delete INDEX`

* Deletes the application of internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` Deletes the 2nd internship application in the list of applications.

### Clearing all internship application entries : `clear`

Clears all internship application entries from the internship tracker.

Format: `clear`

### Displaying list of internship applications:`list_note`

Displays a list of applied internships

Format: `list`

Examples:

* `list` shows all the internships that the user has applied for with 1 indexing.
* If there are no internships applied for at the moment,
  "No applications at the moment" will be shown.

### Adding an application: `add`

Adds an application to the internship tracker.

Format: ` add n/COMPANY_NAME j/JOB_TITLE`

Examples:
* `add n/Facebook j/Product Manager` adds an application for the Product Manager role at Facebook.
* `add n/LinkedIn j/Software Engineer` adds an application for the Software Engineer role at LinkedIn.

### Deleting an application of internship : `delete`

Deletes the specified internship application from the list of internships applied

Format: `delete INDEX`

* Deletes the application of internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` Deletes the 2nd internship application in the list of applications.

### Clearing all internship application entries : `clear`

Clears all internship application entries from the internship tracker.

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

Action | Format, Examples
--------|------------------
**Add Contact** | `add_contact INDEX p/PHONE NUMBER e/EMAIL` <br> e.g., `add_contact INDEX p/87654321 e/abc@gmail.com`
**Clear**  | `clear`                             
**Clear_by**  | `clear_by n/COMPANY_NAME` OR `clear_by j/JOB_TITLE` OR `clear_by s/STATUS`
**Delete** | `delete INDEX`<br> e.g., `delete 2`                              
**Edit Status** | `edit_status INDEX s/STATUS` <br> e.g., `edit_status 2 s/PENDING`
**Exit**   | `exit` 
**List** |`list`
