---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `listc` : Lists all customers.

   * `addc n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a customer named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all customers and orders.

   * `exit` : Exits the app.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listc`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a customer : `addc`

Adds a customer to the current list.

Format: `addc [ct/{ind/ent}] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Customers are 'Individuals' by default!
ind - Individuals
ent - Enterprise
</div>

Examples:
* `addc ct/ind n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addc ct/ent n/The Potato Farm e/thepotatofarm@example.com a/South street, block 983, #02-01 p/1234567`
* `addc n/Mary Jane p/93130151 e/maryjane@example.com a/Mary Lamb Street, block 23, #01-12`

### Listing all customers : `listc`

Shows a list of all customers.

Format: `listc [s/{name|points}] [f/{marked|ind|ent}]`

* Lists all customer with the specified sorting option.
* Be default, customers are sorted by name
* If `f/marked` is provided, then shows only bookmarked customers.
* If `f/ind` or `f/ent` is provided, then shows only individual or enterprise customers respectively.

Examples:
* `listc` lists all customers sorted by name
* `listc s/points` lists all customers sorted by points
* `listc f/marked` lists all bookmarked customers. 

### View a customer : `viewc`

Display a customer's information in the information panel.

Format: `viewc INDEX`

* Displays the customer's information at the specified `INDEX`.
* * The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can click on the customer in the table directly for the same effect!
</div>

Examples:
* `listc` and `viewc 2` opens `Bernice Yu` on the information panel on the right
  ![result for 'viewc 2'](images/viewcBerniceYuResult.png)


### Deleting a customer : `deletec`

Delete a customer from the list.

Format: `deletec INDEX`

* Deletes the customer at the specified `INDEX`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listc` followed by `deletec 2` deletes the 2nd person in the address book.
* `findc Betsy` followed by `deletec 1` deletes the 1st person in the results of the `findc` command.

### Marking a person: `markc`

Bookmarks a customer from the list of customers.

Format: `markc INDEX`

* Bookmarks the customer at the specified `INDEX`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listc` followed by `markc 2` bookmarks the 2nd person in the address book.
* `findc Betsy` followed by `markc 1` bookmarks the 1st person in the results of the `findc` command.

### Unmarking a person: `unmarkc`

Un-bookmarks a customer from the list of customers.

Format: `unmarkc INDEX`

* Un-bookmarks the customer at the specified `INDEX`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listc` followed by `unmarkc 2` un-bookmarks the 2nd person in the address book.
* `findc Betsy` followed by `unmarkc 1` bookmarks the 1st person in the results of the `findc` command.

### Setting reward points for a customer : `setpoints`

Sets a customer's reward points.

Format: `setpoints INDEX pt/POINTS`

* Sets the points of the customer at the specified `INDEX` to `POINTS`.
* Customers by default, have 0 points initially.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​
* The points refers to the reward points of the customer
* The points **must be a positive integer** 1, 2, 3, ​​…
* The points can only range from 0 to 999999
* Setting points will also set cumulative points to the same amount, if you wish to keep the current cumulative points,
use the `addpoints` command instead.

Examples:
* `listc` followed by `setpoints 2 pt/100` sets the 2nd customer points as 100.
* `findc Betsy` followed by `setpoints 1 pt/300` sets the 1st customer points as 300 in the results of the `findc` command.

### Adding points for a customer / Removing points from a customer : `addpoints`

Edits a customer's reward points by adding or removing from it.

Format: `addpoints INDEX pt/[+/-]POINTS`

* Add or subtract the points of the customer at the specified `INDEX` to `POINTS`.
* If the points subtracted is greater than what the user has, the command will not be executed
* If + or - is not explictly stated, the command will default to an addition of `POINTS`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​
* The +/- refers to whether you wish to add or subtract from the reward points of the customer, + to add, - to subtract
* The points refers to how much you wish to add or subtract the reward points of the customer
* The points **must be a positive integer** 1, 2, 3, ​​…
* Addition will also result in an addition of cumulative points, subtraction will not change cumulative points.

Examples:
* `listc` followed by `addpoints 2 pt/100` adds 100 reward points to the 2nd customer.
* `findc Betsy` followed by `addpoints 1 pt/-300` deducts 300 reward points from
the 1st customer in the results of the `findc` command.

### Setting tiers for your reward system : `settier`

Sets a tier for your reward system with a point threshold. Customers above the point threshold are automatically in this tier, and the tiers below it.

Format: `settier TIER_NUM POINT_THRESHOLD`

* There are 3 tiers by default, tiers 1, 2 and 3 will be initially set to 999997 999998 and 999999 respectively.
* The points threshold of tiers must go in the following order, 1 < 2 < 3
* The tier_num refers to the particular tier that you want to set the point_threshold
* The tier_num **must be 1, 2 or 3**
* The point_threshold refers to how much points you want a customer to accmulate before he is in the tier, tier_num
* The points **must be a positive integer** 1, 2, 3, ​​…

Examples:
* `settier 1 500` Sets tier 1 with a point threshold of 500, any customer above 500 points will automatically be
in tier 1.
* `settier 1 500` followed by `settier 2 450` will not be allowed as tier 1 must have a lower point threshold
than tier 2.

### Editing a person : `editc`

Edits an existing customer in the address book.

Format: `editc INDEX [ct/{ind|env}] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* Edits the customer at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `editc 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `editc 2 ct/ind n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Setting a customer's note : `setnotec`

