---
layout: page
title: User Guide
---

SalesPunch is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SalesPunch can get your contact management tasks done faster than traditional GUI apps.

Salespeople managing client contacts who prefer a CLI

- has a need to manage a significant number of contacts
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: Users that want a faster way to log their sales funnel cycle and keep track of their leads through our CLI app. SalesPunch helps salespersons keep track of all the necessary details and set reminders/alerts, keep track of their leads and prioritise sales tasks

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help--help)
  - [Adding a person: `add`](#adding-a-person-add)
  - [Deleting a person : `delete`](#deleting-a-person--delete)
  - [Listing all persons : `list`](#listing-all-persons--list)
  - [Sorting all persons : `sort`](#sorting-all-persons--sort)
  - [Editing a person : `edit`](#editing-a-person--edit)
  - [Assigning lead status: `status`](#assigning-lead-status-status)
  - [Finding a contact name: `find`](#finding-a-contact-name-find)
  - [Finding a contact tag: `findtag`](#finding-a-contact-tag-findtag)
  - [Finding a contact based on lead status: `findlead`](#finding-a-contact-based-on-lead-status-findlead)
  - [Finding a contact based on keyword: `findall`](#finding-a-contact-based-on-keyword-findall)
  - [Finding a contact and their transactions list: `findtxn`](#finding-a-contact-and-their-transactions-list-findtxn)
  - [Adding a transaction: `addtxn`](#adding-a-transaction-addtxn)
  - [Deleting a transaction record : `deletetxn`](#deleting-a-transaction-record--deletetxn)
  - [Listing all transaction records : `listtxn`](#listing-all-transaction-records--listtxn)
  - [Editing a transaction record : `edittxn`](#editing-a-transaction-record--edittxn)
  - [Adding a task: `addtask`](#adding-a-task-addtask)
  - [Clearing all tasks: `cleartask`](#clearing-all-tasks-cleartask)
  - [Clearing all entries : `clear`](#clearing-all-entries--clear)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `salespunch.jar` from [here](https://github.com/AY2223S2-CS2103-W16-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SalesPunch.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar salespunch.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `add n/Charly Brownly g/female p/93210283 e/charly@example.com c/Mac King l/America o/entrepreneur j/CEO a/Blk 11 Ang Mo Kio Street 74, #11-04` : Adds a contact named `Charly Brownly` to the contact list.

   - `delete 3` : Deletes the 3rd contact shown in the current contact list.

   - `clear` : Deletes all contacts from contact list.

   - `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
  <br>
- Items in square brackets are optional.
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

  <br>

- Items with `…`​ after them can be used multiple times including zero times.
  e.g. `[t/TAG …]​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
  <br>

- Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  <br>

- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
  <br>

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  <br>

- Current iteration can only accept up to 2147483647 contacts and and up to 2147483647 transactions. Therefore command referencing INDEX can only reach up to 2147483647.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the contact list.

Format: `add n/NAME g/GENDER p/PHONE_NUMBER e/EMAIL c/COMPANY l/LOCATION o/OCCUPATION j/JOBTITLE a/ADDRESS [t/TAG …]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact must include all fields except tag and have a unique name that is not already in the contact list.
</div>

Examples:

- `add n/Amelia Oliveiro g/female p/93210283 e/Amelia@example.com c/Mac King l/America o/entrepreneur j/CEO a/Blk 11 Ang Mo Kio Street 74, #11-04`
- `add n/Amy Bee g/female p/85355255 e/amy@gmail.com c/Tesleh l/Singapore o/engineer j/industrial engineer a/123, Jurong West Ave 6, #08-111 t/friends`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Contacts can have multiple optional tags associated with them.
</div>

- `add n/Bob Builder g/male p/85355255 e/amy@gmail.com c/Tesleh l/Singapore o/engineer j/industrial engineer a/123, Jurong West Ave 6, #08-111 t/friends t/construction t/alumni` Creates contact with multiple tags associated to them

### Deleting a person : `delete`

Deletes the specified person from the contact list.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed contact list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd person in the contact list.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Listing all persons : `list`

Shows a list of all persons in the contact list.

Format: `sort ATTRIBUTE`

ATTRIBUTE: `name, gender, phone number, email, company, location, occupation, job title, address, status`

- Only one of the attributes needs to be included.
- The contact list will be sorted based on the specified attribute by their value.

Examples:

- `sort name` Sorts all persons alphabetically by name.
- `sort occupation` Sorts all persons alphabetically by occupation.

Format: `list`

### Sorting all persons : `sort`

Sorts all persons in the contact list based on an attribute.

### Editing a person : `edit`

Edits an existing person in the contact list.

Note that the lead status of the person is not available for editing here. Modifying the lead status
should be done via the `status` command.

Format: `edit INDEX [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [l/LOCATION] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG] …​`

- Edits the person at the specified required `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Contacts can have multiple optional tags associated with them.
</div>

- `edit 1 t/friends t/alumni` Edits the name of the 1st person to have the following tags `friends` and `alumni`

### Assigning lead status: `status`

Assigns the lead status of a contact. At the same time, saves the timestamp of when the lead status has changed. The
user can use this information as a gauge of how long a client has stayed in a certain status.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the lead status specified is the same as the previous, nothing is changed and the timestamp is not refreshed. This is
to mitigate the chances of accidental reassignment of the same lead status by the user.
</div>

The 4 types of lead statuses supported are:

| Type        | Formats (Case-sensitive) | Meaning                                                                                                                                                      |
| ----------- | ------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| UNCONTACTED | `Uncontacted` or `U`     | The user has not gotten in touch with (contacted) the saved person. By default, newly added contacts have this status                                        |
| WORKING     | `Working` or `W`         | The person has been contacted. The user is currently nurturing a relationship with the contact with the hopes of making them a qualified lead.               |
| QUALIFIED   | `Qualified` or `Q`       | The contact is a client that has been nurtured to a ready, buying customer. A contact that has a prospect to buy or is in the sales funnel should go here.   |
| UNQUALIFIED | `Unqualified` or `X`     | This status should only be used when it is certain that the customer's intents are not a match for the user's sales, and have no prospects of buying at all. |

<div markdown="span" class="alert alert-info">:information_source: **Note:**
There might be other types of lead statuses definitions (<a href="https://www.varicent.com/blog/6-essential-salesforce-lead-status-options-that-align-sales-and-marketing">examples</a>)
that you might want to use. For this application, we have implemented only 4 types of lead statuses for simplicity's sake.
</div>

Format:
`status INDEX s/STATUS`

Examples:  
`status 1 s/Q` Assigns the status of QUALIFIED to ID `1` in the current list of persons.  
`status 2 s/Working` Assigns the status of WORKING to ID `2` in the current list of persons.

### Finding a contact name: `find`

Search for a contact based on the NAME.

Format: `find NAME`

- The find is case-insensitive. e.g. `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is found.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find Dewy` will return `Dewy Thompson` or `Majorie Dewy`

### Finding a contact tag: `findtag`

Search for a contact based on their tags.

Format: `findtag TAG`

- The search is case-insensitive. e.g `friends` will match `Friends`
- Only full tags will be matched e.g. `friend` will not match `friends`
- Persons matching at least one tag will be returned (i.e. `OR` search).  
  e.g. `findtag friends` will return `Hans Gruber`, `Bo Yang` if they are associated with having a `friends` tag

Examples:

- `findtag friends` - returns all valid contacts that are associated with tag being searched like `friends`, `Dewy Thompson`

### Finding a contact based on lead status: `findlead`

Search for a contact based on their leads.

Format: `findlead STATUS`

The user can use either the long form or short form method to search

| Type        | Formats (Case-sensitive) | Meaning                                                                                                                                                      |
| ----------- | ------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| UNCONTACTED | `Uncontacted` or `U`     | The user has not gotten in touch with (contacted) the saved person. By default, newly added contacts have this status                                        |
| WORKING     | `Working` or `W`         | The person has been contacted. The user is currently nurturing a relationship with the contact with the hopes of making them a qualified lead.               |
| QUALIFIED   | `Qualified` or `Q`       | The contact is a client that has been nurtured to a ready, buying customer. A contact that has a prospect to buy or is in the sales funnel should go here.   |
| UNQUALIFIED | `Unqualified` or `X`     | This status should only be used when it is certain that the customer's intents are not a match for the user's sales, and have no prospects of buying at all. |

- The search works for both short form and long form. e.g `U` will match `Uncontacted`
- Persons matching the tag will be returned
  e.g. `findlead U` will return `Hans Gruber` who is a contact with a lead status of `Uncontacted`

Examples:

- `findlead STATUS` - returns all contacts with the associated lead status, `Dewy Thompson` or `Majorie Dewy`

### Finding a contact based on keyword: `findall`

Search for a contact based on matching KEYWORD to any attribute of a Person recorded in the contact list.

Format: `findall KEYWORD`

\*`KEYWORD` includes all attributes except `TAG` and `STATUS` because they have their own separate commands for searching.  
\*Does not parse through tasklist and transactions (For future extensions).

The user can search for any attribute and if it matches with any Person, that person will be listed.

- The search is case-insensitive. e.g `u` will match `U`
- The search will match with any attribute
- Persons matching the search will be returned:
  1. Example 1: `findall NAME`: `findall John` will return contacts with gender attributes that contains the keyword like `John Doe` and `John The Builder`  
  2. Example 2: `findall ADDRESS`: `findall Blk 30` will return contacts with address attributes that contains the keyword like `Blk 16 Hello Drive` and `Blk Goodbye Drive`  
  3. Applicable for all the following attributes in a person's details: `NAME GENDER PHONE_NUMBER EMAIL COMPANY LOCATION OCCUPATION JOBTITLE ADDRESS`

### Finding a contact and their transactions list: `findtxn`

Search for a single contact and all transactions related to this contact. The user must enter an existing user that is in the contact list and must match exactly the name in the contact list.

Format: `findtxn NAME`

- The search is case-insensitive and exact-match. e.g `John Doe` will match `john doe` and all transactions with `john doe` as the owner in the transaction
- The spaces before and after keywords does not matter. e.g. `   Hans Bo ` will match `Hans Bo`
  e.g. `findtxn John Doe` will return `John Doe` and all txns with the same owner name `John Doe`

Examples:

- `findtxn John Doe` - returns the contact with valid and associated transactions with the owner name `John Doe`

### Adding a transaction: `addtxn`

Adds a transaction record to the transaction list.

Format: `addtxn td/DESCRIPTION tv/VALUE ts/STATUS to/OWNER`

| Type   | Formats (Case-sensitive) | Meaning                |
| ------ | ------------------------ | ---------------------- |
| OPEN   | `Open` or `O`            | Incomplete transaction |
| CLOSED | `Closed` or `C`          | Completed transaction  |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
All fields must be provided, and transaction status should be either 'Open' or 'Closed'.
</div>

Examples:

- `addtxn td/Sample Transaction tv/100 ts/Open to/John Doe`

### Deleting a transaction record : `deletetxn`

Deletes the specified transaction record from the transaction list.

Format: `deletetxn INDEX`

- Deletes the transaction record at the specified `INDEX`.
- The index refers to the index number shown in the displayed transaction list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `deletetxn 2` deletes the 2nd transaction record in the transaction list.

### Listing all transaction records : `listtxn`

Shows a list of all transaction records in the transaction list.

Format: `listtxn`

### Editing a transaction record : `edittxn`

Edits an existing transaction record in the transaction list.

Format: `edittxn INDEX [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]`

- Edits the transaction record at the specified `INDEX`.
- The index refers to the index number shown in the displayed transaction list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:

- `edittxn 1 ts/Closed` Edits the transaction status of the 1st transaction record to be `Closed`.

### Adding a task: `addtask`

Adds a task to the specific person in the contact list.

Format: `addtask INDEX at/TASK_DESCRIPTION`

- Adds a task to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- The task added to the person will have the description `TASK_DESCRIPTION`

### Clearing all tasks: `cleartask`

Clears all tasks of the specified person based on the INDEX in the contact list.

Format: `cleartask INDEX`

- Clears all tasks to the person at the specified `INDEX`. The index refers to the INDEX number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​

### Clearing all entries : `clear`

Clears all entries from the contact list.

Format: `clear`

### Exiting the program : `exit`

Exits and closes the program

Format: `exit`

### Saving the data

SalesPunch contact data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SalesPunch data are saved as a JSON file `[JAR file location]/data/salespunch.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SalesPunch will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SalesPunch home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                      |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Help**   | `help`                                                                                                                                                                                                                                                                |
| **Add**    | `add n/NAME g/GENDER p/PHONE_NUMBER e/EMAIL c/COMPANY l/LOCATION o/OCCUPATION j/JOBTITLE a/ADDRESS [t/TAG…] ​` <br> e.g., `add n/Amy Bee g/female p/85355255 e/amy@gmail.com c/Tesleh l/Singapore o/engineer j/industrial engineer a/123, Jurong West Ave 6, #08-111` |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                   |
| **List**   | `list`                                                                                                                                                                                                                                                                |
| **Sort**   | `sort ATTRIBUTE`, where `ATTRIBUTE` is one of: <br> `name, gender, phone number, email, company, location, occupation, job title, address, status`<br> e.g., `sort name`                                                                                              |
| **Edit**   | `edit INDEX [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [i/INDUSTRY] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG] …​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                          |
| **Status** | `status INDEX_NUMBER s/STATUS` <br> e.g., `status 1 s/Unqualified`                                                                                                                                                                                                    |
| **AddTxn** | `addtxn [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER] ` <br> e.g., `addtxn [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]` |
| **DeleteTxn** | `deletetxn INDEX`<br> e.g., `deletetxn 3` |
| **ListTxn** | `listtxn` |
| **EditTxn** | `edittxn INDEX [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]` <br> e.g., `edittxn 1 ts/closed` |
| **AddTask** | `addtask INDEX at/TASK_DESCRIPTION` <br> e.g. `addtask 1 at/Arrange for sales pitch meeting` |
| **ClearTask** | `cleartask INDEX` <br> e.g., `cleartask 1` |
| **Clear** | `clear` |
| **Exit** | `exit` |
