---
layout: page
title: User Guide
---

Duke Driver is a desktop app for managing delivery jobs and contacts. If you are looking to perform better at your delivery job, Duke Driver can assist you to finish your daily tasks more efficiently, according to your requirements.

* Table of Contents
   *	Features
     * Address book - Customers contacts management system:
       * List and view contacts
       * Add contacts individually
       * Find contacts
       * Delete contacts
       * Edit contacts
       * Clear all contacts
     * Delivery tasking management system:
       * View jobs
       * Add jobs individually
       * Mass import jobs and associated customers
       * Find jobs
       * Mark job as done
       * Delete jobs
       * Edit jobs
     * Reminder and notifications:
       * Set reminder for upcoming deadlines
       * Get notified as soon as you open the app
       * Timetable:
       * Linked with list of jobs
       * Sort job list by date and slot
     * Display timetable of all scheduled/upcoming jobs in the week
       * Display list of invalid and completed jobs
     * Statistics dashboard:
       * Show statistics on:
       * Total number of jobs
       * Pending jobs
       * Completed jobs
       * Total earnings
       * Statistics from previous week


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dukeDriver.jar` in the latest release from [here](https://github.com/AY2223S2-CS2103-F11-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dukeDriver.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `list_job` : Lists all jobs.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `timetable` : Shows timetable of current week.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

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




## 1. Features related to Customers
### *Can only access from Customer Window*
To access the address book containing all customers, please click on `Customers` in menu bar > `Address Book`.
![address book](images/Dukedeliveryaddressbook.png)

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

### 1.6. Clearing all contacts in Address Book : `clear`

Clears all entries from the address book.

Format: `clear`

## 2. Features related to Delivery Jobs
### *Can only access from Main Window*
Commands are input in this text field and app response is printed below the text field.
![input field](images/Inputcommands.png)

### 2.1. Adding a job: `add_job`

Adds a delivery job to the delivery job system.

Format: `add_job si/SENDER_ID ri/RECEIPIENT_ID [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]`

Alternative: Click on `Delivery Job System` in menu bar > `Create Job`. Fill in relevant details and click `Create Job` button. (Click `Cancel` button to stop adding)
![Create Job](images/Addjob.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Description field is only available in GUI mode.
</div>

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




### 2.2. Mass importing jobs

Format: Click on `Delivery Job System` in menu bar > `Import Jobs` > select a file containing all the jobs to be imported

* File **must be a CSV file**.
* There must be a header row as the first row will be skipped when file is parsed.
* These columns `Recipient`	`Sender`	`Delivery date`	`Delivery slot`	`Price`	`Description`	`Recipient`	`Recipient's Name`	`Recipient's Phone`	`Recipient's Email`	`Recipient's Address`	`Recipient's Tag`	`Sender's	Name`	`Sender's Phone`	`Sender's Email`	`Sender's Address`	`Sender's Tag` must exist.
  * The optional details may be empty cells. e.g. `Sender's Tag` may be empty.
* If recipient/ sender does not already exist in customer address book i.e. new customer, recipient/ sender will also be added into the address book.

### 2.3. Listing all jobs : `list_job`

Shows a list of all jobs in the delivery job system in Main Window.

Format: `list_job`

![list job](images/listJob.png)

### 2.3.1. Sort and filter jobs

![sort filter job](images/listJobSortFilter.png)


### 2.4 Edit job : `edit_job`

Edit a selected job by Index or Job id.

Format: `edit_job INDEX [si/SENDER_ID] [ri/RECEIPIENT_ID] [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING] [done/ t | f]`

Alternative: Click on the delivery job > Click on the middle pen button ![pen](images/penbutton.png) on top right hand corner > Make changes > Click on `Edit Job` button


* Existing values will be overwritten by the input values.
* Edits the delivery job at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Refer to `list_job` for argument constrains.

Examples:
* `edit_job 1 slot/4`
* `edit_job 1 date/2023-03-01`
* `edit_job ji/<job_id> si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`


### 2.5 Find job : `find_job`

Finds all jobs which contain any of the specified keywords and displays them as a list with index numbers.

Format: `find_job [ji/JOB_ID] [si/SENDER_ID] [ri/RECIPIENT_ID] [date/DELIVER_DATE] [slot/DELIVERY_SLOT] [earn/EARNING] [done/ t | f]`

* There can be multiple keywords.
* Keywords are case-sensitive.
* Refer to `list_job` for argument constrains.

