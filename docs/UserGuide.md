---
layout: page
title: User Guide
---

![Ui](images/Logo.png)

Welcome to the Paidlancers User Guide! We're thrilled to have you here! At Paidlancers,
we know that freelancing can be both rewarding and challenging. As freelancers ourselves, we understand the unique
obstacles and opportunities that come with freelancing. That's why we created Paidlancers – a desktop app
designed to help you streamline your freelancing event management tasks.

## Introducing Paidlancers

Paidlancers is a **desktop app for keeping track of your freelancing events, optimised for use via a Command Line
Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
Paidlancers can get your freelancing event management tasks done faster than traditional GUI apps.

This user guide will help you get up to speed in no time!

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
    * [Add a Contact](#add-a-contact)
    * [Link Contact to Event](#link-contact-to-event)
    * [View Rate](#view-rate)
    * [Tag Rate](#tag-rate)
    * [Mark Event as Done](#mark-event)
    * [Create New Event](#create-new-event)
    * [View Event List](#list-all-events)
    * [Delete an Event](#delete-an-event)
    * [Edit an Event](#edit-an-event)
    * [View Total Revenue](#view-total-revenue)
    * [View Upcoming Events](#view-upcoming-events)
- [FAQ](#faq)
- [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Using the examples in this Guide
The examples given in this guide are formatted with the following conventions:

`commandword PARAMETERS`

**Command Word**
- Command word is the first word in any command.
- It is written in lowercase.
- These include examples such as `linkcontact` or `edit`.

**Parameters**
- Parameters are the words that follow the command word.
- Parameters are written in UPPERCASE.
- These include examples such as `INDEX` or `PHONE`.
- Parameters are meant to be replaced by the user with the relevant information.
- All parameters are required unless wrapped with `[square brackets]`.

**Example**
- Examples are shown in the format `commandword parameters`.
- This is followed by the expected outcome of the command.
- These include examples such as `newcontact n/John Doe p/98765432`.
- These are meant to be used as a reference for the user to see how the command should be formatted.
- The expected action of this command is written behind.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
    * Mac Users are encouraged to use to use the Azul build of OpenJDK11 version found
      [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
    * Choose the `JDK FX` version and not any other version.

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

[//]: # (Perhaps we could explain the difference between event and contact here?)

## Features

### Add a Contact: `newcontact` <a id = "add-a-contact"></a>

Adds a new contact.

**Format**: `newcontact n/NAME p/NUMBER`

- Creates a new contact with specified `NAME` and `NUMBER`

**Example**:
- `newcontact n/Deborah Tan p/91234567`
  - This command will create a new contact named `Deborah Tan` with phone number `91234567`. 
- `newcontact n/Tan Jun Wei p/82828234`
  - This command will create a new contact named `Tan Jun Wei` with phone number `82828234`.


### Link Contact to Event: `linkcontact` <a id = "link-contact-to-event"></a>

Links client contact to an event.

**Format**: `linkcontact INDEX PHONE`

- Links contact using `PHONE` to the event at the specified `INDEX`.

    - The `INDEX` refers to the index number in the displayed events list.
    - The `INDEX` must be a positive integer 1, 2, 3, …
    - The `PHONE` must be a valid phone number in the contact list.

**Example**:
- `linkcontact 2 91234567`
  - This command will link the contact with phone number `91234567` to the 2nd event in the event list.


### View Rate: `rate` <a id = "view-rate"></a>

Displays the rate tagged to an event.

**Format**: `rate INDEX`

- Displays the agreed rate of the event at the specified `INDEX`.

    - The `INDEX` refers to the index number in the displayed events list.
    - The `INDEX` must be a positive integer 1, 2, 3, …

**Example**:
- `rate 2`
  - This command will display the rate of the 2nd event in the event list.


### Mark Event as Done: `mark` <a id = "mark-event"></a>

Marks a specified event in Paidlancers as done.

**Format**: `mark INDEX`

- Marks the event at the specified `INDEX` as done.

    - The `INDEX` refers to the index number in the displayed events list.
    - The `INDEX` must be a positive integer 1, 2, 3, …

**Example**:
- `mark 2`
  - This command will mark the 2nd event in the event list as done.


### Unmark an Event: `unmark` <a id = "unmark-event"></a>

Unmarks a specified event in Paidlancers.

**Format**: `unmark INDEX`

- Unmarks the event at the specified `INDEX`.
    - The `INDEX` refers to the index number in the displayed events list.
    - The `INDEX` must be a positive integer 1, 2, 3, …

**Example**:
- `unmark 2`
  - This command will unmark the 2nd event in the event list.


### Create new Event: `newevent` <a id = "create-new-event"></a>

Adds a new event.

**Format**: `newevent n/NAME r/RATE a/ADDRESS ds/START_TIME de/END_TIME [t/TAG]…`
- Creates a new event with specified `NAME`, `RATE`, `ADDRESS`, `START_TIME`, `END_TIME` and `TAG`s.
    - Both `START_TIME` and `END_TIME` must have the format `dd-MM-yyyy HH:mm`.

**Example**:
- `newevent n/DJ at wedding r/100 a/311, Clementi Ave 2, #02-25 ds/11-03-2023 11:00 de/11-03-2023 17:00 t/friends t/dj`
  - This command will create a new event named `DJ at wedding` with rate `100` at address `311, Clementi Ave 2, #02-25` from `11-03-2023 11:00` to `11-03-2023 17:00` with tags `friends` and `dj`.


### List all Events: `list` <a id = "list-all-events"></a>

Shows a list of all events.

**Format**: `list`

- Lists down the events in the event book.


### Delete an Event: `delete` <a id = "delete-an-event"></a>

Deletes the specified event from the event book.

**Format**: `delete INDEX`

- Deletes the event at the specified `INDEX`

    - The `INDEX` refers to the index number shown in the displayed event list.
    - The `INDEX` must be a positive integer 1, 2, 3, …

**Example**:
- `delete 2`
  - This command will delete the 2nd event in the event list.


### Edit an Event: `edit` <a id = "edit-an-event"></a>

Edits the specified event.

**Format**: `edit INDEX [n/NAME] [r/RATE] [a/ADDRESS] [ds/TIMING] [de/TIMING] [t/TAG]...`

- Edits the event at the specified `INDEX`

    - The `INDEX` refers to the index number shown in the displayed event list.
    - The `INDEX` must be a positive integer 1, 2, 3, …
    - `[]` are optional parameters.
    - At least one of the optional fields must be provided.
    - Edits will replace existing values, edits are not cumulative.
    - Tags can be removed by typing `t/` without specifying any tags after it.
    - Do note that `edit` is only for editing the event details, not the contact details.
    - To link the event to a new contact, consider using [`linkcontact`](#link-contact-to-event) instead.

**Example**:
- `edit 1 r/100`
  - This command will edit the rate of the 1st event to be `100`.
- `edit 2 n/Wedding Dinner t/`
  - This command will edit the name of the 2nd event to be `Wedding Dinner` and remove all tags.
- `edit 1 n/Wedding Lunch`
  - This command will edit the name of the 1st event to be `Wedding Lunch`.


### View Total Revenue: `revenue` <a id = "view-total-revenue"></a>

Displays the total revenue based on all the completed events.

**Format**: `revenue`
- Displays the total revenue based on all the completed events.

### View Upcoming Events: `remind` <a id = "view-upcoming-events"></a>

Displays events that start within a specified number of days.

**Format**: `remind DAYS`

- Displays events that start within the specified number of `DAYS`.
  - `DAYS` must be a positive integer 1, 2, 3, …
  - Only events that start after the current date and time will be displayed.
  - The number of days to an event are the days from today's date to the event's start date. Their times are not considered.

**Example**:

Assume the current date and time is 22-03-2023 11:00.

- `remind 2`
  - Events that start within 2 days will be displayed. These are events that start on:
    * 22-03-2023 after 11:00
    * 23-03-2023 the whole day
    * 24-03-2023 the whole day
  - Note that 24-03-2023 is within 2 days of 22-03-2023, so events on 24-03-2023 that start more than 48 hours from the current date and time are displayed.

### Saving the data

Paidlancers data are saved in the hard disk automatically on command issue. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer? \
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Paidlancers home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary
|                    Commands                     |               Command Format                |                                Example Usage                                |
|:-----------------------------------------------:|:-------------------------------------------:|:---------------------------------------------------------------------------:|
|         [Add a Contact](#add-a-contact)         |        `newcontact n/NAME p/NUMBER`         |                    `newcontact n/Deborah Tan p/91234567`                    |
| [Link Contact to Event](#link-contact-to-event) |            `linkcontact INDEX CONTACT`             |                              `linkcontact 2 91234567`                              |
|             [View Rate](#view-rate)             |                `rate INDEX`                 |                                  `rate 2`                                   |
|       [Marks Event as Done](#mark-event)        |                `mark INDEX`                 |                                  `mark 2`                                   |
|          [Unmark Event](#unmark-event)          |                `unmark INDEX`                 |                                  `unmark 2`                                   |
|     [Create a new Event](#create-new-event)     |               `newevent n/NAME p/rate a/ADDRESS ds/START_TIME de/END_TIME [t/TAG]…`               |                             `newevent n/DJ at wedding p/100 a/311, Clementi Ave 2, #02-25 ds/11-03-2023 11:00 de/11-03-2023 17:00 t/friends t/dj`                  |
|       [View Event List](#list-all-events)       |                 `list`                 |                                 `list`                                 |
|       [Delete an Event](#delete-an-event)       |               `delete INDEX`               |                               `delete 2`                               |
|         [Edit an Event](#edit-an-event)         |               `edit INDEX  [n/NAME] [r/RATE] [a/ADDRESS] [ds/TIMING] [de/TIMING] [t/TAG]...`               |                               `edit 2 r/100`                               |
|    [View Total Revenue](#view-total-revenue)    |                  `revenue`               |                              `revenue`                               |
|  [View Upcoming Events](#view-upcoming-events)  |                 `remind DAYS`            |                              `remind 2`                             |

[Back to top](#)

