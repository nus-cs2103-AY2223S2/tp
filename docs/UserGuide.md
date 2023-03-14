---
layout: page
title: User Guide
---

Artistic addressbook (ArB) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ArB can get your contact and project management tasks done faster than traditional GUI apps.

## Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `arb.jar` from [here](https://github.com/se-edu/artistic addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your artistic addressbook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar arb.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list clients` : Lists all clients.

   * `add name/John Doe phone/98765432 email/johnd@example.com` : Adds a client named `John Doe` to the Artistic addressbook.

   * `delete client 3` : Deletes the 3rd client shown in the current client list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Syntax

`[ACTION VERB] [options]`

`<Required argument> [optional argument]`

All commands case insensitive

## Prefixes

1. `name/` -> name: name 
2. `email/` -> email: valid email 
3. `phone/` -> phone: valid phone 
4. `deadline/` -> deadline: valid date / date-time 
5. `client/` -> client: index of the client in the client list


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

### Viewing help: `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a client: `add client`

Adds a client to the application with the supplied details. The details that can be supplied are the name, email address and phone number of the client.

Only the name of the client is compulsory. 

The email address and phone number must be in a valid format. E.g. `XXX@gmail.com` or ```XXX@yahoo.com``` for emails and `12345678` for phone numbers.

Format: `add client name/NAME [email/EMAIL] [phone/PHONE_NUMBER]`

Examples:
* `add client name/Bob phone/12345678 email/bob@gmail.com`
* `add client name/Alice`
* `add client name/Clary phone/87654321 email/clary@gmail.com`

### List all clients: `list client`

List out all clients.

Format: `list client`

### Adding a project: `add project`

Adds a project to the application with the supplied details. The details that can be supplied are the name and deadline of the client.

Only the name of the project is compulsory. 

The deadline must be in a valid date or date and time format.

Format: `add project name/NAME [deadline/DEADLINE]`

Examples:
* `add project name/Background Commission deadline/2023-05-05`
* `add project name/Oil Paintingg`

### Listing all projects : `list project`

Shows a list of all projects in the application.

Format: `list project`

### Editing a client : `edit client`

Edits the client at the given index of the client list, changing only the given field(s).
Fields that can be changed:
* Name
* Email address
* Phone number

Format: `edit client <index> [name/NAME] [email/EMAIL] [phone/PHONE]`

Examples:
*  `edit client 1 email/new@email.com` Edits the email address of the 1st client to be `new@email.com`.
*  `edit client 3 name/Alice Risa phone/1234` Edits the name of the 3rd client to `Alice Risa` and phone number to `1234`. 

### Editing a project : `edit project`

Edits the project details at the given index of the project list, changing only the given field(s).
Fields that can be changed:
* Name
* Deadline

Format: `edit project <index> [name/NAME] [deadline/DEADLINE]`

Example:
*  `edit project 2 name/The Starry Night` Edits the name of the 1st project to be `The Starry Night`.

### Deleting a client : `delete client`

Deletes the client at the specified index of the client list.

Format: `delete client <index>`

Example:
*  `delete client 1` Deletes the first client in the list (if there is one).

### Deleting a project : `delete project INDEX`

Deletes the specified project from the artistic artistic addressbook.

Format: `delete INDEX`

* Deletes the project at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` project followed by `delete 2` deletes the 2nd project in the list of projects

### Mark a project as done : `mark INDEX`

Marks the specified project as done from the artistic artistic addressbook.

Format: `mark INDEX`

* Marks a specified project at a specified `INDEX` from the list of projects as done.
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list project` followed by `mark 2` marks the 2nd project in the list of projects as done

### Unmark a project as done : `unmark INDEX`

Unmarks the specified project as undone from the artistic artistic addressbook.

Format: `unmark INDEX`

* Unmarks a specified project at a specified `INDEX` from the list of projects as undone.
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list project` followed by `unmark 2` marks the 2nd project in the list of projects as not done

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Artistic addressbook's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Artistic addressbook's data is saved as a JSON file `[JAR file location]/data/arb.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, artistic addressbook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous artistic addressbook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Client** | `add client name/NAME [email/EMAIL] [phone/PHONE_NUMBER]​` <br> e.g., `add client name/Bob phone/12345678 email/bob@gmail.com` |
| **Add Project** | `add project name/NAME [deadline/DEADLINE]` <br> e.g., `add project name/Background Commission deadline/2023-05-05` |
| **Delete Client** | `delete client <index>`<br> e.g., `delete client 1` |
| **Delete Project** | `delete project <index>`<br> e.g., `delete project 1` |
| **Edit Client** | `edit client <index> [name/NAME] [email/EMAIL] [phone/PHONE]​` <br> e.g.,`edit client 3 name/Alice Risa phone/1234` |
| **Edit Project** | `edit project <index> [name/NAME] [deadline/DEADLINE]` <br> e.g., `edit project 2 name/The Starry Night` |
| **Mark Project** | `mark <index>` e.g. `mark 3` |
| **Unmark Project** | `unmark <index>` e.g. `unmark 3` |
| **List Client** | `list client` |
| **List Project** | `list project` |
