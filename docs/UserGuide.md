---
layout: page
title: User Guide
---

Ultron is a **desktop app for compiling job and internship openings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are familiar with the Ultron interface and type fast, it can help you organise your openings in a structured manner.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

Refer to the [Features](#features) below for details of each command.

_More details coming soon ..._


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

  e.g. in `status s/STATUS`, `STATUS` is a parameter which can be used as `status s/interviewing`.

* Items in square brackets are optional.<br>
  e.g `n/COMPANY_NAME [d/KEY_DATE]` can be used as `n/Google d/Interview@20230401` or as `n/Google`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[d/KEY_DATE]…​` can be used as ` ` (i.e. 0 times), `d/Interview@20230401`, `d/Interview@20230401 d/OA@20230502` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `p/POSITION n/COMPANY_NAME`, `n/COMPANY_NAME p/POSITION` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Google n/Shopee`, only `n/Google` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding an opening : `add`

Adds a new opening to the list.

Format: `add p/POSITION n/COMPANY_NAME e/EMAIL s/STATUS [d/KEY_DATE]…​`

* `KEY_DATE` must be in the form `EVENT@DATE`, where `EVENT` is a stage in the application process and `DATE` is the date of the stage in `YYYYMMDD` format.
* `STATUS` **must be of either found, applied, interviewing, offered, accepted or rejected**.

Examples:
* `add p/Software Engineer n/Google e/chinese@google.com s/interviewing d/Interview@20230314`

### Listing all openings : `list`

Shows the whole list of openings, regardless of whether the opening is found, applied, interviewing, offered, accepted or rejected.

Format: `list`

### Listing all opening statuses : `status`

Shows the list of openings based on the opening status provided.

Format: `status s/STATUS`

* Shows all openings with specified `STATUS`.
* `STATUS` **must be of either found, applied, interviewing, offered, accepted or rejected**.

Examples:
`status s/interviewing` produces a list of openings at the interview stage.

### Editing an opening : `edit`

Edits an existing opening.

Format: `edit INDEX [p/POSITION] [n/COMPANY_NAME] [e/EMAIL] [s/STATUS] [d/KEY_DATE]…​`

* Edits the opening at the specified `INDEX`. The index refers to the index number shown in the displayed opening list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

* When editing `KEY_DATE`, the existing `KEY_DATE` of the opening will be removed i.e adding of `KEY_DATE` is not cumulative.
* You can remove all the `KEY_DATE` of the opening by typing `d/` without
  specifying any `KEY_DATE` after it.

Examples:
*  `edit 1 n/Goggle e/johndoe@example.com` Edits the phone number and email address of the 1st opening to be `Goggle` and `johndoe@example.com` respectively.
*  `edit 2 n/Shopee d/` Edits the name of the 2nd opening to be `Shopee` and clears all existing `KEY_DATE`.

### Deleting an opening : `delete`

Deletes an opening from the list.

Format: `delete INDEX`

* Deletes the opening at the specified `INDEX`.
* The index refers to the index number shown in the displayed opening list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd opening in the opening list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Ultron data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add p/POSITION n/COMPANY_NAME e/EMAIL s/STATUS [d/KEY_DATE]…​` <br> e.g., `add p/Software Engineer n/Google e/chinese@google.com s/interviewing d/Interview@20230314`
**Edit** | `edit INDEX [p/POSITION] [n/COMPANY_NAME] [e/EMAIL] [s/STATUS] [d/KEY_DATE]…​` <br> e.g., `edit 1 n/Goggle e/johndoe@example.com`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Status** | `status s/STATUS`<br> e.g., `status s/interviewing`
**List** | `list`
