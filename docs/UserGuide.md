---

# User Guide

## What is Advis.io?

Advis.io (AIO) is a all-in-one solution for financial advisors with problems managing their clientele. AIO is currently
available on PC, and is optimized for use via a Command Line Interface (CLI) while still having the benefits of having
a Graphical User Interface (GUI). If you can type fast, AIO will be the biggest boost in your productivity that you'll
see in your career.

---

## Table of Contents

* ### How to Get Started
* ### Features
    * #### General Management
        * Viewing help : `help`
        * Undo previous operation : `undo`
        * Redo previous operation : `redo`
        * Clear all entries : `clear`
        * Exiting the program : `exit`
        * Saving the data
        * Editing the data file
    * #### Client Management
        * Adding a client: `add`
        * Selecting a client: `select`
        * Listing all clients : `list`
        * Editing a client : `edit`
        * Filtering clients by name: `find`
        * Deleting a client : `delete`
    * #### Policy Management
        * Adding a policy: `addPolicy`
        * Listing all policies : `listPolicy`
        * Editing a policy : `editPolicy`
        * Deleting a policy : `deletePolicy`
* ### FAQ
* ### Command Summary

## How to Get Started

#### 1. Ensure you have Java `11` or above installed in your Computer.

#### 2. Download the latest `advisio.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

#### 3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

#### 4. Open a command terminal, `cd` into the folder you put the jar file in, and use the

```
java -jar advisio.jar
``` 

#### command to run the application.<br>

A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

![Ui](images/UiPreview.png)

#### 5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>

Some example commands you can try:

* `list` : Lists all contacts.

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`
  to the Address Book.

* `delete 3` : Deletes the 3rd contact shown in the current list.

* `undo` : Undo the previous command `delete 3`. Thus original 3rd contact shown in list will come back

* `redo` : Redo the previous command `delete 3`. Thus 3rd contact will be deleted again from list

* `clear` : Deletes all contacts.

* `exit` : Exits the app.

#### 6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Some notes about the command format**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## General Management

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Undo previous command : `undo`

Undo one previous command and restore that version of address book

Format: `undo`

* Restore the specific address book before the command took place
* `Undo Success` will be shown in the display
* If current address book is **already the newest**, `There is no more operations to undo!` will be shown in display to
  remind of undo failure

### Redo previous command : `redo`

Redo one previous command and restore that version of address book

Format: `redo`

* Restore the specific address book before the undo command took place
* `Redo Success` will be shown in the display
* If current address book is **already the latest**, `There is no more operations to redo!` will be shown in display to
  remind of redo failure

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

## Client Management

### Adding a client: `add`

Adds a client to the list of client profiles.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Selecting a client: `select`

Selects a client to display on the client dashboard.

Format: `select INDEX`

### Listing all clients: `list`

Shows a list of all client profiles.

Format: `list`

### Editing a client : `edit`

Edits an existing client in the list of client profiles.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client
  list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be 91234567
  and johndoe@example.com respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be Betsy Crower and clears all existing tags.

### Filtering clients by name: `find`

Finds clients whose names contain any of the given keywords.

Format: `find KEYWORD \[MORE_KEYWORDS\]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`

- `find alex david` returns `Alex Yeoh, David Li`

![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a client : `delete`

Deletes the specified client from the list of client profiles.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd client in the list of clients list.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

### Adding a client: `add`

Adds a client to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all clients : `list`

Shows a list of all clients in the address book.

Format: `list`

### Editing a client : `edit`

Edits an existing client in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
  specifying any tags after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

### Filtering clients by name: `find`

Finds clients whose names contain any of the given keywords.

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

### Deleting a client : `delete`

Deletes the specified client from the address book.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd client in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

## Policy Management (todo)

### Adding a policy : `addPolicy`

Adds a policy to a specific client

Do note that the Policy Name should be from the following list:

- Health Insurance
- Life Insurance
- Medical Insurance
- Fire Insurance
- Car Insurance
- Travel Insurance

The frequency should be one of the following: `weekly`, `monthly`,`yearly`

Format: `addPolicy INDEX pn/POLICY-NAME pd/START-DATE pp/PREMIUM pf/FREQUENCY`

Examples: `addPolicy INDEX pn/Health Insurance pd/28.05.2023 pp/300 pf/monthly`

### Listing all policies : `listPolicy`

Format: `listPolicy`

### Editing a policy : `editPolicy`

Format: `editPolicy`

### Filtering policies by name : `findPolicy`

Format: `findPolicy`

### Deleting a policy `deletePolicy`

Deletes the indexed policy from a specific client

Format: `deletePolicy n/NAME INDEX`

Examples: `deletePolicy n/John Doe 1`

### Adding a claim. `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: My computer doesn't have Java 11 installed, what should I do?<br>
**A**: You can install Java 11 from [Oracle](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html). All you have to do now is find your operating systems and follow the steps given! Alternatively, you can also install Java 11 from [OpenJDK](https://openjdk.org). 

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AddressBook home folder.

**Q**: Do I need an internet connection use Advisio?<br>
**A**: Advisio is a local application that does not require an internet connection! Feel free to use our application anywhere and anytime as you see fit!

**Q**: Is my data stored on the cloud? How can I ensure that I do not lose such critical information?<br>
**A**:Unfortunately, Advisio does not  support cloud support. However, you can follow these steps to store your data safely in existing cloud services such as Icloud or Google Drive. Firstly, locate the data file `<JAR file location>/data/clientbook.json`. Secondly, copy the file into your preferred cloud service. 

**Q**: I would like to add a new policy, however the application doesn't allow me to add a policy other than those that are supported by the application. What should I do?<br>
**A**: We restricted the policies to a limited few for the convenience of Financial Advisors so that they wouldn't find themselves in a situation where they can't remember the policy that was added. The engineering team for your company should be able to add a new general policy if it's required.

**Q**: Is there a limit to the number of clients or policies that I can add?<br>
**A**: There is approximately a 2 million limit for both clients and policies separately. We have analysed and interviewed verteran Financial Adivsors and have concluded that it is very unlikely for a Financial Advisor to to exceed those thresholds. However, the limit can be expanded in the future if necessary.

**Q**: If I
--------------------------------------------------------------------------------------------------------------------

## Command summary

 Action              | Format, Examples                                                                                                                                                      
---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` 
 **Select**          | `select INDEX` <br> e.g., `select 3`                                                                                                                                  
 **Clear**           | `clear`                                                                                                                                                               
 **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   
 **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           
 **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            
 **List**            | `list`                                                                                                                                                                
 **Undo**            | `undo`                                                                                                                                                                
 **Redo**            | `redo`                                                                                                                                                                
 **Help**            | `help`                                                                                                                                                                
 **Add a Policy**    | `addPolicy INDEX pn/POLICY-NAME pd/START-DATE pp/PREMIUM pf/FREQUENCY` <br> e.g., `addPolicy INDEX pn/Health Insurance pd/28.05.2023 pp/300 pf/monthly`               
 **Delete a Policy** | `deletePolicy n/NAME INDEX` <br> e.g., `deletePolicy n/John Doe 1`                                                                                                    