Sets a customer's note.

Format: `setnotec INDEX nt/NOTE`

* Sets the note of the customer at the specified `INDEX` to `NOTE`.
* Customers, by default, have a blank note.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​
* The note refers to the customer's new note.
* Any existing note will be overwritten.
* A customer's note can be removed by setting an empty note.

Examples:
* `listc` followed by `setnotec 2 nt/Very friendly!` sets the 2nd customer's note as "Very friendly!".
* `listc` followed by `setnotec 2 nt/` removes the 2nd customer's note.
* `findc Betsy` followed by `setnotec 1 nt/Vegetarian` sets the 1st customer's note as "Vegetarian" in the results of the `findc` command.

### Appending a customer's note : `appendnotec`

Adds more text to a customer's note.

Format: `appendnotec INDEX nt/NOTE`

* Adds `NOTE` to any existing note of the customer at the specified `INDEX`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​
* The note refers to the new text that will be added to the end of the customer's existing note.

Examples:
* `listc` followed by `appendnotec 2 nt/Very friendly!` will add "Very friendly!" to the 2nd customer's existing note.
* `findc Betsy` followed by `appendnotec 1 nt/Vegetarian` adds "Vegetarian" to the note of the 1st customer in the results of the `findc` command.

### Locating persons by name: `findc`

Finds persons whose names contain any of the given keywords.

Format: `findc KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findc John` returns `john` and `John Doe`
* `findc alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'findc alex david'](images/findAlexDavidResult.png)

### Adding an order : `addo`

Adds an order tagged to a customer, to the list of orders.

Format: `addo CUSTOMER_INDEX n/NAME [q/QUANTITY] [a/ADDRESS]`

* Adds an order, tagged to CUSTOMER_INDEX.
* ADDRESS is optional and will be set to the customer's address by default
* The index **must be a valid integer** (i.e. if there is 1 customer, CUSTOMER_INDEX can only be 1)
* QUANTITY is optional and will be set to 1 by default. Otherwise, it must be a positive integer 1, 2, 3...

Examples:
* addo 1 n/Banana Cake 1 q/2
  * Adds the order, 2 x Banana cakes to the list, tags it to customer 1, and status is "pending", address is the customer's address.
* addo 4 n/Strawberry ice cream
  * Adds the order 1 x Strawberry ice cream, tags it to customer 4, and sets status to "pending", address is the customer's address.

### Listing all orders : `listo`

Shows a list of all orders.

Format: `listo [s/{created|name|status}] [f/STATUS]`

* Lists all orders with the specified sorting option.
* By default, orders are sorted by their created date.
* If `f/STATUS` is provided, then show only the given status.
* `STATUS` is case-insensitive and can be one of: pending, paid, shipped, completed, cancelled.

Examples:
* `listo` lists all orders sorted by created date.
* `listo s/status` lists all orders sorted by status.
* `listo f/pending` lists all orders with "pending" status.

### Editing an order : `edito`

Edits an existing order in LoyaltyLift.

Format: `edito ORDER_INDEX [n/PRODUCT_NAME] [q/QUANTITY] [a/ADDRESS]`

* Edits the order at the specified `ORDER_INDEX`. The index refers to the index number shown in the displayed order list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edito 1 s/paid` Edits order of id 1, and changes the status to "paid".
*  `edito 2 n/Brownies q/10` Edits the name of the 2nd order to be `Brownies` and the quantity to 10.

### Setting an order's note : `setnoteo`

Sets an order's note.

Format: `setnoteo INDEX nt/NOTE`

* Sets the note of the order at the specified `INDEX` to `NOTE`.
* Orders, by default, have a blank note.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​
* The note refers to the order's new note.
* Any existing note will be overwritten.
* An order's note can be removed by setting an empty note.

Examples:
* `listo` followed by `setnoteo 2 nt/Keep cool` sets the 2nd order's note as "Keep cool".
* `listc` followed by `setnoteo 2 nt/` removes the 2nd order's note.
* `findo Brownies` followed by `setnoteo 1 nt/Vegan-friendly` sets the 1st order's note as "Vegan-friendly" in the results of the `findo` command.

### Appending an order's note : `appendnoteo`

Adds more text to an order's note.

