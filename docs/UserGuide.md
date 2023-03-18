---
layout: page
title: User Guide
---

LE TRACKER is a gamified tracking application that allows fast typist to easily log their lecture progress, search for lecture by mod code/ keywords/ topics for a stress-free learning environment. Unlike todo list applications, LE TRACKER is tailored to the needs of students; it provides additional information specific to lecture media such as watch progress and topics.

- Table of Contents
  - [Quick Start](#quick-start)
  - [Features](#features)

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Type the command in the command box.

3. Press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

- List
  - `list`: Lists the names of all the recorded modules
  - `list /mod {module_code}`: Lists all the lectures in the specified module
  - `list /mod {module_code} /lec {lecture_name}`: Lists all the videos in the specified module and lecture
- Add
  - `add-module /code {module_code} [/name {module_name}]`: Adds a module to Le Tracker
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

- Words encapsulated in `{}` are the parameters to be supplied by the user.<br>
  e.g. in `add-module /code {module_code}`, `{module_code}` is a parameter which can be used as `add-module /code CS2040`.

- Items in square brackets are optional.<br>
  e.g `/code {module_code} [/name {module_name}]` can be used as `/code CS2040 /name Data Structures & Algorithms` or as `/code CS2040`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `/code {module_code} /name {module_name}`, `/name {module_name} /code {module_code}` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `/code CS2030 /code CS2040`, only `CS2040` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Add a Module

Adds a module to Le Tracker

Format: `add-module /code {module_code} [/name {module_name}]`

- `module_code` has to be unique
- If the name argument is not specified, the module will have no name

Examples:

- `add-module /code CS2040 /name Data Structures & Algorithms`

### Add a Lecture

Adds a lecture to a module

Format: `add-lecture /module {module_code}`

- `module_code` has to belong to an existing module

Examples:

- `add-lecture /module CS2040`

### Add a Video

Adds a video to a lecture

Format: `add-video /module {module_name} /lecture {lecture_index} /video {video_name}`

- `module_code` must belong to an existing module
- `lecture_index` must belong to an existing lecture
- `video_name` has to be unique

Examples:

- `add-video /module CS2040 /lecture 1 /video lecture-01-part-1`

### Mark/Unmark a Video

Marks/Unmarks a video as watched/unwatched in a lecture of its specified module

Format: `mark /module {module_name} /lecture {lecture_index} /video {video_name}`

Format: `unmark /module {module_name} /lecture {lecture_index} /video {video_name}`

Examples:

- `mark /module CS2040 /lecture 1 /video lecture_01-part-1`
- `unmark /module CS2040 /lecture 1 /video lecture_01-part-1`

### Delete a Module

Deletes the specified module and all its embodied content from the application

Format: `delete-module CS2040`

- Deletes the module of the specified `module_code`
- If the module does not exist, nothing happens

Examples:

- `delete-module CS2040`

### Delete a Lecture

Deletes the specified lecture from the specified module

Format: `delete-lecture /module {module_code} /lecture {lecture_id}`

- Deletes the lecture of the specified `lecture_id` from the specified `module_code`
- The `lecture_id` **must be a positive integer** 1, 2, 3, ...

Examples:

- `delete-lecture CS2040 /lecture 1` deletes the 1st lecture in the results of the `list /module CS2040` command

### Delete a Video

Deletes the specified video from the specified lecture from the specified module

Format: `delete-video /module {module_code} /lecture {lecture_id} /video {video_id}`

- Deletes the video of the specified `video_id` from the specified `lecture_id` of the specified `module_code`
- `video_id` refers to the index number shown when listing the videos of the specified lecture using the `lecture_id` in the specified module using the `module_code`
- the `video_id` **must be a positive integer** 1, 2, 3, ...

Examples:

- `delete-video /module CS2040 /lecture 1 /video 3` deletes the 3rd video in the results of the `list /module CS2040 /lecture 1` command

### Tag a lecture

Tags a specified lecture from a specified module with a description

Format: `tag /module {module_code} /lecture {lecture_id} /description {tag_description}`

- Tag a lecture of the specified `lecture_id` from the specified `module_code`
- The `lecture_id` refers to the index number shown when listing the lectures specified in it’s `module_code`
- The `lecture_id` **must be a positive integer** 1, 2, 3, ...

Examples:

- `tag /module CS2040 /lecture 1 /description Boohoo` tags the 1st lecture in the results of the `list /module CS2040` command with the description `Boohoo`

### Delete a tag

Deletes a specified tag of a specified lecture

Format: `untag /module {module_code} /lecture {lecture_id} /tag {tag_id}`

- Untag a lecture of the specified `lecture_id` from the specified `module_code`
- The `lecture_id` refers to the index number shown when listing the lectures specified in it’s `module_code`
- The `lecture_id` **must be a positive integer** 1, 2, 3, …
- The `tag_id` refers to the index number shown when listing the tags available of a lecture
- The `tag_id` **must be a positive integer** 1, 2, 3, …

Example: `untag /module CS2040 /lecture 1 /tag 1` deletes the 1st tag of the 1st lecture in the results of the `list /module CS2040` command

### Progress

Displays lecture progress for a specified module

Format: `progress {module_code}`

- Displays progress of a specified `module_code`

Example: `progress CS2040S` lists progress for the module CS2040S

### List Modules

Lists all modules

Format: `list`

### List Lectures of Modules

Lists all lectures belonging to a specified module code

Format: `list /mod {module_code}`

- Lists all lectures of a specified `module_code`

Examples: `list /mod CS2040S` lists lectures belonging to CS2040S

### List Videos of Lectures

Lists all videos belonging to a specified lecture code of a specified module code

Format: `list /mod /{module_code} /lec {lecture_name}`

Examples: `list /mod CS2040 /lect wk1` Lists videos belongs to lecture wk1 of module CS2040S

### Saving the data

Le Tracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Le Tracker data are saved as a JSON file `[JAR file location]/data/letracker.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Le Tracker will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Le Tracker home folder.

---

## Command summary

| Action            | Format, Examples                                                                                                                                         |
| ----------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add a Module**  | `add-module /code {module_code} [/name {module_name}]` <br> e.g., `add-module /code CS2040 /name Data Structures & Algorithms`                           |
| **Add a Lecture** | `add-lecture /module {module_code}` <br> e.g., `add-lecture /module CS2040`                                                                              |
| **Add a Video**   | `add-video /module {module_name} /lecture {lecture_index} /video {video_name}` <br> e.g., `add-video /module CS2040 /lecture 1 /video lecture-01-part-1` |
| **Tag a Lecture** | `tag /module {module_code} /lecture {lecture_id} /description {tag_description}` <br> e.g, `tag /module CS2040 /lecture 1 /description Boohoo`           |
| **Delete a Tag**  | `untag /module {module_code} /lecture {lecture_id} /tag {tag_id}` <br> e.g, `untag /module CS2040 /lecture 1 /tag 1`                                     |
