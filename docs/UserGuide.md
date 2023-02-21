---
layout: page
title: User Guide
---

Calidr is a **time-management and scheduling calendar application**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `calindr.jar` from [here](https://github.com/AY2223S2-CS2103T-W10-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Calindr.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar calindr.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## :mag: Definitions

### Command definitions

<div markdown="block" class="alert alert-info">
🕮 This user guide uses a modifided version of the <a href="http://docopt.org/">docopt</a> command description language.
</div>

 1. We will use the word "**string**" to describe any general sequence of characters.
 1. We will use the word "**word**" to describe a sequence of characters terminated by a whitespace.
 1. Words starting with a backslash `/` are interpreted as _compulsory_, _position-independent_ **options**.
     `todo /by`
 1. Words bracketed with "<" and ">", or uppercase words, denote the position of **arguments**.
 1. All other words that do not follow the above conventions are interpreted as **commands** and **subcommands**.
     `list`
 3. Options can have arguments specified after a whitespace. Together they are refered to as a **field**.
     `todo /by <date>`
 1. An option's arguments, when bracketed by "<" and ">", can be **whitespace-containing** strings. They are terminated by a newline or a different option, matching *leftmost-first*.
     `event /from <start> /to <end>`
 1. Else, if the arguments are in uppercase, they must be **strictly words** terminated by whitespace.
     `delete TASK_INDEX`
 1. Some commands may accept a single *positional* argument string, known as a **main argument**, beginning after a whitespace following the command itself and terminating at a newline or the first option, matching *leftmost-first*.
     `event <title> /from <start> /to <end>`
 1. Options (and arguments) bracketed with "\[" and  "\]" are **optional**.
     `event /from <start> /to <end> [/desc <description>] [/loc <location>]`
 1. Use ellipsis "..." to specify that the argument (or field) to the left could be **repeated** multiple times:
     `delete TASK_INDEX...`
 1. *All elements are required by default*, if not included in brackets "\[ \]". However, sometimes it is necessary to mark elements as **required** explicitly with parentheses "( )". For example, when you need to group mutually-inclusive elements (if *one* element is present, then *another* one is required):
    `edit TASK_INDEX (OPTION <argument>)...`

### Data terminology

 1. Elements on a calendar are refered to as **Items**.
 1. Items must either be be **ToDos** or **Events**.
    a. ToDos have a single associated date-time (e.g. a due date). ToDos also have an associated *status* (done or not done).
    b. Events have *two* associated date-times.


## :paperclip: Features

#### View help: `help`

Shows a message explaning how to access the help page.
The help page provides information about how to use the application and its features.


#### Adding items

While adding an item, apart from the compulsory fields [specified above](#Data-terminology), you can add optional
- *descriptions* to items to better describe it,
- *locations*,
- and *comments*.

<!-- end of the list -->

- #### Adding a ToDo: `todo`
  Adds a ToDo with the given title and date-time to the list of tasks.

  Format: `todo <title> /by <date-time> [/desc <description>] [/loc <location>] [/cmt <comment>]`

- #### Adding an Event: `event`
  Adds an event with the given title, start and end date-times to the list of events.

  Format: `event <title> /from <start> /to <end> [/desc <description>] [/loc <location>] [/cmt <comment>]`

#### Deleting a item: `delete`
Deletes one or more items from the list of items.
Format: `delete TASK_INDEX...`

#### Listing items: `list`
Lists all the items saved.
Format: `list`

#### Editing task information: `edit`
Edits information pertaining to a particular item.
Format: `edit TASK_INDEX (OPTION <argument>)...`

e.g. for a item which is a event of id 4:
`edit 4 /from <start> /to <end>`

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo <title> /by <date-time> [/desc <description>] [/loc <location>] [/cmt <comment>]` <br> e.g., `todo Do UG /by 2023-02-25 23:00 /desc Git is fun /loc Home /cmt Do as a team`
**Add Event** | `event <title> /from <start> /to <end> [/desc <description>] [/loc <location>] [/cmt <comment>]` <br> e.g. `event CS2103T Lecture /from 2023-02-25 22:00 /to 2023-02-25 23:00`
**Delete** | `delete INDEX...`<br> e.g., `delete 1 2 3`
**List** | `list`
**Edit** | `edit INDEX (OPTION <argument>) ...`<br> e.g.,`edit 4 /from 2023-02-25 22:00 /to 2023-02-25 23:00`
**Help** | `help`


### Other

#### Saving the data

Calidr calendar information is saved in the hard disk automatically after any modification command. The data is stored as an [ics file](https://www.ical4j.org/).


#### Editing the data file
The `cal.ics` save file is located in the directory the program resides in (i.e. `[root_directory]/cal.ics`). Advanced users are welcome to manipulate data directly by editing the save file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
    If your changes to the save file makes its format invalid, Calidr will discard all data and start with an empty data file the next time it's run.
</div>

#### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Calindr home folder.

--------------------------------------------------------------------------------------------------------------------

