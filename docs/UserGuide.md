---
layout: page
title: User Guide
---

E-Lister is **a comprehensive desktop app**, **specially designed** for **insurance agents and other financial professionals** to **streamline the management of their customers' contact information**.  This powerful tool provides a simple and intuitive interface, allowing users to not only easily **add, edit, tag and filter out** for contact information via Graphical User Interface (GUI) but also **see the history of commands that the users keyed in and executed** so that they can keep **a record of their workflow progress**, hence improving productivity.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eLister.jar` from [here](https://github.com/AY2223S2-CS2103T-T17-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for E-Lister.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eLister.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/10000` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Some notices before going through this guide:**<br>

* Words in `UPPER_CASE` are the parameters for you to supply.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Words separated by `|` indicate that you should only pick one of the keywords.<br>
  e.g. `all|shown` indicates that you may enter either `all` or `shown`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. both the commands following `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` are acceptable with the same effect.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.


</div>

### Viewing help : `help`

Shows a help message detailing the various commands available in E-Lister.
A link can also bring the user to the online user guide for more detailed help.

Format: `help`

![help message](images/helpMessage.png)

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/INCOME [t/TAG] [t/MORE_TAGS]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
Income field can only have 5 decimals and smaller than 1 Trillion.
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/10000`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal i/10000`
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/400`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 i/200 t/criminal`

![add example](images/addExample.png)

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

![list](images/listExample.png)

*Why do we need this command?* This is mostly used to refresh the whole list of contacts storing in the app so far after you use **filter** command *(see later)*.
### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INCOME] [t/TAG] [t/MORE_TAGS]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Income field can only have 5 decimals and smaller than 1 Trillion.

Examples:
*  `edit 1 p/91234567 e/alex_clone@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `alex_clone@example.com` respectively.![edit example](images/editExample.png)
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial words will also be matched. e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Grub Ya` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find mart On` returns `Martin Henz`, `Ong Wai Kit`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `delete 6` will deletes the 6th person in the address book (which is Betsy Crowe in this example).![delete example](images/deleteExample.png)

### Adding a tag : `tag`

Adds a tag to any given person stored in the address book.

Format: `tag INDEX [tagName]`

* Adds `TAG` to the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `tag 2 banker` adds the tag “banker” to the 2nd person.
* `tag 1 billionaire` will add the tag "billionaire" to the 1st person (which is Alex in this example).![add tag example](images/tagExample.png)

### Deleting a tag : `delete_tag`

Deletes a tag on a person.

Format: `delete_tag INDEX TAG`

* Deletes `TAG` from the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The tag must exist.

Examples:
* `list` followed by `delete_tag 3 teacher` deletes the tag "teacher" from the 3rd person(only if there's a person with index 3 and carrying tag "teacher").
* `delete_tag 1 t/billionaire` will delete the tag "billionaire" from the 1st person (which is Alex in this example).![delete_tag example](images/deleteTagExample.png)

### Filter by fields : `filter`

Search for persons whose fields all match one or more keywords.

* Note that these keywords are case-sensitive, unlike `find`.

Format: `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INCOME] [t/TAG] [n/MORE_NAMES] ...`

* Displays the data of persons who:
  * For every field you entered one or more keywords for,
    * that person's field matches at least one of those keywords.

Examples:

* `filter t/bank` will list all persons with a tag containing "bank", such as `banker`, `bankrupt`, or `riverbanks`.
* `filter p/8765 e/hotmail` will list all persons with a phone number containing "8765", and an email address containing "hotmail".
* `filter e/org e/net` will list all persons with email addresses containing "org" **or** "net".

Important note:
* These keywords are matched as [regular expressions](https://regexone.com/).
  * This gives you more flexibility in finding matches, but means that using non-alphanumeric characters, like `$`, `.`, `-` or `*`, might display unexpected results.
  * If you need to non-alphanumeric characters anyway, write a backslash "\\" before each such character:
  * `filter a/\#459\-2965 \\ Navarro` will list all persons with an address containing "#459-2965 \ Navarro".

Advanced examples:

* `filter t/^bank$` will list all persons with exactly the tag `bank`,
and will not match `banker` or `bankrupt`.
* `filter i/.{7}` will list all persons with 7 or more digits in their income.
* `filter e/.*\.org$ n/(?<!len)rin(?!len) e/.*\.net$` will list all persons with the substring "rin" but not "len" in their name,
as well as an email that ends in ".org" or ".net".

### Freezing the display : `freeze`

Freezes the current <span style="color:green">selection</span> of persons displayed.

* By default, persons which no longer satisfy the conditions of the most recent `find`/`filter` are automatically dropped from the visible list.
  * This can happen e.g. if you `filtered` for a tag `x`, but then deleted `x` from a displayed Person.
  * `freeze` temporarily prevents persons from being hidden from view in such a scenario.

<span style="color:red">The visible details **within** each Person will still be updated,
if modified.</span>

Format: `freeze`

* Commands that reference indices / the list of persons being displayed will be subject to `freeze`,
and will act on the display as it appears to you.
* The effects of a `freeze` are withdrawn when a new `list`, `find`, or `filter` command is entered.

### Unfreezing the display : `unfreeze`

Unfreezes the current <span style="color:green">selection</span> of persons displayed. Any changes to the selection which were previously withheld
due to a `freeze` will now be applied.

