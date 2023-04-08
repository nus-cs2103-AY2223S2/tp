---
layout: page
title: User Guide
---

InternEase is a powerful and innovative desktop app designed to streamline the internship application process primarily
for Computer Science undergraduates. With its optimized combination of a Command Line Interface (CLI)
and Graphical User Interface (GUI), InternEase offers users the best of both worlds - the speed and efficiency of a CLI
for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface.
Whether you're a seasoned CLI user or a first-time applicant new to work environment, InternEase makes it easy
to keep track of your progress, deadlines, and follow-up actions, so you can focus on landing your dream internship.

## Features Menu
- [Quick start](#quick-start)

- [Features](#features)

- [Main Features](#main-features-tracking-applied-internships)
    - [View guide : `help`](#view-help--help)
    - [Add an internship application : `add`](#add-an-internship-application--add)
    - [List currently ongoing internship applications : `list`](#display-a-list-of-ongoing-internship-applications--list)
    - [Sort all internship applications : `sort`](#sort-all-internship-applications--sort)
    - [Find internship applications by the company name, job title, status, or interview date : `find`](#find-internship-applications-by-the-company-name-job-title-status-or-interview-date--find)
    - [Add an interview date : `add_date`](#add-an-interview-date--add_date)
    - [Manage company contact for an internship application](#add-contact-details--add_contact)
        - [Add a company contact : `add_contact`](#add-contact-details--add_contact)
        - [Edit a company contact : `edit_contact`](#edit-contact-details--edit_contact)
        - [Delete a company contact : `delete_contact`](#delete-contact-details--delete_contact)
    - [Edit the status of an internship application : `edit_status`](#edit-application-status--edit_status)
    - [Manage documents for an internship application](#add-documents--add_docs)
        - [Add documents : `add_docs`](#add-documents--add_docs)
        - [Edit documents : `edit_docs`](#edit-documents--edit_docs)
        - [Delete documents : `delete_docs`](#delete-documents--delete_docs)
    - [Archive and unarchive an internship application](#archive-an-internship-application--archive)
        - [Archive an application : `archive`](#archive-an-internship-application--archive)
        - [Unarchive an application : `unarchive`](#unarchive-an-internship-application--unarchive)
        - [List all archived applications : `list_archived`](#display-a-list-of-archived-internship-applications--list_archived)
    - [Edit an internship application : `edit`](#edit-an-internship-application--edit)
    - [Displaying reminders : `remind`](#displaying-the-internship-application-with-the-most-imminent-interview--remind)
    - [Remove entry(entries)](#delete-an-application-of-internship--delete)
        - [Delete an internship application : `delete`](#delete-an-application-of-internship--delete)
        - [Clear all internship applications : `clear`](#clearing-all-internship-application-entries--clear)
        - [Clear specific internship applications : `clear_by`](#clear-internship-application-entries-with-keyword--clear_by)
    - [Revert delete or clear](#revert-a-recent-deleted-internship-application--revert)
        - [Revert the most recent delete command : `revert`](#revert-a-recently-deleted-internship-application--revert)
        - [Revert all delete and clear commands : `revert_all`](#revert-all-recently-deleted-or-cleared-internship-applications--revert_all)
    - [Exit InternEase : `exit`](#exiting-the-program--exit)

- [Side Features](#side-features-planning-to-apply-internships)
    - [Task (todo and notes)](#display-lists-of-tasks-todos-and-notes--list_task)
        - [List current available tasks : `list_task`](#display-lists-of-tasks-todos-and-notes--list_task)
        - [Find specific tasks : `find_task`](#search-for-a-task-todo-and-notes--find_task)
    - [Todo](#display-a-list-of-todo-internship-applications--list_todo)
        - [List current available todo internship applications : `list_todo`](#display-a-list-of-todo-internship-applications--list_todo)
        - [Add a todo internship application : `add_todo`](#add-a-todo-application--add_todo)
        - [Edit the deadline of a todo task : `edit_deadline`](#edit-todo-application-deadline--edit_deadline)
        - [Edit the note content of a todo task : `edit_content`](#edit-todo-note-content--edit_content)
        - [Delete a todo task : `delete_todo`](#delete-a-todo-application--delete_todo)
        - [Clear all todo tasks : `clear_todo`](#clear-all-todo-application-entries--clear_todo)
    - [Note](#display-list-of-short-note--list_note)
        - [List current saved notes : `list_note`](#display-list-of-short-note--list_note)
        - [Add a note : `add_note`](#add-a-note-add_note)
        - [Delete a note : `delete_note`](#delete-a-note--delete_note)
        - [Clear all notes : `clear_note`](#clear-all-notes---clear_note)
      
- [FAQ](#faq)

- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

> **Note**<br>
> This is a desktop app.

1. Have Java `11` or above installed in local laptop or Computer.

2. Download the latest version (InternEase v1.3) of `internease.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-4/tp/releases).<br>

3. Copy the file to the folder you want to use as the _home folder_ for your InternEaseApp.

4. Start the app by:
    - Opening a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar internease.jar` command to run the program.<br>
      or
    - Double-clicking the downloaded `internease.jar` file.<br>
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
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* >**Note:** <br/>
  > InternEase has 4 window interfaces which include the internship application list, the todo list, the note list and the task list. 
  > All the commands can be used in any interface. If the command for a different interface is executed in current interface, the current interface will switch to the respective interface and display the result of the command.

</div>

## Main features: Tracking applied internships

### View help : `help`
Shows user the link to user guide.

Format: `help`

### Add an internship application : `add`

Adds an internship application to the tracker

Format: `add n/COMPANY_NAME j/JOB_TITLE [l/LOCATION] [s/SALARY] [rate/RATING] [q/QUALIFICATION]... [p/PROGRAMMINGLANGUAGE]... [r/REVIEW]... [note/NOTE]... [reflect/REFLECTION]...`
- `SALARY` should be in the form of amount followed by a space and then the currency in upper case.


Examples:
* `add n/Facebook j/Product Manager` adds an application for the Product Manager role at Facebook.
* `add n/LinkedIn j/Software Engineer s/2000 SGD` adds an application for the Software Engineer role at LinkedIn with salary 2000 SGD.

### Edit an internship application : `edit`

Edits the internship .

Format: `edit INDEX [n/COMPANY_NAME] [j/JOB_TITLE] [l/LOCATION] [s/SALARY] [rate/RATING] [q/QUALIFICATION]... [p/PROGRAMMINGLANGUAGE]... [r/REVIEW]... [note/NOTE]... [reflect/REFLECTION]...`

- Edits the internship application at the specified `INDEX`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- If `COMPANY_NAME` or `JOB_TITLE` is empty in the form, they will retain the former value, but the `COMPANY_NAME` or `JOB_TITLE` in CLI command cannot be empty.
- Other attribute can be left as empty string.

Examples:
* `edit 1 q/Singapore citizen q/Pursuing CS degree` updates the qualification of the internship with first index to `Singapore citizen` and `Pursuing CS degree`.
* `edit 2 n/LinkedIn j/Data Engineer` updates the second internship company name to `LinkedIn` and job title to `Data Engineer`.

### Add an interview date : `add_date`

Adds an interview date and time to an internship application.

Format: `add_date INDEX d/DATE_TIME`

- Adds an interview date to the internship application at the specified `INDEX`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- `DATE_TIME` should be a valid date time of the format `yyyy-MM-dd hh:mm a`, where `a` is either `AM` or `PM`, and the date and time must be after the current date and time.

Examples:
* `add_date 1 d/2023-05-02 11:30 AM` adds the date and time 2023-05-02 11:30 AM to the first application in the list of applications.
* `add_date 2 d/2023-07-03 12:30 PM` adds the date and time 2023-07-03 12:30 PM to the second application in the list of applications.

### Add contact details : `add_contact`

Adds the contact details of a company to a specified application.

Format: `add_contact INDEX p/PHONE_NUMBER e/EMAIL`

- Adds contact details to the internship application at the specified `INDEX`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- **Both** the phone number and email must be provided.
- `PHONE_NUMBER` should be a valid phone number of at least 3 digits.
- `EMAIL` should be a valid email of the format `username@domain.com`.

Examples:
* `add_contact 1 p/87654321 e/abc@gmail.com` adds the contact number `87654321` and email `abc@gmail.com` to the 1st application in the list of applications.
* `add_contact 2 p/65432100 e/someemail@gmail.com` adds the contact number `65432100` and the email `someemail@gmail.com` to the 2nd application in the list of applications.

### Edit contact details : `edit_contact`

Edits the contact details of a company previously added to a specified application.

Format: `edit_contact INDEX [p/PHONE_NUMBER] [e/EMAIL]`

- Edits contact details of the internship application at the specified `INDEX`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- **At least one** field should be provided.
- `PHONE_NUMBER` should be a valid phone number of at least 3 digits.
- `EMAIL` should be a valid email of the format `username@domain.com`.

Examples:
* `edit_contact 1 p/87654321 e/abc@gmail.com` updates the contact number and email of the company to `87654321` and `abc@gmail.com` respectively for the 1st application in the list of applications.
* `edit_contact 2 e/someemail@gmail.com` updates the email of the company to `someemail@gmail.com` for the 2nd application in the list of applications.
* `edit_contact 3 p/12345678` updates the contact number of the company to `12345678` for the 3rd application in the list of applications.

### Delete contact details : `delete_contact`

Deletes the contact details of a company previously added to a specified application.

Format: `delete_contact INDEX`

- Deletes contact details to the internship application at the specified `INDEX`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
* `delete_contact 1` deletes the contact number and email of the company for the 1st application in the list of applications.

### Edit application status : `edit_status`

Edits the application status.

Format: `edit_status INDEX s/STATUS`
- Edits the status of the specified `INDEX` to the specified `STATUS`.
- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- Available status: PENDING, RECEIVED, ACCEPTED, DECLINED, REJECTED
    - PENDING: Internship application submitted, outcome has not been released.
    - RECEIVED: Offer received.
    - ACCEPTED: Offer accepted.
    - DECLINED: Offer received and declined.
    - REJECTED: Application rejected.

Examples:
* `edit_status 2 s/PENDING` Changes the status of the 2nd application in the applications list to `PENDING` (Internship application submitted, outcome has not been released).

### Add documents : `add_docs`

Adds documents including a resume link and a cover letter link to a specified application.

Format: `add_docs INDEX rs/RESUME_LINK cl/COVER_LETTER_LINK`

- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- **Both** the resume link and the cover letter link must be provided.
- `RESUME_LINK` must be a valid URL in the format `http://domain/path` or `https://domain/path`.
- `COVER_LETTER_LINK` must be a valid URL in the format `http://domain/path` or `https://domain/path`.

Examples:
* `add_docs 1 rs/https://www.example.com/resume cl/https://www.example.com/coverletter` adds the resume link `https://www.example.com/resume`
  and cover letter link `https://www.example.com/coverletter` to the 1st application in the list of applications.
* `add_docs 2 rs/https://www.goodresume.com/myresume cl/https://www.goodcoverletter.com/mycoverletter` adds the resume link `https://www.goodresume.com/myresume`
  and cover letter link `https://www.goodcoverletter.com/mycoverletter` to the 2nd application in the list of applications.

### Edit documents : `edit_docs`

Edits the documents which include the resume link and cover letter link previously added to a specified application.

Format: `edit_docs INDEX [rs/RESUME_LINK] [cl/COVER_LETTER_LINK]`

- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​
- **At least one** field should be provided.
- `RESUME_LINK` must be a valid URL in the format `http://domain/path` or `https://domain/path`.
- `COVER_LETTER_LINK` must be a valid URL in the format `http://domain/path` or `https://domain/path`.

Examples:
* `edit_docs 1 rs/https://www.example.com/resume cl/https://www.example.com/coverletter` updates the resume link and cover letter link to `https://www.example.com/resume`
  and `https://www.example.com/coverletter` respectively for the 1st application in the list of applications.
* `edit_docs 2 rs/https://www.example.com/resume` updates the resume link to `https://www.example.com/resume` for the 2nd application in the list of applications.
* `edit_docs 3 cl/https://www.example.com/coverletter` updates the cover letter link to `https://www.example.com/coverletter` for the 3rd application in the list of applications.

### Delete documents : `delete_docs`

Deletes the documents previously added to a specified application.

Format: `delete_docs INDEX`

- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
* `delete_docs 1` deletes the documents for the 1st application in the list of applications.

### Archive an internship application : `archive`

Archives a specified application so that it would be hidden from the list of ongoing applications.

Format: `archive INDEX`

- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
* `archive 1` archives the 1st application in the list of applications.

### Unarchive an internship application : `unarchive`

Unarchives a specified application that was previously archived so that it would be shown in the list of ongoing applications.

Format: `unarchive INDEX`

- The index refers to the index number shown in the displayed internship list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
* `unarchive 1` unarchives the 1st application in the list of applications.

### Display a list of archived internship applications : `list_archived`

Displays a list of archived applied internships

Format: `list_archived`

Examples:

* `list_archived` shows all the archived internship applications with 1 indexing.
* If there are no archived internship applications at the moment,
  "No archived applications at the moment" will be shown.

### Delete an application of internship : `delete`

Deletes the specified internship application from the list of internships applied

Format: `delete INDEX`

* Deletes the application of internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` Deletes the 2nd internship application in the list of applications.

### Clear internship application entries with keyword : `clear_by`

Clear all relevant internship application entries from the internship tracker with specific keyword

Format: `clear_by n/COMPANY_NAME` OR `clear_by j/JOB_TITLE` OR `clear_by s/STATUS`

* Clears all internship applications with the specified keyword - `COMPANY_NAME`, `JOB_TITLE` or `STATUS`.
* As a protective approach, only internship applications with desired particulars that are **fully matched** with the entire, case-sensitive keyword will be cleared.
* Three types of clear_by features are provided, they can only be executed independently.

Examples:
* `clear_by n/Meta` Clears all application with COMPANY_NAME as Meta.
* `clear_by j/Software engineer` Clears all application with JOB_TITLE as Software Engineer.
* `clear_by s/REJECTED` Clears all rejected application (with STATUS as REJECTED).

### Display a list of ongoing internship applications : `list`

Displays a list of applied internships which are ongoing

Format: `list`

Examples:

* `list` shows all the ongoing internship applications for with 1 indexing.
* If there are no ongoing internship applications at the moment,
  "No applications at the moment" will be shown.


### Sort all internship applications : `sort`

Sorts internship applications according to either company name, job title, status or interview date in ascending order.

Format: `sort PREFIX`
1. Sort by company name: `sort n/`
2. Sort by job title: `sort j/`
3. Sort by status: `sort s/`
4. Sort by interview date `sort d/`

Example:
* `sort d/` sorts all applications with their interview date in ascending order, those without interview date available
  yet will be placed at the end of the list.


### Find internship applications by the company name, job title, status, or interview date : `find`

Find all internship applications (including those that have been archived) by its company name, job title,
status and/or interview date.

Format:
There are three use cases for the `find` command:
1. Find by the application's company name and/or job title: `find KEYWORD [MORE KEYWORDS]`
2. Find by the current status of the application: `find s/STATUS`
3. Find by upcoming interview date: `find before/DATE`, `find after/DATE_TIME`, `find from/DATE_TIME1 to/DATE_TIME2`

- The search for company name, job title, and status are case-insensitive. 
- The order of KEYWORD doesn't matter.
- In use case 1, as long as a single word in company name and/or job title matches one of the KEYWORD's, it 
will be shown to user. E.g. `JP Morgan` and `goldman Sachs` matches the keyword in `find JP Morgan Goldman Sachs`.
- Only full word will be matched. E.g. `goldman Sachs` won't match `find GOLD`.

Examples:
* `find Google` searches for all application with `COMPANY_NAME` and/or `JOB_TITLE` as Google.
* `find s/PENDING` searches for all application that are pending.
* `find after/2023-12-02 12:30 PM` searches for all application that are having interview after 
2023-12-02 12:30 PM (inclusive).

### Displaying the internship application with the most imminent interview : `remind`

Displays the details of an internship application with the earliest date in a pop up window, with reference to the 
current date and time.
  
Format: `remind`

### Clearing all internship application entries : `clear`

Clears all internship application entries from the internship tracker.

Format: `clear`

### Revert a recently deleted internship application : `revert`

Reverts recent delete command and restores the relevant data to the end of the current internship applications list.

Format: `revert`

Examples:
1. Assume the most recent delete command was `delete 2` which has data `n/Tech j/Job`, the data was removed from the applications list.
2. Command `revert` restores the entries at the back the application list, which has effect similar to `add  n/Tech j/Job`.
   
**This command is only able to restore current session's data, all the deleted / cleared data will be permanently deleted if command `exit` is executed.**

### Revert all recently deleted or cleared internship applications : `revert_all`

Reverts all recent delete command or clear command and restores the affected data back to the end of the current internship applications list.

Format: `revert_all`

**This command is only able to restore current session's data, all the deleted / cleared data will be permanently deleted if command `exit` is executed.**

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Side features: Planning to apply internships

### Display lists of tasks (todos and notes) : `list_task`

Displays the todo list and the note list together

Format: `list_task`

Examples:

* `list_task` shows all the todos and notes that the user has written all together in one window.
* If there are no todo and note at the moment, `No task (todo and note) at the moment` will be shown in the result dialog.

### Search for a task (todo and notes) : `find_task`

Searches the recorded lists of todos and notes by keyword (company name in todos and note content in notes)

Format: `find_task KEYWORD`

Searches for the todos or notes with the specified case-insensitive `KEYWORD`.
The keyword refers to the company name in todos or the note content in notes that the user intends to look for.

Examples:
`find_task test week` searches for all todos with `COMPANY_NAME` or all notes with `NOTE_CONTENT` that contain `test` or `week`.

### Display a list of todo internship applications : `list_todo`

Displays a list of todo applications (todo internship application)

Format: `list_todo`

Examples:

* `list_todo` shows all the todo applications that the user has recorded.
* If there are no todo applications for at the moment, `No todo at the moment` will be shown.

### Add a todo application : `add_todo`

Adds a todo internship application to the todo list

Format: `add_todo n/COMPANY_NAME j/JOB_TITLE by/DEADLINE`
- `DEADLINE` should be in the format yyyy-mm-dd.
- `DEADLINE` should not be earlier than the date when the todo application is created.

Examples:
* `add_todo n/Facebook j/Product Manager by/2023-06-07` adds a todo application for the Product Manager role at Facebook. The internship should be applied by 7 June 2023.
* `add_todo n/LinkedIn j/Software Engineer by/2023-10-04` adds a todo application for the Software Engineer role at LinkedIn.The internship should be applied by 4 October 2023.

### Edit todo application deadline : `edit_deadline`

Edits the deadline of the specified todo from current available todo list

Format: `edit_deadline INDEX by/DEADLINE`
- Edits the deadline of the specified `INDEX` to the specified `DEADLINE`.
- The index refers to the index number shown in the displayed todo list.
- The index must be a positive integer 1, 2, 3, …​
- `DEADLINE` should be in the format yyyy-mm-dd.
- `DEADLINE` should not be earlier than the date when the todo application is created.

Examples:
* `edit_deadline 2 by/2023-07-06` Changes the deadline of the 2nd todo application in the todo list to `2023-07-06` (6 July 2023).

### Edit todo note content : `edit_content`

Edits the note content of the specified todo from current available todo list

Format: `edit_content INDEX c/NOTE_CONTENT`
- Edits the note content of the specified `INDEX` to the specified `NOTE_CONTENT`.
- The index refers to the index number shown in the displayed todo list.
- The index must be a positive integer 1, 2, 3, …​
- **Note content is an optional field for todo applications**
- `NOTE_CONTENT` is empty (null) in default.
- `NOTE_CONTENT` can take 1 to 55 characters.

Examples:
* `edit_content 2 c/Venue changed` Changes the note content of the 2nd todo application in the todo list to `Venue changed`.

### Delete a todo application : `delete_todo`

Deletes the specified todo application from the todo list

Format: `delete_todo INDEX`

* Deletes the todo application at the specified `INDEX`.
* The index refers to the index number shown in the displayed todo list.
* The index **must be a positive integer** 1, 2, 3, …​
* **Note that this action is irreversible**

Examples:
* `delete_todo 2` Deletes the 2nd todo application in the todo list.

### Clear all todo application entries : `clear_todo`

Clears all todo application entries from the todo applications list

Format: `clear_todo`

**Note that this action is irreversible**

### Display list of short note : `list_note`

Displays a list of saved notes

Format: `list_note`

Examples:
* `list_note` shows all the notes that the user has written.
* If there are no internships applied for at the moment, `No note at the moment` will be shown.

### Add a note: `add_note`

Adds a note to the note list.

Format: `add_note c/NOTE_CONTENT`
- `NOTE_CONTENT` can take 1 to 55 characters.

Examples:
* `add_note c/Focus on software engineering jobs!` adds a note with content `Focus on software engineering jobs!` into the note list.

### Delete a note : `delete_note`

Deletes the specified note from the list of notes

Format: `delete_note INDEX`

* Deletes the note at the specified `INDEX`.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​
* **Note that this action is irreversible**

Examples:
* `delete_note 2` Deletes the 2nd note in the list of notes.

### Clear all notes  : `clear_note`

Clears all notes from the note.

Format: `clear_note`

**Note that this action is irreversible**

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: How do I update the statistics at bottom right corner?<br>
**A**: It's automatically update after you execute every command / action via either CLI or GUI.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/COMPANY_NAME j/JOB_TITLE [l/LOCATION] [s/SALARY] [rate/RATING] [q/QUALIFICATION]... [p/PROGRAMMINGLANGUAGE]... [r/REVIEW]... [note/NOTE]... [reflect/REFLECTION]...` <br> e.g., `add n/LinkedIn j/Software Engineer s/2000 SGD` 
**Add Contact** | `add_contact INDEX p/PHONE_NUMBER e/EMAIL` <br> e.g., `add_contact 1 p/87654321 e/abc@gmail.com`
**Add Documents** | `add_docs INDEX rs/RESUME_LINK cl/COVER_LETTER_LINK` <br> e.g., `add_docs 1 rs/https://www.example.com/resume cl/https://www.example.com/coverletter`
**Add Interview Date** | `add_date INDEX d/DATE_TIME` <br> e.g., `add_date 1 d/2023-05-02 11:30 AM`
**Add Note** |`add_note c/NOTE_CONTENT` <br> e.g., `add_note c/The tasks are planned to be done by tomorrow!`
**Add Todo** |`add_todo n/COMPANY_NAME J/JOB_TITLE by/DEADLINE` <br> e.g., `add_todo n/company j/Manager d/2023-09-08`
**Archive** | `archive INDEX`<br> e.g., `archive 2`
**Clear**  | `clear`
**Clear_by**  | `clear_by n/COMPANY_NAME` <br> `clear_by j/JOB_TITLE` <br> `clear_by s/STATUS`
**Clear Note** |`clear_note`
**Clear Todo** |`clear_todo`
**Delete** | `delete INDEX`<br> e.g., `delete 2`
**Delete Contact** | `delete_contact INDEX` <br> e.g., `delete_contact 2`
**Delete Documents** | `delete_docs INDEX` <br> e.g., `delete_docs 2`
**Delete Note** |`delete_note INDEX` <br> e.g., `delete_note 2`
**Delete Todo** |`delete_todo INDEX` <br> e.g., `delete_todo 2`
**Edit** | `edit INDEX [n/COMPANY_NAME] [j/JOB_TITLE] [l/LOCATION] [s/SALARY] [rate/RATING] [q/QUALIFICATION]... [p/PROGRAMMINGLANGUAGE]... [r/REVIEW]... [note/NOTE]... [reflect/REFLECTION]...` <br> e.g., `edit 1 q/Singapore citizen q/Pursuing CS degree` 
**Edit Contact** | `edit_contact INDEX [p/PHONE_NUMBER] [e/EMAIL]` <br> e.g., `edit_contact 3 p/98765432 e/def@gmail.com`
**Edit Documents** | `edit_docs INDEX [rs/RESUME_LINK] [cl/COVER_LETTER_LINK]` <br> e.g., `edit_docs 2 rs/https://www.goodresume.com/myresume cl/https://www.goodcoverletter.com/mycoverletter`
**Edit Deadline** |`edit_deadline INDEX by/DEADLINE` <br> e.g., `edit_deadline 2 by/2023-06-05`
**Edit Note Content** |`edit_content c/NOTE_CONTENT` <br> e.g., `edit_content 2 c/Venue changed`
**Edit Status** | `edit_status INDEX s/STATUS` <br> e.g., `edit_status 2 s/PENDING`
**Exit**   | `exit`
**Find Applications** | `find KEYWORD [MORE KEYWORDS]` <br> e.g., `find Google` <br> `find s/STATUS` <br> e.g., `find s/PENDING` <br> `find before/DATE`, `find after/DATE_TIME`, `find from/DATE_TIME1 to/DATE_TIME2` <br> e.g., `find before/2023-01-31 12:45 PM`
**Find Task** |`find_task KEYWORD`<br> e.g., `find_task test`
**Help** | `help`
**List** |`list`
**List Archived Applications** |`list_archived`
**List Note** |`list_note`
**List Task** |`list_task`
**List Todo** |`list_todo`
**Reminder** | `remind`
**Revert**   | `revert`
**Revert All**   | `revert_all`
**Sort Applications** | `sort n/` <br> `sort j/` <br> `sort s/` <br> `sort d/`
**Unarchive** | `unarchive INDEX`<br> e.g., `unarchive 2`
