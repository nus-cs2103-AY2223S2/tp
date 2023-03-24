---
layout: page
title: User Guide
---

Group: AY2223S2-CS2103T-T15-4

## Introduction

CLIpboard is a desktop app that helps educators (like you!), especially those that tutor multiple classes, by managing their students’ particulars<strong> in an organised manner.</strong>

CLIpboard is optimized **for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It can get your student management tasks done faster than traditional GUI apps. CLIpboard is optimised for keyboard users, so if you can type fast, CLIpboard can work even faster.

![image](./images/Ui.png)

--------------------------------------------------------------------------------------------------------------

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest clipboard.jar from [here](https://github.com/AY2223S2-CS2103T-T15-4/tp/releases).
3. Copy the file to the folder you want to use as the *home folder* for your CLIpboard.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar clipboard.jar` command to run the application.&nbsp;
<br>e.g. your clipboard.jar is stored in `user/app/task/`, you run `cd user/app/task/` then `java -jar clipboard.jar`
<br>A GUI similar to the above should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it.
<br>e.g. typing `help` and pressing Enter will open the help window.
    <br>Some example commands you can try:
    1. `list` : Lists all students.
    2. `add n/John Doe p/98765432 e/johnd@example.com sid/A1234567X m/CS2103T` : Adds a student named John Doe with the particulars to the list.
    3. `delete 3` : Deletes the 3rd student shown in the current list.
    4. `exit` : Exits the app.
7. Refer to the commands list below for a detailed description for each command.

--------------------------------------------------------------------------------------------------------------

## Commands

List of commands:

- [Opening help window:](#opening-help-window-help) `help`
- [Scrolling through command history:](#scrolling-through-command-history-up-or-down-arrow-key) `UP` or `DOWN` arrow key
- [Listing all students:](#listing-all-students-list) `list`
- [Sorting list of students:](#sorting-list-of-students-sort) `sort`
- [Adding a student:](#adding-a-student-add) `add`
- [Editing a student:](#editing-a-student-edit) `edit`
- [Adding or deleting a remark:](#adding-or-deleting-a-remark-remark) `remark`
- [Deleting a student:](#deleting-a-student-delete) `delete`
- [View a student's information:](#view-a-students-information-view) `view`
- [Finding students by name:](#finding-students-by-name-find) `find`
- [Clearing all entries:](#clearing-all-entries-clear) `clear`
- [Uploading a student's photo:](#uploading-a-students-photo-upload) `upload`
- [Exiting the program:](#exiting-the-program-exit) `exit`

--------------------------------------------------------------------------------------------------------------

### Opening help window: `help`

Opens a window showing a link to this User Guide.

Format:
```
help
```

--------------------------------------------------------------------------------------------------------------

### Scrolling through command history: `UP` or `DOWN` arrow key

Scrolls through past commands in command box.

- Similar to Linux command-line interface, use the up or down arrow keys to scroll through previously typed commands in the command box.
- You may use the `LEFT` or `RIGHT` arrow keys to edit the command first.
- Press `ENTER` to execute the command.

--------------------------------------------------------------------------------------------------------------

### Listing all students: `list`

Lists all students in the student roster.

Format:
```
list
```

- Students will be listed in the order they were added to CLIpboard.
- To sort the students based on their names or student IDs, use the [`sort` command](#sorting-list-of-students-sort).

--------------------------------------------------------------------------------------------------------------

### Sorting list of students: `sort`

Sorts list of students in student roster.

Format:
```
sort <CATEGORY>
```

- Current possible categories are `name` and `id`

Examples:
- `sort name` will sort list of students alphabetically according to name.
- `sort id` will sort list of students alphanumerically according to student ID.

--------------------------------------------------------------------------------------------------------------

### Adding a student: `add`

Adds a student to the student roster.

Format:
```
add n/<NAME> p/<PHONE_NUMBER> e/<EMAIL> sid/<STUDENT_ID> m/<MODULES> [t/<TAG>]
```

- `t/<TAG>` field is optional.
- Adding a student with the same student ID is not allowed.
    - For example, if a student with a student ID of `A0123456X` already exists in CLIpboard, entering `add n/Tom p/99887766 e/tom@example.com sid/A0123456X m/CS2105` will display an error message.

Examples:
- `add n/John Doe p/98765432 e/johnd@example.com sid/A1234567X m/CS2103T`
- `add n/Roy Balakrishnan p/92624417 e/royb@example.com sid/A7356561X m/CS2105 t/T06`

--------------------------------------------------------------------------------------------------------------

### Editing a student: `edit`

Edits an existing student in the student roster.

Format:
```
edit <INDEX> [n/<NAME>] [p/<PHONE_NUMBER>] [e/<EMAIL>] [sid/<STUDENT_NUMBER>] [m/<MODULES>] [t/<TAG>]
```

- Edits student at index specified in `<INDEX>`.
- At least one field `[n/<NAME>]`, `[p/<PHONE_NUMBER>]`, `[e/<EMAIL>]`, `[sid/<STUDENT_NUMBER>]`, `[m/<MODULES>]`, or `[t/<TAG>]` must be provided.
- Fields entered following `edit <INDEX>` will replace the original fields.
- Fields not entered will not replace the original fields.

Examples:
- `edit 1 n/John Doe` will replace the name of the first student listed in the student list to 'John Doe'.
- `edit 4 p/99887766 m/CS2105` will replace the phone number and module of the fourth student listed in the student list to '99887766' and 'CS2105' respectively.

--------------------------------------------------------------------------------------------------------------

### Adding or deleting a remark: `remark`

Add or delete a remark to/from a student in the student roster.

Format for adding a remark:
```
remark <INDEX> [r/<REMARK]
```

- Adds a remark to student in the student list whose index is specified in the `<INDEX>` field.

Format for deleting a remark:
```
remark <INDEX>
```

- Deletes a remark from a student in the student list whose index is specified in the `<INDEX` field.

Examples:
- `remark 1 r/Loves watching Sci-Fi movies` will add a remark of 'Loves watching Sci-Fi movies' to the first student listed in the student list.
- `remark 2` will delete the remark from the second student listed in the student list.

--------------------------------------------------------------------------------------------------------------

### Deleting a student: `delete`

Deletes a student from the student roster.

Format:
```
delete <INDEX>
```

Examples:
- `delete 1` will delete 1st student listed in the student list panel of CLIpboard.

--------------------------------------------------------------------------------------------------------------

### View a student's information: `view`

Views personal information for a particular student.

Format:
```
view <INDEX>
```

Examples:
- `view 2` will display the full student details of the 2nd student in the student list on the view panel.

--------------------------------------------------------------------------------------------------------------

### Finding students by name: `find`

Finds students whose names contain any of the given keywords.

Format:
```
find <KEYWORDS> [<MORE_KEYWORDS>]
```

Examples:
- `find John` returns John.
- `find Alex Yu` returns `Alex Yeoh`, `Bernice Yu` (pictured below.)

![image](images/findAlexYuResult.png)

--------------------------------------------------------------------------------------------------------------

### Clearing all entries: `clear`

Clears all entries in the student roster.

Format:
```
clear
```

--------------------------------------------------------------------------------------------------------------

### Uploading a student's photo: `upload`

Uploads a student's photo to be displayed on the student roster.

Format:
```
upload <LOCAL_FILE_PATH>
```
- File path must be an absolute file path to the photo.
- For student photo to be reflected to a specific student in CLIpboard, photo must be named after the said student's student ID (i.e. `<STUDENT_ID>.png`)
- If a new photo with the same name as an existing photo in CLIpboard is uploaded, existing photo will be replaced.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Only images of file type .png is accepted
</div>

Examples:
- `upload /Users/AlexYeoh/Desktop/A0123456X.png`

--------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Exits the program.

Format:
```
exit
```
