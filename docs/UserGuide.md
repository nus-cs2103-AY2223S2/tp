---
layout: page
title: User Guide
---

RIZZipe is a **command-based recipe database** that was designed with **versatile tagging** and **searching** features in mind so you can always find the recipe you need! Make use of RIZZipe's many features to achieve your **culinary rizz**.

- Table of Contents
  {:toc}

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a Rizz-ipe: `add`

Come up with a new innovative recipe and want to store it for future reference,
and want to classify it by ingredients? Simply run the `add` command!

Format: `add n/RIZZIPE_NAME [i/INGREDIENT1, i/INGREDIENT2]...`

> A Rizz-ipe can have any number of Ingredients!
> :bulb: Tip: It is okay to not add a list of ingredients when you first upload
> your rizz-ipe (you can add them later!).

Examples:

- `add /nHoney Chicken Rice`
- `add n/Lemon-Infused Salmon Fillet, i/Lemon, i/Salmon`

### Listing all **_RIZZ_**ipes : `list`

Lists all **_RIZZ_**ipes in the current cook book.

Ever forget how many recipes you have in your storage? Want to view 'em all? Or
simply want to pick a recipe at random? Just run the `list` command.

**Example of usage**:

```shell
list
```

**Expected output**:

```shell
| 1. Aglio e Olio                      |
|    Feeds 3-4          Tags:  Italian |
|    ~ 15 mins                         |
|    Ingredients:                      |
|    .....                             |
|--------------------------------------|
| 2. Egg Fried Rice                    |
|    Feeds 3-4          Tags:  Asian   |
|    ~ 10 mins                         |
|    Ingredients:                      |
|    .....                             |
|--------------------------------------|
|             ....                     |
```

Lists all recipes that are in the storage, in the chronological order they were
added. Depending on the size of the window, may add multiple columns to display
more **_RIZZ_**ipes.

### Viewing **_RIZZ_**ipes: `view`

View a selected Rizz-pie based on specified index on current list. Current list may
change when using find or filter(coming soon).

Example of usage:\
`view 2`

Expected outcome:\
Rizz-pie descriptions are returned, which consist of its ingredients and steps
to cook it.

```
Curry Chicken d
Ingredients:
 Curry paste 8 oz. or 250 g,
 ......
 Directions:
1. Cut the chicken into pieces......
. ......
. ......
```

### Deleting a **_RIZZ_**ipes: `Delete`
No longer like a certain recipe? Simply delete it from the database by its index!

Format: `delete [index]`
- Deletes the dish at the specified `INDEX`.
- The index **must be a positive integer** 1, 2, 3, …​

Example of usage:

`delete 1`

Expected output:
```
Deleted dish: Egg Fried Rice.
```

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

```

```
