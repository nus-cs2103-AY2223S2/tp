---
layout: page
title: User Guide
---

## About QuickContacts

QuickContacts is a **contacts and meetings management application** that enables you to manage your schedule and contacts with ease! It is designed for users who have a large number of contacts and meetings with those contacts.

QuickContacts is a **cross-platform application** that works on Windows, macOS and Linux. We, the developers at QuickContacts, understand the amount of time required to manage many contacts and maintaining a schedule with many meetings with them. Designed for busy individuals, QuickContacts is built around a Command Line Interface (CLI) that empowers users to perform tasks in seconds. With fast fingers, you can be assured that your time spent managing your schedule is minimised!

QuickContacts is specially designed for:

* Seamless creation and management of contacts and meetings
* Users who have a large number of contacts and meetings
* Typists

This user guide serves as an introduction on how to incorporate QuickContacts into your daily workflow. Head over to the [Getting started](#getting-started) section to get onboard!

## Table of Contents
- [Getting started](#getting-started)
- [Features](#features)
    - [`help`](#viewing-help--help)
    - [`add`](#adding-a-person--add)
    - [`list`](#listing-all-persons--list)
    - [`edit`](#editing-a-person--edit)
    - [`find`](#locating-persons-by-name--find)
    - [`delete`](#deleting-a-person--delete)
    - [`clear`](#clearing-all-entries--clear)
    - [`exit`](#exiting-the-program--exit)
    - [`addm`](#adding-a-meeting--addm)
    - [`findm`](#finding-meetings-by-name--findm)
    - [`export`](#exporting-of-contact--export)
    - [`import`](#importing-of-contacts--import)
    - [`exportm`](#export-meetings--exportm)
    - [`importm`](#import-meetings--importm)
    - [Saving of data](#saving-the-data)
    - [Editing of data](#editing-the-data-file)
    - [Archiving of data](#archiving-data-files-coming-in-v20)
- [FAQ](#faq)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `quickcontacts.jar` from [here](https://github.com/AY2223S2-CS2103T-T11-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your `QuickContacts`.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar quickcontacts.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

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

* Commands are **_italicised and bolded_** for ease of identification.

</div>

### Theme toggling

Toggles the theme from dark to light or vice versa.

![toggle theme](images/toggleTheme.png)

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

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

### Sorting Meetings: `sortm`

Sorts meetings in the address book by a specified attribute.

Format: `sortm ATTRIBUTE [r]`

* Sorts the meetings in the address book by the specified `ATTRIBUTE`, which can be one of the following: `m/` (meeting title), `dt/` (date/time), `l/` (location), or `des/` (description).
* If the `r` option is included, the meetings will be sorted in reverse order.
* Meetings with the same value for the specified attribute will be sorted by date/time in ascending order.
* Example: `sortm m/` sorts meetings by title in ascending order.
* Example: `sortm dt/r` sorts meetings by date/time in descending order.

### Editing a meeting: `editm`

Edits an existing meeting in the meeting book.

Format: `editm INDEX [t/TITLE] [dt/DATE] [l/LOCATION] [des/DESCRIPTION]`

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* The `DATE`, `START_TIME` and `END_TIME` must be provided in the format `YYYY-MM-DD`, `HH:MM` (24-hour) respectively.

Examples:
* `editm 1 t/Project Update dt/2023-04-01 10:00`  Edits the date first meeting to be on `2023-04-01`, and change its title to "Project Update".

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Adding a Meeting : `addm`

Adds a meeting to the address book.

Format: `addm m/TITLE [p/PERSON]... dt/DATE_TIME l/LOCATION des/DESCRIPTION`

Examples:
* `addm m/CS2103T Tutorial p/John Doe p/Jane Doe dt/2022-02-22 10:00 l/COM1-B103 des/CS2103T Tutorial`
* `addm m/CS2103T Tutorial p/John Doe p/Mary Jane dt/2022-02-22 22:22 l/COM1-B103 des/CS2101 Tutorial`

### Finding Meetings by name : `findm`

Find meetings whose names contain any of the given keywords.

Format: `findm KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g. `hans` will match `Hans`
* Space is delimiter, so if you search `John Doe` it will list all people with either `John` or `Doe` in their name.
* Only the name of attendees in meeting are searched
* Only full words will be matched e.g. `Han` will not match `Hans`
* Meetings matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John Doe` will return Meetings that contain either `John Doe`, or `Doe John`

Examples:
* `findm John` returns `Meeting` that contains `attendees` whose Name contains `John`
* `findm John Mary` returns `Meeting` that contains `attendees` whose Name contains `John` or `Mary`

### Saving the data

QuickContacts data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

QuickContacts data are saved as a JSON file `[JAR file location]/data/quickcontacts.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, QuickContacts will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Exporting of contact : `export`
* Exports the persons at the specified `INDEX`es.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Format: `export p/ INDEX1 p/ INDEX2`

### Importing of contacts : `import`
* Imports the persons in the provided JSON.
* The JSON **must contain a valid array of persons** 

Example:
```json
[
  {
    "name": "Alice Pauline",
    "phone": "94351253",
    "email": "alice@example.com",
    "address": "123, Jurong West Ave 6, #08-111",
    "tagged": [
      "friends"
    ]
  },
  {
    "name": "Benson Meier",
    "phone": "98765432",
    "email": "johnd@example.com",
    "address": "311, Clementi Ave 2, #02-25",
    "tagged": [
      "owesMoney",
      "friends"
    ]
  }
]
```
Optional Parameter `f/` that forces imports regardless of duplicate values.

Format: `import JSON f/`

### Export meetings : `exportm`
* Exports the meetings at the specified `INDEX`es, between `start` and `end` dates.
* The indexes refer to the index numbers shown in the displayed meetings list.
* The indexes **must be positive integers** 1, 2, 3
* The start and end dates must be valid dates in the DD/MM/YY format

Format: `exportm p/ INDEX1 p/ INDEX2 start/ START_DATE end/ END_DATE`

### Import meetings : `importm`
* Imports the meetings in the provided JSON.
* The JSON **must contain a valid array of meetings**

Example:
```json
[
  {
    "title": "Dinner with Alice",
    "dateTime": "01/02/2023 19:00",
    "attendees": [
      {
        "name": "Alice Pauline",
        "phone": "94351253",
        "email": "alice@example.com",
        "address": "123, Jurong West Ave 6, #08-111",
        "tagged": [
          "friends"
        ]
      }
    ],
    "location": "NUS",
    "description": "Weekly catchup"
  },
  {
    "title": "Study session with Benson and Carl",
    "dateTime": "02/03/2023 15:00",
    "attendees": [
      {
        "name": "Benson Meier",
        "phone": "98765432",
        "email": "johnd@example.com",
        "address": "311, Clementi Ave 2, #02-25",
        "tagged": [
          "owesMoney",
          "friends"
        ]
      },
      {
        "name": "Carl Kurz",
        "phone": "95352563",
        "email": "heinz@example.com",
        "address": "wall street",
        "tagged": []
      }
    ],
    "location": "Central Library",
    "description": "Study for finals"
  },
  {
    "title": "Zoom meeting for agenda planning",
    "dateTime": "13/03/2023 12:45",
    "attendees": [
      {
        "name": "Alice Pauline",
        "phone": "94351253",
        "email": "alice@example.com",
        "address": "123, Jurong West Ave 6, #08-111",
        "tagged": [
          "friends"
        ]
      }
    ],
    "location": "https://us02web.zoom.us/j/99999999999?pwd=ABCdEfGHiJkYkRuYW5WTLmNopQrSt12",
    "description": "Plan for project work"
  }
]


```

Optional Parameter `f/` that forces imports regardless of duplicate values.

Format: `import JSON f/`

### Delete meetings : `delm`

Deletes the specified meeting.

Format: `delm INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed meetings list.
* The index must be a positive integer 1, 2, 3...

Examples:

* `listm` followed by `delm 2` deletes the 2nd meeting.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous QuickContacts home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format, Examples                                                                                                                                                      |
|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Create a contact**  | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Reset all data**    | `clear`                                                                                                                                                               |
| **Delete a contact**  | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit a contact**    | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find a contact**    | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List all contacts** | `list`                                                                                                                                                                |
| **Help**              | `help`                                                                                                                                                                |
| **List all meetings** | `listm`                                                                                                                                                               |
| **Create a meeting**  | `addm m/TITLE dt/DATE_TIME p/CONTACT_NAME... l/LOCATION des/DESCRIPTION`                                                                                              |
| **Edit a meeting**    | `editm INDEX [m/TITLE] [dt/DATE_TIME] [p/CONTACT_NAME]... [l/LOCATION] [des/DESCRIPTION]`                                                                             |
| **Delete a meeting**  | `delm INDEX` <br> e.g., `deletem 3`                                                                                                                                   |
| **Export a contact**  | `export p/INDEX...` <br> e.g., `export p/1 p/2 p/3`                                                                                                                   |
| **Export a meeting**  | `exportm m/INDEX...` <br> e.g., `exportm m/1 m/2 m/3`                                                                                                                 |
| **Import a contact**  | `import VALID_JSON`                                                                                                                                                   |
| **Import a meeting**  | `importm VALID_JSON`                                                                                                                                                  |
| **Sort meetings**     | `sortm SORT_FIELD` <br> e.g., `sortm dt/`                                                                                                                             |
