# NextBigFish

## Features:

### Viewing help : 'help' [coming soon]

Brings users to the help page.

Format: 'help'

### Adding a person: 'add' [coming soon]

Adds a person to the address book.

Format: 'add n/NAME p/PHONE\_NUMBER e/EMAIL a/ADDRESS s/BUSINESS\_SIZE c/COMPANY\_NAME [x/ACTION] [d/DEADLINE] [t/TAG]'

Examples:

- 'add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/100 c/DBS'
- 'add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 s/5000 c/Maybank t/criminal'

### Listing all persons : 'list' [coming soon]

Shows a list of all persons in the contact list.

Format: list

### Editing a person : 'edit' [coming soon]

Edits an existing person in the contact list.

Format: 'edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/BUSINESS\_SIZE] [t/TAG]…'

- Edits the person at the specified 'INDEX'. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person's tags by typing t/ without specifying any tags after it.

Examples:

- 'edit 1 p/91234567 e/johndoe@example.com' Edits the phone number and email address of the 1st person to be 91234567 and johndoe@example.com respectively.
- 'edit 2 n/Betsy Crower t/' Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.

### Filtering contacts : 'filter' [coming soon]

Filters the contact list. 

Format: 'filter [s/BUSINESS\_SIZE] [c/COMPANY\_NAME] [d/DEADLINE] [t/TAG]'

- The filter keyword must be followed by one of these: '[s/BUSINESS\_SIZE] [c/COMPANY\_NAME] [d/DEADLINE] [t/TAG]'

Examples:

- 'filter' followed by 's/\>5000' retrieves contacts with BUSINESS\_SIZE more than 5000.
- 'filter' followed by 'c/Maybank' retrieves contacts working in Maybank.
- 'filter' followed by 't/criminal' retrieves contacts that are criminal.

### Marking contacts : 'mark' [coming soon]

Marks the contact at specified index as requiring follow up action.

Format : 'mark INDEX x/ACTION d/DEADLINE'

- Marks a particular contact as requiring follow up action
- This action can be specified by a String of arbitrary length

Examples :

- 'mark 1 x/text message d/2002-03-01' marks a contact at index one as requiring a follow up text message by 1st March of 2002.

### Marking as done : 'markDone' [coming soon]

Marks the contact with specified index as having completed the follow up action.

Format : 'markDone' INDEX

Examples:

- 'markDone 3' marks the contact at index 3 as having already been followed up with.

###


### Unmarking : 'unmark' [coming soon]

Unmarks the contact with specified index. Which means the contact no longer requires any follow up action.

Format : 'unMark INDEX'

Examples:

- 'unMark 3' unmarks the contact at index 3 as not requiring any further follow up action.

### Deleting a person : 'delete' [coming soon]

Deletes the specified person from the contact list.

Format: 'delete INDEX'

- Deletes the person at the specified INDEX.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- 'delete 2' deletes the 2nd person in the address book.
- find Betsy followed by 'delete 1' deletes the 1st person in the results of the find command.

### Finding via keywords : 'find' [coming soon]

Finds the entries with the relevant keywords

Format: 'find KEYWORD1 KEYWORD2 …'

- Finds the entry/s with the keywords / else shows error message

Examples:

- 'find' followed by 'KEYWORD1 KEYWORD2 …' returns all the
- 'find Betsy' followed by delete 1 deletes the 1st person in the results of the find command.

### Clearing all entries : 'clear' [coming soon]

Clears all entries from the address book.

Format: 'clear'

### Exiting the program : 'exit' [coming soon]

Exits the program.

Format: 'exit'

### Saving the data [coming soon]

NextBigFish data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.