Examples:
* `find_job ji/<job_id>`
* `find_job date/2023-03-01 slot/4`
* `find_job ji/<job_id> si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`

### 2.6 Delete job : `delete_job`

Deletes the job identified by the job ID. Support `del` key in job list.

Format: `delete_job ji/JOB_ID`

Alternative: Click on the delivery job > Click on the right bin button ![delete](images/deletebutton.png) on top right hand corner

* The job id must be **valid**.

Example:
* `delete_job ALBE29E66F`

### 2.7 Complete job : `com_job` / `uncom_job`

Completes or uncompletes the job identified by the job ID.

Format: `com_job ji/JOB_ID` / `uncom_job ji/JOB_ID`

Alternative 1: Scroll to the job and click on the circle on the left, which a tick will appear in to show completion. Click again to uncomplete the job.
![checked](images/Checked.png)

Alternative 2: Click on the delivery job > Click on the left tick button ![complete](images/completebutton.png) on top right hand corner. Click on the button again to uncomplete job.

Example:
* `com_job ALBE29E66F`
* `uncom_job ALBE29E66F`

### 2.8. Clearing all contacts in Address Book : `clear`

Clears all entries from the address book.

Format: `clear`


## 3. Features available for Reminders
### *Can only access from Main Window*
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


## 4. Features available for Timetable
### *Can only access from Main Window*

### 4.1. Showing timetable : `timetable`

Shows timetable of jobs, with the week shown being current week (LocalDate.now()).

Format: `timetable`

Alternative: Click on `Timetable` in menu bar > `Scheduled Jobs`
![timetable](images/Timetable.png)

### 4.2. Showing timetable of week containing specific date: `timetable_date`

Shows timetable of specific week containing a specific date

Format: `timetable_date date/YYYY-mm-DD`

* Shows timetable of the week containing the given date.
* This is the one and only command that Timetable Window can access (identical format).

Examples:
* `timetable_date date/2023-03-16` shows timetable of jobs in week from 13th - 19th March 2023.

### 4.3. Showing list of completed jobs
Shows list of completed jobs, sorted in increasing date and decreasing earning order.

Format: `timetable_completed`

Alternative: Click on `Timetable` in menu bar > `Completed Jobs`
![completed jobs](images/Completedjobs.png)

### 4.4. Showing list of unscheduled jobs
Shows list of unscheduled jobs (i.e. jobs with invalid delivery dates and/or slots).
Jobs are sorted in increasing date and decreasing earning order.

Format: `timetable_unscheduled`

Alternative: Click on `Timetable` in menu bar > `Unscheduled Jobs`
![Unscheduled jobs](images/Unscheduledjobs.png)

## 5. Features available for Statistics
### *Can only access from Main Window*
### 5.1. Showing Statistics : `stats`
![Statistics](images/Statistics.png)

Shows a summary of statistics related to the jobs in the job list
* Total number of jobs in the job list
* Total earnings from all jobs in the job list
* Total number of completed jobs in the job list
* Total number of pending jobs in the job list

Similar statistics are shown for jobs in the previous week


## Other features

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

Alternative: Click "File" in menu bar > "Exit"
* The alternative also applies for `Timetable` in menu bar > `Scheduled Jobs`/ `Completed Jobs` and `Customer` in menu bar > `Address book`

### Saving the data

