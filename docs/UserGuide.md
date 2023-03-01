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
* Persons matching at least one keyword will be returned (i.e. `OR` search) e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

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