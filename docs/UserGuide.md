---
layout: page
title: User Guide
---
# Welcome to PetPal!
![Logo](images/UI/logo.png)

PetPal is your **furry and dependable assistant** who **reminds you of deadlines** and **consolidates crucial information** like pet names, tags dietary needs all in an **aesthetically pleasing** GUI! You can now focus on spend quality time with your furry friends without having to worry about things accidentally slipping your mind.

**PetPal is optimized for a Pet DayCare owner to use via the GUI (Graphical user interface)** while still having the benefits of a Command Lind Interface (CLI). PetPal can help get your pet management tasks done without being tech-savvy and good at typing in the CLI.

This user guide will help you get started and understand how PetPal can **seamlessly streamline your pet caring duties**.

<div style="page-break-after: always;"></div>

# Table of Contents
  {:toc}

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `PetPal.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your PetPal.

4. Double-click the file to start the app. The GUI similar to below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/UI/Ui.png)

5. Type the command in the command line and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all pets currently stored.

    * **`add`**`o/Petricia n/Whiskers p/98746333 e/petricia@petpal.com a/311 Beach Road 2023-03-27 21:09:09 d/Feed dog - 2023-03-27 21:09:09 t/MaineCoon`
      : Adds a `Pet` named `Whiskers` to your PetPal.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.
7. Refer to the [Command Summary](#command-summary) for a quick summary of all commands.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Getting Familiar With Your User Interface

![Ui Breakdown](images/UI/UiHighlighted.png)

1. **Pet Cards**: Contain all the information of a pet. (Highlighted in red)
2. **Command Line**: Type in your commands here. (Highlighted in yellow)
4. **Result Display**: The result of your command execution appears here. (Highlighted in blue)
5. **Help Button**: Provides the URL of this user guide.

[Return to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Legend
* Text in [blue](#legend) are hyperlinks that direct you to the relevant section of the page or to other websites
* Text in **bold** are used to emphasize important details to look out for or to distinguish headers from the rest of the text
* Text in `code snippets such as this` are used to show inputs and their format

<div markdown="block" class="alert alert-block alert-info">

* :white_check_mark: **Input Shortcut:**
Shortened forms of commands which can help increase your efficiency in using PetPal
</div>

<div markdown="block" class="alert alert-block alert-success">

* :bulb: **Note:**
Information that might be useful to know to enhance your PetPal experience, might not be compulsory to know
</div>

<div markdown="block" class="alert alert-block alert-danger">

* :heavy_exclamation_mark: **Caution:**
Important information to note which might negatively impact your experience in using PetPal
</div>

<div markdown="block" class="alert alert-warning">

* :information_source: **Information**

</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-warning">

**:information_source: Overview of commands:**<br>

* Words in `UPPER_CASE` are the information supplied by you.
  e.g `OWNER_NAME` is a parameter in `add o/NAME`, where you can input `add o/John Doe`.
  <br>

* Parameters in `[]` are optional.
  e.g `DEADLINE` is an optional parameter in `create o/OWNER_NAME [d/DEADLINE]`, where you can input `add on/John Doe d/d/Feed dog - 2023-03-27 21:09:09` or just `create n/John Doe`.
  <br>

* Items with `…`​ after them can be used multiple times.
  e.g `TAG…​` can be used as `Pomeranian`, `Pomeranian Vegetarian` etc.
  <br>

* `INDEX` represents the index of a specific pet in PetPal.
  e.g You can use the command `delete INDEX` as `delete 2`.
  <br>

* Parameters can be in **any order**.
  e.g You can input either `add o/OWNER_NAME p/HP_NUMBER` or ` create p/HP_NUMBER o/OWNER_NAME`.
  <br>

* Only the last occurrence of a parameter that is expected once will be taken **if you specify it more than once.**
  e.g Given `p/12341234 p/56785678`, only `p/56785678` will be taken.
  <br>

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`,`clear`, `redo` and `undo`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  <br>

* Names are case-sensitive. e.g. John Doe and john doe are treated as the different names.

 </div>

