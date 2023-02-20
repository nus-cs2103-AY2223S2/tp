---
layout: page
title: User Guide
---

Vaccination Management System (VMS) is a **desktop app for managing vaccination appointments, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, VMS can get your appointments sorted out with great efficiency.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `vms.jar` from [here](https://github.com/AY2223S2-CS2103-F11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your VMS.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar vms.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

<!--    * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app. -->

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Command format

The syntax of commands will take on the following format:

```text
{feature} {feature command}[ --{flag name}[ {flag parameter}]?]...
```

* Words enclosed in braces, `{` and `}`, are the names parameters to be supplied by the user.
* Words in square brackets, `[` and `]`, are optional.
* Items with `?` after them can be used zero or one time.
* Items with `...` after them can be used zero or more times.

### Types

#### `<string>`

Strings can take on any character sequence that do not contain `--` or new line characters.

#### `<integer>`

An integer value.

#### `<date>`

The supported date formats are:

* `yyyy-mm-ddThh:mm`
    eg. 2023-05-03T04:45
* `yyyy-m-d hhmm` - single and double digit day and months are supported.
    eg. 2023-5-3 0455
    * The following formats are also acceptable:
    * `yyyy-mm-d hhmm`
    * `yyyy-mm-dd hhmm`
    * `yyyy-m-dd hhmm`

#### `<phone-number>`

Only 8 digit Singapore numbers are allowed.

## Features

The following are the features that VMS supports.

* [Basic](#basic)
* [Patient](#patient---patient)
* [Appointment](#appointment---appointment)

Only the `basic` feature need not be specified when entering the commands.

### Basic - `basic`

The basic features of the application.

#### Exit the program - `exit`

Exits the application

```text
exit
```

### Patient - `patient`

The patient feature manages patient's information such as their contact information and medical records.

#### Add a patient - `add`

Adds a person to the system.

```text
patient add --name <string> [--phone <phone-number>] [--email <string>]
```

Example:

* `patient add --name John Doe --phone 98765432 --email johnd@example.com`

#### Delete a patient - `delete`

Deletes a patient. The patient will no long show up in systems, however, its records will still be contained in hard disk.

```text
patient delete --index <integer>
```

Example:

* `patent delete --index 5`

#### Locate a patient - `find`

Finds patients whose names contain any of the given keywords.

```text
patient find --name <string>
```

Example:

* `patient find --name john`

#### List all patients - `list`

Shows all patients contained in the system.

```text
patient list
```

### Appointment - `appointment`

The appointment feature manages patient's vaccination appointments.

#### Add an appointment - `add`

Adds an appointment.

```text
appointment add --patient <integer> --start <date> --end <date>
```

Example:

* `appointment add --patient 5 --start 2023-3-5 0700 --end 2023-3-5 0800`

#### Delete an appointment - `delete`

Deletes an appointment.

```text
appointment delete --index <integer>
```

Example:

* `appointment delete --index 5`

#### List all appointments - `list`

Shows all appointments contained in the system.

```text
appointment list
```

## Advance

VMS data are saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

Locations:
1. `[JAR file location]/data/patientlist.json`
2. `[JAR file location]/data/appointmentlist.json`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, VMS will discard all data and start with an empty data file at the next run.
</div>
