---
layout: page
title: User Guide
---

Fast Army Internal Lookup System (FAILS) is a **desktop app for managing the personal information of military personnel,
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (
GUI). If you can type fast, FAILS can get your contact management tasks done faster than traditional GUI apps.

<!-- omit from toc -->

## Table of Contents

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help--help)
  - [Adding a person: `add`](#adding-a-person--add)
  - [Listing all persons : `list`](#listing-all-persons--list)
  - [Copy information to clipboard : `copy`](#copy-information-to-clipboard--copy)
  - [Editing a person : `edit`](#editing-a-person--edit)
  - [Locating persons by name: `find`](#locating-persons-by-name--find)
  - [Locating persons using filters on fields: `filter`](#locating-persons-using-filters--filter)
  - [Deleting a person : `delete`](#deleting-a-person--delete)
  - [Clearing all entries : `clear`](#clearing-all-entries--clear)
  - [Undo last modification : `undo`](#undo-last-modification--undo)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
- [FAQ](#faq)
- [Command summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fails.jar` from [~~here~~](https://github.com/AY2223S2-CS2103T-W10-3/tp/releases) (_coming
   soon_).

1. Copy the file to the folder you want to use as the _home folder_ for your FAILS.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar fails.jar` command to
   run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

    - `add n/Jaden Ng p/91297723 e/jadend@gmail.com a/Smith street, block 13, #07-04 r/3SG u/alpha c/SIR pl/4` adds a new person `Jaden Ng` to FAILS with the following information:

     | Field        | Value                          |
     | ------------ | ------------------------------ |
     | name         | Jaden Ng                       |
     | phone number | 91297723                       |
     | email        | jadend@gmail.com               |
     | address      | Smith street, block 13, #07-04 |
     | rank         | 3SG                            |
     | unit         | alpha                          |
     | company      | SIR                            |
     | platoon      | 4                              |

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as <code>&nbsp;</code> (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<!-- ![help message](images/helpMessage.png) -->

Format: `help`

### Adding a person : `add`

Adds a person to FAILS.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/RANK [u/UNIT] [c/COMPANY] [pl/PLATOON] [t/TAG]…​ `

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** A person can have any number of tags (including 0)

</div>

Examples:

- `add n/Jaden Ng p/91297723 e/jadend@gmail.com a/Smith street, block 13, #07-04 r/3SG u/alpha c/SIR pl/4` adds a new person `Jaden Ng` to FAILS with the following information:

  | Field        | Value                          |
  | ------------ | ------------------------------ |
  | name         | Jaden Ng                       |
  | phone number | 91297723                       |
  | email        | jadend@gmail.com               |
  | address      | Smith street, block 13, #07-04 |
  | rank         | 3SG                            |
  | unit         | alpha                          |
  | company      | SIR                            |
  | platoon      | 4                              |

- `add n/Lawrence Tay t/platoon-leader e/lawrencetay@gmail.com a/124 Drummond Street p/91649723 r/3SG u/801 t/allergy-seafood` adds a new person `Lawrence Tay` to FAILS with the following information:

  | Field        | Value                 |
  | ------------ | --------------------- |
  | name         | Lawrence Tay          |
  | phone number | 91649723              |
  | email        | lawrencetay@gmail.com |
  | address      | 124 Drummond Street   |
  | rank         | 3SG                   |
  | unit         | 801                   |
  | tag          | platoon-leader        |
  | tag          | allergy-seafood       |

### Listing all persons : `list`

Shows a list of all persons in FAILS.

Format: `list`

### Copy information to clipboard : `copy`

Copies the information of a person to the user's clipboard.

Format: `copy INDEX`

- Copies the information of the person at the specified `INDEX` to the user's clipboard.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-info">

:information_source: **Note:** If FAILS fails to access your clipboard, the information will be displayed in the result display box instead. You can then highlight the text using your mouse and copy it to your clipboard by using `Ctrl + C` on Windows/Linux or `Command + C` on Mac.

</div>

Examples:

- Suppose you want to copy the second person, `Bernice Yu`, entering the command `copy 2` will copy all information into your clipboard.
![result for 'copy 2'](images/copy2Result.png)

After running the command, the following text is copied into your clipboard.
```
  Name: Bernice Yu
  Phone: 99272758
  Email: berniceyu@example.com
  Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18
  Rank: CPL
  Unit: Bravo
  Company: Charlie
  Platoon: Tiger 123
 ```

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can use `find`/`filter` to find a person first before using `copy`!

</div>

### Editing a person : `edit`

Edits an existing person in FAILS.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/RANK] [u/UNIT] [c/COMPANY] [pl/PLATOON] [t/TAG]…​`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person's tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567`
  and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name : `find`

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

### Locating persons using filters on fields: `filter`

Filters all persons to only show those whose fields contain all of the given keywords.

Format: `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/RANK] [u/UNIT] [c/COMPANY] [p/PLATOON] [t/TAG]…​`

- The search is case-insensitive. e.g `hanS` will match `Hans`
- The order of the keywords does not matter. e.g. `n/Hans r/3sg` will match `r/3sg n/Hans`
- Only persons matching all keywords will be returned (i.e. `AND` search).
- If multiple keywords for the same field are provided, only the last keyword for that field will be used (i.e. `r/3sg r/cpl` has the same result as `r/cpl`)
- Only part of a field is needed for a successful match e.g. `tanic g` will match `Botanic Gardens`
- At least one of the optional fields must be provided.

Examples:

- `filter n/Jo` returns `john`, `John Doe` and `joseph`
- `filter a/street r/sg ` returns `David Li`, `S Lee Chong Wei`<br>
  ![result for 'filter david lee'](images/filterDavidLeeResult.png)

### Deleting a person : `delete`

Deletes the specified person from FAILS.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd person in FAILS.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from FAILS.

Format: `clear`

### Undo last modification : `undo`

Undoes the last modification done to FAILS.

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:** Once FAILS is closed, the undo history will be lost.

</div>

Format: `undo`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FAILS data is saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

FAILS data is saved as a JSON file `[JAR file location]/data/fails.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** If your changes to the data file makes its format invalid, FAILS will discard all data and
start with an empty data file at the next run.

</div>

### Archiving data files `[coming in v2.0]`

(_coming soon_)

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous FAILS home folder.

---

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/RANK [u/UNIT] [c/COMPANY] [pl/PLATOON] [t/TAG]… ` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 r/RCT u/BRAVO c/207 pl/1 t/friend t/colleague`
**Clear** | `clear`
**Copy** | `copy INDEX`<br> e.g., `copy 1`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/RANK] [u/UNIT] [c/COMPANY] [pl/PLATOON] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
