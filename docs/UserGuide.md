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
  e.g `c/COMPANY [d/KEYDATE]` can be used as `c/Google d/Interview@20230401` or as `c/Google`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[d/KEYDATE]…​` can be used as ` ` (i.e. 0 times), `d/Interview@20230401`, `d/Interview@20230401 d/OA@20230502` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `p/POSITION c/COMPANY`, `c/COMPANY p/POSITION` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `c/Google c/Shopee`, only `c/Shopee` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding an opening : `add`

Adds a new opening to the list.

Format: `add p/POSITION c/COMPANY e/EMAIL s/STATUS [d/KEYDATE]…​`

* `KEYDATE` must be in the form `KEY@DATE`, where `KEY` is a stage or event in the application process and `DATE` is the date of the stage in `YYYYMMDD` format.
* `STATUS` **must be of either found, applied, interviewing, offered, accepted or rejected**.

Examples:
* `add p/Software Engineer c/Google e/chinese@google.com s/interviewing d/Interview@20230314`

### Listing all openings : `list`

Shows the whole list of openings in Ultron.

Format: `list`

### Editing an opening : `edit`

Edits an existing opening.

Format: `edit INDEX [p/POSITION] [c/COMPANY] [e/EMAIL] [s/STATUS] [d/KEYDATE]…​`

* Edits the opening at the specified `INDEX`. The index refers to the index number shown in the displayed opening list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

* When editing `KEYDATE`, the existing `KEYDATE` of the opening will be removed i.e adding of `KEYDATE` is not cumulative.
* You can remove all the `KEYDATE` of the opening by typing `d/` without
  specifying any `KEYDATE` after it.

Examples:
*  `edit 1 c/Goggle e/johndoe@example.com` Edits the company and email address of the 1st opening to be `Goggle` and `johndoe@example.com` respectively.
*  `edit 2 c/Shopee d/` Edits the company of the 2nd opening to be `Shopee` and clears all existing `KEYDATE`.

### Locating openings by name: `find`

Finds openings whose NAME_OF_COMPANY contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `google` will match `Google`
* The order of the keywords does not matter. e.g. `Goldman Sachs` will match `Sachs Goldman`
* Only the name is searched.
* Only full words will be matched e.g. `Amaz` will not match `Amazon`
* Openings with NAME_OF_COMPANY matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `google amazon meta` will return `Google`, `Meta`, `Amazon Web Services`

Examples:
* `find Google` returns `google` and `Google Cloud`
* `find bank america` returns `Bank of America`, `Bank of Singapore`<br>
  <!--![result for 'find alex david'](images/findAlexDavidResult.png)-->

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
**Add** | `add p/POSITION c/COMPANY e/EMAIL s/STATUS [d/KEYDATE]…​` <br> e.g., `add p/Software Engineer c/Google e/chinese@google.com s/interviewing d/Interview@20230314`
**Edit** | `edit INDEX [p/POSITION] [c/COMPANY] [e/EMAIL] [s/STATUS] [d/KEYDATE]…​` <br> e.g., `edit 1 c/Goggle e/johndoe@example.com`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Status** | `status s/STATUS`<br> e.g., `status s/interviewing`
**List** | `list`
