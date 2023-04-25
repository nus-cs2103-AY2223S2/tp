---
layout: page
title: User Guide
---
## About TeachMeSenpai

TeachMeSenpai is a **desktop app targeted at NUS teaching assistants who have many students to keep track of.** It is tailored to assist you in monitoring your students' progress, and details.

As a teaching assistant, you can add all your students' particulars, make notes about them, quickly navigate through your long list of students and so much more!
If using our app seems daunting to you at first, don't worry, we even have an [autocomplete feature](#autocompletion) to guide you through every step of the way!
Tailored to fast-typists, TeachMeSenpai is built around a [**Command Line Interface (CLI)**](#glossary-cli), complete with an **autocomplete** feature and a [**Graphical User Interface** (GUI)](#glossary-gui)
to help you manage your students quicker than a traditional point-and-click app can.

## About this User Guide
This user guide provides everything you need to know to set up TeachMeSenpai and how to use its features.
Head over to the [Quick Start](#quick-start) section to get started with setting up, or the [Features](#features) section
to learn more about what TeachMeSenpai can do for you! If at any point there are any unfamiliar terms, you may head over to the [Glossary](#glossary)
for an explanation.

### Navigating the User Guide
**Note Box**
<div markdown="span" class="alert alert-info">:information_source: **Note:** Provides information that is useful to know.
</div>

**Tip Box**
<div markdown="span" class="alert alert-success">:bulb: **Tip:** Provides information that can help enhance the user experience but is not necessary to know.
</div>

**Warning Box**
<div markdown="span" class="alert alert-danger">:exclamation: **Warning:** Important information to take note of to avoid any unintended effects!
</div>

**Hyperlinks**

[Links](#navigating-the-user-guide): Words highlighted in blue are clickable and will direct you to a relevant section within
this user guide for more information, or to external websites to learn more!

---

## **Table of Contents**
{:.no_toc}

* Table of Contents
{:toc}

---

## Quick start

TeachMeSenpai has been designed to work for all [Operating Systems](#glossary-os) (Mac, Windows, or Linux etc.)!

1. Ensure you have `Java 11` or above installed in your computer (you may refer to [Java Help Resources](https://www.java.com/en/download/help/version_manual.html) if you're not sure how to do so).
2. Download the latest `teachmesenpai.jar` from [here](https://github.com/AY2223S2-CS2103T-W12-2/tp/releases/latest).
3. Copy or move the file to the folder you want to use as the home folder for your application.
4. Double-click the file to start the app.
5. If everything works well, you should be able to see the following User Interface.
<p align=center>
    <img alt="TeachMeSenpai upon launch" src="images/user-guide/sample_GUI.jpg" />
</p>




[↑ Back to top](#table-of-contents)

---

## Guide to TeachMeSenpai's [GUI](#glossary-gui)

TeachMeSenpai has several important components as part of its display. Here is a quick walk-through each component.

<p align=center>
  <img alt="Main window components" src="images/user-guide/mainwindow-components.jpg" />
</p>

<h3 style="color:Gray"><b>Menu bar</b></h3>
* Clicking `File` will give you the option to exit the app
* Clicking `Help` will show `Help F1`, clicking it will provide you a link to this User Guide!

<h3 style="color:MediumSeaGreen" id="command-line"><b>Command line</b></h3>
* This is where you will be typing all the commands for TeachMeSenpai! To learn more about the commands TeachMeSenpai has, head over
to [Features](#features).

<h3 style="color:DodgerBlue"><b>Feedback Box</b></h3>
* This is where TeachMeSenpai will provide you with feedback for your commands! Whether they were successfully done or not.
You will see more examples of the feedback TeachMeSenpai provides in the [Features](#features) section.

<h3 style="color:gold"><b>Result Box</b></h3>
* This is where the results for commands like [`show`](#showing-a-students-remark-show) and [`edit`](#editing-a-student-edit) will show up!
* This section is scrollable when necessary.

<h3 style="color:red"><b>Student List</b></h3>
* This is where you can view all your student entries and their details!
* This section is scrollable as well.

### Adjusting the size of TeachMeSenpai

If the app is too small for your liking, you may resize it by dragging the sides of the window with your cursor! The red lines in the image below are moveable
so feel free to adjust the app however you like!

<p align=center>
  <img alt="Resizing guide" src="images/user-guide/resize-window.jpg" />
</p>

<div markdown="span" class="alert alert-success">:bulb: **Tip:** Hover your mouse over the red lines indicated by the image on your app under you see an arrow cursor, this will allow
you to adjust the size of the window
</div>

[↑ Back to top](#table-of-contents)

---

## Guide on using Features

## Parameter descriptions

Firstly, parameters are the inputs/information you have to fill in together with their respective commands in the [command line](#command-line) when using TeachMeSenpai!

Here is an exhaustive table for you to refer to if you're unsure of what to input when using the various [features](#features) below this section!

| Parameter         | Meaning                                                                               | Notes                                                                                                                                                                                                                                |
|-------------------|---------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ADDRESS`         | Address of the student.                                                               | Can contain numbers, symbols and multiple letters/words                                                                                                                                                                              |
| `EDUCATION` | Education level of the student.                                                       | Can contain numbers and multiple letters/words                                                                                                                                                                                       |
| `EMAIL`           | Email address of the student.                                                         | 1. Follow the format local-part@domain <br/> 2. Must contains `@` symbol <br/> 3. Must not start with a non-[alphanumeric](#glossary-alphanum) character (eg. . , ' " @) <br/> 4. Can contain letters and numbers <br/> 5. Should not contain any spacings |
| `INDEX`           | The number next to the student entry upon using [`list`](#listing-all-students-list). | Must be a positive number (eg. 1, 2, 3...)                                                                                                                                                                                           |
| `MODULE`          | The module you're teaching the student.                                               | 1. Can only contains [alphanumeric](#glossary-alphanum) characters and/or spaces <br/> 2. Any whitespaces in front of the module given will be removed by the app                                                                                          |
| `PHONE`           | Phone number of the student.                                                          | 1. Must only contain numbers <br/>  2. Must be at least 3 numbers long                                                                                                                                                               |
| `REMARK`          | Your notes or remarks on the student.                                                 | Can contain any combination of words, numbers and special characters                                                                                                                                                                 |
| `NAME`    | Name of the student.                                                                  | 1. Can only contain [alphanumeric](#glossary-alphanum) characters and/or spaces <br/> 2. Any whitespaces in front of the name given will be removed by the app                                                                                             |
| `TAG`             | Qualities of the student you'd like to be shown as a [tag](#adding-a-student-add).    | Must be a single word containing [alphanumeric](#glossary-alphanum) characters only                                                                                                                                                                        |
| `TELEGRAM`        | Telegram handle of the student.                                                       | 1. Must begin with "@"<br/> 2. Can only contain uppercase and lowercase alphabets, digits (0-9), and underscores<br/> 3. Minimum length is 5 characters                                                                              |

<div markdown="block" class="alert alert-info">

:information_source: **Note:** The description for a valid Telegram handle has been shortened for brevity and thus might not cover all cases.
* For example, one might think that "@hello__world" is allowed but it isn't due to having consecutive underscores.
* TeachMeSenpai does validate handles according to Telegram specifications, but do check on the Telegram application for whether a handle is valid!

</div>

[↑ Back to top](#table-of-contents)

---

## Features

<div markdown="block" class="alert alert-info">

:information_source: **Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user. \\
  _(eg. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`)_

- Items in square brackets are optional. \\
  _(eg. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`)_

- Items with `...` after them can be used multiple times including zero times. \\
  _(eg. `[t/TAG]...` can be excluded completely, or once `t/friend`, or twice `t/friend t/family`, etc.)_

- Parameters can be specified in any order. \\
  _(eg. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable)_

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken. \\
  _(eg. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken)_

- Extraneous words for commands that do not take in parameters (such as `help`, `list`, `redo`, `undo`, `show`, `exit` and `clear`) will be ignored. \\
  _(eg. if the command entered is `help 123`, it will be interpreted as `help`)_

</div>

[↑ Back to top](#table-of-contents)

---

### Autocompletion

TeachMeSenpai has a lot of features for you to experiment with, and we understand that it might take some getting used to. Hence, we've included an autocompletion feature!

Start typing the first letters of a command to get shadow-like autocomplete suggestions.
The format of the suggested parameters are as described in the [**Guide on using Features**](#guide-on-using-features).

<div markdown="span" class="alert alert-success">:bulb: **Tip:** You can press your `TAB` key to fill in the next suggested word!
</div>

![help popup gui](images/user-guide/autocomplete.jpg)

<br>

Additionally, for the module, tag and education parameters, it will also suggest all the existing values to you!

![help popup gui](images/user-guide/autocomplete_arg_values.jpg)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The `|` seen above are not part of the commands TeachMeSenpai accepts. They're simply there to separate the different existing modules/education/tags you have in the current student list.
</div>

<br>

The autocompletion feature also hints to you if you've entered an invalid command-word or an invalid index!

![autocomplete invalids](images/user-guide/autocomplete_heuristics.jpg)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The above invalid-input hints does not check if the parameters you gave are actually valid. Just because the words aren't red, does not imply that your input will execute when you press enter!
</div>

[↑ Back to top](#table-of-contents)

---

### Viewing help: `help`

Shows a URL to the `User Guide` page.

![help popup gui](images/user-guide/help_popup.jpg)

<div markdown="span" class="alert alert-success">:bulb: **Tip:** Click `F1` as a keyboard shortcut to view the help pop-up!.
</div>

[↑ Back to top](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program.

> Format: `exit`

<div markdown="span" class="alert alert-info">:information_source: **Note:** This is the same as closing the app via the top-right `x` button.
</div>

[↑ Back to top](#table-of-contents)

---

### Listing all students: `list`

Shows a list of all students. This is useful for displaying the full list after using commands that modifies the list _(eg. [`find`](#findfilter-students-findfilter))_.

> Format: `list`

<p align=center>
    <img alt="list before and after" src="images/user-guide/list_remark_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>list</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Adding a student: `add`

Adds a student to the list, along with their education level and any student-specific notes.

> Format: `add n/NAME [a/ADDRESS] [p/PHONE] [e/EMAIL] [edu/EDUCATION] [tele/TELEGRAM] [r/REMARK] [t/TAG]... [m/MODULE]...`
>
> - NAME: [Alphanumeric](#glossary-alphanum) word(s).
> - ADDRESS: The student's address.
> - PHONE: A student's phone number that only contains numbers and is as least 3 numbers long.
> - EMAIL: A valid email follows the `local@domain` format.
> - EDUCATION: [Alphanumeric](#glossary-alphanum) word(s)
> - TELEGRAM: A valid telegram handle must start with `@` and follows Telegram's username specifications
> - REMARK: Can be sentences or even paragraphs.
> - TAG: A detail about a student (limited to one word long).
> - MODULE: The module code the student is taking (can be multiple words).

For more details and examples on the parameters, please refer to the [Parameter descriptions](#parameter-descriptions)!

<div markdown="block" class="alert alert-info">

:information_source: **Note:**
* `EDUCATION`, `TAG`,  and  `MODULE` will be displayed as coloured tags under the student's name.
* Only the name ie. `n/` is compulsory. You can add/edit details for other parameters using the [`edit`](#editing-a-student-edit) command.
* The current version of TeachMeSenpai allows you to add students with the same name (case-insensitive), phone number, and/or telegram handle! However, we have planned
a future enhancement that will only allow unique names (case-sensitive), phone numbers, and telegram handles.

</div>

Examples:

- `add n/Shaun a/123 NUS Street r/Good in Japanese t/submitted m/CS2103T`
- `add n/Shao Hong edu/Bachelors y2 r/Good in German m/CS2101`
- `add n/Wen Li e/e07123456@u.nus.edu p/91234567 a/Kent Ridge PGPR tele/@wenlisan r/Very hardworking :)`


**Using the following input** `add n/Shao Hong edu/Bachelors y2 r/Good in german m/CS2101`
<p align=center>
    <img alt="add before and after" src="images/user-guide/add_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>add</code> command with student's details</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Delete a student: `delete`

Deletes the student(s) at the specified index(es) from the student list.

> Format: `delete INDEX [INDEX]...`

- Deletes the student at the specified `INDEX`.
- More than 1 `INDEX` can be specified, and all of them will be deleted.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, ...
- There **must not have any duplicate indexes**.

<div markdown="span" class="alert alert-info">:information_source: **Note:** As a failsafe, if there're duplicates of the same `INDEX` or if one of the `INDEX` specified are invalid _(do not correspond to a student in the list)_, the command won't execute.
</div>

Examples:

- `list` followed by `delete 2` deletes the 2nd student in the student list.
- `delete 1 4` deletes the 1st and 4th student in the student list.

**Using the following input** `delete 1 4`
<p align=center>
    <img alt="delete before" src="images/user-guide/delete_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>delete 1 4</code> command</i>
</p>

<div markdown="span" class="alert alert-success">:bulb: **Tip:** You can combine `find` and `delete` when you have a very long list of students.
<br>For instance, you can `find` the student(s) you want gone, and then `delete` using the index from the list displayed!
</div>

[↑ Back to top](#table-of-contents)

---

### Editing a student: `edit`

Edits a student's information _(all information except remarks)_. To remove a student's parameter, leave the value after the [prefix](#glossary-prefix) blank _(eg. `a/ p/` to remove address & phone number)_.

> Format: `edit INDEX [n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [edu/EDUCATION] [tele/TELEGRAM] [t/TAG]... [m/MODULE]...`
>
> - The index refers to the index number shown in the displayed student list.
> - The index **must be a positive integer** 1, 2, 3, ...
> - NAME: [Alphanumeric](#glossary-alphanum) word(s).
> - ADDRESS: The student's address.
> - PHONE: A student's phone number that only contains numbers and is as least 3 numbers long.
> - EMAIL: A valid email follows the `local@domain` format.
> - EDUCATION: [Alphanumeric](#glossary-alphanum) word(s)
> - TELEGRAM: A valid telegram handle must start with `@` and follows Telegram's username specifications
> - TAG: A detail about a student (limited to one word long).
> - MODULE: The module code the student is taking (can be multiple words).

For more details and examples on the parameters, please refer to the [Parameter descriptions](#parameter-descriptions)!

<div markdown="block" class="alert alert-info">
:information_source: **Note:**
* `edit` command cannot edit the remark parameter of students. Use the [`remark`](#editing-remark-of-student-remark) command for editing remarks.
* `edit` command does not allow you to change the name of a student to an existing student's name in the student list!
</div>

Examples:

- `edit 2 n/Shaun Tan r/Not good in Japanese`
- `edit 1 n/Lao Hong`
- `edit 3 a/ p/` _(removes student's address & phone number)_

**Using the following input** `edit 1 edu/Year 1`
<p align=center>
    <img alt="edit before and after" src="images/user-guide/edit_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>edit 1 edu/Year 1</code> command</i>
</p>

<div markdown="block" class="alert alert-info">:information_source: **Note:** After using `edit`, the edited student will show up in the [result box](#guide-to-teachmesenpais-gui) to help you view all the
edited student's details.
</div>

[↑ Back to top](#table-of-contents)

---

### Editing remark of student: `remark`

Edits a student's remarks.

> Format: `remark INDEX [REMARK]`
> - The index refers to the index number shown in the displayed student list.
> - The index **must be a positive integer** 1, 2, 3, ...

With `remark` you can type your desired remarks and notes into the popup text box! The text box can take in
multiple sentences, paragraphs etc. When you are done, you can simply press `Ctrl` + `S`
and your remarks will be saved once the text box closes.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** Do not edit or type commands into the [command line](#command-line) of TeachMeSenpai while the remark text box is open!
The current version of TeachMeSenpai requires you to close the remark text box before you continue with other commands :)
</div>

Examples:

- `remark 2`

**Using the following input** `remark 2`:
<p align=center>
    <img alt="remark before" src="images/user-guide/remark_before.jpg" />
    <br><i><b>Above:</b> Before entering <code>remark 2</code> command</i>
</p>

<p align=center>
    <img alt="remark after" src="images/user-guide/remark_during.jpg" />
    <br><i><b>Above:</b> Editing remarks using the <code>remark 2</code> command</i>
</p>

<p align=center>
    <img alt="remark during" src="images/user-guide/remark_after.jpg" />
    <br><i><b>Above:</b> After pressing <code>CTRL + S</code></i>
</p>

<div markdown="span" class="alert alert-info">
:information_source: **Note:** Notice that remarks in the [student list](#guide-to-teachmesenpais-gui) (right panel) are truncated to make your student list more succinct :)
To view the remarks in detail, you may use the [`show`](#showing-a-students-remark-show) feature!
</div>

[↑ Back to top](#table-of-contents)

---


### Showing a student's remark: `show`

Displays all the details, including the full remark of a specified student. This is useful for when the remark is too long to be displayed fully in the student list panel.

> Format: `show INDEX`

Examples:

- `show 1`

**Using the following input** `show 1`:
<p align=center>
    <img alt="show before" src="images/user-guide/show_remark_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>show 1</code> command</i>
</p>

<div markdown="span" class="alert alert-success">:bulb: **Tip:** After typing a long remark with `remark`, use `show` to view the full remark in the [result box](#guide-to-teachmesenpais-gui)
</div>

[↑ Back to top](#table-of-contents)

---

### Find/filter students: `find`/`filter`

Finds students based on a keyword in the parameter that you want.

The `find` and `filter` commands allow you to match keywords or partial keywords with the entries, for example:

<!-- Prevent adding the Find section into the TOC to reduce clutter in TOC. -->
#### Find
{:.no_toc}

The `find` command allows you to zoom in on entries that matches **all** search criterias!

`find n/Sh n/Na` displays the students with names containing both `sh` and `na` _(case-insensitive)_, such as `Shanna` or `Nashira`. This partial-keyword matching _(eg. matching `sh` in `Shaun`)_ applies to all parameters EXCEPT tags and modules, where you will have to enter the keywords in full.

> Format: \\
> `find [n/NAME]... [p/PHONE]... [e/EMAIL]... [a/ADDRESS]... [edu/EDUCATION]... [tele/TELEGRAM]... [r/REMARK]... [t/TAG]... [m/MODULE]...`

<!-- Prevent adding the Find section into the TOC to reduce clutter in TOC. -->
#### Filter
{:.no_toc}

Unlike `find`, the `filter` command instead show entries that match **at least one** of the search criterias!

`filter n/Sh n/Na` displays the students with names containing either `sh` or `na` _(case-insensitive)_, such as `Sheryl` or `Nala`. Same as `find`, partial-keyword matching doesn't apply to tags and modules, where you will have to enter the keywords in full.

> Format: \\
> `filter [n/NAME]... [p/PHONE]... [e/EMAIL]... [a/ADDRESS]... [edu/EDUCATION]... [tele/TELEGRAM]... [r/REMARK]... [t/TAG]... [m/MODULE]...`

Please refer to the [Prefix Summary](#prefix-summary) for more details!

<div markdown="block" class="alert alert-success">:bulb: **Tip:** 

* Using `find` without specifying any fields has the same outcome as `list`! TeachMeSenpai will simply display the list of all your students.
* Using `filter` without specifying any fields will result in an empty list!
</div>

Examples:

- `find n/Sh` will display the students named "Shao Hong" & "Shaun"
- `find a/pas` will display students who stay at places which names that contain `pas` such as `Pasir Ris`
- `find t/URGENT` will display students who have the exact tag `URGENT`
- `find m/CS2101` will display students who have the exact module `CS2101`

**Using the following input** `find n/a m/CS2103T`:
<p align=center>
    <img alt="find before" src="images/user-guide/find_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>find n/a m/CS2103T</code> command</i>
</p>

<div markdown="block" class="alert alert-info">:information_source: **Note:** Here find shows only `Alex Yeoh` as his entry matches all criteria.
</div>

**Using the following input** `filter n/a m/CS2103T` :
<p align=center>
    <img alt="filter before" src="images/user-guide/filter_before_and_after.png" />
    <br><i><b>Above:</b> Before and after entering <code>filter n/a m/CS2103T</code> command</i>
</p>

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here, other entries that only match some criteria like `Roy Balakrishnan`, who only matches `n/a`
are shown too.
</div>

<div markdown="span" class="alert alert-success">:bulb: **Tip:** `find` followed by an `edit` will result in the full student list being shown! (similar to running a third command `list`)
</div>

[↑ Back to top](#table-of-contents)

---

### Undo a previous command: `undo`

Did you make a mistake with a command? Don't worry, simply `undo` it!

You can `undo` as many times as required, as long as any of your previous commmands were **undoable**. All **undoable** commands are given below!

<div markdown="span" class="alert alert-info">:information_source: **Note:** You can only undo `add`, `edit`, `delete`, `remark`, `clear`, and `redo` commands as these are the only commands that change the data of your TeachMeSenpai app! Trying to `undo` without having ANY of the above commands prior will cause TeachMeSenpai to show you an error message
</div>

> Format: `undo`

Examples:

- `delete 2` followed by 'undo' will delete the 2nd student in the list, but `undo` will reverse this deletion and add the student back.
- `add n/Wen Li p/88889999` followed by `undo` will add a student Wen Li, but `undo` will remove this entry from the list.
- `list` followed by `undo` will cause TeachMeSenpai to give you an error message since `list` is not **undoable**
- `delete 2` followed by `list` then `undo` will reverse the deletion done before `list`!

**Using the following inputs** `delete 1 4` followed by `undo`:

<p align=center>
    <img alt="undo before and after" src="images/user-guide/undo_before_and_after.png" />
    <br><i><b>Above:</b> Original state before entering <code>delete 1 4</code>, before entering <code>undo</code> command and after entering <code>undo</code> command</i>
</p>

[↑ Back to top](#table-of-contents)

---

### Redo a previous `undo`: `redo`

Did you `undo` a command on accident? Fret not, you may use `redo` to reverse the changes done by `undo`!

<div markdown="span" class="alert alert-info">:information_source: **Note:** You can only redo an `undo` command. Trying to `redo` without ANY prior `undo` command will simply cause TeachMeSenpai to give you an error message.
</div>

>Format: `redo`

Examples:

- `delete 2`, followed by `undo`, then `redo` has the same effect as `delete 2`
- `list` followed by `redo` will result in an error message from TeachMeSenpai.
- `delete` 2 followed by `redo` will result in an error message from TeachMeSenpai.

[↑ Back to top](#table-of-contents)

---

### Clearing all entries: `clear`

Need to clear all your entries? Simply use `clear` to help you delete all your entries at once!

>Format: `clear`

<div markdown="span" class="alert alert-info">:information_source: **Note:** Don't worry if you cleared all your entries by accident, simply use `undo` to reverse the change! Do note that if you exit the app immediately after `clear`, using `undo` upon relaunching the app can't retrieve your data anymore :(
</div>

### Saving the data

TeachMeSenpai data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually 🙂
The file containing your data can be found in the same folder `teachmesenpai.jar` is found in on your computer.

[↑ Back to top](#table-of-contents)

---

### Editing the data file

<div markdown="block" class="alert alert-danger">:exclamation: **Warning:** Do not attempt to edit the data file unless you are an advanced user and understand some basic computing terminlogies.
If your changes to the data file makes its format invalid, TeachMeSenpai will discard all data and start with an empty data file the next time you launch it.
If you'd still like to edit the data file directly, please proceed to [Appendix: Editing the data file](#appendix-editing-the-data-file).
</div>

[↑ Back to top](#table-of-contents)

---

## Glossary

| Term                           | Details                                                                                                         | Examples                                                                                                                                                                                                                                    |
|--------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <a id="glossary-alphanum"></a>Alphanumeric                   | Characters that are either a numbers or letters.                                                                | 1, 2, 3, a, b, c are alphanumeric                                                                                                                                                                                                           |
| Command                        | Instructions for TeachMeSenpai to execute/carry out.                                                            | [Features](#features) are commands TeachMeSenpai can execute. For example, [`add`](#adding-a-student-add) is a command!                                                                                                                     |
| <a id="glossary-cli"></a>Command Line Interface (CLI)   | A Command Line Interface allows users to use text as command to be executed by an application.                  | The [command line](#command-line) acts as a CLI in this case.                                                                                                                                                                |
| <a id="glossary-gui"></a>Graphical User Interface (GUI) | A Graphical User Interface allows users to interace with an application through graphics like icons, menus etc. | TeachMeSenpai acts as a GUI in this case.                                                                                                                                                                                                   |
| Index                          | The number displayed next to the student entry's name after `list` is used.                                     | 1. Alex Yeoh, 1 would be the index for Alex Yeoh.                                                                                                                                                                                           |
| Integer                        | An integer is a whole number.                                                                                   | 1, 10, 100 are integers. 1.5, 0.00 are not integers!                                                                                                                                                                                        |
| Keyword                        | The word you want to [find/filter](#findfilter-students-findfilter) by.                                         | A keyword for a student named "Shao Hong" could be "Shao Hong"                                                                                                                                                                              |
| <a id="glossary-os"></a>Operating System               | Also known as (OS) of the computer, managers the software and hardware on the computer.                         | Windows 10, Windows 11, Linux, Mac.                                                                                                                                                                                                         |
| Parameter                      | Parameters are information TeachMeSenpai requires you to fill in.                                               | `NAME`, `PHONE` are parameters you have to fill in. Relevant information to fill in could be Shaun and 999 respectively. <br/> You may refer to the [Parameter descriptions](#parameter-descriptions) for more specifications and examples. |
| Partial keyword                | Part of the word you want to [find/filter](#findfilter-students-findfilter) by.                                 | A partial keyword for a student named "Shao Hong" could be "Sh", or "on" etc.                                                                                                                                                               |
| <a id="glossary-prefix"></a>Prefix                         | Indicators for commands. Always ends with a slash `/` and comes before a parameter.                             | `n/` is the prefix for parameter `NAME` which could be a name like Shaun. <br/> You may refer to [Prefix Summary](#prefix-summary) to see all the prefixes used in TeachMeSenpai.                                                           |
| Remark                         | Notes or things to take note of for a student.                                                                  | "Not good in Japanese", "Has not submitted MC" etc.                                                                                                                                                                                         |

[↑ Back to top](#table-of-contents)

---

## Command summary

| Action | Format, Examples                                                                                                                                                                                                                                    |
|--------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add]    | `add n/NAME [a/ADDRESS] [p/PHONE] [e/EMAIL] [edu/EDUCATION] [tele/TELEGRAM] [r/REMARK] [t/TAG]... [m/MODULE]...`<br>eg. `add n/Shaun a/123 NUS Street e/shaun123@gmail.com edu/Year 1 r/Good in Japanese t/active t/hardworking m/CS2103T m/CS2101` |
| [Clear]  | `clear`                                                                                                                                                                                                                                             |
| [Delete] | `delete INDEX [INDEX]...`<br>eg. `delete 3` or `delete 1 2 3 4`                                                                                                                                                                                     |
| [Edit]   | `edit INDEX [n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [edu/EDUCATION] [tele/TELEGRAM] [t/TAG]... [m/MODULE]...` <br/>eg. `edit 1 n/Wen Li edu/Year 2`                                                                                                |
| [Exit]   | `exit`                                                                                                                                                                                                                                              |
| [Filter]   | `filter [n/NAME]... [p/PHONE]... [e/EMAIL]... [a/ADDRESS]... [edu/EDUCATION]... [tele/TELEGRAM]... [r/REMARK]... [t/TAG]... [m/MODULE]...` <br/>eg. `filter a/tampines` <br/>                                                                                 |                                                                                                                                                                                           |
| [Find]   | `find [n/NAME]... [p/PHONE]... [e/EMAIL]... [a/ADDRESS]... [edu/EDUCATION]... [tele/TELEGRAM]... [r/REMARK]... [t/TAG]... [m/MODULE]...` <br/>eg. `find n/Sh` <br/>                                                                                 |                                                                                                                                                                                           |
| [Help]   | `help`                                                                                                                                                                                                                                              |
| [List]   | `list`                                                                                                                                                                                                                                              |
| [Redo]   | `redo`                                                                                                                                                                                                                                              |
| [Remark] | `remark INDEX [REMARK]` <br/>eg. `remark 2 Not good in Japanese`                                                                                                                                                                                    |
| [Undo]   | `undo`                                                                                                                                                                                                                                              |
| [Show]   | `show INDEX` <br/>eg. `show 1`                                                                                                                                                                                                                      |

[Add]: #adding-a-student-add
[Clear]: #clearing-all-entries-clear
[Delete]: #delete-a-student-delete
[Edit]: #editing-a-student-edit
[Exit]: #exiting-the-program-exit
[Filter]: #findfilter-students-findfilter
[Find]: #findfilter-students-findfilter
[Help]: #viewing-help-help
[List]: #listing-all-students-list
[Redo]: #redo-a-previous-undo-redo
[Remark]: #editing-remark-of-student-remark
[Undo]: #redo-a-previous-undo-redo
[Show]: #showing-a-students-remark-show

[↑ Back to top](#table-of-contents)

---

## Prefix summary

| [Prefix](#glossary-prefix) | Meaning                                 | Example                               |
|--------|-----------------------------------------|---------------------------------------|
| n/     | Name of student                         | `n/Shao Hong`                         |
| p/     | Phone number of student                 | `p/81234567`                          |
| e/     | Email of student                        | `e/e07123456@u.edu.sg`                |
| a/     | Address of student                      | `a/16 Bukit Timah Road, S156213`      |
| edu/   | Education level of student              | `edu/P6`                              |
| r/     | Remark for student                      | `r/Good in German`                    |
| t/     | Tag of student                          | `t/active` or `t/hardworking ...`     |
| m/     | Module that the student is being taught | `m/CS2101` or `m/CS2101 m/CS4243 ...` |
| tele/  | Telegram handle of the student          | `tele/@chuuchuu` or `tele/@sO_m4nY`   |


[↑ Back to top](#table-of-contents)

---

## Appendix: Editing the data file

The default data file contains the sample data from TeachMeSenpai. To edit the data, simply follow the format for each parameter you'd like to edit.

The code below is a direct quote from the default data file.

```
{
  "persons" : [ {
    "name" : "Alex Yeoh",
    "phone" : "87438807",
    "email" : "alexyeoh@example.com",
    "address" : "Blk 30 Geylang Street 29, #06-40",
    "remark" : "Hasn't submitted tutorial worksheet for Week 10",
    "education" : "P6",
    "telegram" : "@alexyeoh30",
    "modules" : [ "CS2103T" ],
    "tagged" : [ "CS2101" ]
  }, {
    "name" : "Bernice Yu",
    "phone" : "99272758",
    "email" : "berniceyu@example.com",
    "address" : "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
    "remark" : "Submitted an MC for tutorial on Week 8",
    "education" : "P1",
    "telegram" : "@berniceee30",
    "modules" : [ "CS2030" ],
    "tagged" : [ "colleagues", "friends" ]
  }, {
    "name" : "Charlotte Oliveiro",
    "phone" : "93210283",
    "email" : "charlotte@example.com",
    "address" : "Blk 11 Ang Mo Kio Street 74, #11-04",
    "remark" : "Remarks can take in multiple lines of input \nsee!",
    "education" : "Sec 1",
    "telegram" : "@charlotte_07",
    "modules" : [ "CS2040S", "CS2109" ],
    "tagged" : [ "neighbours" ]
  }, {
    "name" : "David Li",
    "phone" : "91031282",
    "email" : "lidavid@example.com",
    "address" : "Blk 436 Serangoon Gardens Street 26, #16-43",
    "remark" : "",
    "education" : "Sec 4",
    "telegram" : "@david_liiii",
    "modules" : [ "CS2107" ],
    "tagged" : [ "family" ]
  }, {
    "name" : "Irfan Ibrahim",
    "phone" : "92492021",
    "email" : "irfan@example.com",
    "address" : "Blk 47 Tampines Street 20, #17-35",
    "remark" : "",
    "education" : "P5",
    "telegram" : "@irfanlove",
    "modules" : [ "CS2040S", "CS2105" ],
    "tagged" : [ "classmates" ]
  }, {
    "name" : "Roy Balakrishnan",
    "phone" : "92624417",
    "email" : "royb@example.com",
    "address" : "Blk 45 Aljunied Street 85, #11-31",
    "remark" : "",
    "education" : "P3",
    "telegram" : "@royyy123",
    "modules" : [ "CS4243" ],
    "tagged" : [ "colleagues" ]
  } ]
}
```

`persons` is an array containing every student entry as an element. Each element is enclosed in curly braces `{}`

<div markdown="span" class="alert alert-danger">:exclamation: **Warning:** The values you input should adhere to the parameter requirements. Otherwise the app will display an empty student list.
</div>

The parameters in the braces indicate which parameter in the [Parameter descriptions](#parameter-descriptions) the input should adhere to.


- `name`: Represents the student's name. Cannot be empty. (NAME)
- `phone`: Represents the student's phone number. If the student entry lacks a phone value, omit the "phone" row. (PHONE)
- `email`: Represents the student's email. If the student entry lacks an email value, omit the "email" row. (EMAIL)
- `address`: Represents the student's address. If the student entry lacks an address value, omit the entire "address" row. (ADDRESS)
- `remark`: Represents the remark or notes for a student. If the student entry lacks a remark, input`""` or omit the "remark" row. (REMARK)
- `education`: Represents the education level of a student. If the student entry lacks an education value, omit the "education" row. (EDUCATION)
- `telegram`: Represents the telegram handle of a student. If the student entry lacks a telegram value, omit the "telegram" row. (TELEGRAM)
- `modules`: Represents an array of modules the student takes. If the student entry lacks module values, input `[]`. (MODULE)
- `tagged`: Represents an array of tags the student is labelled with. If the student entry lacks tags, input `[]`. (TAG)

After making the desired changes, simply save the data file, and re-launch TeachMeSenpai again.

[↑ Back to top](#table-of-contents)


