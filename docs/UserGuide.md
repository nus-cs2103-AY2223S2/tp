---
layout: page
title: User Guide
---

WIFE is always right. Our product Well Informed Fridge Environment &lt;WIFE/&gt; helps users to manage 
their food items in the fridge, and never question her. With this, one never have to worry about
optimizing storage and organization of food items in a refrigerator, thereby reducing waste and 
improving the efficiency of grocery shopping.

* Table of Contents <br/>
*coming soon...*

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `wife.jar` from [here]().

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    *WIP*

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Getting help: `help`

Dynamic helps functionality provides general help as well as command specific help that includes command formats and example usages.

Format: `help [COMMAND_NAME]` where `COMMAND_NAME` may be omitted to view general help

##### List of COMMAND_NAME
- Food Commands:
  * add
  * delete
  * delbytag
  * edit
  * find
  * inc
  * list
  * view

- Tag Commands:
  * tag
  * untag
  * createtag
  * deltag
  * listbytag
  * listtag

- General Commands:
  * clear
  * exit
  * help


Example 1:
`help` displays a general help message
```
Type 'help COMMAND' to see specific help for a command.
Commands Available: add, delete, edit, find, list, view, tag, clear, exit

For more information refer to the user guide: https://ay2223s2-cs2103t-t11-1.github.io/tp/UserGuide.html
```
Example 2:
`help add` displays the command format and example usages specific to the `add` command
```
Add food item - add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]...
Example Usage: add n/Broccoli u/STALK q/2 e/03-03-2033 t/VEGETABLES t/HEALTHY
```


### Adding a food item: `add`

Add food items into your fridge.

Format: `add`

Examples:
* `add Spinach`

Example: <br/>
`add Spinach` returns
```shell
  You have successfully added Spinach into your fridge.
```

### Listing all food items : `list`

Shows a list of all food item in WIFE.

Format: `list`

Example:

`list` returns
```shell
  1. Spinach
  2. Meiji Milk
  3. Awfully Chocolate
```

### Tagging a food item: `tag`

Tag the specified food item in your fridge with our pre-defined tags.

Pre-Defined Tags:
- Status - `USED`, `UNUSED`
- Categories - `MEAT`, `DAIRY`, `VEGETABLES`

Format: `tag <Index> /with <Tag Name>`
- Tag the food item of index `Index` with `Tag Name`
- Index refers to any number on the food item list and must be a positive number, i.e., 1, 2, 3, 4, …

Example: <br/>
`tag 1 /with VEGETABLES` returns
```markdown
Spinach {VEGETABLES}
```

### Untagging a food item: `untag`

Remove a tag from a specified food item in your fridge.

Format: `untag <index> n/<tag name>`
- Remove `Tag Name` from the food item with index `Index`
- Index refers to any number on the food item list and must be a positive number, i.e., 1, 2, 3, 4, …

Example:
`untag 1 n/vegetables` displays
```
Vegetables successfully untagged from Spinach
```

### Listing your tags: `listtag`

List all the tags that you have created.

Format: `listtag`

Example:
`listtag` displays
```
Here are your existing tags: 
Dairy
Meat
Vegetables
```

### Updating a food item : `update`

Update  food items in your fridge.

Format: `update <Old Item> /to <New Item>`

* `Old Item`must be an item currently in the fridge

Example: <br/>
`update Meiji Milk /to Meiji Chocolate Milk` returns
```markdown
You have successfully updated Meiji Milk to Meiji Chocolate Milk
```

### Deleting a food item : `delete`

Deletes the specified food item from WIFE.

Format: `delete INDEX`

* Deletes the food item at the specified `INDEX`.
* The index refers to the index number shown in the displayed food item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

`delete 1` returns
```shell
  The below item has been deleted for you:
  Meiji Milk
```

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

*placeholder*

### Editing the data file

Wife data are saved as a JSON file `file name placeholder`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: *placeholder* <br/>
**A**: *placeholder*

--------------------------------------------------------------------------------------------------------------------

## Command summary

*coming soon...*
