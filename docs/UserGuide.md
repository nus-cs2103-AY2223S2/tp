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

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Command line syntax

<div markdown="block" class="alert alert-info">

**:information_source: Command syntaxes presentation**<br>

* **Pink italicized bolded capitalized words** represents _placeholders_ that the reader will have to replace with a
  variable. For example, <code><var>PATIENT_ID</var></code> will represent a patient ID in commands or example outputs.
* **Backslash** (`\`) before line breaks represents a _command continuation_ where the following line break and
  backslash are to be replaced with aN EMPTY character. For example,<br>
  <pre>
  appointment add --p <var>PATIENT_ID</var> \
      --s <var>START_TIME</var> --e <var>END_TIME</var> \
      --v <var>VAX_NAME</var> \
  </pre>
  would have the same meaning as this
  <pre>
  appointment add --p <var>PATIENT_ID</var> --s <var>START_TIME</var> --e <var>END_TIME</var> --v <var>VAX_NAME</var>
  </pre>
* **Square brackets** (`[` and `]`) around arguments indicate that the argument is optional. For example,
  <br><code>[--n <var>NEW_NAME</var>]</code> would mean that <wbr><code>--n <var>NEW_NAME</var></code> is optional.
* **Three dots with no space** (`...`) <u>after</u> arguments indicates that multiple of the same type of
  argument can be repeated. For example <wbr><code>[--r <var>REQUIREMENT</var>]...</code> would mean that
  <code>--r <var>REQUIREMENT</var></code> can appear multiple times.
* **Three dots with no space** <u>before</u> and <u>after</u> an argument would indicate that a list of that argument
  is required. The elements of a list are delimited by commas (`,`) and the keyword `{EMPTY}` is used to represent an
  empty list. For example, <code>--g ...<var>GROUP</var>...</code> would mean that a list of
  <code><var>GROUP</var></code> is required. Accepted arguments may be
  <code>--g <var>GROUP_1</var>, <var>GROUP_2</var>, <var>GROUP_3</var></code> for a list of 3 groups or `--g {EMPTY}`
  for an empty list of groups.'
* **Triangle brackets** (`<` and `>`) around words represent a [type](#types).

</div>

### General command syntax

The general command line syntax is as follows:<br>

<pre>
<var>COMPONENT</var> <var>COMMAND_WORD</var> <var>PREAMBLE</var> [--<var>FLAG</var> <var>ARGUMENT</var>]...
</pre>

* <code><var>COMPONENT</var></code> is a variable of type [`<component>`](#component). It may be omitted if it is
  [`basic`](#basic---applications-basic-features).
* <code><var>PREAMBLE</var></code> is any text after <code><var>COMMAND_WORD</var></code> to the end of the command or
  the first `--` flag delimiter. Its type is command dependent and will be taken to be empty if
  <code><var>COMMAND_WORD</var></code> is immediately followed by `--`.
* <code><var>COMMAND_WORD</var></code> and <code><var>FLAG</var></code> are single word arguments that do no accept
  spaces.

##### Additional points

* `--` is used to delimit flags and cannot be present in any of the argument placeholders.
* Leading and trailing white spaces in <code><var>ARGUMENTS</var></code> and elements in lists will be ignored.

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

#### `add` - Add a patient

```text
patient add --name <string> --phone <phone-number> --dob <date> --bloodtype <string> --allergy <list<string>> --vaccine <list<string>>
```

Example:

* `patient add --n John Doe --p 98765432 --d 2001-03-19 --b B+ --a catfur --a pollen --v covax`

#### `delete` - Delete a patient

```text
patient delete --index <integer>
```

Example:

* `patent delete --index 5`

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
appointment add --patient <integer> --start <date> --end <date>
```

Example:

* `appointment add --patient 5 --start 2023-3-5 0700 --end 2023-3-5 0800`

#### `delete` - Delete an appointment

```text
appointment delete --index <integer>
```

Example:

* `appointment delete --index 5`

#### `list` - List all appointments

```text
appointment list
```

### `vaccination` - Vaccination functionalities

#### `add` - Add a vaccination type

Adds a new vaccination type as defined in the command into the system. If any of the optional arguments are omitted,
they will be set to their default values.

##### Syntax

<pre>
vaccination add <var>VAX_NAME</var> [--g ...<var>GROUP</var>...] [--lal <var>MIN_AGE</var>] [--ual <var>MAX_AGE</var>] \
    [--s <var>SPACING</var>] [--a <var>ALLERGY_REQ</var>] [--h <var>HISTORY_REQ</var>]...
</pre>

* <code><var>name</var></code> : `<groupName>` - the vaccination to create.
* <code><var>groups</var></code> : `<groupName>` - the groups the vaccination type classifies under.
* <code><var>minAge</var></code> : `<age>` - the minimum required age (inclusive) to take the vaccine.
* <code><var>maxAge</var></code> : `<age>` - the maximum require age (inclusive) to take the vaccine.
* <code><var>spacing</var></code> : +ve `<integer>` - the minimum time in days from the last vaccination taken to take
  this vaccine.
* <code><var>allergyReq</var></code> : `<requirement>` - the allergy requirement to take the vaccine.
* <code><var>historyReq</var></code> : `<requirement>` - the vaccination group history requirement to
  take the vaccine.

##### Example

```text
vaccination add Pfizer (Dose 1) --groups DOSE 1, PFIZER, VACCINATION \
    --lal 5 --s 56 \
    --a NONE::allergy1, allergy2, allergy3 \
    --h NONE::DOES 1 \
```

Copy and paste:<br>
`vaccination add Pfizer (Dose 1) --groups DOSE 1, PFIZER, VACCINATION --lal 5 --s 56 --a NONE::allergy1, allergy2, allergy3 --h NONE::DOES 1`
<br><br>
Output:<br>
{some ss}

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
