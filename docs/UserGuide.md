---
layout: page
title: User Guide
---
## About TeachMeSenpai

TeachMeSenpai is a **desktop app targeted at teaching assistants who have many students to keep track of.** It is tailored to assist the user in monitoring their students' progress, and details. 
As a teaching assistant, you can easily view and edit your student's details on the go. Tailored to fast-typist, TeachMeSenpai is built around a **Command Line Interface (CLI)**, complete with an **autocomplete** feature
to help you manage your students quicker than a traditional point-and-click interface can.

This user guide provides everything you need to know to get started with TeachMeSenpai and how to use its features.
Head over to the [Quick Start](#quick-start) section to get started with setting up, or the [Features](#features) section
to learn more about what TeachMeSenpai can do!

---

## **Table of Contents**
{:.no_toc}

1. Table of Contents
{:toc}

---

## Quick start

1.  Ensure you have `Java 11` or above installed in your Computer.
2.  Download the latest `teachmesenpai.jar` from [here](https://github.com/AY2223S2-CS2103T-W12-2/tp/releases/latest).
3.  Copy the file to the folder you want to use as the home folder for your application.
4.  Double-click the file to start the app.

[↑ Back to top](#table-of-contents)

---

## Features

> <ins>:bulb: **Notes about the command format:**</ins>
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.  
>   _(eg. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`)_
>
> - Items in square brackets are optional.  
>   _(eg. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`)_
> 
> - Items with `...` after them can be used multiple times including zero times.  
>   _(eg. `[t/TAG]...` can be excluded completely, or once `t/friend`, or twice `t/friend t/family`, etc.)_
> 
> - Parameters can be in any order.  
>   _(eg. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable)_
> 
> - If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.  
>   _(eg. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken)_
> 
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.  
>   _(eg. if the command specifies `help 123`, it will be interpreted as `help`)_

[↑ Back to top](#table-of-contents)

---

### Viewing help: `help`

Shows a URL to the `User Guide` page.

![help popup gui](images/user-guide/help_popup.jpg)

[↑ Back to top](#table-of-contents)

---

### Exiting the program: `exit` / `bye` / `quit`

Exits the program.

> Format: `exit`

<div markdown="span" class="alert alert-info">

>:bulb: **Note:** This is the same as closing the app via the top-right `x` button.

</div>

[↑ Back to top](#table-of-contents)

---

### Listing all students: `list`

Shows a list of the saved all students. Useful for resetting the list after using commands that modifies the list _(eg. [`find`](#findfilter-students-find))_.

> Format: `list`

[↑ Back to top](#table-of-contents)

---

### Adding a student: `add`

Adds a student to the list, along with their education level and any student-specific notes.

> Format: `add n/STUDENT_NAME [a/ADDRESS] [p/PHONE_NUM] [e/EMAIL] [edu/EDUCATION_LEVEL] [r/REMARK] [t/TAG]... [s/SUBJECT]...`

> :bulb: **Note**: You only the name ie. `n/` is compulsory. You can add details for other fields using commands
> that we will talk about later!

Examples:

- `add n/Shaun a/123 NUS Street r/Good in Japanese t/submitted`
- `add n/Shao Hong edu/Bachelors y2 r/Good in German s/German`
- `add n/Wen Li e/e07123456@u.nus.edu p/91234567 a/Kent Ridge PGPR r/Very hardworking :)`

<p align=center>
    <img alt="add before" src="images/user-guide/add_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>add</code> command</i>
</p>

<p align=center>
    <img alt="add after" src="images/user-guide/add_after.jpg" />
    <br><i><b>Above:</b> After entering <code>add</code> command</i>
</p>



[↑ Back to top](#table-of-contents)

---

### Editing a student: `edit`

Edits a student's info _(all info except remark)_. To remove a student's field, leave the value after the prefix blank _(eg. `a/ p/` to remove address & phone number)_.

> Format: `edit INDEX [n/STUDENT_NAME] [a/ADDRESS] [p/PHONE_NUM] [e/EMAIL] [edu/EDUCATION_LEVEL] [t/TAG]... [s/SUBJECT]...`

<div markdown="span" class="alert alert-info">

>:bulb: **Note:** `edit` command cannot edit the remark field of students. Use the [`remark`](#adding-remark-to-student-remark) command for editing remarks.

</div>

Examples:

- `edit 2 n/Shaun Tan r/Not good in Japanese`
- `edit 1 n/Lao Hong`
- `edit 3 a/ p/` _(removes student's address & phone number)_

<p align=center>
    <img alt="edit before" src="images/user-guide/edit_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>edit</code> command</i>
</p>

<p align=center>
    <img alt="edit after" src="images/user-guide/edit_after.jpg" />
    <br><i><b>Above:</b> After entering <code>edit</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Editing remark of student: `remark`

Edits a student's remark.

> Format: `remark INDEX [r/REMARK]`

Examples:

- `remark 2 r/Not good in Japanese`
- `remark 1 r/Bad in German`
- `remark 3` _(removes student's remark)_

<p align=center>
    <img alt="remark before" src="images/user-guide/remark_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>remark</code> command</i>
</p>

<p align=center>
    <img alt="remark after" src="images/user-guide/remark_after.jpg" />
    <br><i><b>Above:</b> After entering <code>remark</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---


### Showing a student's remark: `show`

Show the remark of the specified student. Useful for when the remark is too long to be displayed in the student list.

> Format: `show INDEX`

Examples:

- `show 2`

<p align=center>
    <img alt="show before" src="images/user-guide/show_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>show</code> command</i>
</p>

<p align=center>
    <img alt="show after" src="images/user-guide/show_after.jpg" />
    <br><i><b>Above:</b> After entering <code>show</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Find/filter students: `find`

Finds entries of students based on a keyword in the field that you want. <br>

The `find` command allows you to match keywords or partial keywords with the entries, for example:<br><br>
`find Sh` displays the students with names which contain `sh` in them, such as `Shaun` or `Amresh`.<br>
This applies to all fields EXCEPT tags and subjects, where you will have to enter and find them by the 
tags and subjects in full.

> Format: `find FIELD KEYWORDS...` <br>
* `FIELD` refers to the type of details such as name, address, email and so on.
* Input the field like so:
  * Name: `n/`
  * Address: `a/`
  * Email: `e/`
  * Phone No.: `p/`
  * Education: `edu/`
  * Remark: `r/`
  * Tags: `t/`
  * Subjects: `s/`

<div markdown="span" class="alert alert-info">

>:bulb: **Note:** No input in the `FIELD` defaults to finding by name.

</div>

Examples:

- `find Sh` will display the students named "Shao Hong" & "Shaun"
- `find a/ pas` will display students who stay at places which names that contain `pas` such as `Pasir Ris`
- `find t/ URGENT` will display students who have the exact tag `URGENT`
- `find s/ German` will display students who have the exact subject `German`

<p align=center>
    <img alt="find before" src="images/user-guide/find_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>find</code> command</i>
</p>

<p align=center>
    <img alt="find after" src="images/user-guide/find_after.jpg" />
    <br><i><b>Above:</b> After entering <code>find</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Delete a student: `delete`

Deletes the specified student from the address book.

> Format: `delete INDEX`

- Deletes the student at the specified `INDEX`.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

- `list` followed by `delete 2` deletes the 2nd student in the address book.

<p align=center>
    <img alt="delete before" src="images/user-guide/delete_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>delete</code> command</i>
</p>

<p align=center>
    <img alt="delete after" src="images/user-guide/delete_after.jpg" />
    <br><i><b>Above:</b> After entering <code>delete</code> command</i>
</p>

> :slightly_smiling_face: **Tip:** You can combine `find` and `delete` when you have a 
> very long list of students. <br>
> For instance, you can `find` the student(s) you want gone, and then `delete`
> using the index from the list displayed!

[↑ Back to top](#table-of-contents)

---

### Saving the data

TeachMeSenpai data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually 🙂

[↑ Back to top](#table-of-contents)

---

### Editing the data file

> ❗ **Caution:** If your changes to the data file makes its format invalid, TeachMeSenpai will discard all data and start with an empty data file at the next run.

[↑ Back to top](#table-of-contents)

---

# Command summary

| Action | Format, Examples                                                                                                                                                                                                                                       |
|--------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add    | `add n/STUDENT_NAME [a/ADDRESS] [p/PHONE_NUM] [e/EMAIL] [edu/EDUCATION_LEVEL] [r/REMARK] [t/TAG]... [s/SUBJECT]...`<br>eg. `add n/Shaun a/123 NUS Street e/shaun123@gmail.com edu/Year 1 r/Good in Japanese t/active t/hardworking s/CS2103T s/CS2101` |
| Delete | `delete INDEX`<br>eg. `delete 3`                                                                                                                                                                                                                       |
| Edit   | `edit INDEX [n/STUDENT_NAME] [a/ADDRESS] [p/PHONE_NUM] [e/EMAIL] [edu/EDUCATION_LEVEL] [t/TAG]... [s/SUBJECT]...` <br/>eg. `edit 1 n/Wen Li edu/Year 2`                                                                                                |
| Exit   | `exit`, `bye`, `quit`                                                                                                                                                                                                                                  |
| Find   | `find KEYWORD1 [KEYWORD2]` <br/>eg. `find Sh` <br/>eg. `find Shao Hong`                                                                                                                                                                                |
| List   | `list`                                                                                                                                                                                                                                                 |
| Remark | `remark INDEX [r/REMARK]` <br/>eg. `remark 2 r/Not good in Japanese`                                                                                                                                                                                   |
| Show   | `show INDEX` <br/>eg. `show 1`                                                                                                                                                                                                                         |

[↑ Back to top](#table-of-contents)

---

# Prefix summary

| Prefix | Meaning                                 | Example                               |
|--------|-----------------------------------------|---------------------------------------|
| n/     | Name of student                         | `n/Shao Hong`                         |
| p/     | Phone number of student                 | `p/81234567`                          |
| e/     | Email of student                        | `e/e07123456@u.edu.sg`                |
| a/     | Address of student                      | `a/16 Bukit Timah Road, S156213`      |
| edu/   | Education level of student              | `edu/P6`                              |
| r/     | Remark for student                      | `r/Good in German`                    |
| t/     | Tag of student                          | `t/active` or `t/submited t/late ...` |
| s/     | Subject that the student is being taught | `s/Math` or `s/Math s/Science ...`    |


[↑ Back to top](#table-of-contents)
