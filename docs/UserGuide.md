---
layout: page
title: User Guide
---

BookFace is a desktop GUI application for the students in the National University of Singapore (NUS) to manage the
contact information of the people they meet in the various lessons or clubs that they participate in. Since NUS Students
often take many classes and meet different people. This application helps them to organise their contacts list for an
easier way to set up proper communication channels.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add user contacts](#add-user-contacts-add)
    * [Delete user contacts](#delete-user-contacts-delete)
    * [Edit user contacts](#edit-user-contacts-edit)
    * [Locating persons by keywords](#locating-persons-by-keywords-find)
    * [Listing all contacts](#listing-all-contacts-list)
    * [Help command](#help-command-help)
    * [Add an image for contacts](#add-an-image-for-contacts-add-image)
    * [Delete an image for contacts](#delete-an-image-for-contacts-delete-image)
    * [Quick import admin contacts](#quick-import-for-faculty-contacts-import)
    * [Exit the program](#exit-the-program-exit)
    * [Editing the data file](#editing-the-data-file)
* [Command summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `bookface.jar` from [here](https://github.com/AY2223S2-CS2103-F11-4/tp/releases).
3. Place `bookface.jar` file in the folder you would like to use as the *home directory*.
4. Ensure that the *home directory* is empty and does not contain folder such as 'data'.
5. Run the application. This can be done by either executing the `jar` file, or opening a command terminal, `cd` into the *home directory*, and running the `java -jar bookface.jar` command. The following GUI will appear upon first use of the application.
   ![GUI upon first use](images/Ui.png)
6. The application is initially loaded with sample data for new users to try out the [features](#features) listed below. Experienced users can delete the sample data and proceed with regular usage.

[Back to top](#top)

---

## Features

This section highlights the core features that this application provides, as
well as the necessary guidelines to follow when using the features. The features
generally follow commands of the form `COMMAND_WORD ARGUMENTs`, with each
feature having its own specific `COMMAND_WORD` and set of `ARGUMENTS`.

> **Note:** Unless otherwise stated in the guidelines for the specific
> feature, arguments placed within brackets ("[" and "]") denotes that the
> argument is optional, and not required for the command to run properly.

### Add user contacts: `add`

Format: `add n/NAME s/STATUS p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG, ct/COMMITMENT_TAG, mt/MODULE_TAG]` Optional to
add: `t/TAG, ct/COMMITMENT_TAG, mt/MODULE_TAG`

* User is *required* to enter **name, status, phone number, email, address**
* The status field can be alphanumeric and can be used to display a contact's year and course of study
* Note that when entering course under "status", course can be more than one word
* Tags can be optional
* If the account exists, user can add in related field of interests to share with others

Example:

* `add n/Shenghan s/Year2 Computer Science p/99999999 e/david@gmail.com a/punngol place 696a #12-348` will displays the
  necessary basic information that are the user's name, year/course, phone number, email, address. Optional fields are
  tags,
  for which there are commitment/cca tags, module tags and lastly the general tags for users to enter non-specific typed
  tags.

#### Function for each tag:

`Tag`: This tag can be anything in general, such as relationship or staying on campus.

`Commitment tag`: This tag contains all the NUS CCAs that a person has joined.

`Module tag`: This tag contains all modules that a person is taking in this semester.

Example (with the addition of tags):

* `add n/Shenghan s/Year2 Computer Science p/99999999 e/david@gmail.com a/punngol place 696a #12-348 t/developer ct/soccer
  mt/cs2103`
* Note that the tags can be placed in any part of the command, and it will not break!

Tags are categorised according to tag colors:

* Commitment tags: `coral pink`
* Module tags: `dark green`
* General tags: `default blue`

[Back to top](#top)

### Delete user contacts: `delete`

Delete a contact.
Format: `delete INDEX`

* Show contact details specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …

Example:

* `delete 2` deletes the second person in the list of contacts.

[Back to top](#top)

### Edit user contacts: `edit`

Edit a contact.
Format: `edit INDEX [n/NAME] [s/STATUS] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG, ct/COMMITMENT_TAG, mt/MODULE_TAG]`

* At least one of the optional arguments must be specified.
* Show contact details specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …

Example:

* `edit 2 n/James Lee e/jameslee@example.com s/Year2 Computer Science p/99999999 e/david@gmail.com a/punngol place 696a #12-348 t/developer ct/soccer
  mt/cs2103` Edits the specified information for the second person in the contact list.

[Back to top](#top)

### Locating persons by keywords: `find`

Finds persons whose contact details contain any of the given keywords based on the
prefix specified.

Format: `find PREFIX/KEYWORD [MORE PREFIX/KEYWORD]`

* The search is case-insensitive e.g. `hans` will match `Hans`
* The search will filter by the `PREFIX` provided, e.g. `n/` searches through the
  names of the contacts, `p/` searches through the phone number of the contacts, `t/`
  searches through the tags of the contact, etc...
* Each prefix must be followed by one and only one keyword. See below for example usage.
* Only the contacts that fulfill **all** the requirements in the given
  arguments will be displayed, i.e., `find n/john t/cs` will return
  the list of contacts where his name is `john` **and** has a tag that contains `cs`.
* The following shows a list of allowed prefixes:
    1. `n/` which represents the name
    2. `s/` which represents the status
    3. `p/` which represents the phone number
    4. `e/` which represents the email
    5. `a/` which represents the address
    6. `t/` which represents the tags

Example:

* `find n/amy t/cs2103 e/gmail` will return the list of contacts whose names are `amy`,
  has a tag labeled `cs2103`, and whose emails contain `gmail`.

[Back to top](#top)

### Listing all contacts: `list`

List all contacts in the address book.

Format: `list`

[Back to top](#top)

### Help command: `help`

Shows a link to the user guide to help new users get familiar with the commands for the application.

Format: `help`

[Back to top](#top)

### Add an image for contacts: `add-image`

Add a contact image for each contact.

Format: `add-image INDEX ai/PATH-TO-IMAGE`

* Adds an image to the contact at the specified `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3,...
* Ensure that `ai/` prefix is used before the image path (note that `ai/` is not part of your path)
* Initially, a default image is used for a contact
* After an image is added, if the application is unable to retrieve the image (e.g. erroneous manual edit of
  addressbook.json or the saved images) then it will revert to the default image

Examples:

* Windows: `list` followed by `add-image 2 ai/C:/Users/user/Downloads/weekiat.png` adds the image `weekiat.png` to the
  2nd person in the address book
* MacOS: `list` followed by `add-image 2 ai//Users/user/Downloads/weekiat.png` adds the image `weekiat.png` to the 2nd
  person in the address book

#### Some FAQ Regarding Add Image

How to get Image Path?

> **Note**: Steps to get path may differ depending on your operating system and its version

* MacOS:
1. Right-click on the image file
2. Once the menu appear, hold the OPTION key (⌥) <br> The "copy" option should become "copy [file_name] as Pathname"
3. The path should be copied once you select the option

* Windows:
1. Right-click on the image file
2. Select "copy as path" option
3. The path should be copied once you select the option
> **Note:** That for Windows copied path may have quotation marks, please remove them before using in BookFace.<br>
> For instance: `add-image 2 ai/"C:/Users/user/Downloads/weekiat.png"` will be invalid, whereas
> `add-image 2 ai/C:/Users/user/Downloads/weekiat.png` will be valid.

Common User Errors

* `Referenced file does not exist` even though the image is there
    * The path used may be incorrect due to differences in Absolute and Relative path<br>
    * The 2 types of file paths are different in that:
        * Absolute path is a complete path from root to the file itself (Windows usually starts with `C:` and Linux/Mac
          with `/`)
        * Relative path starts from the [*home directory*](#quick-start)
        * Here is an external link to [learn more](https://www.computerhope.com/issues/ch001708.htm)

[Back to top](#top)

### Delete an Image for contacts: `delete-image`

Delete the image of a contact.

Format: `delete-image INDEX`

* Deletes the image of contact specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …
* A default image will be used after it is deleted

Example:

* `delete-image 2` deletes the image of the 2nd person in the address book.

[Back to top](#top)

### Quick Import for faculty contacts: `import`

Import staff contacts for relevant faculties.
> **Note**: 'staff' can refer to any person in the teaching, administrative or management teams in the faculty

Format: `import [faculty]`

* Faculty acronyms (e.g. soc)
* Only selected faculties (soc and chs) are available

Example:

* `import soc` adds all important contacts of staff for School of Computing
* `import chs` adds all important contacts of staff for College of Humanities and Sciences

[Back to top](#top)

### Exit the program: `exit`

Closes the window and exits the program.

Format: `exit`

[Back to top](#top)

### Editing the data file

It is *possible* but **not recommended** for users to manually edit any of the JSON files created by BookFace, as there is a large chance that the changes render the format of the JSON files invalid, and the application to stop working as intended. In situations where an edit to the JSON file causes the application to stop working, the user should either undo any of the changes made, or delete the JSON file entirely which will prompt BookFace to create a fresh copy. Note that the latter will erase any data that the user previously had. The developers of BookFace will not be liable for any significant loss of data as a result of tampering with the JSON files.

[Back to top](#top)

---

## Command summary

| Action           | Format, Examples                                                                                                                                                                                                              |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n/NAME s/STATUS p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG, ct/COMMITMENT_TAG, mt/MODULE_TAG]…​` <br> e.g., `add n/James Ho s/Y2 Science p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 ct/soccer mt/cs1010s` |
| **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                           |
| **Edit**         | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                   |
| **Find**         | `find PREFIX/Keyword [MORE PREFIX/KEYWORD]...`<br> e.g., `find n/amy t/cs2103 e/gmail`                                                                                                                                        |
| **List**         | `list`                                                                                                                                                                                                                        |
| **Help**         | `help`                                                                                                                                                                                                                        |
| **Add-Image**    | `add-image INDEX ai/PATH-TO-IMAGE` <br> e.g., `add-image 2 ai/C:/Users/user/Downloads/weekiat.png`                                                                                                                            |
| **Delete-Image** | `delete-image INDEX` <br> e.g.,  `delete-image 2`                                                                                                                                                                             |
| **Import**       | `import FACULTY` <br> e.g.,  `import soc, import chs`                                                                                                                                                                         |
| **Exit**         | `exit`                                                                                                                                                                                                                        |

[Back to top](#top)