Duke Driver data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke Driver data are saved as a JSON file `[JAR file location]/data/addressbook.json` and `[JAR file location]/data/deliveryjobsystem.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
**:information_source: Notes about the command format:**<br>
* Commands that start with *(C)* could only be accessed from Customer Window
* Commands that start with *(M)* could only be accessed from Main Window
* Commands that start with *(B)* could be accessed from both Main and Customer Window


| Action                                    | Format, Examples                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|-------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ***(C)* Add Customer**                    | Click on `Customers` in menu bar > `Address Book` then input `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br/><br/>e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`                                                                                                                                                                                                                                 |
| ***(B)* Clear all contacts**              | `clear`                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| ***(C)* Delete Customer**                 | Click on `Customers` in menu bar > `Address Book` then input `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                                                                                                        |
| ***(C)* Edit Customer details**           | Click on `Customers` in menu bar > `Address Book` then input `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br><br/> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                                                                                                                                                                                           |
| ***(C)* Find Customer**                   | Click on `Customers` in menu bar > `Address Book` then input `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                                                                                                                 |
| ***(B)* List Customer**                   | `list` <br/>Or<br/>Click on `Customers` in menu bar > `Address Book` then input `list`                                                                                                                                                                                                                                                                                                                                                                                  |
| ***(M)* Add Job**                         | `add_job si/SENDER_ID ri/RECEIPIENT_ID [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]` <br> e.g., `add_job si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`                                                                                                                                                                                                                                                                                                |
| ***(M)* Import Jobs**                     | click on `Delivery Job System` in menu bar > `Import Jobs` > select CSV file containing jobs to be imported > `open`                                                                                                                                                                                                                                                                                                                                                    |
| ***(M)* List Job**                        | `list_job`                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| ***(M)* Edit Job**                        | `edit_job INDEX [si/SENDER_ID] [ri/RECEIPIENT_ID] [date/DELIVERY_DATE] [slot/DELIVERY_SLOT] [earn/EARNING] [done/Complete]` <br>OR<br/>Click on the delivery job > Click on the middle pen button ![pen](images/penbutton.png) on top right hand corner > Make changes > Click on `Edit Job` button <br><br/> e.g., `add_job si/ALE874 ri/DAV910 date/2023-03-01 slot/3 earn/20`                                                                                        |
| ***(M)* Find Job**                        | `find_job [ji/JOB_ID] [si/SENDER_ID] [ri/RECIPIENT_ID] [date/DELIVER_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]...` <br><br/> e.g., `find_job si/ALE874`                                                                                                                                                                                                                                                                                                                 |
| ***(M)* Delete Job**                      | `delete_job ji/JOB_ID` <br>OR<br/>Alternative: Click on the delivery job > Click on the right bin button ![delete](images/deletebutton.png) on top right hand corner<br/><br/> e.g., `delete_job ALBE29E66F`                                                                                                                                                                                                                                                            |
| ***(M)* Complete Job**                    | `com_job ji/JOB_ID` / `uncom_job ji/JOB_ID`<br>OR<br>Alternative 1: Scroll to the job and click on the circle on the left, which a tick will appear in to show completion. Click again to uncomplete the job.<br>OR<br>Alternative 2: Click on the delivery job > Click on the left tick button ![complete](images/completebutton.png) on top right hand corner. Click on the button again to uncomplete job.<br><br>e.g.,`com_job ALBE29E66F` / `uncom_job ALBE29E66F` |                                                                                                                                                                                                                                                                                                                                                                                  |
| ***(M)* List reminder**                   | `list_reminder`                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| ***(M)* Add reminder**                    | `add_reminder d/DESCRIPTION time/YYY-MM-DD HH:mm` <br> e.g.,`add_reminder d/Submit homework time/2023-12-12 12:00`                                                                                                                                                                                                                                                                                                                                                      |
| ***(M)* Delete reminder**                 | `delete_reminder INDEX` <br> e.g., `delete_reminder 3`                                                                                                                                                                                                                                                                                                                                                                                                                  |
| ***(M)* Show Timetable**                  | `timetable`<br/>OR<br/>Click on `Timetable` in menu bar > `Scheduled Jobs`                                                                                                                                                                                                                                                                                                                                                                                              |
| ***(M)* Show Timetable of Specific Week** | `timetable_date date/YYYY-mm-DD`<br/>OR<br/>Click on `Timetable` in menu bar > `Scheduled Jobs` > input `timetable_date date/YYYY-mm-DD`<br/>e.g., `timetable_date date/2023-03-30`                                                                                                                                                                                                                                                                                     |
| ***(M)* Show List of Completed Jobs**     | `timetable_completed`<br/>OR<br/>Click on `Timetable` in menu bar > `Completed Jobs`                                                                                                                                                                                                                                                                                                                                                                                    |
| ***(M)* Show List of Unscheduled Jobs**   | `timetable_unscheduled`<br/>OR<br/>Click on `Timetable` in menu bar > `Unscheduled Jobs`                                                                                                                                                                                                                                                                                                                                                                                |
| ***(M)* Show Statistics**                 | `stats`<br/>OR<br/>Click on `Statistics` in menu bar > `Display Statistics`                                                                                                                                                                                                                                                                                                                                                                                             |
| ***(B)* Help**                            | `help`<br/>Or<br/>Click on `Help` in menu bar > `Help`                                                                                                                                                                                                                                                                                                                                                                                                                  |
| ***(B)* Exit**                            | `exit`<br/>Or<br/>Click on `Exit` in menu bar > `Exit`                                                                                                                                                                                                                                                                                                                                                                                                                  |


