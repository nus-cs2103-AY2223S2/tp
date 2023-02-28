---
layout: page
title: User Guide
---

FriendlyLink **streamlines volunteer and elderly management** for single administrators of small NPOs.
With its easy-to-use text-based interface and contact management features, say goodbye to manual
record-keeping and hello to a more efficient and organised way of managing the volunteers’ and elderly’s contact details.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `friendlyLink.jar` from [here](https://github.com/AY2223S2-CS2103T-W12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FriendlyLink.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar friendlylink.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add_elderly n/John Doe e/test@gmail.com  a/some address enr/S03123123A ag/76 r/low` : Adds an elderly named `John Doe` to FriendlyLink.

   * `delete_elderly enr/S03123123A` : Deletes the elderly with NRIC `S03123123A`.

   * `clear` : Deletes all data.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Feature List
* Add personnel
    * Add elderly
    * Add volunteer
* Delete personnel
    * Delete elderly
    * Delete volunteer
* Add pairing of elderly and volunteer
* Delete pairing of elderly and volunteer
* View pairing list of elderly and volunteer
* View personnel list
    * View volunteer list
    * View elderly list

<div markdown="block" class="alert alert-info">

[//]: # (person in charge of command format will change this)
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

[//]: # (Need to update help message pic)
![help message](images/helpMessage.png)

Format: `help`


### Adding an elderly: `add_elderly`

Adds an elderly to FriendlyLink.

Format: `add_elderly n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS enr/NRIC ag/AGE r/RISK [t/TAG]…​`
* Every elderly must have a unique `NRIC`
* The `RISK` can only takes 3 values: `LOW`, `MEDIUM` or `HIGH`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An elderly can have any number of tags (including 0)
</div>

Examples:
* `add_elderly n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 enr/S1234567C ag/68 r/HIGH`
* `add_elderly n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 enr/T1234567D ag/75 r/LOW t/lonely`

### Listing all elderly members : `list_elderly`

Shows a list of all elderly members in FriendlyLink.


### Adding a volunteer: `add_volunteer`

Adds a volunteer to the address book.

Format: `add_volunteer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS ag/AGE vnr/NRIC [t/TAG]…​`
* Every volunteer must have a unique `NRIC`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A volunteer can have any number of tags (including 0)
</div>

Examples:
* `add_volunteer n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 ag/23 vnr/S8457677H`
* `add_volunteer n/Betsy Crowe t/graduate e/betsycrowe@example.com a/Newgate Prison p/1234567 ag/27 vnr/S8959886I t/experienced`

### Listing all persons : `list_volunteer`

Shows a list of all volunteers in the application.



Format: `list_volunteer`

[//]: # (### Editing a person : `edit`)

[//]: # ()
[//]: # (Edits an existing person in the address book.)

[//]: # ()
[//]: # (Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`)

[//]: # ()
[//]: # (* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​)

[//]: # (* At least one of the optional fields must be provided.)

[//]: # (* Existing values will be updated to the input values.)

[//]: # (* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.)

[//]: # (* You can remove all the person’s tags by typing `t/` without)

[//]: # (    specifying any tags after it.)

[//]: # ()
[//]: # (Examples:)

[//]: # (*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.)

[//]: # (*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.)

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an elderly : `delete_elderly`

Deletes the specified elderly from FriendlyLink.

Format: `delete enr/NRIC`

* Deletes the elderly with the specific NRIC `NRIC`.

Examples:
* `delete_elderly enr/S8238657A` deletes the elderly with NRIC `S8238657A`


### Deleting a volunteer : `delete_volunteer`

Deletes the specified volunteer from the address book.

Format: `delete vnr/NRIC`

* Deletes the volunteer with NRIC `NRIC`

Example:
`delete_volunteer vnr/S8238658J` deletes the volunteer with NRIC `S8238658J`


### Pair volunteer and elderly: `add_pair`

Pairs up an existing elderly and volunteer.

This allows you to track which elderly members are assigned to which volunteers.

Format: `add_pair vnr/VOLUNTEER_NRIC enr/ELDERLY_NRIC`

* After pairing, the newly added pairs appear in the pair list in the window.
* Only elderly members and volunteers existing in FriendlyLink's data can be paired.
* Duplicate pairs will fail to be added to FriendlyLink.
* Alphabets in NRIC are case-insensitive.

Examples
`add_pair vnr/t0123423a enr/S2235243I` pairs up the volunteer with NRIC T0123423A with the elderly with NRIC S2235243I.
`add_pair vnr/S0773423a enr/s1135243A` pairs up the volunteer with NRIC S0773423A with the elderly with NRIC S1135243A.

### Unpair volunteer and elderly: `delete_pair`

Unpairs an elderly from its assigned volunteer

This deletes the pair while still keeping the information of the elderly member and volunteer.

Format `delete_pair i/<id>`

* After deleting, the pair is removed in the list of pairs in the window.
* Alphabets in NRIC are case-insensitive.

Examples
`delete_pair vnr/t0123423a enr/S2235243I` unpairs the volunteer with NRIC T0123423A and the elderly with NRIC S2235243I.
`delete_pair vnr/S0773423a enr/s1135243A` unpairs the volunteer with NRIC S0773423A and the elderly with NRIC S1135243A.

### Clearing all entries : `clear`

Clears all data from FriendlyLink.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FriendlyLink data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FriendlyLink data are saved as a JSON file `[JAR file location]/data/friendlylink.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FriendlyLink will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FriendlyLink home folder.

## Command summary

| Action               | Format, Examples                                                                                                                                                                                               |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**              | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`                                          |
| **Add Elderly**      | `add_elderly n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS enr/NRIC ag/AGE r/RISK [t/TAG]…​` <br> e.g.,`add_elderly n/John Doe p/98765432 e/johnd@example.com a/John St, blk 123, #01-01 enr/S1234567C ag/68 r/HIGH` |
| **Add Volunteer**    | `add_volunteer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS ag/AGE vnr/NRIC [t/TAG]…​` <br> e.g.,`add_volunteer n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 ag/23 vnr/S8457677H`     |
| **Clear**            | `clear`                                                                                                                                                                                                        |
| **Delete**           | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                            |
| **Delete Elderly**   | `delete_elderly enr/NRIC`<br> e.g., `delete_elderly vnr/S8238655C`                                                                                                                                             |
| **Delete Volunteer** | `delete_volunteer vnr/NRIC`<br> e.g., `delete_volunteer vnr/S8238658J`                                                                                                                                         |
| **Edit**             | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                    |
| **Find**             | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                     |
| **List**             | `list`                                                                                                                                                                                                         |
| **Help**             | `help`                                                                                                                                                                                                         |
