---
layout: page
title: User Guide
---

Duke Driver is a desktop app for managing delivery jobs and contacts. If you are looking to perform better at your delivery job, Duke Driver can assist you to finish your daily tasks more efficiently, according to your requirements.

* Table of Contents
   *	Feature Tracks (plan for v1.2)
         * Delivery tasking management system:
            * View jobs
            * Add jobs
         * Reminder and notifications:
            * Set reminder for upcoming deadlines
            * Get notified as soon as you open the app
         * Timetable:
            * Linked with list of jobs
            * Sort job list by date and slot
            * Display timetable of all scheduled/upcoming jobs in the week
            * Display list of invalid and completed jobs
         * Stats dashboard:
            * Display all-time statistics
            * Display previous week's statistics
            * Statistics includes
              * Total number of jobs 
              * Total earnings
              * Total number of completed jobs 
              * Total number of pending jobs


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dukeDriver.jar` in the latest release from [here](https://github.com/AY2223S2-CS2103-F11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dukeDriver.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.
   
   * `list_job` : Lists all jobs.
   
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `timetable` : Shows timetable of current week.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list_job`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## 1. Features available for Customers/Address Book
### *Can only access from Customer Window*
### 1.1. Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### 1.2. Listing all persons : `list`

Shows a list of all persons in the address book in Customer Window.

Format: `list`

### 1.3. Editing a person : `edit`

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

### 1.4. Locating persons by name: `find`

Finds and lists persons whose names contain any of the given keywords.

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

### 1.5. Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

## 2. Features available for Delivery Jobs
### *Can only access from Main Window*
### 2.1. Adding a job: `add_job`

Adds a delivery job to the delivery job system.

Format: `add_job si/SENDER_ID ri/RECEIPIENT_ID [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]`

* Adds the job to delivery job system.
* `SENDER_ID` and `RECEIPIENT_ID` **must be valid IDs** (i.e. must exist in address book).
* Delivery date **must be in format YYYY-mm-DD**.
* Delivery slot **must be a positive integer** and valid slots should be within the range from 1 to 5. 
* Slot 1: 10AM - 11AM, Slot 2: 11AM - 12PM, Slot 3: 1PM - 2PM, Slot 4: 2PM - 3PM, Slot 5: 3PM - 4PM.
* Delivery slots outside valid range will be classified as "Extra hours (4PM++)".
* Earning **must be a double**.

Examples:
* `add_job si/ALE874 ri/DAV910 date/2023-03-01 slot/3`
* `add_job si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`

### 2.2. Listing all jobs : `list_job`

Shows a list of all jobs in the delivery job system in Main Window.

Format: `list_job`

### 2.3. Listing all customers : `list`

Switch to Customer Window.
Shows a list of all persons/customers in the address book in Customer Window.

Format: `list`

### 2.4. Deleting a job : `delete_job`

Deletes the specified job by job ID from the address book.

Format: `delete JOB_ID`

* Deletes the job at the specified `JOB_ID`.
* The job ID refers to the ID of the job shown in the displayed job list.
* The ID **must be a String containing alphabets and integers** ALBE29E66F, …​
* The job ID is case sensitive.

Examples:
* `list_job` followed by `delete_job ALBE29E66F` deletes the job with ID ALBE29E66F in job list.


## 3. Features related to Reminders
### *Can access from both Main and Customer Window*
### 3.1. Listing all reminders : `list_reminder`

Shows a list of all reminders in Duke Driver.

Format: `list_reminder`

### 3.2. Adding a reminder : `add_reminder`

Adds a reminder into the address book.

Format: `add_reminder d/DESCRIPTION time/YYYY-MM-DD HH:mm`

* Adds a reminder with the specified `DESCRIPTION`.
* The reminder will be reminded from the date time specified in `time/YYYY-MM-DD HH:mm`.
* `DESCRIPTION` can be left empty.

Examples:
* `add_reminder` followed by `d/Submit homework time/2023-12-12 12:00` adds a reminder that will remind the user to submit their homework. The reminder will occur at 12pm, 12 December 2023.

### 3.3. Deleting a reminder : `delete_reminder`

Deletes a reminder in Duke Driver.

Format: `delete_reminder INDEX`

* Deletes the reminder at the specified `INDEX`.
* The index refers to the index number shown beside the reminder.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list_reminder` followed by `delete_reminder 2` deletes the 2nd reminder in the address book.

## 4. Features related to Timetable
### *Can access from both Main and Customer Window*
### 4.1. Showing timetable : `timetable`

Shows timetable of jobs, with the week shown being current week (LocalDate.now()).

Format: `timetable`

### 4.2. Showing timetable of week containing specific date: `timetable_date`

Shows timetable of specific week containing a specific date

Format: `timetable_date date/YYYY-mm-DD`

* Shows timetable of the week containing the given date.
* This is the one and only command that Timetable Window can parse/proceed/understand.

Examples:
* `timetable_date date/2023-03-16` shows timetable of jobs in week from 13th - 19th March 2023.

### 4.3. Showing list of completed jobs
Shows list of completed jobs, sorted in increasing date and decreasing earning order.

Format: `timetable_completed`

### 4.4. Showing list of unscheduled jobs
Shows list of unscheduled jobs (i.e. jobs with invalid delivery dates and/or slots).
Jobs are sorted in increasing date and decreasing earning order.

Format: `timetable_unscheduled`

## 5. Features related to Statistics 
### *Can access from both Main and Customer Window*
### 5.1. Showing Statistics : `stats`

Shows a summary of statistics related to the jobs in the job list
* Total number of jobs in the job list 
* Total earnings from all jobs in the job list 
* Total number of completed jobs in the job list 
* Total number of pending jobs in the job list 

Similar statistics are shown for jobs in the previous week

Format: `stats`

## Other features
### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Duke Driver data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke Driver data are saved as a JSON file `[JAR file location]/data/addressbook.json` and `[JAR file location]/data/deliveryjobsystem.json`. Advanced users are welcome to update data directly by editing that data file.

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
**:information_source: Notes about the command accessibility:**<br>

* Commands that start with *(C)* could only be accessed from Customer Window
* Commands that start with *(M)* could only be accessed from Main Window
* Commands that start with *(B)* could be accessed from both Main and Customer Window


Action | Format, Examples
--------|-----------------
***(C)* Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
***(C)* Clear** | `clear`
***(C)* Delete** | `delete INDEX`<br> e.g., `delete 3`
***(C)* Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
***(C)* Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
***(B)* List** | `list`
***(M)* Add Job** | `add_job si/SENDER_ID ri/RECEIPIENT_ID [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]` <br> e.g., `add_job si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`
***(M)* Delete Job** | `delete_job JOB_ID` <br> e.g., `delete_job ALBE29E66F`
***(B)* List reminder** | `list_reminder`
***(B)* Add reminder** | `add_reminder d/DESCRIPTION time/YYY-MM-DD HH:mm` <br> e.g.,`add_reminder d/Submit homework time/2023-12-12 12:00`
***(B)* Delete reminder** | `delete_reminder INDEX` <br> e.g., `delete_reminder 3`
***(B)* Show Timetable** | `timetable`
***(B)* Show Timetable of Specific Week** | `timetable_date date/YYYY-mm-DD` <br> e.g., `timetable_date date/2023-03-30`
***(B)* Show List of Completed Jobs** | `timetable_completed`
***(B)* Show List of Unscheduled Jobs** | `timetable_unscheduled`
***(B)* Shows Statistics** | `stats`
***(B)* Help** | `help`
