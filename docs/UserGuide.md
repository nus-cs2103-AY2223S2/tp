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

## Command syntax

<div markdown="block" class="alert alert-info">

**:information_source: Command syntaxes presentation**<br>

* Words enclosed in braces, `{` and `}`, represents the name of the parameter to be supplied. It may be accompanied by a type.
* Words enclosed in triangle brackets `<` and `>` represent a type.
* Items in square brackets, `[` and `]`, are optional.
* Items with `?` after them can be used zero or one time.
* Items with `...` after them can be used zero or more times.

Example:

1. `{component <component>}` would mean a `component` parameter of type `<component>` will have to be supplied
2. `[{component <component>}]` would meant the same as the first but is optional.
3. `{component <component>}?` and `{component <component>}...` would mean the same as the above but one or more times and zero or more times respectively.

</div>

### General command syntax

```text
{component <component>} {command word} [{preamble}] [--{flag name} {argument} [::{argument}]]...
```

* `{component}` may be omitted if it is `basic`.
* `--` is used to delimit flags.
* `::` is used to delimit parts of arguments of the same flag.
* Leading and trailing white spaces of arguments will be stripped off.

### Types

#### `<component>`

The list of available components are given in the [components section](#components).

#### `<string>`

Strings can take on any character sequence that do not contain `--` or new line characters.

#### `<groupName>`

A character sequence consisting of only alphanumeric characters, spaces and brackets excluding `<` and `>`.

#### `<integer>`

An integer value between `-2147483648` and `2147483647`.

#### `<age>`

An extension of `<integer>`, allowing only positive values (ie. `x >= 0`). Age also has a max value of `200` which is allowed to be exceeded, provided it conforms to `<integer>` restrictions as well. All values of age that exceed the max value will be evaluated to be equal.

#### `<bloodType>`

The list of blood types are:

* A+
* A-
* B+
* B-
* AB+
* AB-
* O+
* O-

All other values will be rejected

#### `<date>`

The supported date formats are:

* `yyyy-mm-ddThh:mm`<br>
    eg. 2023-05-03T04:45
* `yyyy-m-d hhmm` - single and double digit day and months are supported.<br>
  eg. 2023-5-3 0455
  * The following formats are also acceptable:
  * `yyyy-mm-d hhmm`
  * `yyyy-mm-dd hhmm`
  * `yyyy-m-dd hhmm`

#### `<phone-number>`

Only 8 digit Singapore numbers are allowed.

#### `<requirement>`

`<requirement>` arguments require 2 and only 2 parts. The general syntax is as follows:

```text
{reqType <reqType>} :: {reqSet <list <groupName>>}
```

* The position of `reqType` and `reqSet` arguments are not substitutable.
* `reqSet` cannot be an empty list
* Duplicates withing `reqSet` are allowed but are omitted.

#### `<reqType>`

Only the following values are allowed:

* `ALL` - all groups withing the `grpSet` of the requirement are required for a `true` evaluation.
* `ANY` - at least one occurrence of a group within the `grpSet` of the requirement is required for a `true` evaluation.
* `NONE` - zero occurrence of any groups in the `grpSet` of the requirement is required for a `true` evaluation.

## Components

Below shows a list of components, their supported command words and their usage.

### `basic` - Application's basic features

#### `exit` - Exit the program

```text
exit
```

#### `help` - Display help page

```text
help
```

### `patient` - Patient functionalities

##### Patient data

| Variable      | Is needed | Type                  | Accept multiple |
| ------------- | --------- | --------------------- | --------------- |
| `name`        | YES       | `<name>`              | NO              |
| `phone`       | YES       | `<phone-number>`      | NO              |
| `dateOfBirth` | YES       | `<date>`              | NO              |
| `bloodType`   | YES       | `<bloodType>`         | NO              |
| `allergy`     | NO        | list of `<groupName>` | YES             |
| `vaccine`     | NO        | list of `<groupName>` | YES             |

#### `add` - Add a patient

```text
patient add --name <string> --phone <phone-number> --d <date> --bloodtype <string> --a <groupName> --v <groupName>
patient add --name <string> --phone <phone-number> --d <date> --bloodtype <string>
```

Example:

* `patient add --n John Doe --p 98765432 --d 2001-03-19 --b B+ --a catfur --a pollen --v covax`
* `patient add --n John Doe --p 98765432 --d 2001-03-19 --b B+`

#### `delete` - Delete a patient

```text
patient delete <integer>
```

Example:

* `patent delete 5`

#### `find` - Locate a patient

Finds patients whose names contain any of the given keywords.

```text
patient find --name <string>
```

Example:

* `patient find --name john`

#### `list` - List all patients

```text
patient list
```

### `appointment` - Appointment functionalities

#### `add` - Add an appointment

```text
appointment add --p <integer> --s <date> --e <date> --v <string>
```

Example:

* `appointment add --p 5 --s 2023-03-05 0700 --e 2023-03-05 0800 --v Mordena`

#### `delete` - Delete an appointment

```text
appointment delete <integer>
```

Example:

* `appointment delete 5`

#### `list` - List all appointments

```text
appointment list
```

### `vaccination` - Vaccination functionalities

#### `add` - Add a vaccination type

```text
vaccination add {name <groupName>} [{groups <list <groupName>>}] [--lal {minAge <age>}] [--ual {maxAge <age>}] [--s {spacing <integer>}] [--a {allergyReq {requirement}}]... [--h {historyReq <requirement>}]...
```

* **name** - the name of the vaccination type to create.
* **groups** - the list of groups the vaccination type classifies under.
* **minAge** - the minimum required age (inclusive) to take the vaccine.
* **maxAge** - the maximum require age (inclusive) to take the vaccine.
* **spacing** - the minimum time in days from the last vaccination taken to take this vaccine.
* **allergyReq** - the allergy requirement to take the vaccine.
* **historyReq** - the vaccination group history requirement to take the vaccine.

Example:

* `vaccination add Pfizer (Dose 1) --groups DOSE 1, PFIZER, VACCINATION --lal 5 --s 56 --a NONE::allergy1, allergy2, allergy3 --h NONE::DOES 1`

#### `edit` - Edit a vaccination type

```text
vaccination edit <groupName> [{--name <groupName>}] [{groups <list <groupName>>}] [--lal {minAge <age>}] [--ual {maxAge <age>}] [--s {spacing <integer>}] [--a {allergyReq {requirement}}]... [--h {historyReq <requirement>}]...
```

* **name** - the name of the vaccination type to edit.
* **groups** - the list of groups the vaccination type classifies under.
* **minAge** - the minimum required age (inclusive) to take the vaccine.
* **maxAge** - the maximum require age (inclusive) to take the vaccine.
* **spacing** - the minimum time in days from the last vaccination taken to take this vaccine.
* **allergyReq** - the allergy requirement to take the vaccine.
* **historyReq** - the vaccination group history requirement to take the vaccine.

Example:

* `vaccination edit Pfizer (Dose 1) --n Pfizer (Dose 2) --groups DOSE 2, PFIZER, VACCINATION --lal 5 --s 56 --a NONE::allergy1, allergy2, allergy4 --h NONE::DOES 1`

## Advance

VMS data are saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

Locations:
1. `[JAR file location]/data/patientlist.json`
2. `[JAR file location]/data/appointmentlist.json`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, VMS will discard all data and start with an empty data file at the next run.
</div>

### Vaccination type JSON

Vaccination type JSON files will have the following format:

##### Overall file

| Variable | Is needed | Type                      | Default value |
| -------- | --------- | ------------------------- | ------------- |
| `types`  | YES       | List of vaccination types | -             |

##### Vaccination type

| Variable      | Is needed | Type                    | Default value |
| ------------- | --------- | ----------------------- | ------------- |
| `name`        | YES       | `<groupName>`           | -             |
| `groups`      | NO        | list of `<groupName>`   | `[]`          |
| `minAge`      | NO        | `<age>`                 | `0`           |
| `maxAge`      | NO        | `<age>`                 | `200`         |
| `minSpacing`  | NO        | `integer`               | `2147483647`  |
| `historyReqs` | NO        | list of `<requirement>` | `[]`          |
| `allergyReqs` | NO        | list of `<requirement>` | `[]`          |

##### Requirement

| Variable  | Is needed             | Type                  | Default value |
| --------- | --------------------- | --------------------- | ------------- |
| `reqType` | YES                   | `<reqType>`           | -             |
| `reqSet`  | YES (cannot be empty) | list of `<groupName>` | -             |

##### Example

```json
{
  "types": [
    {
      "name": "Dose 1 (Pfizer)",
      "groups": ["DOSE 1", "Pfizer", "Vaccination"],
      "minAge": 5,
      "minSpacing": 56,
      "historyReqs": [
        {
          "reqType": "NONE",
          "reqSet": ["DOSE 1"]
        }
      ],
      "allergyReqs": [
        {
          "reqType": "NONE",
          "reqSet": [
            "ALC-0315",
            "ALC-0159",
            "DSPC",
            "Cholesterol",
            "Sucrose",
            "Phosphate",
            "Tromethamine",
            "Tromethamine hydrochloride"
          ]
        }
      ]
    }
  ]
}
```
