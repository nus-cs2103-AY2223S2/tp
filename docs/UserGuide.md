### Listing all contacts: `list`

List all contacts in the address book.
Format: `list`

### Locating persons by name/class/group: `find`

Finds persons whose names contain any of the given keywords.
Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive e.g. `hans` will match `Hans`
* The order of the keywords does not matter e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search) e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

### Add image for contacts

Add a contact image for each contact
Format: `add-image INDEX [NAME-OF-IMAGE]`

* Adds an image to the contact at the specified `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3,...
* The image must be placed in a specific folder for BookFace to locate
* If the image cannot be found or user did not specify a contact image, a default image will be used

Examples:

* `list` followed by `add-image 2 weekiat.png` adds the image `weekiat.png` to the 2nd person in the address book

## Delete Image for contacts

Delete image of a contact.
Format: `delete-image INDEX`

* Delete image of contact specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …
* The image must be placed in a specific folder for BookFace to locate
* A default image will be used after it is deleted
  Example:
* `delete-image` 2 deletes the image of the 2nd person in the address book.

## View Image for contacts

View image of a contact.
Format: `view-image INDEX`

* Show image of the contact specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …
* The image must be placed in a specific folder for BookFace to locate
* If the image cannot be found or user did not specify a contact image, a default image will be used
  Example:
* `view-image` 2 Brings up the image of the 2nd person in the address book.

## Quick Import for admin contacts: `import`

Import administrative contacts for relevant faculties.
Format: `import [faculty]`

* Faculty acronyms (e.g. soc)
* Only selected faculties will be available

Example:

* `import soc` adds all important administrative contact for School of Computing
* `import chs` adds all important administrative contact for College of Humanities and Sciences


## Add user contacts: `add`

Format: `add [name] [year/course] [phone number] [address]`

* User is *required* to enter name and course
* Privacy information such as phone number and address can be optionals
* If the account exists, user can add in related field of interests to share with others

Example:
* `add Shenghan Y2/Computer-science 0 punngol place 696a #12-348` will displays user's name, year/course
  and address only

## Delete user contacts: `delete`

Delete a contact.
Format: `delete INDEX`

* Show contact details specified by `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index *must* be a positive integer 1, 2, 3, …
* Extra: Will prompt user to re-confirm again before the contact is erased from BookFace
  Example:
* `delete 2` Brings up the 2nd person in the address book and prompt user to confirm before deleting.

## Help command: `help`

Show a list of command to help users to navigate around
Format: `help`

* Include list of commands to enable users to refer to in terminal.

-----------------------
