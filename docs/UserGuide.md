---
layout: page
title: User Guide
---

Paidlancers is a **desktop app for keeping track of your freelancing events, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Paidlancers can get your freelancing event management tasks done faster than traditional GUI apps.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  * [Add a Contact](#add-a-contact)
  * [View Contact List](#view-contact-list)
  * [Link Contact to Event](#link-contact-event)
  * [View Rate](#view-rate)
  * [Marks event as done](#marks-event-as-done-:-mark)
  * [Create a new event](#create-a-new-event-:-newevent)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Paidlancers.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Paidlancers.jar` command to run the application.<br>
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

### Add a Contact
Adding a contact : `newcontact`
Adding a contact to contacts
Format: newcontact NAME NUMBER
Creates a new contact with specified NAME and NUMBER
Example:
newcontact Deborah Tan /num 91234567


View all contacts : listcontact
Displays all contacts saved in a list
Format: listcontact
Displays the list of contacts saved


Linking a client contact  to a event : link
Links client contact to an event.
Format: link INDEX CONTACT
Links contact to the event at the specified INDEX.
The index refers to the index number in the displayed events list.
The index must be a positive integer 1, 2, 3, …​
Example:
link 2 91234567 links contact 91234567 to 2nd event in the list.


Display the rate: rate
Displays the rate tagged to an event.
Format: rate INDEX
Displays the agreed rate of the event at the specified INDEX.
The index refers to the index number in the displayed events list.
The index must be a positive integer 1, 2, 3, …​
Example: 
rate 2 returns the rate of 2nd event in the event list.


Display the rate: newrate
Tags a rate to an event.
Format: newrate INDEX AMOUNT
Adds a specified rate, AMOUNT, to an event at the specified INDEX.
The index refers to the index number in the displayed events list.
The index must be a positive integer 1, 2, 3, …​
Example: 
newrate 2 100 adds the rate of 100 to thex 2nd event in the event list.


### Marks event as done : `mark`
Marks a specified event in the address book as done.  
Format: `mark INDEX`  
Marks the event at the specified **INDEX** as done.  
The event must not be marked as done beforehand.  
Example:  
- `mark 2` marks the 2nd event as done.

### Create a new event : `newevent`
Creates a new event  
Format: `newevent`  
Creates a new event with the specified event **NAME**  
Example: 
- `newevent Singing` creates an event that has the name “Singing”.

Listing all events : listevent
Shows a list of all events in the address book
Format: listevent


Deleting an event : deleteevent
Deletes the specified event from the event book.
Format: deleteevent INDEX
Deletes the event at the specified INDEX
The index refers to the index number shown in the displayed event list.
The index must be a positive integer 1, 2, 3, …​
Examples:
list followed by delete 2 deletes the 2nd event in the event book.


Adds dates to an event : adddate
Adds a starting time and ending time to the specified event in the event book.
Format: adddate INDEX /from dd/MM/yy /to dd/MM/yy
Adds starting and ending time to the event at the specified INDEX
The index refers to the index number shown in the displayed event list.
The index must be a positive integer 1, 2, 3, …​
The event must not have time associated with it beforehand


Saving all events and contacts: save
Saves all entries in Paidlancer.
Format: save


Loading all events and contacts : load
Loads all entries in Paidlancer from local storage if it exist
Format: load

### Saving the data

Paidlancer data are saved in the hard disk automatically exiting the program. There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?  
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskGenie home folder.

## Command Summary
|                   Commands                    |                     Command Format                      |                        Example Usage                          | 
|:---------------------------------------------:|:-------------------------------------------------------:|:-------------------------------------------------------------:|
| [Add a Contact](#add-a-contact) | | |  
| [View Contact List](#view-contact-list) | | |  
| [Link Contact to Event](#link-contact-event) | | |
| [View Rate](#view-rate) | | |  
| [Marks event as done](#marks-event-as-done-:-mark) | `mark INDEX` | `mark 2` |
| [Create a new event](#create-a-new-event-:-newevent)  | `newevent` | `newevent` |

[Back to top](#user-guide-for-taskgenie)
