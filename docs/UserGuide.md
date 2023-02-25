---
layout: page
title: User Guide
---

LE TRACKER is a gamified tracking application that allows fast typist to easily log their lecture progress, search for lecture by mod code/ keywords/ topics for a stress-free learning environment. Unlike todo list applications, LE TRACKER is tailored to the needs of students; it provides additional information specific to lecture media such as watch progress and topics.

- Table of Contents
  - [Quick Start](#quick-start)

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Type the command in the command box.

3. Press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

- List
  - `list /module`: Lists the names of all the recorded modules
  - `list /module /{module_code}`: Lists all the videos recorded in that module code
- Add
  - `add-module {module_code} [/name {module_name}]`: Adds a module to Le Tracker
  - `add-lecture /module {module_code}`: Adds a lecture to a module
  - `add-video /module {module_name} /lecture {lecture_index} /video {video_name}`: Adds a video to the module code
- Mark/Unmark
  - `mark /module {module_code} /lecture {lecture_index} /video {video_name}`: Marks a video as watched
  - `unmark /module {module_code} /lecture {lecture_index} /video {video_name}`: Unmarks a video as unwatched
- Delete
  - `delete-module {module_code}`: Deletes a module from Le Tracker
  - `delete-lecture /module {module_code} /lecture {lecture_id}`: Deletes the specified lecture from the specified module
  - `delete-video /module {module_code} /lecture {lecture_id} /video {video_id}`: Deletes the specified video from the specified lecture from the specified module
- Tag
  - `tag /module {module_code} /lecture {lecture_id} /description {tag_description}`: Tags a module from Le Tracker
  - `untag /module {module_code} /lecture {lecture_id} /tag {tag_id}`: Untags a module from Le Tracker

Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Add a Module
Adds a module to Le Tracker

Format: `add-module {module_code} [/name {module_name}]`
- `module_code` has to be unique
- If the name argument is not specified, the module will have no name

Examples:

- `add-module CS2040 /name Data Structures & Algorithms`

### Add a Lecture
Adds a lecture to a module

Format: `add-lecture /module {module_code}`
- `module_code` has to belong to an existing module

Examples:
- `add-lecture /module CS2040`

### Add a Video
Adds a video to a lecture
Format: add-video /module {module_name} /lecture {lecture_index} /video {video_name}
- `module_code` must belong to an existing module
- `lecture_index` must belong to an existing lecture
- `video_name` has to be unique

Examples:
- `add-video /module CS2040 /lecture 1 /video lecture-01-part-1`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd person in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |
