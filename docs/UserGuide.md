---
layout: page
title: User Guide
---

ExpressLibrary is a **desktop app created for librarians to better manage library users and books, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). ExpressLibrary's user commands are simple and intuitive, making it easy for librarians to get their tasks done in a quick and efficient manner.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer. Here is a link to check which version of Java is installed in your computer: [Check Java version](https://www.java.com/en/download/help/version_manual.html) <br>
If Java is not installed, you may download the corresponding Java version with respect to your operating system here: [Download Java 11](https://www.oracle.com/sg/java/technologies/downloads/#java11) <br>
_Note that ExpressLibrary is only compatible with Linux, Windows and Mac OS._

1. Download the latest `expresslibrary-{version_num}.jar` from [here](https://github.com/AY2223S2-CS2103T-T12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ExpressLibrary.

1. Open a command terminal, `cd` into the folder you put the .jar file in, and use the `java -jar expresslibrary-{version_num}.jar` command to run the application (note: double-clicking to open the jar file may lead to issues with saved data).<br>

1. Create a new folder in a convenient location on your computer.

1. Move the `.jar` file into the new folder.

1. Open the terminal on your computer. If you are not sure how to do this, please refer to the [FAQ](#faq) section below.

1. Type `cd`, then type in the file path of the new folder you created in step 6 into the terminal window. Press enter. If you are not sure how to use the `cd` command, please refer to [https://www.javatpoint.com/linux-cd](https://www.javatpoint.com/linux-cd).

1. In the terminal window, type "java -jar expresslibrary-{version_num}.jar" (without quotes) and press enter. This will start the application and you will be able to use it. Note that double-clicking on the jar file may cause issues with saved data, so it's important to run it using the command above.

  <div style="page-break-after: always;"></div>
  
<ol start="10" > <li> In a few seconds, a graphical user interface (GUI) similar to the one shown below should appear. Please note that ExpressLibrary initially includes sample data that demonstrates how it can be used in a corporate library.
</li> </ol>

   ![Ui](images/Ui.png)

<ol start="11"> <li> <div markdown="block">
Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

Some example commands you can try:

* `listPerson` : Lists all persons in ExpressLibrary.
* `addPerson n/Bob Tan p/97450597 e/btan@mail.com`: Adds a person named `Bob Tan` to ExpressLibrary.
* `deletePerson 3` : Deletes the 3rd person shown in the current person list.
* `clear` : Clears all persons and books in the ExpressLibrary.
* `exit` : Exits the app.

</div> </li> </ol>

<ol start="12">  <li> <div markdown="block">
  Refer to the [Features](#features) below for details of each command.
</div> </li> </ol>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addPerson n/NAME`, `NAME` is a parameter which can be used as `addPerson n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Optional items in curly braces requires at least one optional item to be entered.<br>
  e.g `{[n/NAME] [t/TAG]}` can be used as `n/John Doe t/friend` or `n/John Doe` but cannot be empty.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/98102341 p/81234134`, only `p/98102341` will be taken.

* Only **Singapore** phone numbers are valid. (Must be 8 digits long and start with 6, 8 or 9.)

* Emails must abide by **RFC5322 standard**. For more details, refer to section 3.4.1 of the RFC5322 protocol found here
[https://www.rfc-editor.org/rfc/rfc5322#section-3.4.1](https://www.rfc-editor.org/rfc/rfc5322#section-3.4.1).<br>
  You can also refer to the error message which will appear if you key in an invalid email.

* Redundant parameters for commands that do not take in parameters (such as `help`, `listPerson`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Person Commands

#### Adding a user: `addPerson`

Adds a person to the ExpressLibrary.

Format: `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* You will not be able to add a person with the same email or phone number of an existing person.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:**
A person can have any number of tags. (including 0)
</div>

Examples:

* `addPerson n/Bob Tan p/91112222 e/btan@mail.com`

#### Deleting a person : `deletePerson`

Deletes a person at the specified person list index.

Format: `deletePerson PERSON_INDEX [-f]`

* Must be preceded by `listBook` command if the person to be deleted has borrowed books not shown in the book list.
* Deletes the person at the specified PERSON_INDEX.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The `-f` flag is optional and should be included in the case where the person to delete has borrowed books and you would like to return all books while deleting the person. Else, a warning message will appear when you try to delete the person without the flag.

Examples:

* `listPerson` followed by `deletePerson 2` deletes the 2nd person in the person list.
* `listPerson` followed by `deletePerson 3 -f` deletes the 3rd person in the person list and returns all books the person has borrowed, if any.

<div style="page-break-after: always;"></div>

#### Editing a person : `editPerson`

Edits an existing person in the ExpressLibrary.

Format: `editPerson PERSON_INDEX {[n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]…}​`

* Edits the person at the specified `PERSON_INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You will not be able to edit a person's email or phone number to be the same as an existing person.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:

* `editPerson 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `editPerson 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Listing all users : `listPerson`

Shows a list of all users in the person list. Mainly to be used after `findPerson` command to unfilter the person list.

Format: `listPerson`

<div style="page-break-after: always;"></div>

#### Locating persons by name: `findPerson`

Finds persons whose names contain any of the given keywords.

Format: `findPerson KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Persons matching at least one keyword will be returned (i.e. `OR` search).

  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:

* `findPerson John` returns `john` , `John Doe` and `johnston`.
* `findPerson Alex Bernice` returns `Alex Yeoh`, `Bernice Yu`.<br>

![result for 'findPerson Alex Bernice'](images/findAlexBernice.png)

<div style="page-break-after: always;"></div>

### Book Commands

#### Adding a book: `addBook`

Adds a book to the book list.

Format: `addBook t/TITLE a/AUTHOR i/ISBN​`

* You will not be able to add a book with the same ISBN of an existing book.

Examples:

* `addBook t/Diary of a Wimpy Kid a/Jeff Kinney i/9780810993136`

#### Deleting a book : `deleteBook`

Delete a book given an book index in the book list.

Format: `deleteBook BOOK_INDEX [-f]`

* Must be preceded by `listPerson` command if the person who borrowed the book is not shown in the person list.
* Deletes the book at the specified BOOK_INDEX.
* The index refers to the index number shown in the displayed book list.
* The index must be a positive integer 1, 2, 3, …
* The `-f` flag is optional and should be included in the case where the book to delete has been borrowed by a person and you would like to return the book while deleting it. Else, a warning message will appear when you try to delete the book without the flag.​

Examples:

* `listBook` followed by `deleteBook 2` deletes the 2nd book in the book list.
* `listBook` followed by `deleteBook 3 -f` deletes the 3rd book in the book list and returns it if it is borrowed.

<div style="page-break-after: always;"></div>

#### Editing a book : `editBook`

Edits an existing book in the book list.

Format: `editBook BOOK_INDEX {[t/TITLE] [a/AUTHOR] [i/ISBN] [bd/BORROW_DATE] [dd/DUE_DATE]}​`

* Edits the book at the specified `BOOK_INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* You will not be able to edit a book's ISBN to be the same as an existing book's ISBN.
* Existing values will be updated to the input values.

Examples:

* `editBook 1 t/Diary of a Wimpy Kid a/Jeff Kinney i/9780810993136` Edits the title, author and ISBN of the 1st book to be `Diary of a Wimpy Kid` and `Jeff Kinney` and `9780810993136` respectively.
* `editBook 2 bd/22/03/2023 dd/29/03/2023` Edits the borrow date and due date of the 2nd book to be `22/03/2023` and `29/03/2023`.

#### Listing all books : `listBook`

Shows a list of all books in the book list. Mainly to be used after `findBook` command to unfilter the person list.

Format: `listBook`

<div style="page-break-after: always;"></div>

#### Locate book by title: `findBook`

Find books whose titles contain any of the given keywords.

Format: `findBook KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `DUNE` will match `dune`.
* The order of the keywords does not matter. e.g. `Great Gatsby` will match `Gatsby Great`.
* Only the title is searched.
* Books matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Dune Great` will return `Dune`, `Great Gatsby`.

Examples:

* `findBook The` returns `The Cat in the Hat` and `Call of the Wild`.
* `findBook dune Cat` returns `Dune`, `The Cat in the Hat`.
* `findBook 1984 kill` returns `1984`, `To Kill a Mockingbird`.<br>

![result for 'findBook 1984 kill'](images/find1984kill.png)

<div style="page-break-after: always;"></div>

### Common Commands

#### Borrowing a book : `borrow`

Lends a book to a person given a person index, book index and due date.

Format: `borrow PERSON_INDEX b/BOOK_INDEX d/DUE_DATE`

* Lends a book specified by the BOOK_INDEX to the person at the specified PERSON_INDEX.
* The PERSON_INDEX refers to the index number shown in the displayed person list.
* The BOOK_INDEX refers to the index number shown in the displayed book list.
* The indexes **must be positive integers** 1, 2, 3, …​

Examples:

* To allow the 2nd person in the person list to borrow the 3rd book in the book list:
  * `listBook` and `listPerson` will show the lists containing the books and the stored persons.
  * `borrow 2 b/3 d/17/10/2024` allows the 2nd user in the person list to borrow the 3rd book in the book list, with a due date of Oct. 17, 2024.
* To **edit** the book's due date only:
  * Simply borrow the book again with the same person but a different due date.
  * `borrow 2 b/3 d/20/11/2024` will renew the book's due date from the previous example.

#### Returning a book : `return`

Returns a person's borrowed book to ExpressLibrary given a person index and book index.

Format: `return PERSON_INDEX b/BOOK_INDEX`

* Returns a person's borrowed book specified by the PERSON_INDEX and specified BOOK_INDEX.
* The PERSON_INDEX refers to the index number shown in the displayed person list.
* The BOOK_INDEX refers to the index number shown in the displayed book list.
* The indexes **must be positive integers** 1, 2, 3, …​

Examples:

* `listBook/listPerson` followed by `return 1 b/2` returns the 2nd book in the book list from the 1st person in the person list.

#### Clearing all entries : `clear`

Deletes all persons and books from ExpressLibrary.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Viewing help : `help`

Shows a message explaining how to access the user guide.

![helpMessage](images/helpMessage.png)

Format: `help`

<div style="page-break-after: always;"></div>

### Colour coded due dates

ExpressLibrary automatically changes the color of due dates to alert the librarian when a book’s due date is within 3 days of the current date or has passed.

![colourDates](images/colourDates.png)

As clearly seen in the image above, if the due date is within 3 days of the current date, the color of the due date will turn yellow. If the due date has passed, the color of the due date will turn orange.

<div style="page-break-after: always;"></div>

### Saving the data

ExpressLibrary data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ExpressLibrary data is saved as a JSON file `[JAR file location]/data/expresslibrary-{version_num}.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** When editing book fields when it is borrowed, make sure to update **both** the `books` array in the person object and the **main** `books` array separate from `persons` in the json file. Please refer to [_How data is saved_](https://ay2223s2-cs2103t-t12-3.github.io/tp/UserGuide.html#how-data-is-saved) for more details.

</div>

<div style="page-break-after: always;"></div>

### How data is saved

:information_source: **_For advanced users only!_**

Data in ExpressLibrary is saved in `[JAR file location]/data/expresslibrary-{version_num}.json`, consisting of an array of `persons` and `books`.

Each person in the `persons` array will contain these fields:

* `name`: Represents the name of the person.
* `phone`: Represents the phone number of the person.
* `email`: Represents the email of the person.
* `books`: Represents the set of books that the person has borrowed, will be an empty set if the person has not borrowed any.
* `tagged`: Represents the tags assigned to the person, will be an empty set if the person has no tags assigned.

<div markdown="span" class="alert alert-warning">

 :warning: **Note:** If editing the file manually, note that `books` in **each person** must be present in the main `books` array. Otherwise, the data file will be invalid.
 </div>

Each book in the `books` array will contain these fields:

* `title`: Represents the title of the book.
* `author`: Represents the author of the book.
* `isbn`: Represents the ISBN of the book.
* `borrowDate`: Represents the date in the form `dd/mm/yyyy` when the book was borrowed by a person, will be an empty string if not borrowed.
* `dueDate`: Represents the date in the form `dd/mm/yyyy` when the book has to be returned by a person, will be an empty string if not borrowed.
* `isBorrowed`: Represents whether the book is borrowed. True if borrowed and false if not.

<div markdown="span" class="alert alert-warning">

 :warning: **Note:** If editing the file manually, note that `borrowDate` and `dueDate` must be included if `isBorrowed` is true and vice versa. Otherwise, the data file will be invalid.

 <br>
 <br>

:exclamation: **Caution:**
If the changes you make to the data file cause its format to become invalid, ExpressLibrary will not be able to use it. In this case, the application will start with an empty data file the next time it is run.

</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I open the terminal on my operating system?<br>
**A**:<br>
Windows: Press `Windows key + R` and type `cmd` in the prompt.<br>
Mac OS: Press `Command + Space` and type `Terminal` in the prompt.<br>
Linux: Press `Ctrl + Alt + T`.

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ExpressLibrary home folder.

**Q**: Why can't I set due date to be in the past using the `borrow` command? What if the book was not borrowed today?<br>
**A**: Please use the `editBook` command after `borrow` if you would like to edit dates without the current date checks. `borrow` is intended to be used as if the person was borrowing the book on that day.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

### Person

Action | Format, Examples
--------|------------------
**AddPerson** | `addPerson n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​` <br> e.g., `addPerson n/James Ho p/92340121 e/jamesho@example.com t/manager t/hr`
**DeletePerson** | `deletePerson PERSON_INDEX`<br> e.g., `deletePerson 3`
**EditPerson** | `editPerson INDEX {[n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…}​`<br> e.g.,`editPerson 2 n/James Lee e/jameslee@example.com`
**FindPerson** | `findPerson KEYWORD [MORE_KEYWORDS]`<br> e.g., `findPerson James Jake`
**ListPerson** | `listPerson`

<div style="page-break-after: always;"></div>

### Book

Action | Format, Examples
--------|------------------
**AddBook** | `addBook t/TITLE a/AUTHOR i/ISBN​` <br> e.g., `addBook t/Diary of a Wimpy Kid a/Jeff Kinney i/9780810993136`
**DeleteBook** | `deleteBook BOOK_INDEX`<br> e.g., `deleteBook 3`
**EditBook** | `editBook BOOK_INDEX {[t/TITLE] [a/AUTHOR] [i/ISBN] [bd/BORROW_DATE] [dd/DUE_DATE]}`<br> e.g.,`editBook 1 t/Diary of a Wimpy Kid a/Jeff Kinney i/9780810993136`
**FindBook** | `findBook KEYWORD [MORE_KEYWORDS]...`<br> e.g., `findBook dune Cat`
**ListBook** | `listBook`

### Common

Action | Format, Examples
--------|------------------
**Borrow** | `borrow PERSON_INDEX b/BOOK_INDEX d/DUE_DATE` <br> e.g., `borrow 1 b/2 d/23/09/2024`
**Return** | `return PERSON_INDEX b/BOOK_INDEX` <br> e.g., `return 3 b/1`
**Clear** | `clear`
**Exit** | `exit`
**Help** | `help`
