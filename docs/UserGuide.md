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
  e.g. in `outcome o/STATUS`, `STATUS` is a parameter which can be used as `outcome o/ongoing`.

* Items in square brackets are optional.<br>
  e.g `n/NAME_OF_COMPANY [d/KEY_DATES]` can be used as `n/Google d/Interview@20230401` or as `n/Google`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[d/KEY_DATES]…​` can be used as ` ` (i.e. 0 times), `d/Interview@20230401`, `d/Interview@20230401 d/OA@20230502` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `p/POSITION n/NAME_OF_COMPANY`, `n/NAME_OF_COMPANY p/POSITION` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `c/12341234 c/56785678`, only `c/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding an opening : `add`

Adds a new opening to the list.

Format: `add p/POSITION n/NAME_OF_COMPANY c/CONTACT_NUMBER e/EMAIL s/STATUS [d/KEY_DATES]…​`

* `KEY_DATES` must be in the form `EVENT@DATE`, where `EVENT` is a stage in the application process and `DATE` is the date of the stage in `YYYYMMDD` format.
* `STATUS` **must be of either ongoing, successful or rejected**.

Examples:
* `add p/Software Engineer n/Google c/98371204 e/chinese@google.com s/ongoing d/Interview@20230314`

### Listing all openings : `list`

Shows the whole list of openings, regardless of whether the opening is ongoing, successful or rejected.

Format: `list`

### Listing all opening outcomes : `outcome`

Shows the list of openings based on the opening status provided.

Format: `outcome o/STATUS`

* Shows all openings with specified `STATUS`.
* `STATUS` **must be of either ongoing, successful or rejected**.

Examples:
`outcome o/ongoing` produces a list of ongoing openings.

### Editing an opening : `edit`

Edits an existing opening.

Format: `edit INDEX [p/POSITION] [n/NAME_OF_COMPANY] [c/CONTACT_NUMBER] [e/EMAIL] [s/STATUS] [d/KEY_DATES]…​`

* Edits the opening at the specified `INDEX`. The index refers to the index number shown in the displayed opening list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing `KEY_DATES`, the existing `KEY_DATES` of the person will be removed i.e adding of `KEY_DATES` is not cumulative.
* You can remove all the person’s `KEY_DATES` by typing `d/` without
  specifying any `KEY_DATES` after it.

Examples:
*  `edit 1 c/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Shopee d/` Edits the name of the 2nd person to be `Shopee` and clears all existing `KEY_DATES`.

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
**Add** | `add p/POSITION n/NAME_OF_COMPANY c/CONTACT_NUMBER e/EMAIL s/STATUS [d/KEY_DATES]…​` <br> e.g., `add p/Software Engineer n/Google c/98371204 e/chinese@google.com s/ongoing d/Interview@20230314`
**Edit** | `edit INDEX [p/POSITION] [n/NAME_OF_COMPANY] [c/CONTACT_NUMBER] [e/EMAIL] [s/STATUS] [d/KEY_DATES]…​` <br> e.g., `edit 1 c/91234567 e/johndoe@example.com`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Outcome** | `outcome o/STATUS`<br> e.g., `outcome o/ongoing`
**List** | `list`