Format: `appendnoteo INDEX nt/NOTE`

* Adds `NOTE` to any existing note of the order at the specified `INDEX`.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​
* The note refers to the new text that will be added to the end of the order's existing note.

Examples:
* `listo` followed by `appendnoteo 2 nt/Keep cool` will add "Keep cool" to the 2nd order's existing note.
* `findo Brownies` followed by `appendnoteo 1 nt/Vegan-friendly` adds "Vegan-friendly" to the note of the 1st order in the results of the `findo` command.

### Locating orders by name: `findo`

Finds orders whose names contain any of the given keywords.

Format: `findo KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `chocolate` will match `Chocolate`
* The order of the keywords does not matter. e.g. `chocolate cake` will match ` cake chocolate`.
* Only the order name is searched.
* Only full words will be matched e.g. `chocolate` will not match `chocolatey`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `chocolate cake` will return `chocolate muffin`, `crepe cake`

Examples:
* `findo chocolate` returns `chocolate cake` and `chocolate muffin`
* `findo banana muffin` returns `banana cake`, `chocolate muffin`<br>

### Deleting an order : `deleteo`

Deletes the specified person from the address book.

Format: `deleteo ORDER_INDEX`

* Deletes the order at the specified `ORDER_INDEX`.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listo` followed by `delete 2` deletes the 2nd order on the displayed order list.
* `findo chocolate` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Clearing all entries : `clear`

Clears all customers and orders from the address book.

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

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

### Customer
|                       Action | Format, Examples                                                                                                                                                             |
|-----------------------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|             **Add Customer** | `addc [ct/{ind/ent}] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `addc n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
|            **List Customer** | `listc` <br>                                                                                                                                                                 |
|            **Find Customer** | `findc KEYWORD [MORE_KEYWORDS]` <br> e.g., `findc Hans Bo`                                                                                                                   |
|            **View Customer** | `viewc INDEX` <br> e.g. `viewc 2`                                                                                                                                            |
|            **Edit Customer** | `editc INDEX [ct/{ind&#124;env}] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]` <br> e.g. `editc 1 p/91234567 e/johndoe@example.com`                                              |
|          **Delete Customer** | `deletec INDEX`<br> e.g., `deletec 3`                                                                                                                                        |
|            **Mark Customer** | `markc INDEX` <br> e.g., `markc 1`                                                                                                                                           |
|          **Unmark Customer** | `unmarkc INDEX` <br> e.g., `unmarkc 1`                                                                                                                                       |                                                                                                                                      |
|    **Set Note for Customer** | `setnotec INDEX nt/NOTE` <br> e.g., `setnotec 2 nt/Very friendly!`                                                                                                           |
| **Append Note for Customer** | `appendnotec INDEX nt/NOTE` <br> e.g., `appendnotec 2 nt/Very friendly!`                                                                                                     |

### Rewards
|              Action | Format, Examples                                                                       |
|--------------------:|----------------------------------------------------------------------------------------|
|      **Set Points** | `setpoints INDEX pt/POINTS` <br> e.g., `setpoints 2 pt/100`                            |
|      **Add Points** | `addpoints INDEX pt/[+/-]POINTS` <br> e.g., `addpoints 2 pt/100`, `addpoints 1 pt/-50` |
| **Set Reward Tier** | `settier TIER_NUM POINT_THRESHOLD` <br> e.g., `settier 1 500`                          |


### Order
|                    Action | Format, Examples                                                                                   |
|--------------------------:|----------------------------------------------------------------------------------------------------|
|             **Add Order** | `addo CUSTOMER_INDEX n/NAME [q/QUANTITY] [a/ADDRESS]` <br> e.g., `addo 1 n/Banana Cake 1 q/2`      |
|            **List Order** | `listo` <br>                                                                                       |
|            **Find Order** | `findo KEYWORD [MORE_KEYWORDS]` <br> e.g., `findo banana muffin`                                   |
|            **View Order** | `viewo INDEX` <br> e.g. `viewo 2`                                                                  |
|            **Edit Order** | `edito ORDER_INDEX [n/PRODUCT_NAME] [q/QUANTITY] [a/ADDRESS]` <br> e.g., `edito 2 n/Brownies q/10` |
|          **Delete Order** | `deleteo INDEX`<br> e.g., `deletec 3`                                                              |                                                                                                                                     |
|    **Set Note for Order** | `setnoteo INDEX nt/NOTE` <br> e.g., `setnoteo 2 nt/Very friendly!`                                 |
| **Append Note for Order** | `appendnoteo INDEX nt/NOTE` <br> e.g., `appendnoteo 2 nt/Very friendly!`                           |

### Miscellaneous
|              Action | Format, Examples                                                                                                                                                             |
|--------------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|           **Clear** | `clear`                                                                                                                                                                      |
|            **Help** | `help`                                                                                                                                                                       |
