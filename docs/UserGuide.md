---
layout: page
title: User Guide
---

Paidlancers is a **desktop app for keeping track of your freelancing events, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Paidlancers can get your freelancing event management tasks done faster than traditional GUI apps.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  * [Add a Contact](#add-a-contact)
  <!-- * [View Contact List](#list-all-contacts) -->
  * [Link Contact to Event](#link-contact-to-event)
  * [View Rate](#view-rate)
  * [Tag Rate](#tag-rate)
  * [Mark Event as Done](#mark-event)
  * [Create New Event](#create-new-event)
  * [View Event List](#list-all-events)
  * [Delete an Event](#delete-an-event)
  * [Edit an Event](#edit-an-event)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Paidlancers.jar` from [here](https://github.com/AY2223S2-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Paidlancers.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:

   * `listcontact` : Lists all contacts.

   * `newcontact n/John Doe p/98765432` : Adds a contact named `John Doe` to the contact list.

   * `delete 3` : Deletes the 3rd event shown in the current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Add a Contact: `newcontact` <a id = "add-a-contact"></a>

Adding a contact to contacts

**Format**: `newcontact n/NAME p/NUMBER`

- Creates a new contact with specified `NAME` and `NUMBER`


**Example**:
- `newcontact n/Deborah Tan p/91234567`
- `newcontact n/Mandy p/98765432`

<!-- ### List all Contacts: `listcontact` <a id = "list-all-contacts"></a>

Displays all contacts saved in a list

**Format**: `listcontact` -->
### Link Contact to Event: `linkcontact` <a id = "link-contact-to-event"></a>

Links client contact to an event.

**Format**: `linkcontact INDEX CONTACT`

- Links contact to the event at the specified `INDEX`.

  - The `INDEX` refers to the index number in the displayed events list.
  - The `INDEX` must be a positive integer 1, 2, 3, …​

**Example**:
- `linkcontact 2 91234567` links `2nd event` to contact `91234567` in the list.


### View Rate: `rate` <a id = "view-rate"></a>

Displays the rate tagged to an event.

**Format**: `rate INDEX`

- Displays the agreed rate of the event at the specified `INDEX`.

  - The `INDEX` refers to the index number in the displayed events list.
  - The `INDEX` must be a positive integer 1, 2, 3, …​

**Example**:
- `rate 2` returns the rate of `2nd event` in the event list.


<!-- ### Tag Rate: `newrate` <a id = "tag-rate"></a>

Tags a rate to an event.

**Format**: `newrate INDEX AMOUNT`

- Adds a specified rate, `AMOUNT`, to an event at the specified `INDEX`.

  - The `INDEX` refers to the index number in the displayed events list.
  - The `INDEX` must be a positive integer 1, 2, 3, …​

**Example**:
- `newrate 2 100` adds the rate of `100` to the `2nd event` in the event list. -->


### Mark Event as Done: `mark` <a id = "mark-event"></a>

Marks a specified event in the address book as done.

**Format**: `mark INDEX`

- Marks the event at the specified `INDEX` as done.

The event must not be marked as done beforehand.

**Example**:
- `mark 2` marks the `2nd event` as done.

### Create new Event: `newevent` <a id = "create-new-event"></a>

Creates a new event

**Format**: `newevent n/NAME p/rate a/ADDRESS ds/START_TIME de/END_TIME [t/TAG]…​`

- Times must have the format `dd-MM-yyyy HH:mm`.

**Example**:
- `newevent n/DJ at wedding p/100 a/311, Clementi Ave 2, #02-25 ds/11-03-2023 11:00 de/11-03-2023 17:00 t/friends t/dj`

### List all Events: `listevent` <a id = "list-all-events"></a>

Shows a list of all events in the address book

**Format**: `list`


### Delete an Event: `delete` <a id = "delete-an-event"></a>

Deletes the specified event from the event book.

**Format**: `delete INDEX`

- Deletes the event at the specified `INDEX`

  - The `INDEX` refers to the index number shown in the displayed event list.
  - The `INDEX` must be a positive integer 1, 2, 3, …​

**Example**:
  - `delete 2` deletes the `2nd event` in the event list.

### Edit an Event: `edit` <a id = "edit-an-event"></a>

Edits the specified event from the event book.

**Format**: `edit INDEX [n/NAME] [r/RATE] [a/ADDRESS] [ds/TIMING] [de/TIMING] [t/TAG]...`

- Edits the event at the specified `INDEX`

  - The `INDEX` refers to the index number shown in the displayed event list.
  - The `INDEX` must be a positive integer 1, 2, 3, …​
  - At least one of the optional fields must be provided.
  - Edits will replace existing values, edits are not cumulative.
  - Tags can be removed by typing `t/` without specifying any tags after it.

**Example**:
  - `edit 1 p/91234567 r/100` Edits the phone number and rate of the 1st person to be 91234567 and j100 respectively.
  - `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.

### Saving the data

Paidlancers data are saved in the hard disk automatically exiting the program. There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Paidlancers home folder.
(#add-a-contact)

## Command Summary
|                    Commands                     |               Command Format                |                                Example Usage                                |
|:-----------------------------------------------:|:-------------------------------------------:|:---------------------------------------------------------------------------:|
|         [Add a Contact](#add-a-contact)         |        `newcontact n/NAME p/NUMBER`         |                    `newcontact n/Deborah Tan p/91234567`                    |
| [Link Contact to Event](#link-contact-to-event) |            `linkcontact INDEX CONTACT`             |                              `linkcontact 2 91234567`                              |
|             [View Rate](#view-rate)             |                `rate INDEX`                 |                                  `rate 2`                                   |
|       [Marks Event as Done](#mark-event)        |                `mark INDEX`                 |                                  `mark 2`                                   |
|     [Create a new Event](#create-new-event)     |               `newevent n/NAME p/rate a/ADDRESS ds/START_TIME de/END_TIME [t/TAG]…`               |                             `newevent n/DJ at wedding p/100 a/311, Clementi Ave 2, #02-25 ds/11-03-2023 11:00 de/11-03-2023 17:00 t/friends t/dj`                  
|       [View Event List](#list-all-events)       |                 `list`                 |                                 `list`                                 |
|       [Delete an Event](#delete-an-event)       |               `delete INDEX`               |                               `delete 2`                               |
|       [Edit an Event](#edit-an-event)       |               `edit INDEX  [n/NAME] [r/RATE] [a/ADDRESS] [ds/TIMING] [de/TIMING] [t/TAG]...`               |                               `edit 2 r/100`                               |
|

[Back to top](#)