Format: `unfreeze`

### Creating a shortcut : `shortcut`

Allows the user to create a shortcut for a command; this shortcut can be used in place of the command.

The shortcut will be saved and can still be used when the user exits E-Lister and re-opens it.

Format: `shortcut ORIGINAL_COMMAND SHORTCUT`

* The shortcut cannot be the same as any existing command or shortcut.
* The shortcut should only consist of alphanumeric characters.

Examples:
* `shortcut list lst` will allow the user to input `lst` in place of `list`.

### Mass operations : `mass`

Takes in a command typically applied to a single target index, and applies it to all _displayed_ persons.

Format: `mass COMMAND [ARGS_WITHOUT_INDEX]`

Examples:
* `mass tag Noticed` will tag all displayed persons with "Noticed".
* `mass edit p/7773354` will edit all displayed persons to have the phone "7773354".
* `mass delete` will delete all displayed persons.
  * Contrast with `clear`, which deletes all persons, whether displayed or not.

### Undoing a command : `undo`

Undo one or more of the most recent commands done.

Format: `undo [NUM]`

* Undoes `NUM` of the most recent commands, or the 1 most recent if `NUM` is not specified.
  * If specified, `NUM` must be a positive integer less than 2^31 ≈ 2 billion.
* Only undoes commands which affect data or the display. E.g.:
  * `edit`, `filter`, `freeze`, and `import` can be undone
  * `help` and `export` cannot be undone; `undo` will skip them for the next most recent command.
* Only undoes commands executed within the current session.
  * **If the program is closed and relaunched, commands performed before the relaunch will no longer be undoable,
  even if they remain visible on the Input Log.**

Examples:
* `undo 3` will undo the last 3 commands.

### Redoing a command : `redo`

Redo one or more of the most recent commands undone.

Format: `redo [NUM]`

* Redoes `NUM` of the most recent undone commands, or the 1 most recent if `NUM` is not specified.
  * If specified, `NUM` must be a positive integer less than 2^31 ≈ 2 billion.

* Only redoes commands undone within the current session.
    * **If the program is closed and relaunched, commands undone before the relaunch will no longer be redoable,
      even if they remain visible on the Input Log.**

Examples:
* `redo 3` will redo 3 commands.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Import data from CSV : `import`

Opens a file chooser to select a CSV file containing relevant data and merges with the existing data.

Format: `import [combine|reset]`

* If `combine` is entered, the existing data will be combined with the imported dataset.
* If `reset` is entered, the existing data will be removed and replaced with the imported dataset.
* If neither keyword is entered, the behaviour will be the same as `combine`.

**Note: Your CSV file must have the following headers in this order: `Name, Phone, Email, Address, Income, Tags`**

![CSV headers example](https://user-images.githubusercontent.com/55232476/230785869-dd530813-1753-4d59-b7bf-18fa12a20894.png)


### Export data to CSV : `export`

Opens a file chooser to select a directory where you can save the data to a CSV file.

If you wish to export to a new file, you may type its intended name in the **File Name** box and click **Save**. This will create the new CSV file with the exported data. Otherwise, you can select an existing CSV file to overwrite.

Format: `export [shown|all]`

* If `shown` is entered, only the current _selection_ of persons will be exported.
* If `all` is entered, all persons will be exported, including those which are not displayed due to filters.
* If neither keyword is entered, the behaviour will be the same as `shown`.

![export example](images/exportExample.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

E-Lister data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

E-Lister data is saved as a JSON file `[JAR file location]/data/elister.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, E-Lister will discard all data and start with an empty data file at the next run.
</div>

### Input Log
On the right side of the application, an Input Log section is available & displays successful commands you have previously entered.

*Why do we need this?* Keeping a record of all commands executed in a contacts managing app is important for several reasons.
- It provides a historical log of all actions taken by the insurance agent, which can be useful in case of any disputes or discrepancies that may arise in the future.
- It allows the agent to keep track of their progress and performance, providing a clear overview of where they are in the workflow at any given time. This can be especially helpful when managing a large number of customers and tasks simultaneously, as it can be easy to lose track of what has been done and what still needs to be done
- Finally, having a record of all commands executed in the app can help identify any issues or areas for improvement in the workflow, allowing the agent to refine their approach and optimize their performance over time.

![history display](images/historyDisplay.png)
### Applying Filters Display
Right above the History box, you can see another small container where all the filters that were used on your persons list will be displayed

*Why do we need this?* The reasoning is pretty the same as for History Display, imagine having to filter out your list of 1000 persons with 5 different eligibilities, don't you think it's great idea to have all the filters displayed so that you know what are people showing up in the app?

![applying filters display](images/filtersDisplay.png)
### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous E-Lister home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL i/INCOME a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague i/10000`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [i/INCOME] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Export** | `export`
**Filter** | `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INCOME] [t/TAG] [n/MORE_NAMES] ...`<br> e.g., `filter e/.*\.org$ n/rin e/.*\.net$`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Freeze** | `freeze`
**Unfreeze** | `unfreeze`
**List** | `list`
**Mass** | `mass COMMAND [ARGS_WITHOUT_INDEX]`<br> e.g., `mass tag Noticed`
**Undo** | `undo [NUM]`
**Redo** | `redo [NUM]`
**Help** | `help`