[Return to Table of Contents](#table-of-contents)

### General

#### Viewing help : `help`

Shows a message explaining how you can access our user guide.

Format: `help` or Press `F1`

![help](images/UG/help.png)

#### Listing your pets : `list`

Shows a list of all pets stored in your PetPal.

Format: `list`

![list](images/UG/list.png)

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `list` with `l` for convenience.

</div>

<div style="page-break-after: always;"></div>

### Add

#### Adding a pet's information : `add`
Format: `add o/OWNER_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS ts/TIME_STAMP [d/DEADLINE] [t/TAG...] `

Adds all the relevant information of a pet to your PetPal.
<br>

There are 3 key things that you should note:
1. All the parameters used in Add has their associated **prefixes**. Prefixes are convenient shorthands that allow you to easily identify
   which parameter a value belongs to. For example, in `add o/John`, the value `John` is associated with the
   parameter `OWNER_NAME` since the `o/` prefix is used before the value.
2. After the prefix please use a / to indicate the start of the value. For example, in `add o/John`, the value `John` is associated with the
   parameter `OWNER_NAME` since the `/` is used after the prefix `o/`.
3. There are **constraints** to the values that you can replace parameters with. The constraints differ based on
   the parameters. If you do not adhere to these constraints in your input, your input will be invalid and an error
   message will be shown in the Results Display when you type the input in and press <button>Enter</button>.

Figure 1 provides a summary of the parameters with their descriptions, prefixes and constraints.

| Parameter      | Description                                   | Prefix | Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | Required?  |
|----------------|-----------------------------------------------|--------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| `OWNER_NAME`   | The name of the owner                         | `o/`   | Names should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | COMPULSORY |
| `PET_NAME`     | The name of the pet                           | `n/`   | Names should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | COMPULSORY |
| `PHONE_NUMBER` | The owner's phone number                      | `p/`   | Phone numbers should only contain numbers, and it should be at least 3 digits long                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | COMPULSORY |
| `EMAIL`        | The owner's email                             | `e/`   | Emails should be of the format local-part@domain and adhere to the following constraints:<br/> 1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, ( + SPECIAL_CHARACTERS + ). The local-part may not start or end with any special "characters."<br/>2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.The domain name must:<br/>- end with a domain label at least 2 characters long<br/>- have each domain label start and end with alphanumeric characters<br/>- have each domain label consist of alphanumeric characters, separated only by hyphens, if any. | COMPULSORY |
| `ADDRESS`      | The owner's address                           | `a/`   | Cannot be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | COMPULSORY |
| `TIMESTAMP`    | The date and time when the pet start boarding | `ts/`  | Timestamps should be in the `YYYY-MM-DD HH:MM:SS` format.   eg. 2023-03-27 21:09:09                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | COMPULSORY | 
| `DEADLINE`     | A deadline attributed with the pet            | `d/`   | Timestamps given in the deadline should be in the `YYYY-MM-DD HH:MM:SS` format.   eg. 2023-03-27 21:09:09                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | OPTIONAL   |
| `TAG`          | A label that you can attach to a pet          | `t/`   | Tags should be alphanumeric                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | OPTIONAL   |


<p style="text-align: center;">Figure 1: Parameters with their descriptions, prefixes and constraints</p>


**Some things to note**
* If you have multiple tags attributed to one pet, simply repeat the field `t/TAG`.
* The `ts/TIMESTAMP` field accepts inputs in the `YYYY-MM-DD HH:MM:SS` format. Note that `YYYY` ranges from `2000` to `2099`.
* Does not allow you to create a pet with the same name and phone number as a current pet in PetPal.
* Allows you to create a pet with same name but different phone number or same number and different name as a current pet in PetPal.

![create](images/UG/add.png)

Example given: `Example: add o/Alice n/Doggo p/98765432 e/example@gmail.com a/311, Clementi Ave 2, #02-25 ts/2023-03-27 21:09:09 d/Feed dog - 2023-03-27 21:09:09 t/Dog t/Chihuahua`

<div markdown="block" class="alert alert-block alert-success">

:bulb: **Note:**
It is recommended to have at most  3 `TAG` per pet.

</div>

Other examples:
* `add o/Petricia n/Whiskers p/98746333 e/petricia@petpal.com a/311 Beach Road 2023-03-27 21:09:09 d/Feed cat - 2023-03-27 21:09:09 t/MaineCoon`
* `add o/Robert n/Fluffy p/98746333 e/rob@bmail.com a/622 Rose Road ts/2023-03-27 21:09:09 d/Feed rabbit - 2023-03-27 21:09:09 t/Vegetarian t/Rabbit`

#### Filtering out reminders that are due soon : `remind`

Get a filtered list of things you should do soon!.
<br>

**Constraints**
* The filtering of the list only checks if the deadline of a pet is within 3 days of current date.
* It does not sort out the list based on the deadline of the pet. (Future implementation)
* However, currently those reminders that are due within the day are highlighted in bright orange on the GUI as seen in the image given below.

Format: `remind`

![remind](images/UG/remind.png)

:bulb: **Note:**
The pets highlighted in bright orange are reminders that are due within a day!<br>

<div style="page-break-after: always;"></div>

### Retrieving

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `find` with `f` for convenience for all retrieving commands.

</div>

#### Finding your client by name : `find`

Find your pets whose name contain any of the given keywords.

**Constraints**
* The search is case-insensitive. e.g. `woofers` will match `Woofers`
* The order of the keywords does not matter. e.g. `Ardent Tyrant` will match `Tyrant Ardent`
* Only the name is searched. e.g. `find Alex` will not return owner names, addresses or emails with
* Partial words will be matched. e.g. `Jack` will match `Jackson`.
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Format: `find n/PET_NAME`

![findname](images/UserGuide/find.png)
Example given: `find n/Milo Charlie`

Other examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f n/NAME`

</div>

#### Finding your client by phone number : `find`

Find your clients whose phone number matches the input number.

You can use this command when:
1. You need to check if an unsaved phone number belongs to any one of your clients.
   <br>

**Constraints**
* Only full numbers will be matched e.g. `7654` will not match `80765432`
* All clients matching the number will be returned. e.g. All clients in the same household will be returned if they share the same home number.

Format: `find p/NUMBER`

![findphone](images/UserGuide/findphone.png)
Example given: `find p/98765432`

Other example:
* `find p/90333333` returns the client(s) with `90333333` stored as their number

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f p/NUMBER`

</div>

#### Finding your client by address : `find`

Find your clients whose addresses matches the input address.

You can use this command when:
1. You are around the area and you want to meet up with clients near you.
   <br>

**Constraints**
* The search is case-insensitive. e.g `serangoon` will match `Serangoon`
* The order of the keywords does not matter. e.g. `Kio Mo Ang` will match `Ang Mo Kio`
* Only the address is searched. e.g. `find a/Kent` won’t return clients with the name 'Kent'
* Words can be matched only if the whole address is included. e.g. `Tamp` won’t match `Tampines`
* Clients with address matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Buona Clementi` will return `Buona Vista Drive`, `6 Clementi Ave`
* Address can contain numbers. Find results will return all clients with that address keyword.
  e.g. `find a/30` will return `Blk 30 Geylang Street 29`, `Blk 30 Lorong 3 Serangoon Gardens`

Format: `find a/ADDRESS`

![findaddress](images/UserGuide/findaddress.png)
Example given: `find a/hollywood`

Other example:
* `find a/Bedok` returns the client(s) with `Bedok` stored as their address

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f a/ADDRESS`

</div>

#### Finding your client by tag : `find`

You can find your clients whose tag matches the input tag.

You can use this command when:
1. You need to consolidate `SECURED` clients to share new perks your company has.
2. You need to consolidate `POTENTIAL` clients so that you can make a targeted effort to secure these clients.
   <br>

**Constraints**
* TAG can only be either `SECURED` or `POTENTIAL`
* If multiple tags are given, only the last one is used.

Format: `find t/TAG`

![findtag](images/findTag.png)
Example given: `find t/potential`

Examples:
* `find t/SECURED` displays the list of all `SECURED` clients.

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f t/TAG`

</div>

#### Opening PDF file of your client : `file`

This will open a client's assigned PDF file in your PDF file viewer.

You can use this command when:
1. You forgot the details of a client's financial plans during the meeting. Simply pull up the PDF that you stored for this client.
   <br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* Moving or renaming the PDF file in your local disk will cause the command to not work, which will require you to reassign a file path to the client again.

Format: `file INDEX`

![file](images/UserGuide/file.png)
Example given: `file 2`

Examples:
* `file 2`

#### Get upcoming meetings : `Upcoming Meetings`

Returns a list of clients who has a scheduled meeting in the next 7 days.

You can use this command when:
1. You need to check which upcoming client meetings you need to prepare for.
   <br>

**Constraints**
* This uses your device's present local time as reference.
* As long as a client has a meeting in the next 7 days, it will be shown.
* Upcoming Meetings does not auto refresh, so meetings that pass after you have opened
  `Upcoming Meetings` will only be refreshed when a new window is opened.

Format: Menu bar on the top of the application or press `F2`.

![Meetings](images/upcomingMeeting.png)

<div markdown="block" class="alert alert-block alert-danger">

:heavy_exclamation_mark: **Caution:**
As this command syncs with your device's system clock, please make sure the current date, time, and timezone are correct before using this command.

</div>

<div style="page-break-after: always;"></div>

### Updating

#### Updating your client's information : `update`

Updates the information of a client stored in your FABook.

You can use this command when:
1. A client changes address, phone number, email
2. You want to edit the description of a client.
3. A client's net worth changes.
4. You have secured a `POTENTIAL` client.
   <br>

**Constraints**
* Edits the client with the provided index.
* `INDEX` is the index of the client in the currently displayed list.<br>
* You must provide **at least one** of the optional fields .
* You can also update the description of a client through the [`description` command](#updating-your-clients-description--description).
* You must update the meeting time of a client through the [`meeting` command](#updating-meetings--meeting), [`deletemeeting` command](#delete-meetings--deletemeeting) and [`sync` commands](#remove-past-meetings--sync).
* Does not allow you to update a person to have the same name and phone number as a current person in the FABook.
* Allows you to update a person to have same name but different phone number or same number and different name as a current person in the FABook.
* Person profiles do not refresh when person is updated, they are only updated when we re-click the person card

Format: `update INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [t/TAG]`

![update](images/UserGuide/update.png)
Example given: `update 1 p/12345678`

<div markdown="block" class="alert alert-block alert-success">

:bulb: **Note:**
Only parameters you provide will be changed.

</div>

Other example:
* `update 2 n/John Doe p/91234567 a/21 Lower Kent Ridge Rd` Updates the second listed client's
  name, phone number and address to be `John Doe`, `91234567` and `21 Lower Kent Ridge Rd` respectively.

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `update` with `u` for convenience.<br>
Format: `u INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [t/TAG]`

</div>

### Deletion

#### Deleting a client : `delete`

Deletes the specified client from your FABook.

You can use this command when:
1. You drop a client.
   <br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* Deletes the client with the specified index in your FABook.

Format: `delete INDEX`

![delete](images/UG/delete.png)

Examples:
* `delete 2` deletes the second pet in the currently displayed pet list in the PetPal.



#### Delete meetings : `deletemeeting`

Deletes a meeting from your client in your FABook.

You can use this command when:
1. Your client or you cancels a meeting.
   <br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* `MEETINGTIME` should be in the format `DD-MM-YYYY-HH:MM`.
* If the given meeting time is not on the list, the client's meetings remain unchanged.

Format: `deletemeeting INDEX mt/MEETINGTIME`

Examples:
* `deletemeeting 2 mt/09-10-2023-23:50` deletes the meeting at 9 October 2023 23:50 from the second client in the displayed list,
  if such a meeting was scheduled.

#### Remove past meetings : `sync`

Removes every scheduled meeting time that has already passed.

You can use this command when:
1. You want an up to date list of meetings.
   <br>

**Constraints**
* This uses your device's present local time as reference. All meetings scheduled to be earlier than the present time will removed.
* Sync command does not refresh the meetings displayed on the person profile, you need to re-click on person card.

Format: `sync`

![sync](images/UserGuide/sync.png)

<div markdown="block" class="alert alert-block alert-danger">

:heavy_exclamation_mark: **Caution:**
As this command syncs with your device's system clock, please make sure the current date, time, and timezone are correct before using this command.
Please note that undo cannot undo this command!

</div>

#### Clearing all entries : `clear`

Clears all entries from your PetPal.json.

Format: `clear`

![clear](images/UserGuide/clear.png)

<div markdown="block" class="alert alert-block alert-warning">

:heavy_exclamation_mark: **Important**
If you run this command by accident, you can [undo](#undoing-a-previous-command--undo) the command to restore all previously cleared entries.

</div>

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `clear` with `cl` for convenience.

</div>

<div style="page-break-after: always;"></div>

### Command Flow
#### Undoing a previous command : `undo`

Undos your last command.

You can use this command when:
1. When you make a mistake editing the contact book.
   <br>

**Constraints**
* The command intended to be undone should be an undoable command.
* Undoable commands are: `create`, `delete`, `update`, `clear`, `description`, `meeting`, `deletemeeting` and `redo`
* Non-undoable commands are: `exit`, `find`, `help`, `list`, `file`, `sync` and `filepath`

Format: `undo`

![undo](images/UserGuide/undo.png)

<div markdown="block" class="alert alert-block alert-warning">

:heavy_exclamation_mark: **Important**
You can undo a [`redo` command](#redoing-a-previous-command--redo).

</div>

#### Redoing a previous command : `redo`

Redos your last undone command.

Format: `redo`

![redo](images/UserGuide/redo.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `exit` with `e` for convenience.

</div>

### Saving the data

Your FABook data are saved in the hard disk automatically after any command that changes the data. This means hassle free saving.

### Editing the data file

FABook data are saved as a text file `[JAR file location]/data/addressbook.json`. If you are an advanced user, you are welcome to update data directly by editing that data file.

<div markdown="block" class="alert alert-block alert-danger">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FABook will discard all data and start with an empty data file at the next run.

</div>

[Return to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**:   
1. Find the save folder on the current computer. The save files can be found in the `data` folder created by the app, it should include both `PetPal.json` and `archive.json`
2. Transfer the save file to your new computer.
3. Download the latest release of PetPal from [here](https://github.com/AY2223S2-CS2103T-T14-2/tp/releases) on your new computer.
4. Launch PetPal on your new computer and exit.
5. You should see a new `data` folder containing a new `PetPal.json` with a sample. Replace this file with the save file copied over from your old computer.
6. Launch FABook again. Your data should be there!

<div markdown="block" class="alert alert-block alert-success">

* :bulb: **Note:**
  If you wish to transfer only 1 of the files over, repeat the same steps, and replace the file you want to move, instead of the entire `data` folder
</div>

**Q**: How do I view the entire pet list after a `find` command?<br>
**A**: Run `list` or `l` to view the entire unfiltered list.

If you have additional questions that is not present in the User Guide, feel free to contact us via our email `contact@petpal.com`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Term                     | Definition                                                                                                                                                                                                                                               |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alphanumeric             | Refers to characters that are the combined set of the 26 alphabetic characters, a to Z, both lowercase and uppercase, and the 10 Arabic numerals, 0 to 9.                                                                                                |
| Command Line Interface   | A command-line interface (CLI) is a text-based user interface (UI) used to run programs, manage computer files and interact with the computer.                                                                                                           |
| dd-MM-yyyy-hh:mm         | Date format whereby `dd` refers to the 2 digit days, `MM` refers to the 2 digit months, `yyyy` refers to the 4 digits years, `hh` refers to the 2 digits hours, `mm` refers to the 2 digits minutes. They are each separated by a hyphen: `-` character. |
| Graphical User Interface | A graphical user interface (GUI) is an interface through which a user interacts with electronic devices such as computers and smartphones through the use of icons, menus and other visual indicators or representations (graphics).                     |
| Index                    | The number that corresponds to the position of the pet in the list. The index must be a numeral above 0.                                                                                                                                                 |
| Java                     | The programming language used for this application. Java is a general-purpose computer programming language designed to produce programs that will run on any computer system that has Java installed.                                                   |
| JAR                      | JAR stands for Java ARchive. FABook uses JAR to deliver its distribution. JAR is a file format based on the popular ZIP file format and is used for aggregating many files into one.                                                                     |
| JSON                     | JSON stands for JavaScript Object Notation. JSON is the format used to store your PetPal's data. JSON is a lightweight format for storing and transporting data.                                                                                         |

[Return to Table of Contents](#table-of-contents)


## Command summary

======

| Action          | Format, Examples                                                                                                                                                                                                                                                        | Shortcut       |
|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|
| **Help**        | `help`                                                                                                                                                                                                                                                                  | **PRESS** `F1` |
| **List**        | `list`                                                                                                                                                                                                                                                                  |                |
| **Add**         | `add o/OWNER_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS ts/TIME_STAMP [d/DEADLINE] [t/TAG...]`<br> e.g `Example: add o/Alice n/Doggo p/98765432 e/example@gmail.com a/311, Clementi Ave 2, #02-25 ts/2023-03-27 21:09:09 d/Feed dog - 2023-03-27 21:09:09 t/Dog t/Chihuahua` | c              |
| **Edit**        | `clear`                                                                                                                                                                                                                                                                 | cl             |
| **Delete**      | `delete 1`                                                                                                                                                                                                                                                              |                |
| **Remind**      | `remind`                                                                                                                                                                                                                                                                |                |
| **Find**        | `find n/PET_NAME` e.g `find Milo`                                                                                                                                                                                                                                       |                |
| **Undo**        | `undo`                                                                                                                                                                                                                                                                  |                |
| **Change Cost** |
| **Archive**     |
| **Clear**       |
| **Exit**        | `exit`                                                                                                                                                                                                                                                                  | e              |

[Return to Table of Contents](#table-of-contents)
