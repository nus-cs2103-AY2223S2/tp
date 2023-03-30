---
layout: page
title: User Guide
---

<style>
   img.center {
      display: block;
      margin-left: auto;
      margin-right: auto;
   }
</style>


<div markdown="block" class="alert alert-info">
:information_source: **Preface:**

* This guide is on the **usage** of Team Builder. 
* For a guide on **developing** Team Builder with us, see our [developer guide](DeveloperGuide.html).
* We are **still working** on making our user guide as user friendly as possible. You can help us by providing feedback [here](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/new)
</div>

**Team Builder (TB)** is a desktop app for students to manage their contacts and build a multidisciplinary team based on soft skills and technical skills.

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TB can help you build your team through your contacts much faster than traditional GUI apps.

## About this guide <!-- omit in toc -->

<div markdown = "span" class="alert alert-primary">
:bulb: <a id="help-command" style="color:black"> **Tip:** </a>
Access this guide quickly through the `help` command or by clicking the **help** menu icon.
<br/>
<img src="images/helpMessage.png" alt="help message" width="75%" class="center"/>
</div>

Welcome to Team Builder, we hope that this product will allow you 
to manage your contacts to build fantastic teams for your events/competitions.

This guide is designed to be followed narratively from top to bottom. If you are looking for a specific command, you can view the [table of commands here](#full-table-of-commands).

<div markdown="block" class="alert alert-info">
:information_source: **What do the symbols and formatting mean?:**

* :information_source: - are important specific information to note

* :bulb: - are usage tips/shortcuts

* :exclamation: - are some warnings about certain actions

* `keyword` - are command/special character to be typed.

* `UPPER_CASE` - are the parameters to be supplied by the user.<br/>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* `[]` - are optional parameters that can be used or not used at all.<br/>
  e.g. `[e/optional]` can be used like ` ` (i.e. not used) or `e/lmao@lmao.com` 

* `[]...` - are parameters that can be used multiple times including zero times.<br/>
  e.g. `[t/multi]...` can be used like ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

</div>

**LABLED WINDOW IMAGE**

### This guide is split into 6 sections: <!-- omit in toc -->

1. [Full Table of Commands](#full-table-of-commands)
2. [Quick Start](#quick-start)
3. [Managing Contacts](#managing-contacts)
4. [Making a Team](#making-a-team)
5. [Extra Team Builder Features](#extra-team-builder-features)
6. [FAQ](#faq)

These 6 sections are the way in which we believe Team Builder can be used.

Below is the full table of contents, in case you are looking for something very specific.

<div markdown="block" class="alert alert-info">
:information_source: **Full Table of Content:**

- [Full Table of Commands](#full-table-of-commands)
- [Quick start](#quick-start)
- [Managing Contacts](#managing-contacts)
  - [Adding a new contact : `add`](#adding-a-new-contact--add)
  - [Listing all contacts : `list`](#listing-all-contacts--list)
  - [Updating a contact : `edit`](#updating-a-contact--edit)
  - [Deleting a contact : `delete`](#deleting-a-contact--delete)
- [Making a Team](#making-a-team)
  - [Create team: `create`](#create-team-todo)
  - [Finding the right teammate I : `find`](#finding-the-right-teammate-i--find)
  - [Finding the right teammate II : `sort`](#finding-the-right-teammate-ii--sort)
  - [Add a person to a team : `edit`](#add-a-person-to-a-team--todo)
  - [List all teammates in a team : `show`](#list-all-teammates-in-a-team--todo)
  - [Remove a team : `remove`](#remove-a-team--remove)
- [Extra Team Builder Features](#extra-team-builder-features)
  - [Clearing all entries : `clear`](#clearing-all-entries--clear)
  - [Undoing a command : `undo`](#undoing-a-command--undo)
  - [Redoing an undo command : `redo`](#redoing-an-undo-command--redo)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
- [FAQ](#faq)

</div>

--------------------------------------------------------------------------------------------------------------------

## Full Table of Commands

Action |          Type          |Format, Examples
:--------:|:----------------------:|:--------:
[**Add**](#adding-a-new-contact--add) |       Modifying        |`add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...` <br> e.g., `add n/James Ho p/22224444`
[**Clear**](#clearing-all-entries--clear) |       Modifying        |`clear`
[**Delete**](#deleting-a-contact--delete) |       Modifying        |`delete INDEX`<br> e.g., `delete 3`
[**Edit**](#updating-a-contact--edit) |       Modifying        |`edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
[**Find**](#finding-the-right-teammate-i--find) |        Listing         | `find NAME/MAJOR/TAG [MORE_KEYWORDS]`<br> e.g., `find James Jake`
[**Sort**](#finding-the-right-teammate-ii--sort) |        Listing         |`sort ORDER SORT_BY`<br> e.g., `sort desc tcount`
[**Create**](#Creating-a-team--create) |       Modifying        |`create tn/TEAM_NAME td/TEAM_DESCRIPTION [t/SKILLTAG]` <br> e.g., `create tn/TeamA td/2103T_tP t/Java`
[**Show**](#listing-all-contacts-within-a-team--show) |        Listing         |`show TEAM_NAME` <br> e.g., `show TeamA`
[**List**](#listing-all-contacts--list) |        Listing         |`list`
[**Remove**](#remove-a-team--remove) |       Modifying        |`remove`
[**Undo**](#undoing-a-command--undo) | Special<br/> Modifying | `undo`
[**Redo**](#redoing-an-undo-command--redo) |       Modifying        |`redo`
[**Help**](#help-command) |         Window         |`help`

--------------------------------------------------------------------------------------------------------------------

## Quick start

Prerequisite:

1. Downloaded [Java JDK 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) for your operating system.

Team Builder Installation:

1. Open a command terminal

2. Ensure you have **Java 11** or above installed in your Computer by typing `java --version` in the command terminal.
Information on the Java version installed should appear in the command terminal. Otherwise, [click here to troubleshoot](https://www.java.com/download/help/troubleshoot_java.html#:~:text=Temporarily%20turn%20off%20firewall%20or,successfully%20completed%20the%20Java%20install.).

3. Download the latest **teambuilder.jar** from [here](https://github.com/AY2223S2-CS2103T-T17-1/tp/releases).

4. Copy the file to the folder you want to use as the _home folder_ for your TeamBuilder.

5. In the command terminal, `cd` into the folder you put the jar file in, and use the `java -jar teambuilder.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/computer science` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

7. Refer to the [Full Table of Commands](UserGuide.html#full-table-of-commands) above for details of each command.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Managing Contacts

This section convers on how to manage potential teammates in your Team Builder.

This includes:
1. Adding new potential teammates
2. Updating existing teammates' information and tags
3. Deleting contacts that you no longer want to be teammates with or have lost contact.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a new contact : `add`

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [m/MAJOR] [t/TAG]...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0). Tags are especially useful for keeping track of skills and traits!
A contact must have a name, other features are optional.
</div>

<img class="center" alt="**John Pic**" src="images/JohnContactCardFront.png" width="60%" />

You have exchanged contacts with a person named **John** at a Computer Science Event.
He seems like a nice guy that you might want to do projects with. You note that he is a business major with **great presentations skills**.

Let's add his contacts to Team Builder as a new potential teammate for future events and projects!

**What to type**:

`add n/John p/98765432 m/Business t/Good Presentor`

**Result**: **Image**


### Listing all contacts : `list`

Format: `list`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
`list` is automatically called after every command except `sort` in the Graphical App. Hence, you don't need to keep typing `list` after each command!
</div>

Now that we have added **John**, we should be able to see him in the Team Builder.

**What you can type**:

`list`, `list 123`, `list ada adw v zklw`, etc.

These are all acceptable as anything after `list` is discarded.


### Updating a contact : `edit`

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MAJOR] [t/TAG]…​`

<img class="center" alt="**BACK OF JOHN'S CARD**" src="images/JohnContactCardBack.jpg" width="60%" />

It seems that we forgot to add some information about **John**.

We missed out the back of his card that has his **email**.

Let's use the `edit` command to update his information.

**Image of John in Team Builder, circled index** We see **John** is index 1 in our display.

**What to type**:

`edit 1 e/john@example.com`

**Result**:

**Screen Cap**

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the edit command:**<br>

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
</div>

### Deleting a contact : `delete`

Format: `delete INDEX`

You hear from your friends that **John** is actually a **snake** :snake: and we do not want a snake as a teammate.

Let's delete him from our Team Builder.

Again, we see **John** is index 1 in our display.

**What to type**:

`delete 1 `

**Result**:

**deletus maximus**


<div style="page-break-after: always;"></div>

## Making a Team

This section converse on how to make a team in your Team Builder.

This includes:
1. Creating a team
2. Finding the right teammate using `find` and `sort`
3. Adding a person to a team
4. Listing all teammates in a team
5. Remove a team

### Create team: `create`

Format: `create tn/TEAMNAME td/TEAMDESC [t/TAG]...`

You have decided to participate in NUS Hack&Roll hackathon. Let's create a team! Since your idea involves creating 
a web application, you will need someone with technical experience in React and AWS. You would also prefer to have a UI 
person to make your web app presentable. As this hackathon involves a tight timeline, proper project management skills 
are required too.

**What to type**:

`create tn/NUSMeets td/A multidisciplinary team for upcoming HacknRoll in mid Jan. t/JavaScript t/React t/AWS 
t/Project Management t/UI Design`

**Result**:

<img class="center" alt="result for 'create team'" src="images/CreateTeam.png" width="50%" />

### Finding the right teammate I : `find`

<a id="find-command" style="color:black"> Format: `find NAME/MAJOR/TAG [MORE_KEYWORDS]...` </a>

You recall that two of your tutorial classmate named Alex and David are good at UI Design and you want to invite them
to your team, but you have forgotten their full name and contact numbers.

**What to type**:

`find alex david`

**Result**:

<img class="center" alt="result for 'find alex david'" src="images/findAlexDavidResult.png" width="50%" />

You still feel your team needs more people, and you decide to find someone who majored in computer science.

**What to type**:

`find computer science`

**Result**:

<img class="center" alt="result for 'find computer science'" src="images/FindComputerScience.png" width="50%" />

Now your team is almost formed, but you still need a person who mastered JavaScript.

**What to type**:

`find javascript`

**Result**:

<img class="center" alt="result for 'find javascript'" src="images/FindJavascript.png" width="50%" />

<div markdown="block" class="alert alert-info">
:information_source: **Specificity of `find`**:

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`


</div>

### Finding the right teammate II : `sort`

<a id="sort-command" style="color:black"> Format: `sort ORDER SORT_BY` </a>

<div markdown="block" class="alert alert-info">

:information_source: **Specificity of `sort`**:

* The order and sort by are case-insensitive. e.g `tcount` will match `tCount`
* The order can only be either ascending (`asc`) or descending (`desc`)
* The following sort by are implemented:
  * `tcount` Sorts by person's tag count

</div>


### Add a person to a team : `edit`

Format: `edit INDEX [T/TEAMTAG]...`

You recall participating in a Hackathon previously with Alex Yeoh and knows he is familiar with React. You decide to 
reach out to him to form your team for Hack&Roll. 

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You can only add a person to the team if the team has been created beforehand!
</div>

**What to type**:

`edit 1 T/NUSMeets`

**Result**:

<img class="center" alt="result for 'edit 1'" src="images/FindReact.png" width="50%" />

### List all teammates in a team : `show`

Format: `show TEAM_NAME`

The Hack&Roll announced that there is a limit on team size. You want to list all people who are currently added to your
NUSMeets team.

**What to type**:

`show NUSMeets`

**Result**:

<img class="center" alt="result for 'edit 1'" src="images/ShowNUSMeets.png" width="50%" />


### Remove a team : `remove`

Format: `remove TEAMNAME`

Oh no, you realised that you have a number of deadlines during the period of time Hack&Roll would run. You decide to 
not participate in it anymore. 

**What to type**:

`remove NUSMeets`

**Result**:

NUSMeets will be removed from the team panel.


## Extra Team Builder Features

This section covers some quality of life features for Team Builder.

This includes:
1. Clearing all entries
2. Undoing a command
3. Redoing an undo command
4. Listing all Tags
5. Exiting the program
6. Saving data
7. Editing the data file

### Clearing all entries : `clear`

Format: `clear`

Clears all entries from the Team Builder.

**What you can type**:

`clear`, `clear 123`, `clear ada adw v zklw`, etc.

These are all acceptable as anything after `clear` is discarded.

### Undoing a command : `undo`

Format: `undo`

Oops! It looks like we accidentally used the `clear` command to remove all our contacts.

Let's use the `undo` command to revert our Team Builder back to before we typed the `clear` command.

**What you can type**:

`undo`, `undo 123`, `undo ada adw v zklw`, etc.

These are all acceptable as anything after `undo` is discarded.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You can only `undo` up to 10 times in a row! After the 10th `undo`, no more `undo` is possible.
</div>

### Redoing an undo command : `redo`

Format: `redo`

Perhaps we want to `clear` the example contacts after all.

Let's use the `redo` command to revert our Team Builder back to before we typed the `undo` command.

**What you can type**:

`redo`, `redo 123`, `redo ada adw v zklw`, etc.

These are all acceptable as anything after `redo` is discarded.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Typing any [modifying command](#full-table-of-commands) (other than an undo) after an `undo` makes it impossible to `redo` that `undo` command!
</div>

### Exiting the program : `exit`

Format: `exit`

Exits the program.


### Saving the data

TeamBuilder data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TeamBuilder data are saved as a JSON file `[JAR file location]/data/teambuilder.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TeamBuilder will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TeamBuilder home folder.

--------------------------------------------------------------------------------------------------------------------


